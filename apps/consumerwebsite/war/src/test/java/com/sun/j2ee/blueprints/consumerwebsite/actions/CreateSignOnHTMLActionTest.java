package com.sun.j2ee.blueprints.consumerwebsite.actions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import com.sun.j2ee.blueprints.consumerwebsite.AdventureKeys;
import com.sun.j2ee.blueprints.test.actions.AbstractActionTests;
import com.sun.j2ee.blueprints.waf.controller.web.html.HTMLAction;

public class CreateSignOnHTMLActionTest extends AbstractActionTests {

	private CreateSignOnHTMLAction action = new CreateSignOnHTMLAction();

	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";

	@Override
	protected void populateRequest(MockHttpServletRequest request) {
		request.setParameter("j_username", USERNAME);
		request.setParameter("j_password", PASSWORD);
	}

	@Test
	public void perform() throws Exception {
		assertNull(action.perform(request));
		assertEquals(USERNAME, request.getSession().getAttribute(
				AdventureKeys.SIGN_ON_TEMP_USERNAME));
		assertEquals(PASSWORD, request.getSession().getAttribute(
				AdventureKeys.SIGN_ON_TEMP_PASSWORD));
	}

	@Override
	protected HTMLAction getActionUnderTest() {
		return this.action;
	}

}
