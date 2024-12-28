package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import tables.ClockingIn;
import tables.Employees;

public class ClockingInDAO extends DAO<ClockingIn> {
	
	private ResultSet rs;
	
	@Override
	public ClockingIn create(ClockingIn clo) {
		String query = "INSERT INTO clockingin (startClockingIn, endClockingIn, idEmployee, userCreate, dateCreate, userModif, dateModif) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement ps = super.getPs(query, PreparedStatement.RETURN_GENERATED_KEYS);
		try {
			ps.setTimestamp(1, clo.getStartClockingIn()); // startClockingIn
			ps.setTimestamp(2, clo.getEndClockingIn()); // endClockingIn
			ps.setLong(3, clo.getIdEmployee().getIdEmployee()); // idEmployee
			ps.setString(4, super.connect.getMetaData().getUserName()); // userCreate
			ps.setTimestamp(5, new Timestamp(System.currentTimeMillis())); // dateCreate
			ps.setString(6, super.connect.getMetaData().getUserName()); // userModif
			ps.setTimestamp(7, new Timestamp(System.currentTimeMillis())); // dateModif
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()){
				long id = rs.getLong(1);
				clo.setIdClockingIn(id);
			}
			rs.close();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clo;
	}
	
	@Override
	public ClockingIn update(ClockingIn clo) {
		String query = "UPDATE clockingin SET "
				+ "startClockingIn = ?, endClockingIn = ?, idEmployee = ?, userCreate = ?, dateCreate = ?, userModif = ?, dateModif = ? "
				+ "WHERE idClockingIn = ?;";
		PreparedStatement ps = super.getPs(query);
		try {
			ps.setTimestamp(1, clo.getStartClockingIn());
			ps.setTimestamp(2, clo.getEndClockingIn());
			ps.setLong(3, clo.getIdEmployee().getIdEmployee());
			ps.setString(4, clo.getUserCreate());
			ps.setTimestamp(5, clo.getDateCreate());
			ps.setString(6, super.connect.getMetaData().getUserName());
			ps.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
			ps.setLong(8, clo.getIdClockingIn());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clo;
	}
	
	@Override
	public void delete(ClockingIn clo) {
		String query = "DELETE FROM clockingin WHERE idClockingIn = ?;";
		PreparedStatement ps = super.getPs(query);
		try {
			ps.setLong(1, clo.getIdClockingIn());
			ps.executeQuery();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Il faudra en faire plusieurs
	public ArrayList<ClockingIn> readAll() {
		ArrayList<ClockingIn> clocks = new ArrayList<ClockingIn>();
		String query = "SELECT * FROM clockingin;";
		Statement stmt = super.getStmt();
		EmployeesDAO empD = new EmployeesDAO();
		Employees emp = null;
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				emp = empD.read(rs.getLong(4));
				ClockingIn clo = new ClockingIn(rs.getLong(1), rs.getTimestamp(2), rs.getTimestamp(3), emp, rs.getString(5), rs.getTimestamp(6), rs.getString(7), rs.getTimestamp(8));
				clocks.add(clo);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clocks;
	}
	

	public ClockingIn read(String codeEmp) {
		ClockingIn clo = null;
		// Un employé rechercher par son code, qui est présent actuellement (donc qui est pointé)
		String query = "SELECT clo.* FROM employees emp INNER JOIN clockingin clo USING(idEmployee) WHERE emp.codeEmp = ? AND clo.endClockingIn IS NULL ORDER BY clo.idClockingIn DESC LIMIT 1;";
		PreparedStatement ps = super.getPs(query);
		EmployeesDAO empDAO = new EmployeesDAO();
		Employees emp = null;
		try {
			ps.setString(1, codeEmp);
			rs = ps.executeQuery();
			while (rs.next()) {
				emp = empDAO.read(rs.getLong(4));
				clo = new ClockingIn(rs.getLong(1), rs.getTimestamp(2), rs.getTimestamp(3), emp, rs.getString(5), rs.getTimestamp(6), rs.getString(7), rs.getTimestamp(8));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return clo;
	}
	
}
