/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: ServiceLocator.java,v 1.3 2004/07/30 23:59:21 inder Exp $ */

package com.sun.j2ee.blueprints.servicelocator.ejb;

import java.net.URL;

import javax.ejb.EJBHome;
import javax.ejb.EJBLocalHome;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import javax.sql.DataSource;
import javax.xml.rpc.Service;

import com.sun.j2ee.blueprints.servicelocator.ServiceLocatorException;

/**
 *  This class is an implementation of the Service Locator pattern. It is
 *  used to looukup resources such as EJBHomes, JMS Destinations, etc.
 */
public class ServiceLocator {
    
    private transient InitialContext ic;
    
    public ServiceLocator() throws ServiceLocatorException  {
        try {
            ic = new InitialContext();
        } catch (Exception e) {
            throw new ServiceLocatorException(e);
        }
    }
    
    /**
     * will get the ejb Local home factory.
     * clients need to cast to the type of EJBHome they desire
     *
     * @return the Local EJB Home corresponding to the homeName
     */
    public EJBLocalHome getLocalHome(final String jndiHomeName) throws ServiceLocatorException {
        try {
            return (EJBLocalHome) ic.lookup(jndiHomeName);
        } catch (Exception e) {
            throw new ServiceLocatorException(e);
        }
    }
    
    /**
     * will get the ejb Remote home factory.
     * clients need to cast to the type of EJBHome they desire
     *
     * @return the EJB Home corresponding to the homeName
     */
    public EJBHome getRemoteHome(final String jndiHomeName, final Class className) throws ServiceLocatorException {
        try {
            final Object objref = ic.lookup(jndiHomeName);
            return (EJBHome) PortableRemoteObject.narrow(objref, className);
        } catch (Exception e) {
            throw new ServiceLocatorException(e);
        }
    }
    
    /**
     * @return the factory for the factory to get JMS connections from
     */
    public  ConnectionFactory getJMSConnectionFactory(final String jmsConnFactoryName)
    throws ServiceLocatorException {
        try {
            return (ConnectionFactory) ic.lookup(jmsConnFactoryName);
        } catch (Exception e) {
            throw new ServiceLocatorException(e);
        }
    }
    
    /**
     * @return the JMS Destination to send messages to
     */
    public Destination getJMSDestination(final String destName) throws ServiceLocatorException {
        try {
            return (Destination)ic.lookup(destName);
        } catch (Exception e) {
            throw new ServiceLocatorException(e);
        }
    }
        
    /**
     * This method obtains the datasource itself for a caller
     * @return the DataSource corresponding to the name parameter
     */
    public DataSource getDataSource(final String dataSourceName) throws ServiceLocatorException {
        try {
            return (DataSource)ic.lookup(dataSourceName);
        } catch (Exception e) {
            throw new ServiceLocatorException(e);
        }
    }
    
    /**
     * @return the URL value corresponding
     * to the env entry name.
     */
    public URL getUrl(final String envName) throws ServiceLocatorException {
        try {
            return (URL)ic.lookup(envName);
        } catch (Exception e) {
            throw new ServiceLocatorException(e);
        }
    }
    
    /**
     * @return the boolean value corresponding
     * to the env entry such as SEND_CONFIRMATION_MAIL property.
     */
    public boolean getBoolean(final String envName) throws ServiceLocatorException {
        try {
            return ((Boolean)ic.lookup(envName)).booleanValue();
        } catch (Exception e) {
            throw new ServiceLocatorException(e);
        }
    }
    
    /**
     * @return the String value corresponding
     * to the env entry name.
     */
    public String getString(final String envName) throws ServiceLocatorException {
        try {
            return (String)ic.lookup(envName);
        } catch (Exception e) {
            throw new ServiceLocatorException(e);
        }
    }
    
    public java.rmi.Remote getPort(final String envName, Class portInterface) throws ServiceLocatorException {
    	try {
    		Service service = (Service) ic.lookup(envName);
    		return service.getPort(portInterface);
    	} catch (Exception e) {
    		throw new ServiceLocatorException(e);
    	}
	}

}
