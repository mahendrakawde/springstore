/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: ProcessManagerLocalHome.java,v 1.2 2004/05/26 00:07:07 inder Exp $ */

package com.sun.j2ee.blueprints.processmanager.ejb;


import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.ejb.DuplicateKeyException;

/**
 * The home interface of the ProcessManager Entity EJB.
 */

public interface ProcessManagerLocalHome extends javax.ejb.EJBLocalHome {

    public ProcessManagerLocal create() throws CreateException;
}
