package com.sun.j2ee.blueprints.consumerwebsite.actions;

public interface OrderTrackingService {
	   public OrderDetails getOrderDetails(String orderId) throws OrderNotFoundException;

}
