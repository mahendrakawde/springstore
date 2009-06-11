/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: Mail.java,v 1.3 2004/07/20 01:08:20 yutayoshida Exp $ */

package com.sun.j2ee.blueprints.opc.mailer;

import java.io.*;
import java.util.*;
import java.net.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

public class Mail {

    private String address;
    private String subject;
    private String content;

    private Mail() {}

    public Mail(String address, String subject, String content) {
        this.address = address;
        this.subject = subject;
        this.content = content;
    }

    // getters

    public String getAddress() {
        return address;
    }

    public String getSubject() {
        return subject;
    }
    public String getContent() {
        return content;
    }

    public String toXML() {      
      String mailXML = null;      
      try{
                    
          //construct the DOM tree
          DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
          docBuilderFactory.setNamespaceAware(true);
          DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
          Document doc = docBuilder.newDocument();          
          Element  mailElem = doc.createElement("Mail");
          doc.appendChild(mailElem);          
          Element  elem = doc.createElement("Subject");          
          elem.appendChild(doc.createTextNode(subject));
          mailElem.appendChild(elem);          
          elem = doc.createElement("Address");          
          elem.appendChild(doc.createTextNode(address));
          mailElem.appendChild(elem);          
          elem = doc.createElement("Content");
          elem.appendChild(doc.createTextNode(content));
          mailElem.appendChild(elem);          
     
          //process the source tree
          ByteArrayOutputStream baStream = new ByteArrayOutputStream();
          Result res = new StreamResult(baStream);
          TransformerFactory transFactory = TransformerFactory.newInstance();
          Transformer transformer = transFactory.newTransformer();
          transformer.setOutputProperty(OutputKeys.METHOD, "xml");          
          transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    transformer.transform(new DOMSource(doc), res);
          mailXML = baStream.toString("UTF-8");  
          
      } catch(Exception exe){
          System.err.println(exe);
      }
      return mailXML;   
  }
}
