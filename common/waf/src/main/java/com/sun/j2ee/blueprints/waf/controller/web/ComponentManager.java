/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: ComponentManager.java,v 1.2 2004/05/26 00:07:29 inder Exp $ */

package com.sun.j2ee.blueprints.waf.controller.web;

import javax.servlet.http.*;

import com.sun.j2ee.blueprints.waf.controller.web.WebController;

/** 
 * This interface defines the services that need to be accessed from multiple
 * components in the web tier. This class extends the HttpSessionListener to
 * ensure that this class is loaded at startup.
 *
 * Implementations of this class can be used to initialize objects used in the
 * persentation tier.
 *
 */
public interface ComponentManager extends HttpSessionListener {

    /**
     * Save the WebController in the ServletContext. The controller should be not
     * tied to any user and should be stateless
     */
    public WebController getWebController(HttpSession session);

}


