package com.sun.j2ee.blueprints.opc.utils;

import static org.junit.Assert.*;

import java.util.Date;

import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.connection.TransactionAwareConnectionFactoryProxy;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.destination.JndiDestinationResolver;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.sun.j2ee.blueprints.opc.JNDINames;
import com.sun.j2ee.blueprints.test.annotation.JndiConfig;

public class JMSUtilsTest extends AbstractJmsJndiContextTests {

	private static String TEXT_MSG = "This is a text message.";
	private static String value = "some value";

	private ConnectionFactory cf = new TransactionAwareConnectionFactoryProxy(new SingleConnectionFactory(new ActiveMQConnectionFactory("vm://localhost?async=false")));
	private JmsTransactionManager txManager = new JmsTransactionManager(cf);
	private TransactionTemplate txTemplate = new TransactionTemplate(txManager);
	private Queue queue = new ActiveMQQueue("ORDER_FILLER_MDB_QUEUE"); 
	
	private JmsTemplate jmsTemplate = new JmsTemplate(cf);

	@Before
	public void setup() throws Exception {
		jmsTemplate.setDestinationResolver(new JndiDestinationResolver());
		jmsTemplate.setSessionTransacted(true);
		jmsTemplate.setSessionAcknowledgeMode(Session.SESSION_TRANSACTED);
	}

	@After
	public void after() throws Exception {
	}

	@JndiConfig()
	public void setupJndiContext(SimpleNamingContextBuilder builder) throws Exception {
		builder.bind(JNDINames.OPC_QUEUE_CONNECTION_FACTORY, cf);
		builder.bind(JNDINames.ORDER_FILLER_MDB_QUEUE, queue);
	}	

	@Test
	public void sendObjectMessage() throws Exception {
		final Date date = new Date();
		Boolean result = (Boolean) txTemplate.execute(new TransactionCallback(){

			@Override
			public Object doInTransaction(TransactionStatus status) {
				return JMSUtils.sendMessage(JNDINames.ORDER_FILLER_MDB_QUEUE, JNDINames.DOC_TYPE, value, date);
			}
		});

		assertTrue(result);
		
		Message msg = receive();
		assertNotNull(msg);
		assertTrue(msg instanceof ObjectMessage);
		Date received = (Date) ((ObjectMessage) msg).getObject();
		assertEquals(date, received);
		assertEquals(value, msg.getStringProperty(JNDINames.DOC_TYPE));
	}

	@Test
	public void sendTextMessage() throws Exception {
		Boolean result = (Boolean) txTemplate.execute(new TransactionCallback(){

			@Override
			public Object doInTransaction(TransactionStatus status) {
				return JMSUtils.sendMessage(JNDINames.ORDER_FILLER_MDB_QUEUE, JNDINames.DOC_TYPE, value, TEXT_MSG);
			}
		});

		assertTrue(result);
		
		Message msg = receive();
		assertNotNull(msg);
		assertTrue(msg instanceof TextMessage);		
		assertEquals(TEXT_MSG, ((TextMessage) msg).getText());
		assertEquals(value, msg.getStringProperty(JNDINames.DOC_TYPE));
		
	}

	Message receive() {
		return (Message) txTemplate.execute(new TransactionCallback() {

			@Override
			public Object doInTransaction(TransactionStatus status) {
				// TODO Auto-generated method stub
				return jmsTemplate.receive(queue);
			}
		});
	}
	
}
