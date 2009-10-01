/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: SignOnNotifier.java,v 1.3 2004/05/26 00:06:16 inder Exp $ */

package com.sun.j2ee.blueprints.consumerwebsite;

import java.beans.Beans;
import java.util.*;
import javax.servlet.http.*;
import javax.servlet.*;

// signon component
import com.sun.j2ee.blueprints.signon.web.SignOnFilter;

// customer component imports
import com.sun.j2ee.blueprints.customer.*;

// adventure imports
import com.sun.j2ee.blueprints.consumerwebsite.actions.*;

/** 
 * This class will bind with the current session and notify the Adventure Builder
 * Back end when a SignOn has occured.
 *
 * This allows for a loose coupling of the SignOn component and the 
 * Adventure Builder Application.  Ensure the neccessary setup is  
 * done when a user signs in.
 */
public class SignOnNotifier  
   implements java.io.Serializable, HttpSessionAttributeListener {


    public SignOnNotifier() { }

    // do nothing
    public void attributeRemoved(HttpSessionBindingEvent se) {}
    
    /**
     *
     * Process an attribute added
     *
     */
    public void attributeAdded(HttpSessionBindingEvent se) {
        processEvent(se);
    }

    /**
     * Process the update
     */
    public void attributeReplaced(HttpSessionBindingEvent se) {
        processEvent(se);
    }
    
    private void processEvent(HttpSessionBindingEvent se) {
        HttpSession session = se.getSession();
        String name = se.getName();
        /* check if the value matches the signon attribute
         * if a macth fire off an event to the ejb tier that the user
         * has signed on and load the account for the user
         */
        if (name.equals(SignOnFilter.SIGNED_ON_USER)) {
            boolean aSignOn  = ((Boolean)se.getValue()).booleanValue();
            if (aSignOn) {
                AdventureComponentManager acm =
                    (AdventureComponentManager)session.getAttribute(AdventureKeys.COMPONENT_MANAGER);
                // Delegate the work to access the customer component.
                CustomerFacade facade = null;
                /**
                 * If the unbinding was the result of a session timeout
                 * the session will be gone and the acm will be null
                 */
                if (acm != null) {
                    facade = acm.getCustomerFacade(session);
                    CustomerHTMLAction action = new CustomerHTMLAction();
                    CustomerBean bean = null;
                    try {
                        bean = action.readAccount(session, facade);
                    } catch (Exception cex) {
                        // log error message
                        cex.printStackTrace();
                    }
                    session.setAttribute(AdventureKeys.CUSTOMER_BEAN, bean);
                }
            }
        }
    }

}


