/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: AdventurePackage.java,v 1.2 2004/05/26 00:06:13 inder Exp $ */

package com.sun.j2ee.blueprints.catalog;

import java.util.*;

/**
 *  This class is used to represent the total package 
 *  of an advenutre which includes activities
 *  and an AdventureItem.
 */
public class AdventurePackage implements java.io.Serializable {
    
    private ArrayList activities;
    private String packageId;
    private String name;
    private String lodginglId;
    private String location;
    private String description;
    private double price;
    
    /** Creates a new instance of AdventurePackage */
    public AdventurePackage(String packageId,
                                                String name,
                                                String description,
                                                String location,
                                                String lodginglId,
                                                double price,
                                                ArrayList activities) {
        this.packageId = packageId;
        this.name = name;
        this.description = description;
        this.lodginglId = lodginglId;
        this.location = location;
        this.price = price;
        this.activities = activities;        
    }
    
    public ArrayList getActivities() {
        return activities;
    }
      
    public void setHotelItemId(String lodginglId) {
        this.lodginglId = lodginglId;
    }
    
    public String getLodgingId() {
        return lodginglId;
    }
     
    public String getLocation() {
       return location;
    }
    
    public double getPrice() {
        return price;
    }
    
    public String getPackageId() {
        return packageId;
    }
    
    public String getName() {
        return name;
    }
    
    public String toString() {
        return "AdventurePackage[packageId=" + packageId + 
                   ", name=" + name +
                   ", lodginglId=" + lodginglId +
                   ", location=" + location + 
                   ", price=" + price +
                   ", activites=" + activities + "]";
    }
}
