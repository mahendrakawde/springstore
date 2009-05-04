package com.sun.j2ee.blueprints.lodgingsupplier.powebservice;

public interface LodgingPOEndpoint {

	String submitLodgingReservationDetails(String xmlPO) throws InvalidOrderException, OrderSubmissionException;
	
}
