/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: WebKeys.java,v 1.2 2004/05/26 00:07:34 inder Exp $ */

package com.sun.j2ee.blueprints.waf.controller.web.util;

/**
 * This interface contains all the keys that are used to 
 * store data in the different scopes of web-tier. These 
 * values are the same as those used in the JSP 
 * pages (useBean tags).
 */
public class WebKeys {
    
    protected WebKeys() {} // prevent instanciation

    public static final String COMPONENT_MANAGER = "com.sun.j2ee.blueprints.waf.COMPONENT_MANAGER";
    public static final String SCREEN_FLOW_MANAGER = "com.sun.j2ee.blueprints.waf.SCREEN_FLOW_MANAGER";
    public static final String REQUEST_PROCESSOR = "com.sun.j2ee.blueprints.waf.REQUEST_PROCESSOR";
    public static final String CURRENT_SCREEN = "com.sun.j2ee.blueprints.waf.CURRENT_SCREEN";
    public static final String PREVIOUS_SCREEN = "com.sun.j2ee.blueprints.waf.PREVIOUS_SCREEN";
    public static final String CURRENT_URL = "com.sun.j2ee.blueprints.waf.CURRENT_URL";
    public static final String PREVIOUS_URL = "com.sun.j2ee.blueprints.waf.PREVIOUS_URL";
    public static final String PREVIOUS_REQUEST_PARAMETERS = "com.sun.j2ee.blueprints.waf.PREVIOUS_REQUEST_PARAMETERS";
    public static final String PREVIOUS_REQUEST_ATTRIBUTES = "com.sun.j2ee.blueprints.waf.PREVIOUS_REQUEST_ATTRIBUTES";
    public static final String URL_MAPPINGS = "com.sun.j2ee.blueprints.waf.URL_MAPPINGS";
    public static final String EVENT_MAPPINGS = "com.sun.j2ee.blueprints.waf.EVENT_MAPPINGS";
    public static final String MISSING_FORM_DATA = "com.sun.j2ee.blueprints.waf.MISSING_FORM_DATA";
    public static final String SERVER_TYPE = "com.sun.j2ee.blueprints.waf.SERVER_TYPE";
    public static final String LOCALE = "com.sun.j2ee.blueprints.waf.LOCALE";
    public static final String WEB_CONTROLLER = "com.sun.j2ee.blueprints.waf.WEB_CLIENT_CONTROLLER";
    public static final String EJB_CONTROLLER = "com.sun.j2ee.blueprints.waf.EJB_CLIENT_CONTROLLER ";
    public static final String WAF_EXCEPTION = "WAFException";
}

