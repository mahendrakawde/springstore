package com.sun.j2ee.blueprints.signon;


import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Test;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

import com.sun.blueprints.test.AbstractJndiContextTests;
import com.sun.blueprints.test.MockHolder;
import com.sun.j2ee.blueprints.signon.dao.InvalidPasswordException;
import com.sun.j2ee.blueprints.signon.dao.JNDINames;
import com.sun.j2ee.blueprints.signon.dao.SignOnDAODupKeyException;
import com.sun.j2ee.blueprints.signon.dao.SignOnDAOFinderException;
import com.sun.j2ee.blueprints.signon.dao.UserDAO;


public class SignOnFacadeTest extends AbstractJndiContextTests {

	private UserDAO dao;
	private SignOnFacade facade;
	
	
	@Override
	protected void setupJndiContext() throws Exception {
		//Setup JNDI 
		SimpleNamingContextBuilder builder = SimpleNamingContextBuilder.emptyActivatedContextBuilder();
		builder.bind(JNDINames.SIGNON_DAO_CLASS, "com.sun.j2ee.blueprints.signon.dao.MockDelegatingUserDao");
	}

	@Override
	protected void onSetup() throws Exception {
		dao = EasyMock.createMock(UserDAO.class);
		MockHolder.setMock(UserDAO.class, dao);
		facade = new SignOnFacade();
	}
	
	@After
	public void after() {
		MockHolder.clear();
		EasyMock.reset(dao);
	}

	@Test(expected=SignOnLongIdException.class)
	public void usernameToLong() {
		String username = "12345678901234567890ABCDEF";
		String password = "12345678901234567890ABCDEF";
		facade.createSignOn(username, password);

	}
	@Test(expected=SignOnLongIdException.class)
	public void passwordToLong() {
		String username = "12345678901234567890";
		String password = "12345678901234567890ABCDEF";
		facade.createSignOn(username, password);
	}
	
	@Test(expected=SignOnInvalidCharException.class)
	public void illegalCharacterInUsername1() {
		String username = "dummy%";
		String password = "test";
		facade.createSignOn(username, password);
	}

	@Test(expected=SignOnInvalidCharException.class)
	public void illegalCharacterInUsername2() {
		String username = "dummy*";
		String password = "test";
		facade.createSignOn(username, password);
	}

	@Test(expected=SignOnDupKeyException.class)
	public void createSignOnDuplicateKey() throws Exception {
		String username = "dummy";
		String password = "test";
		dao.createUser(username, password);
		expectLastCall().andThrow(new SignOnDAODupKeyException());
		replay(dao);
		facade.createSignOn(username, password);		
	}

	@Test
	public void createSignOn() throws Exception {
		String username = "dummy";
		String password = "test";
		dao.createUser(username, password);
		replay(dao);
		facade.createSignOn(username, password);
		verify(dao);
	}

	@Test
	public void authenticateFalse() throws Exception {
		String username = "dummy";
		String password = "test";
		expect(dao.matchPassword(username, password)).andReturn(false);
		replay(dao);
		assertFalse(facade.authenticate(username, password));
		verify(dao);
	}

	@Test
	public void authenticateFalseOnSignOnDAOFinderException() throws Exception {
		String username = "dummy";
		String password = "test";
		expect(dao.matchPassword(username, password)).andThrow(new SignOnDAOFinderException());
		replay(dao);
		assertFalse(facade.authenticate(username, password));
		verify(dao);
	}

	@Test
	public void authenticateFalseOnInvalidPasswordException() throws Exception {
		String username = "dummy";
		String password = "test";
		expect(dao.matchPassword(username, password)).andThrow(new InvalidPasswordException());
		replay(dao);
		assertFalse(facade.authenticate(username, password));
		verify(dao);
	}
	
	@Test
	public void authenticateTrue() throws Exception {
		String username = "dummy";
		String password = "test";
		expect(dao.matchPassword(username, password)).andReturn(true);
		replay(dao);
		assertTrue(facade.authenticate(username, password));
		verify(dao);
	}
	
	
}
