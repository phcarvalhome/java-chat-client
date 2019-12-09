package com.phcarvalho.model.communication.protocol.vo;

import com.phcarvalho.model.communication.commandtemplate.IChatCommandTemplate;
import com.phcarvalho.model.communication.commandtemplate.IConnectionCommandTemplate;

import java.rmi.Remote;

public enum CommandTemplateEnum {

    CHAT(IChatCommandTemplate.class),
    CONNECTION(IConnectionCommandTemplate.class);

    private Class<? extends Remote> templateClass;

    CommandTemplateEnum(Class<? extends Remote> templateClass) {
        this.templateClass = templateClass;
    }

    public Class<? extends Remote> getTemplateClass() {
        return templateClass;
    }
}
