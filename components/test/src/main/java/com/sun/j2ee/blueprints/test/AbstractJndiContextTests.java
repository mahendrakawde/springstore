package com.sun.j2ee.blueprints.test;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sun.j2ee.blueprints.test.context.jndi.JndiContextTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(value={JndiContextTestExecutionListener.class})
public abstract class AbstractJndiContextTests {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

}
