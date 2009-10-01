<%-- Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: package_options.jsp,v 1.4 2004/05/26 00:06:33 inder Exp $ --%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>
<%@ taglib uri="/WEB-INF/waftags.tld" prefix="waf" %>
<%@ taglib prefix="abtags" tagdir="/WEB-INF/tags" %> 
<%@page contentType="text/html"%>

<table>
  <tr>
   <td>
    <label for="head_count_id">Number of People:<label>
   </td>
   <td>
     <waf:select size="1" name="head_count" id="head_count_id">
      <waf:selected>${cart.headCount}</waf:selected>
      <c:forEach varStatus="status" begin="1" end="9">
       <waf:option>${status.index}</waf:option>
      </c:forEach>
     </waf:select>
    </td>
  </tr>
  <tr>
   <td>
    Start Date:
   </td>
   <td>
      <waf:date prefix="start" calendar="${cart.departureDate}"/>
    </td>
  </tr>
  <tr>
    <td>
       <label for="number_of_days">Number of Days</label>:
    </td>
    <td>
     <waf:select name="adventure_days" id="number_of_days">
      <waf:selected>${cart.adventureDays}</waf:selected>
     <c:forEach varStatus="status" begin="3" end="31">
       <waf:option>${status.index}</waf:option>
     </c:forEach>
     </waf:select>
    </td>
  </tr>
  <tr>
    <td>
       <label for="lodging_room_count">Number of Lodging Rooms:</label>
    </td>
    <td>
     <waf:select name="lodging_room_count" id="lodging_room_count">
     <waf:selected>${cart.lodgingRoomCount}</waf:selected>
     <c:forEach varStatus="status" begin="1" end="5">
       <waf:option>${status.index}</waf:option>
     </c:forEach>
     </waf:select>
    </td>
  </tr>
  <tr>
   <td align="right" colspan="3">
    <input type="SUBMIT" value="Set Package Options" name="Set Package Options"/>
  </td>
  </tr>
</table>



