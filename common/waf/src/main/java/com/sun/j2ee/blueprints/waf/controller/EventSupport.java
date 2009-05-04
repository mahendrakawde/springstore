/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: EventSupport.java,v 1.2 2004/05/26 00:07:26 inder Exp $ */

package com.sun.j2ee.blueprints.waf.controller;


/**
 * This is the base class for all events used by the application.
 */
public class EventSupport implements Event {

    private String commandClassName = null;
    
    /**
     * Specifies the class name for the ejb action class (command) used by
     * the command factory to process events.A command factory may be present
     * on the web tier or ejb tier
     *
     * When set to null command is not processed.
     */
    public String getCommandClassName() {
        return commandClassName;
    }
    
    public void setCommandClassName(String commandClassName) {
        this.commandClassName = commandClassName;
    }
    
    /**
      * return the name of the Event
     */
    
    public String getEventName() {
        return null;
    }

}

