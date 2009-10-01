<%-- Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: adventures.jsp,v 1.4 2004/05/26 00:06:29 inder Exp $ --%>
<%@ taglib prefix="sql" uri="/WEB-INF/sql.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/fn.tld" %>
<%@page contentType="text/html"%>

<sql:setDataSource dataSource="jdbc/CatalogDB"/>

<sql:query var="packages"> 
 select distinct p.packageid, p.name, p.imageuri, p.description, c.name as catname from  package p, category c  where p.catid=?  and c.catid = ? and p.locale = ?
 <sql:param>${param.CATEGORY_ID}</sql:param>
 <sql:param>${param.CATEGORY_ID}</sql:param>
 <sql:param>en_US</sql:param>
</sql:query>

 <table border="0"  width="100%" height="100%" cellpadding="0" cellspacing="0">
<tr>
  <td align="center"><h1>${packages.rows[0].catname}</h1></td>
</tr>
<tr><td height="12"><image src="images/spacer.gif" height="12" alt=""></td></tr>
 <c:forEach var="package" begin="0" items="${packages.rows}">
 <tr>
   <td> &nbsp;&nbsp;
      <image src="images/${package.imageuri}" alt="${package.name} Image">
   </td>
   <td>
      <c:url value="/adventure.screen" var="viewPackageURL">
       <c:param name="PACKAGE_ID" value="${fn:trim(package.packageid)}"/>
      </c:url>
      <br>
      <a href="${viewPackageURL}">
       <h2>${package.name}</h2>
      </a>
     <br><br>${package.description}
   </td>
  </tr>
 </c:forEach>
  <tr><td height="12"><image src="images/spacer.gif" height="12" alt=""></td></tr>
</table>

