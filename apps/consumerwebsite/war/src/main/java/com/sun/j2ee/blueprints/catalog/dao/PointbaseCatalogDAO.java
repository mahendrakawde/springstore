/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: PointbaseCatalogDAO.java,v 1.2 2004/05/26 00:06:15 inder Exp $ */

package com.sun.j2ee.blueprints.catalog.dao;

import java.util.List;
import java.util.Locale;

import javax.sql.DataSource;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.sun.j2ee.blueprints.catalog.Activity;
import com.sun.j2ee.blueprints.catalog.AdventurePackage;
import com.sun.j2ee.blueprints.catalog.Lodging;
import com.sun.j2ee.blueprints.catalog.Transportation;
import com.sun.j2ee.blueprints.servicelocator.web.ServiceLocator;
import com.sun.j2ee.blueprints.util.dao.DAOSystemException;


/**
 * This class implements CatalogDAO for Pointbase database.
 * This class encapsulates all the JDBC calls .
 * The logic of inserting/fetching/updating/deleting  the data in
 * relational database tables is implemented here.
 */
public class PointbaseCatalogDAO extends JdbcDaoSupport implements CatalogDAO {
    
    private final static String SELECT_LODGINGS_QUERY_STR = "SELECT "+
    "lodgingid, name, description, price, location" +"  FROM "+ DatabaseNames.LODGING_TABLE +
    " WHERE location = ? AND locale = ?";
    private final static String SELECT_LODGING_QUERY_STR = "SELECT "+
    "lodgingid, name, description, price, location" +"  FROM "+ DatabaseNames.LODGING_TABLE +
    " WHERE lodgingid = ? AND locale = ?";
    private final static String SELECT_TRANSPORTATIONS_QUERY_STR = "SELECT "+
    "transportationid, name, description, imageuri, price, origin, destination,carrier,departuretime, arrivaltime, class"+" FROM " + DatabaseNames.TRANSPORTATION_TABLE +
    " WHERE origin = ? AND destination = ? AND locale = ?";
    private final static String SELECT_TRANSPORTATION_QUERY_STR = "SELECT "+
    "transportationid, name, description, imageuri, price, origin, destination,carrier,departuretime, arrivaltime, class"+" FROM " + DatabaseNames.TRANSPORTATION_TABLE +
    " WHERE transportationid = ? AND locale = ?";
    private final static String SELECT_ACTIVITIES_QUERY_STR = "SELECT "+
    "activityid, name, description, price, location "+" FROM " + DatabaseNames.ACTIVITY_TABLE +
    " WHERE location = ? AND locale = ?";
    private final static String SELECT_ACTIVITY_QUERY_STR = "SELECT "+
    "activityid, name, description, price, location "+" FROM " + DatabaseNames.ACTIVITY_TABLE +
    " WHERE activityid = ? AND locale = ?";
    private final static String SELECT_ADVENTURE_PACKAGE_QUERY_STR = "SELECT "+
    "name, description, price, location, lodgingid  "+" FROM " + DatabaseNames.PACKAGE_TABLE +
    " WHERE packageid = ? AND locale = ?";
    private final static String SELECT_ACTIVITYLIST_QUERY_STR = "SELECT "+
    "activityid"+" FROM " + DatabaseNames.ACTIVITYLIST_TABLE +
    " WHERE packageid = ? AND locale = ?";
    
    private JdbcTemplate catalogTemplate;
    
    public PointbaseCatalogDAO() {
    	super();
        final String dataSourceName = ServiceLocator.getInstance().getString(JNDINames.CATALOG_DATASOURCE);
        DataSource ds = ServiceLocator.getInstance().getDataSource(dataSourceName);
        catalogTemplate = new JdbcTemplate(ds);    
    }
    
    public List getLodgings(final String location, final Locale locale)
    throws CatalogDAOException, DAOSystemException {

    	Object[] params = new Object[] {location.trim(), locale.toString().trim()};
    	List lodgings = catalogTemplate.query(SELECT_LODGINGS_QUERY_STR, params, new LodgingRowMapper());
    	if (lodgings.isEmpty()) {
    		throw new CatalogDAOException("No data found for  " + location +" , "+locale.toString());
    	}
    	return lodgings;
    }
    
    
    
    public Lodging getLodging(String id, Locale locale) throws CatalogDAOException, DAOSystemException {
    	Object[] params = new Object[] {id.trim(), locale.toString().trim()};
    	try {
	    	Lodging lodging = (Lodging) catalogTemplate.queryForObject(SELECT_LODGING_QUERY_STR, params, new LodgingRowMapper());
	    	return lodging;
    	} catch (IncorrectResultSizeDataAccessException e) {
    		throw new CatalogDAOException("No data found for  " + id +" , "+locale.toString());
    	}

    }
    
    public AdventurePackage getAdventurePackage(String packageId, Locale locale)
    throws CatalogDAOException, DAOSystemException {
    	try {
	    	final Object[] params = new Object[] { packageId.trim(), locale.toString().trim()};
	    	List activities = catalogTemplate.queryForList(SELECT_ACTIVITYLIST_QUERY_STR, params, java.lang.String.class);
	    	AdventurePackage adventurePackage = (AdventurePackage) catalogTemplate.queryForObject(SELECT_ADVENTURE_PACKAGE_QUERY_STR, params, new AdventurePackageRowMapper(packageId, activities));
	    	return adventurePackage;
    	} catch (IncorrectResultSizeDataAccessException e) {
    		throw new CatalogDAOException("No data found for " +packageId+ " , " + locale.toString());
    	}
    }
    
    public List getTransportations(String origin, String destination, Locale locale)
    throws CatalogDAOException, DAOSystemException {
    	Object[] params = new Object[] {origin.trim(), destination.trim(), locale.toString().trim() };
    	List transportations = catalogTemplate.query(SELECT_TRANSPORTATIONS_QUERY_STR, params, new TransportationRowMapper());
    	if (transportations.isEmpty()) {
            throw new CatalogDAOException("No data found for  " + origin +" , "+ destination +" , "+locale.toString());    		
    	}
    	return transportations;

    }
    
    public Transportation getTransportation(String id, Locale locale) throws CatalogDAOException, DAOSystemException {
    	try {
	    	Object[] params = new Object[] {id.trim(), locale.toString().trim()};
	    	Transportation transportation = (Transportation) catalogTemplate.queryForObject(SELECT_TRANSPORTATION_QUERY_STR, params, new TransportationRowMapper());
	    	return transportation;
    	} catch (IncorrectResultSizeDataAccessException e) {
            throw new CatalogDAOException("No data found for  " + id +" , "+locale.toString());
    	} 
    }
    
    
    public List getActivities(String location, Locale locale) throws CatalogDAOException, DAOSystemException {
    	Object[] params = new Object[] {location.trim(), locale.toString().trim()};
    	List activities = catalogTemplate.query(SELECT_ACTIVITIES_QUERY_STR, params, new ActivityRowMapper());
    	if (activities.isEmpty()) {
            throw new CatalogDAOException(
                    "No data dound for  " + location +" , "+locale.toString());   		
    	}
    	return activities;

    }
    
    
    public Activity getActivity(String id, Locale locale) throws CatalogDAOException, DAOSystemException {
    	try {
	    	Object[] params = new Object[] {id.trim(), locale.toString().trim()};
	    	Activity activity = (Activity) catalogTemplate.queryForObject(SELECT_ACTIVITY_QUERY_STR, params, new ActivityRowMapper());
	    	return activity;
    	} catch (IncorrectResultSizeDataAccessException e) {
            throw new CatalogDAOException(
                    "No data dound for  " + id +" , "+locale.toString());    		
    	}
    }
    
}
