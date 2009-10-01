/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: FlowHandler.java,v 1.2 2004/05/26 00:07:30 inder Exp $ */

package com.sun.j2ee.blueprints.waf.controller.web;

import javax.servlet.http.HttpServletRequest;

/**
 * This class is the base interface to flow handlers on the
 * web tier. 
 *
*/
public interface FlowHandler extends java.io.Serializable {

    public void doStart(HttpServletRequest request);
    public String processFlow(HttpServletRequest request) throws FlowHandlerException;
    public void doEnd(HttpServletRequest request);

}

