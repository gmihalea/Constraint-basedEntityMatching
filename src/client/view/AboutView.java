package client.view;

import client.util.ViewConstants;

import javax.swing.*;

/**
 * Class that incapsulates the About View
 */
public class AboutView extends JFrame {

    private static final String content = "\n  MatchMe is an open source application that\n" +
                                          "  aims to make the process of matching entities\n" +
                                          "  based on constraints easier.\n\n  Contact:\n" +
                                          "  geanina.mihalea@gmail.com";

    public AboutView() {
        initUI();
    }

    public final void initUI() {
        setLayout(null);
        setTitle(ViewConstants.ABOUT_TITLE);
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
