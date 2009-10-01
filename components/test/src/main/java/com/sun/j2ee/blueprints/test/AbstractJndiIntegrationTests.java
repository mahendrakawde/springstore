package com.sun.j2ee.blueprints.test;

import org.junit.Before;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/**
 * Base class for JNDI based integration tests.
 * 
 * @author Marten Deinum
 *
 * @see AbstractTransactionalJUnit4SpringContextTests
 */
public abstract class AbstractJndiIntegrationTests extends AbstractTransactionalJUnit4SpringContextTests {

	private boolean jndiContextSetup = false;
	
	@Before
	public final void setup() throws Exception {
		if (!jndiContextSetup) {
			logger.debug("Creating JNDI Context");
			setupJndiContext();
			jndiContextSetup=true;
		}
		onSetup();
	}
	
	protected void resetJndiContext() {
		jndiContextSetup=false;
	}
	
	protected abstract void setupJndiContext() throws Exception;
	
	protected void onSetup() throws Exception {}
	
}
