package com.phcarvalho.model.communication.protocol.vo;

public enum CommandTypeEnum {

    SEND_MESSAGE(CommandTemplateEnum.CHAT, "sendMessage"),
    CONNECT(CommandTemplateEnum.CONNECTION, "connect"),
    DISCONNECT(CommandTemplateEnum.CONNECTION, "disconnect"),
    BECOME_ONLINE(CommandTemplateEnum.CHAT, "becomeOnline"),
    BECOME_OFFLINE(CommandTemplateEnum.CHAT, "becomeOffline");

    private CommandTemplateEnum template;
    private String value;

    CommandTypeEnum(CommandTemplateEnum template, String value) {
        this.template = template;
        this.value = value;
    }

    public CommandTemplateEnum getTemplate() {
        return template;
    }

    public String getValue() {
        return value;
    }
}
