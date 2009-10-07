package com.sun.j2ee.blueprints.opc.webservicebroker.requestor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.j2ee.blueprints.servicelocator.ejb.ServiceLocator;

/**
 * Base class which can be extended to create {@link WSClient}. This class 
 * already provides logging and exception handling. Subclasses only need to
 * implement the {@link #sendRequestInternal(String)} method.
 * 
 * @author Marten Deinum
 *
 * @see WSClient
 */
public abstract class AbstractWSClient implements WSClient {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	protected final ServiceLocator sl = new ServiceLocator();
	
	public final String sendRequest(String xmlDoc) {
		if (logger.isDebugEnabled()) {
			logger.debug("Received message: {}", xmlDoc);
		}
		String result = null;
		try {
			result = sendRequestInternal(xmlDoc);
		} catch (Exception e) {
			logger.error("Exception while processing message", e);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Received response: {}", result);
		}
		return result;
	}

	protected abstract String sendRequestInternal(String xmlDoc) throws Exception;
	
}
