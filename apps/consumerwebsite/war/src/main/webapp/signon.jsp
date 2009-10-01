<%-- Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: signon.jsp,v 1.5 2004/05/26 00:06:34 inder Exp $ --%>

<%@ taglib uri="/WEB-INF/waftags.tld" prefix="waf" %>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld" %>

<script language="javascript">
   function initForm() {
      newCustomerForm.submitButton.disabled = true;
   }

   function clearFields() {
      newCustomerForm.new_username.value = "";
      newCustomerForm.new_password.value = "";
      newCustomerForm.new_password2.value = "";
      newCustomerForm.submitButton.disabled = false;
   }

    function enableSubmit() {
      newCustomerForm.submitButton.disabled = false;
   }
</script>

<body onLoad="initForm()">
<h1>Sign In</h1>
<hr noshade="noshade">
<p><h2>Are you a returning customer?</h2></p>

<table cellpadding="20" cellspacing="0" border="1">
 <tr>
  <td valign="top">
   <waf:form  name="existingcustomer" action="j_signon_check" method="POST">
    <table cellpadding="5" cellspacing="0" border="0">
     <tr>
      <td  align="center" colspan="2">
       <b>Yes.</b>
      </td>
     </tr>
     <tr>
      <td align="right">
       <b><label for="username_id">User Name</label>:</b>
      </td>
      <td>
      <c:choose>
      <c:when  test="${cookie['bp_signon'] != null && cookie['bp_signon'] !=''}">
       <waf:input  type="text"
                                   id="username_id"
                              size="15"
                           name="j_username"
                    validation="validation">
       <waf:value><c:out value="${cookie['bp_signon'].value}"/></waf:value>
      </waf:input>
     </td>
    </tr>
    <tr>
     <td align="right">
      <b><label for="password_id">Password</label>:</b>
     </td>
     <td>
       <waf:input type="password"
                                  id="password_id"
                             size="15"
                           name="j_password"
                   validation="validation"
                            value=""/>
     </td>
    </tr>
    </c:when>
   <c:otherwise>
    <waf:input     type="text"
                                    id="username_id"
                               size="15"
                            name="j_username"
                     validation="validation"
                             value="j2ee"/>
     </td>
    </tr>
    <tr>
     <td  align="right">
      <b><label for="password_id">Password</label>:</b>
     </td>
     <td>
      <waf:input     type="password"
                                      id="password_id"
                                 size="15"
                               name="j_password"
                        validation="validation"
                              value="j2ee"/>
      </td>
     </tr>
  </c:otherwise>
 </c:choose>
     <tr>
      <td align="center" colspan="2">
       <input  name="Sign In" type="SUBMIT" value="Sign In">
      </td>
     </tr>
     <tr>
      <td align="center" colspan="2">
       <label for="remember_id">Remember My User Name</label>
       <waf:checkbox name="j_remember_username" id="remember_id">
        <waf:checked><c:out value="${cookie['bp_signon'] != null && cookie['bp_signon'] !=''}"/></waf:checked>
       </waf:checkbox>
      </td>
     </tr>
    </table>
   </waf:form>
  </td>
  <td valign="top">
  <waf:form name="newCustomerForm" action="createsignon.do" method="POST">
   <table cellpadding="5" cellspacing="0" border="0">
    <tr>
     <td  align="center" colspan="2">
      <b>No. I would like to sign up for an account.</b>
     </td>
    </tr>
    <tr>
     <td align="right">
      <b><label for="new_username">User Name</label>:</b>
     </td>
     <td>
      <waf:input      type="text"
                                      id="new_username"
                                 size="15"
                       validation="validation"
                              name="j_username"
                           onClick="clearFields()"
                              value="Enter User Name" />
     </td>
    </tr>
    <tr>
     <td c align="right">
      <b><label for="new_password">Password</label>:</b>
     </td>
     <td >
      <waf:input type="password"
                                    id="new_password"
                                 size="15"
                       validation="validation"
                              name="j_password"
                              value="Enter Password" />
     </td>
    </tr>
    <tr>
     <td align="right">
      <b><label for="new_password2">Password (Repeat)</label>:</b>
     </td>
     <td>
      <waf:input 
                                type="password"
                                      id="new_password2"
                                 size="15"
                        validation="validation"
                               name="j_password_2"
                               value="Enter Password" />
      </td>
     </tr>
     <tr>
      <td align="center" colspan="2">
       <input
                  name="submitButton"
                   type="SUBMIT"
                  value="Create New Account">
       </td>
      </tr>
     </table>
    </waf:form>
   </td>
 </tr>
</table>
</body>
