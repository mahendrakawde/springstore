package com.sun.j2ee.blueprints.catalog;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Locale;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Test;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

import com.sun.blueprints.test.AbstractJndiContextTests;
import com.sun.blueprints.test.MockHolder;
import com.sun.blueprints.test.data.StubTestDataUtil;
import com.sun.j2ee.blueprints.catalog.dao.CatalogDAO;
import com.sun.j2ee.blueprints.catalog.dao.CatalogDAOException;
import com.sun.j2ee.blueprints.catalog.dao.JNDINames;

public class CatalogFacadeTest extends AbstractJndiContextTests {

	private CatalogDAO dao;
	private CatalogFacade facade;
	
	private static final Locale LOCALE = Locale.US;

	@Override
	protected void setupJndiContext() throws Exception {
		//Setup JNDI 
		SimpleNamingContextBuilder builder = SimpleNamingContextBuilder.emptyActivatedContextBuilder();
		builder.bind(JNDINames.CATALOG_DAO_CLASS, "com.sun.j2ee.blueprints.catalog.dao.MockDelegatingCatalogDao");
	}

	@Override
	protected void onSetup() throws Exception {
		dao = EasyMock.createMock(CatalogDAO.class);
		MockHolder.setMock(CatalogDAO.class, dao);
		facade = new CatalogFacade();
	}
	
	@After
	public void after() {
		MockHolder.clear();
		EasyMock.reset(dao);
	}
	
	@Test(expected=CatalogException.class)
	public void getLodgingsCatalogDAOException() throws Exception {
		String location = "test";		
		expect(dao.getLodgings(location, LOCALE)).andThrow(new CatalogDAOException());
		replay(dao);
		facade.getLodgings(location, LOCALE);
		verify(dao);
	}

	@Test
	public void getLodgings() throws Exception {
		String location = "Test location";
		ArrayList lodgings = new ArrayList();
		lodgings.add(StubTestDataUtil.createLodging("test1"));
		lodgings.add(StubTestDataUtil.createLodging("test2"));
		expect(dao.getLodgings(location, LOCALE)).andReturn(lodgings);
		replay(dao);
		assertEquals(lodgings, facade.getLodgings(location, LOCALE));
		verify(dao);
	}
	
	@Test(expected=CatalogException.class)
	public void getLodgingCatalogDAOException() throws Exception {
		String id = "test";		
		expect(dao.getLodging(id, LOCALE)).andThrow(new CatalogDAOException());
		replay(dao);
		facade.getLodging(id, LOCALE);
		verify(dao);
	}

	@Test
	public void getLodging() throws Exception {
		String lodgingId = "test";		
		Lodging lodging = StubTestDataUtil.createLodging(lodgingId);
		expect(dao.getLodging(lodgingId, LOCALE)).andReturn(lodging);
		replay(dao);
		assertEquals(lodging, facade.getLodging(lodgingId, LOCALE));
		verify(dao);
	}
	
	
	@Test(expected=CatalogException.class)
	public void getAdventurePackageCatalogDAOException() throws Exception {
		String packageId = "test";		
		expect(dao.getAdventurePackage(packageId, LOCALE)).andThrow(new CatalogDAOException());
		replay(dao);
		facade.getAdventurePackage(packageId, LOCALE);
		verify(dao);
	}

	@Test
	public void getAdventurePackage() throws Exception {
		String packageId = "test";	
		String lodgingId = "tst-lodging";
		ArrayList activities = new ArrayList();
		activities.add(StubTestDataUtil.createActivity("ACT-1"));
		AdventurePackage adventurePackage = StubTestDataUtil.createAdventurePackage(packageId, lodgingId, activities);
		expect(dao.getAdventurePackage(packageId, LOCALE)).andReturn(adventurePackage);
		replay(dao);
		assertEquals(adventurePackage, facade.getAdventurePackage(packageId, LOCALE));
		verify(dao);
	}
	
	
	@Test(expected=CatalogException.class)
	public void getTransportationsCatalogDAOException() throws Exception {
		String origin = "test-origin";
		String dest = "test-dest";
		expect(dao.getTransportations(origin, dest, LOCALE)).andThrow(new CatalogDAOException());
		replay(dao);
		facade.getTransportations(origin, dest, LOCALE);
		verify(dao);
	}

	@Test
	public void getTransportations() throws Exception {
		String origin = "test-origin";
		String dest = "test-dest";
		ArrayList transportations = new ArrayList();
		transportations.add(StubTestDataUtil.createTransportation("TRANS-1"));
		transportations.add(StubTestDataUtil.createTransportation("TRANS-2"));
		transportations.add(StubTestDataUtil.createTransportation("TRANS-3"));
		expect(dao.getTransportations(origin, dest, LOCALE)).andReturn(transportations);
		replay(dao);
		assertEquals(transportations, facade.getTransportations(origin, dest, LOCALE));
		verify(dao);
	}
	
	
	@Test(expected=CatalogException.class)
	public void getTransportationCatalogDAOException() throws Exception {
		String id = "transportId";
		expect(dao.getTransportation(id, LOCALE)).andThrow(new CatalogDAOException());
		replay(dao);
		facade.getTransportation(id, LOCALE);
		verify(dao);
	}

	@Test
	public void getTransportation() throws Exception {
		String id = "transportId";
		Transportation transportation = StubTestDataUtil.createTransportation(id);
		expect(dao.getTransportation(id, LOCALE)).andReturn(transportation);
		replay(dao);
		assertEquals(transportation, facade.getTransportation(id, LOCALE));
		verify(dao);
	}
	
	
	@Test(expected=CatalogException.class)
	public void getActivitiesCatalogDAOException() throws Exception {
		String location = "test-location";
		expect(dao.getActivities(location, LOCALE)).andThrow(new CatalogDAOException());
		replay(dao);
		facade.getActivities(location, LOCALE);
		verify(dao);
	}

	@Test
	public void getActivities() throws Exception {
		String location = "test-location";
		ArrayList activities = new ArrayList();
		activities.add(StubTestDataUtil.createActivity("TACT-1"));
		activities.add(StubTestDataUtil.createActivity("TACT-2"));
		activities.add(StubTestDataUtil.createActivity("TACT-3"));
		expect(dao.getActivities(location, LOCALE)).andReturn(activities);
		replay(dao);
		assertEquals(activities, facade.getActivities(location, LOCALE));
		verify(dao);
	}
	
	
	@Test(expected=CatalogException.class)
	public void getActivityCatalogDAOException() throws Exception {
		String id = "activityId";
		expect(dao.getActivity(id, LOCALE)).andThrow(new CatalogDAOException());
		replay(dao);
		facade.getActivity(id, LOCALE);
		verify(dao);
	}

	@Test
	public void getActivity() throws Exception {
		String id = "activityId";
		Activity activity = StubTestDataUtil.createActivity(id);
		expect(dao.getActivity(id, LOCALE)).andReturn(activity);
		replay(dao);
		assertEquals(activity, facade.getActivity(id, LOCALE));
		verify(dao);
	}
	
	
}
