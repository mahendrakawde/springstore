/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: CreateSignOnHTMLAction.java,v 1.2 2004/05/26 00:06:18 inder Exp $ */

package com.sun.j2ee.blueprints.consumerwebsite.actions;

// j2ee imports
import javax.servlet.http.*;

// signon filter
import com.sun.j2ee.blueprints.signon.web.SignOnFilter;
import com.sun.j2ee.blueprints.signon.SignOnFacade;

// WAF imports
import com.sun.j2ee.blueprints.waf.controller.Event;
import com.sun.j2ee.blueprints.waf.controller.web.html.*;

// adventure imports
import com.sun.j2ee.blueprints.consumerwebsite.*;
import com.sun.j2ee.blueprints.consumerwebsite.exceptions.*;
/**
 * Handles responsibilities related to getting HTTP request 
 * info and making the calls to the signon component 
 * to access the database.
 */
public class CreateSignOnHTMLAction extends HTMLActionSupport {

    public static final String SIGNON_CREATE = "createSignOn";
    public static final String PASSWD_CHECK = "checkPassword";

    /**
     * Handles the http request to create an signon, and provides an
     * appropriate response.
     *
     * Post-condition: Set user name and password in session 
     * for the CustomerHTMLAction to pick up and create a sign
     * on and a Customer in a single transaction.
     */
    public Event perform(HttpServletRequest request)
  throws HTMLActionException {
            String userId = request.getParameter("j_username");
            String password = request.getParameter("j_password");
            // put the username and password in the session for future reference
            request.getSession().setAttribute(AdventureKeys.SIGN_ON_TEMP_USERNAME, userId);
            request.getSession().setAttribute(AdventureKeys.SIGN_ON_TEMP_PASSWORD, password);
        return null;
    }
}
