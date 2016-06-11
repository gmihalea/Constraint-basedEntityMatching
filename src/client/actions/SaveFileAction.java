package client.actions;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Save File Action Class
 */
public class SaveFileAction {

    public static void saveFile(final Component c) {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(c) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            // save to file
        }
    }
}
