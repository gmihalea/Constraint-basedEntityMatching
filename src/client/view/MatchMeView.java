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

package client.view;

import client.actions.*;
import client.util.ViewConstants;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Class that incapsulates the main frame of the application.
 */
public class MatchMeView extends JFrame{

    private ArrayList<JLabel> labels = new ArrayList<>();

    public MatchMeView() {
        initUI();
    }

    public final void initUI() {
        setLayout(null);
        setTitle(ViewConstants.MATCH_ME_TITLE);
        setSize(ViewConstants.FORM_WIDTH, ViewConstants.FORM_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLocation(ViewConstants.X_TO_START, ViewConstants.Y_TO_START);
        setResizable(false);

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
        JLabel mentorsLabel = new JLabel(ViewConstants.MENTORS);
        mentorsLabel.setBounds(ViewConstants.X_POSITION_LAYER_ONE, ViewConstants.Y_POSITION_LAYER_ONE,
                ViewConstants.WIDTH, ViewConstants.HEIGHT);

        JLabel menteesLabel = new JLabel(ViewConstants.MENTEES);
        menteesLabel.setBounds(ViewConstants.X_POSITION_LAYER_ONE + ViewConstants.DISTANCE_HORIZONTAL_BETWEEN_LAYERS,
                ViewConstants.Y_POSITION_LAYER_ONE,
                ViewConstants.WIDTH, ViewConstants.HEIGHT);

        JLabel constraintsLabel = new JLabel(ViewConstants.CONSTRAINTS);
        constraintsLabel.setBounds(ViewConstants.X_POSITION_LAYER_ONE
                    + 2 * ViewConstants.DISTANCE_HORIZONTAL_BETWEEN_LAYERS, ViewConstants.Y_POSITION_LAYER_ONE,
                ViewConstants.WIDTH, ViewConstants.HEIGHT);

        JLabel mentorsFileName = new JLabel(ViewConstants.EMPTY_STRING);
        mentorsFileName.setBounds(ViewConstants.X_POSITION_LAYER_ONE,
                ViewConstants.Y_POSITION_LAYER_ONE + 2 * ViewConstants.DISTANCE_BUTTON_LABEL,
                ViewConstants.WIDTH, ViewConstants.HEIGHT);
        mentorsFileName.setForeground(Color.GRAY);
        this.labels.add(mentorsFileName);

        JLabel menteesFileName = new JLabel(ViewConstants.EMPTY_STRING);
        menteesFileName.setBounds(ViewConstants.X_POSITION_LAYER_ONE
                    + ViewConstants.DISTANCE_HORIZONTAL_BETWEEN_LAYERS,
                ViewConstants.Y_POSITION_LAYER_ONE + 2 * ViewConstants.DISTANCE_BUTTON_LABEL,
                ViewConstants.WIDTH, ViewConstants.HEIGHT);
        menteesFileName.setForeground(Color.GRAY);
        this.labels.add(menteesFileName);

        JLabel constraintsFileName = new JLabel(ViewConstants.EMPTY_STRING);
        constraintsFileName.setBounds(ViewConstants.X_POSITION_LAYER_ONE
                    + 2 * ViewConstants.DISTANCE_HORIZONTAL_BETWEEN_LAYERS,
                ViewConstants.Y_POSITION_LAYER_ONE + 2 * ViewConstants.DISTANCE_BUTTON_LABEL,
                ViewConstants.WIDTH, ViewConstants.HEIGHT);
        constraintsFileName.setForeground(Color.GRAY);
        this.labels.add(constraintsFileName);

        JLabel errorLabel = new JLabel(ViewConstants.EMPTY_STRING);
        errorLabel.setBounds(ViewConstants.X_MATCH_BUTTON, ViewConstants.Y_MATCH_BUTTON
                + ViewConstants.DISTANCE_BUTTON_LABEL, 3 * ViewConstants.BUTTON_WIDTH, ViewConstants.HEIGHT);
        errorLabel.setForeground(Color.RED);
        this.labels.add(errorLabel);

        add(mentorsLabel);
        add(menteesLabel);
        add(constraintsLabel);
        add(mentorsFileName);
        add(menteesFileName);
        add(constraintsFileName);
        add(errorLabel);

        /**
         * Buttons
         */
        JButton browseMentorsButton = new JButton(ViewConstants.BROWSE);
        browseMentorsButton.setBounds(ViewConstants.X_POSITION_LAYER_ONE,
                ViewConstants.Y_POSITION_LAYER_ONE + ViewConstants.DISTANCE_BUTTON_LABEL,
                ViewConstants.BUTTON_WIDTH, ViewConstants.HEIGHT);

        JButton browseMenteesButton = new JButton(ViewConstants.BROWSE);
        browseMenteesButton.setBounds(ViewConstants.X_POSITION_LAYER_ONE
                + ViewConstants.DISTANCE_HORIZONTAL_BETWEEN_LAYERS,
                ViewConstants.Y_POSITION_LAYER_ONE  + ViewConstants.DISTANCE_BUTTON_LABEL,
                ViewConstants.BUTTON_WIDTH, ViewConstants.HEIGHT);

        JButton browseConstraintsButton = new JButton(ViewConstants.BROWSE);
        browseConstraintsButton.setBounds(ViewConstants.X_POSITION_LAYER_ONE
                    + 2 * ViewConstants.DISTANCE_HORIZONTAL_BETWEEN_LAYERS,
                ViewConstants.Y_POSITION_LAYER_ONE  + ViewConstants.DISTANCE_BUTTON_LABEL,
                ViewConstants.BUTTON_WIDTH, ViewConstants.HEIGHT);

        JButton matchButton = new JButton(ViewConstants.MATCH);
        matchButton.setBounds(ViewConstants.X_MATCH_BUTTON, ViewConstants.Y_MATCH_BUTTON,
                2 * ViewConstants.BUTTON_WIDTH, ViewConstants.HEIGHT);

        JButton downloadResultsButton = new JButton(ViewConstants.DOWNLOAD_RESULTS);
        downloadResultsButton.setBounds(ViewConstants.X_POSITION_LAYER_ONE + ViewConstants.PADDING_X_DOWNLOAD_BUTTON,
                ViewConstants.Y_MATCH_BUTTON + ViewConstants.DISTANCE_VERTICAL_BETWEEN_LAYERS
                        + ViewConstants.TABLE_HEIGHT + ViewConstants.DISTANCE_BUTTON_BUTTON + 10,
                2 * ViewConstants.BUTTON_WIDTH, ViewConstants.HEIGHT);

        JButton showStatisticsButton = new JButton(ViewConstants.SHOW_STATISTICS);
        showStatisticsButton.setBounds(ViewConstants.X_POSITION_LAYER_ONE + ViewConstants.PADDING_X_STATISTICS_BUTTON,
                ViewConstants.Y_MATCH_BUTTON + ViewConstants.DISTANCE_VERTICAL_BETWEEN_LAYERS
                        + ViewConstants.TABLE_HEIGHT + ViewConstants.DISTANCE_BUTTON_BUTTON + 10,
                2 * ViewConstants.BUTTON_WIDTH, ViewConstants.HEIGHT);

        JButton downloadStatisticsButton = new JButton(ViewConstants.EXPORT_STATISTICS);
        downloadStatisticsButton.setBounds(ViewConstants.X_POSITION_LAYER_ONE + ViewConstants.PADDING_X_DOWNLOAD_BUTTON,
                ViewConstants.Y_MATCH_BUTTON + ViewConstants.DISTANCE_VERTICAL_BETWEEN_LAYERS
                        + ViewConstants.TABLE_HEIGHT + ViewConstants.DISTANCE_BUTTON_BUTTON - 10
                        + ViewConstants.PADDING_Y_STATISTISC_BUTTON,
                2 * ViewConstants.BUTTON_WIDTH, ViewConstants.HEIGHT);

        JButton hideStatisticsButton = new JButton(ViewConstants.HIDE_STATISTICS);
        hideStatisticsButton.setBounds(ViewConstants.X_POSITION_LAYER_ONE + ViewConstants.PADDING_X_STATISTICS_BUTTON,
                ViewConstants.Y_MATCH_BUTTON + ViewConstants.DISTANCE_VERTICAL_BETWEEN_LAYERS
                        + ViewConstants.TABLE_HEIGHT + ViewConstants.DISTANCE_BUTTON_BUTTON
                        + ViewConstants.PADDING_Y_STATISTISC_BUTTON - 10,
                2 * ViewConstants.BUTTON_WIDTH, ViewConstants.HEIGHT);

        add(browseMentorsButton);
        add(browseMenteesButton);
        add(browseConstraintsButton);
        add(matchButton);
        add(downloadResultsButton);
        add(showStatisticsButton);
        add(downloadStatisticsButton);
        add(hideStatisticsButton);

        /**
         * Actions
         */
        browseMentorsButton.addActionListener(e -> UploadFileAction.selectFile(mentorsFileName,
                this, ViewConstants.MENTOR));
        browseMenteesButton.addActionListener(e -> UploadFileAction.selectFile(menteesFileName,
                this, ViewConstants.MENTEE));
        browseConstraintsButton.addActionListener(e -> UploadFileAction.selectFile(constraintsFileName,
                this, ViewConstants.CONSTRAINT));

        newAction.addActionListener(e -> MenuAction.newAction(this.labels, this));
        exitAction.addActionListener(e -> MenuAction.exitAction());

        matchButton.addActionListener(e -> MatchDataAction.match(mentorsFileName, menteesFileName,
                                                                   constraintsFileName, errorLabel, this));
        downloadResultsButton.addActionListener(e -> ExportDataAction.saveFile(this,
                MatchDataAction.getMatchingTable(), ViewConstants.SUGGESTED_RESULTS_FILE_NAME));
        downloadStatisticsButton.addActionListener(e -> ExportDataAction.saveFile(this,
                StatisticsAction.getStatisticsTable(), ViewConstants.SUGGESTED_STATISTICS_FILE_NAME));
        showStatisticsButton.addActionListener(e -> StatisticsAction.showStatistics(this));
        hideStatisticsButton.addActionListener(e -> StatisticsAction.hideStatistics(this));
        gettingStartedAction.addActionListener(e -> MenuAction.gettingStarted());
        aboutAction.addActionListener(e -> MenuAction.about());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MatchMeView mainForm = new MatchMeView();
            mainForm.setVisible(true);
        });
    }
}
