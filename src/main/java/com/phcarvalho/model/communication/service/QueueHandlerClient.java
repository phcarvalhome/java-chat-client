package com.phcarvalho.model.communication.service;

import com.phcarvalho.model.communication.webservice.IQueueHandler;
import com.phcarvalho.model.communication.webservice.QueueHandlerService;
import com.phcarvalho.model.communication.webservice.User;

public class QueueHandlerClient {

    private IQueueHandler queueHandler;

    private void locateService(){
        QueueHandlerService queueHandlerService = new QueueHandlerService();

        queueHandler = queueHandlerService.getQueueHandlerPort();
    }

//    @Action(input = "http://webservice.communication.model.phcarvalho.com/IQueueHandler/createRequest", output = "http://webservice.communication.model.phcarvalho.com/IQueueHandler/createResponse")
//    @ResponseWrapper(localName = "createResponse", targetNamespace = "http://webservice.communication.model.phcarvalho.com/", className = "com.phcarvalho.model.communication.webservice.CreateResponse")
//    @RequestWrapper(localName = "create", targetNamespace = "http://webservice.communication.model.phcarvalho.com/", className = "com.phcarvalho.model.communication.webservice.Create")
//    @WebMethod
    public void create(User user) {
        locateService();
        queueHandler.create(user);
    }
}
