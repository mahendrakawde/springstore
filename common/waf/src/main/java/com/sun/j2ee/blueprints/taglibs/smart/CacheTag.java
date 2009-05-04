/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: CacheTag.java,v 1.3 2004/07/20 01:08:21 yutayoshida Exp $ */

package com.sun.j2ee.blueprints.taglibs.smart;

import java.io.IOException;
import java.util.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.http.HttpServletRequest;

/**
 * A caching tag.
 */
public class CacheTag extends BodyTagSupport {

    private String scope;
    private String name;
    private long duration;
    private Entry entry;
    private String key;

    public void setScope(String s) { scope = s; }

    public void setName(String n) { name = n; }

    public void setDuration(long d) { duration = d; }

    public int doStartTag() throws JspTagException {
        HttpServletRequest req = 
            ((HttpServletRequest) pageContext.getRequest());

        key = req.getRequestURL().toString() 
            + '#' + name
            + '?' + req.getQueryString();

        if ("context".equals(scope)) {
            entry = (Entry) pageContext.getServletContext().getAttribute(key);
        } else if ("session".equals(scope)) {
            entry = (Entry) pageContext.getSession().getAttribute(key);
        }else if ("request".equals(scope)) {
            entry = (Entry) pageContext.getRequest().getAttribute(key);
        } else if ("page".equals(scope)) {
            entry = (Entry) pageContext.getAttribute(key);
        }
        if (entry != null && entry.isExpired()) {
            entry = null;
        }
        return (entry == null) ? EVAL_BODY_BUFFERED : SKIP_BODY;
    }

    public int doEndTag() throws JspTagException {
        if (entry == null) {
            BodyContent bc = getBodyContent();
            if (bc != null) {
                String content = bc.getString();
                entry = new Entry(content, duration);
                
                if ("context".equals(scope)) {
                    pageContext.getServletContext().setAttribute(key, entry);
                }
                else if ("session".equals(scope)) {
                    pageContext.getSession().setAttribute(key, entry);
                }
                else if ("request".equals(scope)) {
                    pageContext.getRequest().setAttribute(key, entry);
                }
                else if ("page".equals(scope)) {
                    pageContext.setAttribute(key, entry);
                }
                
                try {
                    JspWriter out = bc.getEnclosingWriter();
                    out.print(content);
                }
                catch (IOException ioe) {
                    System.err.println("Problems with writing...");
                }
            }
        } else {
            try {
                JspWriter out = pageContext.getOut();
                out.print(entry.getContent());
            }
            catch (IOException ioe) {
                System.err.println("Problems with writing...");
            }
        }
        //reset everything
        scope = null;
        name = null;
        duration = 0;
        entry = null;
        key = null;
        return EVAL_PAGE;
    }

    /*
     * An entry in the cache.
     */
    class Entry {

        private String content;
        private long timestamp;
        private long duration;

        public Entry(String content, long duration) {
            this.content = content;
            timestamp = System.currentTimeMillis();
            this.duration = duration;
        }

        public String getContent() { return content; }

        public boolean isExpired() {
            long currentTime = System.currentTimeMillis();
            return ((currentTime - timestamp) > duration);
        }
    }
}
