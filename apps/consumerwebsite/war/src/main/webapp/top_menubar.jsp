<%-- Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: top_menubar.jsp,v 1.4 2004/05/26 00:06:35 inder Exp $ --%>

<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>

 <tr valign="middle">
  <td  align="right"  bgcolor="7DBF7D" nowrap="nowrap">
 <c:choose>
  <c:when test="${cart.packageId == null}">
   <image src="images/Arrow_Selected.gif" width="14" height="20" class="arrow" align="absmiddle" alt="Selected Arrow Image">
   <span class="top_menu_selected">Select Adventure Package</span>
   <image src="images/Arrow.gif" width="10" height="14" align="absmiddle" class="arrow" alt="Arrow Image">
   <span class="top_menu_plain">Set Package Options</span>
   <image src="images/Arrow.gif" width="10" height="14" align="absmiddle" class="arrow" alt="Arrow Image">
   <span class="top_menu_plain">Select Transportation</span>
   <image src="images/Arrow.gif" width="10" height="14" align="absmiddle" class="arrow" alt="Arrow Image">
   <span class="top_menu_plain">Checkout</span></td><td  bgcolor="7DBF7D">&nbsp;</td>
  </c:when>
  <c:when test="${cart.adventureDays <= 0}">
   <image src="images/Arrow.gif" width="10" height="14" align="absmiddle" class="arrow" alt="Arrow Image">
   <span class="top_menu_plain">Select Adventure Package</span>
   <image src="images/Arrow_Selected.gif" width="14" height="20" class="arrow" align="absmiddle" alt="Selected Arrow Image">
   <span class="top_menu_selected">Set Package Options</span>
   <image src="images/Arrow.gif" width="10" height="14" align="absmiddle" class="arrow" alt="Arrow Image">
   <span class="top_menu_plain">Select Transportation</span>
   <image src="images/Arrow.gif" width="10" height="14" align="absmiddle" class="arrow" alt="Arrow Image">
   <span class="top_menu_plain">Checkout</span></td><td  bgcolor="7DBF7D">&nbsp;</td>
  </c:when>
  <c:when test="${cart.origin == null}">
   <image src="images/Arrow.gif" width="10" height="14" align="absmiddle" class="arrow" alt="Arrow Image">
   <span class="top_menu_plain">Select Adventure Package</span>
   <image src="images/Arrow.gif" width="10" height="14" align="absmiddle" class="arrow" alt="Arrow Image">
   <span class="top_menu_plain">Set Package Options</span>
   <image src="images/Arrow_Selected.gif" width="14" height="20" class="arrow" align="absmiddle" alt="Selected Arrow Image">
   <span class="top_menu_selected">Select Transportation</span>
   <image src="images/Arrow.gif" width="10" height="14" align="absmiddle" class="arrow" alt="Arrow Image">
   <span class="top_menu_plain">Checkout</span></td><td  bgcolor="7DBF7D">&nbsp;</td>
  </c:when>
  <c:otherwise>
   <image src="images/Arrow.gif" width="10" height="14" align="absmiddle" class="arrow" alt="Arrow Image">
   <span class="top_menu_plain">Select Adventure Package</span>
   <image src="images/Arrow.gif" width="10" height="14" align="absmiddle" class="arrow" alt="Arrow Image">
   <span class="top_menu_plain">Set Package Options</span>
   <image src="images/Arrow.gif" width="10" height="14" align="absmiddle" class="arrow" alt="Arrow Image">
   <span class="top_menu_plain">Select Transportation</span>
   <image src="images/Arrow_Selected.gif" width="14" height="20" class="arrow" align="absmiddle" alt="Selected Arrow Image">
   </td>
   <td valign="middle" bgcolor="7DBF7D"  nowrap="nowrap">
    <form  action="enter_order_info.screen">
      <input type="SUBMIT" value="Checkout" name="Checkout">
    </form>
   </td>
  </c:otherwise>
 </c:choose>
 </tr>
