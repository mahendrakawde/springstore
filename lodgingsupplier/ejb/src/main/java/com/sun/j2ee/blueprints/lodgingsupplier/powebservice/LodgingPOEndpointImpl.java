package com.sun.j2ee.blueprints.lodgingsupplier.powebservice;

import javax.jms.ConnectionFactory;

import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.destination.JndiDestinationResolver;

import com.sun.j2ee.blueprints.lodgingsupplier.JNDINames;

public class LodgingPOEndpointImpl implements LodgingPOEndpoint {

	private JmsTemplate jmsTemplate;

	public LodgingPOEndpointImpl(ConnectionFactory connectionFactory) {
		this.jmsTemplate = new JmsTemplate(connectionFactory);
		jmsTemplate.setDestinationResolver(new JndiDestinationResolver());
	}

	public String submitLodgingReservationDetails(String xmlPO)
			throws InvalidOrderException, OrderSubmissionException {
		// Do Interaction layer processing
		LodgingOrder lodgeObj = preProcessInput(xmlPO);

		// Submit request to JMS Queue
		try {
			submitRequest(lodgeObj);
		} catch (JmsException ex) {
			throw new OrderSubmissionException(ex.getMessage());
		}

		// Return co-relation ID - dummy ID for now
		return ("LODGE1234");
	}

	private LodgingOrder preProcessInput(String po)
			throws InvalidOrderException {
		// XML doc should be ideally validated against its schema here;
		// Similar scenario shown in OPC module;
		// Here it is skipped in this sample - we will convert doc to obj
		return (LodgingOrder.fromXML(po));
	}

	private void submitRequest(LodgingOrder lodge) throws OrderSubmissionException {
		jmsTemplate.convertAndSend(JNDINames.LODGE_QUEUE, lodge);
	}

}
