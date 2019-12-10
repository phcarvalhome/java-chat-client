package com.phcarvalho.model;

import com.phcarvalho.controller.ConnectedUserController;
import com.phcarvalho.model.communication.protocol.vo.command.ConnectCommand;
import com.phcarvalho.model.configuration.Configuration;
import com.phcarvalho.model.configuration.entity.User;

import javax.swing.*;

public class ConnectedUserModel {

    private ConnectedUserController controller;
    private DefaultListModel<User> list;

    public ConnectedUserModel(ConnectedUserController controller) {
        this.controller = controller;
        list = new DefaultListModel();
    }

    public void add(User user) {
        list.addElement(user);
    }

    public void clear() {
        list.clear();
    }

    public void refreshList(ConnectCommand connectCommand) {
        User localUser = Configuration.getSingleton().getLocalUser();

        clear();
        connectCommand.getConnectedUserList().forEach(connectedUser -> {

            if(!localUser.equals(connectedUser))
                add(connectedUser);
        });
    }

    public int getUserIndex(User user) {
        return list.indexOf(user);
    }

    public DefaultListModel<User> getList() {
        return list;
    }
}
