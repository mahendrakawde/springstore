package com.sun.j2ee.blueprints.test;

import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.sun.j2ee.blueprints.test.context.jndi.JndiContextTestExecutionListener;

/**
 * Base class for JNDI based integration tests.
 * 
 * @author Marten Deinum
 * 
 * @see AbstractTransactionalJUnit4SpringContextTests
 */
@TestExecutionListeners(value={JndiContextTestExecutionListener.class})
public abstract class AbstractJndiIntegrationTests extends AbstractTransactionalJUnit4SpringContextTests {}
