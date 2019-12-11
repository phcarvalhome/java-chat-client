package com.phcarvalho.model.communication.protocol.vo.command;

import com.phcarvalho.model.communication.protocol.vo.CommandTypeEnum;
import com.phcarvalho.model.vo.OfflineMessageList;

public class BecomeOnlineCommand extends AbstractCommand {

    private OfflineMessageList offlineMessageList;

    @Override
    public CommandTypeEnum getType() {
        return CommandTypeEnum.BECOME_ONLINE;
    }

    public OfflineMessageList getOfflineMessageList() {
        return offlineMessageList;
    }

    public void setOfflineMessageList(OfflineMessageList offlineMessageList) {
        this.offlineMessageList = offlineMessageList;
    }

    @Override
    public String toString() {
        return "BecomeOnlineCommand{" +
                "offlineMessageList=" + offlineMessageList +
                '}';
    }
}
