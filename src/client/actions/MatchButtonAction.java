package client.actions;

import client.util.ViewConstants;

import javax.swing.*;
import java.awt.*;

/**
 * Match Button Action
 */
public class MatchButtonAction {

    public static void match(final JLabel mentorsFileName, final JLabel menteesFileName,
                             final JLabel constraintsFileName, final JLabel errorMessage, final Component c) {
        if(ViewConstants.EMPTY_STRING.equals(mentorsFileName.getText())
                || ViewConstants.INVALID_FILE.equals(mentorsFileName.getText())

                || ViewConstants.EMPTY_STRING.equals(menteesFileName.getText())
                || ViewConstants.INVALID_FILE.equals(menteesFileName.getText())

                || ViewConstants.EMPTY_STRING.equals(constraintsFileName.getText())
                || ViewConstants.INVALID_FILE.equals(constraintsFileName.getText())) {
            errorMessage.setText(ViewConstants.ALL_FILE_MUST_BE_FILLED);
        } else {
            errorMessage.setText(ViewConstants.EMPTY_STRING);
        }

        //c.setSize(ViewConstants.FORM_WIDTH, 2*ViewConstants.FORM_HEIGHT);
    }
}
