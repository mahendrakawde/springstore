<%-- Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: lodgings.jsp,v 1.4 2004/05/26 00:06:32 inder Exp $ --%>
<%@ taglib prefix="sql" uri="/WEB-INF/sql.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
<%@page contentType="text/html"%>

<fmt:setLocale value="en_US" />

<sql:setDataSource dataSource="jdbc/CatalogDB"/>

<sql:query var="lodgings"> 
select lodgingid,name, description, price, imageuri from lodging where location = ? and locale = ?
 <sql:param>${cart.destination}</sql:param>
 <sql:param>en_US</sql:param>
</sql:query>
<table>
<c:forEach var="lodging" varStatus="lodgingStatus" items="${lodgings.rows}">
<form action="cart.do" method="POST">
 <input type="HIDDEN" name ="target_action" value="purchase_lodging"/>
 <input type="HIDDEN" name ="tab" value="${param.tab}"/>
  <tr>
   <td>
     <c:url var="imageURL" value="images/${lodging.imageuri}"/>
     <image src="${imageURL}" alt="${lodging.name} Image">
   </td>
   <td>
    <h2>${lodging.name}</h2><br><br>
     ${lodging.description}
    </td>
   <td><b>Price:</b> <fmt:formatNumber value="${lodging.price}" type="currency" /> per room per night</td>
    <td>
     <input type="HIDDEN" name="lodging_id" value="${lodging.lodgingid}">
     <input type="SUBMIT"  value="Purchase"  name="Purchase">
    </td>
  </form>
  </tr>
  <c:choose>
     <c:when test="${lodgingStatus.last == false}">
      <tr><td colspan="5"><hr width="90%"></td></tr>
     </c:when>
    </c:choose>
 </c:forEach>
  <tr><td colspan="4">&nbsp;</td></tr>
  <tr>
   <td colspan="4" align="right">
   <form action="cart.do" method="POST">
     <input type="SUBMIT" value="Cancel" name="Cancel">
   </form>
   </td>
  </tr>
</table>