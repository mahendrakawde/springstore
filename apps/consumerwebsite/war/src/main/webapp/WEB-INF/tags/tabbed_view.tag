<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>

<%@ attribute name="tabNames" %>
<%@ attribute name="height" %>
<%@ attribute name="width" %>
<%@ attribute name="selectedTab" %>
<%@ attribute name="defaultTab" %>
<%@ attribute name="tabContent" %>
<%@ attribute name="uri" %>


<table border="0"  cellpadding="0" cellspacing="0" bordercolor="#66ff66">
<tr>
<c:forEach var="tabName" items="${tabNames}" varStatus="status">
   <c:choose>
     <c:when test="${tabName == selectedTab ||
                              (selectedTab == '' &&
	        tabName == defaultTab)}">
      <c:set var="selectedTabIndex" value="${status.index}"/>
      <td><image src="images/left_selected.gif" alt=""></td>
      <td bgcolor="#6699FF">
       <a href="${uri}?tab=${tabName}">
       <strong class="tab_selected">${tabName}</strong>
       </a>
      </td>
      <td><image src="images/right_selected.gif" alt=""></td>
     </c:when>
     <c:otherwise>
      <td><image src="images/left_unselected.gif" alt=""></td>
      <td bgcolor="#7786a4">
       <a href="${uri}?tab=${tabName}">
       <strong class="tab_normal">${tabName}</strong>
       </a>
      </td>
      <td><image src="images/right_unselected.gif" alt=""></td>
     </c:otherwise>
    </c:choose>
 </c:forEach>
 </tr>
</table>
<table width="${width}" height="${height}" border="2"> 
 <tr>
   <td>
    <c:forEach var="tabContentURI" items="${tabContent}" varStatus="status">
     <c:choose>
      <c:when test="${status.index == selectedTabIndex}">
       <jsp:include page="${tabContentURI}"/>
      </c:when>
     </c:choose>
    </c:forEach>
   </td>
  </tr>
</table>
