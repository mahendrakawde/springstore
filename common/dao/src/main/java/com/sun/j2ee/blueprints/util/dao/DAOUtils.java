/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: DAOUtils.java,v 1.3 2004/05/26 00:07:19 inder Exp $ */

package com.sun.j2ee.blueprints.util.dao;

import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import com.sun.j2ee.blueprints.servicelocator.web.ServiceLocator;

public class DAOUtils {
    
    private DAOUtils() { }
    
    private static DataSource getDataSource(String dsName)
    throws DAOSystemException {
        String dataSourceName = ServiceLocator.getInstance().getString(dsName);
        return (DataSource) ServiceLocator.getInstance().getDataSource(dataSourceName);
    }
    
    public static Connection getDBConnection(String source)
    throws DAOSystemException {
        try {
            return getDataSource(source).getConnection();
        } catch (SQLException se) {
            throw new DAOSystemException("SQL Exception while getting "
            + "DB connection : \n" + se);
        }
    }
    
    public static void closeConnection(Connection dbConnection)
    throws DAOSystemException {
        try {
            if (dbConnection != null && !dbConnection.isClosed()) {
                dbConnection.close();
            }
        } catch (SQLException se) {
            throw new DAOSystemException("SQL Exception while closing "
            + "DB connection : \n" + se);
        }
    }
    
    public static void closeResultSet(ResultSet result)
    throws DAOSystemException {
        try {
            if (result != null) {
                result.close();
                result = null;
            }
        } catch (SQLException se) {
            throw new DAOSystemException("SQL Exception while closing "
            + "Result Set : \n" + se);
        }
    }
    
    public static void closeStatement(PreparedStatement stmt)
    throws DAOSystemException {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException se) {
            throw new DAOSystemException("SQL Exception while closing "
            + "Statement : \n" + se);
        }
    }
}
