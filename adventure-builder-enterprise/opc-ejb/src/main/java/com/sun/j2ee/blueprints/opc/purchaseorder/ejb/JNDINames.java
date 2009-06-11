/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: JNDINames.java,v 1.2 2004/05/26 00:06:57 inder Exp $ */
package com.sun.j2ee.blueprints.opc.purchaseorder.ejb;

/**
 * JNDI names of various EJBs
 */
public class JNDINames {

    // JNDI name of contact info  EJB
    public static final String CINFO_EJB = "java:comp/env/ejb/local/ContactInfo";

    // JNDI name of address  EJB
    public static final String ADDR_EJB = "java:comp/env/ejb/local/Address";
  
   // JNDI name of credit card  EJB
    public static final String CCARD_EJB = "java:comp/env/ejb/local/CreditCard";

   // JNDI name of lodging  EJB
    public static final String LDG_EJB = "java:comp/env/ejb/local/Lodging";

   // JNDI name of transportatioion  EJB
    public static final String TRPN_EJB = "java:comp/env/ejb/local/Transportation";

   // JNDI name of activity  EJB
    public static final String ACTY_EJB = "java:comp/env/ejb/local/Activity";




}

