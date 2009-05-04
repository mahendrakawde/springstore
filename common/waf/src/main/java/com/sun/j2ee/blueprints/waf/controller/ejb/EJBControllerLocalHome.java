/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: EJBControllerLocalHome.java,v 1.2 2004/05/26 00:07:27 inder Exp $ */

package com.sun.j2ee.blueprints.waf.controller.ejb;

import javax.ejb.EJBLocalHome;
import javax.ejb.CreateException;

/** 
 * The Home interface for EJBControllerHome 
 */
public interface EJBControllerLocalHome extends EJBLocalHome {
    public EJBControllerLocal create() 
  throws  CreateException;
}

