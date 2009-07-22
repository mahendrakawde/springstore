/* @@copyright $Id: OrderTrackingHTMLAction.java,v 1.3 2005/03/08 00:20:14 smitha Exp $ */

package com.sun.j2ee.blueprints.consumerwebsite.actions;

import javax.servlet.http.HttpServletRequest;

import com.sun.j2ee.blueprints.order.service.JNDINames;
import com.sun.j2ee.blueprints.order.service.OrderTrackingService;
import com.sun.j2ee.blueprints.util.dao.DAOFactory;
import com.sun.j2ee.blueprints.waf.controller.Event;
import com.sun.j2ee.blueprints.waf.controller.web.html.HTMLActionException;
import com.sun.j2ee.blueprints.waf.controller.web.html.HTMLActionSupport;


/**
 * Handles responsibilities related to getting HTTP request 
 * info and making the calls to process the action 
 */
public class OrderTrackingHTMLAction extends HTMLActionSupport{
   
    /**
     * Handles the http request and provides an
     * appropriate response.
     *
     * Post-condition: Set the bean with info to populate response.
     */
    public Event perform(HttpServletRequest request) throws HTMLActionException {
        
        String orderId = null;
        OrderDetails result = null;      
        orderId = request.getParameter("orderId");
        // put the orderId in the request to display in the screen
        request.setAttribute("orderTrackingId", orderId);
        try {
            result = this.getOrderDetails(orderId, request);
            if (result.getPO() == null) {
                throw new OrderNotFoundException("Order Not Found: " + orderId);
            }
            // places result bean data in the response.
            request.setAttribute("orderDetails", result);
        } catch(OrderNotFoundException ex) {
          System.out.println("OrderTrackingHTMLAction caught the OrderNotFoundException Service Exception");
           throw new com.sun.j2ee.blueprints.consumerwebsite.exceptions.OrderNotFoundException("Action error calling ordertracking endpoint " + ex);
        } catch(Exception ex) {
            System.out.println("OrderTrackingHTMLAction caught an Exception");
           throw new com.sun.j2ee.blueprints.consumerwebsite.exceptions.OrderNotFoundException("Action error calling ordertracking endpoint " + ex);
        }
        return null;
    }

    /**
     * Accesses OrderTracking Web Service endpoint using Jax-rpc
     */
    private OrderDetails getOrderDetails(String orderId,
     HttpServletRequest request) throws Exception {
    	OrderTrackingService service = (OrderTrackingService) DAOFactory.getDAO(JNDINames.PURCHASE_ORDER_SERVICE_CLASS);
    	return service.getOrderDetails(orderId);
   }
}
