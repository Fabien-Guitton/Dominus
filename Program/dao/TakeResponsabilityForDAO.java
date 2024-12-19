package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import tables.Employees;
import tables.Orders;
import tables.TakeResponsabilityFor;

public class TakeResponsabilityForDAO extends DAO<TakeResponsabilityFor> {
	
	private ResultSet rs;
	
	@Override
	public TakeResponsabilityFor create(TakeResponsabilityFor trf) {
		String query = "INSERT INTO takeresponsibilityfor (idOrder, idEmployee, deliveryTakeON, paymentTakeON, userCreate, dateCreate, userModif, dateModif) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement ps = super.getPs(query, PreparedStatement.RETURN_GENERATED_KEYS);
		try {
			ps.setLong(1, trf.getIdOrder().getIdOrder()); // idOrder
			ps.setLong(2, trf.getIdEmployee().getIdEmployee()); // idEmployee
			ps.setBoolean(3, trf.isDeliveryTakeON()); // deliveryTakeON
			ps.setBoolean(4, trf.isPaymentTakeON()); // paymentTakeON
			ps.setString(5, super.connect.getMetaData().getUserName()); // userCreate
			ps.setTimestamp(6, new Timestamp(System.currentTimeMillis())); // dateCreate
			ps.setString(7, super.connect.getMetaData().getUserName()); // userModif
			ps.setTimestamp(8, new Timestamp(System.currentTimeMillis())); // dateModif
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()){
				long id = rs.getLong(1);
				trf.setidTakeResponsabilityFor(id);
			}
			rs.close();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return trf;
	}
	
	@Override
	public TakeResponsabilityFor update(TakeResponsabilityFor trf) {
		String query = "UPDATE takeresponsibilityfor SET "
				+ "idOrder = ?, idEmployee = ?, deliveryTakeON = ?, paymentTakeON = ?, userCreate = ?, dateCreate = ?, userModif = ?, dateModif = ? "
				+ "WHERE idTakeResponsibilityFor = ?;";
		PreparedStatement ps = super.getPs(query);
		try {
			ps.setLong(1, trf.getIdOrder().getIdOrder()); // idOrder
			ps.setLong(2, trf.getIdEmployee().getIdEmployee()); // idEmployee
			ps.setBoolean(3, trf.isDeliveryTakeON()); // deliveryTakeON
			ps.setBoolean(4, trf.isPaymentTakeON()); // paymentTakeON
			ps.setString(5, trf.getUserCreate()); // userCreate
			ps.setTimestamp(6, trf.getDateCreate()); // dateCreate
			ps.setString(7, super.connect.getMetaData().getUserName()); // userModif
			ps.setTimestamp(8, new Timestamp(System.currentTimeMillis())); // dateModif
			ps.setLong(9, trf.getidTakeResponsabilityFor()); // id
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return trf;
	}
	
	@Override
	public void delete(TakeResponsabilityFor trf) {
		String query = "DELETE FROM takeresponsibilityfor WHERE idTakeResponsibilityFor = ?;";
		PreparedStatement ps = super.getPs(query);
		try {
			ps.setLong(1, trf.getidTakeResponsabilityFor());
			ps.executeQuery();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public TakeResponsabilityFor read(long idTakeResponsibilityFor) {
		TakeResponsabilityFor trf = null;
		String query = "SELECT * FROM takeresponsibilityfor WHERE idTakeResponsibilityFor = ?;";
		PreparedStatement ps = super.getPs(query);
		OrdersDAO ordDAO = new OrdersDAO();
		Orders ord = null;
		EmployeesDAO empDAO = new EmployeesDAO();
		Employees emp = null;
		try {
			ps.setLong(1, idTakeResponsibilityFor);
			rs = ps.executeQuery();
			while (rs.next()) {
				ord = ordDAO.read(rs.getLong(2));
				emp = empDAO.read(rs.getLong(3));
				trf = new TakeResponsabilityFor(rs.getLong(1), ord, emp, rs.getBoolean(4), rs.getBoolean(5), rs.getString(6), rs.getTimestamp(7), rs.getString(8), rs.getTimestamp(9));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return trf;
	}
}
