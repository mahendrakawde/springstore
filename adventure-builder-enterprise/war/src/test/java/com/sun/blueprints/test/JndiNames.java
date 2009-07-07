package com.sun.blueprints.test;

/**
 * The JNDI names used for each db for testing. As mentioned and extracted
 * from the web.xml of the 1.0.6 version of this project.
 * 
 * @author Marten Deinum
 *
 */
public abstract class JndiNames {

	public static final String SIGNNONDB = "java:comp/env/jdbc/CatalogDB";
	public static final String CATALOGDB = "java:comp/env/jdbc/CatalogDB";
	public static final String CUSTOMERDB = "java:comp/env/jdbc/CatalogDB";
	
}
