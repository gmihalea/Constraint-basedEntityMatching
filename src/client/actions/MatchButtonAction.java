package client.actions;

import client.util.ViewConstants;
import server.core.Entity;
import server.core.Matcher;
import server.util.Constants;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Match Button Action
 */
public class MatchButtonAction {

    private static HashMap<Entity, ArrayList<Entity>> results;
    private static JScrollPane scrollPane;

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

        c.setSize(ViewConstants.FORM_WIDTH, 3 * ViewConstants.FORM_HEIGHT);

        Matcher matcher = new Matcher(SelectFileAction.getMentors(),
                                      SelectFileAction.getMentees(),
                                      SelectFileAction.getConstraints());

        try {
            results =  matcher.match(Constants.SCORE_CRITERIA, 1);
            insertTableWithResults(c);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static HashMap<Entity, ArrayList<Entity>> getResults() {
        return results;
    }

    public static void insertTableWithResults(final JFrame frame) {
        String columnNames[] = {ViewConstants.COLUMN_1, ViewConstants.COLUMN_2, ViewConstants.COLUMN_3};
        String[][] data = new String[results.size()][ViewConstants.NO_OF_COLUMNS];

        int count = 0;
        for(Map.Entry<Entity, ArrayList<Entity>> entry : results.entrySet()){
            data[count][0] = Integer.toString(count);
            data[count][1] = entry.getKey().getAttributes().get("FirstName").get(0)
                    + ViewConstants.EMPTY_STRING + entry.getKey().getAttributes().get("LastName").get(0);
            data[count][2] = entry.getValue().get(0).getAttributes().get("FirstName").get(0)
                    + ViewConstants.EMPTY_STRING + entry.getValue().get(0).getAttributes().get("LastName").get(0);
            count++;
        }

        JTable matchingTable = new JTable(data, columnNames);
        scrollPane = new JScrollPane(matchingTable);
        scrollPane.setBounds(ViewConstants.X_POSITION_LAYER_ONE, ViewConstants.Y_MATCH_BUTTON + 40, 430, 380);
        frame.add(scrollPane);
    }
}
