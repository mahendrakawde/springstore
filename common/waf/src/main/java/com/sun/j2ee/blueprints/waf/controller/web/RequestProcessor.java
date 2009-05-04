/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: RequestProcessor.java,v 1.3 2004/08/27 22:14:10 gmurray71 Exp $ */

package com.sun.j2ee.blueprints.waf.controller.web;

import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

// WAF imports
import com.sun.j2ee.blueprints.waf.util.JNDINames;
import com.sun.j2ee.blueprints.waf.controller.web.util.WebKeys;
import com.sun.j2ee.blueprints.waf.controller.*;
// tracer import
import com.sun.j2ee.blueprints.util.tracer.Debug;

/** 
 * This is the web tier controller for the sample application.
 *
 * This class is responsible for processing web requests 
 * that could originate from any number of front controllers
 *
 * This class is responsible for ensuring that the dependencies
 * of the client accessing the controller prior to the being
 * passed off to the WebController.
 * 
 */
public class RequestProcessor implements java.io.Serializable {

    private ServletContext context;
    private HashMap urlMappings;
    private HashMap eventMappings;
    private HashMap actionMap;

    public RequestProcessor() {
        actionMap = new HashMap();
    }


    public void init(ServletContext context) {
        this.context = context;
        urlMappings = (HashMap)context.getAttribute(WebKeys.URL_MAPPINGS);
        eventMappings = (HashMap)context.getAttribute(WebKeys.EVENT_MAPPINGS);
    }
    
    /**
     * The UrlMapping object contains information that will match
     * a url to a mapping object that contains information about
     * the current screen, the HTMLAction that is needed to
     * process a request, and the HTMLAction that is needed
     * to insure that the propper screen is displayed.
    */
    private URLMapping getURLMapping(String urlPattern) {
        if ((urlMappings != null) && urlMappings.containsKey(urlPattern)) {
            return (URLMapping)urlMappings.get(urlPattern);
        } else {
            return null;
        }
    }

    /**
     * The EventMapping object contains information that will match
     * a event class name to an EJBActionClass.
     *
    */
    private EventMapping getEventMapping(Event eventClass) {
        // get the fully qualified name of the event class
        String eventClassName = eventClass.getClass().getName();
        if ((eventMappings != null) && eventMappings.containsKey(eventClassName)) {
            return (EventMapping)eventMappings.get(eventClassName);
        } else {
            return null;
        }
    }

    /**
    * This method is the core of the RequestProcessor. It receives all requests
    *  and generates the necessary events.
    */
    public void processRequest(URLMapping urlMapping, ServletRequest request) 
         throws ActionException, EventException, ServletException {
        Event ev = null;
        Action action = getAction(urlMapping);
        if (action != null) {
            action.setServletContext(context);
            action.doStart(request);
            ev = action.perform(request);
            EventResponse eventResponse = null;
            if (ev != null) {
               // set the command class name on the event
                EventMapping eventMapping = getEventMapping(ev);
                if (eventMapping != null) {
                    ev.setCommandClassName(eventMapping.getCommandClassName());
                }
                HttpSession session = ((HttpServletRequest)request).getSession();
                ComponentManager cm = 
                      (ComponentManager)session.getAttribute(WebKeys.COMPONENT_MANAGER);
               WebController wc = cm.getWebController(session);
               if (wc != null) {
                   eventResponse  = wc.handleEvent(ev, session);
               }
           }
           action.doEnd(request, eventResponse);
        }
    }
    
    /**
     * This method load the necessary Action class necessary to
     * process a the request for the specified URL. Action instances
     * are cached so that they may be re-used.
     */
    private Action getAction(URLMapping urlMapping) {
        Action handler = null;
        if (urlMapping != null) {      
            if (urlMapping.isAction()) {
                String actionClassString = urlMapping.getWebAction();
                if ((actionClassString != null) &&
                    actionMap.containsKey(actionClassString)) {
                        handler = (Action)actionMap.get(actionClassString);
                } else {
                    try {
                        handler = (Action)getClass().getClassLoader().loadClass(actionClassString).newInstance();
                        actionMap.put(actionClassString, handler);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        Debug.print("RequestProcessor caught loading action: " + ex);          
                    }
                }
            }
        }
        return handler;
    }
}

