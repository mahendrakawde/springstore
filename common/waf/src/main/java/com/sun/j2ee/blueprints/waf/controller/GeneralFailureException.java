/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: GeneralFailureException.java,v 1.2 2004/05/26 00:07:26 inder Exp $ */

package com.sun.j2ee.blueprints.waf.controller;

/**
 * This exception is the base class for all the web runtime exceptions.
 */
public class GeneralFailureException extends RuntimeException 
    implements java.io.Serializable {
    
    private Throwable t;

    public GeneralFailureException(String s) {
  super(s);
    }

    public GeneralFailureException(String s, Throwable t) {
  super(s);
        this.t = t;
    }

    public String getThrowable() {
        return ("Received throwable with Message: "+ t.getMessage());
    }
}

