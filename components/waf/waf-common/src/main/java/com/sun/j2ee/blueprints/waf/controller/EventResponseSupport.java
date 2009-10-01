/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: EventResponseSupport.java,v 1.2 2004/05/26 00:07:26 inder Exp $ */

package com.sun.j2ee.blueprints.waf.controller;


/**
 * Convience class to extend for EventResponses
 */
public abstract class EventResponseSupport implements EventResponse {
    
    private Object payload = null;
    
    public EventResponseSupport(Object payload) {
        this.payload = payload;
    }
    
    public Object getPayload() {
        return payload;
    }
}

