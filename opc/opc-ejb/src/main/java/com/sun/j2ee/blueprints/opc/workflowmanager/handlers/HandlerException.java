/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: HandlerException.java,v 1.2 2004/05/26 00:07:03 inder Exp $ */

package com.sun.j2ee.blueprints.opc.workflowmanager.handlers;

public class HandlerException extends Exception {
    private String message;

    public HandlerException(String message) {
        super(message);
  this.message = message;
    }
    
    public String getMessage() {
        return message;
    }
}
