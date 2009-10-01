/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: HTMLAction.java,v 1.2 2004/05/26 00:07:32 inder Exp $ */

package com.sun.j2ee.blueprints.waf.controller.web.html;

// j2ee imports
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletContext;

// WAF imports
import com.sun.j2ee.blueprints.waf.controller.web.Action;
import com.sun.j2ee.blueprints.waf.controller.Event;
import com.sun.j2ee.blueprints.waf.controller.EventResponse;

/**
 * This class is the base interface to request handlers on the
 * web tier. 
 *
*/
public interface HTMLAction extends Action {
    
    public void setServletContext(ServletContext context);
    public void doStart(HttpServletRequest request);
    public Event perform(HttpServletRequest request) throws HTMLActionException;
    public void doEnd(HttpServletRequest request, EventResponse eventResponse);
}

