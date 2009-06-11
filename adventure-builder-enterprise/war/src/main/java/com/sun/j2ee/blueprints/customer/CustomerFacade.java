/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: CustomerFacade.java,v 1.2 2004/05/26 00:06:21 inder Exp $ */

package com.sun.j2ee.blueprints.customer;

import com.sun.j2ee.blueprints.customer.dao.*;
import com.sun.j2ee.blueprints.util.dao.DAOFactory;

/**
 * This class is accessed by any client wanting to access the customer data
 **/
public class CustomerFacade {
    
    private AccountDAO accountDao = null;
    
    public CustomerFacade() {
        accountDao = (AccountDAO) DAOFactory.getDAO(JNDINames.ACCOUNT_DAO_CLASS);
    }
    
    
    public void createAccount(Account accountDetails)throws CustomerException {
        try {
            accountDao.create(accountDetails);
        } catch (Exception e) {
            throw new CustomerException("Exception in CustomerFacade while creating account.", e);
        }
    }
    
    public Account getAccount(String userId) throws CustomerException {
        Account account = null;
        try {
            account = accountDao.getAccount(userId);
        } catch(AccountDAOFinderException fe) {
            throw new CustomerException("Exception in CustomerFacade while creating account.", fe);
        }
        return account;
    }
}
