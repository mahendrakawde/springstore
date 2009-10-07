/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: ApplicationComponentManager.java,v 1.2 2004/05/26 00:07:29 inder Exp $ */

package com.sun.j2ee.blueprints.waf.controller.web;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.j2ee.blueprints.waf.controller.web.util.WebKeys;


/**
 *  Provides all the startup objects needed by the WAF which will
 *  be loaded when the application Servlet Context is started
 */
public class ApplicationComponentManager implements ServletContextListener {
    
	private final Logger logger = LoggerFactory.getLogger(ApplicationComponentManager.class);
	
    public ApplicationComponentManager() {}
    
    public void contextDestroyed(ServletContextEvent sce){
        // do nothing for destroying now
    }
    
    public void contextInitialized(ServletContextEvent sce){
        try {
            doInit(sce.getServletContext());
            getRequestProcessor(sce.getServletContext());
            getScreenFlowManager(sce.getServletContext());
        } catch (Throwable ex) {
        	logger.error("WAF ApplicaitonComponentManager Error Initializing:", ex);
            throw new IllegalStateException(ex);
        }
    }
    
    private void doInit(ServletContext context) {
        String requestMappingsURL = null;
        try {
            requestMappingsURL = context.getResource("/WEB-INF/mappings.xml").toString();
        } catch (java.net.MalformedURLException ex) {
           logger.error("ApplicationComponentManager: initializing ScreenFlowManager malformed URL exception: ", ex);
        }
        Map urlMappings = URLMappingsXmlDAO.loadRequestMappings(requestMappingsURL);
        context.setAttribute(WebKeys.URL_MAPPINGS, urlMappings);
        Map eventMappings = URLMappingsXmlDAO.loadEventMappings(requestMappingsURL);
        context.setAttribute(WebKeys.EVENT_MAPPINGS, eventMappings);
    }
       
    public static RequestProcessor getRequestProcessor(final ServletContext context) {
        RequestProcessor rp = (RequestProcessor)context.getAttribute(WebKeys.REQUEST_PROCESSOR);
        if ( rp == null ) {
            rp = new RequestProcessor();
            rp.init(context);
            context.setAttribute(WebKeys.REQUEST_PROCESSOR, rp);
        }
        return rp;
    }
    
    public static ScreenFlowManager getScreenFlowManager(final ServletContext context) {
        ScreenFlowManager screenManager = (ScreenFlowManager)context.getAttribute(WebKeys.SCREEN_FLOW_MANAGER);
        if (screenManager == null ) {
            screenManager = new ScreenFlowManager();
            screenManager.init(context);
            context.setAttribute(WebKeys.SCREEN_FLOW_MANAGER, screenManager);
        }
        return screenManager;
    }
}
