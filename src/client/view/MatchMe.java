package client.view;

import client.actions.MatchButtonAction;
import client.actions.MenuAction;
import client.actions.SelectFileAction;
import client.util.ViewConstants;
import server.core.Entity;
import server.util.Constants;
import server.util.Printer;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that incapsulates the user interface.
 */
public class MatchMe extends JFrame{

    private ArrayList<JLabel> labels = new ArrayList<>();

    public MatchMe() {
        initUI();
    }

    public final void initUI() {
        setLayout(null);
        setTitle(ViewConstants.TITLE);
        setSize(ViewConstants.FORM_WIDTH, ViewConstants.FORM_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
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
        this.labels.add(mentorsLabel);

        JLabel menteesLabel = new JLabel(ViewConstants.MENTEES);
        menteesLabel.setBounds(ViewConstants.X_POSITION_LAYER_ONE + ViewConstants.DISTANCE_HORIZONTAL_BETWEEN_LAYERS,
                ViewConstants.Y_POSITION_LAYER_ONE,
                ViewConstants.WIDTH, ViewConstants.HEIGHT);
        this.labels.add(menteesLabel);

        JLabel constraintsLabel = new JLabel(ViewConstants.CONSTRAINTS);
        constraintsLabel.setBounds(ViewConstants.X_POSITION_LAYER_ONE + 2 * ViewConstants.DISTANCE_HORIZONTAL_BETWEEN_LAYERS,
                ViewConstants.Y_POSITION_LAYER_ONE,
                ViewConstants.WIDTH, ViewConstants.HEIGHT);
        this.labels.add(constraintsLabel);


        JLabel mentorsFileName = new JLabel(ViewConstants.EMPTY_STRING);
        mentorsFileName.setBounds(ViewConstants.X_POSITION_LAYER_ONE,
                ViewConstants.Y_POSITION_LAYER_ONE + 2 * ViewConstants.DISTANCE_BUTTON_LABEL,
                ViewConstants.WIDTH, ViewConstants.HEIGHT);
        mentorsFileName.setForeground(Color.GRAY);
        this.labels.add(mentorsFileName);

        JLabel menteesFileName = new JLabel(ViewConstants.EMPTY_STRING);
        menteesFileName.setBounds(ViewConstants.X_POSITION_LAYER_ONE + ViewConstants.DISTANCE_HORIZONTAL_BETWEEN_LAYERS,
                ViewConstants.Y_POSITION_LAYER_ONE + 2 * ViewConstants.DISTANCE_BUTTON_LABEL,
                ViewConstants.WIDTH, ViewConstants.HEIGHT);
        menteesFileName.setForeground(Color.GRAY);
        this.labels.add(menteesFileName);

        JLabel constraintsFileName = new JLabel(ViewConstants.EMPTY_STRING);
        constraintsFileName.setBounds(ViewConstants.X_POSITION_LAYER_ONE + 2 * ViewConstants.DISTANCE_HORIZONTAL_BETWEEN_LAYERS,
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

        JButton exportResultsButton = new JButton(ViewConstants.MATCH);
        exportResultsButton.setBounds(ViewConstants.X_MATCH_BUTTON, ViewConstants.Y_MATCH_BUTTON,
                2 * ViewConstants.BUTTON_WIDTH, ViewConstants.HEIGHT);

        JButton showStatisticsButton = new JButton(ViewConstants.MATCH);
        showStatisticsButton.setBounds(ViewConstants.X_MATCH_BUTTON, ViewConstants.Y_MATCH_BUTTON,
                2 * ViewConstants.BUTTON_WIDTH, ViewConstants.HEIGHT);

        add(browseMentorsButton);
        add(browseMenteesButton);
        add(browseConstraintsButton);
        add(matchButton);
        add(exportResultsButton);
        add(showStatisticsButton);

        /**
         * Matching Table
         */


        /**
         * Actions
         */
        browseMentorsButton.addActionListener(e -> SelectFileAction.selectFile(mentorsFileName,
                this, ViewConstants.MENTOR));
        browseMenteesButton.addActionListener(e -> SelectFileAction.selectFile(menteesFileName,
                this, ViewConstants.MENTEE));
        browseConstraintsButton.addActionListener(e -> SelectFileAction.selectFile(constraintsFileName,
                this, ViewConstants.CONSTRAINT));

        newAction.addActionListener(e -> MenuAction.newAction(this.labels));
        exitAction.addActionListener(e -> MenuAction.exitAction());

        matchButton.addActionListener(e -> MatchButtonAction.match(mentorsFileName, menteesFileName,
                                                                   constraintsFileName, errorLabel, this));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MatchMe mainForm = new MatchMe();
            mainForm.setVisible(true);
        });
    }
}
