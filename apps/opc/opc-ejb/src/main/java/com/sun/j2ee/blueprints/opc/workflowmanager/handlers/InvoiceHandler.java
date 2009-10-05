/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: InvoiceHandler.java,v 1.12 2004/08/07 00:04:15 smitha Exp $ */

package com.sun.j2ee.blueprints.opc.workflowmanager.handlers;

import javax.jms.Message;
import javax.jms.TextMessage;

import com.sun.j2ee.blueprints.opc.JNDINames;
import com.sun.j2ee.blueprints.opc.invoice.Invoice;
import com.sun.j2ee.blueprints.opc.invoice.XMLException;
import com.sun.j2ee.blueprints.opc.mailer.Mail;
import com.sun.j2ee.blueprints.opc.purchaseorder.ejb.PurchaseOrderLocal;
import com.sun.j2ee.blueprints.opc.purchaseorder.ejb.PurchaseOrderLocalHome;
import com.sun.j2ee.blueprints.opc.utils.JMSUtils;
import com.sun.j2ee.blueprints.processmanager.ejb.OrderStatusNames;
import com.sun.j2ee.blueprints.processmanager.ejb.ProcessManagerLocal;
import com.sun.j2ee.blueprints.processmanager.ejb.ProcessManagerLocalHome;

/**
 * This is the Invoice handler that gets called by the
 * OPC Work Flow Manager. This handler calls the
 * workers that process the invoice
 */
public class InvoiceHandler extends AbstractHandler {
    
    private ProcessManagerLocal processManager;    
    
    public InvoiceHandler() throws HandlerException {
        try{
            ProcessManagerLocalHome pmHome =
                (ProcessManagerLocalHome)sl.getLocalHome(JNDINames.PM_EJB);
            processManager = pmHome.create();
        } catch (Exception exe) {
            logger.error(exe.getMessage(), exe);
            throw new HandlerException("OPC Exception creating InvoiceHandler"); 
        }
    }
    
    /* (non-Javadoc)
	 * @see com.sun.j2ee.blueprints.opc.workflowmanager.handlers.Handler#handle(javax.jms.Message)
	 */
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
            logger.error(exe.getMessage(), exe);
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
            	logger.error(exe.getMessage(), exe);
            }    
        } catch (Exception exe) {
        	logger.error(exe.getMessage(), exe);
            throw new HandlerException("OPC Exception handling invoice");
        }    
    }
   
    public void sendOrderCompletedMail(String orderID) throws HandlerException{
        try{
            //get email id and call CRM
            PurchaseOrderLocalHome poHome = (PurchaseOrderLocalHome)sl.getLocalHome(JNDINames.PO_EJB);
            PurchaseOrderLocal poLocal = poHome.findByPrimaryKey(orderID);
            String msg = "Your order (# " + orderID  + " ) has been completed.";
            msg += " Thank you for shopping with us and we hope to see you again soon";
            Mail mail = new Mail(poLocal.getEmailId(),
                   " Your Adventure Builder order has been completed ", msg); 
            String xmlMail = mail.toXML();
            JMSUtils.sendMessage(JNDINames.CRM_MDB_QUEUE, JNDINames.DOC_TYPE, JNDINames.MAIL_DOCUMENT, xmlMail);
        } catch (Exception exe) {
        	logger.error(exe.getMessage(), exe);
            throw new HandlerException("OPC Exception sending mail");
        }     

    }
}
