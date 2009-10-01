<%-- Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: account.jsp,v 1.2 2004/05/26 00:06:28 inder Exp $ --%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>

<font size="+3">Account</font>
<hr>
<br/>
<table>
 <tr>
  <td>
   <font size="+2">Name:</font>
  </td>
  <td>
   <c:out value="${customerBean.givenName}"/>
   <c:out value="${customerBean.familyName}"/>
  </td>
 </tr>
 <tr>
 <tr><td>&nbsp;</td></tr>
  <td>
  </td>
  <td>
   <c:out value="${customerBean.streetName1}"/>
  </td>
 </tr>
 <tr>
  <td><font size="+2">Address:</font>
  </td>
  <td>
   <c:out value="${customerBean.streetName2}"/>
  </td>
 </tr>
 <tr>
  <td>
  </td>
  <td>
   <c:out value="${customerBean.city}"/>,
   <c:out value="${customerBean.state}"/> &nbsp;
   <c:out value="${customerBean.zipCode}"/>
  </td>
 </tr>
 <tr>
  <td>
  </td>
  <td>
   <c:out value="${customerBean.country}"/>
  </td>
 </tr>
</table>


