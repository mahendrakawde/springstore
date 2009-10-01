
/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: CrmBean.java,v 1.10 2004/07/20 01:08:20 yutayoshida Exp $ */

package com.sun.j2ee.blueprints.opc.crm.ejb;

import java.util.*;
import javax.ejb.*;
import javax.naming.*;
import javax.jms.*;
import java.io.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.StreamResult;

import com.sun.j2ee.blueprints.opc.mailer.*;

/**
 * MailCompletedOrderMDB receives a JMS message containing an Order
 * xml message for orders that are COMPLETELY shipped. It builds a mail
 * message that it then sends to the mailer service so that
 * the customer gets email
 */
public class CrmBean implements MessageDrivenBean, MessageListener {
    
  private Context context;
  private MessageDrivenContext mdc;

  public CrmBean() {
  }

  public void ejbCreate() {
  }

  /**
   * Receive a JMS Message containing the Order that is completed,
   * generate Mail xml messages for the customer
   * The Mail xml mesages contain html presentation
   */
  public void onMessage(Message recvMsg) {
    TextMessage recdTM = null;
    String recdText = null;
    String result = null;

    try {
      recdTM = (TextMessage)recvMsg;
      recdText = recdTM.getText();
      result = doWork(recdText);
    } catch  (JMSException je) {
      throw new EJBException(je);
    }
  }

  public void setMessageDrivenContext(MessageDrivenContext mdc) {
    this.mdc = mdc;
  }

  public void ejbRemove() {
  }

    /**
      * Process the message and do the right thing.
      */
    private String doWork(String message) throws JMSException {

        Document doc = getDocument(message);
        String recipient = getTagValue(doc.getDocumentElement(), "Address");
        String xmlMessage = getTagValue(doc.getDocumentElement(), "Content");
        String title = getTagValue(doc.getDocumentElement(), "Subject");
        if (recipient == null) return "failed";
        try {
            MailHelper mh = new MailHelper();
            mh.createAndSendMail(recipient, title, xmlMessage,  Locale.getDefault());
          } catch (MailerException mx) {
            System.err.println("CrmBean: caught=" + mx);
            mx.printStackTrace();
          } catch (Exception ex) {
            System.err.println("CrmBean: caught=" + ex);
            ex.printStackTrace();
        }
        return "success";
      }
      
    private Document getDocument(String xmlText) {
        Document doc = null;
        try {
            DocumentBuilderFactory docBuilderFactory = null;
            DocumentBuilder db = null;
            try {
                docBuilderFactory = DocumentBuilderFactory.newInstance();
                if (docBuilderFactory != null) 
                    db = docBuilderFactory.newDocumentBuilder();
            } catch ( javax.xml.parsers.ParserConfigurationException pce) {
                System.err.println(pce);
            }
          InputSource is =  new InputSource(new StringReader(xmlText));
            doc = db.parse(is);
        } catch (Exception e) {
            System.err.println("CrmBean::getDocument error loading XML Document " + e);
        }
        return doc;
    }
    
    private String getTagValue(Element root, String tagName) {
        String returnString = "";
        NodeList list = root.getElementsByTagName(tagName);
        for (int loop = 0; loop < list.getLength(); loop++) {
            Node node = list.item(loop);
            if (node != null) {
                Node child = node.getFirstChild();
                if ((child != null) && child.getNodeValue() != null) return child.getNodeValue();
            }
        }
        return returnString;
    }
}

