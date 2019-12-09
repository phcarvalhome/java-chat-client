package com.phcarvalho.model;

import com.phcarvalho.controller.ConnectedUserController;
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

    public int getUserIndex(User user) {
        return list.indexOf(user);
    }

    public DefaultListModel<User> getList() {
        return list;
    }
}
