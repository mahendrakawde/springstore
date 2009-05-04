/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: UserDAO.java,v 1.2 2004/05/26 00:06:26 inder Exp $ */

package com.sun.j2ee.blueprints.signon.dao;

/**
 * This interface provides methods to view and modify sign on
 * information for a particular user.
 * All the data source access code will be encapsulated in 
 * the concrete implementations of this interface following the 
 * Data Access Object pattern.
 */
public interface UserDAO {

    public void createUser(String userName, String password) throws SignOnDAODupKeyException ;
    public boolean matchPassword(String userName, String password) 
                    throws SignOnDAOFinderException,InvalidPasswordException;

}

