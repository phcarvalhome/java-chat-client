package com.phcarvalho.model.configuration.entity;

import java.util.Objects;

public class ChatUserHistory {

    private User user;
    private StringBuilder historyBuilder;

    public ChatUserHistory(User user) {
        this.user = user;
    }

    public void append(String message){
        historyBuilder.append(message);
    }

    public User getUser() {
        return user;
    }

    public String getHistory() {
        return historyBuilder.toString();
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
        return "ChatHistory{" +
                "user=" + user +
                ", historyBuilder=" + historyBuilder +
                '}';
    }
}
