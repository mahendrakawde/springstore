/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: SelectTag.java,v 1.3 2004/07/20 01:08:22 yutayoshida Exp $ */

package com.sun.j2ee.blueprints.taglibs.smart;

import java.io.*;
import java.util.*;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

/**
 * HTML 'select' tag. 
 * By default @param isEditable is set to true. This tag contains
 * a set of OptionTags in it's body which while look up this tag
 * and add the options. This tag allows you to just display the
 * OptionTag that is selcted when @param isEditable is set to false
 * otherwise the selected item is pre-selected in the page.
 */
public class SelectTag extends BodyTagSupport {

    private ArrayList options = null;
    private String selectedValue = null;
    private int size = 0;
    private String name = null;
    private String onChange = null;
    private boolean isEditable = true;
    private String id = null;

    public void setSelectedValue(String sv) { selectedValue = sv; }

    public void setSize(int s) { size = s; }
    
    public void setName(String n) { name = n; }
    
    public void setOnChange(String oc) { onChange = oc;}

    public void setEditable(boolean e) { isEditable = e; }
    
   public void setId(String id) { this.id = id; }

    public void putOption(String value, String text) {
        options.add(new OptionItem(value, text));
    }

    public int doStartTag() throws JspTagException {
        options = new ArrayList();
        return EVAL_BODY_BUFFERED;
    }

    public int doEndTag() throws JspTagException { 
        try {
            StringBuffer html = new StringBuffer();
            
            if (isEditable) {
                html.append("<select");
                if (onChange != null) {
                     html.append(" onChange=\"" + onChange + "\" ");
                }
                html.append(id != null ? (" id=\"" + id + "\"") : "");
                html.append(size > 0 ? (" size=\"" + size + "\"") : "");
                html.append(" name=\"" + name + "\">");
                Iterator it = options.iterator();
                while (it.hasNext()) {
                    OptionItem item = (OptionItem) it.next();
                    String value = item.getName();
                    String text = item.getValue();
                    html.append("<option value=\"" + value + "\"");
                    html.append(value.equals(selectedValue)  ? " selected>" : ">");
                    html.append(text);
                    html.append("</option>");
                }
                html.append("</select>");
            } else {
                OptionItem item = getItem(selectedValue);
                if (item != null) {
                    html.append(item.getValue());
                }
            }
            pageContext.getOut().print(html.toString());
            options = null;
            name = null;
            onChange = null;
            selectedValue = null;
            id = null;
            size = 0;
            isEditable = true;
            return EVAL_PAGE; 
        } catch (IOException e) {
            throw new JspTagException("SelectTag: " + e.getMessage());
        }
    }
    
    private OptionItem getItem(String key) {
          Iterator it = options.iterator();
          while (it.hasNext()) {
              OptionItem item = (OptionItem) it.next();
              if (item.getName().equals(key)) return item;
          }
          return null;
        }
    /*
     *  Holds an Option for this tag
     */
    private class OptionItem {
        private String name;
        private String value;
        
        public OptionItem (String name, String value) {
            this.name = name;
            this.value = value;            
        }
        
        public String getName() {
            return name;
        }
        
        public String getValue() {
            return value;
        }
    }
}

