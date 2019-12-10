package com.phcarvalho.model.configuration.entity;

import com.phcarvalho.model.communication.protocol.vo.command.SendMessageCommand;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class ChatUserHistory {

    private User user;
    private List<SendMessageCommand> messageList;

    public ChatUserHistory(User user) {
        this.user = user;
        messageList = new LinkedList<>();
    }

    public void addMessage(SendMessageCommand sendMessageCommand){
        messageList.add(sendMessageCommand);
    }

    public User getUser() {
        return user;
    }

    public List<SendMessageCommand> getMessageList() {
        return messageList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatUserHistory that = (ChatUserHistory) o;
        return Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user);
    }

    @Override
    public String toString() {
        return "ChatUserHistory{" +
                "user=" + user +
                ", messageList=" + messageList +
                '}';
    }
}
