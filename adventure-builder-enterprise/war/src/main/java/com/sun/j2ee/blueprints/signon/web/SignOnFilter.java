/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: SignOnFilter.java,v 1.2 2004/05/26 00:06:27 inder Exp $ */

package com.sun.j2ee.blueprints.signon.web;

import java.io.*;
import java.util.*;
import java.net.URL;

// J2EE imports
import javax.servlet.*;
import javax.servlet.http.*;
import javax.naming.*;

import com.sun.j2ee.blueprints.signon.SignOnFacade;


public class SignOnFilter implements Filter {

    // these static strings define where to put/get things
    public static final String FORM_SIGNON_URL = "j_signon_check";
    public static final String FORM_USER_NAME = "j_username";
    public static final String FORM_PASSWORD = "j_password";
    public static final String REMEMBER_USERNAME = "j_remember_username";
    public static final String USER_NAME = "j_signon_username";
    public static final String SIGNED_ON_USER  = "j_signon";
    public static final String ORIGINAL_URL = "j_signon_original_url";
    public static final String CREATE_USER_URL = "j_create_user";
    public static final String COOKIE_NAME = "bp_signon";


    private HashMap protectedResources;
    private FilterConfig config = null;
    private String signOnErrorPage = null;
    private String signOnPage = null;
    private String userCreationError = null;

    public void init(FilterConfig config) throws ServletException {
        this.config = config;
        URL protectedResourcesURL = null;
        try {
            protectedResourcesURL = config.getServletContext().getResource("/WEB-INF/signon-config.xml");
            ConfigFileSignOnDAO dao = new ConfigFileSignOnDAO(protectedResourcesURL);
            signOnErrorPage = dao.getSignOnErrorPage();
            signOnPage = dao.getSignOnPage();
            protectedResources = dao.getProtectedResources();
        } catch (java.net.MalformedURLException ex) {            
            System.err.println("SignonFilter: malformed URL exception: " + ex);
            throw new RuntimeException(ex);
        }
    }

    public void destroy() {
        config = null;
    }

     public  void doFilter(ServletRequest request, ServletResponse  response, FilterChain chain)
        throws IOException, ServletException {
        HttpServletRequest hreq = (HttpServletRequest)request;
        String currentURI = hreq.getRequestURL().toString();
        String currentURL = hreq.getRequestURI();
        // get everything after the context root
        int firstSlash = currentURL.indexOf("/",1); // jump past the starting slash
        String targetURL = null;
        if (firstSlash != -1) targetURL = currentURL.substring(firstSlash + 1, currentURL.length());

        if ((targetURL != null) && targetURL.equals(FORM_SIGNON_URL)) {
            validateSignOn(request, response, chain);
            // jump out of this method
            return;
        }

        // check if the user is signed on
        boolean signedOn = false;
        if (hreq.getSession().getAttribute(SIGNED_ON_USER) != null) {
            signedOn =((Boolean)hreq.getSession().getAttribute(SIGNED_ON_USER)).booleanValue();
        } else {
            hreq.getSession().setAttribute(SIGNED_ON_USER, new Boolean(false));
        }
        // jump to the resource if signed on
        if (signedOn) {
                chain.doFilter(request,response);
                return;
        }
        // find out if the patterns match the target URL
        Iterator it = protectedResources.keySet().iterator();
        while (it.hasNext()) {
            String protectedName = (String)it.next();
            ProtectedResource resource  = (ProtectedResource)protectedResources.get(protectedName);
            String urlPattern = resource.getURLPattern();

            // now check agains the targetURL
            if (urlPattern.equals(targetURL)) {
                // put the orginal url in the session so others can access
                hreq.getSession().setAttribute(ORIGINAL_URL,  targetURL);
                config.getServletContext().getRequestDispatcher("/" + signOnPage).forward(request, response);
                // Jump out of the filter and go to the next page
                return;
            }
        }
        // No matches if we made it to here
        chain.doFilter(request,response);
    }

     public  void validateSignOn(ServletRequest request, ServletResponse  response, FilterChain chain)
        throws IOException, ServletException {
        // convert to a http servlet request for now
        HttpServletRequest hreq = (HttpServletRequest)request;
        HttpServletResponse hres = (HttpServletResponse)response;
        // get the user name
        String userName = hreq.getParameter(FORM_USER_NAME);
        // get the password
        String password = hreq.getParameter(FORM_PASSWORD);
        // check if the user wants userName set in cookie
        String rememberUserName = hreq.getParameter(REMEMBER_USERNAME);
        if (rememberUserName != null) {
          // set a cookie with the username in it
          Cookie userNameCookie = new Cookie(COOKIE_NAME, userName);
          // set cookie to last for one month
          userNameCookie.setMaxAge(2678400);
          hres.addCookie(userNameCookie);
        } else {
            // see if the cookie exists and remove accordingly
            Cookie[] cookies = hreq.getCookies();
            if (cookies != null) {
                for (int loop=0; loop < cookies.length; loop++) {
                    if (cookies[loop].getName().equals(COOKIE_NAME)) {
                        cookies[loop].setMaxAge(0);
                        hres.addCookie(cookies[loop]);
                    }
                }
            }
        }

        //validate against the registered users
        try {
            SignOnFacade signOn = new SignOnFacade();
            boolean authenticated = signOn.authenticate(userName, password);
            if (authenticated) {
                // place a true boolean in the session
                if (hreq.getSession().getAttribute(USER_NAME) != null) {
                    hreq.getSession().removeAttribute(USER_NAME);
                }
                hreq.getSession().setAttribute(USER_NAME, userName);
                // remove the sign on user key before putting it back in
                if (hreq.getSession().getAttribute(SIGNED_ON_USER) != null) {
                    hreq.getSession().removeAttribute(SIGNED_ON_USER);
                }
                hreq.getSession().setAttribute(SIGNED_ON_USER, new Boolean(true));
                // redirect to the original destination
                String targetURL = (String)hreq.getSession().getAttribute(ORIGINAL_URL);
                hres.sendRedirect(targetURL);
                return;
            } else {
                hres.sendRedirect(signOnErrorPage);
                return;
            }
        } catch(Exception e) {
            System.out.println("SignOnFilter signOnError:::exception to:" + e);
        }
     }

 }

