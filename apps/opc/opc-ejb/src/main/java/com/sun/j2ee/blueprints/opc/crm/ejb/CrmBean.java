/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: CrmBean.java,v 1.10 2004/07/20 01:08:20 yutayoshida Exp $ */

package com.sun.j2ee.blueprints.opc.crm.ejb;

import java.util.Locale;

import javax.ejb.EJBException;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.ejb.support.AbstractJmsMessageDrivenBean;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.xml.transform.StringSource;

import com.sun.j2ee.blueprints.opc.mailer.JNDINames;
import com.sun.j2ee.blueprints.opc.mailer.Mail;
import com.sun.j2ee.blueprints.opc.mailer.MailHelper;
import com.sun.j2ee.blueprints.servicelocator.ejb.NullBeanFactoryLocator;
import com.sun.j2ee.blueprints.servicelocator.ejb.ServiceLocator;

/**
 * MailCompletedOrderMDB receives a JMS message containing an Order xml message
 * for orders that are COMPLETELY shipped. It builds a mail message that it then
 * sends to the mailer service so that the customer gets email
 */
public class CrmBean extends AbstractJmsMessageDrivenBean {

	private MailHelper mh;
	private Unmarshaller marshaller;
	
	protected void onEjbCreate() {
		mh = new MailHelper();
		marshaller = new XStreamMarshaller();
		((XStreamMarshaller)marshaller).addAlias("mail", Mail.class);
	}

	public void setMessageDrivenContext(MessageDrivenContext messageDrivenContext) {
		super.setMessageDrivenContext(messageDrivenContext);
		setBeanFactoryLocator(new NullBeanFactoryLocator());
	}

	
	/**
	 * Receive a JMS Message containing the Order that is completed, generate
	 * Mail xml messages for the customer The Mail xml mesages contain html
	 * presentation
	 */
	public void onMessage(Message recvMsg) {
		
		ServiceLocator sl = new ServiceLocator();
		boolean sendmail = sl.getBoolean(JNDINames.SEND_MAIL);

		try {
			TextMessage recdTM = (TextMessage) recvMsg;
			Mail mail = (Mail) marshaller.unmarshal(new StringSource(recdTM.getText()));
			if (sendmail) {
				doWork(mail);
			} else {
				logger.info("Received mail message.\n" + mail);
			}
		} catch (Exception je) {
			throw new EJBException(je);
		}
	}

	/**
	 * Process the message and do the right thing.
	 */
	private String doWork(final Mail mail) throws JMSException {
		String recipient = mail.getAddress();
		String xmlMessage = mail.getContent();
		String title = mail.getSubject();
		if (recipient == null)
			return "failed";
		try {
			mh.createAndSendMail(recipient, title, xmlMessage, Locale
					.getDefault());
		} catch (Exception ex) {
			logger.error("CrmBean: caught=" + ex.getMessage(), ex);
			return "failed";
		}
		return "success";
	}

}
