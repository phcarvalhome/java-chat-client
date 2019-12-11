
package com.phcarvalho.model.communication.webservice;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "QueueHandlerService", targetNamespace = "http://webservice.communication.model.phcarvalho.com/", wsdlLocation = "http://localhost:9990/chat-server/queue-handler?wsdl")
public class QueueHandlerService
    extends Service
{

    private final static URL QUEUEHANDLERSERVICE_WSDL_LOCATION;
    private final static WebServiceException QUEUEHANDLERSERVICE_EXCEPTION;
    private final static QName QUEUEHANDLERSERVICE_QNAME = new QName("http://webservice.communication.model.phcarvalho.com/", "QueueHandlerService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:9990/chat-server/queue-handler?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        QUEUEHANDLERSERVICE_WSDL_LOCATION = url;
        QUEUEHANDLERSERVICE_EXCEPTION = e;
    }

    public QueueHandlerService() {
        super(__getWsdlLocation(), QUEUEHANDLERSERVICE_QNAME);
    }

    public QueueHandlerService(WebServiceFeature... features) {
        super(__getWsdlLocation(), QUEUEHANDLERSERVICE_QNAME, features);
    }

    public QueueHandlerService(URL wsdlLocation) {
        super(wsdlLocation, QUEUEHANDLERSERVICE_QNAME);
    }

    public QueueHandlerService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, QUEUEHANDLERSERVICE_QNAME, features);
    }

    public QueueHandlerService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public QueueHandlerService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns IQueueHandler
     */
    @WebEndpoint(name = "QueueHandlerPort")
    public IQueueHandler getQueueHandlerPort() {
        return super.getPort(new QName("http://webservice.communication.model.phcarvalho.com/", "QueueHandlerPort"), IQueueHandler.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IQueueHandler
     */
    @WebEndpoint(name = "QueueHandlerPort")
    public IQueueHandler getQueueHandlerPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://webservice.communication.model.phcarvalho.com/", "QueueHandlerPort"), IQueueHandler.class, features);
    }

    private static URL __getWsdlLocation() {
        if (QUEUEHANDLERSERVICE_EXCEPTION!= null) {
            throw QUEUEHANDLERSERVICE_EXCEPTION;
        }
        return QUEUEHANDLERSERVICE_WSDL_LOCATION;
    }

}
