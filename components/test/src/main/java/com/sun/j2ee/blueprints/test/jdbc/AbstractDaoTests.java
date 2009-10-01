package com.sun.j2ee.blueprints.test.jdbc;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;

import com.sun.j2ee.blueprints.test.AbstractJndiIntegrationTests;

@ContextConfiguration(locations={"/context-infrastructure.xml"})
public abstract class AbstractDaoTests extends AbstractJndiIntegrationTests {

	protected final DataSource getDataSource() {
		JdbcTemplate template = (JdbcTemplate) simpleJdbcTemplate.getJdbcOperations();
		return template.getDataSource();
	}
	
	@Override
	protected void setupJndiContext() throws Exception {}

}
