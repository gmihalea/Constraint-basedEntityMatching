package client.view;

import client.actions.MenuAction;
import client.actions.SelectFileAction;
import client.util.ViewConstants;

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
        setTitle(ViewConstants.TITLE);
        setSize(ViewConstants.FORM_WIDTH, ViewConstants.FORM_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

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
        JLabel mentors = new JLabel(ViewConstants.MENTORS);
        mentors.setBounds(ViewConstants.X_POSITION_LAYER_ONE, ViewConstants.Y_POSITION_LAYER_ONE,
                ViewConstants.WIDTH, ViewConstants.HEIGHT);
        this.labels.add(mentors);

        JLabel mentees = new JLabel(ViewConstants.MENTEES);
        mentees.setBounds(ViewConstants.X_POSITION_LAYER_ONE,
                ViewConstants.X_POSITION_LAYER_ONE + ViewConstants.DISTANCE_VERTICAL_BETWEEN_LAYERS,
                ViewConstants.WIDTH, ViewConstants.HEIGHT);
        this.labels.add(mentees);

        JLabel constraintsLabel = new JLabel(ViewConstants.CONSTRAINTS);
        constraintsLabel.setBounds(ViewConstants.X_POSITION_LAYER_ONE,
                ViewConstants.X_POSITION_LAYER_ONE + 2 * ViewConstants.DISTANCE_VERTICAL_BETWEEN_LAYERS,
                ViewConstants.WIDTH, ViewConstants.HEIGHT);
        this.labels.add(constraintsLabel);


        JLabel mentorsFileName = new JLabel(ViewConstants.EMPTY_STRING);
        mentorsFileName.setBounds(ViewConstants.X_POSITION_LAYER_ONE + ViewConstants.DISTANCE_HORIZONTAL_BETWEEN_LAYERS,
                ViewConstants.Y_POSITION_LAYER_ONE + ViewConstants.DISTANCE_BUTTON_LABEL,
                ViewConstants.WIDTH, ViewConstants.HEIGHT);
        mentorsFileName.setForeground(Color.GRAY);
        this.labels.add(mentorsFileName);

        JLabel menteesFileName = new JLabel(ViewConstants.EMPTY_STRING);
        menteesFileName.setBounds(ViewConstants.X_POSITION_LAYER_ONE + ViewConstants.DISTANCE_HORIZONTAL_BETWEEN_LAYERS,
                ViewConstants.Y_POSITION_LAYER_ONE + ViewConstants.DISTANCE_BUTTON_LABEL
                + ViewConstants.DISTANCE_VERTICAL_BETWEEN_LAYERS,
                ViewConstants.WIDTH, ViewConstants.HEIGHT);
        menteesFileName.setForeground(Color.GRAY);
        this.labels.add(menteesFileName);

        JLabel constraintsFileName = new JLabel(ViewConstants.EMPTY_STRING);
        constraintsFileName.setBounds(ViewConstants.X_POSITION_LAYER_ONE
                + ViewConstants.DISTANCE_HORIZONTAL_BETWEEN_LAYERS,
                ViewConstants.Y_POSITION_LAYER_ONE + ViewConstants.DISTANCE_BUTTON_LABEL
                + 2 * ViewConstants.DISTANCE_VERTICAL_BETWEEN_LAYERS,
                ViewConstants.WIDTH, ViewConstants.HEIGHT);
        constraintsFileName.setForeground(Color.GRAY);
        this.labels.add(constraintsFileName);

        JLabel mentorsFileDim = new JLabel(ViewConstants.EMPTY_STRING);
        mentorsFileDim.setBounds(ViewConstants.X_POSITION_LAYER_ONE
                + 2 * ViewConstants.DISTANCE_HORIZONTAL_BETWEEN_LAYERS,
                ViewConstants.Y_POSITION_LAYER_ONE + ViewConstants.DISTANCE_BUTTON_LABEL,
                ViewConstants.WIDTH, ViewConstants.HEIGHT);
        mentorsFileDim.setForeground(Color.GRAY);
        this.labels.add(mentorsFileDim);

        JLabel menteesFileDim = new JLabel(ViewConstants.EMPTY_STRING);
        menteesFileDim.setBounds(ViewConstants.X_POSITION_LAYER_ONE
                + 2 * ViewConstants.DISTANCE_HORIZONTAL_BETWEEN_LAYERS,
                ViewConstants.Y_POSITION_LAYER_ONE + ViewConstants.DISTANCE_BUTTON_LABEL
                + ViewConstants.DISTANCE_VERTICAL_BETWEEN_LAYERS, ViewConstants.WIDTH, ViewConstants.HEIGHT);
        menteesFileDim.setForeground(Color.GRAY);
        this.labels.add(menteesFileDim);

        JLabel constraintsFileDim = new JLabel(ViewConstants.EMPTY_STRING);
        constraintsFileDim.setBounds(ViewConstants.X_POSITION_LAYER_ONE
                + 2 * ViewConstants.DISTANCE_HORIZONTAL_BETWEEN_LAYERS,
                ViewConstants.Y_POSITION_LAYER_ONE + ViewConstants.DISTANCE_BUTTON_LABEL
                + 2 * ViewConstants.DISTANCE_VERTICAL_BETWEEN_LAYERS,
                ViewConstants.WIDTH, ViewConstants.HEIGHT);
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
        JButton browseMentors = new JButton(ViewConstants.BROWSE);
        browseMentors.setBounds(ViewConstants.X_POSITION_LAYER_ONE,
                ViewConstants.Y_POSITION_LAYER_ONE + ViewConstants.DISTANCE_BUTTON_LABEL,
                ViewConstants.BUTTON_WIDTH, ViewConstants.HEIGHT);

        JButton browseMentees = new JButton(ViewConstants.BROWSE);
        browseMentees.setBounds(ViewConstants.X_POSITION_LAYER_ONE,
                ViewConstants.Y_POSITION_LAYER_ONE + ViewConstants.DISTANCE_BUTTON_LABEL
                        + ViewConstants.DISTANCE_VERTICAL_BETWEEN_LAYERS,
                ViewConstants.BUTTON_WIDTH, ViewConstants.HEIGHT);

        JButton browseConstraints = new JButton(ViewConstants.BROWSE);
        browseConstraints.setBounds(ViewConstants.X_POSITION_LAYER_ONE,
                ViewConstants.Y_POSITION_LAYER_ONE + ViewConstants.DISTANCE_BUTTON_LABEL
                        + 2 * ViewConstants.DISTANCE_VERTICAL_BETWEEN_LAYERS,
                ViewConstants.BUTTON_WIDTH, ViewConstants.HEIGHT);

        JButton matchButton = new JButton(ViewConstants.MATCH);
        matchButton.setBounds(ViewConstants.X_POSITION_LAYER_ONE,
                ViewConstants.Y_POSITION_LAYER_ONE + ViewConstants.DISTANCE_BUTTON_LABEL
                        + 3 * ViewConstants.DISTANCE_VERTICAL_BETWEEN_LAYERS,
                ViewConstants.BUTTON_WIDTH, ViewConstants.HEIGHT);
        matchButton.setEnabled(false);

        add(browseMentors);
        add(browseMentees);
        add(browseConstraints);
        add(matchButton);

        /**
         * Actions
         */
        browseMentors.addActionListener(e -> SelectFileAction.selectFile(mentorsFileName, mentorsFileDim,
                this, ViewConstants.MENTOR));
        browseMentees.addActionListener(e -> SelectFileAction.selectFile(menteesFileName, menteesFileDim,
                this, ViewConstants.MENTEE));
        browseConstraints.addActionListener(e -> SelectFileAction.selectFile(constraintsFileName, constraintsFileDim,
                this, ViewConstants.CONSTRAINT));

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
