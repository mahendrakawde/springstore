/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: MainServlet.java,v 1.2 2004/05/26 00:07:31 inder Exp $ */

package com.sun.j2ee.blueprints.waf.controller.web;

import java.io.*;
import java.util.*;

// J2EE Imports
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletConfig;

// WAF imports
import com.sun.j2ee.blueprints.waf.util.I18nUtil;
import com.sun.j2ee.blueprints.waf.util.JNDINames;
import com.sun.j2ee.blueprints.waf.controller.GeneralFailureException;
import com.sun.j2ee.blueprints.waf.controller.web.util.WebKeys;
import com.sun.j2ee.blueprints.util.tracer.Debug;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.*;


public class MainServlet extends HttpServlet {
    
    private ServletContext context;
    private HashMap urlMappings;
    private HashMap eventMappings;
    private Locale defaultLocale = null;

    private RequestProcessor requestProcessor;
    private ScreenFlowManager screenFlowManager;
    
    public void init(ServletConfig config) throws ServletException {
        String defaultLocaleString = config.getInitParameter("default_locale");
        defaultLocale = I18nUtil.getLocaleFromString(defaultLocaleString);
        this.context = config.getServletContext();
        // these will have been initialized by the ApplicationComponentManager
        eventMappings = (HashMap)context.getAttribute(WebKeys.EVENT_MAPPINGS);
        urlMappings = (HashMap)context.getAttribute(WebKeys.URL_MAPPINGS);
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
                    ne.printStackTrace();
                } catch (NotSupportedException nse) {
                    // Again this is a recoverable error. 
                    nse.printStackTrace();
                } catch (SystemException se) {
                    // Again this is a recoverable error. 
                    se.printStackTrace();
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
                        re.printStackTrace();
                    } catch (RollbackException re) {
                        re.printStackTrace();
                    } catch (HeuristicMixedException hme) {
                        hme.printStackTrace();
                    } catch (HeuristicRollbackException hre) {
                        hre.printStackTrace();
                    } catch (SystemException se) {
                        se.printStackTrace();
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
            context.getRequestDispatcher(nextScreen).forward(request, response);
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

