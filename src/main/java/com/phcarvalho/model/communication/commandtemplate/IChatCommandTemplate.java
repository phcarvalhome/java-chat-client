package com.phcarvalho.model.communication.commandtemplate;

import com.phcarvalho.model.communication.protocol.vo.command.BecomeOfflineCommand;
import com.phcarvalho.model.communication.protocol.vo.command.BecomeOnlineCommand;
import com.phcarvalho.model.communication.protocol.vo.command.SendMessageCommand;

import java.rmi.RemoteException;

public interface IChatCommandTemplate extends ICommandTemplate {

    void sendMessage(SendMessageCommand sendMessageCommand) throws RemoteException;

    void becomeOnline(BecomeOnlineCommand becomeOnlineCommand) throws RemoteException;

    void becomeOffline(BecomeOfflineCommand becomeOfflineCommand) throws RemoteException;
}
