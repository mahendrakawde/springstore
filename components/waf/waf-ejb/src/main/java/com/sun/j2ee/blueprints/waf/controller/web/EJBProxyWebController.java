/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: EJBProxyWebController.java,v 1.4 2004/10/07 19:09:07 yutayoshida Exp $ */

package com.sun.j2ee.blueprints.waf.controller.web;

import javax.ejb.RemoveException;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.j2ee.blueprints.waf.controller.Event;
import com.sun.j2ee.blueprints.waf.controller.EventException;
import com.sun.j2ee.blueprints.waf.controller.EventResponse;
import com.sun.j2ee.blueprints.waf.controller.ejb.EJBControllerLocal;
import com.sun.j2ee.blueprints.waf.controller.web.util.WebKeys;

/**
 * This class is essentially just a proxy object that calls methods on the EJB
 * tier using the
 * com.sun.j2ee.blueprints.waf.controller.ejb.EJBClientControllerEJB object. All
 * the methods that access the EJB are synchronized so that concurrent requests
 * do not happen to the stateful session bean.
 * 
 */
public class EJBProxyWebController implements WebController {

	private final Logger logger = LoggerFactory
			.getLogger(EJBProxyWebController.class);

	public EJBProxyWebController() {
	}

	/**
	 * constructor for an HTTP client.
	 * 
	 * @param session
	 *            the HttpSession object of the application
	 */
	public void init(HttpSession session) {
	}

	/**
	 * feeds the specified event to the state machine of the business logic.
	 * 
	 * @param ev
	 *            is the current event
	 * @return com.sun.j2ee.blueprints.waf.controller.EventResponse object which
	 *         can be extend to carry any payload.
	 * @exception com.sun.j2ee.blueprints.waf.controller.EventException
	 *                <description>
	 * @exception com.sun.j2ee.blueprints.wafcontroller.GeneralFailureException
	 */
	public synchronized EventResponse handleEvent(Event ev, HttpSession session)
			throws EventException {
		DefaultComponentManager cm = (DefaultComponentManager) session
				.getAttribute(WebKeys.COMPONENT_MANAGER);
		EJBControllerLocal controllerEJB = cm.getEJBController(session);
		return controllerEJB.processEvent(ev);
	}

	/**
	 * frees up all the resources associated with this controller and destroys
	 * itself.
	 */
	public synchronized void destroy(HttpSession session) {
		// call ejb remove on EJBClientController
		DefaultComponentManager cm = (DefaultComponentManager) session
				.getAttribute(WebKeys.COMPONENT_MANAGER);
		EJBControllerLocal controllerEJB = cm.getEJBController(session);
		try {
			controllerEJB.remove();
		} catch (RemoveException re) {
			// ignore, after all its only a remove() call!
			logger.warn(re.getMessage(), re);
		}
	}
}
