<%-- Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: sidebar.jsp,v 1.3 2004/05/26 00:06:34 inder Exp $ --%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>

 <c:choose>
  <c:when test="${(categoryId != null || param.CATEGORY_ID != null) && cart.packageId == null}">
    <c:import url="subcategorybar.jsp"/>
  </c:when>
   <c:when test="${(cart.packageId != null && cart.configurationComplete == false) || param.PACKAGE_ID != null }">
   <c:import url="packagebar.jsp"/>
  </c:when>
  <c:when test="${cart.packageId != null}">
    <c:import url="packagebar.jsp"/>  
  </c:when>
  <c:otherwise>
    <c:import url="subcategorybar.jsp"/>
  </c:otherwise>
 </c:choose>
