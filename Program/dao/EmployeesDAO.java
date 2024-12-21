package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import tables.Employees;

public class EmployeesDAO extends DAO<Employees> {
	
	private ResultSet rs;
	
	@Override
	public Employees create(Employees emp) {
		String query = "INSERT INTO employees (nameEmp, codeEmp, roleEmp, telEmp, userCreate, dateCreate, userModif, dateModif) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement ps = super.getPs(query, PreparedStatement.RETURN_GENERATED_KEYS);
		try {
			ps.setString(1, emp.getNameEmp()); // nameEmp
			ps.setString(2, emp.getCodeEmp()); // codeEmp
			ps.setString(3, emp.getRoleEmp()); // roleEmp
			ps.setString(3, emp.getTelEmp()); // telEmp
			ps.setString(4, super.connect.getMetaData().getUserName()); // userCreate
			ps.setTimestamp(5, new Timestamp(System.currentTimeMillis())); // dateCreate
			ps.setString(6, super.connect.getMetaData().getUserName()); // userModif
			ps.setTimestamp(7, new Timestamp(System.currentTimeMillis())); // dateModif
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()){
				long id = rs.getLong(1);
				emp.setIdEmployee(id);
			}
			rs.close();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emp;
	}
	
	@Override
	public Employees update(Employees emp) {
		String query = "UPDATE employees SET "
				+ "nameEmp = ?, codeEmp = ?, roleEmp = ?, telEmp = ?, userCreate = ?, dateCreate = ?, userModif = ?, dateModif = ? "
				+ "WHERE idEmployee = ?;";
		PreparedStatement ps = super.getPs(query);
		try {
			ps.setString(1, emp.getNameEmp()); // nameEmp
			ps.setString(2, emp.getCodeEmp()); // codeEmp
			ps.setString(3, emp.getRoleEmp()); // roleEmp
			ps.setString(3, emp.getTelEmp()); // telEmp
			ps.setString(4, emp.getUserCreate()); // userCreate
			ps.setTimestamp(5, emp.getDateCreate()); // dateCreate
			ps.setString(6, super.connect.getMetaData().getUserName()); // userModif
			ps.setTimestamp(7, new Timestamp(System.currentTimeMillis())); // dateModif
			ps.setLong(8, emp.getIdEmployee()); // id
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emp;
	}
	
	@Override
	public void delete(Employees emp) {
		String query = "DELETE FROM employees WHERE idEmployee = ?;";
		PreparedStatement ps = super.getPs(query);
		try {
			ps.setLong(1, emp.getIdEmployee());
			ps.executeQuery();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Employees> readAll() {
		ArrayList<Employees> employees = new ArrayList<Employees>();
		String query = "SELECT * FROM employees;";
		Statement stmt = super.getStmt();
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				Employees emp = new Employees(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getTimestamp(7), rs.getString(8), rs.getTimestamp(9));
				employees.add(emp);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employees;
	}
	
	public Employees read(long idEmployee) {
		Employees emp = null;
		String query = "SELECT * FROM employees WHERE idEmployee = ?;";
		PreparedStatement ps = super.getPs(query);
		try {
			ps.setLong(1, idEmployee);
			rs = ps.executeQuery();
			while (rs.next()) {
				emp = new Employees(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getTimestamp(7), rs.getString(8), rs.getTimestamp(9));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emp;
	}
	
	public Employees readCode(String codeEmp) {
		Employees emp = null;
		String query = "SELECT * FROM employees WHERE codeEmp = ?;";
		PreparedStatement ps = super.getPs(query);
		try {
			ps.setString(1, codeEmp);
			rs = ps.executeQuery();
			while (rs.next()) {
				emp = new Employees(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getTimestamp(7), rs.getString(8), rs.getTimestamp(9));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emp;
	}
}
