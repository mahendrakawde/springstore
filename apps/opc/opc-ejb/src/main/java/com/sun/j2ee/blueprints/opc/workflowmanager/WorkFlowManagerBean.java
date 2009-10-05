/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: WorkFlowManagerBean.java,v 1.7 2004/08/07 00:04:05 smitha Exp $ */

package com.sun.j2ee.blueprints.opc.workflowmanager;

import java.util.Collection;
import java.util.Iterator;

import javax.ejb.EJBException;
import javax.ejb.MessageDrivenContext;
import javax.ejb.TimedObject;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.springframework.ejb.support.AbstractJmsMessageDrivenBean;

import com.sun.j2ee.blueprints.opc.JNDINames;
import com.sun.j2ee.blueprints.opc.workflowmanager.handlers.Handler;
import com.sun.j2ee.blueprints.opc.workflowmanager.handlers.HandlerException;
import com.sun.j2ee.blueprints.opc.workflowmanager.handlers.InvoiceHandler;
import com.sun.j2ee.blueprints.opc.workflowmanager.handlers.POHandler;
import com.sun.j2ee.blueprints.processmanager.ejb.OrderStatusNames;
import com.sun.j2ee.blueprints.processmanager.ejb.ProcessManagerLocal;
import com.sun.j2ee.blueprints.processmanager.ejb.ProcessManagerLocalHome;
import com.sun.j2ee.blueprints.processmanager.manager.ejb.ManagerLocal;
import com.sun.j2ee.blueprints.servicelocator.ServiceLocatorException;
import com.sun.j2ee.blueprints.servicelocator.ejb.ServiceLocator;

/**
 * This is the work flow manager that controls work flow within the Order
 * Processing Center
 */
public class WorkFlowManagerBean extends AbstractJmsMessageDrivenBean implements
		TimedObject {

	private MessageDrivenContext context;
	private Handler poHandler;
	private Handler invHandler;
	private ProcessManagerLocal pm;
	
	protected void onEjbCreate() {
		try {
			poHandler = new POHandler();
			invHandler = new InvoiceHandler();
			ServiceLocator sl = new ServiceLocator();
			ProcessManagerLocalHome pmlh = (ProcessManagerLocalHome) sl
					.getLocalHome(JNDINames.PM_EJB);
			pm = pmlh.create();
		} catch (HandlerException he) {
			throw new EJBException(he.getMessage());
		} catch (ServiceLocatorException se) {
			throw new EJBException(se.getMessage());
		} catch (Exception exe) {
			throw new EJBException(exe.getMessage());
		}
	}

	public void onMessage(Message message) {
		try {
			/*
			 * For now, just call the handlers This will change as the state
			 * machine is implemented
			 */
			String docType = message.getStringProperty(JNDINames.DOC_TYPE);
			if (docType.equals(JNDINames.PO_DOCUMENT)) {
				poHandler.handle(message);
			} else if (docType.equals(JNDINames.INVOICE_DOCUMENT)) {
				invHandler.handle(message);
				createStatusUpdateTimer();
			}
		} catch (HandlerException he) {
			throw new EJBException(he);
		} catch (JMSException exe) {
			throw new EJBException(exe);
		}
	}

	private void createStatusUpdateTimer() {
		try {

			TimerService timerService = context.getTimerService();

			// check if a timer already exists
			if ((timerService.getTimers()).isEmpty()) {
				Context ic = new InitialContext();

				// create an interval timer to update the order status
				int expiration = (((Integer) ic
						.lookup(JNDINames.TIMER_EXPIRATION)).intValue()) * 60000;
				int interval = (((Integer) ic.lookup(JNDINames.TIMER_INTERVAL))
						.intValue()) * 60000;
				Timer timer = timerService.createTimer(expiration, interval,
						"OPC order update timer");
			}
		} catch (Exception exe) {
			throw new EJBException(exe);
		}
	}

	public void ejbTimeout(Timer timer) {
		try {

			// check the status of all the orders that are submitted to
			// suppliers
			Collection ordersList = pm
					.getOrdersByStatus(OrderStatusNames.SUBMITTED);
			Iterator iter = ordersList.iterator();
			while (iter.hasNext()) {
				ManagerLocal mgr = (ManagerLocal) iter.next();
				String orderID = mgr.getOrderId();

				// change status to completed if all the three supplier orders
				// are completed
				pm.updateStatusToCompleted(orderID);

				// send the order completed mail
				if (pm.getOrderStatus(orderID).equals(OrderStatusNames.COMPLETED)) {
					((InvoiceHandler) invHandler).sendOrderCompletedMail(orderID);
				}
			}
		} catch (Exception exe) {
			throw new EJBException(exe);
		}
	}

}
