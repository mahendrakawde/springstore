/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: Mail.java,v 1.3 2004/07/20 01:08:20 yutayoshida Exp $ */

package com.sun.j2ee.blueprints.opc.mailer;

import java.io.StringWriter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.CompactWriter;


public class Mail {

    private String address;
    private String subject;
    private String content;

    private Mail() {}

    public Mail(String address, String subject, String content) {
        this.address = address;
        this.subject = subject;
        this.content = content;
    }

    // getters

    public String getAddress() {
        return address;
    }

    public String getSubject() {
        return subject;
    }
    public String getContent() {
        return content;
    }

    public String toXML() {
    	XStream xstream = new XStream();
    	xstream.alias("mail", Mail.class);
    	StringWriter writer = new StringWriter();
    	xstream.marshal(this, new CompactWriter(writer));
    	return writer.toString();
    }
}
