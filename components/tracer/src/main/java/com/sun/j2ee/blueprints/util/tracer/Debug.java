/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: Debug.java,v 1.2 2004/05/26 00:07:20 inder Exp $ */

package com.sun.j2ee.blueprints.util.tracer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This class is just a helper class to make it handy
 * to print out debug statements
 * 
 * @deprecated Classes should handle logging themselves.
 */
public final class Debug {
    
	private static final Logger LOGGER = LoggerFactory.getLogger("com.sun.j2ee.blueprints");
	
	private Debug() { super(); }
    
    public static void print(final String msg) {
        LOGGER.info(msg);
    }
    
    public static void print(final Exception e, final String msg) {
        print((Throwable)e, msg);
    }
    
    public static void print(final Exception e) {
        print(e, null);
    }
    
    public static void print(final Throwable t, final String msg) {
        LOGGER.warn("Received throwable with Message: "+msg, t);
    }
    
    public static void print(final Throwable t) {
        print(t, null);
    }
}
