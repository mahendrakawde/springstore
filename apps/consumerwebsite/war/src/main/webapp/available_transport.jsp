<%-- Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: available_transport.jsp,v 1.6 2004/05/26 00:06:29 inder Exp $ --%>

<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>

<fmt:setLocale value="en_US" />

<h1> Departure - Available Flights</h1>
<br><br>

<table border="0">
 <form method="POST" action="cart.do">
  <input type="HIDDEN" name="target_action" value="purchase_transportation" >
  <input type="HIDDEN" name="tab" value="${param.tab}">
 <c:forEach var="item" varStatus="status"  begin="0" items="${departure_result}">
  <tr>
    <td>
     <c:url var="imageURL" value="images/${item.imageURI}"/>
     <image src="${imageURL}" alt="${item.name} Image">
    </td>
   <td>
             <b>${item.carrier}:</b> ${item.name}<br>
             <b>Departure Time :</b> ${item.departureTime}<br>
             <b>Arrival Time :</b> ${item.arrivalTime}<br>
             <b>Travel Class :</b> ${item.travelClass}<br>
             <b>Price : </b><fmt:formatNumber value="${item.price}" type="currency" /><br>
    </td>
    <td>
       <label id="select_flight_${item.id}">Select Flight ${item.id}</label>
      <input type="checkbox" name="departure_flight" value="${item.id}" id="select_flight_${item.id}"
      <%--  Check the first flight --%>
     <c:choose>
     <c:when test="${status.first == true}">
      checked="true"
     </c:when>
    </c:choose>
      />&nbsp;</td>
  </tr>
</c:forEach>
</table>
<hr noshade="true"><br>
<h1> Return - Available Flights</h1>
<br><br>
<table border="0">
 <c:forEach var="item" varStatus="status" begin="0" items="${return_result}">
  <tr>
    <td>
     <c:url var="imageURL" value="images/${item.imageURI}"/>
     <image src="${imageURL}" alt="${item.name} Image">
    </td>
  <td>
             <b>${item.carrier}</b> ${item.name}<br>
             <b>Departure Time :</b> ${item.departureTime}<br>
             <b>Arrival Time :</b> ${item.arrivalTime}<br>
             <b>Travel Class :</b> ${item.travelClass}<br>
             <b>Price : </b><fmt:formatNumber value="${item.price}" type="currency" /><br>
   </td>
  <td>
       <label id="select_return_flight_${item.id}">Select Flight ${item.id}</label>
      <input type="checkbox" name="return_flight" value="${item.id}" id="select_return_flight_${item.id}"
      <%--  Check the first flight --%>
     <c:choose>
     <c:when test="${status.first == true}">
      checked="true"
     </c:when>
    </c:choose>
   />&nbsp;</td>
  </tr>
</c:forEach>
 <tr>
  <td colspan="3">
    &nbsp;
  </td>
 </tr>
 <tr>
  <td colspan="3" align="right">
    <input type="SUBMIT" value="Purchase Selected Flights" name="Purchase Selected Flights">
    </form>
  </td>
  <td colspan="3" align="right">
    <form action="cart.do" method="POST">
     <input type="SUBMIT" value="Cancel Flight Purchase" name="cancelButton">
    </form>
  </td>
 </tr>
</table>
