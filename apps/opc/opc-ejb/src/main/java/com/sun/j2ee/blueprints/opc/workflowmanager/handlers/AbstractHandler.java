package com.sun.j2ee.blueprints.opc.workflowmanager.handlers;

import javax.jms.Message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.j2ee.blueprints.servicelocator.ejb.ServiceLocator;

public abstract class AbstractHandler implements Handler {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	protected final ServiceLocator sl = new ServiceLocator();
	
	public abstract void handle(Message message) throws HandlerException;	

}
