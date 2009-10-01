/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: AdventureComponentManager.java,v 1.2 2004/05/26 00:06:15 inder Exp $ */

package com.sun.j2ee.blueprints.consumerwebsite;

// j2ee imports
import javax.servlet.ServletContext;
import javax.servlet.http.*;

import java.beans.Beans;

import com.sun.j2ee.blueprints.util.tracer.Debug;

// WAF imports
import com.sun.j2ee.blueprints.waf.controller.web.WebComponentManager;

// customer component imports
import com.sun.j2ee.blueprints.customer.CustomerFacade;

// signon component
import com.sun.j2ee.blueprints.signon.SignOnFacade;

// Catalog imports
import com.sun.j2ee.blueprints.catalog.CatalogFacade;

/** 
 * This class manages components used in the web tier
 *  
 */
public class AdventureComponentManager extends WebComponentManager
              implements HttpSessionListener {
            
    public AdventureComponentManager () {
    }
    
    /**
     *
     * Initialize the component manager
     *
     */
    public void init(HttpSession session) {
        session.setAttribute(AdventureKeys.COMPONENT_MANAGER, this);
        // intialize the adventure package
        getCart(session);
    }
    /**
     *
     * Initialize what needs initialization
     *
     */
    public void sessionCreated(HttpSessionEvent se) {
        se.getSession().setAttribute(AdventureKeys.COMPONENT_MANAGER, this);
        se.getSession().setAttribute(AdventureKeys.CART, 
                                                   getCart(se.getSession()));
    }
    
    public void sessionDestroyed(HttpSessionEvent se) {
        // initialize the adventure package
  try {
      getCart(se.getSession());
  } catch (Exception e) {
      // swallow the session listener exception for now
  }
    }
    
    public Cart getCart(HttpSession session) {
        Cart cart = (Cart)session.getAttribute(AdventureKeys.CART);
        if (cart == null) {
           try {
                cart = (Cart)Beans.instantiate(this.getClass().getClassLoader(),
                                                            "com.sun.j2ee.blueprints.consumerwebsite.Cart");
                session.setAttribute(AdventureKeys.CART, cart);
            } catch (Exception ex) {
                ex.printStackTrace();
                Debug.print("Error instanciating Cart object: " + ex);
            }
        }
        return cart;
    }
    
    /**
     *  Keep only one copy of the Catalog Facade around
     *  in the context to provide optimized creation of DAOs for
     *  accessing signon data.
     */
    public CatalogFacade getCatalogFacade (HttpSession session) {
        ServletContext context = session.getServletContext();
        CatalogFacade catalogFacade = null;
        if (context.getAttribute(AdventureKeys.CATALOG_FACADE) != null) {
            catalogFacade = (CatalogFacade)context.getAttribute(AdventureKeys.CATALOG_FACADE);
        } else {
            catalogFacade = new CatalogFacade();
            context.setAttribute(AdventureKeys.CATALOG_FACADE, catalogFacade);
        }
        return catalogFacade;
    }
    
    /**
     *  Keep only one copy of the SignOn Facade around
     *  in the context to provide optimized creation of DAOs for
     *  accessing signon data.
     */
    public SignOnFacade getSignOnFacade (HttpSession session) {
        ServletContext context = session.getServletContext();
        SignOnFacade signOnFacade = null;
        if (context.getAttribute(AdventureKeys.SIGN_ON_FACADE) != null) {
            signOnFacade = (SignOnFacade)context.getAttribute(AdventureKeys.SIGN_ON_FACADE);
        } else {
            signOnFacade = new SignOnFacade();
            context.setAttribute(AdventureKeys.SIGN_ON_FACADE, signOnFacade);
        }
        return signOnFacade;
    }
    
    /**
     *  Keep only one copy of the Customer Facade around
     *  in the context to provide optimized creation of DAOs for
     *  accessing customer data.
     */
    public CustomerFacade getCustomerFacade (HttpSession session) {
        ServletContext context = session.getServletContext();
        CustomerFacade CustomerFacade = null;
        if (context.getAttribute(AdventureKeys.CUSTOMER_FACADE) != null) {
            CustomerFacade = (CustomerFacade)context.getAttribute(AdventureKeys.CUSTOMER_FACADE);
        } else {
            CustomerFacade = new CustomerFacade();
            context.setAttribute(AdventureKeys.CUSTOMER_FACADE, CustomerFacade);
        }
        return CustomerFacade;
    }
}


