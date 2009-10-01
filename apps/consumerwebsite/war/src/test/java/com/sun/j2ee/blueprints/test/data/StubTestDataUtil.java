package com.sun.j2ee.blueprints.test.data;

import java.util.ArrayList;

import com.sun.j2ee.blueprints.catalog.Activity;
import com.sun.j2ee.blueprints.catalog.AdventurePackage;
import com.sun.j2ee.blueprints.catalog.Lodging;
import com.sun.j2ee.blueprints.catalog.Transportation;
import com.sun.j2ee.blueprints.customer.Account;
import com.sun.j2ee.blueprints.customer.Address;
import com.sun.j2ee.blueprints.customer.ContactInformation;

public abstract class StubTestDataUtil {

	public static Account createAccount(String userId) {
		Address address = new Address("Teststreet 1", "tst", "Testcity", "TST", "12345TS", "Testland");
		ContactInformation ci=  new ContactInformation("Tester", "Testy", "0123456789", "test@testershome.com", address);
		return new Account(userId, ci);
	}
	
	public static Lodging createLodging(String lodgingId) {
		Lodging lodging = new Lodging(lodgingId, "Dummy Test lodging", "Lodging created by the StubTestDataUtil", 234.0d, "Test location");
		return lodging;
	}
	
	public static Activity createActivity(String activityId) {
		Activity activity = new Activity(activityId, "Dummy Test Activity", "Activity created by the StubTestDatautil", 123.04d, "Test Location");
		return activity;
	}
	
	public static Transportation createTransportation(String transportationId) {
		Transportation transportation = new Transportation(transportationId, "Test Transportation", "Transportation created by the StubTestDatautil", "", 321.12d, "ORIG", "DEST", "TST", "11:12", "14:45", "Economy");
		return transportation;
	}
	
	public static AdventurePackage createAdventurePackage(String packageId, String lodgingId, ArrayList activities) {
		AdventurePackage adventurePackage = new AdventurePackage(packageId, "Test AdventurePackage", "AdventurePackage created by the StubTestDatautil", "Somewhere", lodgingId, 555.55d, activities);
		return adventurePackage;
	}
	
//	public static getLocation(String locationId) {		
//	}
	
	
}
