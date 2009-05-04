/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: URLMapping.java,v 1.2 2004/05/26 00:07:31 inder Exp $ */

package com.sun.j2ee.blueprints.waf.controller.web;

import java.util.HashMap;

public class URLMapping implements java.io.Serializable {

    private String url;
    private boolean isAction = false;
    private boolean useFlowHandler = false;
    private String flowHandler = null;
    private String webActionClass = null;
    private String ejbActionClass = null;
    private HashMap resultMappings;
    private String screen;
    private boolean usesTransaction = false;

    public URLMapping(String url, String screen) {
        this.url = url;
        this.screen = screen;
    }

    public URLMapping(String url, 
                                    String screen,
                                    boolean isAction,
                                    boolean useFlowHandler,
                                    boolean usesTransaction,
                                    String webActionClass,
                                    String flowHandler,
                                    HashMap resultMappings) {
        this.url = url;
        this.flowHandler = flowHandler;
        this.webActionClass = webActionClass;
        this.isAction = isAction;
        this.useFlowHandler = useFlowHandler ;
        this.resultMappings = resultMappings;
        this.screen = screen;
        this.usesTransaction = usesTransaction;
    }
    
    public String getURI() {
        return url;
    }

    public boolean useFlowHandler() {
        return useFlowHandler;
    }
    
    public boolean isTransactional() {
        return usesTransaction;
    }

    public boolean isAction() {
        return isAction;
    }

    public String getWebAction() {
        return webActionClass;
    }
    
    public String getFlowHandler() {
        return flowHandler;
    }

    public String getScreen() {
        return screen;
    }

    public String getResultScreen(String key) {
        if (resultMappings != null) {
            return (String)resultMappings.get(key);
        } else {
            return null;
        } 
    }

    public HashMap getResultMappings() {
        return resultMappings;
    }

    public String toString() {
        return "[URLMapping: url=" + url +
                ", isAction=" + isAction +
                ", isTransactional=" + usesTransaction +
                ", useFlowHandler=" + useFlowHandler +
                ", webActionClass=" + webActionClass +
                ", ejbActionClass=" + ejbActionClass +
                ", flowHandler=" + flowHandler +
                ", resultMappings=" + resultMappings +
                "]";
    }
}


