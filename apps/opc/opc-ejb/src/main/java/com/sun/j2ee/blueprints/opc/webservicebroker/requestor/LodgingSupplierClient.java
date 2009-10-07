/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: LodgingSupplierClient.java,v 1.6 2005/03/08 00:18:36 smitha Exp $ */

package com.sun.j2ee.blueprints.opc.webservicebroker.requestor;

import com.sun.j2ee.blueprints.opc.JNDINames;

public class LodgingSupplierClient extends AbstractWSClient {

    protected String sendRequestInternal(String xmlDoc) throws Exception {
      LodgingPOIntf port=(LodgingPOIntf) sl.getPort(JNDINames.LODGING_SERVICE_NAME, LodgingPOIntf.class);
      return port.submitLodgingReservationDetails(xmlDoc);
    }
}
