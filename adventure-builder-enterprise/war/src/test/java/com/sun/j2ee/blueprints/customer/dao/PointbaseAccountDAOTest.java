package com.sun.j2ee.blueprints.customer.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

import com.sun.blueprints.test.JndiNames;
import com.sun.blueprints.test.data.StubTestDataUtil;
import com.sun.blueprints.test.jdbc.AbstractDaoTests;
import com.sun.j2ee.blueprints.customer.Account;
import com.sun.j2ee.blueprints.customer.Address;
import com.sun.j2ee.blueprints.customer.ContactInformation;
import com.sun.j2ee.blueprints.util.dao.DAOSystemException;

public class PointbaseAccountDAOTest extends AbstractDaoTests{

	private PointbaseAccountDAO dao = new PointbaseAccountDAO();

        @Override
	protected void setupJndiContext() throws Exception {
		//Setup JNDI 
		SimpleNamingContextBuilder builder = SimpleNamingContextBuilder.emptyActivatedContextBuilder();
		builder.bind(JNDINames.CUSTOMER_DATASOURCE, JndiNames.CUSTOMERDB);
		builder.bind(JndiNames.CUSTOMERDB, getDataSource());
		builder.bind(JNDINames.ACCOUNT_DAO_CLASS, "com.sun.j2ee.blueprints.customer.dao.PointbaseAccountDAO");
	}
	
	@Test
	public void testCreate() throws Exception {
		Account test = StubTestDataUtil.createAccount("test");
		int count = countRowsInTable("ACCOUNT");
		dao.create(test);
		assertEquals(count + 1, countRowsInTable("ACCOUNT"));
	}

	@Test(expected=AccountDAOException.class)
	public void testCreateInvalidData() throws Exception {
		Address address = new Address("Teststreet 1", "tst", "Testcity", "TST", "12345TS", null);
		ContactInformation ci=  new ContactInformation("Tester", "Testy", "0123456789", "test@testershome.com", address);
		Account test = new Account("test", ci);
		dao.create(test);
	}
	
	@Test(expected=AccountDAODupKeyException.class)
	public void testCreateDuplicate() throws Exception {
		Account test = StubTestDataUtil.createAccount("j2ee");
		dao.create(test);
	}

	
	@Test
	public void testGetAccount() throws Exception {
		Account account = dao.getAccount("j2ee");
		assertNotNull(account);
		assertEquals("j2ee", account.getUserId().trim());
	}
	
	@Test(expected=AccountDAOFinderException.class)
	public void testGetAccountNonExisting() throws AccountDAOFinderException, DAOSystemException {
		dao.getAccount("doesnotexist");
	}

}
