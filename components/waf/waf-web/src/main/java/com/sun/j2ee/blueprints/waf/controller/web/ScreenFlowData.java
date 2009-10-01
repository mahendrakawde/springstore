/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: ScreenFlowData.java,v 1.2 2004/05/26 00:07:31 inder Exp $ */

package com.sun.j2ee.blueprints.waf.controller.web;

import java.util.*;

public class ScreenFlowData implements java.io.Serializable {

    private ArrayList exceptionMappings;
    private String defaultScreen = null;

    public ScreenFlowData (ArrayList exceptionMappings,
                           String defaultScreen) {
        this.exceptionMappings = exceptionMappings;
        this.defaultScreen = defaultScreen;
    }

    public String getDefaultScreen() {
        return defaultScreen;
    }
    
    public ArrayList getExceptionMappings() {
        return exceptionMappings;
    }
    
    public String toString() {
        return "ScreenFlowData: {defaultScreen=" + defaultScreen + ", " +
                    " exceptionMappings=" + exceptionMappings + "}";
    }
}


