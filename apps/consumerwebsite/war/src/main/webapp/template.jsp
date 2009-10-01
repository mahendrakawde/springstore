<%-- Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: template.jsp,v 1.3 2004/05/26 00:06:35 inder Exp $ --%>
<%@ taglib prefix="template" uri="/WEB-INF/template.tld" %>

 <head>
  <title><template:insert parameter="title" /></title>
  <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
  <link href="styles.css" rel="stylesheet" type="text/css">
 </head>

 <body bgcolor="#FFFFFF" leftmargin="0" marginwidth="0" marginheight="0">

  <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">

   <!--banner done start -->
   <tr valign="top" align="right">
    <td height="143" colspan="4" valign="bottom" nowrap="nowrap">
      <table width="100%" height="143" border="0" cellpadding="0" cellspacing="0">
       <tr valign="bottom">
        <td height="116">
         <template:insert parameter="banner"/>
        </td>
       </tr>
      </table>
    </td>
   </tr>
  <!--banner done -->

  <tr align="center"> 
    <td height="12" colspan="3"><image src="images/spacer.gif" height="12" alt=""></td>
  </tr>

   <tr valign="top">

    <td  width="10">
      &nbsp;
    </td>

     <!--sidebar start -->
     <td  valign="top" width="30%" background="images/green-left.gif">
      <template:insert parameter="sidebar" />
     </td>
     <!--sidebar done -->

     <!--body start -->
     <td valign="top" height="100%">
      <table width="100%" height="100%" border="0" align="center" cellpadding="0">
       <tr> 
        <td align="left" valign="top" bgcolor="#ffffff" cellpadding="0">
         <!-- body start-->
         <template:insert parameter="body" />
         <!-- body done-->
        </td>
       </tr>
      </table>
     </td>     
    <td width="12">&nbsp;</td>
    <!--body done -->
   </tr>

  <tr align="center"> 
    <td height="10" colspan="3"><image src="images/spacer.gif" width="1" height="10" alt=""></td>
  </tr>

  <!-- footer start-->
   <tr>
    <td width="12">&nbsp;</td><td colspan="2" cellpading="12"><template:insert parameter="footer" /></td>
   </tr>
   <tr><td height="12"><image src="images/spacer.gif" height="12" alt=""></td></tr>
  <!-- footer done-->
  </table>

 </body>
</html>
