/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: Transportation.java,v 1.2 2004/05/26 00:06:14 inder Exp $ */

package com.sun.j2ee.blueprints.catalog;

import java.io.Serializable;

/**
 * This class represents a Transportation in the Adventure Builder.
 * It has attributes like name, description etc.
 */
public class Transportation implements Serializable {
    
    protected String transportationId;
    protected String name;
    protected String description;
    protected String imageURI;
    protected String origin;
    protected String destination;
    protected String carrier;
    protected String departureTime;
    protected String arrivalTime;
    protected double price;
    protected String travelClass;
    
    public Transportation() { }
    
    public Transportation(String transportationId,
    String name,
    String description,
    String imageURI,
    double price,
    String origin,
    String destination,
    String carrier,
    String departureTime,
    String arrivalTime,
    String travelClass) {
        
        this.transportationId = transportationId;
        this.name = name;
        this.description = description;
        this.imageURI = imageURI;
        this.price = price;
        this.origin = origin;
        this.destination = destination;
        this.carrier = carrier;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.travelClass = travelClass;
    }
    
    public String getTransportationId() {
        return transportationId;
    }
    
    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public String getImageURI() {
        return imageURI;
    }
    
    public double getPrice() {
        return price;
    }
    
    public String getOrigin() {
        return origin;
    }
    public String getDestination() {
        return destination;
    }
    public String getCarrier() {
        return carrier;
    }
    
    public String getDepartureTime() {
        return departureTime;
    }
    
    public String getArrivalTime() {
        return arrivalTime;
    }
    
    public String getTravelClass() {
        return travelClass;
    }
    
    
    public String toString() {
        return "Transportation[id=" + transportationId +
        ", name=" + name +
        ", price=" + price +
        ", description=" + description +
        ", imageURI=" + imageURI +
        ", origin=" + origin +
        ", destination=" + destination +
        ", carrier=" + carrier +
        ", departure time=" + departureTime +
        ", arrival time =" + arrivalTime +
        ", travel class =" + travelClass +
        "]";
    }
}
