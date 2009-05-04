/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: JNDINames.java,v 1.2 2004/05/26 00:06:23 inder Exp $ */

package com.sun.j2ee.blueprints.customer.dao;

/**
 * This class is the central location to store the internal
 * names of various entities, and these internal names are later 
 * mapped to the JNDI names in the deployment environment. Any change 
 * here should also be reflected in the deployment descriptors.
 */
public class JNDINames {

      
    private JNDINames() { } //Prevents instantiation

    public static final String CUSTOMER_DATASOURCE =
         "java:comp/env/customer/CustomerDataSource";
    
    //ENV Entry for name of DAO class to use
    public static final String ACCOUNT_DAO_CLASS =
         "java:comp/env/dao/customer/AccountDAOClass";
}
