package com.phcarvalho.model.communication.protocol.vo.command;

import com.phcarvalho.model.communication.protocol.vo.CommandTypeEnum;
import com.phcarvalho.model.configuration.entity.User;

import java.time.LocalDateTime;

public class SendMessageCommand extends AbstractCommand {

    private User targetUser;
    private String message;
    private LocalDateTime dateTime;

    public SendMessageCommand(User targetUser, String message) {
        this.targetUser = targetUser;
        this.message = message;
        dateTime = LocalDateTime.now();
    }

    @Override
    public CommandTypeEnum getType() {
        return CommandTypeEnum.SEND_MESSAGE;
    }

    public User getTargetUser() {
        return targetUser;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "SendMessageCommand{" +
                "targetUser=" + targetUser +
                ", message='" + message + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
