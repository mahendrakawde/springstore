/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: PointbaseUserDAO.java,v 1.2 2004/05/26 00:06:26 inder Exp $ */

package com.sun.j2ee.blueprints.signon.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.sql.Connection;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.Context;

import com.sun.j2ee.blueprints.util.tracer.Debug;
import com.sun.j2ee.blueprints.util.dao.DAOSystemException;
import com.sun.j2ee.blueprints.util.dao.DAOUtils;


/**
 * This class encapsulates all the database access. It follows
 * the Data Access Object pattern.
 **/
public class PointbaseUserDAO implements UserDAO {
    
    private final static String MATCH_PASSWORD_QUERY =
    "SELECT password FROM " + DatabaseNames.SIGNON_TABLE +
    " WHERE username = ?";
    private final static String CREATE_USER_QUERY = "INSERT INTO " +
    DatabaseNames.SIGNON_TABLE + " VALUES(?, ?)";
    
    public PointbaseUserDAO() {}
    
    
    /**
     * @throws SignOnDAODupKeyException if userName already exists in database
     **/
    public void createUser(String userName, String password)
    throws SignOnDAODupKeyException {
        
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DAOUtils.getDBConnection(JNDINames.SIGNON_DATASOURCE);
            ps = conn.prepareStatement(CREATE_USER_QUERY);
            ps.setString(1, userName.trim());
            ps.setString(2, password.trim());
            int result = ps.executeUpdate();
            if(result != 1) {
                throw new SignOnDAODupKeyException("Unable to create user. " +
                "Duplicate Key : " + userName);
            }
        } catch (SQLException se) {
            throw new DAOSystemException(se);
        } finally {
            DAOUtils.closeStatement(ps);
            DAOUtils.closeConnection(conn);
        }
    }
    
    
    /**
     *  @return true  if userName already exists in database AND the
     *                corresponding password in the database matches
     *                the password parameter
     **/
    public boolean matchPassword(String userName, String password)
    throws SignOnDAOFinderException,
    InvalidPasswordException{
        
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DAOUtils.getDBConnection(JNDINames.SIGNON_DATASOURCE);
            ps = conn.prepareStatement(MATCH_PASSWORD_QUERY);
            ps.setString(1, userName.trim());
            rs = ps.executeQuery();
            if(rs.next()) {
                if(!rs.getString(1).equals(password)) {
                    throw new InvalidPasswordException("Password does not match");
                }
            } else {
                throw new SignOnDAOFinderException("Unable to find user " +
                userName);
            }
        } catch (SQLException se) {
            throw new DAOSystemException(se);
        } finally {
            DAOUtils.closeResultSet(rs);
            DAOUtils.closeStatement(ps);
            DAOUtils.closeConnection(conn);
        }
        return(true);
    }
}
