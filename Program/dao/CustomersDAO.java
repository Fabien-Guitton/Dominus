package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import tables.Customers;

public class CustomersDAO extends DAO<Customers> {
	
	private ResultSet rs;
	
	@Override
	public Customers create(Customers cst) {
		String query = "INSERT INTO customers (nameCst, telCst, streetNumberCst, streetNameCst, postcodeCst, instructionsCst, internalComCst, userCreate, dateCreate, userModif, dateModif) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement ps = super.getPs(query, PreparedStatement.RETURN_GENERATED_KEYS);
		try {
			ps.setString(1, cst.getNameCst()); // nameCst
			ps.setString(2, cst.getTelCst()); // telCst
			ps.setString(3, cst.getStreetNumberCst()); // streetNumberCst
			ps.setString(4, cst.getStreetNameCst()); // streetNameCst
			ps.setString(5, cst.getPostcodeCst()); // postcodeCst
			ps.setString(6, cst.getInstructionsCst()); // instructionsCst
			ps.setString(7, cst.getInternalComCst()); // internalComCst
			ps.setString(8, super.connect.getMetaData().getUserName()); // userCreate
			ps.setTimestamp(9, new Timestamp(System.currentTimeMillis())); // dateCreate
			ps.setString(10, super.connect.getMetaData().getUserName()); // userModif
			ps.setTimestamp(11, new Timestamp(System.currentTimeMillis())); // dateModif
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()){
				long id = rs.getLong(1);
				cst.setIdCustomer(id);
			}
			rs.close();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cst;
	}
	
	@Override
	public Customers update(Customers cst) {
		String query = "UPDATE customers SET "
				+ "nameCst = ?, telCst = ?, streetNumberCst = ?, streetNameCst = ?, postcodeCst = ?, instructionsCst = ?, internalComCst = ?, userCreate = ?, dateCreate = ?, userModif = ?, dateModif = ? "
				+ "WHERE idCustomer = ?;";
		PreparedStatement ps = super.getPs(query);
		try {
			ps.setString(1, cst.getNameCst());
			ps.setString(2, cst.getTelCst());
			ps.setString(3, cst.getStreetNumberCst());
			ps.setString(4, cst.getStreetNameCst());
			ps.setString(5, cst.getPostcodeCst());
			ps.setString(6, cst.getInstructionsCst());
			ps.setString(7, cst.getInternalComCst());
			ps.setString(8, cst.getUserCreate());
			ps.setTimestamp(9, cst.getDateCreate());
			ps.setString(10, super.connect.getMetaData().getUserName());
			ps.setTimestamp(11, new Timestamp(System.currentTimeMillis()));
			ps.setLong(12, cst.getIdCustomer());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cst;
	}
	
	@Override
	public void delete(Customers cst) {
		String query = "DELETE FROM customers WHERE idCustomer = ?;";
		PreparedStatement ps = super.getPs(query);
		try {
			ps.setLong(1, cst.getIdCustomer());
			ps.executeQuery();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Customers> readAll() {
		ArrayList<Customers> customers = new ArrayList<Customers>();
		String query = "SELECT * FROM customers;";
		Statement stmt = super.getStmt();
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				Customers cst = new Customers(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getTimestamp(10), rs.getString(11), rs.getTimestamp(12));
				customers.add(cst);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customers;
	}
	
	public Customers read(long idCustomer) {
		Customers cst = null;
		String query = "SELECT * FROM customers WHERE idCustomer = ?;";
		PreparedStatement ps = super.getPs(query);
		try {
			ps.setLong(1, idCustomer);
			rs = ps.executeQuery();
			while (rs.next()) {
				cst = new Customers(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getTimestamp(10), rs.getString(11), rs.getTimestamp(12));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cst;
	}
}
