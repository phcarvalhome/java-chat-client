package com.phcarvalho.model.communication.strategy;

import com.phcarvalho.model.communication.commandtemplate.IChatCommandTemplate;
import com.phcarvalho.model.communication.commandtemplate.IConnectionCommandTemplate;

public abstract class AbstractCommandTemplateFactory implements ICommandTemplateFactory {

    private IConnectionCommandTemplate connection;
    private IChatCommandTemplate chat;

    @Override
    public IConnectionCommandTemplate getConnection() {

        if(connection == null)
            connection = buildConnection();

        return connection;
    }

    @Override
    public IChatCommandTemplate getChat() {

        if(chat == null)
            chat = buildChat();

        return chat;
    }

    protected abstract IConnectionCommandTemplate buildConnection();

    protected abstract IChatCommandTemplate buildChat();
}
