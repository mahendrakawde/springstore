/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: Action.java,v 1.2 2004/05/26 00:07:29 inder Exp $ */

package com.sun.j2ee.blueprints.waf.controller.web;

import javax.servlet.ServletRequest;
import javax.servlet.ServletContext;

// WAF imports
import com.sun.j2ee.blueprints.waf.controller.Event;
import com.sun.j2ee.blueprints.waf.controller.EventException;
import com.sun.j2ee.blueprints.waf.controller.EventResponse;

/**
 * This class is the base interface to request handlers on the
 * web tier. 
 *
*/
public interface Action extends java.io.Serializable {
    
    public void setServletContext(ServletContext context);
    public void doStart(ServletRequest request);
    public Event perform(ServletRequest request) throws ActionException;
    public void doEnd(ServletRequest request, EventResponse eventResponse);
}

