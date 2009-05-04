/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: EJBControllerLocalEJB.java,v 1.2 2004/05/26 00:07:27 inder Exp $ */

package com.sun.j2ee.blueprints.waf.controller.ejb;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

// waf imports
import com.sun.j2ee.blueprints.waf.controller.Event;
import com.sun.j2ee.blueprints.waf.controller.EventResponse;
import com.sun.j2ee.blueprints.waf.controller.EventException;
import com.sun.j2ee.blueprints.waf.controller.AppException;

/** 
 * Session Bean implementation for EJBController EJB.
 * See the StateMachine for more details.
 */
public class EJBControllerLocalEJB implements SessionBean {
    
    protected StateMachine sm;
    protected SessionContext sc;

    public EJBControllerLocalEJB() {}


    public void ejbCreate() {
        sm = new StateMachine(this, sc);
    }

    /** returns an EventResponse */

    public EventResponse processEvent(Event ev) 
        throws EventException {
          return (sm.processEvent(ev));
    }
    
    public void setSessionContext(SessionContext sc) {
  this.sc = sc;
    }

    public void ejbRemove() {
        sm = null;
    }

    public void ejbActivate() {}

    public void ejbPassivate() {}
}


