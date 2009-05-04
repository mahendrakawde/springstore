/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: InvalidOrderException.java,v 1.2 2004/05/26 00:06:06 inder Exp $ */

package com.sun.j2ee.blueprints.airlinesupplier.powebservice;

public class InvalidOrderException  extends Exception {
  private String message = "";

  public InvalidOrderException(String message) {
    super(message);
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
