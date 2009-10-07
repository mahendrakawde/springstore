/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: ChangeLocaleHTMLAction.java,v 1.3 2004/07/20 01:08:23 yutayoshida Exp $ */

package com.sun.j2ee.blueprints.waf.controller.web.html.impl;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import com.sun.j2ee.blueprints.waf.controller.Event;
import com.sun.j2ee.blueprints.waf.controller.impl.ChangeLocaleEvent;
import com.sun.j2ee.blueprints.waf.controller.web.html.HTMLActionException;
import com.sun.j2ee.blueprints.waf.controller.web.html.HTMLActionSupport;
import com.sun.j2ee.blueprints.waf.util.I18nUtil;

/**
 * Implementation of HTMLAction that processes a user change in language.
 * 
 */

public final class ChangeLocaleHTMLAction extends HTMLActionSupport {

	public static final String LOCALE = "com.sun.j2ee.blueprints.waf.LOCALE";

	public Event perform(HttpServletRequest request) throws HTMLActionException {
		// Extract attributes we will need
		String localeString = request.getParameter("locale");
		Locale locale = I18nUtil.getLocaleFromString(localeString);
		if (locale != null) {
			request.getSession().setAttribute(LOCALE, locale);
			return new ChangeLocaleEvent(locale);
		} else {
			logger.warn(
					"Can't Change the locale becasue the locale is null!!! {}",
					localeString);
			throw new HTMLActionException(
					"ChangeLocaleAction: Unable to change language to "
							+ localeString);
		}
	}
}
