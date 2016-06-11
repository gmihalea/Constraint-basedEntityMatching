package client.actions;

import client.util.Constants;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Menu Action Class
 */
public class MenuAction {

    /**
     * Action that set all labels to empty string
     * @param labels the list of labels that should be set to empty string
     */
    public static void newAction(final ArrayList<JLabel> labels) {
        for(JLabel label : labels) {
            label.setText(Constants.EMPTY_STRING);
        }
    }

    /**
     * Action that closes the entire application
     */
    public static void exitAction() {
        System.exit(0);
    }
}
