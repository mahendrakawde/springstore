package com.sun.j2ee.blueprints.opc.webservicebroker.requestor;

import java.util.HashMap;
import java.util.Map;

import com.sun.j2ee.blueprints.opc.JNDINames;

public class BrokerRequestorImpl implements BrokerRequestor {

	private Map mappings = new HashMap();
	
	public BrokerRequestorImpl() {
		super();
		initialize();
	}
	
	public void setMappings(Map mappings) {
		this.mappings=mappings;
	}

	public void sendRequest(String docType, String message) {		
		String target = (String) mappings.get(docType);
		if (target != null) {
			WSClient client = WSClientFactory.getWSClient(target);
			client.sendRequest(message);
		}
	}
	
	public void initialize() {
		mappings.put(JNDINames.ACTIVITY_ORDER, JNDINames.ACTIVITY_SUPPLIER_CLIENT);
		mappings.put(JNDINames.LODGING_ORDER, JNDINames.LODGING_SUPPLIER_CLIENT);
		mappings.put(JNDINames.AIRLINE_ORDER, JNDINames.AIRLINE_SUPPLIER_CLIENT);
	}
}
