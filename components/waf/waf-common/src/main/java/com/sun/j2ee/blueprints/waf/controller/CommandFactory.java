/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: CommandFactory.java,v 1.2 2004/05/26 00:07:25 inder Exp $ */

package com.sun.j2ee.blueprints.waf.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class provides a single point from which
 * com.sun.j2ee.blueprints.waf.controller.Command objects
 * are accessed. A Command is mapped to each
 * com.sun.j2ee.blueprints.waf.controller.Event object.
 * The mapping between the objects is maintained in the 
 * mappings.xml file and set by the 
   com.sun.j2ee.blueprints.waf.controller.RequestProcessor
 */
public final class CommandFactory implements java.io.Serializable {

	private static final Logger logger = LoggerFactory.getLogger(CommandFactory.class);
	
    private static Map commandMap = new HashMap();

    private CommandFactory() {} // prevent instanciation

    public static Command getCommand(Event ev) {
        String commandName = ev.getCommandClassName();
        Command command = null;
        if (commandName  != null) {
            try {
                 if (commandMap.get(commandName) != null) {
                    command = (Command)commandMap.get(commandName);
                 } else {
                     command = (Command)Class.forName(commandName).newInstance();
                     commandMap.put(commandName, command);
                 }
            } catch (Exception ex) {
            	logger.warn("CommandFactory: error loading command {}", commandName);
            	logger.error(ex.getMessage(), ex);
            }
        }
        return command;
    }
}

