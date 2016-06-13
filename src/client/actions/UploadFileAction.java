package client.actions;

import client.util.ViewConstants;
import server.core.Constraint;
import server.core.Entity;
import server.core.Item;
import server.util.Constants;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Select File class
 */
public class UploadFileAction {

    private static String mentorsFilePath;
    private static String menteesFilePath;
    private static String constraintsFilePath;

    private static ArrayList<Entity> mentors;
    private static ArrayList<Entity> mentees;
    private static ArrayList<Constraint> constraints;

    /**
     * Opens a JFileChooser and lets the user to pick a CSV file
     * @param nameLabel the label with the name of the file
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

            ArrayList<? extends Item> items = new ArrayList<>();

            switch(type) {
                case ViewConstants.MENTOR:
                    mentorsFilePath = f.getAbsolutePath();
                    items = server.parsers.CSVParser.parseCSV(mentorsFilePath, Constants.ENTITY);
                    mentors = (ArrayList<Entity>) items;
                    break;
                case ViewConstants.MENTEE:
                    menteesFilePath = f.getAbsolutePath();
                    items = server.parsers.CSVParser.parseCSV(menteesFilePath, Constants.ENTITY);
                    mentees = (ArrayList<Entity>) items;
                    break;
                case ViewConstants.CONSTRAINT:
                    constraintsFilePath = f.getAbsolutePath();
                    items = server.parsers.CSVParser.parseCSV(constraintsFilePath, Constants.CONSTRAINT);
                    constraints = (ArrayList<Constraint>) items;
                    break;
            }
            if(server.util.Checker.checkAllEntitiesCorrectness(items)) {
                nameLabel.setForeground(Color.GREEN);
            } else {
                nameLabel.setText(ViewConstants.INVALID_FILE);
                nameLabel.setForeground(Color.RED);
            }
        }
    }

    public static ArrayList<Constraint> getConstraints() {
        return constraints;
    }

    public static ArrayList<Entity> getMentees() {
        return mentees;
    }

    public static ArrayList<Entity> getMentors() {
        return mentors;
    }
}
