/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: ByteArrayDataSource.java,v 1.3 2004/07/20 01:08:20 yutayoshida Exp $ */

package com.sun.j2ee.blueprints.opc.mailer;


import java.io.*;
import java.util.Date;
import javax.activation.DataSource;


/**
 * Used to create a DataSource for the mail message.
 * @see MailHelper
 */
class ByteArrayDataSource implements DataSource {
    private byte[] data; // data for mail message
    private String type; // content type/mime type

   /**
    * Create a DataSource from a String
    * @param data is the contents of the mail message
    * @param type is the mime-type such as text/html
    */
    ByteArrayDataSource(String data, String type) {
        try {
           this.data = data.getBytes("UTF-8");
        } catch (UnsupportedEncodingException uex) { }
        this.type = type;
    }

    //DataSource interface methods

    public InputStream getInputStream() throws IOException {
        if (data == null)
            throw new IOException("no data");
        return new ByteArrayInputStream(data);
    }

    public OutputStream getOutputStream() throws IOException {
        throw new IOException("cannot do this");
    }

    public String getContentType() {
        return type;
    }

    public String getName() {
        return "dummy";
    }
}


