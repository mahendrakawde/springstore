/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: InvoiceHandler.java,v 1.12 2004/08/07 00:04:15 smitha Exp $ */

package com.sun.j2ee.blueprints.opc.workflowmanager.handlers;

import javax.jms.*;

import com.sun.j2ee.blueprints.opc.invoice.*;
import com.sun.j2ee.blueprints.opc.*;
import com.sun.j2ee.blueprints.processmanager.ejb.*;
import com.sun.j2ee.blueprints.servicelocator.*;
import com.sun.j2ee.blueprints.servicelocator.ejb.*;
import com.sun.j2ee.blueprints.opc.purchaseorder.ejb.*;
import com.sun.j2ee.blueprints.opc.utils.*;
import com.sun.j2ee.blueprints.opc.JNDINames;
import com.sun.j2ee.blueprints.opc.mailer.*;

/**
 * This is the Invoice handler that gets called by the
 * OPC Work Flow Manager. This handler calls the
 * workers that process the invoice
 */
public class InvoiceHandler {
    
    private ProcessManagerLocal processManager;    
    private ServiceLocator sl;
    
    public InvoiceHandler() throws HandlerException {
        try{
            sl = new ServiceLocator();
            ProcessManagerLocalHome pmHome =
                (ProcessManagerLocalHome)sl.getLocalHome(JNDINames.PM_EJB);
            processManager = pmHome.create();
        } catch (Exception exe) {
            System.err.println(exe);
            throw new HandlerException("OPC Exception creating InvoiceHandler"); 
        }
    }
    
    public void handle(Message message) throws HandlerException {
        Invoice invoice = null;
        String inv = null;
        String opcPoID = null;
        
        //extract the Invoice from the message
        try {
            if(message instanceof TextMessage){
                TextMessage txtMsg = (TextMessage) message;
                inv = txtMsg.getText();
            }    
            if(inv != null){ 
                invoice = Invoice.fromXML(inv);
                String supplierID = invoice.getSupplierId();
                opcPoID = invoice.getOpcPoId();
                String invStat = invoice.getStatus();
                if(supplierID.equals(JNDINames.ACTIVITY_INVOICE))
                    processManager.updateActivityOrderStatus(opcPoID, invStat);
                if(supplierID.equals(JNDINames.LODGING_INVOICE))
                    processManager.updateLodgingOrderStatus(opcPoID, invStat);
                if(supplierID.equals(JNDINames.AIRLINE_INVOICE))
                    processManager.updateAirlineOrderStatus(opcPoID, invStat);                                  
           }
        } catch (XMLException exe) {
            System.err.println(exe);
            //call process manager and set error status
            try{ 
                /* 
                 * The status is set to invoice xml error, indicating that an error
                 * occurred while deserializing the invoice. Advanced error handling 
                 * will be done for a later release, with the status having additional
                 * information about the supplier(lodging, activity, or airline)
                 */
                processManager.updateStatus(opcPoID,OrderStatusNames.INVOICE_XML_ERROR);
                processManager.updateOrderErrorStatus(opcPoID, true);
            } catch(Exception xe){
                System.err.println(xe);
            }    
        } catch (Exception exe) {
            System.err.println(exe);
            throw new HandlerException("OPC Exception handling invoice");
        }    
    }
   
    public void sendOrderCompletedMail(String orderID) throws HandlerException{
        boolean sendMail = sl.getBoolean(JNDINames.SEND_MAIL);
        try{
            //get email id and call CRM
            if(sendMail){
                PurchaseOrderLocalHome poHome = 
                  (PurchaseOrderLocalHome)sl.getLocalHome(JNDINames.PO_EJB);
                PurchaseOrderLocal poLocal = poHome.findByPrimaryKey(orderID);
                String msg = "Your order (# " + orderID  + " ) has been completed.";
                msg += " Thank you for shopping with us and we hope to see you again soon";
                Mail mail = new Mail(poLocal.getEmailId(),
                       " Your Adventure Builder order has been completed ", msg); 
                String xmlMail = mail.toXML();
                JMSUtils.sendMessage(JNDINames.CRM_MDB_QUEUE, 
                                JNDINames.DOC_TYPE, JNDINames.MAIL_DOCUMENT, xmlMail);
            }
        } catch (Exception exe) {
            System.err.println(exe);
            throw new HandlerException("OPC Exception sending mail");
        }     

    }
}
