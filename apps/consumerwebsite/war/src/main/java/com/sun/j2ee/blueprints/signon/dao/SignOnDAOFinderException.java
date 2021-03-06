/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: SignOnDAOFinderException.java,v 1.2 2004/05/26 00:06:26 inder Exp $ */

package com.sun.j2ee.blueprints.signon.dao;

/**
 * SignOnDAOFinderException is thrown by the DAOs of the signon component when
 * there is no row corresponding to a primary key
 */
public class SignOnDAOFinderException extends Exception {

	/**
	 * Constructor
	 * 
	 * @param str
	 *            a string that explains what the exception condition is
	 */
	public SignOnDAOFinderException(String str) {
		super(str);
	}

	/**
	 * Default constructor. Takes no arguments
	 */
	public SignOnDAOFinderException() {
		super();
	}

}
