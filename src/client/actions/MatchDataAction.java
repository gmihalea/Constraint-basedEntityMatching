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
import server.core.Entity;
import server.core.Matcher;
import server.util.Constants;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Match Button Action
 */
public class MatchDataAction {

    private static HashMap<Entity, ArrayList<Entity>> results;
    private static JScrollPane scrollPane;
    private static JTable matchingTable;

    public static void match(final JLabel mentorsFileName, final JLabel menteesFileName,
                             final JLabel constraintsFileName, final JLabel errorMessage, final JFrame c) {
        if(ViewConstants.EMPTY_STRING.equals(mentorsFileName.getText())
                || ViewConstants.INVALID_FILE.equals(mentorsFileName.getText())

                || ViewConstants.EMPTY_STRING.equals(menteesFileName.getText())
                || ViewConstants.INVALID_FILE.equals(menteesFileName.getText())

                || ViewConstants.EMPTY_STRING.equals(constraintsFileName.getText())
                || ViewConstants.INVALID_FILE.equals(constraintsFileName.getText())) {
            errorMessage.setText(ViewConstants.ALL_FILE_MUST_BE_FILLED);
            return;
        }

        errorMessage.setText(ViewConstants.EMPTY_STRING);

        c.setSize(ViewConstants.FORM_WIDTH, 4 * ViewConstants.FORM_HEIGHT + 40);

        Matcher matcher = new Matcher(UploadFileAction.getMentors(),
                                      UploadFileAction.getMentees(),
                                      UploadFileAction.getConstraints());

        results =  matcher.match(Constants.SCORE_CRITERIA, 1);
        final long startTime = System.currentTimeMillis();
        insertTableWithResults(c);

        final long stopTime = System.currentTimeMillis();
        final long elapsedTime = stopTime - startTime;
        System.out.println("Elapsed time on the user interface: " + elapsedTime + " ms");
    }

    public static void insertTableWithResults(final JFrame frame) {
        final String columnNames[] = {ViewConstants.COLUMN_1, ViewConstants.COLUMN_2, ViewConstants.COLUMN_3};
        final String[][] data = new String[results.size()][ViewConstants.NO_OF_COLUMNS];

        final String columnMentors[] = {"No", "Mentors"};
        final String[][] unmatchedMentors = new String[Matcher.unmatchedMentors.size()][2];

        final String columnMentees[] = {"No", "Mentees"};
        final String[][] unmatchedMentees = new String[Matcher.unmatchedMentees.size()][2];

        int count = 0;
        for(Map.Entry<Entity, ArrayList<Entity>> entry : results.entrySet()){
            data[count][0] = Integer.toString(count);
            data[count][1] = entry.getKey().getAttributes().get(ViewConstants.FIRST_NAME_ATTRIBUTE).get(0)
                    + ViewConstants.SPACE + entry.getKey().getAttributes()
                    .get(ViewConstants.LAST_NAME_ATTRIBUTE).get(0);
            data[count][2] = entry.getValue().get(0).getAttributes().get(ViewConstants.FIRST_NAME_ATTRIBUTE).get(0)
                    + ViewConstants.SPACE + entry.getValue().get(0).getAttributes()
                    .get(ViewConstants.LAST_NAME_ATTRIBUTE).get(0);
            ++count;
        }

        JPanel tab1 = new JPanel();
        JPanel tab2 = new JPanel();
        JPanel tab3 = new JPanel();

        tab1.setLayout(null);
        tab2.setLayout(null);
        tab3.setLayout(null);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(ViewConstants.X_POSITION_LAYER_ONE,
                ViewConstants.Y_MATCH_BUTTON + ViewConstants.DISTANCE_VERTICAL_BETWEEN_LAYERS,
                ViewConstants.TABLE_WIDTH + 7, ViewConstants.TABLE_HEIGHT + 10);

        tabbedPane.addTab("Final Pairs", tab1);
        tabbedPane.addTab("Unmatched Mentors", tab2);
        tabbedPane.addTab("Unmatched Mentees", tab3);

        matchingTable = new JTable(data, columnNames);
        matchingTable.getColumnModel().getColumn(0).setMaxWidth(30);
        matchingTable.getColumnModel().getColumn(1).setMinWidth(190);
        matchingTable.getColumnModel().getColumn(2).setMinWidth(190);
        matchingTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        scrollPane = new JScrollPane(matchingTable);
        scrollPane.setBounds(0, 0, ViewConstants.TABLE_WIDTH, ViewConstants.TABLE_HEIGHT);
        scrollPane.setVisible(true);
        tab1.add(scrollPane);
        frame.add(tabbedPane);

        JTable mentorsTable = new JTable(unmatchedMentors, columnMentors);
        mentorsTable.getColumnModel().getColumn(0).setMaxWidth(30);
        mentorsTable.getColumnModel().getColumn(1).setMinWidth(280);

        JTable menteesTable = new JTable(unmatchedMentees, columnMentees);
        menteesTable.getColumnModel().getColumn(0).setMaxWidth(30);
        menteesTable.getColumnModel().getColumn(1).setMinWidth(280);

        JScrollPane mentorsScrollPane = new JScrollPane(mentorsTable);
        JScrollPane menteesScrollPane = new JScrollPane(menteesTable);
        mentorsScrollPane.setBounds(0, 0, ViewConstants.TABLE_WIDTH, ViewConstants.TABLE_HEIGHT);
        menteesScrollPane.setBounds(0, 0, ViewConstants.TABLE_WIDTH, ViewConstants.TABLE_HEIGHT);
        tab2.add(mentorsScrollPane);
        tab3.add(menteesScrollPane);

        count = 0;
        for(Entity entry : Matcher.unmatchedMentees){
            unmatchedMentees[count][0] = Integer.toString(count);
            unmatchedMentees[count][1] = entry.getAttributes().get(ViewConstants.FIRST_NAME_ATTRIBUTE).get(0)
                    + ViewConstants.SPACE + entry.getAttributes()
                    .get(ViewConstants.LAST_NAME_ATTRIBUTE).get(0);
            ++count;
        }

        count = 0;
        for(Entity entry : Matcher.unmatchedMentors){
            unmatchedMentors[count][0] = Integer.toString(count);
            unmatchedMentors[count][1] = entry.getAttributes().get(ViewConstants.FIRST_NAME_ATTRIBUTE).get(0)
                    + ViewConstants.SPACE + entry.getAttributes()
                    .get(ViewConstants.LAST_NAME_ATTRIBUTE).get(0);
            ++count;
        }
    }

    public static JTable getMatchingTable() {
        return matchingTable;
    }

    public static HashMap<Entity, ArrayList<Entity>> getResults() {
        return results;
    }

    public static JScrollPane getScrollPane() {
        return scrollPane;
    }
}
