package com.sun.j2ee.blueprints.opc.otwebservice;

import java.util.Iterator;

import javax.xml.namespace.QName;
import javax.xml.rpc.handler.MessageContext;
import javax.xml.rpc.handler.soap.SOAPMessageContext;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OtMessageHandler extends javax.xml.rpc.handler.GenericHandler {
    
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
    public OtMessageHandler() {
         logger.trace("Initializaing OtMessageHandler");
    }
    
    public boolean handleRequest(MessageContext context) {
        logger.trace("Initializaing OtMessageHandler");
        try {
            SOAPMessageContext smc = (SOAPMessageContext)context;
            SOAPMessage msg = smc.getMessage();
            SOAPPart sp = msg.getSOAPPart();
            SOAPEnvelope se = sp.getEnvelope();
            SOAPHeader sh = se.getHeader();
            SOAPBody body = se.getBody();
            Iterator it = body.getChildElements();
            while ((it != null) && it.hasNext()) {
                logger.info("OtMessageHandler: {}", it.next());
            }
            // Process one or more header blocks
            // Next step based on the processing model for this
            // handler
        } catch(Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return true;
    }
    
    public boolean handleResponse(MessageContext context) {
        logger.trace("OtMessageHandler: handleResponse");
        return true;
    }
    // Other methods: handleResponse, handleFault init, destroy
    
    public QName[] getHeaders() {
        return null;    
    }
    
}
