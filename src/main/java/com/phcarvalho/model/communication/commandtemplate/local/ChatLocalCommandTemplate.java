package com.phcarvalho.model.communication.commandtemplate.local;

import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.model.ChatModel;
import com.phcarvalho.model.ConsoleModel;
import com.phcarvalho.model.communication.commandtemplate.IChatCommandTemplate;
import com.phcarvalho.model.communication.protocol.vo.command.BecomeOfflineCommand;
import com.phcarvalho.model.communication.protocol.vo.command.BecomeOnlineCommand;
import com.phcarvalho.model.communication.protocol.vo.command.SendMessageCommand;
import com.phcarvalho.model.configuration.Configuration;
import com.phcarvalho.model.configuration.entity.User;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChatLocalCommandTemplate extends UnicastRemoteObject implements IChatCommandTemplate {

    private ChatModel chatModel;
    private ConsoleModel consoleModel;

    public ChatLocalCommandTemplate() throws RemoteException {
        super();
        chatModel = DependencyFactory.getSingleton().get(ChatModel.class);
        consoleModel = DependencyFactory.getSingleton().get(ConsoleModel.class);
    }

    @Override
    public void sendMessage(SendMessageCommand sendMessageCommand) throws RemoteException {
        chatModel.sendMessageByCallback(sendMessageCommand);
    }

    @Override
    public void becomeOnline(BecomeOnlineCommand becomeOnlineCommand) throws RemoteException {
        User localUser = Configuration.getSingleton().getLocalUser();
        User sourceUser = becomeOnlineCommand.getSourceUser();

        if(localUser.equals(sourceUser))
            chatModel.becomeOnlineByCallback(becomeOnlineCommand);
        else
            consoleModel.becomeOnlineByCallback(becomeOnlineCommand);
    }

    @Override
    public void becomeOffline(BecomeOfflineCommand becomeOfflineCommand) throws RemoteException {
        consoleModel.becomeOfflineByCallback(becomeOfflineCommand);
    }
}
