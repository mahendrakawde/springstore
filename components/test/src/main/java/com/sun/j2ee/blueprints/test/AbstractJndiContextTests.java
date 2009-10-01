package com.sun.j2ee.blueprints.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

public abstract class AbstractJndiContextTests {

	protected final Log logger = LogFactory.getLog(getClass());
	
	private boolean jndiContextSetup = false;
	
	@Before
	public final void setup() throws Exception {
		if (!jndiContextSetup) {
			logger.debug("Creating JNDI Context");
			SimpleNamingContextBuilder builder = SimpleNamingContextBuilder.emptyActivatedContextBuilder();
			setupJndiContext(builder);
			jndiContextSetup=true;
		}
		onSetup();
	}
	
	protected final void resetJndiContext() {
		jndiContextSetup=false;
		SimpleNamingContextBuilder builder = SimpleNamingContextBuilder.getCurrentContextBuilder();
		if (builder != null) {
			builder.clear();
			builder.deactivate();
		}
	}
	
	protected abstract void setupJndiContext(SimpleNamingContextBuilder builder) throws Exception;
	
	protected void onSetup() throws Exception {}
	
}
