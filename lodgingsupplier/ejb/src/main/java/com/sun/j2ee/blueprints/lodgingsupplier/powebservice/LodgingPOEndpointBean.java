/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: LodgingPOEndpointBean.java,v 1.8 2004/07/30 23:59:20 inder Exp $ */

package com.sun.j2ee.blueprints.lodgingsupplier.powebservice;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.jms.ConnectionFactory;

import org.springframework.ejb.support.AbstractStatelessSessionBean;

import com.sun.j2ee.blueprints.lodgingsupplier.JNDINames;
import com.sun.j2ee.blueprints.servicelocator.ejb.ServiceLocator;

/**
 * This class is the entry point for purchase orders submitted by OPC to the
 * lodging suppliers;
 */
public class LodgingPOEndpointBean extends AbstractStatelessSessionBean {

	private LodgingPOEndpoint lodgingPOEndpoint;
	
	public LodgingPOEndpointBean() {
	}
	
	protected void onEjbCreate() throws CreateException {
		ServiceLocator sl = new ServiceLocator();
		//move this to an ApplicationContext so we can do dependency injection/lookup
		ConnectionFactory jmsConnectionFactory = sl.getJMSConnectionFactory(JNDINames.LODGE_QUEUECONNECTIONFACTORY);
		lodgingPOEndpoint = new LodgingPOEndpointImpl(jmsConnectionFactory);
	}

	/**
	 * Receive an order, preprocess the request, submit request to workflow and
	 * return the order id so that the caller can have a correlation id for the
	 * order
	 */
	public String submitLodgingReservationDetails(String xmlPO)
			throws InvalidOrderException, OrderSubmissionException,
			RemoteException {
		
		return lodgingPOEndpoint.submitLodgingReservationDetails(xmlPO);

	}


}
