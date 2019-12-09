package com.phcarvalho.model.communication.strategy.socket;

import com.phcarvalho.model.communication.commandtemplate.IChatCommandTemplate;
import com.phcarvalho.model.communication.commandtemplate.IConnectionCommandTemplate;
import com.phcarvalho.model.communication.commandtemplate.remote.socket.ChatRemoteCommandTemplate;
import com.phcarvalho.model.communication.commandtemplate.remote.socket.ConnectionRemoteCommandTemplate;
import com.phcarvalho.model.communication.strategy.AbstractCommandTemplateFactory;

public class SocketCommandTemplateFactory extends AbstractCommandTemplateFactory {

    @Override
    public IConnectionCommandTemplate buildConnection() {
        return new ConnectionRemoteCommandTemplate();
    }

    @Override
    public IChatCommandTemplate buildChat() {
        return new ChatRemoteCommandTemplate();
    }
}
