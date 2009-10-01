/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: EventResponse.java,v 1.2 2004/05/26 00:07:26 inder Exp $ */

package com.sun.j2ee.blueprints.waf.controller;


/**
 * This interface determines the required methods for an waf event
 */
public interface EventResponse extends java.io.Serializable {
    /**
    *   Specifiy a logical name that is mapped to the event in
    *   in the StateMachine.
    */
    public  String getEventName();
}

