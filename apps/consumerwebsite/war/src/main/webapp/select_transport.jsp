<%-- Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: select_transport.jsp,v 1.4 2004/05/26 00:06:33 inder Exp $ --%>
<%@ taglib prefix="sql" uri="/WEB-INF/sql.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@page contentType="text/html"%>

<sql:setDataSource dataSource="jdbc/CatalogDB"/>
<sql:query var="flightOrigins"> 
  select distinct origin from transportation where locale = 'en_US'  and destination = ?
 <sql:param><c:out value="${cart.destination}"/></sql:param>
</sql:query>


<form method="POST" name="searchtransportation" action="searchtransport.do" >
<input type="HIDDEN" name="target_action" value="select_transport" />
<table>
  <tr> <td><label for="origin_id">Origin</label>:</td>
   <td> 
     <select name="origin" id="origin_id">
     <c:forEach var="flightOrigin" begin="0" items="${flightOrigins.rows}">
       <option>${flightOrigin.origin}</option>
     </c:forEach> 
     </select>
  </td>
  </tr>
  <tr><td>Destination :</td>
   <td> 
       ${sessionScope.cart.destination} 
  </td>
  </tr> 
 <tr> <td></td>
  <td>
  </td>

  <tr>
  <td> </td> <td>
    <input type="SUBMIT" value="Search" name="Search">
  </td>
  </tr>
</table>
</form>

