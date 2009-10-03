/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: PointbaseUserDAO.java,v 1.2 2004/05/26 00:06:26 inder Exp $ */

package com.sun.j2ee.blueprints.signon.dao;

import javax.sql.DataSource;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.sun.j2ee.blueprints.signon.dao.JNDINames;
import com.sun.j2ee.blueprints.servicelocator.web.ServiceLocator;

/**
 * This class encapsulates all the database access. It follows the Data Access
 * Object pattern.
 **/
public class PointbaseUserDAO implements UserDAO {

	private final static String MATCH_PASSWORD_QUERY = "SELECT password FROM "
			+ DatabaseNames.SIGNON_TABLE + " WHERE username = ?";
	private final static String CREATE_USER_QUERY = "INSERT INTO "
			+ DatabaseNames.SIGNON_TABLE + " VALUES(?, ?)";

	private JdbcTemplate userTemplate;

	public PointbaseUserDAO() {
		super();
		final String dataSourceName = ServiceLocator.getInstance().getString(
				JNDINames.SIGNON_DATASOURCE);
		DataSource ds = ServiceLocator.getInstance().getDataSource(
				dataSourceName);
		this.userTemplate = new JdbcTemplate(ds);

	}

	/**
	 * @throws SignOnDAODupKeyException
	 *             if userName already exists in database
	 **/
	public void createUser(String userName, String password)
			throws SignOnDAODupKeyException {

		Object[] params = new Object[] { userName.trim(), password.trim() };
		try {
			userTemplate.update(CREATE_USER_QUERY, params);
		} catch (DataIntegrityViolationException ex) {
			throw new SignOnDAODupKeyException("Unable to create user. Duplicate Key : " + userName);
		}
	}

	/**
	 * @return true if userName already exists in database AND the corresponding
	 *         password in the database matches the password parameter
	 **/
	public boolean matchPassword(String userName, String password)
			throws SignOnDAOFinderException, InvalidPasswordException {

		try {
			Object[] params = new Object[] { userName.trim() };
			String dbPassword = (String) userTemplate.queryForObject(
					MATCH_PASSWORD_QUERY, params, java.lang.String.class);
			if (!dbPassword.equals(password)) {
				throw new InvalidPasswordException("Password does not match");
			}

		} catch (IncorrectResultSizeDataAccessException e) {
			throw new SignOnDAOFinderException("Unable to find user "
					+ userName);
		}
		return true;
	}
}
