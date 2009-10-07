package com.sun.j2ee.blueprints.consumerwebsite.actions;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import com.sun.j2ee.blueprints.test.actions.AbstractActionTests;
import com.sun.j2ee.blueprints.waf.controller.web.html.HTMLAction;

public class OrderTrackingHTMLActionTest extends AbstractActionTests {

	private OrderTrackingHTMLAction action = new OrderTrackingHTMLAction();

	private static final String ORDER_ID = "some-id-12";

	@Override
	protected HTMLAction getActionUnderTest() {
		return this.action;
	}

	@Override
	protected void populateRequest(MockHttpServletRequest request2) {
		request2.addParameter("orderId", ORDER_ID);
	}

	@Test(expected = com.sun.j2ee.blueprints.consumerwebsite.exceptions.OrderNotFoundException.class)
	public void performNoOrderFoundException() throws Exception {
		expect(orderTrackingService.getOrderDetails(ORDER_ID)).andThrow(
				new OrderNotFoundException("Test Exception"));
		replay(orderTrackingService);
		action.perform(request);
		verify(orderTrackingService);
	}

	@Test(expected = com.sun.j2ee.blueprints.consumerwebsite.exceptions.OrderNotFoundException.class)
	public void performOrderNotFoundExceptionConversion() throws Exception {
		expect(orderTrackingService.getOrderDetails(ORDER_ID)).andThrow(
				new RuntimeException("Test Remote Exception"));
		replay(orderTrackingService);
		action.perform(request);
		verify(orderTrackingService);
	}

	@Test(expected = com.sun.j2ee.blueprints.consumerwebsite.exceptions.OrderNotFoundException.class)
	public void performOrderNotFoundExceptionNullPO() throws Exception {
		expect(orderTrackingService.getOrderDetails(ORDER_ID)).andReturn(
				new OrderDetails());
		replay(orderTrackingService);
		action.perform(request);
		verify(orderTrackingService);
	}

	@Test
	@Ignore("Find a solution for JAX-RPC stubs")
	public void perform() throws Exception {
		PurchaseOrder po = new PurchaseOrder();
		po.setPoId(ORDER_ID);
		OrderDetails orderDetails = new OrderDetails(po, "COMPLETED");
		expect(orderTrackingService.getOrderDetails(ORDER_ID)).andReturn(
				orderDetails);
		replay(orderTrackingService);
		action.perform(request);

		assertEquals(ORDER_ID, request.getAttribute("orderTrackingId"));
		assertEquals(orderDetails, request.getAttribute("orderDetails"));
		verify(orderTrackingService);

	}

}
