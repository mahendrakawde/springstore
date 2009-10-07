/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: AirlineSupplierClient.java,v 1.4 2005/03/08 00:18:36 smitha Exp $ */

package com.sun.j2ee.blueprints.opc.webservicebroker.requestor;

import com.sun.j2ee.blueprints.opc.JNDINames;

public class AirlineSupplierClient extends AbstractWSClient {

	
	
    protected String sendRequestInternal(String xmlDoc) throws Exception {
      AirlinePOIntf port=(AirlinePOIntf) sl.getPort(JNDINames.AIRLINE_SERVICE_NAME, AirlinePOIntf.class); 
      return port.submitAirlineReservationDetails(xmlDoc);
    }
}
