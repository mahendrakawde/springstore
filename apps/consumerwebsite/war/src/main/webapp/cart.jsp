<%-- Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: cart.jsp,v 1.2 2004/05/26 00:06:30 inder Exp $ --%>

<%@ taglib prefix="abtags" tagdir="/WEB-INF/tags" %> 

<h1>Adventure Package</h1>
<hr>

<abtags:tabbed_view  tabNames="${'Summary,Options,Lodgings,Activities,Transportation'}"
                                          tabContent="${'cart_summary_tab.jsp,cart_options_tab.jsp,cart_lodging_tab.jsp,cart_activities_tab.jsp,cart_transportation_tab.jsp'}"
                                        selectedTab="${param.tab}"
                                           defaultTab="Summary"
                                                    height="300"
                                                     width="100%"
                                                            uri="cart.do"/>