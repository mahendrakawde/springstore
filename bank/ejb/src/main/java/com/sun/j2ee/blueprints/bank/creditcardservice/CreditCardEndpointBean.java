/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: CreditCardEndpointBean.java,v 1.3 2004/05/26 00:06:10 inder Exp $ */

package com.sun.j2ee.blueprints.bank.creditcardservice;

import javax.ejb.*;
import java.rmi.*;

/**
 * Web service end point for the Credit Card 
 * service that validates credit cards submitted
 * by the OPC
 */
public class CreditCardEndpointBean implements SessionBean {

    private SessionContext sc;
 
    public CreditCardEndpointBean(){}
    
    public void ejbCreate() throws CreateException {}


    /**
     * Validate a credit card and return the status
     */
    public boolean validateCreditCard(String xmlCreditCard)
                                        throws RemoteException {
      //This is stubbed for now. Just returns true.
  return true;
    }
        
    public void setSessionContext(SessionContext sc) {
        this.sc = sc;
    }
    
    public void ejbRemove() throws RemoteException {}
    

    //empty for Stateless EJBs
    public void ejbActivate() {}

    //empty for Stateless EJBs
    public void ejbPassivate() {}
}
