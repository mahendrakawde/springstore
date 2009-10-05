package com.sun.j2ee.blueprints.opc.mailer;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.Locale;
import java.util.Properties;

import javax.mail.Session;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dumbster.smtp.SimpleSmtpServer;
import com.dumbster.smtp.SmtpMessage;
import com.sun.j2ee.blueprints.test.annotation.JndiConfig;
import com.sun.j2ee.blueprints.test.context.jndi.JndiContextTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(value={JndiContextTestExecutionListener.class})
public class MailHelperTests {

	private SimpleSmtpServer server;
	
	@Before
	public void setup() {
		server = SimpleSmtpServer.start();		
	}
	
	@JndiConfig
	public void setupJndi(SimpleNamingContextBuilder builder) {
		Properties props = new Properties();
		props.setProperty("mail.smtp.host", "localhost");
		props.setProperty("mail.smtp.port", "25");
		builder.bind(JNDINames.MAIL_SESSION, Session.getDefaultInstance(props, null));
	}
	
	@After
	public void cleanUp() {
		
	}
	
	@Test
	public void testSend() throws Exception {
		MailHelper helper = new MailHelper();
		String emailAddress = "test@mytest.class";
		String subject = "Test Subject";
		String mailContent = "Test Mail Content";
		
		helper.createAndSendMail(emailAddress, subject, mailContent, Locale.US);
		server.stop();
		assertEquals(1, server.getReceivedEmailSize());
		Iterator emailIter = server.getReceivedEmail();
		SmtpMessage email = (SmtpMessage)emailIter.next();
		assertEquals(subject, email.getHeaderValue("Subject"));
		assertEquals(emailAddress, email.getHeaderValue("To"));
		assertEquals(mailContent, email.getBody());
		
	}
	
}
