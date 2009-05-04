/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: MissingFormDataException.java,v 1.2 2004/05/26 00:06:19 inder Exp $ */

package com.sun.j2ee.blueprints.consumerwebsite.exceptions;

import java.util.Collection;
import com.sun.j2ee.blueprints.waf.controller.web.html.HTMLActionException;

/**
 * This exception is thrown by an Action
 * when a user fails to provide enough form information. This
 * excption contains list of form fields needed. This exception
 * is used by a JSP page to generate an error page.
 */
public class MissingFormDataException extends HTMLActionException implements java.io.Serializable {

    private Collection missingFields;
    private String message;

    public MissingFormDataException(String message, Collection missingFields) {
        this.message = message;
        this.missingFields = missingFields;
    }
    
    public MissingFormDataException(String message, Throwable cause, Collection missingFields) {
        super(cause);
        this.message = message;
        this.missingFields = missingFields;
    }

    public Collection getMissingFields() {
        return missingFields;
    }

    public String getMessage() {
        return message;
    }

}

