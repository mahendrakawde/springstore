<%-- Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: enter_order_tracking_info.jsp,v 1.5 2004/05/26 00:06:32 inder Exp $ --%>

<script language="javascript">

   function init() {
       orderTrackingForm.submitButton.disabled = true;
   }

   function initField() {
      orderTrackingForm.orderId.value = "";
      orderTrackingForm.submitButton.disabled = false;
   }
</script>

<body onLoad="init()">
<h2>Check Order Status</h2>

<p>Please enter an Order ID name below to track that order.</p>

<form  name="orderTrackingForm" method="POST" action="ordertracking.do" >

<table border="0" bgcolor="#EEEEEE" cellpadding="5" cellspacing="0">

  <tr>
  <td><b><label for="orderid_id">Order ID</label>:</b></td>
   <td><input type="text" size="20" name="orderId" id="orderId_id" onClick="initField()" value="Enter Order ID"></td>
  </tr>

  <tr>
  <td align="right" colspan="2">
  <input type="SUBMIT" value="Track Order" name="submitButton">
  </td>
  </tr>
</table>
</form>
</body>
