package com.sun.j2ee.blueprints.customer.dao;

import com.sun.blueprints.test.MockHolder;
import com.sun.j2ee.blueprints.customer.Account;
import com.sun.j2ee.blueprints.util.dao.DAOSystemException;

/**
 * Mock implementation of the AccountDAO. It delegates to the one registered in
 * the MockHolder.
 *
 * @author Marten Deinum (mdeinum@gmail.com)
 */
public class MockDelegatingAccountDao implements AccountDAO {

	@Override
	public void create(Account accountDetails) throws DAOSystemException,
			AccountDAODupKeyException, AccountDAODBUpdateException,
			AccountDAOException {
		AccountDAO delegate = MockHolder.getMock(AccountDAO.class);
		delegate.create(accountDetails);
	}

	@Override
	public Account getAccount(String userId) throws AccountDAOFinderException,
			DAOSystemException {
		AccountDAO delegate = MockHolder.getMock(AccountDAO.class);
		return delegate.getAccount(userId);
	}

}
