/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: CatalogDAO.java,v 1.2 2004/05/26 00:06:14 inder Exp $ */

package com.sun.j2ee.blueprints.catalog.dao;

import java.util.*;
import com.sun.j2ee.blueprints.catalog.*;
import com.sun.j2ee.blueprints.util.dao.*;

/**
 * This is the interface for Catalog DAO.
 */
public interface CatalogDAO {
    
    public ArrayList getLodgings(String location, Locale locale)
            throws CatalogDAOException, DAOSystemException;
    public Lodging getLodging(String id, Locale locale)
            throws CatalogDAOException, DAOSystemException;
    public ArrayList getTransportations(String origin, String destination, Locale locale)
            throws CatalogDAOException, DAOSystemException;
    public Transportation getTransportation(String id, Locale locale)
            throws CatalogDAOException, DAOSystemException;
    public ArrayList getActivities(String location, Locale locale)
            throws CatalogDAOException, DAOSystemException;
    public Activity getActivity(String id, Locale locale)
            throws CatalogDAOException, DAOSystemException;
    public AdventurePackage getAdventurePackage(String packageId, Locale locale)
            throws CatalogDAOException, DAOSystemException;
    
}
