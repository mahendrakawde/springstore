/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: ValueTag.java,v 1.3 2004/07/20 01:08:22 yutayoshida Exp $ */

package com.sun.j2ee.blueprints.taglibs.smart;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.BodyContent;

import java.io.IOException;
import java.util.*;

/**
 * Defines 'value' attribute for 'input' tag.
 */
public class ValueTag extends BodyTagSupport {
    
    public int doAfterBody() throws JspTagException {
        InputTag tag 
            = (InputTag) findAncestorWithClass(this, InputTag.class);
        BodyContent bc = getBodyContent();
        String body = bc.getString();
        if (body != null && !body.trim().equals("")) {
            tag.setValue(body);
        }
        bc.clearBody();
        return SKIP_BODY;
    }
}

