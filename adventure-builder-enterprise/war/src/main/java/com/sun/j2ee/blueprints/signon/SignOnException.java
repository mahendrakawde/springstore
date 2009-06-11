/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: SignOnException.java,v 1.2 2004/05/26 00:06:24 inder Exp $ */

package com.sun.j2ee.blueprints.signon;

/**
 * SignOnException is an exception that extends the
 * standard Exception. This is thrown by the the signon
 * component when there is some failure because of user error
 */
public class SignOnException extends RuntimeException {

    public SignOnException () { }
    public SignOnException (String str) { super(str);  }
    public SignOnException (Throwable cause) { super(cause); }
    public SignOnException (String str, Throwable cause) { super(str, cause);  }
}
