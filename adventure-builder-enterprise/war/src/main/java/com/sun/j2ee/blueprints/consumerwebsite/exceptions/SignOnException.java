/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: SignOnException.java,v 1.2 2004/05/26 00:06:19 inder Exp $ */

package com.sun.j2ee.blueprints.consumerwebsite.exceptions;

import com.sun.j2ee.blueprints.waf.controller.web.html.HTMLActionException;

/**
 * An application exception indicating something has gone wrong
 *
 */
public class SignOnException extends HTMLActionException {

    public SignOnException() {}
    public SignOnException(String s) { super(s); }
    public SignOnException(Throwable cause) { super(cause); }
    public SignOnException(String s, Throwable cause) { super(s, cause); }
}
