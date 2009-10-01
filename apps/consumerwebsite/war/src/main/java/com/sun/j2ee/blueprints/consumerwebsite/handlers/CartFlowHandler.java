/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: CartFlowHandler.java,v 1.2 2004/05/26 00:06:20 inder Exp $ */

package com.sun.j2ee.blueprints.consumerwebsite.handlers;

import java.io.*;
import java.util.*;


// J2EE imports 
import javax.servlet.http.HttpServletRequest;

// WAF imports
import com.sun.j2ee.blueprints.waf.controller.web.*;

//adventure imports
import com.sun.j2ee.blueprints.consumerwebsite.*;

/**
 * This class forwards the application to the correct page following a
 * package action.
 *
*/
public class CartFlowHandler implements FlowHandler {

    public void doStart(HttpServletRequest request){
    }
    
    public String processFlow(HttpServletRequest request) 
        throws FlowHandlerException {

        String actionType= (String)request.getParameter("target_action");
        if (actionType == null) return "CART";
        // based on the target action that was processed forward
        // to the necessary screen
        if (actionType.equals("select_package")) {
            return "PACKAGE_OPTIONS";
        } else if (actionType.equals("set_package_options")) {
            return "SELECT_TRANSPORT";
        } else if (actionType.equals("update_package_options")) {
            return "CART";
        } else if (actionType.equals("update_activities")) {
            return "CART-ACTIVITIES";
         } else if (actionType.equals("purchase_activities")) {
            return "CART-ACTIVITIES";
        } else if (actionType.equals("update_lodging_room_count")) {
            return "CART-LODGING";
        } else if (actionType.equals("purchase_lodging")) {
            return "CART-LODGING";
        } else if (actionType.equals("cancel_return_flight")) {
            return "CART_TRANSPORT";
        } else if (actionType.equals("cancel_departure_flight")) {
            return "CART_TRANSPORT";
         } else if (actionType.equals("purchase_transportation")) {
            return "CART_TRANSPORT";
        } else if (actionType.equals("no_transportation")) {
            return "CART";
        } else if (actionType.equals("purchase_activity")) {
            return "CART-ACTIVITIES";        
        } else if (actionType.equals("cancel")) {
            return "CANCEL";        
        } else {
            return "CART"; 
        }
    }
    
    
    public void doEnd(HttpServletRequest request) {
    }

}

