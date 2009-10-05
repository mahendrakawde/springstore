package com.sun.j2ee.blueprints.opc.mailer;

import static org.junit.Assert.assertTrue;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.StringUtils;

import com.thoughtworks.xstream.XStream;

public class MailTest {
	
	private final Mail mail = new Mail("test@test.ex", "test subject", "This is the content");

	private XStream xstream = new XStream();
	
	@Before()
	public void setup() throws Exception {
		xstream.alias("mail", Mail.class);		
	}
	
	@Test
	public void fromXml() throws Exception{
		String xml = mail.toXML();		
		assertTrue(StringUtils.hasLength(xml));

		Mail m = (Mail) xstream.fromXML(xml);
		assertTrue(EqualsBuilder.reflectionEquals(mail, m));
	}	
 
}
