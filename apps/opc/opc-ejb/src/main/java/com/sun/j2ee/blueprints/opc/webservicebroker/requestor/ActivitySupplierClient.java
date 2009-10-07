/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: ActivitySupplierClient.java,v 1.4 2005/03/08 00:18:36 smitha Exp $ */

package com.sun.j2ee.blueprints.opc.webservicebroker.requestor;

import com.sun.j2ee.blueprints.opc.JNDINames;

public class ActivitySupplierClient extends AbstractWSClient {


	protected String sendRequestInternal(String xmlDoc) throws Exception {
		ActivityPOIntf port = (ActivityPOIntf) sl.getPort(JNDINames.ACTIVITY_SERVICE_NAME, ActivityPOIntf.class);
		return port.submitActivityReservationDetails(xmlDoc);
	}
}
