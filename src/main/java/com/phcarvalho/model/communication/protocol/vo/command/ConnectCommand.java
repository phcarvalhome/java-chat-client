package com.phcarvalho.model.communication.protocol.vo.command;

import com.phcarvalho.model.communication.protocol.vo.CommandTypeEnum;
import com.phcarvalho.model.configuration.entity.User;

import java.util.List;

public class ConnectCommand extends AbstractCommand {

    private User remoteUser;
    private List<User> connectedUserList;

    public ConnectCommand(User remoteUser) {
        this.remoteUser = remoteUser;
    }

    @Override
    public CommandTypeEnum getType() {
        return CommandTypeEnum.CONNECT;
    }

    public User getRemoteUser() {
        return remoteUser;
    }

    public List<User> getConnectedUserList() {
        return connectedUserList;
    }

    public void setConnectedUserList(List<User> connectedUserList) {
        this.connectedUserList = connectedUserList;
    }

    @Override
    public String toString() {
        return "ConnectCommand{" +
                "remoteUser=" + remoteUser +
                ", connectedUserList=" + connectedUserList +
                '}';
    }
}
