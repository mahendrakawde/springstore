/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: HTMLActionSupport.java,v 1.2 2004/05/26 00:07:33 inder Exp $ */

package com.sun.j2ee.blueprints.waf.controller.web.html;

// j2ee imports
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.j2ee.blueprints.waf.controller.Event;
import com.sun.j2ee.blueprints.waf.controller.EventResponse;
import com.sun.j2ee.blueprints.waf.controller.web.ActionException;

/**
 * This class is the default implementation of the WebAction 
 *
*/
public abstract class HTMLActionSupport implements HTMLAction {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
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

