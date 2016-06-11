package client.actions;

import client.util.Constants;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Menu Action Class
 */
public class MenuAction {

    public static void newAction(final ArrayList<JLabel> labels) {
        for(JLabel label : labels) {
            label.setText(Constants.EMPTY_STRING);
        }
    }

    public static void exitAction() {
        System.exit(0);
    }
}
