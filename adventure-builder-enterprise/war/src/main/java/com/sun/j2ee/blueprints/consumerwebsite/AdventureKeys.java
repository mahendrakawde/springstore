/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: AdventureKeys.java,v 1.2 2004/05/26 00:06:16 inder Exp $ */

package com.sun.j2ee.blueprints.consumerwebsite;

import com.sun.j2ee.blueprints.waf.controller.web.util.WebKeys;
/**
 * This interface contains all the keys that are used to
 * store data in the different scopes of web-tier. These
 * values are the same as those used in the JSP
 * pages (useBean tags).
 */
public class AdventureKeys extends WebKeys {

    private AdventureKeys() {} //prevent instanciation
    // JSP accessible web keys
    public static final String CART = "cart";
    public static final String CUSTOMER_BEAN = "customerBean";
    public static final String CHECKOUT_BEAN = "checkoutBean";
    public static final String CART_BEAN = "cartBean";
    public static final String MISSING_FORM_DATA_EXCEPTION_KEY = "missingFormData";
    public static final String SIGN_ON_TEMP_USERNAME = "com.sun.j2ee.blueprints.adventure.SIGN_ON_TEMP_USERNAME";
    public static final String SIGN_ON_TEMP_PASSWORD = "com.sun.j2ee.blueprints.adventure.SIGN_ON_TEMP_PASSWORD";
    public static final String SIGN_ON_FACADE = "com.sun.j2ee.blueprints.adventure.SIGN_ON_FACADE";
    public static final String CUSTOMER_FACADE = "com.sun.j2ee.blueprints.adventure.CUSTOMER_FACADE";
    public static final String CATALOG_FACADE = "com.sun.j2ee.blueprints.adventure.CATALOG_FACADE";
    
    // internal keys
    public static final String PO_SERVICE = 
         "java:comp/env/service/OpcPurchaseOrderService";
}

