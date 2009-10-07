/**
 * 
 */
package com.sun.j2ee.blueprints.catalog.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.sun.j2ee.blueprints.catalog.AdventurePackage;

class AdventurePackageRowMapper implements RowMapper {

	private final List activities;
	private final String packageId;

	public AdventurePackageRowMapper(String packageId, List activities) {
		super();
		this.activities = activities;
		this.packageId = packageId;
	}

	public Object mapRow(ResultSet result, int rowNum) throws SQLException {
		int i = 1;
		String name = result.getString(i++).trim();
		String description = result.getString(i++).trim();
		double price = result.getDouble(i++);
		String location = result.getString(i++).trim();
		String lodgingId = result.getString(i++).trim();
		return new AdventurePackage(packageId, name, description, location,
				lodgingId, price, activities);
	}

}