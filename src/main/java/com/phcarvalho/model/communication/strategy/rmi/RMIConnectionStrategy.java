package com.phcarvalho.model.communication.strategy.rmi;

import com.phcarvalho.model.communication.commandtemplate.IChatCommandTemplate;
import com.phcarvalho.model.communication.commandtemplate.IConnectionCommandTemplate;
import com.phcarvalho.model.communication.commandtemplate.local.ChatLocalCommandTemplate;
import com.phcarvalho.model.communication.commandtemplate.local.ConnectionLocalCommandTemplate;
import com.phcarvalho.model.communication.protocol.vo.CommandTemplateEnum;
import com.phcarvalho.model.communication.strategy.IConnectionStrategy;
import com.phcarvalho.model.configuration.Configuration;
import com.phcarvalho.model.configuration.entity.User;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class RMIConnectionStrategy implements IConnectionStrategy {

    @Override
    public void connectToServer(User remoteUser) throws RemoteException {
        String apiName = buildAPIName(remoteUser);

        rebind(apiName);
    }

    private String buildAPIName(User remoteUser) {
        String name = Configuration.getSingleton().getLocalUser().getName().replace(" ", "");
        return "rmi://" + remoteUser.getHost() + ":" + remoteUser.getPort() + "/" +
                name + "/";
    }

    private void rebind(String apiName) throws RemoteException {
        IConnectionCommandTemplate connectionLocalCommandTemplate = new ConnectionLocalCommandTemplate();
        String connectionLocalAPIName = apiName + CommandTemplateEnum.CONNECTION.name();
        IChatCommandTemplate chatLocalCommandTemplate = new ChatLocalCommandTemplate();
        String chatLocalAPIName = apiName + CommandTemplateEnum.CHAT.name();

        try {
            Naming.rebind(connectionLocalAPIName, connectionLocalCommandTemplate);
            Naming.rebind(chatLocalAPIName, chatLocalCommandTemplate);
        } catch (MalformedURLException e) {
            //todo handle it...
            e.printStackTrace();

            throw new RuntimeException(e);
        }
    }
}
