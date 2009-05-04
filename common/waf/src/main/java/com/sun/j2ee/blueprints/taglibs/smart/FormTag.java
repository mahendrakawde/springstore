/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: FormTag.java,v 1.3 2004/07/20 01:08:22 yutayoshida Exp $ */

package com.sun.j2ee.blueprints.taglibs.smart;

import java.io.IOException;
import java.util.*;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

/**
 * This tag provides the web page developer with the ability to
 *  required form content is provided for specified form fields
 * using JavaScript.
 */
public class FormTag extends BodyTagSupport {

    private Map validatedFields = new TreeMap();
    private String name;
    private String action;
    private String method;
    private String formHTML;

    public void putValidatedField(String fieldName, String fieldType) {
        validatedFields.put(fieldName, fieldType);
    }

    public void setName(String n) { name = n; }
    
    public void setAction(String a) { action = a; }
    
    public void setMethod(String m) { method = m; }

    public int doStartTag() throws JspTagException {
        return EVAL_BODY_BUFFERED;
    }

    public int doAfterBody() throws JspTagException { 
        BodyContent bc = getBodyContent();
        formHTML = bc.getString();
        bc.clearBody();
        return SKIP_BODY; 
    }

    public int doEndTag() throws JspTagException {
        try {
            StringBuffer html = new StringBuffer();
            StringBuffer javaScript = new StringBuffer();

            javaScript.append("<script language=\"JavaScript\">\n");
            javaScript.append("function validate_" + name +"() {\n");
            javaScript.append("    var ret = true;\n");
            for (Iterator it = validatedFields.keySet().iterator();
                 it.hasNext(); ) {
                String fieldName = (String) it.next();
                String fieldType = (String) validatedFields.get(fieldName);
                javaScript.append("    if (window.document.");
                javaScript.append(name + ".");
                javaScript.append(fieldName + ".");
                javaScript.append("value");
                javaScript.append(" ==");
                javaScript.append(" \"\"");
                javaScript.append(") {\n");
                javaScript.append("        alert(\"" + fieldName 
                                  + " is empty.\");\n");
                javaScript.append("        ret = false;\n");
                javaScript.append("    }\n");
            }
            javaScript.append("    return ret;\n");
            javaScript.append("}\n");
            javaScript.append("</script>\n");

            html.append("<form");
            html.append(" name=\"" + name +"\"");
            html.append(" action=\"" + action + "\"");
            html.append(" method=\"" + method + "\"");
            html.append(" onSubmit=\"return validate_" + name + "();\"");
            html.append(">\n");
            html.append(formHTML);
            html.append("</form>");
            pageContext.getOut().print(javaScript.toString());
            pageContext.getOut().print(html.toString());
            return EVAL_PAGE;
        } catch (IOException e) {
            throw new JspTagException("FormTag: " + e.getMessage());
        } finally {
            // force the tag values to be reset
            validatedFields = new TreeMap();
            name = null;
            action = null;
            method = null;
            formHTML = null;
        }
    }
}
