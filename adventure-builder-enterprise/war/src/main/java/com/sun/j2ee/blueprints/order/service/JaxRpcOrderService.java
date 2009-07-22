package com.sun.j2ee.blueprints.order.service;

import java.rmi.RemoteException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.rpc.Service;
import javax.xml.rpc.ServiceException;

import com.sun.j2ee.blueprints.consumerwebsite.actions.InvalidPOException;
import com.sun.j2ee.blueprints.consumerwebsite.actions.OpcOrderTrackingService;
import com.sun.j2ee.blueprints.consumerwebsite.actions.OrderDetails;
import com.sun.j2ee.blueprints.consumerwebsite.actions.OrderNotFoundException;
import com.sun.j2ee.blueprints.consumerwebsite.actions.OrderTrackingIntf;
import com.sun.j2ee.blueprints.consumerwebsite.actions.ProcessingException;
import com.sun.j2ee.blueprints.consumerwebsite.actions.PurchaseOrder;
import com.sun.j2ee.blueprints.consumerwebsite.actions.PurchaseOrderIntf;

/**
 * JAX-RPC implementation of the {@link PurchaseOrderService} and the
 * {@link OrderTrackingService} interfaces.
 * 
 * @author Marten Deinum
 *
 */
public class JaxRpcOrderService implements PurchaseOrderService,
		OrderTrackingService {

	public String submitPurchaseOrder(PurchaseOrder po)
			throws ProcessingException, InvalidPOException {
		try {
			Context ic = new InitialContext();
			Service opcPurchaseOrderSvc = (Service) ic
					.lookup("java:comp/env/service/OpcPurchaseOrderService");
			PurchaseOrderIntf port = (PurchaseOrderIntf) opcPurchaseOrderSvc
					.getPort(PurchaseOrderIntf.class);
			return port.submitPurchaseOrder(po);
		} catch (NamingException ne) {
			throw new RuntimeException(ne);
		} catch (ServiceException se) {
			throw new RuntimeException(se);
		} catch (RemoteException re) {
			throw new RuntimeException(re);
		}
	}

	public OrderDetails getOrderDetails(String orderId)
			throws OrderNotFoundException {
		try {
			Context ic = new InitialContext();
			OpcOrderTrackingService opcOrderTrackingSvc = (OpcOrderTrackingService) ic
					.lookup("java:comp/env/service/OpcOrderTrackingService");
			OrderTrackingIntf port = (OrderTrackingIntf) opcOrderTrackingSvc
					.getOrderTrackingIntfPort();
			return port.getOrderDetails(orderId);
		} catch (NamingException ne) {
			throw new RuntimeException(ne);
		} catch (ServiceException se) {
			throw new RuntimeException(se);
		} catch (RemoteException re) {
			throw new RuntimeException(re);
		}
	}

}
