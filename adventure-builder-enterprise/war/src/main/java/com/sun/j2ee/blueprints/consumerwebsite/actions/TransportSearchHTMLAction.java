/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: TransportSearchHTMLAction.java,v 1.2 2004/05/26 00:06:18 inder Exp $ */

package com.sun.j2ee.blueprints.consumerwebsite.actions;

import java.io.*;
import java.util.*;

// j2ee imports
import javax.servlet.http.*;

// waf imports
import com.sun.j2ee.blueprints.waf.controller.*;
import com.sun.j2ee.blueprints.waf.controller.web.html.*;

//adventure imports
import com.sun.j2ee.blueprints.consumerwebsite.*;

// Catalog imports
import com.sun.j2ee.blueprints.catalog.*;
import com.sun.j2ee.blueprints.catalog.dao.*;

/**
 * Implementation of HTMLAction that processes a
 * user change in language.
 */

public final class TransportSearchHTMLAction extends HTMLActionSupport {   

    public Event perform(HttpServletRequest request)
  throws HTMLActionException {
            
            HttpSession session = request.getSession();
             // look up the adventure transportation
            AdventureComponentManager acm = 
                        (AdventureComponentManager)session.getAttribute(AdventureKeys.COMPONENT_MANAGER);
            Cart cart = acm.getCart(session);
            String origin = request.getParameter("origin");
            // if we are doing a search for a different flight from the cart page
            if (origin == null) {
                origin = cart.getOrigin();
            } else {
                cart.setOrigin(origin);
            }
             
            ArrayList transpDepartureBeans = null;
            ArrayList transpReturnBeans = null;
            
            String noTransport = request.getParameter("no_transport");
            String showTransport = request.getParameter("show_flights");
            Locale locale = new Locale("en","us");
            String destination = cart.getDestination();
            //access catalog component and retrieve  data from the database
            transpDepartureBeans = searchTransportation(origin, destination, locale);
            transpReturnBeans = searchTransportation(destination, origin, locale);
            
            // places result bean data in the request
            request.setAttribute("departure_result", transpDepartureBeans );
            request.setAttribute("return_result", transpReturnBeans );
            request.setAttribute("search_target","transportation");
        return null; 
    }
    
        /**
     * Access catalog component and retrieve transportation data from the database
     */
    public ArrayList searchTransportation(String origin, String destination ,Locale locale) throws HTMLActionException {
        ArrayList transportation = null;
        ArrayList transportationBean = new ArrayList();
        
        //call catalog component
        try {
            CatalogFacade catalogFacade = new CatalogFacade();            
            transportation = catalogFacade.getTransportations(origin, destination, locale);
        
        // Catch catalog exceptions and re-throw them as
        // mini-app application defined exceptions.
        } catch (Exception e) {
            throw new HTMLActionException("Transportation Search Exception:: Catalog Exception accessing catalog component: " + e);
        }
        for(int i=0;i<transportation.size() ;++i){
            transportationBean.add(new TransportationBean(((com.sun.j2ee.blueprints.catalog.Transportation)transportation.get(i)).getTransportationId(),
                                                           ((com.sun.j2ee.blueprints.catalog.Transportation)transportation.get(i)).getName(),
                                                           ((com.sun.j2ee.blueprints.catalog.Transportation)transportation.get(i)).getDescription(),
                                                            ((com.sun.j2ee.blueprints.catalog.Transportation)transportation.get(i)).getImageURI(),
                                                           ((com.sun.j2ee.blueprints.catalog.Transportation)transportation.get(i)).getPrice(),
                                                           ((com.sun.j2ee.blueprints.catalog.Transportation)transportation.get(i)).getOrigin(),
                                                           ((com.sun.j2ee.blueprints.catalog.Transportation)transportation.get(i)).getDestination(),
                                                           ((com.sun.j2ee.blueprints.catalog.Transportation)transportation.get(i)).getCarrier(),
                                                           ((com.sun.j2ee.blueprints.catalog.Transportation)transportation.get(i)).getDepartureTime(),
                                                           ((com.sun.j2ee.blueprints.catalog.Transportation)transportation.get(i)).getArrivalTime(),
                                                           ((com.sun.j2ee.blueprints.catalog.Transportation)transportation.get(i)).getTravelClass()));
        }
        return transportationBean ;
    }
}
    
