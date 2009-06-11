/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: SignOnLongIdException.java,v 1.2 2004/05/26 00:06:25 inder Exp $ */

package com.sun.j2ee.blueprints.signon;

/**
 * SignOnLongIdException is thrown by the the signon
 * component when user id or password specified is too long.
 */
public class SignOnLongIdException extends RuntimeException {

    public SignOnLongIdException () { }
    public SignOnLongIdException (String str) { super(str); }
    public SignOnLongIdException (Throwable cause) { super(cause); }
    public SignOnLongIdException (String str, Throwable cause) { super(str, cause); }
}
