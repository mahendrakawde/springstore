/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: TransportationBean.java,v 1.2 2004/05/26 00:06:17 inder Exp $ */

package com.sun.j2ee.blueprints.consumerwebsite;

import java.io.Serializable;
import com.sun.j2ee.blueprints.catalog.*;


/**
 * A JavaBeans component representing transportation details.
 */
public class TransportationBean extends Transportation implements Serializable {
    
    
    public TransportationBean(String transportationId,
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
        
        super(transportationId, name, description, imageURI, price, origin,
        destination, carrier, departureTime, arrivalTime, travelClass);
        
    }
    public TransportationBean() {
        
    }
    
    //getter methods are provided in the base class
    
    //setter methods
    
    public void setId(String transportationId) {
        this.transportationId = transportationId;
    }
    
    public String getId() {
        return transportationId;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setImageURI(String imageURI) {
        this.imageURI = imageURI;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    public void setOrigin(String origin) {
        this.origin = origin;
    }
    public void setDestination(String destination) {
        this.destination= destination;
    }
    public void setCarrier(String carrier) {
        this.carrier= carrier;
    }
    public void setDepartureTime(String departureTime) {
        this.departureTime= departureTime;
    }
    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime= arrivalTime;
    }
    public void setTravelClass(String travelClass) {
        this.travelClass= travelClass;
    }
    
    
    public String toString() {
        return "Transportation[id=" + getTransportationId() +
        ", name=" + getName() +
        ", price=" + getPrice() +
        ", description=" + getDescription() +
        ", origin=" + getOrigin() +
        ", destination=" + getDestination()+
        ", carrier=" + getCarrier() +
        ", departure time=" + getDepartureTime() +
        ", arrival time =" + getArrivalTime() +
        ", travel class =" + getTravelClass() +
        "]";
    }
    
}
