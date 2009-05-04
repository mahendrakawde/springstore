/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: NameTag.java,v 1.3 2004/07/20 01:08:22 yutayoshida Exp $ */

package com.sun.j2ee.blueprints.taglibs.smart;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import java.io.*;
import java.util.*;

/**
 * Name tag. Defines 'name' attribute for InputTag.
 */
public class NameTag extends BodyTagSupport {
    public int doAfterBody() throws JspTagException {
        InputTag tag 
            = (InputTag) findAncestorWithClass(this, InputTag.class);
        BodyContent bc = getBodyContent();
        String body = bc.getString();
        if (body != null && !body.trim().equals("")) {
            tag.setName(body);
        }
        bc.clearBody();
        return SKIP_BODY;
    }
}
