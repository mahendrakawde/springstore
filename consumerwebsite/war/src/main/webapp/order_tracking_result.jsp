<%-- Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: order_tracking_result.jsp,v 1.3 2004/05/26 00:06:33 inder Exp $ --%>


<h1>OrderTracking</h1>
<hr noshade="true">
<br>
<br>
The following is the current state of the order number
${orderDetails.PO.poId}
<br>
<br>
<b>User Name: <b/>${orderDetails.PO.userId}
<br><br>
<h2>Billing Address:</h2>
<br><br>
<table border="0">
 <tr>
 <td>${orderDetails.PO.billingInfo.address.streetName1}</td>
 </tr>
 <tr>
 <td>${orderDetails.PO.billingInfo.address.streetName2}</td>
 </tr>
 <tr>
  <td>
           ${orderDetails.PO.billingInfo.address.city},
           ${orderDetails.PO.billingInfo.address.state} &nbsp;&nbsp;
           ${orderDetails.PO.billingInfo.address.postalCode}
   </td>
 </tr>
</table>
<br>
<h2>Shipping Address:</h2>
<br><br>
<table border="0">
 <tr>
 <td>${orderDetails.PO.shippingInfo.address.streetName1}</td>
 </tr>
 <tr>
 <td>${orderDetails.PO.shippingInfo.address.streetName2}</td>
 </tr>
 <tr>
  <td>
           ${orderDetails.PO.shippingInfo.address.city},
           ${orderDetails.PO.shippingInfo.address.state} &nbsp;&nbsp;
           ${orderDetails.PO.shippingInfo.address.postalCode}
   </td>
 </tr>
</table>
<br>
<br>
<b>Status:</b>${orderDetails.status}
<br>
<br>
<a href="track_order.screen">Track another order</a>
