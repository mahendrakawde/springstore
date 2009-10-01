/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: DatabaseNames.java,v 1.2 2004/05/26 00:06:15 inder Exp $ */

package com.sun.j2ee.blueprints.catalog.dao;

/**
 * This interface stores the name of all the database tables.
 * The String constants in this class should be used by other
 * classes instead of hardcoding the name of a database table
 * into the source code.
 * The integer constants in this class can be used in the place of
 * integer constants
 */
public class DatabaseNames {
    
    private DatabaseNames() { } //Prevents instantiation
    
    public static final String LODGING_TABLE = "lodging";
    public static final String TRANSPORTATION_TABLE = "transportation";
    public static final String ACTIVITY_TABLE = "activity";
    public static final String PACKAGE_TABLE = "package";
    public static final String ACTIVITYLIST_TABLE = "activitylist";
}
