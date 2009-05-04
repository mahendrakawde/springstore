/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: TransitionDelegate.java,v 1.2 2004/05/26 00:07:08 inder Exp $ */


package com.sun.j2ee.blueprints.processmanager.transitions;


/**
  * This is the interface that all transition delegates must implement
  */
public interface TransitionDelegate {

   public void setup() throws TransitionException;

   public void doTransition(TransitionInfo info) throws TransitionException;
      
}

