package com.sun.j2ee.blueprints.test.jdbc;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.test.jdbc.SimpleJdbcTestUtils;
import org.springframework.util.Assert;

/**
 * @author M. Deinum (mdeinum@gmail.com)
 */
public class DatabasePopulator implements InitializingBean, DisposableBean {

	private final Logger logger = LoggerFactory.getLogger(DatabasePopulator.class);

	// configurable properties


	private Resource schemaLocation;
	
	private Resource dropSchemaLocation;

	private Resource testDataLocation;

	private DataSource dataSource;
	private SimpleJdbcTemplate simpleJdbcTemplate;

	private boolean dropBeforeCreate;

	/**
	 * Sets the location of the file containing the schema DDL to export to the test database.
	 * @param schemaLocation the location of the database schema DDL
	 */
	public void setSchemaLocation(Resource schemaLocation) {
		this.schemaLocation = schemaLocation;
	}
	
	/**
	 * Sets the location of the file containing the schema DDL to drop to the test database.
	 * @param schemaLocation the location of the drop-schema DDL
	 */
	public void setDropSchemaLocation(Resource dropSchemaLocation) {
		this.dropSchemaLocation = dropSchemaLocation;
	}

	/**
	 * Sets the location of the file containing the test data to load into the database.
	 * @param testDataLocation the location of the test data file
	 */
	public void setTestDataLocation(Resource testDataLocation) {
		this.testDataLocation = testDataLocation;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.simpleJdbcTemplate=new SimpleJdbcTemplate(this.dataSource);
	}

	public void setDropBeforeCreate(boolean dropBeforeCreate) {
		this.dropBeforeCreate = dropBeforeCreate;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(this.dataSource, "DataSource is required.");
		Assert.notNull(this.schemaLocation, "SchemaLocation is required.");
		Assert.notNull(this.testDataLocation, "TestDataLocation is required.");
		if (dropBeforeCreate) {
			Assert.notNull(this.dropSchemaLocation, "dropBeforeCreate is true, dropSchemaLocation must be specified");
		}
		populateDataSource();
		if (logger.isDebugEnabled()) {
			logger.debug("Exported schema in {}", schemaLocation);
			logger.debug("Loaded test data in {}", testDataLocation);
		}
	}
	
	// internal helper methods

	private void populateDataSource() {
		if (dropBeforeCreate) {
			SimpleJdbcTestUtils.executeSqlScript(simpleJdbcTemplate, dropSchemaLocation, true);
		}
		SimpleJdbcTestUtils.executeSqlScript(simpleJdbcTemplate, this.schemaLocation, false);
		SimpleJdbcTestUtils.executeSqlScript(simpleJdbcTemplate, this.testDataLocation, true);
	}
	
	public void destroy() throws Exception {
		if (dropSchemaLocation != null) {
			SimpleJdbcTestUtils.executeSqlScript(simpleJdbcTemplate, dropSchemaLocation, true);
		}
	}

}