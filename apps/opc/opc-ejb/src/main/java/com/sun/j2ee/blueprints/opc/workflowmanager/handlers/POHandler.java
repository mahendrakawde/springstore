/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: POHandler.java,v 1.8 2004/07/20 01:08:21 yutayoshida Exp $ */

package com.sun.j2ee.blueprints.opc.workflowmanager.handlers;

import java.rmi.*;

import javax.jms.*;
import javax.ejb.*;

import com.sun.j2ee.blueprints.opc.purchaseorder.*;
import com.sun.j2ee.blueprints.opc.orderreceiver.*;
import com.sun.j2ee.blueprints.opc.JNDINames;
import com.sun.j2ee.blueprints.processmanager.ejb.*;
import com.sun.j2ee.blueprints.servicelocator.*;
import com.sun.j2ee.blueprints.servicelocator.ejb.*;
import com.sun.j2ee.blueprints.opc.financial.*;
import com.sun.j2ee.blueprints.opc.utils.*;
import com.sun.j2ee.blueprints.opc.mailer.*;

/**
 * This is the PO handler that gets called by the
 * OPC Work Flow Manager. The handler first calls
 * the PO receiver that persists the PO. Then
 * it makes a call to the Credit Card Verifier
 * and then the CRM component. Finally it calls the 
 * order filler component. 
 */
public class POHandler {
    
    private ProcessManagerLocal processManager;
    private ServiceLocator sl;
    private POReceiver poReceiver;
    private CreditCardVerifier cardVerifier;
    
    public POHandler()throws HandlerException {
        try{
            sl = new ServiceLocator();
            cardVerifier = new CreditCardVerifier();
            poReceiver = new POReceiver();
            ProcessManagerLocalHome pmHome = (ProcessManagerLocalHome)sl.getLocalHome(JNDINames.PM_EJB);
            processManager = pmHome.create();
        } catch (Exception exe) {
            System.err.println(exe);
            throw new HandlerException("OPC Exception creating POHandler");  
        }
    }
    
    public void handle(Message message) throws HandlerException {

        PurchaseOrder po = null;
        String poID = null;
        String emailID = null;
        boolean sendMail = sl.getBoolean(JNDINames.SEND_MAIL);
        
        //extract the PO from the message
        try {
            if(message instanceof ObjectMessage){
                ObjectMessage objMsg = (ObjectMessage) message;
                po = (PurchaseOrder)objMsg.getObject();
            }    
            if(po != null){ 
                poID = po.getPoId();
                emailID = po.getEmailId();             
                //persist the PO
                poReceiver.persistPO(po);
                String lodgOrderStatus = po.getLodging() == null 
                                         ? OrderStatusNames.COMPLETED 
                                         : OrderStatusNames.PENDING;
                String actyOrderStatus = po.getActivities().length == 0
                                         ? OrderStatusNames.COMPLETED 
                                         : OrderStatusNames.PENDING;          
                String airlineOrderStatus = (po.getDepartureFlightInfo() == null)
                                            && (po.getReturnFlightInfo() == null) 
                                            ? OrderStatusNames.COMPLETED 
                                            : OrderStatusNames.PENDING;
                processManager.createManager(poID, OrderStatusNames.PENDING,
                                            actyOrderStatus, airlineOrderStatus, 
                                            lodgOrderStatus);
           
                //next call Credit Card Verifier                
                String creditCardXML = po.getCreditCard().toXML();
                boolean ccStatus = cardVerifier.verifyCreditCard(creditCardXML);       

                //change status and submit the order to order filler if credit
                //card is approved
                if (ccStatus){
                    processManager.updateStatus(poID, OrderStatusNames.APPROVED);                   
                    //call CRM 
                    if(sendMail){
                        String subject = "Your Adventure Builder order has been  approved";
                        String msg = "Your order (# " + poID + " ) has been approved.";
                        msg += " Thank you for shopping with us and we hope to see you again soon";
                        sendMail(emailID, subject, msg);                    
                    }   
                    //next call order filler
                    if (JMSUtils.sendMessage(JNDINames.ORDER_FILLER_MDB_QUEUE, 
                                         JNDINames.DOC_TYPE, JNDINames.PO_DOCUMENT,
                                         (Object)po) == false){
                        processManager.updateStatus(poID, OrderStatusNames.ORDER_FILLER_ERROR);
                        processManager.updateOrderErrorStatus(poID, true);
                    } else {
                        processManager.updateStatus(poID, OrderStatusNames.SUBMITTED);
                    }   
                } else { 
                    processManager.updateStatus(poID, OrderStatusNames.DENIED);
                    //call CRM
                    if(sendMail){ 
                        String subject = "Your Adventure Builder order has been  denied";
                        String msg = "Your order (# " + poID + " ) has been denied. ";
                        msg += " Thank you for shopping with us and we hope to see you again soon";
                        sendMail(emailID, subject, msg);                      
                    }
                }
            }    
        } catch (CreateException ce) {
            //call CRM to notify the customer
            if(sendMail){
                String subject = "Problems processing your Adventure Builder order";
                String msg = "We had problems processing your Adventure Builder order.";
                msg += " Please resubmit the order";
                sendMail(emailID, subject, msg);             
            }
        } catch (RemoteException re) {
            //call process manager and set error status
            try{  
                processManager.updateStatus(poID,OrderStatusNames.PAYMENT_PROCESSING_ERROR);
                processManager.updateOrderErrorStatus(poID, true);
            } catch(FinderException fe){
                System.err.println(fe);
            }    
        } catch (Exception exe) {
            System.err.println(exe);
            throw new HandlerException("OPC Exception handling PO");
        }  
    }
    
    private void sendMail(String emailID, String subject, String msg){
        Mail mail = new Mail(emailID, subject, msg);                
        String xmlMail = mail.toXML();
        JMSUtils.sendMessage(JNDINames.CRM_MDB_QUEUE, 
                            JNDINames.DOC_TYPE, JNDINames.MAIL_DOCUMENT,
                            xmlMail);
    }    
}
