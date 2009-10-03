/**
 * 
 */
package com.sun.j2ee.blueprints.catalog.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sun.j2ee.blueprints.catalog.Lodging;

class LodgingRowMapper implements RowMapper {

	public Object mapRow(ResultSet result, int rowNum) throws SQLException {
        int i = 1;
        String lodgingId = result.getString(i++);
        String name = result.getString(i++);
        String description = result.getString(i++);
        double price = result.getDouble(i++);
        String location = result.getString(i++);
        return new Lodging(lodgingId , name, description, price, location);
	}    	
}