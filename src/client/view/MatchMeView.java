package client.view;

import client.actions.*;
import client.util.ViewConstants;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Class that incapsulates the main frame of the application.
 */
public class MatchMeView extends JFrame{

    private ArrayList<JLabel> labels = new ArrayList<>();

    public MatchMeView() {
        initUI();
    }

    public final void initUI() {
        setLayout(null);
        setTitle(ViewConstants.MATCH_ME_TITLE);
        setSize(ViewConstants.FORM_WIDTH, ViewConstants.FORM_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLocation(ViewConstants.X_TO_START, ViewConstants.Y_TO_START);
        setResizable(false);

        /**
         * Menu Bar
         */
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu(ViewConstants.FILE);
        JMenu helpMenu = new JMenu(ViewConstants.HELP);
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        JMenuItem newAction = new JMenuItem(ViewConstants.NEW);
        JMenuItem exitAction = new JMenuItem(ViewConstants.EXIT);
        fileMenu.add(newAction);
        fileMenu.add(exitAction);

        JMenuItem gettingStartedAction = new JMenuItem(ViewConstants.GETTING_STARTED);
        JMenuItem aboutAction = new JMenuItem(ViewConstants.ABOUT);
        helpMenu.add(gettingStartedAction);
        helpMenu.add(aboutAction);

        /**
         * Labels
         */
        JLabel mentorsLabel = new JLabel(ViewConstants.MENTORS);
        mentorsLabel.setBounds(ViewConstants.X_POSITION_LAYER_ONE, ViewConstants.Y_POSITION_LAYER_ONE,
                ViewConstants.WIDTH, ViewConstants.HEIGHT);

        JLabel menteesLabel = new JLabel(ViewConstants.MENTEES);
        menteesLabel.setBounds(ViewConstants.X_POSITION_LAYER_ONE + ViewConstants.DISTANCE_HORIZONTAL_BETWEEN_LAYERS,
                ViewConstants.Y_POSITION_LAYER_ONE,
                ViewConstants.WIDTH, ViewConstants.HEIGHT);

        JLabel constraintsLabel = new JLabel(ViewConstants.CONSTRAINTS);
        constraintsLabel.setBounds(ViewConstants.X_POSITION_LAYER_ONE
                    + 2 * ViewConstants.DISTANCE_HORIZONTAL_BETWEEN_LAYERS, ViewConstants.Y_POSITION_LAYER_ONE,
                ViewConstants.WIDTH, ViewConstants.HEIGHT);

        JLabel mentorsFileName = new JLabel(ViewConstants.EMPTY_STRING);
        mentorsFileName.setBounds(ViewConstants.X_POSITION_LAYER_ONE,
                ViewConstants.Y_POSITION_LAYER_ONE + 2 * ViewConstants.DISTANCE_BUTTON_LABEL,
                ViewConstants.WIDTH, ViewConstants.HEIGHT);
        mentorsFileName.setForeground(Color.GRAY);
        this.labels.add(mentorsFileName);

        JLabel menteesFileName = new JLabel(ViewConstants.EMPTY_STRING);
        menteesFileName.setBounds(ViewConstants.X_POSITION_LAYER_ONE
                    + ViewConstants.DISTANCE_HORIZONTAL_BETWEEN_LAYERS,
                ViewConstants.Y_POSITION_LAYER_ONE + 2 * ViewConstants.DISTANCE_BUTTON_LABEL,
                ViewConstants.WIDTH, ViewConstants.HEIGHT);
        menteesFileName.setForeground(Color.GRAY);
        this.labels.add(menteesFileName);

        JLabel constraintsFileName = new JLabel(ViewConstants.EMPTY_STRING);
        constraintsFileName.setBounds(ViewConstants.X_POSITION_LAYER_ONE
                    + 2 * ViewConstants.DISTANCE_HORIZONTAL_BETWEEN_LAYERS,
                ViewConstants.Y_POSITION_LAYER_ONE + 2 * ViewConstants.DISTANCE_BUTTON_LABEL,
                ViewConstants.WIDTH, ViewConstants.HEIGHT);
        constraintsFileName.setForeground(Color.GRAY);
        this.labels.add(constraintsFileName);

        JLabel errorLabel = new JLabel(ViewConstants.EMPTY_STRING);
        errorLabel.setBounds(ViewConstants.X_MATCH_BUTTON, ViewConstants.Y_MATCH_BUTTON
                + ViewConstants.DISTANCE_BUTTON_LABEL, 3 * ViewConstants.BUTTON_WIDTH, ViewConstants.HEIGHT);
        errorLabel.setForeground(Color.RED);
        this.labels.add(errorLabel);

        add(mentorsLabel);
        add(menteesLabel);
        add(constraintsLabel);
        add(mentorsFileName);
        add(menteesFileName);
        add(constraintsFileName);
        add(errorLabel);

        /**
         * Buttons
         */
        JButton browseMentorsButton = new JButton(ViewConstants.BROWSE);
        browseMentorsButton.setBounds(ViewConstants.X_POSITION_LAYER_ONE,
                ViewConstants.Y_POSITION_LAYER_ONE + ViewConstants.DISTANCE_BUTTON_LABEL,
                ViewConstants.BUTTON_WIDTH, ViewConstants.HEIGHT);

        JButton browseMenteesButton = new JButton(ViewConstants.BROWSE);
        browseMenteesButton.setBounds(ViewConstants.X_POSITION_LAYER_ONE
                + ViewConstants.DISTANCE_HORIZONTAL_BETWEEN_LAYERS,
                ViewConstants.Y_POSITION_LAYER_ONE  + ViewConstants.DISTANCE_BUTTON_LABEL,
                ViewConstants.BUTTON_WIDTH, ViewConstants.HEIGHT);

        JButton browseConstraintsButton = new JButton(ViewConstants.BROWSE);
        browseConstraintsButton.setBounds(ViewConstants.X_POSITION_LAYER_ONE
                    + 2 * ViewConstants.DISTANCE_HORIZONTAL_BETWEEN_LAYERS,
                ViewConstants.Y_POSITION_LAYER_ONE  + ViewConstants.DISTANCE_BUTTON_LABEL,
                ViewConstants.BUTTON_WIDTH, ViewConstants.HEIGHT);

        JButton matchButton = new JButton(ViewConstants.MATCH);
        matchButton.setBounds(ViewConstants.X_MATCH_BUTTON, ViewConstants.Y_MATCH_BUTTON,
                2 * ViewConstants.BUTTON_WIDTH, ViewConstants.HEIGHT);

        JButton downloadResultsButton = new JButton(ViewConstants.DOWNLOAD_RESULTS);
        downloadResultsButton.setBounds(ViewConstants.X_POSITION_LAYER_ONE + ViewConstants.PADDING_X_DOWNLOAD_BUTTON,
                ViewConstants.Y_MATCH_BUTTON + ViewConstants.DISTANCE_VERTICAL_BETWEEN_LAYERS
                        + ViewConstants.TABLE_HEIGHT + ViewConstants.DISTANCE_BUTTON_BUTTON + 10,
                2 * ViewConstants.BUTTON_WIDTH, ViewConstants.HEIGHT);

        JButton showStatisticsButton = new JButton(ViewConstants.SHOW_STATISTICS);
        showStatisticsButton.setBounds(ViewConstants.X_POSITION_LAYER_ONE + ViewConstants.PADDING_X_STATISTICS_BUTTON,
                ViewConstants.Y_MATCH_BUTTON + ViewConstants.DISTANCE_VERTICAL_BETWEEN_LAYERS
                        + ViewConstants.TABLE_HEIGHT + ViewConstants.DISTANCE_BUTTON_BUTTON + 10,
                2 * ViewConstants.BUTTON_WIDTH, ViewConstants.HEIGHT);

        JButton downloadStatisticsButton = new JButton(ViewConstants.EXPORT_STATISTICS);
        downloadStatisticsButton.setBounds(ViewConstants.X_POSITION_LAYER_ONE + ViewConstants.PADDING_X_DOWNLOAD_BUTTON,
                ViewConstants.Y_MATCH_BUTTON + ViewConstants.DISTANCE_VERTICAL_BETWEEN_LAYERS
                        + ViewConstants.TABLE_HEIGHT + ViewConstants.DISTANCE_BUTTON_BUTTON - 10
                        + ViewConstants.PADDING_Y_STATISTISC_BUTTON,
                2 * ViewConstants.BUTTON_WIDTH, ViewConstants.HEIGHT);

        JButton hideStatisticsButton = new JButton(ViewConstants.HIDE_STATISTICS);
        hideStatisticsButton.setBounds(ViewConstants.X_POSITION_LAYER_ONE + ViewConstants.PADDING_X_STATISTICS_BUTTON,
                ViewConstants.Y_MATCH_BUTTON + ViewConstants.DISTANCE_VERTICAL_BETWEEN_LAYERS
                        + ViewConstants.TABLE_HEIGHT + ViewConstants.DISTANCE_BUTTON_BUTTON
                        + ViewConstants.PADDING_Y_STATISTISC_BUTTON - 10,
                2 * ViewConstants.BUTTON_WIDTH, ViewConstants.HEIGHT);

        add(browseMentorsButton);
        add(browseMenteesButton);
        add(browseConstraintsButton);
        add(matchButton);
        add(downloadResultsButton);
        add(showStatisticsButton);
        add(downloadStatisticsButton);
        add(hideStatisticsButton);

        /**
         * Actions
         */
        browseMentorsButton.addActionListener(e -> UploadFileAction.selectFile(mentorsFileName,
                this, ViewConstants.MENTOR));
        browseMenteesButton.addActionListener(e -> UploadFileAction.selectFile(menteesFileName,
                this, ViewConstants.MENTEE));
        browseConstraintsButton.addActionListener(e -> UploadFileAction.selectFile(constraintsFileName,
                this, ViewConstants.CONSTRAINT));

        newAction.addActionListener(e -> MenuAction.newAction(this.labels, this));
        exitAction.addActionListener(e -> MenuAction.exitAction());

        matchButton.addActionListener(e -> MatchDataAction.match(mentorsFileName, menteesFileName,
                                                                   constraintsFileName, errorLabel, this));
        downloadResultsButton.addActionListener(e -> ExportDataAction.saveFile(this,
                MatchDataAction.getMatchingTable(), ViewConstants.SUGGESTED_RESULTS_FILE_NAME));
        downloadStatisticsButton.addActionListener(e -> ExportDataAction.saveFile(this,
                StatisticsAction.getStatisticsTable(), ViewConstants.SUGGESTED_STATISTICS_FILE_NAME));
        showStatisticsButton.addActionListener(e -> StatisticsAction.showStatistics(this));
        hideStatisticsButton.addActionListener(e -> StatisticsAction.hideStatistics(this));
        gettingStartedAction.addActionListener(e -> MenuAction.gettingStarted());
        aboutAction.addActionListener(e -> MenuAction.about());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MatchMeView mainForm = new MatchMeView();
            mainForm.setVisible(true);
        });
    }
}
