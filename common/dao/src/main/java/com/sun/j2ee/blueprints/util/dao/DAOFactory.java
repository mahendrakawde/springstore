/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: DAOFactory.java,v 1.2 2004/05/26 00:07:19 inder Exp $ */

package com.sun.j2ee.blueprints.util.dao;

import javax.naming.NamingException;
import javax.naming.InitialContext;

public class DAOFactory {

    /**
     * This method instantiates the DAO class that is used in this
     * applications deployment environment to access the data. 
     * @param daoEnvEntry The env-entry in the deployment descriptor
     * that contains the name of the class corresponding to
     * which DAO to instantiate.
     *
     * @throws DAOSystemException if it has system error looking up 
     *         the DAO class name in the env-entry.
     */
    public static Object getDAO(String daoEnvEntry) throws DAOSystemException {
        try {
            InitialContext ic = new InitialContext();
            String className = (String) ic.lookup(daoEnvEntry);
            return Class.forName(className).newInstance();
        } catch (NamingException ne) {
            throw new DAOSystemException("DAOFactory.getDAO(" + daoEnvEntry +"):  NamingException while getting DAO type : \n" + ne.getMessage());
        } catch (Exception se) {
            throw new DAOSystemException("DAOFactory.getDAO(" + daoEnvEntry +"):  Exception while getting DAO type : \n" + se.getMessage());
        }
    }
}
