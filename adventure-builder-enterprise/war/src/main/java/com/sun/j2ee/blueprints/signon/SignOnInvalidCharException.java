/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: SignOnInvalidCharException.java,v 1.2 2004/05/26 00:06:25 inder Exp $ */

package com.sun.j2ee.blueprints.signon;

/**
 * SignOnInvalidCharException is an exception that extends the
 * SignOnException. This is thrown by the the signon
 * component when user id or password specified is too long.
 */
public class SignOnInvalidCharException extends RuntimeException {

    public SignOnInvalidCharException () { }
    public SignOnInvalidCharException (String str) { super(str); }
    public SignOnInvalidCharException (Throwable cause) { super(cause); }
    public SignOnInvalidCharException (String str, Throwable cause) { super(str, cause); }
}
