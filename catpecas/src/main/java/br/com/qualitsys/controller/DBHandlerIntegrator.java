/* -------------------------------------------------------------------------------------------------------

	 HikariCP Configuration:

     -----------------------------------------------------------------------------------------------------------
	We can use Java based configuration or we can use property file to configure HikariCP. 
	Let’s have a look at below properties.

	idleTimeout: Time in milliseconds for which connection object can stay in the pool as idle. 
	It works with minimumIdle and maximumPoolSize properties. After a specified time connection object will be released.

	connectionTimeout:  Time in milliseconds for which the client will wait for connection object from Pool. 
	If the time limit is reached then SQL Exception will be thrown.

	autoCommit: We can specify true or false and if it is set to true then it will automatically commit 
	every SQL statements you execute and if it is set to false then we need to commit SQL statements manually

	cachePrepStmts: Enable caching for Prepare Statement

	minimumIdle: Minimum number of connection objects needs to remain in the pool at any time.

	maximumPoolSize: Maximum number of connections that can stay in the pool.


	------------------------------------------------------------------------------------------------------ */

package br.com.qualitsys.controller;
import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DBHandlerIntegrator {
	private static HikariDataSource ds;

	static {

		HikariConfig config = new HikariConfig();

		String db = "qualitsy_catpecas";
		String porta = "3306";
		String username = "qualitsy_catpecas";
		String password = "#MHmarcam#00";

		String driver = "com.mysql.cj.jdbc.Driver"; 
		String url1 = "jdbc:mysql://";
		String host = "localhost";
		String url2 = "?useTimezone=true&serverTimezone=UTC";

		String url = url1+host+":"+porta+"/"+db+url2;

		config.setDriverClassName(driver); 

		config.setJdbcUrl(url);
		config.setUsername(username);
		config.setPassword(password);
		config.addDataSourceProperty("minimumIdle", "5");
		config.addDataSourceProperty("maximumPoolSize", "25");
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

		ds = new HikariDataSource(config);
	}

	public static  Connection getConn() throws SQLException, javax.servlet.ServletException{
		return ds.getConnection();
	}

}
