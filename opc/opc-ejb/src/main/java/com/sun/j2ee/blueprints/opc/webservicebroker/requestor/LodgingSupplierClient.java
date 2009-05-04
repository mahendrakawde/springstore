/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: LodgingSupplierClient.java,v 1.6 2005/03/08 00:18:36 smitha Exp $ */

package com.sun.j2ee.blueprints.opc.webservicebroker.requestor;

import javax.xml.rpc.*;
import javax.naming.*;

import com.sun.j2ee.blueprints.opc.JNDINames;

public class LodgingSupplierClient implements WSClient {

    public String sendRequest(String xmlDoc) {
  String ret = null;
  try {
      InitialContext ic = new InitialContext();
      LodgingPurchaseOrderService svc = (LodgingPurchaseOrderService) 
    ic.lookup(JNDINames.LODGING_SERVICE_NAME);
      LodgingPOIntf port=(LodgingPOIntf)svc.getPort(LodgingPOIntf.class);
      ret = port.submitLodgingReservationDetails(xmlDoc);
  } catch(Exception ex) {
            /* 
             *  Advanced error handling will be done for a later release, with 
             * the broker placing an error message in the work flow manager
             * queue so that the work flow manager can again call the broker
             * to send the PO to the proper supplier
             */ 
            System.err.println(ex);
  }
  return ret;
    }
}
