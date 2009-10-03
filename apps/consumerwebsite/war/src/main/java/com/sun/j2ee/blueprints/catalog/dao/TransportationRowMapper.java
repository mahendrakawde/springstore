/**
 * 
 */
package com.sun.j2ee.blueprints.catalog.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sun.j2ee.blueprints.catalog.Transportation;

class TransportationRowMapper implements RowMapper {

	public Object mapRow(ResultSet result, int rowNum) throws SQLException {
           int i = 1;
            String transportId = result.getString(i++);
            String name = result.getString(i++);
            String description = result.getString(i++);
            String imageURI = result.getString(i++);
            double price = result.getDouble(i++);
            String origin = result.getString(i++);
            String destination  = result.getString(i++);
            String carrier  = result.getString(i++);
            String departureTime = result.getString(i++);
            String arrivalTime = result.getString(i++);
            String travelClass = result.getString(i++);
            return new Transportation(transportId , name, description, imageURI, price, origin, destination, carrier, departureTime, arrivalTime, travelClass);
	}    	
}