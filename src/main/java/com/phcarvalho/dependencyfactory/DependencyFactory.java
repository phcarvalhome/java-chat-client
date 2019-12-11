package com.phcarvalho.dependencyfactory;

import com.phcarvalho.controller.*;
import com.phcarvalho.model.*;
import com.phcarvalho.model.communication.commandtemplate.local.socket.CommandInvoker;
import com.phcarvalho.model.communication.service.QueueHandlerClient;
import com.phcarvalho.model.communication.strategy.ICommandTemplateFactory;
import com.phcarvalho.model.communication.strategy.IConnectionStrategy;
import com.phcarvalho.model.communication.strategy.factory.ICommunicationStrategy;
import com.phcarvalho.model.communication.strategy.socket.SocketConnectionStrategy;
import com.phcarvalho.view.*;
import com.phcarvalho.view.util.DialogUtil;

import java.util.HashMap;
import java.util.Map;

public class DependencyFactory {

    private static DependencyFactory singleton;

    public static DependencyFactory getSingleton(){

        if(singleton == null)
            singleton = new DependencyFactory();

        return singleton;
    }

    private ICommunicationStrategy communicationStrategyFactory;
    private Map<Class<?>, Object> dependencyMap;

    private DependencyFactory() {
        dependencyMap = new HashMap<>();
    }

    public void build(){
        dependencyMap.put(DialogUtil.class, new DialogUtil());
        dependencyMap.put(CommandInvoker.class, new CommandInvoker());
        dependencyMap.put(SocketConnectionStrategy.class, new SocketConnectionStrategy());
        dependencyMap.put(IConnectionStrategy.class, communicationStrategyFactory.buildConnectionStrategy());
        dependencyMap.put(ICommandTemplateFactory.class, communicationStrategyFactory.buildCommandTemplateFactory());

        dependencyMap.put(QueueHandlerClient.class, new QueueHandlerClient());

        buildConnectedUserMVC();
        buildConnectionMVC();
        buildConsoleMVC();
        buildChatMVC();
        buildMainMVC();

        get(CommandInvoker.class).buildCommandObserverMap();
//        get(IConnectionStrategy.class).setCommandInvoker(get(CommandInvoker.class));
    }

    private void buildConnectedUserMVC() {
        ConnectedUserController connectedUserController = new ConnectedUserController();
        ConnectedUserView connectedUserView = new ConnectedUserView(connectedUserController);
        ConnectedUserModel connectedUserModel = new ConnectedUserModel(connectedUserController);

        connectedUserController.setView(connectedUserView);
        connectedUserController.setModel(connectedUserModel);
        connectedUserController.initializeList();
        dependencyMap.put(ConnectedUserView.class, connectedUserView);
        dependencyMap.put(ConnectedUserModel.class, connectedUserModel);
//        get(IConnectionHandlerStrategy.class).setConnectedUserModel(connectedUserModel);
    }

    private void buildConnectionMVC() {
        ConnectionController connectionController = new ConnectionController();
        ConnectionView connectionView = new ConnectionView(connectionController);
        ConnectionModel connectionModel = new ConnectionModel(connectionController);

        connectionController.setView(connectionView);
        connectionController.setModel(connectionModel);
        dependencyMap.put(ConnectionView.class, connectionView);
        dependencyMap.put(ConnectionModel.class, connectionModel);
//        get(IConnectionHandlerStrategy.class).setConnectionModel(connectionModel);
    }

    private void buildConsoleMVC() {
        ConsoleController consoleController = new ConsoleController();
        ConsoleView consoleView = new ConsoleView(consoleController);
        ConsoleModel consoleModel = new ConsoleModel(consoleController);

        consoleController.setView(consoleView);
        consoleController.setModel(consoleModel);
        dependencyMap.put(ConsoleView.class, consoleView);
        dependencyMap.put(ConsoleModel.class, consoleModel);
    }

    private void buildChatMVC() {
        ChatController chatController = new ChatController();
        ChatView chatView = new ChatView(chatController);
        ChatModel chatModel = new ChatModel(chatController);

        chatController.setView(chatView);
        chatController.setModel(chatModel);
        dependencyMap.put(ChatView.class, chatView);
        dependencyMap.put(ChatModel.class, chatModel);
    }

    private void buildMainMVC() {
        MainController mainController = new MainController();
        MainView mainView = new MainView(mainController);
        MainModel mainModel = new MainModel(mainController);

        mainController.setView(mainView);
        mainController.setModel(mainModel);
        dependencyMap.put(MainView.class, mainView);
        dependencyMap.put(MainModel.class, mainModel);
        get(DialogUtil.class).setMainView(mainView);
        get(ConnectedUserView.class).setMainView(mainView);
        get(ConnectionView.class).setMainView(mainView);
        get(ConsoleView.class).setMainView(mainView);
        get(ChatView.class).setMainView(mainView);
//        get(IConnectionStrategy.class).setMainModel(mainModel);
        //TODO talvez fazer o set de cada model que foi colocado lá...
    }

    public <T> T get(Class<T> classType){
        Object dependency = dependencyMap.get(classType);

        if(dependency == null)
            throw new RuntimeException("The dependency is null! Type: " + classType);

        return (T) dependency;
    }

    public void setCommunicationStrategyFactory(ICommunicationStrategy communicationStrategyFactory) {
        this.communicationStrategyFactory = communicationStrategyFactory;
    }
}
