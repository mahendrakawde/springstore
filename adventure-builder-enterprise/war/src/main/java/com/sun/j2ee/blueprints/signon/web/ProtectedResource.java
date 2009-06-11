/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: ProtectedResource.java,v 1.2 2004/05/26 00:06:27 inder Exp $ */

package com.sun.j2ee.blueprints.signon.web;


import java.util.ArrayList;

/**
 * This class is an object representation of a protected resource
 */

public class ProtectedResource implements java.io.Serializable {

    private String name = null;
    private String urlPattern = null;
    private ArrayList roles = null;

    public ProtectedResource (String name, String urlPattern, ArrayList roles) {
        this.name = name;
        this.urlPattern = urlPattern;
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public String getURLPattern() {
        return urlPattern;
    }

    public ArrayList getRoles() {
        return roles;
    }

    public String toString() {
        return "ProtectedResource [ name=" + name + ", urlPattern=" + urlPattern + ", roles=" + roles + "]";
    }
}
