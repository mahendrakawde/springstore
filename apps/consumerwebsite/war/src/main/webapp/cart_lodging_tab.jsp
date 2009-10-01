<%-- Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: cart_lodging_tab.jsp,v 1.4 2004/05/26 00:06:30 inder Exp $ --%>
<%@ taglib prefix="sql" uri="/WEB-INF/sql.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
<%@ taglib uri="/WEB-INF/waftags.tld" prefix="waf" %>
<%@page contentType="text/html"%>

<fmt:setLocale value="en_US" />

<sql:setDataSource dataSource="jdbc/CatalogDB"/>

<sql:query var="lodgingDetails"> 
 select name, description, price,imageuri from lodging where lodgingid =  ? and locale = ?
 <sql:param>${cart.lodgingId}</sql:param>
 <sql:param>en_US</sql:param>
</sql:query>

<script language="javascript">
   function changePeople() {
       lodgingForm.submit();
   }
</script>

<form name="lodgingForm" method="POST" action="cart.do" >
<input type="HIDDEN" name="target_action" value="update_lodging_room_count" />
<table  border="0" width="100%" cellspacing="0">
  <tr>
   <td>
     <c:url var="imageURL" value="images/${lodgingDetails.rows[0].imageuri}"/>
     <image src="${imageURL}" alt="${lodgingDetails.rows[0].name} Image">
   </td>
    <td  align="left">
    <h2>${lodgingDetails.rows[0].name}</h2>
      <br>${lodgingDetails.rows[0].description}
      
      <br><b>Number of Nights:</b> ${cart.lodgingDays}
      <br><b>Number of Rooms:</b>${cart.lodgingRoomCount}
       <br><br>
       <b>Price Per Room Per Night:</b><fmt:formatNumber value="${lodgingDetails.rows[0].price}" type="currency" />
   </td>
   </tr>
  <tr>
   <td colspan="3">
 <hr noshade="noshade">
   </td>
   </tr>
  <tr>
   <td colspan="2" align="left">
    <h2>Lodging Total:</h2>
    </td>
   <td align="right">
     <b><fmt:formatNumber value="${cartBean.lodgingTotal}" type="currency" /></b>
    </td>
  </tr>
  <tr>
    <td colspan="3" align="right">
        <br>
       <a href="lodgings.screen?tab=${param.tab}">Change Lodging</a>
  </tr>
 </table>
</form>
