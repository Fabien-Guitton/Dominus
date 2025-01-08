package dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.cj.jdbc.MysqlDataSource;

public class SingleConnection {
	private static Connection connect;
	
	private SingleConnection(String serverName, String dbName, String login, String password) {
		String databaseName = "Dominus";
		// Connection with xampp
		String url = "jdbc:mysql://localhost:3306/" + databaseName + "?serverTimezone=Europe/Paris";
//		String login = "root"; // dans l'ideal un login de connexion pour l'application, et non root...
//		String password = ""; // mot de passe avec xampp
		connect = null;
		
		MysqlDataSource mysqlDS = new MysqlDataSource();
		mysqlDS.setURL(url);
		mysqlDS.setUser(login);
		mysqlDS.setPassword(password);

		try {
			connect = mysqlDS.getConnection();
		} catch (SQLException e1) {
			System.err.println("Error: There was a problem during the connection opening process.");
			e1.printStackTrace();
		}
		
	}
	
	public static Connection getInstance(String serverName, String dbName, String login, String password) throws ClassNotFoundException, SQLException {
		if(connect == null) {
			new SingleConnection(serverName, dbName, login, password);
		}
		return connect;
	}

	public static Connection getInstance() throws ClassNotFoundException, SQLException {
		if(connect == null) {
			new SingleConnection("127.0.0.1", "Dominus", "root", "");
		}
		return connect;
	}
	
	public static void setAutoCommit(Boolean autoCommit) {
		try {
			connect.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close() {
		try {
			connect.close();
		} catch (SQLException e2) {
			System.err.println("Error: There was a problem closing the connection.");
			e2.printStackTrace();
		}
	}
}
