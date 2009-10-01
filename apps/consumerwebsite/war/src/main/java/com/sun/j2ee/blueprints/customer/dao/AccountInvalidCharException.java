/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: AccountInvalidCharException.java,v 1.2 2004/05/26 00:06:23 inder Exp $ */

package com.sun.j2ee.blueprints.customer.dao;

/**
 * AccountInvalidCharException is thrown by the the account
 * component when there is some failure because of user error
 */
public class AccountInvalidCharException extends AccountException {

    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public AccountInvalidCharException (String str) {
        super(str);
    }

    public AccountInvalidCharException () {
        super();
    }

}
