/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: CustomerException.java,v 1.2 2004/05/26 00:06:21 inder Exp $ */

package com.sun.j2ee.blueprints.customer;

/**
 * CustomerAppException is an exception that extends the
 * standard Exception. This is thrown by the the customer
 * component when there is some failure because of user error
 */
public class CustomerException extends RuntimeException {
    public CustomerException() {}
    public CustomerException(String msg) { super(msg); }
    public CustomerException(String msg, Throwable cause) { super(msg, cause); }
    public CustomerException(Throwable cause) { super(cause); }    
}
