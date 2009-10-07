/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: CreditCardVerifier.java,v 1.5 2005/03/08 00:18:35 smitha Exp $ */

package com.sun.j2ee.blueprints.opc.financial;

import java.rmi.RemoteException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.j2ee.blueprints.opc.JNDINames;
import com.sun.j2ee.blueprints.servicelocator.ejb.ServiceLocator;

/**
 * This component verifies a credit card with the bank's credit card service
 */
public class CreditCardVerifier {

	private static final Logger logger = LoggerFactory
			.getLogger(CreditCardVerifier.class);

	private CreditCardIntf port;

	public CreditCardVerifier() {
		try {
			ServiceLocator sl = new ServiceLocator();
			port = (CreditCardIntf) sl.getPort(
					JNDINames.CREDIT_CARD_SERVICE_NAME, CreditCardIntf.class);
		} catch (Exception exe) {
			logger.error(exe.getMessage(), exe);
		}
	}

	public boolean verifyCreditCard(String cc) throws RemoteException {
		return port.validateCreditCard(cc);
	}
}
