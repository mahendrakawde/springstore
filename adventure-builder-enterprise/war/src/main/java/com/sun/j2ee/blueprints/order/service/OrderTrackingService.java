package com.sun.j2ee.blueprints.order.service;

import com.sun.j2ee.blueprints.consumerwebsite.actions.OrderDetails;
import com.sun.j2ee.blueprints.consumerwebsite.actions.OrderNotFoundException;

public interface OrderTrackingService {

	OrderDetails getOrderDetails(String orderId) throws OrderNotFoundException;
	
}
