/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: CatalogDAOException.java,v 1.2 2004/05/26 00:06:14 inder Exp $ */

package com.sun.j2ee.blueprints.catalog.dao;

/**
 * An application exception indicating something has gone wrong
 *
 */
public class CatalogDAOException extends RuntimeException {

    public CatalogDAOException() {}
    public CatalogDAOException(String s) { super(s); }
    public CatalogDAOException(String msg, Throwable cause) {
        super(msg, cause);
    }
    public CatalogDAOException(Throwable cause) {
        super(cause);
    }
}
