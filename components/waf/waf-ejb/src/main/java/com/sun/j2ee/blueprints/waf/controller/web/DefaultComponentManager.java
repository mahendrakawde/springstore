/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: DefaultComponentManager.java,v 1.2 2004/05/26 00:07:30 inder Exp $ */

package com.sun.j2ee.blueprints.waf.controller.web;

import java.beans.Beans;

import javax.ejb.CreateException;
import javax.ejb.RemoveException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.j2ee.blueprints.servicelocator.ServiceLocatorException;
import com.sun.j2ee.blueprints.servicelocator.web.ServiceLocator;
import com.sun.j2ee.blueprints.waf.controller.GeneralFailureException;
import com.sun.j2ee.blueprints.waf.controller.ejb.EJBControllerLocal;
import com.sun.j2ee.blueprints.waf.controller.ejb.EJBControllerLocalHome;
import com.sun.j2ee.blueprints.waf.controller.web.util.WebKeys;
import com.sun.j2ee.blueprints.waf.util.JNDINames;

/**
 * This implmentation class of the ComponentManager provides access to services
 * in the web tier and ejb tier.
 * 
 */
public class DefaultComponentManager implements ComponentManager,
		java.io.Serializable {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected ServiceLocator sl = null;

	public DefaultComponentManager() {
		sl = ServiceLocator.getInstance();
	}

	public EJBControllerLocal getEJBController(HttpSession session) {
		EJBControllerLocal ccEjb = (EJBControllerLocal) session
				.getAttribute(WebKeys.EJB_CONTROLLER);
		if (ccEjb == null) {
			try {
				EJBControllerLocalHome home = (EJBControllerLocalHome) sl
						.getLocalHome(JNDINames.EJB_CONTROLLER_EJBHOME);
				ccEjb = home.create();
			} catch (CreateException ce) {
				logger.warn(ce.getMessage(), ce);
				throw new GeneralFailureException(ce.getMessage());
			}
		}
		return ccEjb;
	}

	/**
	 * 
	 * Create the WebController which in turn should create the
	 * EJBClientController.
	 * 
	 */
	public void sessionCreated(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		sl = ServiceLocator.getInstance();
		session.setAttribute(WebKeys.COMPONENT_MANAGER, this);
	}

	public WebController getWebController(HttpSession session) {
		WebController wcc = (WebController) session
				.getAttribute(WebKeys.WEB_CONTROLLER);
		if (wcc == null) {
			try {
				String wccClassName = sl
						.getString(JNDINames.DEFAULT_WEB_CONTROLLER);
				wcc = (WebController) Beans.instantiate(this.getClass()
						.getClassLoader(), wccClassName);
				wcc.init(session);
			} catch (ServiceLocatorException slx) {
				logger.warn(slx.getMessage(), slx);
				throw new RuntimeException(
						"Cannot create bean of class WebController: " + slx);
			} catch (Exception exc) {
				logger.warn(exc.getMessage(), exc);
				throw new RuntimeException(
						"Cannot create bean of class WebController: " + exc);
			}
			session.setAttribute(WebKeys.WEB_CONTROLLER, wcc);
		}
		return wcc;
	}

	/**
	 * Destroy the EJBClientController
	 */
	public void sessionDestroyed(HttpSessionEvent se) {
		EJBControllerLocal controllerEJB = getEJBController(se.getSession());
		try {
			controllerEJB.remove();
		} catch (RemoveException re) {
			// ignore, after all its only a remove() call!
			logger.info(re.getMessage(), re);
		}
	}
}
