package com.phcarvalho.model;

import com.phcarvalho.controller.ConsoleController;
import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.model.communication.protocol.vo.command.SendMessageCommand;
import com.phcarvalho.model.communication.strategy.ICommandTemplateFactory;

import java.rmi.RemoteException;

public class ConsoleModel {

    private ConsoleController controller;
    private ICommandTemplateFactory commandTemplateFactory;

    public ConsoleModel(ConsoleController controller) {
        this.controller = controller;
        commandTemplateFactory = DependencyFactory.getSingleton().get(ICommandTemplateFactory.class);
    }
}
