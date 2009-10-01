/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: InvalidPasswordException.java,v 1.1 2004/07/20 01:08:19 yutayoshida Exp $ */

package com.sun.j2ee.blueprints.signon.dao;

/**
 * InvalidPasswordException is thrown by the the signon
 * component when there is some failure because of user error.
 * This is thrown when the user input is  fails a validation 
 * test.
 */
public class InvalidPasswordException extends RuntimeException {

    public InvalidPasswordException () { }
    public InvalidPasswordException (String str) { super(str); }
    public InvalidPasswordException (Throwable cause) { super(cause); }
    public InvalidPasswordException (String str, Throwable cause) { super(str, cause); }

}
