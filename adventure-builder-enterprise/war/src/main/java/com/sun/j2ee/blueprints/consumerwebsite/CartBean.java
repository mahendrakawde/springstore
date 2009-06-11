/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: CartBean.java,v 1.2 2004/05/26 00:06:16 inder Exp $ */

package com.sun.j2ee.blueprints.consumerwebsite;

import java.io.Serializable;
import java.util.Calendar;

/**
 * A JavaBeans component representing an account.
 */
public class CartBean implements Serializable {

    private double adventureTotal = 0;
    private double lodgingTotal = 0;
    private double activityTotal = 0;
    private double transportationTotal = 0;
    private Calendar departureDate = null;

    private int lodgingDays = 0;
    private int adventureDays = 0;
    
    // activities when we allow number of people will
    // have to compensate (assumption for now is that 
    // one person per activity
    
    public CartBean(double adventureTotal,
                                       double lodgingTotal,
                                       double activityTotal,
                                       double transportationTotal,
                                       int lodgingDays,
                                       int adventureDays,
                                       Calendar departureDate) {
                                           
      this.adventureTotal = adventureTotal;
      this.lodgingTotal = lodgingTotal;
      this.activityTotal = activityTotal;
      this.transportationTotal = transportationTotal;
      this.lodgingDays = lodgingDays;
      this.adventureDays = adventureDays;
      this.departureDate = departureDate;
    }

    public double getGrandTotal() {
        return adventureTotal + lodgingTotal + activityTotal;
    }
    
    public double getTotal() {
        return adventureTotal;
    }
        
    public double getLodgingTotal() {
        return lodgingTotal;
    }
    
    public double getActivityTotal() {
        return activityTotal;
    }
    
    public int getLodgingDays() {
        return lodgingDays;
    }
    
    public double getTransportationTotal() {
        return transportationTotal;
    }
    
    public int getAdventureDays() {
        return adventureDays;
    }
    
    public Calendar getDepartureDate() {
        return departureDate;
    }
}
