/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: PointbaseAccountDAO.java,v 1.2 2004/05/26 00:06:23 inder Exp $ */

package com.sun.j2ee.blueprints.customer.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.sun.j2ee.blueprints.customer.dao.JNDINames;
import com.sun.j2ee.blueprints.customer.Account;
import com.sun.j2ee.blueprints.customer.ContactInformation;
import com.sun.j2ee.blueprints.servicelocator.web.ServiceLocator;
import com.sun.j2ee.blueprints.util.dao.DAOSystemException;

/**
 * This class implements AccountDAO for Pointbase database. This class
 * encapsulates all the JDBC calls made to the Account. The logic of
 * inserting/fetching/updating/deleting the data in relational database tables
 * is implemented here.
 */
public class PointbaseAccountDAO implements AccountDAO {

	private final static String INSERT_ACCOUNT_QUERY_STR = "INSERT INTO "
			+ DatabaseNames.ACCOUNT_TABLE + "(userid,email,firstname,lastname,"
			+ "addr1,addr2,city,state,zip,country," + "phone)"
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private final static String SELECT_USER_ID_QUERY_STR = "SELECT userid FROM "
			+ DatabaseNames.ACCOUNT_TABLE + " WHERE userid = ?";

	private final static String SELECT_ACCOUNT_QUERY_STR = "SELECT "
			+ "userid,email,firstname,lastname,"
			+ "addr1,addr2,city,state,zip,country,phone" + " FROM "
			+ DatabaseNames.ACCOUNT_TABLE + " WHERE userid = ?";

	private final JdbcTemplate customerTemplate;

	public PointbaseAccountDAO() {
		super();
		final String dataSourceName = ServiceLocator.getInstance().getString(
				JNDINames.CUSTOMER_DATASOURCE);
		DataSource ds = ServiceLocator.getInstance().getDataSource(
				dataSourceName);
		customerTemplate = new JdbcTemplate(ds);
	}

	public void create(Account details) throws DAOSystemException,
			AccountDAODupKeyException, AccountDAODBUpdateException,
			AccountDAOException {
		insertAccount(details);
	}

	public Account getAccount(String userId) throws AccountDAOFinderException,
			DAOSystemException {
		return (selectAccount(userId));
	}

	private void insertAccount(Account details) throws DAOSystemException,
			AccountDAODupKeyException, AccountDAODBUpdateException,
			AccountDAOException {

		if (!isValidData(details.getUserId(), details.getContactInformation()))
			throw new AccountDAOException("Illegal data values for insert");
		if (userExists(details.getUserId()))
			throw new AccountDAODupKeyException("Account exists for "
					+ details.getUserId());

		int resultCount = customerTemplate.update(INSERT_ACCOUNT_QUERY_STR,
				new CustomerPreparedStatementSetter(details));
		if (resultCount != 1) {
			throw new AccountDAODBUpdateException(
					"ERROR in ACCOUNT_TABLE INSERT !! resultCount = "
							+ resultCount);
		}

	}

	private boolean isValidData(String userId, ContactInformation info) {
		if ((userId == null) || (info.getEMail() == null)
				|| (info.getGivenName() == null)
				|| (info.getFamilyName() == null)
				|| (info.getAddress().getStreetName1() == null)
				|| (info.getAddress().getCity() == null)
				|| (info.getAddress().getState() == null)
				|| (info.getAddress().getZipCode() == null)
				|| (info.getAddress().getCountry() == null)
				|| (info.getTelephone() == null))
			return (false);
		else
			return (true);
	}

	private boolean userExists(String userId) throws DAOSystemException {
		try {
			customerTemplate.queryForObject(SELECT_USER_ID_QUERY_STR,
					new Object[] { userId }, String.class);
			return true;
		} catch (IncorrectResultSizeDataAccessException e) {
			return false;
		}
	}

	private Account selectAccount(String userId) throws DAOSystemException,
			AccountDAOFinderException {

		Object[] params = new Object[] { userId.trim() };
		try {
			Account account = (Account) customerTemplate.queryForObject(
					SELECT_ACCOUNT_QUERY_STR, params, new AccountRowMapper());
			return account;
		} catch (IncorrectResultSizeDataAccessException e) {
			throw new AccountDAOFinderException("No record for primary key "
					+ userId);
		}
	}

	private static class CustomerPreparedStatementSetter implements
			PreparedStatementSetter {

		private final Account details;

		public CustomerPreparedStatementSetter(Account details) {
			super();
			this.details = details;
		}

		public void setValues(PreparedStatement stmt) throws SQLException {
			ContactInformation info = details.getContactInformation();

			stmt.setString(1, details.getUserId().trim());
			stmt.setString(2, info.getEMail().trim());
			stmt.setString(3, info.getGivenName().trim());
			stmt.setString(4, info.getFamilyName().trim());
			stmt.setString(5, info.getAddress().getStreetName1().trim());

			if (info.getAddress().getStreetName2() != null)
				stmt.setString(6, info.getAddress().getStreetName2().trim());
			else
				stmt.setString(6, " ");

			stmt.setString(7, info.getAddress().getCity().trim());
			stmt.setString(8, info.getAddress().getState().trim());
			stmt.setString(9, info.getAddress().getZipCode().trim());
			stmt.setString(10, info.getAddress().getCountry().trim());
			stmt.setString(11, info.getTelephone().trim());
		}

	}
}
