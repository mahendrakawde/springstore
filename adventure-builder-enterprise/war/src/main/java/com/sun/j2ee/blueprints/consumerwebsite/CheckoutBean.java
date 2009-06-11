/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: CheckoutBean.java,v 1.2 2004/05/26 00:06:16 inder Exp $ */

package com.sun.j2ee.blueprints.consumerwebsite;

import java.io.Serializable;



/**
 * A JavaBeans component representing checkout information.
 */
public class CheckoutBean implements Serializable {


    private String orderId = null;



    public CheckoutBean(String orderId) {
      this.orderId = orderId;
  
    }

   //getter methods

   public String getOrderId() {
        return orderId;
    }
 

    public String toString() {
      return "CustomerBean[orderId=" + orderId + "]";
    }

}
