/* Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: CheckoutHTMLAction.java,v 1.5 2005/03/08 00:20:13 smitha Exp $ */

package com.sun.j2ee.blueprints.consumerwebsite.actions;


import java.io.*;
import java.util.*;


// j2ee imports
import javax.naming.*;
import javax.servlet.http.*;
import javax.xml.rpc.*;


// WAF imports
import com.sun.j2ee.blueprints.waf.controller.Event;
import com.sun.j2ee.blueprints.waf.controller.web.html.*;

// signon filter - for the userId
import com.sun.j2ee.blueprints.signon.web.SignOnFilter;

// customer component imports
import com.sun.j2ee.blueprints.customer.*;

//adventure imports

import com.sun.j2ee.blueprints.consumerwebsite.*;
import com.sun.j2ee.blueprints.consumerwebsite.exceptions.*;

// Catalog imports
import com.sun.j2ee.blueprints.catalog.CatalogFacade;

/**
 * Implementation of HTMLAction that processes a
 * checkout.
 */

public final class CheckoutHTMLAction extends HTMLActionSupport {   

    public Event perform(HttpServletRequest request)
  throws HTMLActionException {
            
        buildPurchaseOrder(request);
        return null; 
    }
    
    private void buildPurchaseOrder(HttpServletRequest request) 
        throws HTMLActionException {

        HttpSession session = request.getSession();
        AdventureComponentManager acm = 
            (AdventureComponentManager)session.getAttribute(AdventureKeys.COMPONENT_MANAGER);
        // get the customer and cart 
        String userId = (String)request.getSession().getAttribute(SignOnFilter.USER_NAME);
        CatalogFacade catalog = acm.getCatalogFacade(session);
        CustomerFacade cfacade = acm.getCustomerFacade(session);
        Cart cart = acm.getCart(session);
        try {
            Locale locale = new Locale("en","us"); 
            double totalPrice = 0;
            ContactInfo shippingInfo = extractContactInfo(request, "_b");
            ContactInfo billingInfo = extractContactInfo(request, "_a");
            CreditCard card = new CreditCard();
            card.setCardNumber(request.getParameter("credit_card_number"));
            card.setCardExpiryDate(request.getParameter("credit_card_month") + "/" + request.getParameter("credit_card_year"));
            card.setCardType(request.getParameter("credit_card_name"));

            com.sun.j2ee.blueprints.catalog.Lodging lodging = catalog.getLodging(cart.getLodgingId(), locale);
            Lodging lodgingPO = new Lodging();
            lodgingPO.setLodgingId(lodging.getLodgingId());
            lodgingPO.setName(lodging.getName());
            lodgingPO.setPricePerNight((new Double(lodging.getPrice())).floatValue());
            lodgingPO.setLocation(lodging.getLocation());
            lodgingPO.setStartDate(cart.getDepartureDate());
            lodgingPO.setEndDate(cart.getReturnDate());
            lodgingPO.setNoNights(cart.getLodgingDays());
            lodgingPO.setNoRooms(cart.getLodgingRoomCount());
            totalPrice += lodging.getPrice();

            // set the transportation info
            Transportation dF = null;
            if (cart.getDepartureFlight() != null) {
                dF= new Transportation();
                com.sun.j2ee.blueprints.catalog.Transportation departureFlight =
                    catalog.getTransportation(cart.getDepartureFlight(), locale);
                dF.setTransportationId(departureFlight.getTransportationId());
                dF.setCarrier(departureFlight.getCarrier());
                dF.setOrigin(departureFlight.getOrigin());
                dF.setDestination(departureFlight.getDestination());
                dF.setDepartureDate(cart.getDepartureDate());
                dF.setDepartureTime(departureFlight.getDepartureTime());
                dF.setPrice((new Double(departureFlight.getPrice())).floatValue());
                dF.setTravelClass(departureFlight.getTravelClass());
                dF.setHeadCount(cart.getHeadCount());
                totalPrice += departureFlight.getPrice();
            }
            Transportation rF = null;
            if (cart.getReturnFlight() != null) {
                rF = new Transportation();
                com.sun.j2ee.blueprints.catalog.Transportation returnFlight =
                    catalog.getTransportation(cart.getReturnFlight(), locale);
            
                rF.setTransportationId(returnFlight.getTransportationId());
                rF.setCarrier(returnFlight.getCarrier());
                rF.setOrigin(returnFlight.getOrigin());
                rF.setDestination(returnFlight.getDestination());
                rF.setDepartureDate(cart.getReturnDate());
                rF.setDepartureTime(returnFlight.getDepartureTime());
                rF.setPrice((new Double(returnFlight.getPrice())).floatValue());
                rF.setTravelClass(returnFlight.getTravelClass());
                rF.setHeadCount(cart.getHeadCount());
                totalPrice += returnFlight.getPrice();
            }
            
            ArrayList poActivities = new ArrayList();
            HashMap activities = cart.getActivities();
            Iterator it = null;
            if (activities != null) {
                it = activities.keySet().iterator();
                 while ((it != null) && it.hasNext()) {
                    String itemId = (String)it.next();
                    int headCount = ((Integer)activities.get(itemId)).intValue();
                    com.sun.j2ee.blueprints.catalog.Activity item = catalog.getActivity(itemId, locale);
                    Activity a1 = new Activity();
                    a1.setActivityId(item.getActivityId());
                    a1.setName(item.getName());
                    a1.setPrice((new Double(item.getPrice())).floatValue());
                    a1.setLocation(item.getLocation());
                    a1.setStartDate(cart.getDepartureDate());
                    a1.setEndDate(cart.getReturnDate());
                    a1.setHeadCount(headCount);
                    totalPrice += item.getPrice();
                    poActivities.add(a1);
                }
        }
       Activity[] myPoActivities = new  Activity[poActivities.size()];
       poActivities.toArray(myPoActivities);

        //  create a new po
        PurchaseOrder mypo = new PurchaseOrder();
        String poId = "AB-" +userId + "-" + System.currentTimeMillis();
        mypo.setPoId(poId);
        mypo.setUserId(userId);
        mypo.setEmailId(shippingInfo.getEmail());
        mypo.setLocale("US");
        mypo.setOrderDate(Calendar.getInstance());
        mypo.setShippingInfo(shippingInfo);
        mypo.setBillingInfo(billingInfo);
        mypo.setTotalPrice((new Double(totalPrice)).floatValue());
        mypo.setCreditCard(card);
        mypo.setHeadCount(cart.getHeadCount());
        mypo.setStartDate(Calendar.getInstance());
        mypo.setEndDate(Calendar.getInstance());
        mypo.setDepartureCity(cart.getOrigin());
        mypo.setActivities(myPoActivities);
        mypo.setLodging(lodgingPO);
        if (dF != null) mypo.setDepartureFlightInfo(dF);
        if (rF != null) mypo.setReturnFlightInfo(rF);

        Context ic = new InitialContext();
        Service opcPurchaseOrderSvc =
          (Service) ic.lookup("java:comp/env/service/OpcPurchaseOrderService");
        PurchaseOrderIntf port = (PurchaseOrderIntf)opcPurchaseOrderSvc.getPort(PurchaseOrderIntf.class);
        String ret = port.submitPurchaseOrder(mypo);
        CheckoutBean checkoutBean = new CheckoutBean(ret);
        request.setAttribute(AdventureKeys.CHECKOUT_BEAN, checkoutBean);

      } catch (Exception e) {
        throw new HTMLActionException("CheckoutHTMLAction Exception : " + e.getMessage(),e);
      }
        // clear the adventure package
        cart.clear();
    }
      
    /* parse address form and generate a ContactInfo object */
    private ContactInfo extractContactInfo(HttpServletRequest request, String suffix)
  throws HTMLActionException {
        ArrayList missingFields = null;
        String familyName =  request.getParameter("family_name" +suffix).trim();
        if (familyName.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add("Last Name");
        }
        String givenName =  request.getParameter("given_name" +suffix).trim();
        if (givenName.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add("First Name");
        }
        String address1 = request.getParameter("address_1" +suffix).trim();
        if (address1.equals("")){
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add("Street Address");
        }
        String address2 = request.getParameter("address_2" +suffix).trim();
        if (address2.equals("")) {
          address2 = null;
        }
        String city =   request.getParameter("city"  +suffix).trim();
        if (city.equals("")){
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add("City");
        }
        String stateOrProvince = request.getParameter("state_or_province" +suffix).trim();
        if (stateOrProvince.equals("")) {
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add("State or Province" +suffix);
        }
        String postalCode = request.getParameter("postal_code" +suffix).trim();
        if (postalCode.equals("")){
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add("Postal Code");
        }

        String country = request.getParameter("country" + suffix).trim();
        String telephone = request.getParameter("telephone_number" +suffix).trim();
        if (telephone.equals("")){
            if (missingFields == null) {
                missingFields = new ArrayList();
            }
            missingFields.add("Telephone Number");
        }
        String email = null;
        if (request.getParameter("email"  +suffix) != null) {
            email = request.getParameter("email" +suffix).trim();
        }
        if (missingFields != null) {
            MissingFormDataException ex = new MissingFormDataException("Missing Address Data", missingFields);
            request.setAttribute(AdventureKeys.MISSING_FORM_DATA_EXCEPTION_KEY, ex);
            return null;
        }

  ContactInfo cInfo = new ContactInfo();
  Address addr = new Address();
  addr.setStreetName1(address1);
  addr.setStreetName2(address2);
  addr.setCity(city);
  addr.setState(stateOrProvince);
  addr.setCountry(country);
  addr.setPostalCode(postalCode);
  cInfo.setFamilyName(familyName);
  cInfo.setGivenName(givenName);
  cInfo.setAddress(addr);
  cInfo.setEmail(email);
  cInfo.setPhone(telephone);
  return(cInfo);
    }
}

