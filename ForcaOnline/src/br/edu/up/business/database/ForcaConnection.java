package br.edu.up.business.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class ForcaConnection {

	private static final String CONNECTION_URL = "jdbc:sqlserver://ajeuq4qabd.database.windows.net:1433;database=forca;user=forca@ajeuq4qabd;password={Daniel01};encrypt=true;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";

	private static ForcaConnection conn = null;

	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;

	private ForcaConnection() {

	}

	public static ForcaConnection getInstance() throws Exception {
			conn = new ForcaConnection();
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn.setConnection(DriverManager.getConnection(CONNECTION_URL));
		return conn;
	}

	public void executeCommand(String sql) throws Exception {
		this.statement = this.connection.createStatement();
		this.statement.execute(sql);
		return;
	}

	public ResultSet executeQuery(String sql) throws Exception {
		this.statement = this.connection.createStatement();
		this.resultSet = this.statement.executeQuery(sql);
		return resultSet;
	}

	public void finalize() {
		if (resultSet != null)
			try {
				resultSet.close();
			} catch (Exception e) {
			}
		if (statement != null)
			try {
				statement.close();
			} catch (Exception e) {
			}
		if (connection != null)
			try {
				connection.close();
			} catch (Exception e) {
			}
	}

	private void setConnection(Connection connection) {
		this.connection = connection;
	}

}
