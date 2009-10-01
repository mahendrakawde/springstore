<%-- Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: set_package_options.jsp,v 1.3 2004/05/26 00:06:33 inder Exp $ --%>
<%@ taglib prefix="sql" uri="/WEB-INF/sql.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
<%@page contentType="text/html"%>

<fmt:setLocale value="en_US" />

<sql:setDataSource dataSource="jdbc/CatalogDB"/>

<sql:query var="packageDetails"> 
 select name, description, imageuri from package where packageid =  ? and locale = ?
 <sql:param>${cart.packageId}</sql:param>
 <sql:param>en_US</sql:param>
</sql:query>
<table width="100%">
 <tr>
  <td>
  <image src="images/${packageDetails.rows[0].imageuri}" alt="${packageDetails.rows[0].name} Image">
  </td>
  <td align="left">
   <h1>${packageDetails.rows[0].name}</h1><br><br>
   Please customize the options on your adventure package.
  </td>
 </tr>
 <tr>
 <td colspan="2">
<form method="POST" action="cart.do" >
<input type="HIDDEN" name="target_action" value="set_package_options">
<jsp:include page="package_options.jsp"/>
</form>
 </td>
 </tr>
</table>
