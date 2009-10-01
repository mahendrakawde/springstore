/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: CreditCardVerifier.java,v 1.5 2005/03/08 00:18:35 smitha Exp $ */

package com.sun.j2ee.blueprints.opc.financial;

import javax.naming.*;
import javax.xml.rpc.*;
import java.rmi.*;

import com.sun.j2ee.blueprints.servicelocator.*;
import com.sun.j2ee.blueprints.servicelocator.ejb.*;
import com.sun.j2ee.blueprints.opc.JNDINames;

/**
 * This component verifies a credit card
 * with the bank's credit card service
 */
public class CreditCardVerifier { 
    
    private CreditCardIntf port;
  
    public CreditCardVerifier () {
    try{
        Context ic = new InitialContext();
  CreditCardService ccSvc = (CreditCardService) ic.lookup(JNDINames.CREDIT_CARD_SERVICE_NAME);
      port = (CreditCardIntf) ccSvc.getPort(CreditCardIntf.class);
    } catch (Exception exe){
        System.err.println(exe);    
    }
    }
    
    public boolean verifyCreditCard(String cc) throws RemoteException { 
        boolean stat = false; 
  stat = port.validateCreditCard(cc);
        return stat;
    }
}
