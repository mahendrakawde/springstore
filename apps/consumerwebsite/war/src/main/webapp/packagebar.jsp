<%-- Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: packagebar.jsp,v 1.5 2004/05/26 00:06:33 inder Exp $ --%>
<%@ taglib prefix="sql" uri="/WEB-INF/sql.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/fn.tld" %>
<%@page contentType="text/html"%>
<%@ taglib uri="/WEB-INF/waftags.tld" prefix="waf" %>

<sql:setDataSource dataSource="jdbc/CatalogDB"/>

<%-- Need to set the category id  --%>
 <c:choose>
  <c:when test="${param.CATEGORY_ID  != null}">
   <c:set var="theCategory" value="${param.CATEGORY_ID}" scope="session"/>
   <c:set var="categoryId" value="${param.CATEGORY_ID}" scope="session"/>
  </c:when>
  <c:otherwise>
   <c:set var="theCategory" value="${categoryId}" scope="session"/>
  </c:otherwise>
 </c:choose>


<sql:query var="packages"> 
 select distinct package.packageid, package.name as pname,category.name  as cname, category.catid from  package, category  where package.catid=?  and category.catid = ? and package.packageid = ? and package.locale = ?
 <sql:param>${theCategory}</sql:param>
 <sql:param>${theCategory}</sql:param>
 <sql:param>${cart.packageId}</sql:param>
 <sql:param>en_US</sql:param>
</sql:query>

<sql:query var="categories"> 
 select catid,name,description from category where catid = ? and locale = ? order by name
 <sql:param>${theCategory}</sql:param>
<sql:param>en_US</sql:param>
</sql:query>


<table  cellspacing="12" cellpadding="12" border="0">

 <tr>
    <td  align="left" nowrap="nowrap" bgcolor="#ffffff">
     <c:choose>
        <c:when test="${cart.configurationComplete}">
         <a href="cart.do"><strong class="navigationTitle">Adventure Package</strong></a>
        </c:when>
        <c:otherwise>
          <strong class="navigationTitle">Adventure Package</strong>
        </c:otherwise>
      </c:choose>
    </td>
   </tr>
   <tr>
    <td nowrap="nowrap">
         <c:forEach var="category" begin="0" items="${categories.rows}">
          <p class="navigation">
           ${category.name}
          </p>
          <c:choose>
           <%-- List the Sub Category Stuff here --%>
           <c:when test="${fn:trim(category.catid) == theCategory}">
            <c:forEach var="package" begin="0" items="${packages.rows}">
            <p class="sub_navigation">
             &nbsp;&nbsp;&nbsp;
            <c:choose>
             <%-- Identify a selected Adventure Package --%>
             <c:when test="${cart.packageId == fn:trim(package.packageid)}">
                <image src="images/Arrow_small.gif" width="10" height="14" alt="Selected Arrow Image">
             </c:when>
             <c:otherwise>
               <image src="images/Arrow_spacer.gif" alt="">
             </c:otherwise>
            </c:choose>
            ${package.pname}
             </p>
            </c:forEach>
           </c:when>
           <%-- Done Listing the Sub Category Stuff here --%>
          </c:choose>
       </c:forEach>
       </td>
     </tr>
 <tr><td>&nbsp;</td></tr>
<tr><td>&nbsp;</td></tr>
  <tr valign="bottom">
      <td align="left" valign="bottom">
         <form action="cart.do" method="POST">
         <input type="HIDDEN" name="target_action" value="cancel">
         <input type="SUBMIT" value="Cancel this Adventure" name="Cancel this Adventure">
        </form>
       </td>
   </tr>
</table>


