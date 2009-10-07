/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: StateMachine.java,v 1.2 2004/05/26 00:07:27 inder Exp $ */

package com.sun.j2ee.blueprints.waf.controller.ejb;


import java.util.HashMap;
import java.util.Map;

import javax.ejb.SessionContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.j2ee.blueprints.waf.controller.Command;
import com.sun.j2ee.blueprints.waf.controller.CommandFactory;
import com.sun.j2ee.blueprints.waf.controller.Event;
import com.sun.j2ee.blueprints.waf.controller.EventException;
import com.sun.j2ee.blueprints.waf.controller.EventResponse;

/**
 * This class is a responsible for processing Events recieved from the 
 * client tier. Af part of the WAF framework the events are generated
 * by web actions.
 * 
 * The State Machine ties all EJB components together dynamically at
 * runtime thus providing support for reusable components.
 * 
 * This class should not be updated to handle various event types.
 * This class will use ActionHandlers to handle events that require
 * processing beyond the scope of this class.
 *
 * The mapping of the event names to handlers is mangaged by the JNDI
 * key contained in the Event:getEventName() which is looked up from
 * an environment entry located in the EJB Deployment descriptor of the
 * EJBClientController. A second option to event handling is to do so
 * in the XML file.
 * 
 * State may be stored in the attributeMap
 *
 * 
 */
public class StateMachine implements java.io.Serializable {
    
	private final Logger logger = LoggerFactory.getLogger(StateMachine.class);
	
    private EJBControllerLocalEJB ccejb;
    private Map attributeMap;
    private SessionContext sc;

    public StateMachine(EJBControllerLocalEJB ccejb, SessionContext sc) {
        this.ccejb = ccejb;
        this.sc = sc;
        attributeMap = new HashMap();
    }

    public EventResponse processEvent(Event ev) throws EventException {
        EventResponse response = null;
        EJBCommand ejbCommand = null;
        Command command = CommandFactory.getCommand(ev);;
        try {
            // try to cast the command into an EJBCommand
            if (command != null) ejbCommand = (EJBCommand)command;
        } catch (ClassCastException cx) {
            logger.info("StateMachine: Command not EJBCommand", cx);
        }
        // if we have an ejb command then intialize it like one
        // otherwise treat the command as is.
        if (ejbCommand != null) {
            ejbCommand.init(this);
            ejbCommand.doStart();
            response = ejbCommand.perform(ev);
            ejbCommand.doEnd();
        } else {
            command.doStart();
            response = command.perform(ev);
            command.doEnd();
        }
        return response;
    }
    
    public void setAttribute(String key, Object value) {
        attributeMap.put(key, value);
    }

    public Object getAttribute(String key) {
        return attributeMap.get(key);
    }

    public EJBControllerLocalEJB getEJBController() {
        return ccejb;
    }

    public SessionContext getSessionContext() {
        return sc;
    }

}

