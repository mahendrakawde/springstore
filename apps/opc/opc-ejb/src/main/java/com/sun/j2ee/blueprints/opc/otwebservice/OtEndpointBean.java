/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: OtEndpointBean.java,v 1.3 2004/08/06 20:28:13 smitha Exp $ */

package com.sun.j2ee.blueprints.opc.otwebservice;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.ejb.SessionContext;

import org.springframework.ejb.support.AbstractStatelessSessionBean;

import com.sun.j2ee.blueprints.opc.JNDINames;
import com.sun.j2ee.blueprints.opc.purchaseorder.ejb.PurchaseOrderLocal;
import com.sun.j2ee.blueprints.opc.purchaseorder.ejb.PurchaseOrderLocalHome;
import com.sun.j2ee.blueprints.processmanager.ejb.ProcessManagerLocal;
import com.sun.j2ee.blueprints.processmanager.ejb.ProcessManagerLocalHome;
import com.sun.j2ee.blueprints.servicelocator.ServiceLocatorException;
import com.sun.j2ee.blueprints.servicelocator.ejb.NullBeanFactoryLocator;
import com.sun.j2ee.blueprints.servicelocator.ejb.ServiceLocator;

/**
 * This class is used to get Order Tracking info by adventure builder web site
 * application after a user has submitted an order, and wants to track it.
 */
public class OtEndpointBean extends AbstractStatelessSessionBean {

	private ProcessManagerLocal processManager = null;
	private PurchaseOrderLocalHome poHome = null;

	public void setSessionContext(SessionContext sessionContext) {
		super.setSessionContext(sessionContext);
		setBeanFactoryLocator(new NullBeanFactoryLocator());
	}

	protected void onEjbCreate() throws CreateException {
		try {
			ServiceLocator sl = new ServiceLocator();
			ProcessManagerLocalHome pmHome = (ProcessManagerLocalHome) sl
					.getLocalHome(JNDINames.PM_EJB);
			processManager = pmHome.create();
			poHome = (PurchaseOrderLocalHome) sl.getLocalHome(JNDINames.PO_EJB);
		} catch (ServiceLocatorException se) {
			throw new CreateException(se.getMessage());
		}
	}

	/**
	 * Accept an order id, and return the details of the current status for the
	 * order.
	 * 
	 * @return OrderDetails if orderId exists, else return null to indicate
	 *         orderId not found
	 */
	public OrderDetails getOrderDetails(String orderId)
			throws OrderNotFoundException, RemoteException {

		OrderDetails details = new OrderDetails();
		try {
			String status = processManager.getOrderStatus(orderId);
			details.setStatus(status);
			PurchaseOrderLocal polocal = poHome.findByPrimaryKey(orderId);
			details.setPO(polocal.getPO());
		} catch (FinderException fe) {
			throw new OrderNotFoundException("Unable to locate order with id "
					+ orderId
					+ "; Please ensure that you entered the correcr order Id");
		}
		return details;
	}

}
