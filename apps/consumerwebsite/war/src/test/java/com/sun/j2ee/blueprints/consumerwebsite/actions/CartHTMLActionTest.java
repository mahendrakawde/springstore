package com.sun.j2ee.blueprints.consumerwebsite.actions;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.isA;
import static org.easymock.EasyMock.matches;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Locale;

import org.junit.Test;

import com.sun.j2ee.blueprints.catalog.Transportation;
import com.sun.j2ee.blueprints.consumerwebsite.AdventureKeys;
import com.sun.j2ee.blueprints.consumerwebsite.Cart;
import com.sun.j2ee.blueprints.consumerwebsite.CartBean;
import com.sun.j2ee.blueprints.test.actions.AbstractActionTests;
import com.sun.j2ee.blueprints.waf.controller.web.html.HTMLAction;

public class CartHTMLActionTest extends AbstractActionTests {

	private static final String LODGING_ID = "LODG-1";
	private static final double LODGING_PRICE = 150.0d;
	private static final double FLIGHT_PRICE = 225.0d;
	private static final String DEP_FLIGHT_ID = "DEP-1";
	private static final String RET_FLIGHT_ID = "RET-1";
	private static final int HEAD_COUNT = 2;

	private CartHTMLAction action = new CartHTMLAction();

	// Data for testing
	private com.sun.j2ee.blueprints.catalog.Lodging lodging;
	private Transportation departureFlight;
	private Transportation returnFlight;

	@Override
	protected HTMLAction getActionUnderTest() {
		return this.action;
	}

	protected Cart getCart() {
		return getAdventureComponentManager().getCart(request.getSession());
	}

	@Override
	protected void doOnSetup() {
		Cart cart = getCart();
		cart.setLodgingDays(5);
		cart.setLodgingRoomCount(1);
		cart.setLodgingId(LODGING_ID);
		cart.setHeadCount(HEAD_COUNT);
		cart.setDepartureFlight(DEP_FLIGHT_ID);
		cart.setReturnFlight(RET_FLIGHT_ID);

		lodging = new com.sun.j2ee.blueprints.catalog.Lodging(LODGING_ID,
				"CartAction test lodging", "CartAction test lodging",
				LODGING_PRICE, "LOC-1");
		departureFlight = new Transportation("DEP-1", "Test Dep Flight", "",
				"", FLIGHT_PRICE, "ORIG", "DEST", "TC", "12:00", "14:00", "E");
		returnFlight = new Transportation("RET-1", "Test Ret Flight", "", "",
				FLIGHT_PRICE, "ORIG", "DEST", "TC", "12:00", "14:00", "E");
	}

	@Test
	public void performNoActionType() throws Exception {
		expect(catalogDAO.getLodging(matches(LODGING_ID), isA(Locale.class)))
				.andReturn(lodging);
		expect(
				catalogDAO.getTransportation(matches(DEP_FLIGHT_ID),
						isA(Locale.class))).andReturn(departureFlight);
		expect(
				catalogDAO.getTransportation(matches(RET_FLIGHT_ID),
						isA(Locale.class))).andReturn(returnFlight);
		replay(catalogDAO);
		action.perform(request);
		CartBean cb = (CartBean) request.getAttribute(AdventureKeys.CART_BEAN);
		assertNotNull(cb);
		double lodging_total = 5 * 1 * LODGING_PRICE;
		double flight_total = 2 * FLIGHT_PRICE + 2 * FLIGHT_PRICE;
		assertEquals(lodging_total, cb.getLodgingTotal(), 0.0d);
		assertEquals(flight_total, cb.getTransportationTotal(), 0.0d);
		verify(catalogDAO);
	}

	@Test
	public void performPurchaseTransportation() throws Exception {
		request.addParameter("target_action", "purchase_transportation");
		request.addParameter("return_flight", (String) null);
		request.addParameter("departure_flight", RET_FLIGHT_ID);

		expect(catalogDAO.getLodging(matches(LODGING_ID), isA(Locale.class)))
				.andReturn(lodging);
		expect(
				catalogDAO.getTransportation(matches(RET_FLIGHT_ID),
						isA(Locale.class))).andReturn(returnFlight);
		replay(catalogDAO);

		action.perform(request);

		assertNull(getCart().getReturnFlight());

		CartBean cb = (CartBean) request.getAttribute(AdventureKeys.CART_BEAN);
		assertNotNull(cb);
		double lodging_total = 5 * 1 * LODGING_PRICE;
		double flight_total = 2 * FLIGHT_PRICE;
		assertEquals(lodging_total, cb.getLodgingTotal(), 0.0d);
		assertEquals(flight_total, cb.getTransportationTotal(), 0.0d);

		verify(catalogDAO);
	}

	@Test
	public void performCancelDepartureFlight() throws Exception {
		request.addParameter("target_action", "cancel_departure_flight");

		expect(catalogDAO.getLodging(matches(LODGING_ID), isA(Locale.class)))
				.andReturn(lodging);
		expect(
				catalogDAO.getTransportation(matches(RET_FLIGHT_ID),
						isA(Locale.class))).andReturn(returnFlight);
		replay(catalogDAO);

		action.perform(request);

		assertNull(getCart().getDepartureFlight());

		CartBean cb = (CartBean) request.getAttribute(AdventureKeys.CART_BEAN);
		assertNotNull(cb);
		double lodging_total = 5 * 1 * LODGING_PRICE;
		double flight_total = 2 * FLIGHT_PRICE;
		assertEquals(lodging_total, cb.getLodgingTotal(), 0.0d);
		assertEquals(flight_total, cb.getTransportationTotal(), 0.0d);

		verify(catalogDAO);

	}

	@Test
	public void performCancelReturnFlight() throws Exception {
		request.addParameter("target_action", "cancel_return_flight");

		expect(catalogDAO.getLodging(matches(LODGING_ID), isA(Locale.class)))
				.andReturn(lodging);
		expect(
				catalogDAO.getTransportation(matches(DEP_FLIGHT_ID),
						isA(Locale.class))).andReturn(departureFlight);
		replay(catalogDAO);

		action.perform(request);

		assertNull(getCart().getReturnFlight());

		CartBean cb = (CartBean) request.getAttribute(AdventureKeys.CART_BEAN);
		assertNotNull(cb);
		double lodging_total = 5 * 1 * LODGING_PRICE;
		double flight_total = 2 * FLIGHT_PRICE;
		assertEquals(lodging_total, cb.getLodgingTotal(), 0.0d);
		assertEquals(flight_total, cb.getTransportationTotal(), 0.0d);

		verify(catalogDAO);

	}
}
