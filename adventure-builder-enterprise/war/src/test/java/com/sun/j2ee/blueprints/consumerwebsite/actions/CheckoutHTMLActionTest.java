package com.sun.j2ee.blueprints.consumerwebsite.actions;

import java.util.Locale;

import static org.easymock.EasyMock.*;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import com.sun.blueprints.test.actions.AbstractActionTests;
import com.sun.blueprints.test.data.StubTestDataUtil;
import com.sun.j2ee.blueprints.consumerwebsite.Cart;
import com.sun.j2ee.blueprints.signon.web.SignOnFilter;
import com.sun.j2ee.blueprints.waf.controller.web.html.HTMLAction;


public class CheckoutHTMLActionTest extends AbstractActionTests {

	private static final String USERNAME = "username";
	private static final String LODGING_ID = "LODG-1";

	private CheckoutHTMLAction action = new CheckoutHTMLAction();
	
	
	@Override
	protected HTMLAction getActionUnderTest() {
		return this.action;
	}
	
	@Override
	protected void populateRequest(MockHttpServletRequest request2) {
		//Credit Card Information
		request2.addParameter("credit_card_number", "1234567812345678");
		request2.addParameter("credit_card_month", "12");
		request2.addParameter("credit_card_year", "2020");
		request2.addParameter("credit_card_name", "T. Dummy");
		
		//Contact Info _a (required)
		request2.addParameter("family_name_a", "Dummy");
		request2.addParameter("given_name_a", "Testy");
		request2.addParameter("address_1_a", "Dummy Address Line 1");
		request2.addParameter("city_a", "Testcity");
		request2.addParameter("state_or_province_a", "Dummy State");
		request2.addParameter("postal_code_a", "1111 DT");
		request2.addParameter("country_a", "Dummyland");
		request2.addParameter("telephone_number_a", "0123456789");

		//Contact Info _a (optional)
		request2.addParameter("address_2_a", "Dummy Address Line 2");
		request2.addParameter("email_a", "t.dummy@dummy-tester.org");

		//Contact Info _b (required)
		request2.addParameter("family_name_b", "Dummy");
		request2.addParameter("given_name_b", "Testy");
		request2.addParameter("address_1_b", "Dummy Address Line 1");
		request2.addParameter("city_b", "Testcity");
		request2.addParameter("state_or_province_b", "Dummy State");
		request2.addParameter("postal_code_b", "1111 DT");
		request2.addParameter("country_b", "Dummyland");
		request2.addParameter("telephone_number_b", "0123456789");

		//Contact Info _b (optional)
		request2.addParameter("address_2_b", "Dummy Address Line 2");
		request2.addParameter("email_b", "t.dummy@dummy-tester.org");
		
		//setup session info
		request2.getSession().setAttribute(SignOnFilter.USER_NAME, USERNAME);
		
		//setup cart
		Cart cart = getAdventureComponentManager().getCart(request2.getSession());
		//lodging
		cart.setLodgingDays(10);
		cart.setLodgingRoomCount(1);
		cart.setLodgingId(LODGING_ID);
		//Remainder is optional omitted for now
	}
	
	@Test
	public void perform() throws Exception {
		expect(catalogDAO.getLodging(matches(LODGING_ID), isA(Locale.class))).andReturn(StubTestDataUtil.createLodging(LODGING_ID));
		replay(catalogDAO);
		action.perform(request);
		verify(catalogDAO);
	}
	
}
