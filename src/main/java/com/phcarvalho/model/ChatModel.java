package com.phcarvalho.model;

import com.phcarvalho.controller.ChatController;
import com.phcarvalho.controller.ConsoleController;
import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.model.communication.protocol.vo.command.SendMessageCommand;
import com.phcarvalho.model.communication.strategy.ICommandTemplateFactory;
import com.phcarvalho.model.configuration.Configuration;
import com.phcarvalho.model.configuration.entity.ChatUserHistory;
import com.phcarvalho.model.configuration.entity.User;

import java.rmi.RemoteException;

public class ChatModel {

    private ChatController controller;
    private ICommandTemplateFactory commandTemplateFactory;

    public ChatModel(ChatController controller) {
        this.controller = controller;
        commandTemplateFactory = DependencyFactory.getSingleton().get(ICommandTemplateFactory.class);
    }

    public void sendMessage(SendMessageCommand sendMessageCommand) throws RemoteException {
        User targetUser = sendMessageCommand.getTargetUser();
        ChatUserHistory chatHistory = Configuration.getSingleton().getChatHistory(targetUser);

        chatHistory.addMessage(sendMessageCommand);
        controller.display(targetUser);
        commandTemplateFactory.getChat().sendMessage(sendMessageCommand);
    }

    public void sendMessageByCallback(SendMessageCommand sendMessageCommand) {
        User sourceUser = sendMessageCommand.getSourceUser();
        ChatUserHistory chatHistory = Configuration.getSingleton().getChatHistory(sourceUser);

        chatHistory.addMessage(sendMessageCommand);
        controller.sendMessageByCallback(sendMessageCommand);
    }
}
