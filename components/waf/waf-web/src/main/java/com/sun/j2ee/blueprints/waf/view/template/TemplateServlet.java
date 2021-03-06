/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: TemplateServlet.java,v 1.4 2004/08/26 23:32:55 gmurray71 Exp $ */

package com.sun.j2ee.blueprints.waf.view.template;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.j2ee.blueprints.waf.controller.web.util.WebKeys;

public class TemplateServlet extends HttpServlet {

	private final Logger logger = LoggerFactory.getLogger(TemplateServlet.class);
	
    private Map allScreens;
    private ServletConfig config;
    private ServletContext context;
    private String defaultLocale;
    private boolean cachePreviousScreenAttributes = false;
    private boolean cachePreviousScreenParameters = false;
    private static final String PREVIOUS_SCREEN = "PREVIOUS";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.config = config;
        this.context = config.getServletContext();
        String cachePreviousScreenAttributesString = config.getInitParameter("cache_previous_screen_attributes");
        if (cachePreviousScreenAttributesString != null) {
            if (cachePreviousScreenAttributesString.toLowerCase().equals("true")) {
                logger.info("TemplateServlet: Enabled caching of previous screen attributes.");
                cachePreviousScreenAttributes = true;
            }
        }
        // enable the caching of previous screen parameters
        String cachePreviousScreenParametersString = config.getInitParameter("cache_previous_screen_parameters");
        if (cachePreviousScreenParametersString != null) {
            if (cachePreviousScreenParametersString.toLowerCase().equals("true")) {
                logger.info("TemplateServlet: Enabled caching of previous screen parameters.");
                cachePreviousScreenParameters = true;
            }
        }
        allScreens = new HashMap();
        defaultLocale = config.getInitParameter("default_locale");
        if (defaultLocale == null) {
            defaultLocale = (Locale.getDefault()).toString();
        }
        String locales = config.getInitParameter("locales");
        if (locales != null) {
            StringTokenizer strTok = new StringTokenizer(locales,",");
            while (strTok.hasMoreTokens()) {
                initScreens(config.getServletContext(), strTok.nextToken());
            }
        }
    }

    private void initScreens(ServletContext context, String language) {
        URL screenDefinitionURL = null;
        try {
            screenDefinitionURL = context.getResource("/WEB-INF/screendefinitions_" + language + ".xml");
        } catch (java.net.MalformedURLException ex) {
            logger.error("TemplateServlet: malformed URL exception: ", ex);
        }
        if (screenDefinitionURL != null) {
            Screens screenDefinitions = ScreenDefinitionDAO.loadScreenDefinitions(screenDefinitionURL);
            if (screenDefinitions != null) {
                allScreens.put(language, screenDefinitions);
            } else {
                logger.error("Template Servlet Error Loading Screen Definitions: Confirm that file at URL /WEB-INF/screendefinitions_{}.xml contains the screen definitions", language);
            }
        } else {
            logger.error("Template Servlet Error Loading Screen Definitions: URL /WEB-INF/screendefinitions_{}.xml not found", language);
        }
    }

    public void doPost (HttpServletRequest request, 
      HttpServletResponse response)
        throws IOException, ServletException {
  process(request, response);
    }

    public void doGet (HttpServletRequest request, 
           HttpServletResponse  response)
        throws IOException, ServletException {
  process(request, response);
    }

    public void process (HttpServletRequest request, 
        HttpServletResponse  response)
        throws IOException, ServletException {

        String screenName = null;
        String localeString = null;
        String selectedUrl = request.getRequestURI();
        if (request.getSession().getAttribute(WebKeys.CURRENT_URL) != null) {
            request.getSession().removeAttribute(WebKeys.CURRENT_URL);
        }
        
        String languageParam = request.getParameter("locale");
        // The language when specified as a parameter overrides the language set in the session
        if (languageParam != null) {
            localeString = languageParam;
        } else if (request.getSession().getAttribute(WebKeys.LOCALE) != null) {
            localeString = ((Locale)request.getSession().getAttribute(WebKeys.LOCALE)).toString();
        }
        if (allScreens.get(localeString) == null) {
           localeString = defaultLocale;
        }
                
        // get the screen name
        int lastPathSeparator = selectedUrl.lastIndexOf("/") + 1;
        int lastDot = selectedUrl.lastIndexOf(".");
        if (lastPathSeparator != -1 && lastDot != -1 && lastDot > lastPathSeparator) {
            screenName = selectedUrl.substring(lastPathSeparator, lastDot);
        }
         // check if request is for the previous screen
        if (screenName.equals(PREVIOUS_SCREEN)) {
            String longScreenName  =  (String)request.getSession().getAttribute(WebKeys.PREVIOUS_SCREEN);
            int lastDot2 = longScreenName.lastIndexOf(".");
            if ( lastDot2 != -1 && lastDot2 > 0) {
                screenName = longScreenName.substring(0, lastDot2);
             }
            // put the request attributes stored in the session in the request
            if (cachePreviousScreenParameters) {
                Map previousParams = (Map)request.getSession().getAttribute(WebKeys.PREVIOUS_REQUEST_PARAMETERS);
                Map params = (Map)request.getParameterMap();
                Iterator it = previousParams.keySet().iterator();
                while (it.hasNext()) {
                    String key = (String)it.next();
                    Object value = previousParams.get(key);
                    params.put(key,value);
                }
            }
            // put in the previous request attributes
            if (cachePreviousScreenAttributes) {
                Map previousAttributes = (Map)request.getSession().getAttribute(WebKeys.PREVIOUS_REQUEST_ATTRIBUTES);
                Iterator it2 = previousAttributes.keySet().iterator();
                // put the request attributes stored in the session in the request
                while (it2.hasNext()) {
                    String key = (String)it2.next();
                    Object value = previousAttributes.get(key);
                    request.setAttribute(key,value);
                }
            }
        } else {
                  String extension = selectedUrl.substring(lastDot, selectedUrl.length());
                  request.getSession().setAttribute(WebKeys.PREVIOUS_SCREEN, screenName + extension);
                  if (cachePreviousScreenParameters) {
                      // copy all the parameters into a new map
                      HashMap newParams =  new HashMap();
                      Map params = (Map)request.getParameterMap();
                      Iterator it = params.keySet().iterator();
                     // put the request attributes stored in the session in the request
                     while (it.hasNext()) {
                         String key = (String)it.next();
                         Object value = params.get(key);
                         newParams.put(key,value);
                     }
                     request.getSession().setAttribute(WebKeys.PREVIOUS_REQUEST_PARAMETERS, newParams);
                  }
                  if (cachePreviousScreenAttributes) {
                     // put the request attributes into a map
                     HashMap attributes = new HashMap();
                     Enumeration enumeration = request.getAttributeNames();
                     while (enumeration.hasMoreElements()) {
                         String key = (String)enumeration.nextElement();
                         Object value = request.getAttribute(key);
                         attributes.put(key, value);
                     }
                     request.getSession().setAttribute(WebKeys.PREVIOUS_REQUEST_ATTRIBUTES, attributes);
                  }
        }                
        // get the screen information for the coresponding language
        Screen screen = null;
        if (screenName != null){
            Screens localeScreens = (Screens)allScreens.get(localeString);
            if (localeScreens != null) screen = (Screen)localeScreens.getScreen(screenName);
            // default to the default locale screen if it was not defined in the locale specific definitions
            if (screen == null) {
                logger.warn("Screen not Found loading default from {}.",  defaultLocale);
                localeScreens = (Screens)allScreens.get(defaultLocale);
                screen = (Screen)localeScreens.getScreen(screenName);
            }
            if (screen != null) {
                request.setAttribute(WebKeys.CURRENT_SCREEN, screen);
                String templateName = localeScreens.getTemplate(screenName);
                if (templateName != null) {
                    context.getRequestDispatcher(templateName).forward(request, response);
                } else {
                    response.setContentType("text/html;charset=UTF-8");
                    PrintWriter out = response.getWriter();
                    out.println("<font size=+4>Error:</font><br>Template file not found for " + screenName);
                }
            } else {
                response.setContentType("text/html;charset=8859_1");
                PrintWriter out = response.getWriter();
                out.println("<font size=+4>Error:</font><br>Definition for screen " + screenName + " not found");
            }
        }
    }
}
