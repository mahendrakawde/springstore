/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: MainServlet.java,v 1.2 2004/05/26 00:07:31 inder Exp $ */

package com.sun.j2ee.blueprints.waf.controller.web;

import org.slf4j.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.sun.j2ee.blueprints.waf.controller.web.util.WebKeys;
import com.sun.j2ee.blueprints.waf.util.I18nUtil;


public class MainServlet extends HttpServlet {
    
	private final Logger logger = LoggerFactory.getLogger(MainServlet.class);
	
    private Map urlMappings;
    private Locale defaultLocale = null;

    private RequestProcessor requestProcessor;
    private ScreenFlowManager screenFlowManager;
    
    public void init(ServletConfig config) throws ServletException {
        String defaultLocaleString = config.getInitParameter("default_locale");
        defaultLocale = I18nUtil.getLocaleFromString(defaultLocaleString);
        ServletContext context = config.getServletContext();
        urlMappings = (Map)context.getAttribute(WebKeys.URL_MAPPINGS);
        requestProcessor = (RequestProcessor)context.getAttribute(WebKeys.REQUEST_PROCESSOR);
        screenFlowManager = (ScreenFlowManager)context.getAttribute(WebKeys.SCREEN_FLOW_MANAGER);
    }
   
     public  void doGet(HttpServletRequest request, HttpServletResponse  response)
        throws IOException, ServletException {
        doProcess(request, response);
    }

    public  void doPost(HttpServletRequest request, HttpServletResponse  response)
        throws IOException, ServletException {
        doProcess(request, response);
        
    }
    
    private void doProcess(HttpServletRequest request, HttpServletResponse response)
                   throws IOException, ServletException {
        // set the locale of the user to default if not set
        if (request.getSession().getAttribute(WebKeys.LOCALE) == null) {
            request.getSession().setAttribute(WebKeys.LOCALE, defaultLocale);
        }
        try {
            String fullURL = request.getRequestURI();
            // get the screen name
            String selectedURL = null;            
            int lastPathSeparator = fullURL.lastIndexOf("/") + 1;
            if (lastPathSeparator != -1) {
                selectedURL = fullURL.substring(lastPathSeparator, fullURL.length());
            }
            URLMapping urlMapping = getURLMapping(selectedURL);
            
           // now check if the URI uses a transaction
            if (urlMapping.isTransactional()) {
                // start the transaction
                boolean tx_started = false;
                UserTransaction ut = null;
                try {
                    // Lookup the UserTransaction object
                    InitialContext ic = new InitialContext();
                    ut = (UserTransaction) ic.lookup("java:comp/UserTransaction");
                    ut.begin();   // start the transaction. 
                    tx_started = true;
                } catch (NamingException ne) {
                    // it should not have happened, but it is a recoverable error. 
                    // Just dont start the transaction.
                	logger.warn(ne.getMessage(), ne);
                } catch (NotSupportedException nse) {
                    // Again this is a recoverable error. 
                	logger.warn(nse.getMessage(), nse);
                } catch (SystemException se) {
                    // Again this is a recoverable error. 
                	logger.warn(se.getMessage(), se);
                }
                try {
                    requestProcessor.processRequest(urlMapping, request);
                    screenFlowManager.forwardToNextScreen(request,response);
                } finally {
                    // commit the transaction if it was started earlier successfully. 
                    try {
                        if (tx_started && ut != null) {
                            ut.commit();
                        }
                    } catch (IllegalStateException re) {
                    	logger.warn(re.getMessage(), re);
                    } catch (RollbackException re) {
                    	logger.warn(re.getMessage(), re);
                    } catch (HeuristicMixedException hme) {
                    	logger.warn(hme.getMessage(), hme);
                    } catch (HeuristicRollbackException hre) {
                    	logger.warn(hre.getMessage(), hre);
                    } catch (SystemException se) {
                    	logger.warn(se.getMessage(), se);
                    }
                }
            } else {
                    requestProcessor.processRequest(urlMapping, request);
                    screenFlowManager.forwardToNextScreen(request,response);
            }
        } catch (Throwable ex) {
            String className = ex.getClass().getName();
            String nextScreen = screenFlowManager.getExceptionScreen(ex);
            // put the exception in the request
            request.setAttribute(WebKeys.WAF_EXCEPTION, ex);
            if (nextScreen == null) {
                // send to general error screen
                ex.printStackTrace();
                throw new ServletException("MainServlet: unknown exception: " + className);
            }
            request.getRequestDispatcher(nextScreen).forward(request, response);
      }
    }
    
    /**
     * The UrlMapping object contains information that will match
     * a url to a mapping object that contains information about
     * the current screen, the Action that is needed to
     * process a request, and the Action that is needed
     * to insure that the propper screen is displayed.
    */
    private URLMapping getURLMapping(String urlPattern) {
        if ((urlMappings != null) && urlMappings.containsKey(urlPattern)) {
            return (URLMapping)urlMappings.get(urlPattern);
        } else {
            return null;
        }
    }
}

