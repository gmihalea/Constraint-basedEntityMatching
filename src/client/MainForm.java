package client;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Class that incapsulates the user interface.
 */
public class MainForm extends JFrame{

    JPanel panel = new JPanel();
    JButton uploadMentors = new JButton("Upload Mentors");
    JButton uploadMentees = new JButton("Upload Mentees");
    JButton uploadConstraints = new JButton("Upload Constraints");

    public MainForm() {
        initUI();
    }

    public final void initUI() {
        setLayout(null);
        setTitle("MatchMe");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        /**
         * Menu Bar
         */
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        JMenu helpMenu = new JMenu("Help");
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        JMenuItem newAction = new JMenuItem("New");
        JMenuItem exitAction = new JMenuItem("Exit");
        fileMenu.add(newAction);
        fileMenu.add(exitAction);

        JMenuItem gettingStartedAction = new JMenuItem("Getting Started");
        JMenuItem aboutAction = new JMenuItem("About");
        helpMenu.add(gettingStartedAction);
        helpMenu.add(aboutAction);

        /**
         * Labels
         */
        JLabel mentors = new JLabel("Mentors");
        mentors.setBounds(30, 30, 150, 20);

        JLabel mentees = new JLabel("Mentees");
        mentees.setBounds(30, 100, 150, 20);

        JLabel constraintsLabel = new JLabel("Constraints");
        constraintsLabel.setBounds(30, 170, 150, 20);

        JLabel mentorsFileName = new JLabel("");
        mentorsFileName.setBounds(150, 50, 150, 20);
        mentorsFileName.setForeground(Color.GRAY);

        JLabel menteesFileName = new JLabel("");
        menteesFileName.setBounds(150, 120, 150, 20);
        menteesFileName.setForeground(Color.GRAY);

        JLabel constraintsFileName = new JLabel("");
        constraintsFileName.setBounds(150, 190, 150, 20);
        constraintsFileName.setForeground(Color.GRAY);

        JLabel mentorsFileDim = new JLabel("");
        mentorsFileDim.setBounds(250, 50, 150, 20);
        mentorsFileDim.setForeground(Color.GRAY);

        JLabel menteesFileDim = new JLabel("");
        menteesFileDim.setBounds(250, 120, 150, 20);
        menteesFileDim.setForeground(Color.GRAY);

        JLabel constraintsFileDim = new JLabel("");
        constraintsFileDim.setBounds(250, 190, 150, 20);
        constraintsFileDim.setForeground(Color.GRAY);

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
        JButton browseMentors = new JButton("Browse");
        browseMentors.setBounds(30, 50, 110, 25);

        JButton browseMentees = new JButton("Browse");
        browseMentees.setBounds(30, 120, 110, 25);

        JButton browseConstraints = new JButton("Browse");
        browseConstraints.setBounds(30, 190, 110, 25);

        add(browseMentors);
        add(browseMentees);
        add(browseConstraints);

        /**
         * Actions
         */
        browseMentors.addActionListener(e -> selectFile(mentorsFileName, mentorsFileDim));
        browseMentees.addActionListener(e -> selectFile(menteesFileName, menteesFileDim));
        browseConstraints.addActionListener(e -> selectFile(constraintsFileName, constraintsFileDim));
    }

    public void selectFile(final JLabel nameLabel, final JLabel dimLabel) {
        JFileChooser chooser = new JFileChooser();

        // Permit only CSV files.
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV FILES", "csv");
        chooser.setFileFilter(filter);

        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();

            nameLabel.setText(f.getName());
            dimLabel.setText(((float)f.length() / (float)1000) + "kB");

            // read  and/or display the file somehow. ....
        } else {
            // user changed their mind
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainForm mainForm = new MainForm();
            mainForm.setVisible(true);
        });
    }
}
