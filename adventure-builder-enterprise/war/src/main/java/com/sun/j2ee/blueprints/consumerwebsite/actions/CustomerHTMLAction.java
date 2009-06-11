/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: CustomerHTMLAction.java,v 1.2 2004/05/26 00:06:18 inder Exp $ */

package com.sun.j2ee.blueprints.consumerwebsite.actions;

import javax.servlet.http.*;

// signon filter
import com.sun.j2ee.blueprints.signon.web.SignOnFilter;

// waf imports
import com.sun.j2ee.blueprints.waf.controller.Event;
import com.sun.j2ee.blueprints.waf.controller.web.html.*;

// customer component imports
import com.sun.j2ee.blueprints.customer.*;

// signon filter
import com.sun.j2ee.blueprints.signon.web.SignOnFilter;
import com.sun.j2ee.blueprints.signon.SignOnFacade;

// adventure imports
import com.sun.j2ee.blueprints.consumerwebsite.*;
import com.sun.j2ee.blueprints.consumerwebsite.exceptions.CustomerException;
import com.sun.j2ee.blueprints.consumerwebsite.exceptions.SignOnException;
/**
 * Handles responsibilities related to getting HTTP request
 * info and making the calls to the customer account component
 * to access the database.
 */
public final class CustomerHTMLAction extends HTMLActionSupport {
    
    public static final String ACCOUNT_READ_ACTION = "readAccount";
    public static final String ACCOUNT_CREATE_ACTION = "createAccount";
    
    /**
     * Handles the http request to create an account, and provides an
     * appropriate response.
     *
     * Post-condition: Set the bean with info to populate response.
     */
    public Event perform(HttpServletRequest request)
        throws HTMLActionException {
        
        CustomerBean resultBean = null;
        
        //determine which type of request to process
        String targetAction =request.getParameter("target_action");
        // get a handle on the Adventure Component Manager
        HttpSession session = request.getSession();
        AdventureComponentManager acm =
        (AdventureComponentManager)session.getAttribute(AdventureKeys.COMPONENT_MANAGER);
        // Delegate the work to access the customer component.
        CustomerFacade facade = acm.getCustomerFacade(session);
        if ((targetAction != null) &&
               targetAction.equals(ACCOUNT_CREATE_ACTION)) {
            resultBean = createAccount(request, facade);
        } else {
            Boolean signedOn = (Boolean)request.getSession().getAttribute(SignOnFilter.SIGNED_ON_USER);
            //FOR READ REQUESTS
            if ((signedOn != null) && signedOn.booleanValue()) {
                // Delegate the work to access the customer component.
                resultBean = readAccount(session,facade);
            } else {
                throw new CustomerException("CustomerHTMLAction: User is not signed on.");
            }
        }
        // places result bean data in the response.
        session.setAttribute(AdventureKeys.CUSTOMER_BEAN, resultBean);
        return null;
    }
    
    /**
     * Validates the given feedback.
     */
    protected void validate(String userId)
        throws CustomerException {
        if ((userId == null) || userId.trim().length() == 0) {
            throw new CustomerException("Unfortunately, there was a problem: The userId must have data. Your request has not been sent.");
        }
    }
    
    /**
     * Access customer component and retrieve account data in the database
     */
    public CustomerBean readAccount(HttpSession session,
                                                                       CustomerFacade facade)
            throws CustomerException {
        
        String userId = (String)session.getAttribute(SignOnFilter.USER_NAME);
        Account acct = null;
        //call customer component
        try {
            acct = facade.getAccount(userId);
            // Catch customer component exceptions and re-throw them as
            // app application defined exceptions
        }catch (Exception e) {
            e.printStackTrace();
            throw new CustomerException("CustomerHTMLAction:: CustomerAppException accessing Customer Component: ", e);
        }
        
        return new CustomerBean(acct);
    }
    
    /**
     * Creates a new customer account
     */
    private CustomerBean createAccount(HttpServletRequest request,
                                                                             CustomerFacade facade)
        throws CustomerException, SignOnException {
        
         // create the sign on here
         Boolean result = new Boolean(false);
         String userId = null;

        // Delegate the work to access the signon component.
        result = new Boolean(createSignOn(request));
        if (result.booleanValue()) {
             userId = (String)request.getSession().getAttribute(SignOnFilter.USER_NAME);
        } else {
            throw new SignOnException("CustomerHTMLAction: failed to create SignOn for " + userId);
        }

        // places result bean data in the session using the key that the
        // signonfilter will understand.
        request.getSession().setAttribute(SignOnFilter.SIGNED_ON_USER, result);
        String familyName =request.getParameter("acct_familyName");
        String givenName =request.getParameter("acct_givenName");
        String telephone =request.getParameter("acct_telephone");
        String email =request.getParameter("acct_email");
        String street1 =request.getParameter("acct_street1");
        String street2 =request.getParameter("acct_street2");
        String city =request.getParameter("acct_city");
        String state =request.getParameter("acct_state");
        String zipCode =request.getParameter("acct_zipCode");
        String country =request.getParameter("acct_country");
        
        com.sun.j2ee.blueprints.customer.Address address =
        new com.sun.j2ee.blueprints.customer.Address(street1, street2, city, state, zipCode, country);
        com.sun.j2ee.blueprints.customer.ContactInformation info =
        new com.sun.j2ee.blueprints.customer.ContactInformation(familyName, givenName,telephone,
        email, address);
        
        com.sun.j2ee.blueprints.customer.Account acct = new com.sun.j2ee.blueprints.customer.Account(userId, info);
        
        //call customer component
        try {
            facade.createAccount(acct);
            // Catch customer component exceptions and re-throw them as
            // app application defined exceptions.
        } catch (Exception e) {
            System.out.println("**** Customer Error");
            e.printStackTrace();
            throw new CustomerException("CustomerBD:: CustomerAppException Error Creating Customer", e);
        }
        
        //return back same data as input
        return new CustomerBean(acct);
    }
    
            /**
     * Creates a new signon in the database
     */
    private boolean createSignOn(HttpServletRequest request)
                              throws SignOnException {
        try {
            HttpSession session = request.getSession();
            String userId = (String)session.getAttribute(AdventureKeys.SIGN_ON_TEMP_USERNAME);
            String password = (String)session.getAttribute(AdventureKeys.SIGN_ON_TEMP_PASSWORD);
            session.removeAttribute(AdventureKeys.SIGN_ON_TEMP_USERNAME);
            session.removeAttribute(AdventureKeys.SIGN_ON_TEMP_PASSWORD);
            // put the userID in the session for future reference
            session.setAttribute(SignOnFilter.USER_NAME, userId);
            AdventureComponentManager acm =
              (AdventureComponentManager)session.getAttribute(AdventureKeys.COMPONENT_MANAGER);
            SignOnFacade facade = acm.getSignOnFacade(session);
            facade.createSignOn(userId, password);
            return true;
        // you may chose to catch each of the individual signon exceptions and pass on a
        // a more detailed message to the user here
        } catch (Exception e) {
            throw new SignOnException("SignOnHTMLAction:: Exception creating new signon: ", e);
        }
    }
}
