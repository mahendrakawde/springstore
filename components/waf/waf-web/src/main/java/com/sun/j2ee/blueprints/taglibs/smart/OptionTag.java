/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: OptionTag.java,v 1.3 2004/07/20 01:08:22 yutayoshida Exp $ */

package com.sun.j2ee.blueprints.taglibs.smart;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;


import java.io.IOException;
import java.util.*;

/**
 * HTML 'option' tag. Child of SelectTag.
 */
public class OptionTag extends BodyTagSupport {

    String value = "";

    public void setValue(String v) { value = v; }

    public int doEndTag() throws JspTagException {
        SelectTag selectTag 
            = (SelectTag) findAncestorWithClass(this, SelectTag.class);
        BodyContent bc = getBodyContent();
        if (bc == null) {
            return SKIP_BODY;
        }
        String text = bc.getString();
        if (value != null && !value.trim().equals("")) {
            if (text != null && !text.trim().equals("")) {
                selectTag.putOption(value, 
                                    ((text != null) && !text.trim().equals(""))
                                    ? text
                                    : value);
            } else {
                selectTag.putOption(value, value);
            }
        } else if ((text != null) && !text.trim().equals(""))  {
            selectTag.putOption(text,text); 
        }
        bc.clearBody();
        value = "";
        return SKIP_BODY;
    }
}


