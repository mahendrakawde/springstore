package com.sun.j2ee.blueprints.purchaseorder.service;

import com.sun.j2ee.blueprints.consumerwebsite.actions.InvalidPOException;
import com.sun.j2ee.blueprints.consumerwebsite.actions.OrderDetails;
import com.sun.j2ee.blueprints.consumerwebsite.actions.OrderNotFoundException;
import com.sun.j2ee.blueprints.consumerwebsite.actions.ProcessingException;
import com.sun.j2ee.blueprints.consumerwebsite.actions.PurchaseOrder;
import com.sun.j2ee.blueprints.order.service.OrderTrackingService;
import com.sun.j2ee.blueprints.order.service.PurchaseOrderService;
import com.sun.j2ee.blueprints.test.MockHolder;

public class MockDelegatingOrderService implements PurchaseOrderService, OrderTrackingService {

	@Override
	public String submitPurchaseOrder(PurchaseOrder po) throws ProcessingException, InvalidPOException {
		PurchaseOrderService delegate = MockHolder.getMock(PurchaseOrderService.class);
		return delegate.submitPurchaseOrder(po);
	}

	@Override
	public OrderDetails getOrderDetails(String orderId) throws OrderNotFoundException {
		OrderTrackingService delegate = MockHolder.getMock(OrderTrackingService.class);
		return delegate.getOrderDetails(orderId);
	}

}
