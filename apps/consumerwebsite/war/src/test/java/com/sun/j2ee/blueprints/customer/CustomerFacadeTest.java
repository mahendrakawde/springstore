package com.sun.j2ee.blueprints.customer;


import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

import com.sun.j2ee.blueprints.customer.dao.AccountDAO;
import com.sun.j2ee.blueprints.customer.dao.AccountDAODupKeyException;
import com.sun.j2ee.blueprints.customer.dao.AccountDAOException;
import com.sun.j2ee.blueprints.customer.dao.JNDINames;
import com.sun.j2ee.blueprints.customer.dao.MockDelegatingAccountDao;
import com.sun.j2ee.blueprints.test.AbstractJndiContextTests;
import com.sun.j2ee.blueprints.test.MockHolder;
import com.sun.j2ee.blueprints.test.annotation.JndiConfig;
import com.sun.j2ee.blueprints.test.data.StubTestDataUtil;


public class CustomerFacadeTest extends AbstractJndiContextTests {

	private AccountDAO dao;
	private CustomerFacade facade;
	
	@Before
	public void onSetup() throws Exception {
		dao = EasyMock.createMock(AccountDAO.class);
		MockHolder.setMock(AccountDAO.class, dao);
		facade = new CustomerFacade();
	}

	@JndiConfig
	public void setupJndiContext(final SimpleNamingContextBuilder builder) throws Exception {
		builder.bind(JNDINames.ACCOUNT_DAO_CLASS, MockDelegatingAccountDao.class.getName());		
	}
	
	@After
	public void after() {
		EasyMock.reset(dao);
	}
	
	@Test(expected=CustomerException.class)
	public void exceptionOnFinderException() throws Exception {
		String userId = "321";
		expect(dao.getAccount(userId)).andThrow(new CustomerException());
		replay(dao);
		facade.getAccount(userId);
		verify(dao);
	}
	
	@Test
	public void getAccount() throws Exception {
		String userId = "123";
		Account account = StubTestDataUtil.createAccount(userId);
		expect(dao.getAccount(userId)).andReturn(account);
		replay(dao);
		assertEquals(account, facade.getAccount(userId));
		verify(dao);
	}
	
	@Test
	public void createAccount() throws Exception {
		Account account = StubTestDataUtil.createAccount("dummy");
		dao.create(account);
		replay(dao);
		facade.createAccount(account);
		verify(dao);
	}

	@Test(expected=CustomerException.class)
	public void createAccountDuplicate() throws Exception {
		Account account = StubTestDataUtil.createAccount("dummy");
		dao.create(account);
		expectLastCall().andThrow(new AccountDAODupKeyException());
		replay(dao);
		facade.createAccount(account);
		verify(dao);
	}

	@Test(expected=CustomerException.class)
	public void createAccountInvalidData() throws Exception {
		Account account = StubTestDataUtil.createAccount("dummy");
		dao.create(account);
		expectLastCall().andThrow(new AccountDAOException());
		replay(dao);
		facade.createAccount(account);
		verify(dao);
	}
	
	
}
