/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: ClientStateValueTag.java,v 1.3 2004/07/20 01:08:22 yutayoshida Exp $ */

package com.sun.j2ee.blueprints.taglibs.smart;

import java.io.IOException;
import java.util.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;


/**
 * A Parameter Value for the ClientStateValueTag
 */
public class ClientStateValueTag extends SimpleTagSupport {

    private String name= null;
    private String value = null;
    
    public ClientStateValueTag() {
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setValue(String value) {
        this.value = value;
    }

    public void doTag() throws JspTagException {
        ClientStateTag parentTag  =
                  (ClientStateTag) findAncestorWithClass(this, ClientStateTag.class);
        parentTag.putParameter(name, value);
        name = null;
        value = null;
    }
}

