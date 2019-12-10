package com.phcarvalho.view;

import com.phcarvalho.controller.ChatController;
import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.model.communication.protocol.vo.command.SendMessageCommand;
import com.phcarvalho.model.configuration.Configuration;
import com.phcarvalho.model.configuration.entity.ChatUserHistory;
import com.phcarvalho.model.configuration.entity.User;
import com.phcarvalho.model.util.LogUtil;
import com.phcarvalho.view.listener.ChatTextPaneKeyListener;
import com.phcarvalho.view.util.DialogUtil;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.rmi.RemoteException;

public class ChatView extends JPanel {

    private static final String TITLE = "Chat";
    private static final int WIDTH = 760;
    private static final int HEIGHT = 220;
    private static final String SEND_MESSAGE = "Send Message";

    private ChatController controller;
    private MainView mainView;
    private JTextPane displayTextPane;
    private JTextField messageTextField;
    private DialogUtil dialogUtil;

    public ChatView(ChatController controller) {
        super(new GridBagLayout());
        this.controller = controller;
        displayTextPane = new JTextPane();
        messageTextField = new JTextField();
        dialogUtil = DependencyFactory.getSingleton().get(DialogUtil.class);
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

        messageTextField.setEnabled(false);
        messageTextField.addKeyListener(new ChatTextPaneKeyListener(() -> sendMessage()));
        messageTextField.setPreferredSize(new Dimension(WIDTH, 30));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        add(messageTextField, gridBagConstraints);
    }

    private void sendMessage() {
//        User user = Configuration.getSingleton().getLocalUser();
        User targetUser = mainView.getConnectedUserView().getList().getSelectedValue();

        if(targetUser != null){
            String message = messageTextField.getText();
            SendMessageCommand sendMessageCommand = new SendMessageCommand(targetUser, message);

            try {
                controller.sendMessage(sendMessageCommand);
            } catch (RemoteException e) {
                e.printStackTrace();
                //TODO add handling...
            }

            messageTextField.setText("");
        }else{
            dialogUtil.showInformation("The target user is not selected!", SEND_MESSAGE);
        }
    }

    public void sendMessageByCallback(SendMessageCommand sendMessageCommand) {
//        String sourceUser = sendMessageCommand.getSourceUser().getName();
//
//        sourceUser = sourceUser.replace(">>> ", "");
//        displayMessage(sourceUser, sendMessageCommand.getMessage(), Color.BLACK);
        User targetUser = mainView.getConnectedUserView().getList().getSelectedValue();

        if(targetUser != null && targetUser.equals(sendMessageCommand.getSourceUser()))
            display(targetUser);
    }

    private void displayMessage(String sourceUser, String message, Color color){
        append(sourceUser + ": ", buildSimpleAttributeSetWithBold(color));
        append(message + "\n", buildSimpleAttributeSet(color));
    }

    private void displayMessage(SendMessageCommand sendMessageCommand){
        //TODO formatar datetime...
        String formattedDateTime = sendMessageCommand.getDateTime().toString();

        append(sendMessageCommand.getSourceUser().getName(), buildSimpleAttributeSetWithBold(Color.BLACK));
        append(" [" + formattedDateTime + "]: ", buildSimpleAttributeSet(Color.BLACK));
        append(sendMessageCommand.getMessage() + "\n", buildSimpleAttributeSet(Color.BLACK));
    }

    public void display(User targetUser){
        ChatUserHistory chatHistory = Configuration.getSingleton().getChatHistory(targetUser);

        clearDisplay();
        chatHistory.getMessageList()
                .forEach(sendMessageCommand -> displayMessage(sendMessageCommand));
    }

    private void clearDisplay(){

        try {
            displayTextPane.getDocument().remove(0, displayTextPane.getDocument().getLength());
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    private void append(String message, AttributeSet attributeSet) {

        try {
            int length = displayTextPane.getDocument().getLength();

            displayTextPane.setCaretPosition(length);
            displayTextPane.getDocument().insertString(length, message, attributeSet);
        } catch (BadLocationException e) {
            LogUtil.logError("Error in the chat message insertion", "Insert Chat Message", e);
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

    public void setTargetUser(User targetUser){
        TitledBorder titledBorder = new TitledBorder(targetUser.getName());

        titledBorder.setTitleJustification(TitledBorder.CENTER);
        titledBorder.setTitlePosition(TitledBorder.TOP);
        titledBorder.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        setBorder(titledBorder);
        display(targetUser);
    }

    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }

    public JTextField getMessageTextField() {
        return messageTextField;
    }
}
