/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: ScreenDefinitionDAO.java,v 1.2 2004/05/26 00:07:36 inder Exp $ */

package com.sun.j2ee.blueprints.waf.view.template;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;


/**
 * This class provides the data bindings for the screendefinitions.xml
 * and the requestmappings.xml file.
 * The data obtained is maintained by the ScreenFlowManager
 */

public class ScreenDefinitionDAO {

	private static final Logger logger = LoggerFactory.getLogger(ScreenDefinitionDAO.class);
	
    // event - flow constants
    public static final String URL_MAPPING = "url-mapping";
    public static final String SCREEN_DEFINITION = "screen-definition";
    public static final String LANGUAGE = "language";
    public static final String TEMPLATE = "template";
    public static final String DEFAULT_TEMPLATE = "default-template";
    public static final String RESULT = "result";
    public static final String NEXT_SCREEN = "screen";
    public static final String USE_REQUEST_HANDLER = "useRequestHandler";
    public static final String USE_FLOW_HANDLER = "useFlowHandler";
    public static final String FLOW_HANDLER_CLASS = "class";
    public static final String REQUEST_HANDLER_CLASS = "request-handler-class";
    public static final String HANDLER_RESULT = "handler-result";
    public static final String FLOW_HANDLER = "flow-handler";

    // screendefinitions.xml contansts
    public static final String KEY = "key";
    public static final String VALUE= "value";
    public static final String DIRECT="direct";
    public static final String SCREEN= "screen";
    public static final String NAME = "name";
    public static final String URL = "url";
    public static final String PARAMETER = "parameter";

    public static Element loadDocument(URL url) {
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
            logger.error("ScreenFlowXmlDAO ** Parsing error, line {}, uri {}", Integer.valueOf(err.getLineNumber()), err.getSystemId());
            logger.error("ScreenFlowXmlDAO error: ", err);
        } catch (Exception pce) {
            logger.error("ScreenFlowXmlDAO error: ", pce);
        }
        return null;
    }

    public static Screens loadScreenDefinitions(URL location) {
        Element root = loadDocument(location);
        if (root != null) return getScreens(root);
        else return null;
    }




    public static Map getScreenDefinitions(Element root) {
        Map screensDefs = new HashMap();
        NodeList list = root.getElementsByTagName(SCREEN_DEFINITION);
        for (int loop = 0; loop < list.getLength(); loop++) {
            Node node = list.item(loop);
            if (node != null) {
                String language = null;
                String url = null;
                if (node instanceof Element) {
                    language = ((Element)node).getAttribute(LANGUAGE);
                    url = ((Element)node).getAttribute(URL);
                }
                if ((language != null) && (url != null) && !screensDefs.containsKey(language)) {
                    screensDefs.put(language, url);
                } else {
                    logger.warn("*** Non Fatal errror: ScreenDefinitions for language {} defined more than once in screen definitions file", language);
                }
            }
        }
        return screensDefs;
    }

    public static Screens getScreens(Element root) {
        // get the template
        String defaultTemplate = getTagValue(root, DEFAULT_TEMPLATE);
        if (defaultTemplate == null) {
            logger.warn("*** ScreenDefinitionDAO error: Default Template not Defined.");
            return null;
        }

        Screens screens = new Screens(defaultTemplate);
        getTemplates(root, screens);
        // get screens
        NodeList list = root.getElementsByTagName(SCREEN);
        for (int loop = 0; loop < list.getLength(); loop++) {
            Node node = list.item(loop);
            if ((node != null) && node instanceof Element) {
                String templateName = ((Element)node).getAttribute(TEMPLATE);
                String screenName = ((Element)node).getAttribute(NAME);
                Map parameters = getParameters(node);
                Screen screen = new Screen(screenName, templateName, parameters);
                if (!screens.containsScreen(screenName)) {
                    screens.addScreen(screenName, screen);
                } else {
                    logger.warn("*** Non Fatal errror: Screen {} defined more than once in screen definitions file", screenName);
                }
            }
        }
        return screens;
    }

    /**
     *    Load the templates into the Screens object
     */

    public static void getTemplates(Element root, Screens screens) {
        NodeList list = root.getElementsByTagName(TEMPLATE);
        for (int loop = 0; loop < list.getLength(); loop++) {
            Node node = list.item(loop);
            String templateName = null;
            if (node != null) {
                if (node instanceof Element) {
                    templateName = ((Element)node).getAttribute(NAME);
                }
                String templateURL = getSubTagValue(node, URL);

                if (!screens.containsTemplate(templateName)) {
                    screens.addTemplate(templateName, templateURL);
                } else {
                	logger.warn("*** Non Fatal errror: Template {} defined more than once in screen definitions file", templateName);
                }
            }
        };
    }

    private static Map getParameters(Node node) {
        Map params = new HashMap();
        if (node != null) {
            NodeList  children = node.getChildNodes();
            for (int innerLoop =0; innerLoop < children.getLength(); innerLoop++) {
                Node  child = children.item(innerLoop);
                if ((child != null) && (child.getNodeName() != null) && child.getNodeName().equals(PARAMETER) ) {
                    if (child instanceof Element) {
                        Element childElement = ((Element)child);
                        String key = childElement.getAttribute(KEY);
                        String value = childElement.getAttribute(VALUE);
                        String directString = childElement.getAttribute(DIRECT);
                        boolean direct = false;
                        if ((directString != null) && directString.equals("true")) {
                            direct = true;
                        }
                        if (!params.containsKey(key)) {
                            params.put(key, new Parameter(key, value, direct));
                        } else {
                            logger.warn("*** Non Fatal errror: Parameter {} is defined more than once", key);
                        }
                    }
                }
            } // end inner loop
        }
        return params;
    }

    public static String getSubTagValue(Node node, String subTagName) {
        String returnString = "";
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
        return returnString;
    }

    private String getSubTagAttribute(Element root, String tagName, String subTagName, String attribute) {
        String returnString = "";
        NodeList list = root.getElementsByTagName(tagName);
        for (int loop = 0; loop < list.getLength(); loop++) {
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

    public static String getSubTagValue(Element root, String tagName, String subTagName) {
        String returnString = "";
        NodeList list = root.getElementsByTagName(tagName);
        for (int loop = 0; loop < list.getLength(); loop++) {
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

    public static String getTagValue(Element root, String tagName) {
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


