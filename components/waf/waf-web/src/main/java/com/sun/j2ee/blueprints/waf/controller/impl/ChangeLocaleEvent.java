/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: ChangeLocaleEvent.java,v 1.2 2004/05/26 00:07:28 inder Exp $ */

package com.sun.j2ee.blueprints.waf.controller.impl;

import java.io.Serializable;
import java.util.Locale;

import com.sun.j2ee.blueprints.waf.controller.EventSupport;

/**
 * This Event notifies the EJBController of a change in Locale
 */
public class ChangeLocaleEvent extends EventSupport {

    private Locale locale;
    
    public ChangeLocaleEvent(Locale locale) {
        this.locale = locale;
    }
    
    public Locale getLocale() {
        return locale;
    }
    
    public String getEventName() {
        return "java:comp/env/param/event/ChangeLocaleEvent";
    }

}

