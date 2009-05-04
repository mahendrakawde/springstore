/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: CheckedTag.java,v 1.3 2004/07/20 01:08:22 yutayoshida Exp $ */

package com.sun.j2ee.blueprints.taglibs.smart;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import java.io.IOException;
import java.util.*;

/**
 * Defines what should be selected for an 'option' tag.
 */
public class CheckedTag extends BodyTagSupport {
    public int doAfterBody() throws JspTagException {
        CheckboxTag tag 
            = (CheckboxTag) findAncestorWithClass(this, CheckboxTag.class);
        BodyContent bc = getBodyContent();
        String selectedValue = bc.getString();
        if (selectedValue != null && !selectedValue.trim().toLowerCase().equals("true")) {
            tag.setChecked(true);
        } else {
            tag.setChecked(false);
        }
        bc.clearBody();
        return SKIP_BODY;
    }
}

