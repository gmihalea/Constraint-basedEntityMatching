package client.actions;

import client.util.ViewConstants;

import javax.swing.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import static java.util.Arrays.asList;

/**
 * Class that incapsulates the action of pressing Statistics Button
 */
public class StatisticsAction {

    private static JScrollPane scrollPane;
    private static JTable statisticsTable;

    public static void showStatistics(final JFrame frame) {
        frame.setSize(ViewConstants.FORM_WIDTH, 4 * ViewConstants.FORM_HEIGHT);
        insertTableWithStatistics(frame);
    }

    private static void insertTableWithStatistics(final JFrame frame) {


        final String columnNames[] = {ViewConstants.STATISTICS_COLUMN_1,
                                      ViewConstants.STATISTICS_COLUMN_2,
                                      ViewConstants.STATISTICS_COLUMN_3,
                                      ViewConstants.STATISTICS_COLUMN_4};

        final String[][] data = new String[ViewConstants.STATISTICS_ROWS_NO][ViewConstants.STATISTICS_COLUMNS_NO];
        ArrayList<String> listOfAttributes = new ArrayList<>(asList(ViewConstants.STATISTICS_ATTRIBUTE_1,
                                                                    ViewConstants.STATISTICS_ATTRIBUTE_2,
                                                                    ViewConstants.STATISTICS_ATTRIBUTE_3,
                                                                    ViewConstants.STATISTICS_ATTRIBUTE_4));

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        data[0][0] = Integer.toString(0);
        data[0][1] = "Matched Mentors";
        data[0][2] = Integer.toString(MatchDataAction.getResults().size())
                + " / " + UploadFileAction.getMentors().size();
        data[0][3] = String.format("%.02f", ((float) MatchDataAction.getResults().size())
                / ((float) UploadFileAction.getMentors().size()) * 100) + " %";

        data[1][0] = Integer.toString(1);
        data[1][1] = "Matched Mantees";
        data[1][2] = Integer.toString(MatchDataAction.getResults().size())
                + " / " + (UploadFileAction.getMentees().size() + MatchDataAction.getResults().size());
        data[1][3] = String.format("%.02f", ((float) MatchDataAction.getResults().size())
                / ((float) UploadFileAction.getMentees().size()  + MatchDataAction.getResults().size()) * 100) + " %";


        for(int i = 0; i < listOfAttributes.size(); ++i) {
            data[2 + 2 * i][0] = Integer.toString(2 + 2 * i);
            data[2 + 2 * i][1] = listOfAttributes.get(i) + " Mentors";
            data[2 + 2 * i][2] = server.statistics.Statistics.getMostCommonValues(listOfAttributes.get(i),
                    UploadFileAction.getMentors()).get(0);
            data[2 + 2 * i][3] = String.format("%.02f", server.statistics.Statistics.getPercentage(listOfAttributes.get(i),
                    data[2 + 2 * i][2], UploadFileAction.getMentors())) + " %";

            data[2 + 2 * i + 1][0] = Integer.toString(2 + 2 *i + 1);
            data[2 + 2 * i + 1][1] = listOfAttributes.get(i) + " Mentees";
            data[2 + 2 * i + 1][2] = server.statistics.Statistics.getMostCommonValues(listOfAttributes.get(i),
                    UploadFileAction.getMentees()).get(0);
            data[2 + 2 * i + 1][3] = String.format("%.02f", server.statistics.Statistics.getPercentage(listOfAttributes.get(i),
                    data[2 + 2 * i + 1][2], UploadFileAction.getMentees())) + " %";
        }

        statisticsTable = new JTable(data, columnNames);
        scrollPane = new JScrollPane(statisticsTable);
        scrollPane.setBounds(ViewConstants.X_POSITION_LAYER_ONE,
                ViewConstants.Y_MATCH_BUTTON + ViewConstants.DISTANCE_VERTICAL_BETWEEN_LAYERS + 50 + ViewConstants.TABLE_HEIGHT,
                ViewConstants.TABLE_WIDTH, 150);
        frame.add(scrollPane);
    }
}
