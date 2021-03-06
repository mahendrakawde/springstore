/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: Screens.java,v 1.2 2004/05/26 00:07:36 inder Exp $ */

package com.sun.j2ee.blueprints.waf.view.template;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Screens implements java.io.Serializable {

	private final Logger logger = LoggerFactory.getLogger(Screens.class);
	
    private Map screenMap;
    private Map templateMap;
    private String defaultTemplate;

    public Screens (String defaultTemplate) {
        screenMap = new HashMap();
        templateMap = new HashMap();
        this.defaultTemplate = defaultTemplate;
    }

    public String getDefaultTemplate() {
        return defaultTemplate;
    }

    public void addScreen(String screenName, Screen screen) {
        if (screenMap.containsKey(screenName)) {
            screenMap.remove(screenName);
        }
        screenMap.put(screenName, screen);
    }

    public void addTemplate(String templateName, String templateURL) {
        if (templateMap.containsKey(templateName)) {
            templateMap.remove(templateName);
        }
        templateMap.put(templateName, templateURL);
    }

    public Screen getScreen(String screenName) {

        if (screenMap.containsKey(screenName)) {
            return (Screen)screenMap.get(screenName);
        } else {
            logger.warn("Screens Error: Screen {} not defined.", screenName);
           return null;
        }
    }
    
    public boolean containsScreen(String screenName) {
        return screenMap.containsKey(screenName);
    }
    
    public boolean containsTemplate(String templateName) {
        return templateMap.containsKey(templateName);
    }

    public String getTemplate(String screenName) {
        if (screenMap.containsKey(screenName)) {
            String templateName = ((Screen)screenMap.get(screenName)).getTemplate();
            if ((templateName != null) && templateMap.containsKey(templateName)) {
                    return (String)templateMap.get(templateName);
            } else {
                // return the default if template not defined for screen
                return defaultTemplate;
            }
        } else {
                logger.warn("Screens:getTemplate() error: Screen {} not defined.", screenName);
                return null;
        }
    }
    
    public String toString() {
        return "[Screens: defaultTemplate=" + defaultTemplate + ", " +
                    "screenMap=" + screenMap +
                    "templateMap=" + templateMap + "]";
    }
}
