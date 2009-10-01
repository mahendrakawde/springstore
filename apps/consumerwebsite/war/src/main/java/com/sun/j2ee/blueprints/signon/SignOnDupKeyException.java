/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: SignOnDupKeyException.java,v 1.2 2004/05/26 00:06:24 inder Exp $ */

package com.sun.j2ee.blueprints.signon;

/**
 * SignOnDAODupKeyException is thrown by the DAOs of the signon
 * component when a row is already found with a given primary key.
 * This is thrown when the user input is  fails a validation 
 * test.
 */
public class SignOnDupKeyException extends RuntimeException {

    public SignOnDupKeyException () { }
    public SignOnDupKeyException (String str) { super(str); }
    public SignOnDupKeyException (Throwable cause) { super(cause); }
    public SignOnDupKeyException (String str, Throwable cause) { super(str, cause); }
}
