package com.sun.j2ee.blueprints.signon.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

import com.sun.j2ee.blueprints.test.JndiNames;
import com.sun.j2ee.blueprints.test.annotation.JndiConfig;
import com.sun.j2ee.blueprints.test.jdbc.AbstractDaoTests;
import com.sun.j2ee.blueprints.util.dao.DAOSystemException;

public class PointbaseUserDAOTest extends AbstractDaoTests {

	private PointbaseUserDAO dao;	
	
	@Before
	public void setup() throws Exception {
		dao = new PointbaseUserDAO();
	}

	@JndiConfig
	public void setupJndiContext(SimpleNamingContextBuilder builder) throws Exception {
		builder.bind(JNDINames.SIGNON_DATASOURCE, JndiNames.SIGNNONDB);
		builder.bind(JndiNames.SIGNNONDB, getDataSource());
	}
	
	
	@Test
	public void testCreateUser() throws Exception {
		int count = countRowsInTable(DatabaseNames.SIGNON_TABLE);
		dao.createUser("tester", "test123");
		assertEquals(count + 1, countRowsInTable(DatabaseNames.SIGNON_TABLE));		
	}

	@Test(expected=DAOSystemException.class)
	public void testCreateUserDuplicate() throws Exception {
		dao.createUser("j2ee", "test123");
	}
	
	@Test
	public void testMatchPasswordTrue() throws Exception {		
		assertTrue(dao.matchPassword("j2ee", "j2ee"));
	}

	@Test(expected=InvalidPasswordException.class)
	public void testMatchPasswordFalse() throws Exception {		
		dao.matchPassword("j2ee", "jee");
	}
	
	@Test(expected=SignOnDAOFinderException.class) 
	public void testMatchPasswordNoUser() throws Exception {
		dao.matchPassword("dummy", "dummy");
	}


}
