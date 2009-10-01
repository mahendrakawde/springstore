<%-- Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: activities.jsp,v 1.5 2004/05/26 00:06:29 inder Exp $ --%>
<%@ taglib prefix="sql" uri="/WEB-INF/sql.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
<%@ taglib uri="/WEB-INF/waftags.tld" prefix="waf" %>
<%@ taglib prefix="fn" uri="/WEB-INF/fn.tld" %>
<%@page contentType="text/html"%>

<fmt:setLocale value="en_US" />

<sql:setDataSource dataSource="jdbc/CatalogDB"/>
<sql:query var="activities"> 
select activityid,name, description, price, imageuri from activity where location = ? and locale = ?
 <sql:param>${cart.destination}</sql:param>
 <sql:param>en_US</sql:param>
</sql:query>


<script language="javascript">
   function disableUpdateButton() {
       activitiesForm.updateButton.disabled = true;
   }

   function changePeople() {
       activitiesForm.updateButton.disabled = false;
   }
</script>

<body onLoad="disableUpdateButton()">
<table>
<form name="activitiesForm" action="cart.do" method="POST">
 <input type="HIDDEN" name ="target_action" value="purchase_activities">
 <input type="HIDDEN" name ="tab" value="${param.tab}">
<c:forEach var="activity"  varStatus="activityStatus" items="${activities.rows}">
  <tr>
   <td>
     <c:url var="imageURL" value="images/${activity.imageuri}"/>
     <image src="${imageURL}" alt="${activity.name} Image">
   </td>
   <td>
    <h2>${activity.name}</h2>
     ${activity.description}
    </td>
    <td><b>Price</b> <fmt:formatNumber value="${activity.price}" type="currency" /> per person</td>
    <td colspan="2">
      <label>Number of People</label>:<br>
     <waf:select name="activity_${fn:trim(activity.activityid)}" onChange="changePeople()">
      <c:choose>
       <c:when test="${cart.activities[fn:trim(activity.activityid)] != null}">
        <waf:selected>${cart.activities[fn:trim(activity.activityid)]}</waf:selected>
        <waf:option value="0">None - Cancel</waf:option>
       </c:when>
       <c:otherwise>
           <waf:selected>0</waf:selected>
           <waf:option value="0">None</waf:option>
       </c:otherwise>
      </c:choose>
      
      <c:forEach varStatus="status" begin="1" end="${cart.headCount}">
       <waf:option value="${status.index}">${status.index}</waf:option>
      </c:forEach>
     </waf:select>
    </td>
    <td>
  </tr>
  <c:choose>
   <c:when test="${activityStatus.last == false}">
      <tr><td colspan="5"><hr width="90%"></td></tr>
   </c:when>
  </c:choose>
 </c:forEach>
  <tr>
   <td></td><td></td><td></td>
   <td valign="center">
      <input type="SUBMIT"  value="Update Activities" name="updateButton">
   </form>
   </td>
   <td valign="center">
   <form action="cart.do" method="POST">
     <input type="SUBMIT" value="Cancel Update" name="cancelButton">
   </form>
    </td>
 </tr>
</table>
</body>
