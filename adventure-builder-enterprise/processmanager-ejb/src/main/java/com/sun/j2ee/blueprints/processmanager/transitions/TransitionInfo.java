
/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: TransitionInfo.java,v 1.2 2004/05/26 00:07:08 inder Exp $ */

package com.sun.j2ee.blueprints.processmanager.transitions;

import java.util.Collection;

/**
  * Encapsulates the parameters passed to the transition delegates
  */
public class TransitionInfo {

  private String xmlMessage;
  private Collection xmlMessageBatch;


  public TransitionInfo(String xmlMessage) {
    this.xmlMessage = xmlMessage;
  }

  public TransitionInfo(Collection xmlMessageBatch) {
    this.xmlMessageBatch = xmlMessageBatch;
  }

  public TransitionInfo(String xmlMessage, Collection xmlMessageBatch) {
    this.xmlMessage = xmlMessage;
    this.xmlMessageBatch = xmlMessageBatch;
  }
 

  public String getXMLMessage() {
    return xmlMessage;
  }

  public Collection getXMLMessageBatch() {
    return xmlMessageBatch;
  }
      
}

