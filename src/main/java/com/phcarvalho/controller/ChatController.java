package com.phcarvalho.controller;

import com.phcarvalho.model.ChatModel;
import com.phcarvalho.model.communication.protocol.vo.command.SendMessageCommand;
import com.phcarvalho.model.configuration.entity.User;
import com.phcarvalho.view.ChatView;

import java.rmi.RemoteException;

public class ChatController {

    private ChatView view;
    private ChatModel model;

    public ChatController() {
    }

    public void sendMessage(SendMessageCommand sendMessageCommand) throws RemoteException {
        model.sendMessage(sendMessageCommand);
    }

    public void sendMessageByCallback(SendMessageCommand sendMessageCommand) {
        view.sendMessageByCallback(sendMessageCommand);
    }

    public void display(User targetUser){
        view.display(targetUser);
    }

    public void setView(ChatView view) {
        this.view = view;
    }

    public void setModel(ChatModel model) {
        this.model = model;
    }
}
