
/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: TransitionException.java,v 1.2 2004/05/26 00:07:08 inder Exp $ */

package com.sun.j2ee.blueprints.processmanager.transitions;


/**
 * This class implements an exception which can wrapped a lower-level exception.
 *
 */
public class TransitionException extends Exception {
  private Exception exception;

  /**
   * Creates a new TransitionException wrapping another exception, and with a detail message.
   * @param message the detail message.
   * @param exception the wrapped exception.
   */
  public TransitionException(String message, Exception exception) {
    super(message);
    this.exception = exception;
    return;
  }

  /**
   * Creates a TransitionException with the specified detail message.
   * @param message the detail message.
   */
  public TransitionException(String message) {
    this(message, null);
    return;
  }

  /**
   * Creates a new TransitionException wrapping another exception, and with no detail message.
   * @param exception the wrapped exception.
   */
  public TransitionException(Exception exception) {
    this(null, exception);
    return;
  }

  /**
   * Gets the wrapped exception.
   *
   * @return the wrapped exception.
   */
  public Exception getException() {
    return exception;
  }

  /**
   * Retrieves (recursively) the root cause exception.
   *
   * @return the root cause exception.
   */
  public Exception getRootCause() {
    if (exception instanceof TransitionException) {
      return ((TransitionException) exception).getRootCause();
    }
    return exception == null ? this : exception;
  }

  public String toString() {
    if (exception instanceof TransitionException) {
      return ((TransitionException) exception).toString();
    }
    return exception == null ? super.toString() : exception.toString();
  }
}
