/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: Account.java,v 1.2 2004/05/26 00:06:20 inder Exp $ */

package com.sun.j2ee.blueprints.customer;

/**
 * This class provides methods to view account
 * information for a particular account.
 * This class is immutable
 */
public class Account  implements java.io.Serializable {

    protected String userId;
    protected ContactInformation info;
   
    public Account () {}

    public Account (String userId, ContactInformation info) {
        this.userId = userId;
        this.info = info;
    }

    public String getUserId() {
        return userId;
    }

    public ContactInformation getContactInformation() {
        return info;
    }

    public String toString() {
        String ret = null;
        ret = "userId = " + userId + "\n";
        ret += "contact info = " + info.toString() + "\n";
        return ret;
    }

}
