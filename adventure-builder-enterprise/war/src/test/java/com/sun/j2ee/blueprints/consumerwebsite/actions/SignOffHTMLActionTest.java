package com.sun.j2ee.blueprints.consumerwebsite.actions;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.mock.web.MockHttpSession;

import com.sun.blueprints.test.jdbc.AbstractActionTests;
import com.sun.j2ee.blueprints.waf.controller.web.html.HTMLAction;

public class SignOffHTMLActionTest extends AbstractActionTests {

	private SignOffHTMLAction action = new SignOffHTMLAction();

	@Test
	public void perform() throws Exception {
		MockHttpSession session = (MockHttpSession) request.getSession();
		assertNull(action.perform(request));
		assertTrue(session.isInvalid());
	}

	@Override
	protected HTMLAction getActionUnderTest() {
		return this.action;
	}

}
