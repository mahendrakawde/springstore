package com.sun.j2ee.blueprints.catalog.dao;

import java.util.ArrayList;
import java.util.Locale;

import com.sun.blueprints.test.MockHolder;
import com.sun.j2ee.blueprints.catalog.Activity;
import com.sun.j2ee.blueprints.catalog.AdventurePackage;
import com.sun.j2ee.blueprints.catalog.Lodging;
import com.sun.j2ee.blueprints.catalog.Transportation;
import com.sun.j2ee.blueprints.util.dao.DAOSystemException;

/**
 * Stub {@link CatalogDAO} which delegates to a Mock registered in the
 * {@link MockHolder}.
 *  
 * @author Marten Deinum
 *
 */
public class MockDelegatingCatalogDao implements CatalogDAO {

	@Override
	public ArrayList getActivities(String location, Locale locale) throws CatalogDAOException, DAOSystemException {
		CatalogDAO delegate = MockHolder.getMock(CatalogDAO.class);
		return delegate.getActivities(location, locale);
	}

	@Override
	public Activity getActivity(String id, Locale locale)
			throws CatalogDAOException, DAOSystemException {
		CatalogDAO delegate = MockHolder.getMock(CatalogDAO.class);
		return delegate.getActivity(id, locale);
	}

	@Override
	public AdventurePackage getAdventurePackage(String packageId, Locale locale)
			throws CatalogDAOException, DAOSystemException {
		CatalogDAO delegate = MockHolder.getMock(CatalogDAO.class);
		return delegate.getAdventurePackage(packageId, locale);
	}

	@Override
	public Lodging getLodging(String id, Locale locale)
			throws CatalogDAOException, DAOSystemException {
		CatalogDAO delegate = MockHolder.getMock(CatalogDAO.class);
		return delegate.getLodging(id, locale);
	}

	@Override
	public ArrayList getLodgings(String location, Locale locale)
			throws CatalogDAOException, DAOSystemException {
		CatalogDAO delegate = MockHolder.getMock(CatalogDAO.class);
		return delegate.getLodgings(location, locale);
	}

	@Override
	public Transportation getTransportation(String id, Locale locale)
			throws CatalogDAOException, DAOSystemException {
		CatalogDAO delegate = MockHolder.getMock(CatalogDAO.class);
		return delegate.getTransportation(id, locale);
	}

	@Override
	public ArrayList getTransportations(String origin, String destination,
			Locale locale) throws CatalogDAOException, DAOSystemException {
		CatalogDAO delegate = MockHolder.getMock(CatalogDAO.class);
		return delegate.getTransportations(origin, destination, locale);
	}

}
