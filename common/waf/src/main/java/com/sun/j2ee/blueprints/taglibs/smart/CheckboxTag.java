/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: CheckboxTag.java,v 1.3 2004/07/20 01:08:21 yutayoshida Exp $ */

package com.sun.j2ee.blueprints.taglibs.smart;

import java.io.IOException;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;


/**
 * HTML 'checkbox' tag. This tab also lets the web page
 * developer specify the default state of the tag.
 */
public class CheckboxTag extends SimpleTagSupport {

    private String value;
    private String name;
    private String id;
    private boolean checked = true;

    public void setName(String name) { this.name = name;}

    public void setValue(String value) {this.value = value;}

    public void setChecked(boolean checked) {this.checked = checked;}
    
    public void setId(String id) {this.id = id;}


    public void doTag() throws JspTagException { 
        try {
            FormTag tag 
                = (FormTag) findAncestorWithClass(this, FormTag.class);
            StringBuffer html = new StringBuffer();
            html.append("<input type=\"checkbox\"");
            html.append(name != null ? (" name=\"" + name + "\"") : "");
            html.append(value != null ? (" value=\"" + value + "\"") : "");
            html.append(id != null ? (" id=\"" + id + "\"") : "");
            if (!checked) html.append(" checked");
            html.append(">");
            getJspContext().getOut().print(html.toString());
            value = null;
            name = null;
            id = null;
            checked = true;
        } catch (IOException e) {
            throw new JspTagException("CheckboxTag: " + e.getMessage());
        }
    }
}
