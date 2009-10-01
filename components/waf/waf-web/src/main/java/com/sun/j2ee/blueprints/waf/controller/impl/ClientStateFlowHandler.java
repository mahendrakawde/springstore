/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: ClientStateFlowHandler.java,v 1.3 2004/07/20 01:08:23 yutayoshida Exp $ */

package com.sun.j2ee.blueprints.waf.controller.impl;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Map;
import java.util.Iterator;
import java.util.HashMap;

// Apache Commons- Tag-Lib Imports
import org.apache.commons.codec.base64.Base64;

// J2EE imports 
import javax.servlet.http.HttpServletRequest;

// tracer import
import com.sun.j2ee.blueprints.util.tracer.Debug;

// WAF imports
import com.sun.j2ee.blueprints.waf.controller.web.FlowHandler;
import com.sun.j2ee.blueprints.waf.controller.web.FlowHandlerException;

/**
 * This class de-serializes Base64 encoded parameters encoded into a web page using the
 * ClientCacheLinkTag.
 *
*/
public class ClientStateFlowHandler implements FlowHandler {

    public void doStart(HttpServletRequest request){
    }
    
    public String processFlow(HttpServletRequest request) 
        throws FlowHandlerException {
            
   
        String forwardScreen = request.getParameter("referring_screen");
        // de-serialize the request attributes.
        Map params = (Map)request.getParameterMap();
        HashMap newParams = new HashMap();
        String cacheId= request.getParameter("cacheId");
        if (!params.isEmpty()) {
            Iterator it = params.keySet().iterator();
            // put the request attributes stored in the session in the request
            while (it.hasNext()) {
                String key = (String)it.next();
                if (key.startsWith(cacheId + "_attribute_")) {
                    String[] values = (String[])params.get(key);
                    String valueString = values[0];
                    byte[] bytes  = Base64.decode(valueString.getBytes());
                    try {
                        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
                        Object requestObject = requestObject = ois.readObject();
                        ois.close();
                        // put the de-serialized object back into the request
                        String requestObjectKey = key.substring((cacheId + "_attribute_").length(), key.length());
                        request.setAttribute(requestObjectKey, requestObject);
                    } catch (java.io.OptionalDataException ode) {
                        Debug.print("ClientCacheLinkFlowHandler caught: " + ode);
                    } catch (java.lang.ClassNotFoundException cnfe) {
                        Debug.print("ClientCacheLinkFlowHandler caught: " + cnfe);                  
                    } catch (java.io.IOException iox) {
                        Debug.print("ClientCacheLinkFlowHandler caught: " + iox);                  
                    }
                }
            }
        }
        return forwardScreen;
    }
    
    
    public void doEnd(HttpServletRequest request) {
    }

}

