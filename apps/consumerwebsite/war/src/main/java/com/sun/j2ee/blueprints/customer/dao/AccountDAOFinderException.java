/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: AccountDAOFinderException.java,v 1.2 2004/05/26 00:06:22 inder Exp $ */

package com.sun.j2ee.blueprints.customer.dao;

/**
 * AccountDAOFinderException is thrown by the DAOs of the account component when
 * there is no row corresponding to a primary key
 */
public class AccountDAOFinderException extends AccountDAOException {

	/**
	 * Constructor
	 * 
	 * @param str
	 *            a string that explains what the exception condition is
	 */
	public AccountDAOFinderException(String str) {
		super(str);
	}

	/**
	 * Default constructor. Takes no arguments
	 */
	public AccountDAOFinderException() {
		super();
	}

}
