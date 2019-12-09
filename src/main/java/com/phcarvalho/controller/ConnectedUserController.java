package com.phcarvalho.controller;

import com.phcarvalho.model.ConnectedUserModel;
import com.phcarvalho.model.configuration.entity.User;
import com.phcarvalho.view.ConnectedUserView;

public class ConnectedUserController {

    private ConnectedUserView view;
    private ConnectedUserModel model;

    public ConnectedUserController() {
    }

    public void initializeList() {
        view.getList().setModel(model.getList());
    }

    public void add(User user) {
        model.add(user);
    }

    public void removeByCallback(User user) {
        view.removeByCallback(user);
    }

    public int getUserIndex(User user) {
        return model.getUserIndex(user);
    }

    public void clear() {
        model.clear();
    }

    public void setView(ConnectedUserView view) {
        this.view = view;
    }

    public void setModel(ConnectedUserModel model) {
        this.model = model;
    }
}
