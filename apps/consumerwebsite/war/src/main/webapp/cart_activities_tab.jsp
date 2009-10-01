<%-- Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: cart_activities_tab.jsp,v 1.4 2004/05/26 00:06:30 inder Exp $ --%>
<%@ taglib prefix="sql" uri="/WEB-INF/sql.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
<%@ taglib uri="/WEB-INF/waftags.tld" prefix="waf" %>
<%@page contentType="text/html"%>

<script language="javascript">
   function changePeople() {
       activitiesForm.submit();
   }
</script>

<fmt:setLocale value="en_US" />
<sql:setDataSource dataSource="jdbc/CatalogDB"/>
<form name="activitiesForm" method="POST" action="cart.do">
 <input type="HIDDEN" name="target_action" value="update_activities" />
 <input type="HIDDEN" name="tab" value="${param.tab}" />
<table width="100%" cellspacing="0" border="0">
    <table width="100%" cellspacing="0" border="0">
      <c:choose>
       <c:when test="${cart.activityCount > 0}">  
        <c:forEach var="activity" items="${cart.activityIds}">
        <sql:query var="activityDetails"> 
          select name, description, price, imageuri from activity where activityid =  ? and locale =?
        <sql:param>${activity}</sql:param>
        <sql:param>en_US</sql:param>
          </sql:query>
         <tr>
           <td>
            <c:url var="imageURL" value="images/${activityDetails.rows[0].imageuri}"/>
            <image src="${imageURL}" alt="${activityDetails.rows[0].name} Image">
           </td>
          <td>
              <h2>${activityDetails.rows[0].name}</h2>
              ${activityDetails.rows[0].description}
              <br><b><label for="number_of_people_id">Number of people</label>:</b>
              <waf:select size="1"
                                       id="number_of_people_id"
                          onChange="changePeople()"
                                   name="headcount_${activity}">
                <waf:selected>${cart.activities[activity]}</waf:selected>
               <waf:option value="0">None - Cancel</waf:option>
                <c:forEach varStatus="status" begin="1" end="${cart.headCount}">
                 <waf:option value="${status.index}">${status.index}</waf:option>
                </c:forEach>
              </waf:select>
              <br>
              <b>Price Per Person:</b> <fmt:formatNumber value="${activityDetails.rows[0].price}" type="currency" />
              <br><br>
           </td>
           <td align="right">
            <fmt:formatNumber value="${activityDetails.rows[0].price * cart.activities[activity]}" type="currency" />
          </td>
         </tr>
        </c:forEach>
    <tr>
     <td colspan="3">
     <hr noshade="true">
     </td>
    </tr>
   <tr>
    <td colspan="2">
     <h2>Activity Total:</h2>
    </td>
   <td align="right">
     <b><fmt:formatNumber value="${cartBean.activityTotal}" type="currency" /></b>
    </td>
  </tr>
   <tr>
   <td colspan="3" align="right">
      <input type="SUBMIT" name="Update Number of People" value="Update Number of People"/> <a href="activities.screen?tab=${param.tab}">Purchase More Activities</a>
   </td>
  </tr>
  </c:when>
   <c:otherwise>
    <tr>
     <td>
         There are no activities in your adventure. <br><br>
       <a href="activities.screen?tab=${param.tab}">Purchase Activities</a>
     </td>
    </tr>
   </c:otherwise>
  </c:choose>
 </table>
</form>