/**
 * Copyright (c) 2016, Geanina Mihalea <geanina.mihalea@gmail.com>.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package client.actions;

import client.util.ViewConstants;
import client.view.AboutView;
import client.view.GettingStartedView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Menu Action Class
 */
public class MenuAction {

    /**
     * Action that set all labels to empty string
     * @param labels the list of labels that should be set to empty string
     */
    public static void newAction(final ArrayList<JLabel> labels, final Component c) {
        for(JLabel label : labels) {
            label.setText(ViewConstants.EMPTY_STRING);
        }
        c.setSize(ViewConstants.FORM_WIDTH, ViewConstants.FORM_HEIGHT);
        MatchDataAction.getScrollPane().setVisible(false);
    }

    /**
     * Action that closes the entire application
     */
    public static void exitAction() {
        System.exit(0);
    }

    /**
     * Action that opens a new Getting Started Frame
     */
    public static void gettingStarted() {
        GettingStartedView gettingStartedView = new GettingStartedView();
        gettingStartedView.setVisible(true);
    }

    /**
     * Action that opens a new About Frame
     */
    public static void about() {
        AboutView aboutView = new AboutView();
        aboutView.setVisible(true);
    }
}
