/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: JNDINames.java,v 1.6 2005/03/08 00:18:35 smitha Exp $ */

package com.sun.j2ee.blueprints.opc;

/**
 * JNDI names of various entities used by the OPC
 */
public class JNDINames {

    // JNDI name of Process Manager  EJB
    public static final String PM_EJB = "java:comp/env/ejb/local/processmanager/ProcessManager";
  
    // JNDI name of PO EJB
    public static final String PO_EJB = "java:comp/env/ejb/local/purchaseorder/PurchaseOrder";

    // JNDI name of Unique Id Generator  EJB
    public static final String UIDGEN_EJB = "java:comp/env/ejb/local/uidgen/UniqueIdGenerator";

    // JNDI name of OPC Queue Connection Factory
    public static final String OPC_QUEUE_CONNECTION_FACTORY = "java:comp/env/jms/opc/QueueConnectionFactory";

    // JNDI name of OPC Work Flow Manager MDB Queue
    public static final String WORKFLOW_MGR_MDB_QUEUE= "java:comp/env/jms/opc/WorkFlowMgrQueue";

    // JNDI name of OPC Order Filler MDB Queue
    public static final String ORDER_FILLER_MDB_QUEUE= "java:comp/env/jms/opc/OrderFillerQueue";

    // JNDI Name for Broker's queue
    public static final String WS_BROKER_MDB_QUEUE = "java:comp/env/jms/opc/WebServiceBrokerQueue";

    // JNDI Name for CRM queue
    public static final String CRM_MDB_QUEUE = "java:comp/env/jms/opc/CRMQueue";

    // JNDI name for DOCTYPE to be set in JMS property
    public static final String DOC_TYPE = "DOCUMENT_TYPE";

    // JNDI Name for PO document
    public static final String PO_DOCUMENT = "PO_DOCUMENT";

    // JNDI Name for mail document
    public static final String MAIL_DOCUMENT = "MAIL_DOCUMENT";

    // JNDI names for the supplier order doc types
    public static final String ACTIVITY_ORDER = "ACTIVITY_ORDER";
    public static final String LODGING_ORDER = "LODGING_ORDER";
    public static final String AIRLINE_ORDER = "AIRLINE_ORDER";

    // JNDI Names for invoice document 
    public static final String INVOICE_DOCUMENT = "INVOICE_DOCUMENT";

    // JNDI names for invoice sender type
    public static final String ACTIVITY_INVOICE = "ACTIVITY_INVOICE";
    public static final String LODGING_INVOICE = "LODGING_INVOICE";
    public static final String AIRLINE_INVOICE = "AIRLINE_INVOICE";

    // JNDI names for Supplier Factory Classes
    public static final String ACTIVITY_SUPPLIER_CLIENT = "java:comp/env/param/ActivitySupplier";
    public static final String LODGING_SUPPLIER_CLIENT = "java:comp/env/param/LodgingSupplier";
    public static final String AIRLINE_SUPPLIER_CLIENT = "java:comp/env/param/AirlineSupplier";

    // JNDI Names for supplier services
    public static final String LODGING_SERVICE_NAME = "java:comp/env/service/LodgingPOService";
    public static final String ACTIVITY_SERVICE_NAME = "java:comp/env/service/ActivityPOService";
    public static final String AIRLINE_SERVICE_NAME = "java:comp/env/service/AirlinePOService";
    
    // JNDI Name for credit card service
    public static final String CREDIT_CARD_SERVICE_NAME = "java:comp/env/service/CreditCardService";

    //JNDI name for the environment entry to enable email notifications
    public static final String SEND_MAIL = "java:comp/env/param/SendMail";

   //JNDI name for the environment entries to configure the timer to update order status
    public static final String TIMER_EXPIRATION = "java:comp/env/param/OrderStatusTimerInitialExpiration";
    public static final String TIMER_INTERVAL = "java:comp/env/param/OrderStatusTimerInterval";

}
