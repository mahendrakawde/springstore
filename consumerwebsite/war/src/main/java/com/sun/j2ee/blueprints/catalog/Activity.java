/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: Activity.java,v 1.2 2004/05/26 00:06:13 inder Exp $ */

package com.sun.j2ee.blueprints.catalog;

import java.io.Serializable;

/**
 * This class represents an activity in the Adventure Builder.
 * It has attributes like name, description etc.
 */
public class Activity implements Serializable {
    
    protected String activityId;
    protected String name;
    protected String description;
    protected double price;
    protected String location;
    
    public Activity() { }
    
    public Activity(String activityId,
    String name,
    String description,
    double price,
    String location) {
        
        this.activityId = activityId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.location = location;
    }
    
    public String getActivityId() {
        return activityId;
    }
    
    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public double getPrice() {
        return price;
    }
    
    public String getLocation() {
        return location;
    }
    
    
    public String toString() {
        return "Activity [id=" + activityId +
        ", name=" + name +
        ", price=" + price +
        ", description=" + description +
        ", location=" + location +
        "]";
    }
}
