/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: AccountDAODupKeyException.java,v 1.2 2004/05/26 00:06:22 inder Exp $ */

package com.sun.j2ee.blueprints.customer.dao;

/**
 * AccountDAODupKeyException is thrown by the DAOs of the account
 * component when a row is already found with a given primary key.
 */
public class AccountDAODupKeyException extends AccountDAOException {

    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public AccountDAODupKeyException (String str) {
        super(str);
    }

    /**
     * Default constructor. Takes no arguments
     */
    public AccountDAODupKeyException () {
        super();
    }

}
