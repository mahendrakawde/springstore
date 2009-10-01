/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: ServiceLocatorException.java,v 1.2 2004/05/26 00:07:17 inder Exp $ */

package com.sun.j2ee.blueprints.servicelocator;

public class ServiceLocatorException extends RuntimeException {
    public ServiceLocatorException() { super(); }
    public ServiceLocatorException(final String msg) { super(msg); }
    public ServiceLocatorException(final String msg, final Throwable cause) { super(msg, cause); }
    public ServiceLocatorException(final Throwable cause) { super(cause); }
}
