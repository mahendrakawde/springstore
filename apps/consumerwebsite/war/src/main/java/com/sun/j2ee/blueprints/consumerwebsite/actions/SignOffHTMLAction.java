/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: SignOffHTMLAction.java,v 1.2 2004/05/26 00:06:18 inder Exp $ */

package com.sun.j2ee.blueprints.consumerwebsite.actions;

// j2ee imports
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// signon filter
import com.sun.j2ee.blueprints.signon.web.SignOnFilter;

// waf imports
import com.sun.j2ee.blueprints.waf.controller.Event;
import com.sun.j2ee.blueprints.waf.controller.web.html.HTMLActionSupport;
import com.sun.j2ee.blueprints.waf.controller.web.html.HTMLActionException;

// adventure imports
import com.sun.j2ee.blueprints.consumerwebsite.AdventureKeys;
/**
 * Handles responsibilities related to getting HTTP request 
 * info and making the calls to the signon component 
 * to access the database.
 */
public class SignOffHTMLAction extends HTMLActionSupport {

    /**
     * Handles the http request to create an signon, and provides an
     * appropriate response.
     *
     * Post-condition: Set the bean with info to populate response.
     */
    public Event perform(HttpServletRequest request)
  throws HTMLActionException {
        // destroy the session will understand.
        try {
            request.getSession().invalidate();
        } catch (Exception e) {
              // swallow the invalid state exception
        }
        // there is no need for an event because all processing was done here
        return null;
    }
}
