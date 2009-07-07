package com.sun.blueprints.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;

public abstract class AbstractJndiContextTests {

	protected final Log logger = LogFactory.getLog(getClass());
	
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
