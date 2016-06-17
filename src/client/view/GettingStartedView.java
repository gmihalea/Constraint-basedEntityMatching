package client.view;

import client.util.ViewConstants;

import javax.swing.*;

/**
 * Class that incapsulates the Getting Started View
 */
public class GettingStartedView extends JFrame {

    private static final String content = "\n  MatchMe Workflow\n\n" +
                                          "  1) Upload Mentors file (CSV format)\n" +
                                          "  2) Upload Mentees file (CSV format)\n" +
                                          "  3) Upload Constraints file (CSV format)\n" +
                                          "  4) Press Match Button\n" +
                                          "  5) Show / Export Results in xls file format\n" +
                                          "  6) Show / Export Statistics in xls file format\n";

    public GettingStartedView() {
        initUI();
    }

    public final void initUI() {
        setLayout(null);
        setTitle(ViewConstants.GETTING_STARTED_TITLE);
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JTextArea textArea = new JTextArea(content, 20, 20);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setBounds(0,0,300,200);
        add(textArea);
    }
}
