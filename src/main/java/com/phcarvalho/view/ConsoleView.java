package com.phcarvalho.view;

import com.phcarvalho.controller.ConsoleController;
import com.phcarvalho.model.communication.protocol.vo.command.SendMessageCommand;
import com.phcarvalho.model.configuration.Configuration;
import com.phcarvalho.model.configuration.entity.User;
import com.phcarvalho.model.util.LogUtil;
import com.phcarvalho.view.listener.ChatTextPaneKeyListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.rmi.RemoteException;

public class ConsoleView extends JPanel {

    private static final String TITLE = "Console";
    private static final int WIDTH = 280;
    private static final int HEIGHT = 120;

    private ConsoleController controller;
    private MainView mainView;
    private JTextPane displayTextPane;

    public ConsoleView(ConsoleController controller) {
        super(new GridBagLayout());
        this.controller = controller;
        displayTextPane = new JTextPane();
        initialize();
    }

    private void initialize() {
        TitledBorder titledBorder = new TitledBorder(TITLE);

        titledBorder.setTitleJustification(TitledBorder.CENTER);
        titledBorder.setTitlePosition(TitledBorder.TOP);
        titledBorder.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        setBorder(titledBorder);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        displayTextPane.setEditable(false);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);

        JScrollPane scrollPane = new JScrollPane(displayTextPane);

        scrollPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        add(scrollPane, gridBagConstraints);

//        messageTextField.setEnabled(false);
//        messageTextField.addKeyListener(new ChatTextPaneKeyListener(() -> displayMessage()));
//        messageTextField.setPreferredSize(new Dimension(WIDTH, 30));
//        gridBagConstraints.gridx = 0;
//        gridBagConstraints.gridy = 1;
//        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
//        add(messageTextField, gridBagConstraints);
    }

    private void displayMessage(String sourceUser, String message, Color color){
        append(sourceUser + ": ", buildSimpleAttributeSetWithBold(color));
        append(message + "\n", buildSimpleAttributeSet(color));
    }

    private void append(String message, AttributeSet attributeSet) {

        try {
            int length = displayTextPane.getDocument().getLength();

            displayTextPane.setCaretPosition(length);
            displayTextPane.getDocument().insertString(length, message, attributeSet);
        } catch (BadLocationException e) {
            LogUtil.logError("Error in the console message insertion", "Insert Console Message", e);
        }
    }

    private SimpleAttributeSet buildSimpleAttributeSetWithBold(Color color) {
        SimpleAttributeSet simpleAttributeSet = buildSimpleAttributeSet(color);

        StyleConstants.setBold(simpleAttributeSet, true);

        return simpleAttributeSet;
    }

    private SimpleAttributeSet buildSimpleAttributeSet(Color color) {
        SimpleAttributeSet simpleAttributeSet = new SimpleAttributeSet();

        StyleConstants.setForeground(simpleAttributeSet, color);
        StyleConstants.setFontFamily(simpleAttributeSet, "Lucida Console");
        StyleConstants.setAlignment(simpleAttributeSet, StyleConstants.ALIGN_JUSTIFIED);

        return simpleAttributeSet;
    }

    public void displaySystemMessage(String message){
        String remoteUserName = Configuration.getSingleton().getRemoteUser().getName();

        displayMessage(remoteUserName, message, Color.BLACK);
    }

    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }
}
