package com.sun.j2ee.blueprints.opc.utils;

import org.apache.activemq.broker.BrokerService;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.sun.j2ee.blueprints.test.AbstractJndiContextTests;

public abstract class AbstractJmsJndiContextTests extends AbstractJndiContextTests {

	protected static BrokerService broker;
	
	@BeforeClass
	public static void setupJmsBroker() throws Exception {
		broker = new BrokerService();
		broker.setPersistent(false);
		broker.setUseJmx(false);
		broker.addConnector("vm://localhost?async=false");
		
		broker.start();
	}
	
	@AfterClass
	public static void cleanUpJmsBroke() throws Exception {
		broker.stop();
	}
	
}
