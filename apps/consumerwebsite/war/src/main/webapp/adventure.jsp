<%-- Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: adventure.jsp,v 1.4 2004/05/26 00:06:29 inder Exp $ --%>
<%@ taglib prefix="sql" uri="/WEB-INF/sql.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
<%@page contentType="text/html"%>

<fmt:setLocale value="en_US" />

<sql:setDataSource dataSource="jdbc/CatalogDB"/>
<sql:query var="packageContents"> 
 select p.name, p.description,p.location, p.price , l.name as lodgingname, l.description as lodgingdescription, l.price as lodgingprice, p.imageuri, l.imageuri as lodgingimageuri from package p, lodging l where p.lodgingid = l.lodgingid and p.packageid = ? and p.locale = ?
 <sql:param>${param.PACKAGE_ID}</sql:param>
 <sql:param>en_US</sql:param>
</sql:query>
<sql:query var="activityDetails"> 
 select activity.name, activity.description, activity.price, activity.imageuri from activity, activitylist, package where package.packageid = activitylist.packageid and activity.activityid = activitylist.activityid and package.packageid = ? and package.locale = ?
 <sql:param>${param.PACKAGE_ID}</sql:param>
 <sql:param>en_US</sql:param>
</sql:query>

<table border="0" width="100%">

  <tr>
     <td>
     <c:url var="imageURL" value="images/${packageContents.rows[0].imageuri}"/>
     <image src="${imageURL}" alt="${packageContents.rows[0].name} Image">
   </td>
    <td>
    <h1>${packageContents.rows[0].name}</h1><br><br>
    <b> Location :</b>${packageContents.rows[0].location} <br><br>
     ${packageContents.rows[0].description} <br><br>
    <b>Estimated Price :</b>
    <fmt:formatNumber value="${packageContents.rows[0].price}" type="currency" /></b></td>
    <td align="right">
     <form mthod="POST" action="cart.do">
     <input type="HIDDEN" name="target_action" value="select_package">
     <input type="HIDDEN" name="package_id" value="${param.PACKAGE_ID}">
     <input type="SUBMIT" value="Select Package" name="Select Package">
    </form>
    </td>
  </tr> 
</table>
<table width="100%" border="0">
  <tr>   
   <td colspan="3" >
    <h2>Lodging Details </h2>
   </td>
  </tr>
  <tr>
   <td>&nbsp;&nbsp;&nbsp;</td>
   <td>
     <c:url var="imageURL" value="images/${packageContents.rows[0].lodgingimageuri}"/>
     <image src="${imageURL}" alt="${packageContents.rows[0].lodgingname} Image">
    </td>
    <td>
        Name : ${packageContents.rows[0].lodgingname}<br>
       ${packageContents.rows[0].lodgingdescription}<br>  
        <b>Price : <fmt:formatNumber value="${packageContents.rows[0].lodgingprice}" type="currency" />  &nbsp; ${packageContent[5]}</b><br> 
   </td>   
  </tr>

  <tr>   
   <td colspan="3" >
    <h2>Included Activities</h2>
   </td>
  </tr>
        <c:forEach var="activityDetail" items="${activityDetails.rows}">
          <tr>
           <td>&nbsp;&nbsp;&nbsp;</td>
           <td>
            <c:url var="imageURL" value="images/${activityDetail.imageuri}"/>
            <image src="${imageURL}" alt="${activityDetail.name} Image">
           </td>
         <td>
          <b><c:out value="${activityDetail.name}"/></b><br> 
          ${activityDetail.description}<br> 
           <b>Price : <fmt:formatNumber value="${activityDetail.price}" type="currency" /></b>
          </td>
         </tr>
        </c:forEach>
  <tr/>
 <tr><td colsapn="3">&nbsp;</td></tr>
  <tr>
    <td colspan="4" align="right">
     <form mthod="POST" action="cart.do">
     <input type="HIDDEN" name="target_action" value="select_package">
     <input type="HIDDEN" name="package_id" value="${param.PACKAGE_ID}">
     <input type="SUBMIT" value="Select Package" name="Select Package">
    </form>
   </td>
  </tr>
 </table>