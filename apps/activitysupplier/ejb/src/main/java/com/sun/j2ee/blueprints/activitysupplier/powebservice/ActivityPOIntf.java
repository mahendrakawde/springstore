/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: ActivityPOIntf.java,v 1.3 2004/05/26 00:05:58 inder Exp $ */

package com.sun.j2ee.blueprints.activitysupplier.powebservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface for the web service endpoint of activity supplier that receives PO
 */
public interface ActivityPOIntf extends Remote {
    public String submitActivityReservationDetails(String poXmlString)
     throws InvalidOrderException, OrderSubmissionException, RemoteException;
}
