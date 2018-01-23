package com.less.aspider.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import org.apache.commons.dbutils.QueryRunner;

public class JdbcUtils {

	public static QueryRunner getQueryRunner(String dbName) {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		try {
			dataSource.setDriverClass( "com.mysql.jdbc.Driver" );
			dataSource.setJdbcUrl( "jdbc:mysql://localhost:3306/" + dbName);
			dataSource.setUser("root");
			dataSource.setPassword("root");
			dataSource.setInitialPoolSize(30);
			dataSource.setMinPoolSize(20);
			dataSource.setMaxPoolSize(150);
			dataSource.setAcquireIncrement(20);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new QueryRunner(dataSource);
	}
}
