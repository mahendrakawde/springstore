/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: ChangeLocaleHTMLAction.java,v 1.3 2004/07/20 01:08:23 yutayoshida Exp $ */

package com.sun.j2ee.blueprints.waf.controller.web.html.impl;

import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


// waf imports
import com.sun.j2ee.blueprints.waf.util.I18nUtil;
import com.sun.j2ee.blueprints.waf.controller.*;
import com.sun.j2ee.blueprints.waf.controller.impl.ChangeLocaleEvent;
import com.sun.j2ee.blueprints.waf.controller.web.html.*;


/**
 * Implementation of HTMLAction that processes a
 * user change in language.
 *
 */

public final class ChangeLocaleHTMLAction extends HTMLActionSupport {


    public static final String LOCALE = "com.sun.j2ee.blueprints.waf.LOCALE";
    
    public Event perform(HttpServletRequest request)
  throws HTMLActionException {
  // Extract attributes we will need
        String localeString = request.getParameter("locale");
  Locale locale = I18nUtil.getLocaleFromString(localeString);   
  if (locale != null) {
            request.getSession().setAttribute(LOCALE,locale);
            return new ChangeLocaleEvent(locale);
        } else {
            System.out.println("Can't Change the locale becasue the locale is null!!!" + localeString);
            throw new HTMLActionException("ChangeLocaleAction: Unable to change language to " + localeString);
        }
    }
}

