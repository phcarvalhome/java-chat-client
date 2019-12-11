package com.phcarvalho.controller;

import com.phcarvalho.model.ConsoleModel;
import com.phcarvalho.model.communication.protocol.vo.command.BecomeOfflineCommand;
import com.phcarvalho.model.communication.protocol.vo.command.BecomeOnlineCommand;
import com.phcarvalho.model.communication.protocol.vo.command.SendMessageCommand;
import com.phcarvalho.view.ConsoleView;

import java.rmi.RemoteException;

public class ConsoleController {

    private ConsoleView view;
    private ConsoleModel model;

    public ConsoleController() {
    }

    public void becomeOnlineByCallback(BecomeOnlineCommand becomeOnlineCommand) {
        view.becomeOnlineByCallback(becomeOnlineCommand);
    }

    public void becomeOfflineByCallback(BecomeOfflineCommand becomeOfflineCommand) {
        view.becomeOfflineByCallback(becomeOfflineCommand);
    }

    public void setView(ConsoleView view) {
        this.view = view;
    }

    public void setModel(ConsoleModel model) {
        this.model = model;
    }
}
