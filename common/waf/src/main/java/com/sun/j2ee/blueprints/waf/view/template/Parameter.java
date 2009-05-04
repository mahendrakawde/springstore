/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: Parameter.java,v 1.2 2004/05/26 00:07:35 inder Exp $ */


package com.sun.j2ee.blueprints.waf.view.template;

import java.util.HashMap;

public class Parameter implements java.io.Serializable {

    private String key;
    private String value;
    private boolean direct;

    public Parameter(String key, String value, boolean direct) {
        this.key = key;
        this.value = value;
        this.direct = direct;
    }

    public boolean isDirect() {
        return direct;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public String toString() {
        return "[Parameter: key=" + key + ", value=" + value + ", direct="+ direct + "]";
    }
}


