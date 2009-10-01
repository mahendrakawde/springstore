/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: MailerException.java,v 1.3 2004/07/20 01:08:20 yutayoshida Exp $ */

package com.sun.j2ee.blueprints.opc.mailer;

/**
 * MailerAppException is an exception that extends the standrad
 * Exception. This is thrown by the mailer component when there is some
 * failure while sending the mail
 */

public class MailerException extends Exception {

    /**
     * Default constructor. Takes no arguments
     */
    public MailerException() {}

    /**
     * Constructor
     * @param str    a string that explains what the exception condition is
     */
    public MailerException(String str) {
        super(str);
    }
}

