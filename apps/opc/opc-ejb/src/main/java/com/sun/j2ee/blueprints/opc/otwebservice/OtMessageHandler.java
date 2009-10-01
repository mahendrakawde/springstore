package com.sun.j2ee.blueprints.opc.otwebservice;

import java.util.*;
import javax.xml.rpc.handler.*;
import javax.xml.rpc.handler.soap.*;
import javax.xml.soap.*;
import  javax.xml.namespace.QName;

public class OtMessageHandler extends javax.xml.rpc.handler.GenericHandler {
    
    public OtMessageHandler() {
         System.out.println("Initializaing OtMessageHandler");
    }
    
    public boolean handleRequest(MessageContext context) {
        System.out.println("Initializaing OtMessageHandler");
        try {
            SOAPMessageContext smc = (SOAPMessageContext)context;
            SOAPMessage msg = smc.getMessage();
            SOAPPart sp = msg.getSOAPPart();
            SOAPEnvelope se = sp.getEnvelope();
            SOAPHeader sh = se.getHeader();
            SOAPBody body = se.getBody();
            Iterator it = body.getChildElements();
            while ((it != null) && it.hasNext()) {
                System.out.println("OtMessageHandler:" + it.next());
            }
            // Process one or more header blocks
            // Next step based on the processing model for this
            // handler
        } catch(Exception ex) {
            // throw exception
        }
        return true;
    }
    
    public boolean handleResponse(MessageContext context) {
        System.out.println("OtMessageHandler: handleResponse");
        return true;
    }
    // Other methods: handleResponse, handleFault init, destroy
    
    public QName[] getHeaders() {
        return null;    
    }
    
}
