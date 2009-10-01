/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: LineItem.java,v 1.2 2004/05/26 00:06:13 inder Exp $ */

package com.sun.j2ee.blueprints.catalog;

import java.io.Serializable;

/** 
 * This class represents a particular item in the Adventure Builder.
 *  Each item belongs to particular type of product
 * and has attributes like id,listprice etc.
*/
public class LineItem implements Serializable {

    private String itemId;
    private String name;
    private String description;
    private double listPrice;
    private double unitCost;
    private String attr1;
    private String attr2;
    private String attr3;
    private String attr4;
    private String attr5;

    public LineItem() { }

    public LineItem(String itemId,
                    String name,
                    String description,
                    double listPrice,
                    double unitCost,
                    String attr1,
                    String attr2,
                    String attr3,
                    String attr4,
                    String attr5) {

        this.itemId = itemId;
        this.name = name;
        this.description = description;
        this.listPrice = listPrice;
        this.unitCost = unitCost;
        this.attr1 = attr1;
        this.attr2 = attr2;
        this.attr3 = attr3;
        this.attr4 = attr4;
        this.attr5 = attr5;
    }
    
    public String getItemId() {
        return itemId;
    }
    
    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public double getListPrice() {
        return listPrice;
    }

    public String getAttr1() {
        return attr1;
    }
    
    public String getAttr2() {
        return attr2;
    }
    
    public String getAttr3() {
        return attr3;
    }
    
    public String getAttr4() {
        return attr4;
    }
    
    public String getAttr5() {
        return attr5;
    }
    
    public String toString() {
        return "LineItem [id=" + itemId +
               ", name=" + name + 
               ", listPrice=" + listPrice +
               ", unitCost=" + unitCost +
               ", description=" + description +
               ", attr1=" + attr1 +
               ", attr2=" + attr2 +
               ", attr3=" + attr3 +
               ", attr4=" + attr4 +
               ", attr5=" + attr5 +
               "]";
    }
}
