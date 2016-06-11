package client.actions;

import client.util.Constants;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.text.DecimalFormat;

/**
 * Select File class
 */
public class SelectFile {

    public static void selectFile(final JLabel nameLabel, final JLabel dimLabel, Component c) {
        JFileChooser chooser = new JFileChooser();

        // Permit only CSV actions.
        FileNameExtensionFilter filter = new FileNameExtensionFilter(Constants.FILTER_DESCRIPTION,
                Constants.FILE_EXTENSTION);
        chooser.setFileFilter(filter);

        if (chooser.showOpenDialog(c) == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();

            // Show only one fraction digit
            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(1);

            nameLabel.setText(f.getName());
            float result = (float) f.length() / (float) Constants.K;
            dimLabel.setText(df.format(result) + Constants.KB);

            //TODO check actions !!!
            // read  and/or display the file somehow. ....
        } else {
            // user changed their mind
        }
    }
}
