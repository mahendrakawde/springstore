/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: XMLException.java,v 1.2 2004/05/26 00:06:49 inder Exp $ */

package com.sun.j2ee.blueprints.opc.invoice;

public class XMLException extends RuntimeException {
    public XMLException() {}
    public XMLException(String msg) { super(msg); }
    public XMLException(String msg, Throwable cause) { super(msg, cause); }
    public XMLException(Throwable cause) { super(cause); }
}
