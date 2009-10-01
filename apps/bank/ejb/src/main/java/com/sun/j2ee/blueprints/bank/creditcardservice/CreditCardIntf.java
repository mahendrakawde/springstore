/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: CreditCardIntf.java,v 1.3 2004/05/26 00:06:10 inder Exp $ */

package com.sun.j2ee.blueprints.bank.creditcardservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface for the credit card web service endpoint 
 * that receives credit card info
 */
public interface CreditCardIntf extends Remote {
    public boolean validateCreditCard(String ccXMLString)
                                       throws RemoteException;
}
