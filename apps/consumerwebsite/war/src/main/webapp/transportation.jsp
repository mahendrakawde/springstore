<%-- Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: transportation.jsp,v 1.5 2004/05/26 00:06:35 inder Exp $ --%>
<%@ taglib prefix="sql" uri="/WEB-INF/sql.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@page contentType="text/html"%>

<sql:setDataSource dataSource="jdbc/CatalogDB"/>
<sql:query var="flightOrigins"> 
  select distinct origin from transportation where locale = 'en_US'  and destination = ?
 <sql:param>${cart.destination}</sql:param>
</sql:query>


<form method="POST" name="searchtransportation" action="searchtransport.do" >
<input type="HIDDEN" name="target_action" value="select_transport" >

<h2>Transportation:</h2>
<hr noshade="noshade">
<p>Please select the Origin for your trip before searching for a flight.</p><br>
<table>
  <tr>
   <td><label for="origin_id">Origin</label> :</td>
   <td> 
     <select name="origin" id="origin_id">
     <c:forEach var="flightOrigin" begin="0" items="${flightOrigins.rows}">
       <option>${flightOrigin.origin}</option>
     </c:forEach> 
     </select>
  </td>
  </tr>
  <tr><td><label>Destination</label> :</td>
   <td> 
       ${cart.destination} 
  </td>
  </tr> 
  <tr>
   <td> </td>
   <td>
    <input type="SUBMIT" value="Show Available Flights" name="Show Available Flights">
     </form>
     <br><br>
     <form method="POST" action="cart.do">
      <input type="HIDDEN" name="target_action" value="no_transportation">
      <input type="SUBMIT"  value="I Will Provide My Own Transportation" name="I Will Provide My Own Transportation">
     </form>
  </td>
  </tr>
</table>


