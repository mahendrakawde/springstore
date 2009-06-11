/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: MailHelper.java,v 1.3 2004/07/20 01:08:20 yutayoshida Exp $ */

package com.sun.j2ee.blueprints.opc.mailer;

import java.util.*;

import javax.naming.InitialContext;
import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.*;


/**
 * A helper class to create and send mail.
 */
public class MailHelper {

    /**
     * This method creates an email message and sends it using the
     * J2EE mail services
     * @param mailContent contains the message contents to send
     */
    public void createAndSendMail(String emailAddress, String subject, String mailContent, Locale locale) throws MailerException {
        try {
            InitialContext ic = new InitialContext();
            Session session = (Session) ic.lookup(JNDINames.MAIL_SESSION);
            Message msg = new MimeMessage(session);
            msg.setFrom();
            msg.setRecipients(Message.RecipientType.TO,
                     InternetAddress.parse(emailAddress, false));
            msg.setSubject(subject);
            String contentType = "text/html";
            StringBuffer sb = new StringBuffer(mailContent);
            msg.setDataHandler(new DataHandler(
                              new ByteArrayDataSource(sb.toString(), contentType)));
            msg.setHeader("X-Mailer", "JavaMailer");
            msg.setSentDate(new Date());
            Transport.send(msg);
        } catch (Exception e) {
            System.err.println("MailHelper caught: " + e);
            e.printStackTrace();
            throw new MailerException("Failure while sending mail:" + e);
        }
    }
}

