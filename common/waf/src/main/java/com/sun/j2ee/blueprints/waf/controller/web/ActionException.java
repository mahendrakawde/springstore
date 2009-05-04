/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: ActionException.java,v 1.2 2004/05/26 00:07:29 inder Exp $ */

package com.sun.j2ee.blueprints.waf.controller.web;

/**
 * This exception will be thrown when there is an error processing a flow handler
 */
public class ActionException extends Exception 
    implements java.io.Serializable {
    
    public ActionException() {}    
    public ActionException(String str) { super(str);  }
    public ActionException(Throwable cause) { super(cause); }    
    public ActionException(String str, Throwable cause) { super(str, cause);  }
}

