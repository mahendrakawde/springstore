/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: JNDINames.java,v 1.2 2004/05/26 00:07:34 inder Exp $ */

package com.sun.j2ee.blueprints.waf.util;

/**
 * This class is the central location to store the internal 
 * JNDI names of various entities. Any change here should 
 * also be reflected in the deployment descriptors. 
 */
public class JNDINames {

    private JNDINames(){} // prevent instanciation
    
    /** JNDI name of the home interface of EJBController */
    public static final String EJB_CONTROLLER_EJBHOME = 
        "java:comp/env/ejb/local/EJBController";

    public static final String COMPONENT_MANAGER = 
        "java:comp/env/param/ComponentManager";
    
    public static final String DEFAULT_WEB_CONTROLLER =
            "java:comp/env/param/WebController";
}

