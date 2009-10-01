package com.sun.j2ee.blueprints.order.service;

import com.sun.j2ee.blueprints.consumerwebsite.actions.InvalidPOException;
import com.sun.j2ee.blueprints.consumerwebsite.actions.ProcessingException;
import com.sun.j2ee.blueprints.consumerwebsite.actions.PurchaseOrder;

public interface PurchaseOrderService {

	String submitPurchaseOrder(PurchaseOrder po) throws ProcessingException, InvalidPOException;

}
