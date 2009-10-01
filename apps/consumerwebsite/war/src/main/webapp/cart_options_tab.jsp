<%-- Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: cart_options_tab.jsp,v 1.4 2004/05/26 00:06:30 inder Exp $ --%>
<%@ taglib prefix="sql" uri="/WEB-INF/sql.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>

<%@page contentType="text/html"%>

<fmt:setLocale value="en_US" />

<form method="POST" action="cart.do">
<input type="HIDDEN" name="target_action" value="update_package_options">
<jsp:include page="package_options.jsp"/>
</form>

