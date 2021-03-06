/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: ScreenFlowManager.java,v 1.2 2004/05/26 00:07:31 inder Exp $ */

package com.sun.j2ee.blueprints.waf.controller.web;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.j2ee.blueprints.waf.controller.web.util.WebKeys;

/**
 * This file looks at the Request URL and maps the request
 * to the page for the web-templating mechanism.
 */
public class ScreenFlowManager implements java.io.Serializable {

	private final Logger logger = LoggerFactory.getLogger(ScreenFlowManager.class);
	
    private Map urlMappings;
    private List exceptionMappings;
    private String defaultScreen = "";

    public ScreenFlowManager() {
    	super();
    }

    public void init(ServletContext context) {
        String requestMappingsURL = null;
        try {
            requestMappingsURL = context.getResource("/WEB-INF/mappings.xml").toString();
        } catch (java.net.MalformedURLException ex) {
            logger.error("ScreenFlowManager: initializing ScreenFlowManager malformed URL exception: ", ex);
        }
        urlMappings = (HashMap)context.getAttribute(WebKeys.URL_MAPPINGS);
        ScreenFlowData screenFlowData = URLMappingsXmlDAO.loadScreenFlowData(requestMappingsURL);
        defaultScreen = screenFlowData.getDefaultScreen();
        exceptionMappings = screenFlowData.getExceptionMappings();
    }

    /**
     * The UrlMapping object contains information that will match
     * a url to a mapping object that contains information about
     * the current screen, the WebAction that is needed to
     * process a request, and the WebAction that is needed
     * to insure that the propper screen is displayed.
    */

    private URLMapping getURLMapping(String urlPattern) {
        if ((urlMappings != null) && urlMappings.containsKey(urlPattern)) {
            return (URLMapping)urlMappings.get(urlPattern);
        } else {
            return null;
        }
    }       

    /**
     * Using the information we have in the request along with
     * The url map for the current url we will insure that the
     * propper current screen is selected based on the settings
     * in both the screendefinitions.xml file and requestmappings.xml
     * files.
    */
    public void forwardToNextScreen(HttpServletRequest request, HttpServletResponse response) 
              throws java.io.IOException, FlowHandlerException, javax.servlet.ServletException {
        // set the presious screen
        String fullURL = request.getRequestURI();
        // get the screen name
        String selectedURL = defaultScreen;            
        int lastPathSeparator = fullURL.lastIndexOf("/") + 1;
        if (lastPathSeparator != -1) {
            selectedURL = fullURL.substring(lastPathSeparator, fullURL.length());
        }
        String currentScreen = "";
        URLMapping urlMapping = getURLMapping(selectedURL);
        if (urlMapping != null) {
            if (!urlMapping.useFlowHandler()) {
                currentScreen = urlMapping.getScreen();
            } else {
                // load the flow handler
                FlowHandler handler = null;
                String flowHandlerString = urlMapping.getFlowHandler();
                try {
                    handler = (FlowHandler)getClass().getClassLoader().loadClass(flowHandlerString).newInstance();
                    // invoke the processFlow(HttpServletRequest)
                    handler.doStart(request);
                    String flowResult = handler.processFlow(request);
                    handler.doEnd(request);
                    currentScreen = urlMapping.getResultScreen(flowResult);
                    // if there were no screens by the id then assume that the result was
                    //the screen itself
                    if (currentScreen == null) currentScreen = flowResult;
               } catch (Exception ex) {
            	   logger.error("ScreenFlowManager caught loading handler: ", ex);
               }
            }
        }
        if (currentScreen == null) {
            throw new IllegalArgumentException("Screen not found for " + selectedURL);
        }
        
        request.getRequestDispatcher("/" + currentScreen).forward(request, response);
        
    }
    /**
            go through the list and use the Class.isAssignableFrom(Class method)
            to see it is a subclass of one of the exceptions
    */
    public String getExceptionScreen(final Throwable e) {
        Iterator it = exceptionMappings.iterator();
        while (it.hasNext()) {
            ErrorMapping em = (ErrorMapping)it.next();
            String exceptionName = em.getExceptionClassName();
            // check if the exception is a sub class of matches the exception
            if (exceptionName.equals(e.getClass().getName())) {
                return "/" + (em.getScreenName());
            }
        }
        return null;
    }

    public void setDefaultScreen(String defaultScreen) {
        this.defaultScreen = defaultScreen;
    }
    
    /**
     * Returs the current screen
     */

    public String getCurrentScreen(HttpSession session)  {
        return (String)session.getAttribute(WebKeys.CURRENT_SCREEN);
    }
}


