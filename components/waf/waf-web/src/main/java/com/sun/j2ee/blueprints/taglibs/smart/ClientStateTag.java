/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: ClientStateTag.java,v 1.4 2004/07/19 22:40:31 yutayoshida Exp $ */

package com.sun.j2ee.blueprints.taglibs.smart;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.codec.base64.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.j2ee.blueprints.waf.controller.web.util.WebKeys;

/**
 * This tag caches state in the page and provides a button or
 * image link within a form with the current page parameters
 * and the page request attributes by encoding them as hidden
 * form variables that are serialized using Base 64 Encoded Strings.
 *
 * This tag when used along with a front controller such as the
 * one provided in the WAF can utilize a flow handler to forward
 * a request page to a page with the same attributes it recieved
 * when the original request was made.
 */
public class ClientStateTag extends BodyTagSupport {

	private final Logger logger = LoggerFactory.getLogger(ClientStateTag.class);
	
    private String altText = "";
    private String buttonText;
    private String imageURL;
    private String cacheId;
    private String targetURL;
    private boolean encodeRequestAttributes = true;
    private boolean encodeRequestParameters = true;
    private Class serializableClass = null;
    private Map parameters = null;

    public void setId(String cacheId) {
        this.cacheId = cacheId;
    }

    public void setAlt(String altText) {
        this.altText = altText;
    }

    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setTargetURL(String targetURL) {
        this.targetURL = targetURL;
    }

    public void setEncodeRequestAttributes(boolean encodeRequestAttributes) {
        this.encodeRequestAttributes = encodeRequestAttributes;
    }

    public void setEncodeRequestParameters(boolean encodeRequestParameters) {
        this.encodeRequestAttributes = encodeRequestParameters;
    }

    public int doStartTag() throws JspTagException {
        return EVAL_BODY_BUFFERED;
    }
    
    public int doEndTag() throws JspTagException {
         if (imageURL == null && buttonText == null) {
            throw new JspTagException("ClientStateTag error: either an " +
                                                        "imageURL or buttonText attribute must be specified.");
        }
        HttpServletRequest request =
            (HttpServletRequest) pageContext.getRequest();
        StringBuffer buffer = new StringBuffer();
        buffer.append("<form method=\"POST\" action=\"" + targetURL + "\">");
        // insert any parameters that may have been added via sub tags
        if (parameters != null) {
            Iterator it = parameters.keySet().iterator();
            // put the request attributes stored in the session in the request
            while (it.hasNext()) {
                String key = (String)it.next();
                String value = (String)parameters.get(key);
                buffer.append(" <input type=\"hidden\" name=\"" +
                                          key + "\" value=\"" + value + "\" />");
            }
        }
        String fullURL = request.getRequestURI();
        // find the url that sent us this page
        String targetURL = null;
        int lastPathSeparator = fullURL.lastIndexOf("/") + 1;
        if (lastPathSeparator != -1) {
           targetURL = fullURL.substring(lastPathSeparator, fullURL.length());
        }
        buffer.append(" <input type=\"hidden\" name=\"referring_URL\"" +
                               "value=\"" + targetURL +
                               "\">");
        String referringScreen =
                (String)request.getSession().getAttribute(WebKeys.PREVIOUS_SCREEN);
        buffer.append(" <input type=\"hidden\" name=\"referring_screen\"" +
                               "value=\"" + referringScreen +
                               "\">");
        buffer.append(" <input type=\"hidden\" name=\"cacheId\"" +
                               "value=\"" + cacheId +  "\">");
        // check the request for previous parameters
        Map params = (Map)request.getParameterMap();
        if (!params.isEmpty() && encodeRequestParameters) {
            Iterator it = params.entrySet().iterator();
            // copy in the request parameters stored
            while (it.hasNext()) {
            	Entry entry = (Entry) it.next();
                String key = (String) entry.getKey();
                if (!key.startsWith(cacheId)) {
                    String[] values = (String[]) entry.getValue();
                    String valueString = values[0];
                    buffer.append(" <input type=\"hidden\" name=\"" +
                                             key + "\" value=\"" +
                                            valueString  +
                                            "\" />");
                }
            }
        }
        /**
          *  Now serialize the request attributes into the page (only sealizable objects are going
          *  to be processed).
          */
        if (encodeRequestAttributes) {
                // put the request attributes into tattributres
                Enumeration enumeration = request.getAttributeNames();
                while (enumeration.hasMoreElements()) {
                     String key = (String)enumeration.nextElement();
                      // check if we have already serialized the items
                      // also don't serialize javax items because
                     if (!key.startsWith(cacheId) &&
                         !key.startsWith("javax.servlet")) {
                         Object value = request.getAttribute(key);
                         if (serializableClass == null) {
                            try {
                                 serializableClass = Class.forName("java.io.Serializable");
                             } catch (java.lang.ClassNotFoundException cnf) {
                                 logger.error("ClientStateTag caught: ", cnf);
                             }
                         }
                         // check if seralizable
                         if (serializableClass.isAssignableFrom(value.getClass())) {
                             try {
                                 ByteArrayOutputStream bos = new ByteArrayOutputStream();
                                 ObjectOutput out = new ObjectOutputStream(bos);
                                 out.writeObject(value);
                                 out.close();
                                 buffer.append(" <input type=\"hidden\" name=\"" + cacheId +
                                        "_attribute_" + key + "\" value=\"" +
                                       new String(Base64.encode(bos.toByteArray()), "ISO-8859-1")  + "\" />");
                         } catch (java.io.IOException iox) {
                             logger.error("ClientStateTag caught: ", iox);
                         }
                     } else {
                    	 logger.info(key + " not to Serializeable");
                     }
                 }
            }
        }// end get attributes
        // now put the link in
        if (imageURL != null) {
            buffer.append(" <input alt=\"" + altText+ "\" type=\"image\" " +
                           "src=\"" + imageURL + "\"/>");
        } else {
            buffer.append(" <input alt=\"" + altText + "\"  type=\"submit\" " +
                           "value=\"" + buttonText + "\"/>");
        }
        buffer.append("</form>");
        // write the output to the output stream
        try {
               JspWriter out = pageContext.getOut();
                out.print(buffer.toString());
        } catch (IOException ioe) {
            logger.error("ClientStateTag: Problems with writing...", ioe);
        }
        // reset everything
        parameters = null;
        altText = "";
        buttonText = null;
        imageURL = null;
        cacheId = null;
        targetURL = null;
        encodeRequestAttributes = true;
        encodeRequestParameters = true;
        serializableClass = null;
        return EVAL_PAGE;
    }

    public void putParameter(String key, String value) {
        if (parameters == null) parameters = new HashMap();
        parameters.put(key,value);
    }
}
