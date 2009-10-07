package com.sun.j2ee.blueprints.servicelocator.ejb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;
import org.springframework.beans.factory.access.BeanFactoryReference;

public class NullBeanFactoryLocatorTests {

	private NullBeanFactoryLocator factory = new NullBeanFactoryLocator();
	
	@Test
	public void checkNull() {
		assertNotNull("BeanFactoryLocator returned null.", factory.useBeanFactory("foo"));		
	}

	@Test
	public void checkSame() {
		BeanFactoryReference one = factory.useBeanFactory("foo");
		BeanFactoryReference two = factory.useBeanFactory("bar");
		assertEquals("Beanfactoryreferences are not equal!", one, two);
		assertSame("Not the same BeanFactoryReference", one, two);
	}
	
}
