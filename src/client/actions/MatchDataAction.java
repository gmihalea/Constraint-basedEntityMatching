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

        c.setSize(ViewConstants.FORM_WIDTH, 4 * ViewConstants.FORM_HEIGHT + 30);

        Matcher matcher = new Matcher(UploadFileAction.getMentors(),
                                      UploadFileAction.getMentees(),
                                      UploadFileAction.getConstraints());

        results =  matcher.match(Constants.SCORE_CRITERIA, 1);
        insertTableWithResults(c);
    }

    public static void insertTableWithResults(final JFrame frame) {
        final String columnNames[] = {ViewConstants.COLUMN_1, ViewConstants.COLUMN_2, ViewConstants.COLUMN_3};
        final String[][] data = new String[results.size()][ViewConstants.NO_OF_COLUMNS];

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

        matchingTable = new JTable(data, columnNames);
        matchingTable.getColumnModel().getColumn(0).setMaxWidth(30);
        matchingTable.getColumnModel().getColumn(1).setMinWidth(190);
        matchingTable.getColumnModel().getColumn(2).setMinWidth(190);
        matchingTable.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
        scrollPane = new JScrollPane(matchingTable);
        scrollPane.setBounds(ViewConstants.X_POSITION_LAYER_ONE,
                ViewConstants.Y_MATCH_BUTTON + ViewConstants.DISTANCE_VERTICAL_BETWEEN_LAYERS,
                ViewConstants.TABLE_WIDTH, ViewConstants.TABLE_HEIGHT);
        scrollPane.setVisible(true);
        frame.add(scrollPane);
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
