/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: CatalogException.java,v 1.2 2004/05/26 00:06:13 inder Exp $ */

package com.sun.j2ee.blueprints.catalog;

/**
 * CatalogException is an exception that extends the
 * standard Exception. This is thrown by the the signon
 * component when there is some failure because of user error
 */
public class CatalogException extends RuntimeException {

    public CatalogException() {}
    public CatalogException(String msg) { super(msg); }
    public CatalogException(String msg, Throwable cause) {
        super(msg, cause);
    }
    public CatalogException(Throwable cause) {
        super(cause);
    }
}
