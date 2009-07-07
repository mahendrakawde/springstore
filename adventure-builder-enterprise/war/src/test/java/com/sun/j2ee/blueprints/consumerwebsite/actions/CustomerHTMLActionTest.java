package com.sun.j2ee.blueprints.consumerwebsite.actions;

import static org.easymock.EasyMock.*;
import static org.easymock.EasyMock.isA;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.mock.web.MockHttpSession;

import com.sun.blueprints.test.MockHolder;
import com.sun.blueprints.test.data.StubTestDataUtil;
import com.sun.blueprints.test.jdbc.AbstractActionTests;
import com.sun.j2ee.blueprints.consumerwebsite.AdventureKeys;
import com.sun.j2ee.blueprints.consumerwebsite.CustomerBean;
import com.sun.j2ee.blueprints.customer.Account;
import com.sun.j2ee.blueprints.customer.dao.AccountDAO;
import com.sun.j2ee.blueprints.signon.dao.UserDAO;
import com.sun.j2ee.blueprints.signon.web.SignOnFilter;
import com.sun.j2ee.blueprints.waf.controller.web.html.HTMLAction;

public class CustomerHTMLActionTest extends AbstractActionTests {

	private CustomerHTMLAction action = new CustomerHTMLAction();
	
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	
	@Override
	protected HTMLAction getActionUnderTest() {
		return this.action;
	}

	@Test
	public void performCreateAction() throws Exception {
		userDAO.createUser(USERNAME, PASSWORD);
		accountDAO.create(isA(Account.class));
		replay(userDAO, accountDAO);
		request.addParameter("target_action", CustomerHTMLAction.ACCOUNT_CREATE_ACTION);
		MockHttpSession session = (MockHttpSession) request.getSession();
		session.setAttribute(AdventureKeys.SIGN_ON_TEMP_USERNAME, USERNAME);
		session.setAttribute(AdventureKeys.SIGN_ON_TEMP_PASSWORD, PASSWORD);
		
		action.perform(request);
		
		assertNull(session.getAttribute(AdventureKeys.SIGN_ON_TEMP_USERNAME));
		assertNull(session.getAttribute(AdventureKeys.SIGN_ON_TEMP_PASSWORD));
		assertTrue((Boolean) session.getAttribute(SignOnFilter.SIGNED_ON_USER));
		assertEquals(USERNAME, session.getAttribute(SignOnFilter.USER_NAME));
		CustomerBean cb = (CustomerBean) session.getAttribute(AdventureKeys.CUSTOMER_BEAN); 
		assertNotNull(cb);
		assertEquals(USERNAME, cb.getUserId());
		verify(userDAO, accountDAO);
	}
	
	@Test
	public void performReadAction() throws Exception {
		request.addParameter("target_action", CustomerHTMLAction.ACCOUNT_READ_ACTION);
		MockHttpSession session = (MockHttpSession) request.getSession();
		session.setAttribute(SignOnFilter.SIGNED_ON_USER, Boolean.TRUE);
		session.setAttribute(SignOnFilter.USER_NAME, USERNAME);
		
		Account account = StubTestDataUtil.createAccount(USERNAME);
		expect(accountDAO.getAccount(USERNAME)).andReturn(account);
		replay(accountDAO);
		
		action.perform(request);
		CustomerBean cb = (CustomerBean) session.getAttribute(AdventureKeys.CUSTOMER_BEAN); 
		assertNotNull(cb);
		assertEquals(USERNAME, cb.getUserId());
		
		verify(accountDAO);
		
	}

}
