/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: JMSUtils.java,v 1.4 2004/07/30 23:59:20 inder Exp $ */

package com.sun.j2ee.blueprints.opc.utils;

import javax.jms.*;

import com.sun.j2ee.blueprints.servicelocator.*;
import com.sun.j2ee.blueprints.servicelocator.ejb.*;
import com.sun.j2ee.blueprints.opc.JNDINames;

public class JMSUtils {

    public static boolean sendMessage(String jmsDest, String property, 
              String value, String xmlDoc) {
  Connection jmsCon = null;
  try {
      ServiceLocator sl = new ServiceLocator(); 
      ConnectionFactory jmsConFactory = sl.getJMSConnectionFactory(JNDINames.OPC_QUEUE_CONNECTION_FACTORY);
      Destination target = sl.getJMSDestination(jmsDest);
      jmsCon = jmsConFactory.createConnection();
      Session jmsSession = jmsCon.createSession(true,0);
      MessageProducer jmsSender = jmsSession.createProducer(target);
      TextMessage message = jmsSession.createTextMessage(xmlDoc);
      message.setStringProperty(property, value);
      jmsSender.send(message);
        } catch (ServiceLocatorException se) {
      System.err.println("JMSUtil exception " + se.getMessage());
      return false;
  } catch (JMSException exe) {
      System.err.println("JMSUtil exception " + exe.getMessage());
      return false;
  } catch (Exception ge) {
      System.err.println("JMSUtil exception " + ge.getMessage());
      return false;
        } finally {
            if (jmsCon != null) {
                try {
                    jmsCon.close();
                } catch (JMSException exe) {
        System.err.println("JMSUtil exception " + exe.getMessage());
        return false;
                }
            }
        }
  return true;
    }

    public static boolean sendMessage(String jmsDest, String property, 
              String value, Object obj) {  
  Connection jmsCon = null;
  try {
      ServiceLocator sl = new ServiceLocator(); 
      ConnectionFactory jmsConFactory = sl.getJMSConnectionFactory(JNDINames.OPC_QUEUE_CONNECTION_FACTORY);
      Destination target = sl.getJMSDestination(jmsDest);
            jmsCon = jmsConFactory.createConnection();
            Session jmsSession = jmsCon.createSession(true,0);
            MessageProducer jmsSender = jmsSession.createProducer(target);
            ObjectMessage message = jmsSession.createObjectMessage((java.io.Serializable) obj);
            message.setStringProperty(property, value); 
            jmsSender.send(message);
        } catch (ServiceLocatorException se) {
      System.err.println("JMSUtil exception" + se.getMessage());
      return false;
  } catch (JMSException exe) {
      System.err.println("JMSUtil exception " + exe.getMessage());
      return false;
  } catch (Exception ge) {
      System.err.println("JMSUtil exception " + ge.getMessage());
      return false;
        } finally {
            if (jmsCon != null) {
                try {
                    jmsCon.close();
                } catch (JMSException exe) {
        System.err.println("JMSUtil exception " + exe.getMessage());
        return false;
                }
            }
        }
  return true;
    }
}
