package client;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
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
        JLabel uploadMentors = new JLabel("Upload Mentors");
        uploadMentors.setBounds(30, 30, 150, 20);

        JLabel uploadMentees = new JLabel("Upload Mentees");
        uploadMentees.setBounds(250, 30, 150, 20);

        JLabel mentorsFileName = new JLabel("");
        mentorsFileName.setBounds(30, 80, 150, 20);

        JLabel menteesFileName = new JLabel("");
        menteesFileName.setBounds(250, 80, 150, 20);

        add(uploadMentors);
        add(uploadMentees);
        add(mentorsFileName);
        add(menteesFileName);

        /**
         * Buttons
         */
        JButton browseMentors = new JButton("Browse");
        browseMentors.setBounds(30, 50, 110, 25);

        JButton browseMentees = new JButton("Browse");
        browseMentees.setBounds(250, 50, 110, 25);

        add(browseMentors);
        add(browseMentees);

        /**
         * Actions
         */
        browseMentors.addActionListener(e -> selectFile(mentorsFileName));
        browseMentees.addActionListener(e -> selectFile(menteesFileName));
    }

    public void selectFile(final JLabel label) {
        JFileChooser chooser = new JFileChooser();

        // Permit only CSV files.
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV FILES", "csv");
        chooser.setFileFilter(filter);

        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();

            label.setText(f.getName() + " ... " + (f.length() / 1000) + "kB");
            //textField_1.setText(selected.length());
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
