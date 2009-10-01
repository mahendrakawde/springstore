/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: InputTag.java,v 1.3 2004/07/20 01:08:22 yutayoshida Exp $ */

package com.sun.j2ee.blueprints.taglibs.smart;

import java.io.IOException;
import java.util.*;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.*;

/**
 * HTML 'input' tag. Use with NameTag and ValueTag.
 */
public class InputTag extends BodyTagSupport {

    private String value;
    private String name;
    private String type;
    private int size;
    private String validation;
    private int maxlength;
    private String cssClass;
    private String id;
    private String onClick;

    public void setCssClass(String c) { cssClass = c; }
    
    public void setType(String t) { type = t; }

    public void setSize(int s) { size = s; }
    
    public void setName(String n) { name = n; }

    public void setOnClick(String oc) { onClick = oc; }
    
    public void setValue(String v) { value = v; }

    public void setValidation(String v) { validation = v; }
    
    public void setMaxlength(int ml) { maxlength = ml; }
    
    public void setId(String id) {this.id = id;}


    public int doEndTag() throws JspTagException { 
        try {
            FormTag tag 
                = (FormTag) findAncestorWithClass(this, FormTag.class);
            if (tag != null && validation != null) {
                tag.putValidatedField(name, type);
            }
            StringBuffer html = new StringBuffer();
            html.append("<input");
            html.append(size > 0 ? (" size=\"" + size + "\"") : "");
            html.append(" type=\"" + type + "\"");
            html.append(name != null ? (" name=\"" + name + "\"") : "");
            html.append(value != null ? (" value=\"" + value + "\"") : "");
            html.append(cssClass != null  ? (" class=\"" + cssClass + "\"")  : "");
            html.append(id != null  ? (" id=\"" + id + "\"")  : "");
            html.append(onClick != null  ? (" onClick=\"" + onClick + "\"")  : "");
            html.append(maxlength > 0   ? (" maxlength=\"" + maxlength + "\"")  : "");
            html.append(">");
            pageContext.getOut().print(html.toString());
            type = null;
            name = null;
            onClick = null;
            value = null;
            cssClass = null;
            validation = null;
            id = null;
            size = 0;
            maxlength = 0;
            return EVAL_PAGE;
        } catch (IOException e) {
            throw new JspTagException("InputTag: " + e.getMessage());
        }
    }
}

