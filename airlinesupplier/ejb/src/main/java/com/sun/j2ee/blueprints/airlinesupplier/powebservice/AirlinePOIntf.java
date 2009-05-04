/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: AirlinePOIntf.java,v 1.3 2004/05/26 00:06:06 inder Exp $ */

package com.sun.j2ee.blueprints.airlinesupplier.powebservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface for the web service endpoint of airline supplier that receives PO
 */
public interface AirlinePOIntf extends Remote {
    public String submitAirlineReservationDetails(String poXmlString)
      throws InvalidOrderException, OrderSubmissionException, RemoteException;
}
