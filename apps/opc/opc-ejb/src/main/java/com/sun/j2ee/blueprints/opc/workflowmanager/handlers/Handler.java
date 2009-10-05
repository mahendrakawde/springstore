package com.sun.j2ee.blueprints.opc.workflowmanager.handlers;

import javax.jms.Message;

public interface Handler {

	void handle(Message message) throws HandlerException;

}