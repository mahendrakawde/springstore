/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: EventMapping.java,v 1.2 2004/05/26 00:07:30 inder Exp $ */

package com.sun.j2ee.blueprints.waf.controller.web;

/**
 * This class represents the mapping between an Event and the necessary EJB Action Class
*/

public class EventMapping implements java.io.Serializable {

    private String eventClass = null;
    private String commandClass = null;



    public EventMapping(String eventClassName, String commandClass ) {
        this.eventClass = eventClassName;
        this.commandClass = commandClass;

    }

   public String getCommandClassName() {
        return commandClass;
    }

   public String getEventClassName() {
        return eventClass;
    }


    public String toString() {
        return "[EventMapping:" +
                " eventClass=" + eventClass +
                ", commandClass=" + commandClass +
                "]";
    }
}


