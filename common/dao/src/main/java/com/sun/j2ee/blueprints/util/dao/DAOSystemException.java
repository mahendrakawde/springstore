/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: DAOSystemException.java,v 1.2 2004/05/26 00:07:19 inder Exp $ */

package com.sun.j2ee.blueprints.util.dao;

import java.lang.RuntimeException;

/**
 * DAOSystemException is thrown by a DAO component when there is 
 * some irrecoverable error (like SQLException)
 */
public class DAOSystemException extends RuntimeException {

    public DAOSystemException() {}
    public DAOSystemException(String msg) { super(msg); }
    public DAOSystemException(String msg, Throwable cause) {
        super(msg, cause);
    }
    public DAOSystemException(Throwable cause) {
        super(cause);
    }
}
