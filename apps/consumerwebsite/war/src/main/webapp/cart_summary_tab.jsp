<%-- Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: cart_summary_tab.jsp,v 1.3 2004/05/26 00:06:30 inder Exp $ --%>
<%@ taglib prefix="sql" uri="/WEB-INF/sql.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
<%@ taglib uri="/WEB-INF/waftags.tld" prefix="waf" %>
<%@page contentType="text/html"%>

<fmt:setLocale value="en_US" />

<sql:setDataSource dataSource="jdbc/CatalogDB"/>

<sql:query var="packageDetails"> 
 select name, description, imageuri from package where packageid =  ? and locale = ?
 <sql:param>${cart.packageId}</sql:param>
 <sql:param>en_US</sql:param>
</sql:query>


<table border="0" width="100%" cellspacing="0">
  <tr>
    <td colspan="3">
    <h2>${packageDetails.rows[0].name}</h2>
    </td><td/>
  </tr>
  <tr><td colspan="3"><br/></td>
  <tr>
   <td>
     <c:url var="imageURL" value="images/${packageDetails.rows[0].imageuri}"/>
     <image src="${imageURL}" height="50" width="50" alt="${packageDetails.rows[0].name}">
   </td>
  <td colspan="2"> ${packageDetails.rows[0].description} </td>
  </tr> 
  <tr>
   <td><b> Location :</b> </td>
   <td colspan="2">
    ${cart.destination}
   </td>
   <td align="right">
   <form action="enter_order_info.screen">
    <input type="Submit" value="Checkout"/>
   </form>
   </td>
  </tr>
  <tr><td colspan="3"><br/></td>
  <tr>
     <td><b> Number of People :</b> </td>
    <td colspan="2">
         ${cart.headCount}
     </td>
  </tr>
  <tr>
     <td><b> Number of Days :</b> </td>
    <td  colspan="2">
      ${cart.adventureDays}
     </td>
  </tr>
  <tr><td colspan="3"><br/></td> 
  <tr>   
   <td>
   <b>Lodging Total:</b>
   </td>
   <td  colspan="3" align="right">
       <fmt:formatNumber value="${cartBean.lodgingTotal}" type="currency" />
   </td>   
  </tr>
  <tr/><tr/>  
  <tr>   
   <td>
    <b>Activity Total:</b>
    </td>
   <td  colspan="3" align="right"> 
      <fmt:formatNumber value="${cartBean.activityTotal}" type="currency" />
   </td> 
  </tr>
  <tr/>  
  <tr>   
   <td>
    <b>Transportation Total:</b>
   </td>
   <td  colspan="3" align="right"> 
      <fmt:formatNumber value="${cartBean.transportationTotal}" type="currency" />
   </td> 
  </tr>
  <tr>   
   <td colspan="4" >
    <hr noshade="true">
   </td> 
  </tr>
  <tr>
   <td colspan="3">
    <b>Total Price : </b>
   </td>
   <td align="right">
    <b><fmt:formatNumber value="${cartBean.total}" type="currency" /></b>
   </td>
  </tr>
 </table>
