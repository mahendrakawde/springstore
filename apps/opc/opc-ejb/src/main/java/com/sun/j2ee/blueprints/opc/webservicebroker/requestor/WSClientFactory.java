/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: WSClientFactory.java,v 1.3 2004/05/26 00:07:02 inder Exp $ */

package com.sun.j2ee.blueprints.opc.webservicebroker.requestor;

import javax.naming.InitialContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Factory for creating {@link WSClient} instances.
 * 
 * @author Marten Deinum
 *
 * @See WSClient
 */
public class WSClientFactory {

	private static final Logger logger = LoggerFactory.getLogger(WSClientFactory.class);

	public static WSClient getWSClient(String providerName) {
		WSClient client = null;
		try {
			InitialContext ic = new InitialContext();
			String className = (String) ic.lookup(providerName);
			client = (WSClient) Class.forName(className).newInstance();
		} catch (Exception se) {
			logger.error(se.getMessage(), se);
		}
		return client;
	}
}
