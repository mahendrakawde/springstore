package com.sun.j2ee.blueprints.opc.mailer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.StringUtils;

import com.sun.j2ee.blueprints.opc.purchaseorder.Lodging;
import com.thoughtworks.xstream.XStream;

public class XmlTests {
	
	private final Mail mail = new Mail("test@test.ex", "test subject", "This is the content");

	private static final String EXPECTED_XML = "<mail><address>test@test.ex</address><subject>test subject</subject><content>This is the content</content></mail>";
	
	private XStream xstream = new XStream();
	
	@Before()
	public void setup() throws Exception {
		xstream.alias("mail", Mail.class);
		xstream.alias("lodging", Lodging.class);
	}
	
	@Test
	public void mailTtoXml() throws Exception {
		String xml = mail.toXML();
		assertEquals(EXPECTED_XML, xml);
	}
	@Test
	public void mailFromXml() throws Exception{
		String xml = mail.toXML();		
		assertTrue(StringUtils.hasLength(xml));

		Mail m = (Mail) xstream.fromXML(xml);
		assertTrue(EqualsBuilder.reflectionEquals(mail, m));
	}
	
	@Test
	public void lodgingToXml() {
		Calendar start = new GregorianCalendar(2008, 1, 1);
		Calendar end = new GregorianCalendar(2008, 1, 15);
		Lodging lodging = new Lodging("LODG-1", "XML Test Lodging", 12.50f, "Test Location", start, end, 14, 2);
		String xml = lodging.toXML("AB-test-12345678900");
		System.out.println(xml);
		xstream.toXML(lodging, new PrintWriter(System.out));
	}
 
}
