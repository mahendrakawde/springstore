/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: AccountDAO.java,v 1.2 2004/05/26 00:06:22 inder Exp $ */

package com.sun.j2ee.blueprints.customer.dao;

import com.sun.j2ee.blueprints.customer.Account;
import com.sun.j2ee.blueprints.util.dao.DAOSystemException;

/**
 * This is the interface for Account DAO.
 */
public interface AccountDAO {

    public void create(Account accountDetails) throws DAOSystemException,
                                AccountDAODupKeyException,
                                AccountDAODBUpdateException,
                                AccountDAOException;

    public Account getAccount(String userId) throws AccountDAOFinderException,
                                            DAOSystemException;
}
