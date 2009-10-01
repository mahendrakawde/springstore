<%-- Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: banner.jsp,v 1.3 2005/02/04 20:06:13 yutayoshida Exp $ --%>

<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>

<table width="100%"  border="0" cellpadding="0" cellspacing="0">
  <tr align="right"> 
    <td height="143" colspan="3" valign="bottom" nowrap STYLE="background-image:url(images/Banner.gif); background-repeat: no-repeat;"> 
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr> 
          <td height="116" nowrap> 
            <p><a href="main.screen?CATEGORY_ID=''"><strong class="banner_link">Home</strong></a></p>
            <p><a href="customer.do"><strong class="banner_link">Account</strong></a></p>
            <p><a href="track_order.screen"><strong class="banner_link">Track Order</strong></a></p>
            <p>
    <c:choose>
     <c:when test="${j_signon == true}">
      <a href="signoff.do"><strong class="banner_link">Sign Off</strong></a>
      </c:when>
      <c:otherwise>
      <a href="signon_welcome.screen"><strong class="banner_link">Sign On</strong></a>
      </c:otherwise>
    </c:choose>
     </p></td>
     <td nowrap>&nbsp;</td>
      
 </tr>
  <tr><td>
   <jsp:include page="top_menubar.jsp"/>
 </td></tr>
</table>
</table>
