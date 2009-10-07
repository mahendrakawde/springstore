/**
 * 
 */
package com.sun.j2ee.blueprints.customer.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sun.j2ee.blueprints.customer.Account;
import com.sun.j2ee.blueprints.customer.Address;
import com.sun.j2ee.blueprints.customer.ContactInformation;

class AccountRowMapper implements RowMapper {

	public Object mapRow(ResultSet result, int rowNum) throws SQLException {
		int i = 1;
		String userId = result.getString(i++);
		String email = result.getString(i++);
		String firstName = result.getString(i++);
		String lastName = result.getString(i++);
		String street1 = result.getString(i++);
		String street2 = result.getString(i++);
		String city = result.getString(i++);
		String state = result.getString(i++);
		String zip = result.getString(i++);
		String country = result.getString(i++);
		String phone = result.getString(i++);

		Address addr = new Address(street1, street2, city, state, zip, country);
		ContactInformation info = new ContactInformation(lastName, firstName,
				phone, email, addr);
		return (new Account(userId, info));
	}
}