package com.sun.j2ee.blueprints.catalog.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

import com.sun.j2ee.blueprints.catalog.Activity;
import com.sun.j2ee.blueprints.catalog.AdventurePackage;
import com.sun.j2ee.blueprints.catalog.Lodging;
import com.sun.j2ee.blueprints.catalog.Transportation;
import com.sun.j2ee.blueprints.test.JndiNames;
import com.sun.j2ee.blueprints.test.annotation.JndiConfig;
import com.sun.j2ee.blueprints.test.jdbc.AbstractDaoTests;

public class PointbaseCatalogDAOTest extends AbstractDaoTests {

	private PointbaseCatalogDAO dao;

	@Before
	public void setup() throws Exception {
		dao = new PointbaseCatalogDAO();
	}

	@JndiConfig
	public void setupJndiContext(SimpleNamingContextBuilder builder) throws Exception {
		builder.bind(JNDINames.CATALOG_DATASOURCE, JndiNames.CATALOGDB);
		builder.bind(JndiNames.CATALOGDB, getDataSource());
	}
	
	@Test
	public void testGetLodgings() {
		List lodgings = dao.getLodgings("Antarctica", Locale.US);
		assertNotNull(lodgings);
		assertEquals(2, lodgings.size());
	}

	@Test(expected=CatalogDAOException.class)
	public void testGetLodgingsNoResults() {
		dao.getLodgings("Sahara Dessert", Locale.US);
	}
	
	@Test
	public void testGetLodging() {
		Lodging lodging = dao.getLodging("LODG-1", Locale.US);
		assertNotNull(lodging);
		assertEquals("LODG-1", lodging.getLodgingId().trim());
	}

	@Test(expected=CatalogDAOException.class)
	public void testGetLodgingNotExisting() {
		dao.getLodging("LODG-666", Locale.US);
	}
	
	
	@Test
	public void testGetAdventurePackage() {
		AdventurePackage package1 = dao.getAdventurePackage("PACK-12", Locale.US);
		assertNotNull(package1);
		assertEquals("PACK-12", package1.getPackageId().trim());
	}

	@Test(expected=CatalogDAOException.class)
	public void testGetAdventurePackageNotExisting() {
		dao.getAdventurePackage("PACK-66", Locale.US);
	}
	
	
	@Test
	public void testGetTransportations() {
		List transportations = dao.getTransportations("Texas", "Detroit", Locale.US);
		assertNotNull(transportations);
		assertEquals(1, transportations.size());
	}

	@Test(expected=CatalogDAOException.class)
	public void testGetTransportationsNoResults() {
		dao.getTransportations("j2ee", "spring", Locale.US);
	}
	
	
	@Test(expected=NullPointerException.class)
	public void testGetTransportationsNullOrigin() {
		dao.getTransportations(null, "Detroit", Locale.US);
	}

	@Test(expected=NullPointerException.class)
	public void testGetTransportationsNullDestination() {
		dao.getTransportations("Texas", null, Locale.US);
	}

	@Test(expected=NullPointerException.class)
	public void testGetTransportationsNullLocale() {
		dao.getTransportations("Texas", "Detroit", null);
	}

	
	@Test
	public void testGetTransportation() {
		Transportation transportation = dao.getTransportation("TRPN-2", Locale.US);
		assertNotNull(transportation);
		assertEquals("TRPN-2", transportation.getTransportationId().trim());
	}

	@Test(expected=CatalogDAOException.class)
	public void testGetTransportationNotExisting() {
		dao.getTransportation("TRPN-666", Locale.US);
	}
	
	
	@Test(expected=NullPointerException.class)
	public void testGetTransportationNullLocale() {
		dao.getTransportation("TRPN-2", null);
	}

	@Test(expected=NullPointerException.class)
	public void testGetTransportationNullId() {
		dao.getTransportation(null, Locale.US);
	}
	
	@Test
	public void testGetActivities() {
		List activities = dao.getActivities("Antarctica", Locale.US);
		assertNotNull(activities);
		assertEquals(5, activities.size());
	}

	@Test(expected=CatalogDAOException.class)
	public void testGetActivitiesNoResults() {
		dao.getActivities("Sahara Dessert", Locale.US);
	}

	
	@Test
	public void testGetActivity() {
		Activity activity = dao.getActivity("ACTY-10", Locale.US);
		assertNotNull(activity);
		assertEquals("ACTY-10", activity.getActivityId().trim());
	}

	@Test(expected=CatalogDAOException.class)
	public void testGetActivityNotExisting() {
		dao.getActivity("ACTY-666", Locale.US);
	}
	
	
	@Test(expected=NullPointerException.class)
	public void testGetActivityNullLocale() {
		dao.getActivity("ACTY-12", null);
	}

	@Test(expected=NullPointerException.class)
	public void testGetActivityNullId() {
		dao.getActivity(null, Locale.US);
	}

}
