/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: HTMLActionException.java,v 1.2 2004/05/26 00:07:32 inder Exp $ */

package com.sun.j2ee.blueprints.waf.controller.web.html;

import com.sun.j2ee.blueprints.waf.controller.web.ActionException;

/**
 * This exception will be thrown when there is an error processing a flow handler
 */
public class HTMLActionException extends ActionException 
    implements java.io.Serializable {
    
    public HTMLActionException() {}    
    public HTMLActionException(String str) { super(str); }
    public HTMLActionException(Throwable cause) { super(cause); }    
    public HTMLActionException(String str, Throwable cause) { super(str, cause); }
}

