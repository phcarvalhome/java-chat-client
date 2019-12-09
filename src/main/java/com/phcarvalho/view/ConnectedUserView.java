package com.phcarvalho.view;

import com.phcarvalho.controller.ConnectedUserController;
import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.model.configuration.entity.User;
import com.phcarvalho.view.util.DialogUtil;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class ConnectedUserView extends JPanel {

    private static final String TITLE = "Connected User";
    private static final String EMPTY_LABEL = "-";
    private static final int WIDTH = 280;
    private static final int HEIGHT = 120;

    private ConnectedUserController controller;
    private MainView mainView;
    private DialogUtil dialogUtil;
    private JList<User> list;
    private JPanel bottomPanel;
    private JLabel turnUserLabel;
    private JLabel turnUserValueLabel;

    public ConnectedUserView(ConnectedUserController controller) {
        super(new GridBagLayout());
        this.controller = controller;
        dialogUtil = DependencyFactory.getSingleton().get(DialogUtil.class);
        list = new JList();
        bottomPanel = new JPanel(new GridBagLayout());
        turnUserLabel = new JLabel("Turn user:");
        turnUserValueLabel = new JLabel(EMPTY_LABEL);
        initialize();
    }

    private void initialize() {
        TitledBorder titledBorder = new TitledBorder(TITLE);

        titledBorder.setTitleJustification(TitledBorder.CENTER);
        titledBorder.setTitlePosition(TitledBorder.TOP);
        titledBorder.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        setBorder(titledBorder);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        list.setEnabled(true);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);

        JScrollPane scrollPane = new JScrollPane(list);

        scrollPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        add(scrollPane, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        add(bottomPanel, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        bottomPanel.add(turnUserLabel, gridBagConstraints);

        turnUserValueLabel.setPreferredSize(new Dimension(140, 30));
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        bottomPanel.add(turnUserValueLabel, gridBagConstraints);
    }

    public void add(User user){
        controller.add(user);
    }

    public void removeByCallback(User user) {
        String message = String.join("",
                "The user ", user.getName(), " is DISCONNECTED!");

//        list.repaint();
        mainView.getConsoleView().displaySystemMessage(message);
    }

    public void reset() {
        controller.clear();
        turnUserValueLabel.setText(EMPTY_LABEL);
    }

    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }

    public JList getList() {
        return list;
    }
}
