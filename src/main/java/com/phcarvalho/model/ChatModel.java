package com.phcarvalho.model;

import com.phcarvalho.controller.ChatController;
import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.model.communication.protocol.vo.command.BecomeOfflineCommand;
import com.phcarvalho.model.communication.protocol.vo.command.BecomeOnlineCommand;
import com.phcarvalho.model.communication.protocol.vo.command.SendMessageCommand;
import com.phcarvalho.model.communication.strategy.ICommandTemplateFactory;
import com.phcarvalho.model.configuration.Configuration;
import com.phcarvalho.model.configuration.entity.ChatUserHistory;
import com.phcarvalho.model.configuration.entity.User;
import com.phcarvalho.model.vo.OfflineMessageList;

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

    public void becomeOnline(BecomeOnlineCommand becomeOnlineCommand) throws RemoteException {
        commandTemplateFactory.getChat().becomeOnline(becomeOnlineCommand);
    }

    public void becomeOnlineByCallback(BecomeOnlineCommand becomeOnlineCommand) {
        OfflineMessageList offlineMessageList = becomeOnlineCommand.getOfflineMessageList();

        offlineMessageList.getSendMessageCommandList().forEach(sendMessageCommand -> {
            User targetUser = sendMessageCommand.getSourceUser();
            ChatUserHistory chatHistory = Configuration.getSingleton().getChatHistory(targetUser);

            chatHistory.addMessage(sendMessageCommand);
            controller.becomeOnlineByCallbackForEachTargetUser(targetUser);
        });
    }

    public void becomeOffline(BecomeOfflineCommand becomeOfflineCommand) throws RemoteException {
        commandTemplateFactory.getChat().becomeOffline(becomeOfflineCommand);
    }
}
