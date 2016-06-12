package client.actions;

import client.util.ViewConstants;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.text.DecimalFormat;

/**
 * Select File class
 */
public class SelectFileAction {

    private static String mentorsFilePath;
    private static String menteesFilePath;
    private static String constraintsFilePath;

    /**
     * Opens a JFileChooser and lets the user to pick a CSV file
     * @param nameLabel the label with the name of the file
     * @param dimLabel the label with the dimension of the file
     * @param c the main frame
     */
    public static void selectFile(final JLabel nameLabel, final Component c, final String type) {
        JFileChooser chooser = new JFileChooser();

        // Permit only CSV actions.
        FileNameExtensionFilter filter = new FileNameExtensionFilter(ViewConstants.FILTER_DESCRIPTION,
                                                                     ViewConstants.FILE_EXTENSTION);
        chooser.setFileFilter(filter);

        if (chooser.showOpenDialog(c) == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();

            // Show only one fraction digit
            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(1);

            nameLabel.setText(f.getName());
            float result = (float) f.length() / (float) ViewConstants.K;

            switch(type) {
                case ViewConstants.MENTOR:
                    mentorsFilePath = f.getAbsolutePath();
                    break;
                case ViewConstants.MENTEE:
                    menteesFilePath = f.getAbsolutePath();
                    break;
                case ViewConstants.CONSTRAINT:
                    constraintsFilePath = f.getAbsolutePath();
                    break;
            }

            //TODO check actions !!!
            // read  and/or display the file somehow. ....
        } else {
            // user changed their mind
        }
    }

    public String getMenteesFilePath() {
        return menteesFilePath;
    }

    public String getConstraintsFilePath() {
        return constraintsFilePath;
    }

    public String getMentorsFilePath() {
        return mentorsFilePath;
    }
}
