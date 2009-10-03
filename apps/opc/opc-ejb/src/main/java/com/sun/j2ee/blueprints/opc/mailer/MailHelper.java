/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: MailHelper.java,v 1.3 2004/07/20 01:08:20 yutayoshida Exp $ */

package com.sun.j2ee.blueprints.opc.mailer;

import java.util.Date;
import java.util.Locale;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.naming.InitialContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;


/**
 * A helper class to create and send mail.
 */
public class MailHelper {

	private static final Logger logger = LoggerFactory.getLogger(MailHelper.class);
	
	private JavaMailSenderImpl mailSender;
	
    /**
     * This method creates an email message and sends it using the
     * J2EE mail services
     * @param mailContent contains the message contents to send
     */
    public void createAndSendMail(String emailAddress, String subject, String mailContent, Locale locale) throws MailerException {
        try {
        	init();
        	MimeMessageHelper helper = new MimeMessageHelper(mailSender.createMimeMessage());
        	helper.setTo(emailAddress);
            helper.setSubject(subject);
            helper.setText(mailContent, true);
            helper.setSentDate(new Date());
            MimeMessage msg = helper.getMimeMessage();
            msg.addHeader("X-Mailer", "JavaMailer");
            mailSender.send(msg);
        } catch (Exception e) {        	
            logger.error("MailHelper caught: " + e.getMessage(), e);
            throw new MailerException("Failure while sending mail:" + e);
        }
    }
    
    private void init() throws Exception {
        InitialContext ic = new InitialContext();
        Session session = (Session) ic.lookup(JNDINames.MAIL_SESSION);
        mailSender = new JavaMailSenderImpl();
        mailSender.setSession(session);        
    }
}

