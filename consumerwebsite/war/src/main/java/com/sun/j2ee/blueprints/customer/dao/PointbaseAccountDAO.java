/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: PointbaseAccountDAO.java,v 1.2 2004/05/26 00:06:23 inder Exp $ */

package com.sun.j2ee.blueprints.customer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.Context;
import javax.naming.NamingException;
import java.util.ArrayList;
import java.util.Iterator;


import com.sun.j2ee.blueprints.customer.ContactInformation;
import com.sun.j2ee.blueprints.customer.Address;
import com.sun.j2ee.blueprints.customer.Account;
import com.sun.j2ee.blueprints.util.tracer.Debug;
import com.sun.j2ee.blueprints.util.dao.DAOUtils;
import com.sun.j2ee.blueprints.util.dao.DAOSystemException;

/**
 * This class implements AccountDAO for Pointbase database.
 * This class encapsulates all the JDBC calls made to the Account.
 * The logic of inserting/fetching/updating/deleting  the data in
 * relational database tables is implemented here.
 */
public class PointbaseAccountDAO implements AccountDAO {

   private final static String INSERT_ACCOUNT_QUERY_STR = "INSERT INTO " +
            DatabaseNames.ACCOUNT_TABLE 
            + "(userid,email,firstname,lastname,"
            + "addr1,addr2,city,state,zip,country,"
            + "phone)" + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
   private final static String  SELECT_USER_ID_QUERY_STR = 
       "SELECT userid FROM " + DatabaseNames.ACCOUNT_TABLE + 
       " WHERE userid = ?";

   private final static String  SELECT_ACCOUNT_QUERY_STR = "SELECT "+
       "userid,email,firstname,lastname,"+
       "addr1,addr2,city,state,zip,country,phone"+
       " FROM " + DatabaseNames.ACCOUNT_TABLE + " WHERE userid = ?";

   
    public PointbaseAccountDAO() {
    }

    public void create(Account details) throws DAOSystemException,
                                AccountDAODupKeyException,
                                AccountDAODBUpdateException,
                                AccountDAOException {
        insertAccount(details);
    }

    public Account getAccount(String userId) throws AccountDAOFinderException,
                                            DAOSystemException {
        return(selectAccount(userId));
    }


    private void insertAccount(Account details) throws
                                 DAOSystemException,
                                 AccountDAODupKeyException,
                                 AccountDAODBUpdateException,
                                 AccountDAOException {

        if (!isValidData(details.getUserId(), details.getContactInformation()))
            throw new AccountDAOException("Illegal data values for insert");
        if (userExists(details.getUserId()))
            throw new AccountDAODupKeyException("Account exists for "+
                                                details.getUserId());

        PreparedStatement stmt = null;
        ContactInformation info = details.getContactInformation();

        Connection dbConnection = null;
        try {
            dbConnection = DAOUtils.getDBConnection(JNDINames.CUSTOMER_DATASOURCE);
            stmt = dbConnection.prepareStatement(INSERT_ACCOUNT_QUERY_STR);

            stmt.setString(1, details.getUserId().trim() );
            stmt.setString(2, info.getEMail().trim()  );
            stmt.setString(3, info.getGivenName().trim() );
            stmt.setString(4, info.getFamilyName().trim()  );
            stmt.setString(5, info.getAddress().getStreetName1().trim()  );

            if (info.getAddress().getStreetName2() != null)
                stmt.setString(6, info.getAddress().getStreetName2().trim() );
            else
                stmt.setString(6, " ");
                
            stmt.setString(7, info.getAddress().getCity().trim() );
            stmt.setString(8, info.getAddress().getState().trim() );
            stmt.setString(9, info.getAddress().getZipCode().trim() );
            stmt.setString(10,info.getAddress().getCountry().trim() );
            stmt.setString(11, info.getTelephone().trim() );

            int resultCount = stmt.executeUpdate();

            if ( resultCount != 1 ) {
                throw new AccountDAODBUpdateException(
                    "ERROR in ACCOUNT_TABLE INSERT !! resultCount = " +
                                   resultCount);
            }
        } catch(SQLException se) {
            throw new DAOSystemException("SQLException while inserting new " +
                      "account; id = " + details.getUserId() + "\n", se);
        } finally {
            DAOUtils.closeStatement(stmt);
            DAOUtils.closeConnection(dbConnection);
        }
    }

    private boolean isValidData(String userId, ContactInformation info) {
        if ( (userId == null) ||
             ( info.getEMail() == null) ||
             (info.getGivenName() == null) || (info.getFamilyName() == null)
             || (info.getAddress().getStreetName1() == null) ||
             (info.getAddress().getCity() == null) ||
             (info.getAddress().getState() == null) ||
             (info.getAddress().getZipCode() == null) ||
             (info.getAddress().getCountry() == null)
             || (info.getTelephone() == null) )
            return (false);
        else
            return (true);
    }

    private boolean userExists (String userId) throws DAOSystemException {
        PreparedStatement stmt = null;
        ResultSet result = null;
        boolean returnValue = false;

        Connection dbConnection = null;
        try {
           dbConnection = DAOUtils.getDBConnection(JNDINames.CUSTOMER_DATASOURCE);
            stmt = dbConnection.prepareStatement(SELECT_USER_ID_QUERY_STR);
            stmt.setString(1, userId.trim());
            result = stmt.executeQuery();
            if ( !result.next() ) {
                returnValue = false;
            } else {
                userId = result.getString(1);
                returnValue = true;
            }
        } catch(SQLException se) {
            throw new DAOSystemException("SQLException while checking for an"
                           + " existing user - id -> " + userId + "\n", se);
        } finally {
            DAOUtils.closeResultSet(result);
            DAOUtils.closeStatement(stmt);
            DAOUtils.closeConnection(dbConnection);
        }
        return returnValue;
    }


    private Account selectAccount(String userId) throws
      DAOSystemException, AccountDAOFinderException {

        PreparedStatement stmt = null;
        ResultSet result = null;

        Connection dbConnection = null;
        try {
            dbConnection = DAOUtils.getDBConnection(JNDINames.CUSTOMER_DATASOURCE);
            stmt = dbConnection.prepareStatement(SELECT_ACCOUNT_QUERY_STR);
            stmt.setString(1, userId.trim());
            result = stmt.executeQuery();

            if ( !result.next() )
                throw new AccountDAOFinderException(
                                  "No record for primary key " + userId);

            int i = 1;
            userId = result.getString(i++);
            String email = result.getString(i++);
            String firstName = result.getString(i++);
            String lastName = result.getString(i++);
            String street1 = result.getString(i++);
            String street2 = result.getString(i++);
            String city = result.getString(i++);
            String state = result.getString(i++);
            String zip = result.getString(i++);
            String country = result.getString(i++);
            String phone = result.getString(i++);

            Address addr = new Address(street1, street2, city, state, zip,
                               country);
            ContactInformation info =
                new ContactInformation(lastName, firstName, phone,
                                          email, addr);
            return(new Account(userId, info));
        } catch(SQLException se) {
            throw new DAOSystemException("SQLException while getting " +
                      "account; id = " + userId + " :\n", se);
        } finally {
            DAOUtils.closeResultSet(result);
            DAOUtils.closeStatement(stmt);
            DAOUtils.closeConnection(dbConnection);
        }
    }
}
