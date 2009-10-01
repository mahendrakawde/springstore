package com.sun.j2ee.blueprints.consumerwebsite.actions;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.Test;

import com.sun.j2ee.blueprints.consumerwebsite.Cart;
import com.sun.j2ee.blueprints.test.actions.AbstractActionTests;
import com.sun.j2ee.blueprints.test.data.StubTestDataUtil;
import com.sun.j2ee.blueprints.waf.controller.web.html.HTMLAction;

public class TransportSearchHTMLActionTest extends AbstractActionTests {

	private TransportSearchHTMLAction action = new TransportSearchHTMLAction();
	
	private static final String ORIGIN = "LOC-1";
	private static final String DESTINATION = "LOC-2";
	
	@Override
	protected HTMLAction getActionUnderTest() {
		return this.action;
	}
	
	@Test
	public void search() throws Exception {
		Cart cart = getAdventureComponentManager().getCart(request.getSession());
		cart.setOrigin(ORIGIN);
		cart.setDestination(DESTINATION);
		
		ArrayList origins = new ArrayList();
		origins.add(StubTestDataUtil.createTransportation("ORIG-1"));
		ArrayList destinations = new ArrayList();
		destinations.add(StubTestDataUtil.createTransportation("DEST-1"));
		destinations.add(StubTestDataUtil.createTransportation("DEST-2"));

		expect(catalogDAO.getTransportations(ORIGIN, DESTINATION, Locale.US)).andReturn(origins);
		expect(catalogDAO.getTransportations(DESTINATION, ORIGIN, Locale.US)).andReturn(destinations);
		replay(catalogDAO);
		
		action.perform(request);
		
		assertNotNull(request.getAttribute("departure_result"));
		assertNotNull(request.getAttribute("return_result"));
		assertEquals(origins.size(), ((List) request.getAttribute("departure_result")).size());
		assertEquals(destinations.size(), ((List) request.getAttribute("return_result")).size());
		assertEquals("transportation", request.getAttribute("search_target"));
		
		verify(catalogDAO);
	}

	@Test
	public void searchBasedOnRequest() throws Exception {
		final String REQUEST_ORIGIN = "req-orig";
		request.addParameter("origin", REQUEST_ORIGIN);
		Cart cart = getAdventureComponentManager().getCart(request.getSession());
		cart.setOrigin(ORIGIN);
		cart.setDestination(DESTINATION);
		
		ArrayList origins = new ArrayList();
		origins.add(StubTestDataUtil.createTransportation("ORIG-1"));
		ArrayList destinations = new ArrayList();
		destinations.add(StubTestDataUtil.createTransportation("DEST-1"));
		destinations.add(StubTestDataUtil.createTransportation("DEST-2"));

		expect(catalogDAO.getTransportations(REQUEST_ORIGIN, DESTINATION, Locale.US)).andReturn(origins);
		expect(catalogDAO.getTransportations(DESTINATION, REQUEST_ORIGIN, Locale.US)).andReturn(destinations);
		replay(catalogDAO);
		
		action.perform(request);
		
		assertNotNull(request.getAttribute("departure_result"));
		assertNotNull(request.getAttribute("return_result"));
		assertEquals(origins.size(), ((List) request.getAttribute("departure_result")).size());
		assertEquals(destinations.size(), ((List) request.getAttribute("return_result")).size());
		assertEquals("transportation", request.getAttribute("search_target"));
		
		verify(catalogDAO);
	}


}
