/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: Screen.java,v 1.2 2004/05/26 00:07:35 inder Exp $ */

package com.sun.j2ee.blueprints.waf.view.template;

import java.util.Map;

public class Screen implements java.io.Serializable {

    private String name = null;
    private String templateName = null;
    private Map parameters;


    public Screen(String name,
                  Map parameters) {
        this.name = name;
        this.parameters = parameters;
    }

    public Screen(String name,
                  String templateName,
                  Map parameters) {
        this.name = name;
        this.templateName = templateName;
        this.parameters = parameters;
    }

    public String getTemplate() {
        return templateName;
    }

    public Map getParameters() {
        return parameters;
    }

    public Parameter getParameter(String key) {
        return (Parameter) parameters.get(key);
    }

    public String toString() {
        return "[Screen: name=" + name + ", parameters=" + parameters + "]";
    }
}


