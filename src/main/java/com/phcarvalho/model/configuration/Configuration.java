package com.phcarvalho.model.configuration;

import com.phcarvalho.model.configuration.entity.ChatUserHistory;
import com.phcarvalho.model.configuration.entity.User;

import java.util.HashMap;
import java.util.Map;

public class Configuration {

    private static Configuration singleton;

    public static Configuration getSingleton(){

        if(singleton == null)
            singleton = new Configuration();

        return singleton;
    }

    private User localUser;
    private User remoteUser;
    private Map<User, ChatUserHistory> chatUserHistoryMap;
    private boolean serverConnected;

    private Configuration() {
        chatUserHistoryMap = new HashMap<>();
        serverConnected = false;
    }

    public void addChatHistory(User user){
        chatUserHistoryMap.put(user, new ChatUserHistory(user));
    }

    public void removeChatHistory(User user){
        chatUserHistoryMap.remove(user);
    }

    public ChatUserHistory getChatHistory(User user){
        return chatUserHistoryMap.get(user);
    }

    public void clear(){
//        gameMap = new HashMap<>();
        serverConnected = false;
    }

    public User getLocalUser() {

//        if(localUser == null)
//            throw new RuntimeException("The localUser field is null!");

        return localUser;
    }

    public void setLocalUser(User localUser) {
        this.localUser = localUser;
    }

    public User getRemoteUser() {

        if(remoteUser == null)
            throw new RuntimeException("The remoteUser field is null!");

        return remoteUser;
    }

    public void setRemoteUser(User remoteUser) {
        this.remoteUser = remoteUser;
    }

//    public Map<Integer, Game> getGameMap() {
//        return gameMap;
//    }

    public boolean isServerConnected() {
        return serverConnected;
    }

    public void setServerConnected(boolean serverConnected) {
        this.serverConnected = serverConnected;
    }
}
