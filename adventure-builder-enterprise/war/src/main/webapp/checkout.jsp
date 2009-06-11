<%-- Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: checkout.jsp,v 1.2 2004/05/26 00:06:31 inder Exp $ --%>

<font size="+3">Checkout Complete</font>
<hr>
<br>
Thank you for planning your adventure with the Adventure Builder Web
Application. Your order id is 
<a href="ordertracking.do?orderId=${checkoutBean.orderId}"><font size="+1" color="blue">${checkoutBean.orderId}</font></a>.
<br><br>
Please come again.