package com.phcarvalho.model.communication.strategy;

import com.phcarvalho.model.communication.commandtemplate.IChatCommandTemplate;
import com.phcarvalho.model.communication.commandtemplate.IConnectionCommandTemplate;

public interface ICommandTemplateFactory {

    IConnectionCommandTemplate getConnection();

    IChatCommandTemplate getChat();
}
