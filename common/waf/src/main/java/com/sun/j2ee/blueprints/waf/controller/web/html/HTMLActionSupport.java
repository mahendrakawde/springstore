/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: HTMLActionSupport.java,v 1.2 2004/05/26 00:07:33 inder Exp $ */

package com.sun.j2ee.blueprints.waf.controller.web.html;

// j2ee imports
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletRequest;
import javax.servlet.ServletContext;

// WAF imports
import com.sun.j2ee.blueprints.waf.controller.web.ActionException;
import com.sun.j2ee.blueprints.waf.controller.Event;
import com.sun.j2ee.blueprints.waf.controller.EventException;
import com.sun.j2ee.blueprints.waf.controller.EventResponse;

/**
 * This class is the default implementation of the WebAction 
 *
*/
public abstract class HTMLActionSupport implements HTMLAction {

    protected ServletContext context;
    
    public void setServletContext(ServletContext context) {
        this.context  = context;
    }
    
    public void doStart(ServletRequest request){
        doStart((HttpServletRequest)request);
    }
    
    public void doStart(HttpServletRequest request){}
    
    public void doEnd(ServletRequest request, EventResponse eventResponse){
        doEnd((HttpServletRequest)request, eventResponse);
    }
    
    public Event perform(ServletRequest request) throws ActionException{
        return perform((HttpServletRequest)request);
    }
    
    public void doEnd(HttpServletRequest request, EventResponse eventResponse){}
}

