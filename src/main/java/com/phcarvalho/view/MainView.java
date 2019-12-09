package com.phcarvalho.view;

import com.phcarvalho.controller.MainController;
import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.view.listener.MainWindowAdapter;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {

    private static final String TITLE = "Chinese Checkers - Client";

    private MainController controller;
    private JPanel mainPanel;
    private JPanel topLeftPanel;
    private JPanel topRightPanel;
    private JPanel bottomLeftPanel;
    private JPanel bottomRightPanel;
    private ConnectedUserView connectedUserView;
    private ConnectionView connectionView;
    private ConsoleView consoleView;
    private ChatView chatView;

    public MainView(MainController controller) {
        this.controller = controller;
        mainPanel = new JPanel(new GridBagLayout());
        topLeftPanel = new JPanel(new GridBagLayout());
        topRightPanel = new JPanel(new GridBagLayout());
        bottomLeftPanel = new JPanel(new GridBagLayout());
        bottomRightPanel = new JPanel(new GridBagLayout());
        connectedUserView = DependencyFactory.getSingleton().get(ConnectedUserView.class);
        connectionView = DependencyFactory.getSingleton().get(ConnectionView.class);
        consoleView = DependencyFactory.getSingleton().get(ConsoleView.class);
        chatView = DependencyFactory.getSingleton().get(ChatView.class);
        initialize();
    }

    private void initialize() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        mainPanel.add(topLeftPanel, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        topLeftPanel.add(connectedUserView, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        topLeftPanel.add(consoleView, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        mainPanel.add(topRightPanel, gridBagConstraints);

//        gridBagConstraints.gridx = 0;
//        gridBagConstraints.gridy = 0;
//        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
//        topRightPanel.add(gameView, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        topRightPanel.add(chatView, gridBagConstraints);

//        gridBagConstraints.gridx = 0;
//        gridBagConstraints.gridy = 1;
//        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
//        mainPanel.add(bottomLeftPanel, gridBagConstraints);
//
//        gridBagConstraints.gridx = 0;
//        gridBagConstraints.gridy = 0;
//        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
//        bottomLeftPanel.add(connectionView, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        mainPanel.add(bottomRightPanel, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        bottomRightPanel.add(connectionView, gridBagConstraints);

        addWindowListener(new MainWindowAdapter(() -> disconnect()));
        setTitle(TITLE);
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    private void disconnect() {
        connectionView.disconnect();
    }

    public ConnectedUserView getConnectedUserView() {
        return connectedUserView;
    }

    public ConnectionView getConnectionView() {
        return connectionView;
    }

    public ConsoleView getConsoleView() {
        return consoleView;
    }

    public ChatView getChatView() {
        return chatView;
    }
}
