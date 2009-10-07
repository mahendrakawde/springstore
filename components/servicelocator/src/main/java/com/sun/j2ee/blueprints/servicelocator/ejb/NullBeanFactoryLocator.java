package com.sun.j2ee.blueprints.servicelocator.ejb;

import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.access.BeanFactoryLocator;
import org.springframework.beans.factory.access.BeanFactoryReference;

public class NullBeanFactoryLocator implements BeanFactoryLocator {

	private static final BeanFactoryReference INSTANCE = new EmptyBeanFactoryReference();
	
	public BeanFactoryReference useBeanFactory(String factoryKey) throws BeansException {
		return INSTANCE;
	}
	
	private static final class EmptyBeanFactoryReference implements BeanFactoryReference {

		public BeanFactory getFactory() {
			return null;
		}

		public void release() throws FatalBeanException {
		}
		
	}

}
