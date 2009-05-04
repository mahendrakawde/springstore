/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: CatalogFacade.java,v 1.2 2004/05/26 00:06:13 inder Exp $ */

package com.sun.j2ee.blueprints.catalog;

import java.util.Locale;
import java.util.ArrayList;
import com.sun.j2ee.blueprints.catalog.dao.CatalogDAO;
import com.sun.j2ee.blueprints.catalog.dao.JNDINames;
import com.sun.j2ee.blueprints.catalog.dao.CatalogDAOException;
import com.sun.j2ee.blueprints.util.dao.DAOFactory;
import com.sun.j2ee.blueprints.util.dao.DAOSystemException;

/**
 * This class is used by clients wanting to access the catalog data
 **/
public class CatalogFacade {
    
    private CatalogDAO catalogDao = null;
    
    public CatalogFacade() {
        catalogDao = (CatalogDAO) DAOFactory.getDAO(JNDINames.CATALOG_DAO_CLASS);
    }
    
    public ArrayList getLodgings(String location, Locale locale)
            throws CatalogException {
        try {
            return catalogDao.getLodgings(location,locale);
        } catch (CatalogDAOException cdos) {
            throw new CatalogException("Catalog Exception", cdos);
        }
    }
    
     public Lodging getLodging(String id, Locale locale)
            throws CatalogException {
        try {
            return catalogDao.getLodging(id,locale);
        } catch (CatalogDAOException cdos) {
            throw new CatalogException("Catalog Exception", cdos);
        }
    }
    
    
    public AdventurePackage getAdventurePackage(String packageId,  Locale locale)
            throws CatalogException {
        try {             
            return catalogDao.getAdventurePackage(packageId,locale);
        } catch (CatalogDAOException cdos) {            
            throw new CatalogException("Catalog Exception", cdos);
        }
    }
    
    public ArrayList getTransportations(String origin, String destination,  Locale locale)
            throws CatalogException {
        try {             
            return catalogDao.getTransportations(origin, destination, locale);
        } catch (CatalogDAOException cdos) {            
            throw new CatalogException("Catalog Exception", cdos);
        }
    }
    
    public Transportation getTransportation(String id,  Locale locale)
            throws CatalogException {
        try {             
            return catalogDao.getTransportation(id,locale);
        } catch (CatalogDAOException cdos) {            
            throw new CatalogException("Catalog Exception", cdos);
        }
    }
    
    public ArrayList getActivities(String location, Locale locale)
            throws CatalogException {
        try {
            return catalogDao.getActivities(location,locale);
        } catch (CatalogDAOException cdos) {
            throw new CatalogException("Catalog Exception", cdos);
        }
    }
    
    public Activity getActivity(String id, Locale locale)
            throws CatalogException {
        try {
            return catalogDao.getActivity(id,locale);
        } catch (CatalogDAOException cdos) {
            throw new CatalogException("Catalog Exception", cdos);
        }
    }
    
}
