package com.sun.j2ee.blueprints.consumerwebsite.handlers;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

public class CartFlowHandlerTest {

	private CartFlowHandler handler = new CartFlowHandler();

	private MockHttpServletRequest request = new MockHttpServletRequest();

	@After
	public void clean() {
		request.clearAttributes();
		request.getSession().invalidate();
	}

	@Test
	public void processFlowBogusAction() throws Exception {
		request.addParameter("target_action", "bogus-action");
		assertEquals("CART", handler.processFlow(request));
	}

	@Test
	public void processNullAction() throws Exception {
		assertEquals("CART", handler.processFlow(request));
	}

	@Test
	public void processSelectPackage() throws Exception {
		request.addParameter("target_action", "select_package");
		assertEquals("PACKAGE_OPTIONS", handler.processFlow(request));
	}

	@Test
	public void processSetPackageOptions() throws Exception {
		request.addParameter("target_action", "set_package_options");
		assertEquals("SELECT_TRANSPORT", handler.processFlow(request));
	}

	@Test
	public void processUpdatePackageOptions() throws Exception {
		request.addParameter("target_action", "update_package_options");
		assertEquals("CART", handler.processFlow(request));
	}

	@Test
	public void processUpdateActivities() throws Exception {
		request.addParameter("target_action", "update_activities");
		assertEquals("CART-ACTIVITIES", handler.processFlow(request));
	}

	@Test
	public void processPurchaseActivities() throws Exception {
		request.addParameter("target_action", "purchase_activities");
		assertEquals("CART-ACTIVITIES", handler.processFlow(request));
	}

	@Test
	public void processPurchaseActivity() throws Exception {
		request.addParameter("target_action", "purchase_activity");
		assertEquals("CART-ACTIVITIES", handler.processFlow(request));
	}

	@Test
	public void processUpdateLodgingRoomCount() throws Exception {
		request.addParameter("target_action", "update_lodging_room_count");
		assertEquals("CART-LODGING", handler.processFlow(request));
	}

	@Test
	public void processPurchaseLodging() throws Exception {
		request.addParameter("target_action", "purchase_lodging");
		assertEquals("CART-LODGING", handler.processFlow(request));
	}

	@Test
	public void processCancelReturnFlight() throws Exception {
		request.addParameter("target_action", "cancel_return_flight");
		assertEquals("CART_TRANSPORT", handler.processFlow(request));
	}

	@Test
	public void processNoTransport() throws Exception {
		request.addParameter("target_action", "no_transportation");
		assertEquals("CART", handler.processFlow(request));
	}

	@Test
	public void processCancelDepartureFlight() throws Exception {
		request.addParameter("target_action", "cancel_departure_flight");
		assertEquals("CART_TRANSPORT", handler.processFlow(request));
	}

	@Test
	public void processPurchaseTransportation() throws Exception {
		request.addParameter("target_action", "purchase_transportation");
		assertEquals("CART_TRANSPORT", handler.processFlow(request));
	}

	@Test
	public void processCancel() throws Exception {
		request.addParameter("target_action", "cancel");
		assertEquals("CANCEL", handler.processFlow(request));
	}

}
