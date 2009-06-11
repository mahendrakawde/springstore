/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: BrokerServiceIntf.java,v 1.2 2004/05/26 00:07:01 inder Exp $ */

package com.sun.j2ee.blueprints.opc.webservicebroker.provider;

import java.rmi.Remote;
import java.rmi.RemoteException;
import com.sun.j2ee.blueprints.opc.serviceexceptions.*;

public interface BrokerServiceIntf extends Remote {
    public String submitDocument(String xmlDoc) 
       throws InvalidDocumentException, ProcessingException, RemoteException;
}

