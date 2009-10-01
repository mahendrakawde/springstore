/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: LodgingPOIntf.java,v 1.3 2004/05/26 00:06:42 inder Exp $ */

package com.sun.j2ee.blueprints.lodgingsupplier.powebservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface for the web service endpoint of lodging supplier that receives PO
 */
public interface LodgingPOIntf extends Remote {
    public String submitLodgingReservationDetails(String poXmlString)
      throws InvalidOrderException, OrderSubmissionException, RemoteException;
}
