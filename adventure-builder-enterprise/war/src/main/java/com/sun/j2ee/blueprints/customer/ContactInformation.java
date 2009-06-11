/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: ContactInformation.java,v 1.2 2004/05/26 00:06:21 inder Exp $ */

package com.sun.j2ee.blueprints.customer;


/**
 * This class represents all the data needed to
 * identify an indvidual and contact that individual.
 * This class is meant to be immutable.
 */
public class ContactInformation implements java.io.Serializable {

    private String telephone;
    private String email;
    private Address address;
    private String familyName;
    private String givenName;

    public ContactInformation() {}

    public ContactInformation(String familyName,
                String givenName,
                String telephone,
                String email,
                Address address){

        this.givenName = givenName;
        this.familyName = familyName;
        this.email = email;
        this.telephone = telephone;
        this.address = address;
    }

    public String getGivenName(){
        return givenName;
    }

    public String getFamilyName(){
        return familyName;
    }

    public String getEMail(){
        return email;
    }

    public Address getAddress(){
        return address;
    }
    public String getTelephone(){
        return telephone;
    }

    public String toString(){
        return "[familyName=" + familyName + ", givenName=" +
            givenName + ", telephone=" + telephone + ", email=" +
            email + ",  address=" + address+ "]";
    }

}
