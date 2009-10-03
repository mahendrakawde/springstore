/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: JMSUtils.java,v 1.4 2004/07/30 23:59:20 inder Exp $ */

package com.sun.j2ee.blueprints.opc.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.jms.support.destination.JndiDestinationResolver;

import com.sun.j2ee.blueprints.opc.JNDINames;
import com.sun.j2ee.blueprints.servicelocator.ejb.ServiceLocator;

public class JMSUtils {

	private static final Logger logger = LoggerFactory
			.getLogger(JMSUtils.class);
	private static JmsTemplate jmsTemplate;

	static {
		ServiceLocator sl = new ServiceLocator();
		ConnectionFactory jmsConFactory = sl
				.getJMSConnectionFactory(JNDINames.OPC_QUEUE_CONNECTION_FACTORY);
		jmsTemplate = new JmsTemplate(jmsConFactory);
		jmsTemplate.setSessionTransacted(true);
		jmsTemplate.setSessionAcknowledgeMode(Session.SESSION_TRANSACTED);
		jmsTemplate.setDestinationResolver(new JndiDestinationResolver());

	}

	private static class StringPropertMessagePostProcessor implements
			MessagePostProcessor {

		private final Map properties = new HashMap();

		public StringPropertMessagePostProcessor(String property, String value) {
			super();
			properties.put(property, value);
		}
		
		public Message postProcessMessage(Message message) throws JMSException {
			for (Iterator iterator = properties.entrySet().iterator(); iterator
					.hasNext();) {
				Entry prop = (Entry) iterator.next();
				message.setStringProperty((String) prop.getKey(), (String) prop
						.getValue());

			}
			return message;
		}

	}

	public static boolean sendMessage(String jmsDest, String property,
			String value, String xmlDoc) {
		try {
			jmsTemplate.convertAndSend(jmsDest, xmlDoc,
					new StringPropertMessagePostProcessor(property, value));
			return true;
		} catch (JmsException exe) {
			logger.error("JMSUtil exception " + exe.getMessage(), exe);
		}
		return false;
	}

	public static boolean sendMessage(String jmsDest, String property,
			String value, Object obj) {
		try {
			jmsTemplate.convertAndSend(jmsDest, obj,
					new StringPropertMessagePostProcessor(property, value));
			return true;
		} catch (JmsException exe) {
			logger.error("JMSUtil exception " + exe.getMessage(), exe);
		}
		return false;

	}
}
