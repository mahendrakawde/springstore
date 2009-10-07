package com.sun.j2ee.blueprints.order.service;

import java.rmi.RemoteException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.j2ee.blueprints.consumerwebsite.actions.InvalidPOException;
import com.sun.j2ee.blueprints.consumerwebsite.actions.OrderDetails;
import com.sun.j2ee.blueprints.consumerwebsite.actions.OrderNotFoundException;
import com.sun.j2ee.blueprints.consumerwebsite.actions.OrderTrackingIntf;
import com.sun.j2ee.blueprints.consumerwebsite.actions.ProcessingException;
import com.sun.j2ee.blueprints.consumerwebsite.actions.PurchaseOrder;
import com.sun.j2ee.blueprints.consumerwebsite.actions.PurchaseOrderIntf;
import com.sun.j2ee.blueprints.servicelocator.web.ServiceLocator;

/**
 * JAX-RPC implementation of the {@link PurchaseOrderService} and the
 * {@link OrderTrackingService} interfaces.
 * 
 * @author Marten Deinum
 * 
 */
public class JaxRpcOrderService implements PurchaseOrderService,
		OrderTrackingService {

	private static final Logger logger = LoggerFactory
			.getLogger(JaxRpcOrderService.class);

	public String submitPurchaseOrder(PurchaseOrder po)
			throws ProcessingException, InvalidPOException {
		PurchaseOrderIntf port = (PurchaseOrderIntf) ServiceLocator
				.getInstance().getPort(
						"java:comp/env/service/OpcPurchaseOrderService",
						PurchaseOrderIntf.class);
		try {
			return port.submitPurchaseOrder(po);
		} catch (RemoteException re) {
			logger.error("RemoteException during order submission", re);
			throw new ProcessingException(re.getMessage());
		}
	}

	public OrderDetails getOrderDetails(String orderId)
			throws OrderNotFoundException {
		OrderTrackingIntf port = (OrderTrackingIntf) ServiceLocator
				.getInstance().getPort(
						"java:comp/env/service/OpcOrderTrackingService",
						OrderTrackingIntf.class);
		try {
			return port.getOrderDetails(orderId);
		} catch (RemoteException re) {
			logger.error("RemoteException during getting order details", re);
			throw new RuntimeException(re);
		}
	}

}
