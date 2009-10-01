/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: CreditCardBean.java,v 1.2 2004/05/26 00:06:56 inder Exp $ */
package com.sun.j2ee.blueprints.opc.purchaseorder.ejb;

import javax.ejb.*;

import com.sun.j2ee.blueprints.opc.purchaseorder.*;

/**
 * Implementation class for the  CreditCardBean .
 * CreditCardBean is a CMP Entity Bean
 **/


 public abstract class CreditCardBean implements EntityBean {

  private EntityContext entityContext = null;

  public Object ejbCreate(CreditCard card) throws CreateException {
    setCardNumber(card.getCardNumber());
    setCardType(card.getCardType());
    setCardExpiryDate(card.getCardExpiryDate());

    return null;
  }

  public void ejbPostCreate(CreditCard card) throws CreateException {

  }

  public CreditCard getDetails() {
   CreditCard creditCard = new CreditCard();
   creditCard.setCardNumber(getCardNumber());
   creditCard.setCardType(getCardType());
   creditCard.setCardExpiryDate(getCardExpiryDate());
   return creditCard;
 }


 //getters ans setters for CMP fields
  public abstract void setCardNumber(String cardNumber);

  public abstract void setCardExpiryDate(String cardExpiryDate);

  public abstract void setCardType(String cardType);

  public abstract String getCardNumber();

  public abstract String getCardExpiryDate();

  public abstract String getCardType();

  public void ejbRemove() throws RemoveException {

  }

  public void ejbLoad() {

  }

  public void ejbStore() {

  }

  public void ejbActivate() {

  }

  public void ejbPassivate() {

  }

  public void unsetEntityContext() {
    this.entityContext = null;
  }
  public void setEntityContext(EntityContext entityContext) {
    this.entityContext = entityContext;
  }

}
