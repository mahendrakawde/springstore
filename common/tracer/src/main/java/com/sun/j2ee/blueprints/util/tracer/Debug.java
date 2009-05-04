/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: Debug.java,v 1.2 2004/05/26 00:07:20 inder Exp $ */

package com.sun.j2ee.blueprints.util.tracer;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * This class is just a helper class to make it handy
 * to print out debug statements
 */
public final class Debug {
    
    private static Logger logger = Logger.getLogger("com.sun.j2ee.blueprints");
    
    public static void print(String msg) {
        logger.log(Level.INFO, msg);
    }
    
    public static void print(Exception e, String msg) {
        print((Throwable)e, msg);
    }
    
    public static void print(Exception e) {
        print(e, null);
    }
    
    public static void print(Throwable t, String msg) {
        logger.log(Level.WARNING, "Received throwable with Message: "+msg, t);
    }
    
    public static void print(Throwable t) {
        print(t, null);
    }
}
