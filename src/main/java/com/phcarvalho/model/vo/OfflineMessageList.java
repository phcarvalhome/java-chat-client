package com.phcarvalho.model.vo;

import com.phcarvalho.model.communication.protocol.vo.command.SendMessageCommand;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OfflineMessageList implements Serializable {

    private List<SendMessageCommand> sendMessageCommandList;

    public OfflineMessageList() {
        this.sendMessageCommandList = new ArrayList<>();
    }

    public void add(SendMessageCommand sendMessageCommand){
        sendMessageCommandList.add(sendMessageCommand);
    }

    public void clear(){
        sendMessageCommandList.clear();
    }

    public List<SendMessageCommand> getSendMessageCommandList() {
        return sendMessageCommandList;
    }

    @Override
    public String toString() {
        return "OfflineMessageList{" +
                "sendMessageCommandList=" + sendMessageCommandList +
                '}';
    }
}
