/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: OrderStatusNames.java,v 1.2 2004/05/26 00:07:06 inder Exp $ */

package com.sun.j2ee.blueprints.processmanager.ejb;

/**
 * This class is the central location to store the names of the 
 * status that an order can be in.
 * The states an order goes through are:
 * pending->approved->supplierSubmitted(submitted to supplier)->completed(whole order shipped)  or 
 * pending->denied
 */
public class OrderStatusNames {

    private OrderStatusNames() { } //Prevents instantiation

    /** for orders that have been placed but not yet approved */
    public static final String PENDING = "PENDING";

    /** for orders that have been approved */
    public  static final String APPROVED = "APPROVED";

    /** for orders that have been denied */
    public  static final String DENIED = "DENIED";

    /** for orders that have been submitted to the supplier*/
    public  static final String SUBMITTED = "SUBMITTED TO SUPPLIER";

    /** for orders that have been completed*/
    public  static final String COMPLETED = "COMPLETED";

    /** for orders that encountered an error while processing the credit card*/
    public  static final String PAYMENT_PROCESSING_ERROR = "PAYMENT PROCESSING ERROR";

    /** for orders that encountered an error while sending to the order filler queue*/
    public  static final String ORDER_FILLER_ERROR = "ERROR SENDING PO TO ORDER FILLER QUEUE";

    /** for orders that encountered an error while deserializing the invoice XML*/
    public  static final String INVOICE_XML_ERROR = "ERROR DESERALIZING THE INVOICE XML";
}

