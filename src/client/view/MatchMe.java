package client.view;

import client.actions.MatchButtonAction;
import client.actions.MenuAction;
import client.actions.SelectFileAction;
import client.util.Constants;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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
        setTitle(Constants.TITLE);
        setSize(Constants.FORM_WIDTH, Constants.FORM_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        /**
         * Menu Bar
         */
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu(Constants.FILE);
        JMenu helpMenu = new JMenu(Constants.HELP);
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        JMenuItem newAction = new JMenuItem(Constants.NEW);
        JMenuItem exitAction = new JMenuItem(Constants.EXIT);
        fileMenu.add(newAction);
        fileMenu.add(exitAction);

        JMenuItem gettingStartedAction = new JMenuItem(Constants.GETTING_STARTED);
        JMenuItem aboutAction = new JMenuItem(Constants.ABOUT);
        helpMenu.add(gettingStartedAction);
        helpMenu.add(aboutAction);

        /**
         * Labels
         */
        JLabel mentors = new JLabel(Constants.MENTORS);
        mentors.setBounds(Constants.X_POSITION_LAYER_ONE, Constants.Y_POSITION_LAYER_ONE,
                Constants.WIDTH, Constants.HEIGHT);
        this.labels.add(mentors);

        JLabel mentees = new JLabel(Constants.MENTEES);
        mentees.setBounds(Constants.X_POSITION_LAYER_ONE,
                Constants.X_POSITION_LAYER_ONE + Constants.DISTANCE_VERTICAL_BETWEEN_LAYERS,
                Constants.WIDTH, Constants.HEIGHT);
        this.labels.add(mentees);

        JLabel constraintsLabel = new JLabel(Constants.CONSTRAINTS);
        constraintsLabel.setBounds(Constants.X_POSITION_LAYER_ONE,
                Constants.X_POSITION_LAYER_ONE + 2 * Constants.DISTANCE_VERTICAL_BETWEEN_LAYERS,
                Constants.WIDTH, Constants.HEIGHT);
        this.labels.add(constraintsLabel);


        JLabel mentorsFileName = new JLabel(Constants.EMPTY_STRING);
        mentorsFileName.setBounds(Constants.X_POSITION_LAYER_ONE + Constants.DISTANCE_HORIZONTAL_BETWEEN_LAYERS,
                Constants.Y_POSITION_LAYER_ONE + Constants.DISTANCE_BUTTON_LABEL, Constants.WIDTH, Constants.HEIGHT);
        mentorsFileName.setForeground(Color.GRAY);
        this.labels.add(mentorsFileName);

        JLabel menteesFileName = new JLabel(Constants.EMPTY_STRING);
        menteesFileName.setBounds(Constants.X_POSITION_LAYER_ONE + Constants.DISTANCE_HORIZONTAL_BETWEEN_LAYERS,
                Constants.Y_POSITION_LAYER_ONE + Constants.DISTANCE_BUTTON_LABEL
                        + Constants.DISTANCE_VERTICAL_BETWEEN_LAYERS, Constants.WIDTH, Constants.HEIGHT);
        menteesFileName.setForeground(Color.GRAY);
        this.labels.add(menteesFileName);

        JLabel constraintsFileName = new JLabel(Constants.EMPTY_STRING);
        constraintsFileName.setBounds(Constants.X_POSITION_LAYER_ONE + Constants.DISTANCE_HORIZONTAL_BETWEEN_LAYERS,
                Constants.Y_POSITION_LAYER_ONE + Constants.DISTANCE_BUTTON_LABEL
                        + 2 * Constants.DISTANCE_VERTICAL_BETWEEN_LAYERS, Constants.WIDTH, Constants.HEIGHT);
        constraintsFileName.setForeground(Color.GRAY);
        this.labels.add(constraintsFileName);

        JLabel mentorsFileDim = new JLabel(Constants.EMPTY_STRING);
        mentorsFileDim.setBounds(Constants.X_POSITION_LAYER_ONE + 2 * Constants.DISTANCE_HORIZONTAL_BETWEEN_LAYERS,
                Constants.Y_POSITION_LAYER_ONE + Constants.DISTANCE_BUTTON_LABEL, Constants.WIDTH, Constants.HEIGHT);
        mentorsFileDim.setForeground(Color.GRAY);
        this.labels.add(mentorsFileDim);

        JLabel menteesFileDim = new JLabel(Constants.EMPTY_STRING);
        menteesFileDim.setBounds(Constants.X_POSITION_LAYER_ONE + 2 * Constants.DISTANCE_HORIZONTAL_BETWEEN_LAYERS,
                Constants.Y_POSITION_LAYER_ONE + Constants.DISTANCE_BUTTON_LABEL
                        + Constants.DISTANCE_VERTICAL_BETWEEN_LAYERS, Constants.WIDTH, Constants.HEIGHT);
        menteesFileDim.setForeground(Color.GRAY);
        this.labels.add(menteesFileDim);

        JLabel constraintsFileDim = new JLabel(Constants.EMPTY_STRING);
        constraintsFileDim.setBounds(Constants.X_POSITION_LAYER_ONE + 2 * Constants.DISTANCE_HORIZONTAL_BETWEEN_LAYERS,
                Constants.Y_POSITION_LAYER_ONE + Constants.DISTANCE_BUTTON_LABEL
                        + 2 * Constants.DISTANCE_VERTICAL_BETWEEN_LAYERS, Constants.WIDTH, Constants.HEIGHT);
        constraintsFileDim.setForeground(Color.GRAY);
        this.labels.add(constraintsFileDim);

        add(mentors);
        add(mentees);
        add(mentorsFileName);
        add(menteesFileName);
        add(mentorsFileDim);
        add(menteesFileDim);
        add(constraintsLabel);
        add(constraintsFileName);
        add(constraintsFileDim);

        /**
         * Buttons
         */
        JButton browseMentors = new JButton(Constants.BROWSE);
        browseMentors.setBounds(Constants.X_POSITION_LAYER_ONE,
                Constants.Y_POSITION_LAYER_ONE + Constants.DISTANCE_BUTTON_LABEL,
                Constants.BUTTON_WIDTH, Constants.HEIGHT);

        JButton browseMentees = new JButton(Constants.BROWSE);
        browseMentees.setBounds(Constants.X_POSITION_LAYER_ONE,
                Constants.Y_POSITION_LAYER_ONE + Constants.DISTANCE_BUTTON_LABEL
                        + Constants.DISTANCE_VERTICAL_BETWEEN_LAYERS,
                Constants.BUTTON_WIDTH, Constants.HEIGHT);

        JButton browseConstraints = new JButton(Constants.BROWSE);
        browseConstraints.setBounds(Constants.X_POSITION_LAYER_ONE,
                Constants.Y_POSITION_LAYER_ONE + Constants.DISTANCE_BUTTON_LABEL
                        + 2 * Constants.DISTANCE_VERTICAL_BETWEEN_LAYERS,
                Constants.BUTTON_WIDTH, Constants.HEIGHT);

        JButton matchButton = new JButton(Constants.MATCH);
        matchButton.setBounds(Constants.X_POSITION_LAYER_ONE,
                Constants.Y_POSITION_LAYER_ONE + Constants.DISTANCE_BUTTON_LABEL
                        + 3 * Constants.DISTANCE_VERTICAL_BETWEEN_LAYERS,
                Constants.BUTTON_WIDTH, Constants.HEIGHT);
        matchButton.setEnabled(false);

        add(browseMentors);
        add(browseMentees);
        add(browseConstraints);
        add(matchButton);

        /**
         * Actions
         */
        browseMentors.addActionListener(e -> SelectFileAction.selectFile(mentorsFileName, mentorsFileDim, this));
        browseMentees.addActionListener(e -> SelectFileAction.selectFile(menteesFileName, menteesFileDim, this));
        browseConstraints.addActionListener(e -> SelectFileAction.selectFile(constraintsFileName, constraintsFileDim, this));

        newAction.addActionListener(e -> MenuAction.newAction(this.labels));
        exitAction.addActionListener(e -> MenuAction.exitAction());

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MatchMe mainForm = new MatchMe();
            mainForm.setVisible(true);
        });
    }
}
