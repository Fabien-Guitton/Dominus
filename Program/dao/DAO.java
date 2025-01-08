package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DAO<T> {
	protected Connection connect;
	
	public DAO() {
		open();
	}
	
	public void open() {
		try {
			connect = SingleConnection.getInstance();
		} catch (Exception e1) {
			System.out.println("Error: There was a problem opening the DAO.");
			e1.printStackTrace();
		}
	}
	
	public abstract T create(T object);
	
	public abstract T update(T object);
	
	public abstract void delete(T object);
	
	public Statement getStmt() {
		Statement stmt = null;
		try{
			stmt = connect.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stmt;
	}
	
	public PreparedStatement getPs(String query) {
		 PreparedStatement ps = null;
		try {
			ps = connect.prepareStatement(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}
	
	public PreparedStatement getPs(String query, int param) {
		 PreparedStatement ps = null;
		try {
			ps = connect.prepareStatement(query, param);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}
	
	public void close() {
		try {
			SingleConnection.close();
		} catch (Exception e2) {
			System.out.println("Error: There was a problem closing the DAO.");
			e2.printStackTrace();
		}
	}
}
