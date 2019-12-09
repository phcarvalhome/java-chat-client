package com.phcarvalho.model.communication.protocol.vo.command;

import com.phcarvalho.model.communication.protocol.vo.CommandTypeEnum;
import com.phcarvalho.model.configuration.entity.User;

public class SendMessageCommand extends AbstractCommand {

    private User user;
    private String message;

    public SendMessageCommand(User user, String message) {
        this.user = user;
        this.message = message;
    }

    @Override
    public CommandTypeEnum getType() {
        return CommandTypeEnum.SEND_MESSAGE;
    }

    public User getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "SendMessageCommand{" +
                "user=" + user +
                ", message='" + message + '\'' +
                '}';
    }
}
