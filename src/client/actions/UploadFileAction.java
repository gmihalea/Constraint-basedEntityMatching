/**
 * Copyright (c) 2016, Geanina Mihalea <geanina.mihalea@gmail.com>.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

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
                nameLabel.setForeground(new Color(0, 153, 51));
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
