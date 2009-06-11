/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: ConfigFileSignOnDAO.java,v 1.2 2004/05/26 00:06:27 inder Exp $ */

package com.sun.j2ee.blueprints.signon.web;

import org.xml.sax.InputSource;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.SAXException;


// jaxp 1.0.1 imports
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * This class provides the data bindings for the screendefinitions.xml
 * and the requestmappings.xml file.
 * The data obtained is maintained by the ScreenFlowManager
 */

public class ConfigFileSignOnDAO  {

    // xml tag constants
    public static final String SIGNON_FORM_LOGIN_PAGE = "signon-form-login-page";
    public static final String SIGNON_FORM_ERROR_PAGE = "signon-form-error-page";
    public static final String SECURITY_CONSTRAINT = "security-constraint";
    public static final String WEB_RESOURCE_COLLECTION = "web-resource-collection";
    public static final String WEB_RESOURCE_NAME = "web-resource-name";
    public static final String URL_PATTERN = "url-pattern";
    public static final String AUTH_CONSTRAINT = "auth-constraint";
    public static final String ROLE_NAME = "role-name";

    private String signOnLoginPage = null;
    private String signOnErrorPage = null;
    private HashMap protectedResources = null;

    public ConfigFileSignOnDAO (URL configURL) {
        Element root = loadDocument (configURL);
        protectedResources = getProtectedResources(root);
    }

    public String getSignOnPage() {
        return signOnLoginPage;
    }

    public String getSignOnErrorPage() {
        return signOnErrorPage;
    }

    public HashMap getProtectedResources() {
        return protectedResources;
    }

    private  Element loadDocument(URL url) {
        Document doc = null;
        try {
            InputSource xmlInp = new InputSource(url.openStream());

            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder parser = docBuilderFactory.newDocumentBuilder();
            doc = parser.parse(xmlInp);
            Element root = doc.getDocumentElement();
            root.normalize();
            return root;
        } catch (SAXParseException err) {
            System.err.println ("ConfigFileSignOnDAO  ** Parsing error" + ", line " +
                        err.getLineNumber () + ", uri " + err.getSystemId ());
            System.err.println("ConfigFileSignOnDAO  error: " + err.getMessage ());
        } catch (SAXException e) {
            System.err.println("ConfigFileSignOnDAO  error: " + e);
        } catch (java.net.MalformedURLException mfx) {
            System.err.println("ConfigFileSignOnDAO  error: " + mfx);
        } catch (java.io.IOException e) {
            System.err.println("ConfigFileSignOnDAO  error: " + e);
        } catch (Exception pce) {
            System.err.println("ConfigFileSignOnDAO  error: " + pce);
        }
        return null;
    }

    private HashMap getProtectedResources(Element root) {
        HashMap resources = new HashMap();
        // get the signon page and signon error page
        signOnLoginPage = getTagValue(root, SIGNON_FORM_LOGIN_PAGE ).trim();
        signOnErrorPage = getTagValue(root, SIGNON_FORM_ERROR_PAGE ).trim();

        // get protected pages //
        NodeList outterList = root.getElementsByTagName(SECURITY_CONSTRAINT);
        for (int outterLoop = 0; outterLoop < outterList.getLength(); outterLoop++) {
            Element element = (Element)outterList.item(outterLoop);
            // get  roles that can see this page
            ArrayList roles = new ArrayList();
            NodeList roleList = element.getElementsByTagName(AUTH_CONSTRAINT);
            for (int roleLoop = 0; (roleList != null) && roleLoop < roleList.getLength(); roleLoop++) {
                        Node  roleNode = roleList.item(roleLoop);
                        String roleName = getSubTagValue(roleNode, ROLE_NAME);
                        if ((roleName != null) && !roleName.equals("")) roles.add(roleName);
            }

            NodeList list = element.getElementsByTagName(WEB_RESOURCE_COLLECTION);
            for (int loop = 0; (list != null) && loop < list.getLength(); loop++) {
                Node node = list.item(loop);
                if (node != null) {
                    String resourceName = getSubTagValue(node, WEB_RESOURCE_NAME);
                    String urlPattern = getSubTagValue(node, URL_PATTERN);
                    ProtectedResource resource = new ProtectedResource(resourceName, urlPattern, roles);
                    if (!resources.containsKey(resourceName)) {
                         resources.put(resourceName, resource);
                    } else {
                        System.err.println("*** Non Fatal errror: Protected Resource " + resourceName +
                                       " defined more than once in screen definitions file");
                    }
                }
            }
        }
        return resources;
    }
    private String getSubTagAttribute(Element root, String tagName, String subTagName, String attribute) {
        String returnString = "";
        NodeList list = root.getElementsByTagName(tagName);
        for (int loop = 0; (list != null) && loop < list.getLength(); loop++) {
            Node node = list.item(loop);
            if (node != null) {
                NodeList  children = node.getChildNodes();
                for (int innerLoop =0; innerLoop < children.getLength(); innerLoop++) {
                    Node  child = children.item(innerLoop);
                    if ((child != null) && (child.getNodeName() != null) && child.getNodeName().equals(subTagName) ) {
                        if (child instanceof Element) {
                            return ((Element)child).getAttribute(attribute);
                        }
                    }
                } // end inner loop
            }
        }
        return returnString;
    }

    private String getSubTagValue(Node node, String subTagName) {
        String returnString = "";
        if (node != null) {
            NodeList  children = node.getChildNodes();
            for (int innerLoop =0; (children != null) && innerLoop < children.getLength(); innerLoop++) {
                Node  child = children.item(innerLoop);
                if ((child != null) && (child.getNodeName() != null) && child.getNodeName().equals(subTagName) ) {
                    Node grandChild = child.getFirstChild();
                    if (grandChild.getNodeValue() != null) return grandChild.getNodeValue();
                }
            } // end inner loop
        }
        return returnString;
    }

    private String getSubTagValue(Element root, String tagName, String subTagName) {
        String returnString = "";
        NodeList list = root.getElementsByTagName(tagName);
        for (int loop = 0; (list != null) && loop < list.getLength(); loop++) {
            Node node = list.item(loop);
            if (node != null) {
                NodeList  children = node.getChildNodes();
                for (int innerLoop =0; innerLoop < children.getLength(); innerLoop++) {
                    Node  child = children.item(innerLoop);
                    if ((child != null) && (child.getNodeName() != null) && child.getNodeName().equals(subTagName) ) {
                        Node grandChild = child.getFirstChild();
                        if (grandChild.getNodeValue() != null) return grandChild.getNodeValue();
                    }
                } // end inner loop
            }
        }
        return returnString;
    }

    private String getTagValue(Element root, String tagName) {
        String returnString = "";
        NodeList list = root.getElementsByTagName(tagName);
        for (int loop = 0; (list != null) && loop < list.getLength(); loop++) {
            Node node = list.item(loop);
            if (node != null) {
                Node child = node.getFirstChild();
                if ((child != null) && child.getNodeValue() != null) return child.getNodeValue();
            }
        }
        return returnString;
    }
}


