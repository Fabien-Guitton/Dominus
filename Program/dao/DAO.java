package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public abstract class DAO<T> {
	protected Connection connect;
	protected Statement stmt;
	
	public DAO() {
		open();
	}
	
	public void open() {
		try {
			connect = SingleConnection.getInstance();
			stmt = connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		} catch (Exception e1) {
			System.out.println("Error: There was a problem opening the DAO.");
			e1.printStackTrace();
		}
	}
	
	public abstract T create(T object);
	
	public abstract T update(T object);
	
	public abstract void delete(T object);
	
	public void close() {
		try {
			SingleConnection.close();
		} catch (Exception e2) {
			System.out.println("Error: There was a problem closing the DAO.");
			e2.printStackTrace();
		}
	}
}
