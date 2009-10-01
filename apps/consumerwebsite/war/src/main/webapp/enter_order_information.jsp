<%-- Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: enter_order_information.jsp,v 1.4 2004/05/26 00:06:31 inder Exp $ --%>

<%@ taglib uri="/WEB-INF/waftags.tld" prefix="waf" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>

 <c:set var="theCategory" value="${null}" scope="session"/>
 <c:set var="categoryId" value="${null}" scope="session"/>

<font size="+2">Billing Information</font>

<waf:form name="order_ino" method="POST" action="checkout.do">
 <table cellpadding="5" cellspacing="0" width="100%" border="0">
  <tr>
   <td align="right">
    <b><label for="given_name_a">First Name</label></b>
   </td> 
   <td align="left" colspan="2">
    <waf:input  type="text"
                           name="given_name_a"
                                  id="given_name_a"
                              size="30"
                   maxlength="30"
                       validation="validation">
      <waf:value>${customerBean.givenName}</waf:value>
    </waf:input>
   </td>
  </tr>
  <tr>
   <td align="right">
    <b><label for="family_name_a">Last Name</label></b>
   </td> 
   <td align="left" colspan="2">
    <waf:input  type="text"
                             name="family_name_a"
                                   id="family_name_a"
                               size="30"
                    maxlength="30"
                      validation="validation">
     <waf:value>${customerBean.familyName}</waf:value>
    </waf:input>
   </td>
  </tr>
  <tr>
   <td align="right"><b><label for="address_1_a">Street Address 1</label></b></td>
   <td align="left" colspan="2">
   <waf:input type="text"
                           name="address_1_a"
                           id="address_1_a"
                              size="55"
                   maxlength="70"
              validation="validation">
     <waf:value>${customerBean.streetName1}</waf:value>
    </waf:input>
   </td>
  </tr>
  <tr>
   <td align="right"><b><label for="address_2_a">Street Address 2</label></b></td>
   <td align="left" colspan="2">
    <waf:input type="text"
                       id="address_2_a"
                       name="address_2_a"
                          size="55"
               maxlength="70">
       <waf:value>${customerBean.streetName2}</waf:value>
     </waf:input>
   </td>
  </tr>
  <tr>
   <td align="right">
    <b><label for="city_a">City</label></b>
   </td>
   <td align="left" colspan="2">
    <waf:input  type="text"
                             name="city_a"
                             id="city_a"
                               size="55"
                    maxlength="70"
                      validation="validation">
      <waf:value>${customerBean.city}</waf:value>
     </waf:input>
   </td>
  </tr>
  <tr>
   <td align="right">
    <b><label for="state_or_province_a">State/Province</label></b>
   </td>
   <td align="left">
    <waf:select size="1"
                      id="state_or_province_a"
                      name="state_or_province_a">
     <waf:selected>${customerBean.state}</waf:selected>
     <waf:option value="California">CA</waf:option>
     <waf:option value="New York">NY</waf:option>
     <waf:option value="Texas">TX</waf:option>
    </waf:select>
   </td>
   <td><b><label for="postal_code_a">Zip Code</label></b>
    <waf:input  type="text"
                            id="postal_code_a" 
                            name="postal_code_a" 
                               size="12"
                    maxlength="12"
                      validation="validation">
     <waf:value>${customerBean.zipCode}</waf:value>
    </waf:input>
   </td>
  </tr>
  <tr>
   <td align="right">
    <b><label for="country_a">Country</label></b>
   </td>
   <td align="left" colspan="2">
    <waf:select size="1" name="country_a" id="country_a">
    <waf:selected>${customerBean.country}</waf:selected>
     <waf:option value="United States">United States</waf:option>
     <waf:option value="Canada" />
     <waf:option value="Japan" /> 
     <waf:option value="China" /> 
    </waf:select>
   </td>
  </tr>
  <tr>
   <td align="right">
    <b><label for="telephone_number_a">Telephone</label></b>
   </td>
   <td align="left" colspan="2">
    <waf:input  type="text"
                             name="telephone_number_a"
                             id="telephone_number_a"
                                size="12"
                     maxlength="70"
                         validation="validation">
      <waf:value>${customerBean.telephone}</waf:value>
     </waf:input>
   </td>
  </tr>
  <tr>
   <td align="right">
    <b><label for="email_a">E-Mail</label></b>
   </td>
   <td align="left" colspan="2">
    <waf:input  type="text"
                             name="email_a"
                             id="email_a"
                               size="22"
                    maxlength="70"
                      validation="validation">
      <waf:value>${customerBean.email}</waf:value>
     </waf:input>
   </td>
  </tr>
 </table>

 <font size="+2">Credit Card  Information</font>

 <table cellpadding="5" cellspacing="0" width="100%" border="0">
  <tr>
   <td> <label for="credit_card_name">Credit Card Name</label>:
      <select name="credit_card_name" id="credit_card_name">
       <option>Duke Express Card</option>
       <option>Globe Traveler Card</option>
      </select>
   </td>
 </tr>
   <tr>
   <td> <label for="credit_card_number">Credit Card Number</label>:
    <waf:input type="text"
                           name="credit_card_number"
                           id="credit_card_number"
                              size="20"
                   maxlength="20"
                       validation="validation">
      <waf:value>1111-1234-5432</waf:value>
    </waf:input>
   </td>
 </tr>
  <tr>
   <td><label for="credit_card_month">Expiration Month</label>:
     <select name="credit_card_month" id="credit_card_month">
     <c:forEach varStatus="status" begin="1" end="12">
       <option>${status.index}</option>
     </c:forEach>
     </select>
     <label for="credit_card_year">Expiration Year</label>:
     <select name="credit_card_year" id="credit_card_year">
     <c:forEach varStatus="status" begin="2004" end="2006">
       <option>${status.index}</option>
     </c:forEach>
     </select>
    </td>
  </tr>
 </table>

 <font size="+2">Shipping Information</font>

 <table cellpadding="5" cellspacing="0" width="100%" border="0">
  <tr>
   <td align="right">
    <b><label for="given_name_b">First Name</label></b>
   </td> 
   <td align="left" colspan="2">
    <waf:input type="text"
                                  id="given_name_b"
                           name="given_name_b"
                              size="30"
                   maxlength="30"
                       validation="validation">
      <waf:value>${customerBean.givenName}</waf:value>
    </waf:input>
   </td>
  </tr>
  <tr>
   <td align="right">
    <b><label for="family_name_b">Last Name</label></b>
   </td> 
   <td align="left" colspan="2">
    <waf:input  type="text"
                                   id="family_name_b"
                             name="family_name_b"
                               size="30"
                    maxlength="30"
                      validation="validation">
     <waf:value>${customerBean.familyName}</waf:value>
    </waf:input>
   </td>
  </tr>
  <tr>
   <td align="right"><b><label for="address_1_b">Street Address 1</label></b></td>
   <td align="left" colspan="2">
   <waf:input type="text"
                                 id="address_1_b"
                           name="address_1_b"
                              size="55"
                   maxlength="70"
              validation="validation">
     <waf:value>${customerBean.streetName1}</waf:value>
    </waf:input>
   </td>
  </tr>
  <tr>
   <td align="right"><b><label for="address_2_a">Street Address 2</label></b></td>
   <td align="left" colspan="2">
    <waf:input type="text"
                       name="address_2_b"
                          size="55"
               maxlength="70">
       <waf:value>${customerBean.streetName2}</waf:value>
     </waf:input>
   </td>
  </tr>
  <tr>
   <td align="right">
    <b><label for="city_b">City</label></b>
   </td>
   <td align="left" colspan="2">
    <waf:input  type="text"
                             name="city_b"
                                   id="city_b"
                               size="55"
                    maxlength="70"
                      validation="validation">
      <waf:value>${customerBean.city}</waf:value>
     </waf:input>
   </td>
  </tr>
  <tr>
   <td align="right">
    <b><label for="state_or_province_b">State/Province</label></b>
   </td>
   <td align="left">
    <waf:select size="1"
                      id="state_or_province_b"
                      name="state_or_province_b">
     <waf:selected>${customerBean.state}</waf:selected>
     <waf:option value="California">CA</waf:option>
     <waf:option value="New York">NY</waf:option>
     <waf:option value="Texas">TX</waf:option>
    </waf:select>
   </td>
   <td><b><label for="postal_code_b">Zip Code</label></b>
    <waf:input     type="text"
                            name="postal_code_b" 
                                   id="postal_code_b" 
                               size="12"
                    maxlength="12"
                      validation="validation">
     <waf:value>${customerBean.zipCode}</waf:value>
    </waf:input>
   </td>
  </tr>
  <tr>
   <td align="right">
    <b><label for="country_b">Country</label></b>
   </td>
   <td align="left" colspan="2">
    <waf:select size="1" name="country_b" id="country_b">
    <waf:selected>${customerBean.country}</waf:selected>
     <waf:option value="United States">United States</waf:option>
     <waf:option value="Canada" />
     <waf:option value="Japan" /> 
    </waf:select>
   </td>
  </tr>
  <tr>
   <td align="right">
    <b><label for="telephone_number_b">Telephone</b>
   </td>
   <td align="left" colspan="2">
    <waf:input  type="text"
                             name="telephone_number_b"
                                    id="telephone_number_b"
                                size="12"
                     maxlength="70"
                         validation="validation">
      <waf:value>${customerBean.telephone}</waf:value>
     </waf:input>
   </td>
  </tr>
  <tr>
   <td align="right">
    <b><label for="email_b">E-Mail</label></b>
   </td>
   <td align="left" colspan="2">
    <waf:input  type="text"
                             name="email_b"
                                    id="email_b"
                               size="22"
                    maxlength="70"
                      validation="validation">
      <waf:value>${customerBean.email}</waf:value>
     </waf:input>
   </td>
  </tr>
 </table>
 <input type="SUBMIT" value="Submit" name="Submit">
</waf:form>

