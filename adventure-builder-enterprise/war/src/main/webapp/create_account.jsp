<%-- Copyright 2004 Sun Microsystems, Inc.  All rights reserved.  You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: 
 http://adventurebuilder.dev.java.net/LICENSE.txt
 $Id: create_account.jsp,v 1.4 2004/05/26 00:06:31 inder Exp $ --%>
<h2>Create New Account</h2>

<p>Please use the form below to create a new account.</p>

<form name="createaccount" action="createcustomer.do" method="POST">

<table border="0" bgcolor="#EEEEEE" cellpadding="5" cellspacing="0">

  <input type="HIDDEN" name="target_action" value="createAccount"/>

  <tr>
  <td><b><label for="acct_familyName">Family Name</label>:</b></td> <td><input type="text" size="20" id="acct_familyName" name="acct_familyName" value ="Paternson"></td>
  </tr>

  <tr>
  <td><b><label for="acct_givenName">Given Name:</label></b></td> <td><input type="text" size="20" id="acct_givenName" name="acct_givenName" value="Duke"></td>
  </tr>

  <tr>
  <td><b><label for="acct_telephone">Telephone</label>:</b></td> <td><input type="text" size="20" id="acct_telephone" name="acct_telephone" value="(408) 1234-5678"></td>
  </tr>

  <tr>
  <td><b><label for="acct_email">Email</label>:</b></td> <td><input type="text" size="20" id="acct_email" name="acct_email" value="duke@somehost.com"></td>
  </tr>

  <tr>
  <td><b><label for="acct_street1">Street 1</label>:</b></td> <td><input type="text" size="20" id="acct_street1" name="acct_street1" value="1234 Duke Street"></td>
  </tr>

  <tr>
  <td><b><label for="acct_street2">Street 2:</label></b></td> <td><input type="text" size="20" id="acct_street2" name="acct_street2" value="Apartment 3"></td>
  </tr>

  <tr>
  <td><b><label for="acct_city">City</label>:</b></td> <td><input type="text" size="20" id="acct_city"  name="acct_city" value="Dukeville"></td>
  </tr>

  <tr>
  <td><b><label for="acct_state">State</label>:</b></td> <td><input type="text" size="20" id="acct_state" name="acct_state" value="CA"></td>
  </tr>

  <tr>
  <td><b><label for="acct_zipCode">Zip Code</label>:</b></td> <td><input type="text" size="20" id="acct_zipCode" name="acct_zipCode" value="95134"></td>
  </tr>

  <tr>
  <td><b><label for="acct_country">Country</label>:</b></td> <td><input type="text" size="20" id="acct_country" name="acct_country" value="USA"></td>
  </tr>

  <tr>
  <td align="right" colspan="2">
  <input type="RESET" value="Clear Form" name="Clear Form">
  <input type="SUBMIT" value="Create Account" name="Create Account">
  </td>
  </tr>
</table>
</form>

