/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: WSClientFactory.java,v 1.3 2004/05/26 00:07:02 inder Exp $ */

package com.sun.j2ee.blueprints.opc.webservicebroker.requestor;

import javax.naming.NamingException;
import javax.naming.InitialContext;

public class WSClientFactory {

    public static WSClient getWSClient(String providerName) {

        WSClient client = null;
        try {
            InitialContext ic = new InitialContext();
            String className = (String) ic.lookup(providerName);
            client = (WSClient) Class.forName(className).newInstance();
        } catch (NamingException ne) {
      System.err.println(ne);
        } catch (Exception se) {
      System.err.println(se);
        }
        return client;
    }
}

