
/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: TransitionDelegateFactory.java,v 1.2 2004/05/26 00:07:08 inder Exp $ */

package com.sun.j2ee.blueprints.processmanager.transitions;

/**
 * Used to get the required type of TransitionDelegate
 */
public class TransitionDelegateFactory {


  public TransitionDelegateFactory() { }

  public TransitionDelegate getTransitionDelegate(String className) throws TransitionException {
    TransitionDelegate td = null;
    try { 
      td = (TransitionDelegate)Class.forName(className).newInstance();
    } catch(Exception e) {
      throw new TransitionException(e);
    }
    return td;
  }

}

