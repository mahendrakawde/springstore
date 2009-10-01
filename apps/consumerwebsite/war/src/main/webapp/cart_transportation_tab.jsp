<%-- Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: cart_transportation_tab.jsp,v 1.6 2004/05/26 00:06:31 inder Exp $ --%>
<%@ taglib prefix="sql" uri="/WEB-INF/sql.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
<%@page contentType="text/html"%>

<fmt:setLocale value="en_US" />

<sql:setDataSource dataSource="jdbc/CatalogDB"/>

<table border="0" width="100%" cellspacing="0">
<c:choose>
  <c:when test="${cart.departureFlight != null}">
<sql:query var="departureFlight"> 
 select name, carrier, description, price,imageuri from transportation where transportationid =  ? and locale = ?
 <sql:param>${cart.departureFlight}</sql:param>
 <sql:param>en_US</sql:param>
</sql:query>
  <tr><td colspan="4" align="left"><h2>Departing Flight</h2></td></tr>
  <tr>
   <td>
     <c:url var="imageURL" value="images/${departureFlight.rows[0].imageuri}"/>
     <image src="${imageURL}" alt="${departureFlight.rows[0].carrier} ${departureFlight.rows[0].name} Image">
   </td>
    <td align="left" colspan="2" >    
      <br>
      <b>${departureFlight.rows[0].carrier} ${departureFlight.rows[0].name}</b>
      <br>${departureFlight.rows[0].description}
      <br><b>Price Per Person:</b><fmt:formatNumber value="${departureFlight.rows[0].price}" type="currency" />
      <br><b>Number of People:</b> ${cart.headCount}
   </td>
   <td align="right">
    <form action="cart.do" method="POST">
     <input type="HIDDEN" name ="target_action" value="cancel_departure_flight">
     <input type="HIDDEN" name="tab" value="${param.tab}">
     <input type="SUBMIT" value="Cancel This Flight" name="Cancel This Flight">
    </form>
   </td>
  </tr>
  </c:when>
 </c:choose>

<c:choose>
  <c:when test="${cart.returnFlight != null}">
<sql:query var="returnFlight"> 
 select name, description, price,imageuri from transportation where transportationid =  ? and locale = ?
 <sql:param>${cart.returnFlight}</sql:param>
 <sql:param>en_US</sql:param>
</sql:query>
  <tr><td colspan="4" align="center"><hr width="90%" noshade="true"/></td></tr>
  <tr><td colspan="4" align="left"><h2>Return Flight</h2></td></tr>
  <tr>
   <td>
     <c:url var="imageURL" value="images/${returnFlight.rows[0].imageuri}"/>
     <image src="${imageURL}" alt="${departureFlight.rows[0].carrier} ${returnFlight.rows[0].name} Image">
   </td>
   <td colspan="2">
      <b>${departureFlight.rows[0].carrier} ${returnFlight.rows[0].name}</b>
      <br>${returnFlight.rows[0].description}
      <br><b>Price Per Person:</b><fmt:formatNumber value="${returnFlight.rows[0].price}" type="currency" />
      <br><b>Number of People:</b> ${cart.headCount}
   </td>
   <td align="right">
    <form action="cart.do" method="POST">
     <input type="HIDDEN" name ="target_action" value="cancel_return_flight">
     <input type="HIDDEN" name="tab" value="${param.tab}">
     <input type="SUBMIT" value="Cancel This Flight" name="Cancel This Flight">
    </form>
   </td>
  </tr>
  </c:when>
 </c:choose>
 <c:choose>
  <c:when test="${cart.returnFlight != null ||  cart.departureFlight != null}">
  <tr><td colspan="4"><hr width="100%" noshade="true"/></td></tr>
  <tr>
   <td colspan="2">
    <h2>Flight Total:</h2>
    </td>
   <td/>
   <td align="right">
     <b><fmt:formatNumber value="${cartBean.transportationTotal}" type="currency" /></b>
    </td>
  </tr>
  <tr>
   <td colspan="4" align="right">
    <form method="POST" action="searchtransport.do" >
     <input type="HIDDEN" name="tab" value="${param.tab}">
     <input type="SUBMIT" value="Change Transportation">
    </form>
   </td>
  </tr>
  </c:when>
  <c:when test="${cart.returnFlight == null && cart.departureFlight == null}">
   <sql:setDataSource dataSource="jdbc/CatalogDB"/>
   <sql:query var="flightOrigins"> 
     select distinct origin from transportation where locale = 'en_US'  and destination = ?
    <sql:param>${cart.destination}</sql:param>
   </sql:query>
  <tr>
   <td colspan="4">
    You have not selected any flights.
    </td>
  <tr> <td><label for="origin_flight_id">Origin</label> :</td>
   <td>
    <form method="POST"  action="searchtransport.do" >
     <input type="HIDDEN" name="tab" value="${param.tab}">
     <select name="origin" id="origin_flight_id">
     <c:forEach var="flightOrigin" begin="0" items="${flightOrigins.rows}">
       <option>${flightOrigin.origin}</option>
     </c:forEach> 
     </select>
  </td>
  </tr>
  <tr><td>Destination :</td>
   <td> 
       ${cart.destination} 
   </td>
  </tr> 
   <td colspan="2" align="right">
       <input type="SUBMIT" value="Search Available Transportation" name="Search Available Transportation">
     </form>
    </td>
  </tr>
 </c:when>
  </c:choose>
</table>
