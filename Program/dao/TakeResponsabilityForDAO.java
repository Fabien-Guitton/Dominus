package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import tables.Employees;
import tables.Orders;
import tables.TakeResponsabilityFor;

public class TakeResponsabilityForDAO extends DAO<TakeResponsabilityFor> {
	
	private ResultSet rs;
	private PreparedStatement psBatch;
	
	@Override
	public TakeResponsabilityFor create(TakeResponsabilityFor trf) {
		String query = "INSERT INTO takeresponsibilityfor (idOrder, idEmployee, deliveryTakeON, paymentTakeON, startDateTake, endDateTake, userCreate, dateCreate, userModif, dateModif) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement ps = super.getPs(query, PreparedStatement.RETURN_GENERATED_KEYS);
		try {
			ps.setLong(1, trf.getIdOrder().getIdOrder()); // idOrder
			ps.setLong(2, trf.getIdEmployee().getIdEmployee()); // idEmployee
			ps.setBoolean(3, trf.isDeliveryTakeON()); // deliveryTakeON
			ps.setBoolean(4, trf.isPaymentTakeON()); // paymentTakeON
			ps.setTimestamp(5, trf.getStartDateTake()); // startDateTake
			ps.setTimestamp(6, trf.getEndDateTake()); // endDateTake
			ps.setString(7, super.connect.getMetaData().getUserName()); // userCreate
			ps.setTimestamp(8, new Timestamp(System.currentTimeMillis())); // dateCreate
			ps.setString(9, super.connect.getMetaData().getUserName()); // userModif
			ps.setTimestamp(10, new Timestamp(System.currentTimeMillis())); // dateModif
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
	

	public void create(ArrayList<TakeResponsabilityFor> trfList) {
		String query = "INSERT INTO takeresponsibilityfor (idOrder, idEmployee, deliveryTakeON, paymentTakeON, startDateTake, endDateTake, userCreate, dateCreate, userModif, dateModif) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		if (trfList.size() > 0) {
			if (psBatch == null) {
				psBatch = super.getPs(query);
			}
			trfList.stream().forEach(trf -> {
				try {
					psBatch.setLong(1, trf.getIdOrder().getIdOrder()); // idOrder
					psBatch.setLong(2, trf.getIdEmployee().getIdEmployee()); // idEmployee
					psBatch.setBoolean(3, trf.isDeliveryTakeON()); // deliveryTakeON
					psBatch.setBoolean(4, trf.isPaymentTakeON()); // paymentTakeON
					psBatch.setTimestamp(5, trf.getStartDateTake()); // startDateTake
					psBatch.setTimestamp(6, trf.getEndDateTake()); // endDateTake
					psBatch.setString(7, super.connect.getMetaData().getUserName()); // userCreate
					psBatch.setTimestamp(8, new Timestamp(System.currentTimeMillis())); // dateCreate
					psBatch.setString(9, super.connect.getMetaData().getUserName()); // userModif
					psBatch.setTimestamp(10, new Timestamp(System.currentTimeMillis())); // dateModif
					psBatch.addBatch();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			});
			
			try {
				psBatch.executeBatch();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	@Override
	public TakeResponsabilityFor update(TakeResponsabilityFor trf) {
		String query = "UPDATE takeresponsibilityfor SET "
				+ "idOrder = ?, idEmployee = ?, deliveryTakeON = ?, paymentTakeON = ?, startDateTake = ?, endDateTake = ?, userCreate = ?, dateCreate = ?, userModif = ?, dateModif = ? "
				+ "WHERE idTakeResponsibilityFor = ?;";
		PreparedStatement ps = super.getPs(query);
		try {
			ps.setLong(1, trf.getIdOrder().getIdOrder()); // idOrder
			ps.setLong(2, trf.getIdEmployee().getIdEmployee()); // idEmployee
			ps.setBoolean(3, trf.isDeliveryTakeON()); // deliveryTakeON
			ps.setBoolean(4, trf.isPaymentTakeON()); // paymentTakeON
			ps.setTimestamp(5, trf.getStartDateTake()); // startDateTake
			ps.setTimestamp(6, trf.getEndDateTake()); // endDateTake
			ps.setString(7, trf.getUserCreate()); // userCreate
			ps.setTimestamp(8, trf.getDateCreate()); // dateCreate
			ps.setString(9, super.connect.getMetaData().getUserName()); // userModif
			ps.setTimestamp(10, new Timestamp(System.currentTimeMillis())); // dateModif
			ps.setLong(11, trf.getidTakeResponsabilityFor()); // id
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return trf;
	}
	
	public void update(ArrayList<TakeResponsabilityFor> trfList) {
		String query = "UPDATE takeresponsibilityfor SET "
				+ "idOrder = ?, idEmployee = ?, deliveryTakeON = ?, paymentTakeON = ?, startDateTake = ?, endDateTake = ?, userCreate = ?, dateCreate = ?, userModif = ?, dateModif = ? "
				+ "WHERE idTakeResponsibilityFor = ?;";
		if (trfList.size() > 0) {
			if (psBatch == null) {
				psBatch = super.getPs(query);
			}
			trfList.stream().forEach(trf -> {
				try {
					psBatch.setLong(1, trf.getIdOrder().getIdOrder()); // idOrder
					psBatch.setLong(2, trf.getIdEmployee().getIdEmployee()); // idEmployee
					psBatch.setBoolean(3, trf.isDeliveryTakeON()); // deliveryTakeON
					psBatch.setBoolean(4, trf.isPaymentTakeON()); // paymentTakeON
					psBatch.setTimestamp(5, trf.getStartDateTake()); // startDateTake
					psBatch.setTimestamp(6, trf.getEndDateTake()); // endDateTake
					psBatch.setString(7, trf.getUserCreate()); // userCreate
					psBatch.setTimestamp(8, trf.getDateCreate()); // dateCreate
					psBatch.setString(9, super.connect.getMetaData().getUserName()); // userModif
					psBatch.setTimestamp(10, new Timestamp(System.currentTimeMillis())); // dateModif
					psBatch.setLong(11, trf.getidTakeResponsabilityFor()); // id
					psBatch.addBatch();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			});
			
			try {
				psBatch.executeBatch();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void clearBatch() {
		try {
			if (psBatch != null) {
				psBatch.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		psBatch = null;
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
	
	public ArrayList<TakeResponsabilityFor> readAll() {
		ArrayList<TakeResponsabilityFor> trfs = new ArrayList<TakeResponsabilityFor>();
		String query = "SELECT * FROM takeresponsibilityfor;";
		Statement stmt = super.getStmt();
		OrdersDAO ordDAO = new OrdersDAO();
		Orders ord = null;
		EmployeesDAO empDAO = new EmployeesDAO();
		Employees emp = null;
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				ord = ordDAO.read(rs.getLong(2));
				emp = empDAO.read(rs.getLong(3));
				TakeResponsabilityFor trf = new TakeResponsabilityFor(rs.getLong(1), ord, emp, rs.getBoolean(4), rs.getBoolean(5), rs.getTimestamp(6), rs.getTimestamp(7), rs.getString(8), rs.getTimestamp(9), rs.getString(10), rs.getTimestamp(11));
				trfs.add(trf);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return trfs;
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
				trf = new TakeResponsabilityFor(rs.getLong(1), ord, emp, rs.getBoolean(4), rs.getBoolean(5), rs.getTimestamp(6), rs.getTimestamp(7), rs.getString(8), rs.getTimestamp(9), rs.getString(10), rs.getTimestamp(11));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return trf;
	}
	
	public ArrayList<TakeResponsabilityFor> readInDeliveryEmployee() {
		ArrayList<TakeResponsabilityFor> trfList = new ArrayList<TakeResponsabilityFor>();
		String query = "SELECT trf.idTakeResponsibilityFor, trf.idOrder, trf.idEmployee, trf.deliveryTakeON, trf.paymentTakeON, MIN(trf.startDateTake), trf.endDateTake, trf.userCreate, trf.dateCreate, trf.userModif, trf.dateModif FROM employees emp INNER JOIN takeresponsibilityfor trf USING (idEmployee) WHERE trf.deliveryTakeON AND trf.endDateTake IS NULL AND trf.startDateTake IS NOT NULL GROUP BY emp.idEmployee;";
		Statement stmt = super.getStmt();
		OrdersDAO ordDAO = new OrdersDAO();
		Orders ord = null;
		EmployeesDAO empDAO = new EmployeesDAO();
		Employees emp = null;
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				ord = ordDAO.read(rs.getLong(2));
				emp = empDAO.read(rs.getLong(3));
				TakeResponsabilityFor trf = new TakeResponsabilityFor(rs.getLong(1), ord, emp, rs.getBoolean(4), rs.getBoolean(5), rs.getTimestamp(6), rs.getTimestamp(7), rs.getString(8), rs.getTimestamp(9), rs.getString(10), rs.getTimestamp(11));
				trfList.add(trf);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return trfList;
	}
	
	public ArrayList<TakeResponsabilityFor> readInDeliveryEmployee(String codeEmp) {
		ArrayList<TakeResponsabilityFor> trfList = new ArrayList<TakeResponsabilityFor>();
		String query = "SELECT trf.* FROM employees emp INNER JOIN takeresponsibilityfor trf USING (idEmployee) WHERE emp.codeEmp = ? AND trf.deliveryTakeON AND trf.endDateTake IS NULL AND trf.startDateTake IS NOT NULL;";
		PreparedStatement ps = super.getPs(query);
		OrdersDAO ordDAO = new OrdersDAO();
		Orders ord = null;
		EmployeesDAO empDAO = new EmployeesDAO();
		Employees emp = null;
		try {
			ps.setString(1, codeEmp);
			rs = ps.executeQuery();
			while (rs.next()) {
				ord = ordDAO.read(rs.getLong(2));
				emp = empDAO.read(rs.getLong(3));
				TakeResponsabilityFor trf = new TakeResponsabilityFor(rs.getLong(1), ord, emp, rs.getBoolean(4), rs.getBoolean(5), rs.getTimestamp(6), rs.getTimestamp(7), rs.getString(8), rs.getTimestamp(9), rs.getString(10), rs.getTimestamp(11));
				trfList.add(trf);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return trfList;
	}
	
	// SELECT GROUP_CONCAT(idOrder) AS ordersIDs, idEmployee FROM `takeresponsibilityfor` WHERE deliveryTakeON AND endDateTake IS NULL GROUP BY idEmployee ORDER BY startDateTake;
	
	public HashMap<Employees, ArrayList<Orders>> readInDeliveryOrders() {
		HashMap<Employees, ArrayList<Orders>> empWithOrds = new HashMap<Employees, ArrayList<Orders>>();
		String query = "SELECT GROUP_CONCAT(idOrder) AS ordersIDs, idEmployee FROM `takeresponsibilityfor` WHERE deliveryTakeON AND endDateTake IS NULL GROUP BY idEmployee ORDER BY startDateTake;";
		Statement stmt = super.getStmt();
		OrdersDAO ordDAO = new OrdersDAO();
		EmployeesDAO empDAO = new EmployeesDAO();
		Employees emp = null;
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				ArrayList<Orders> ordersList = new ArrayList<Orders>();
				emp = empDAO.read(rs.getLong(2));
				ArrayList<String> ordersIDs = new ArrayList<>(Arrays.asList(rs.getString(1).split(",")));
				ordersIDs.stream().forEach(orderID -> {
					Orders ord = ordDAO.read(Long.valueOf(orderID));
					ordersList.add(ord);
				});
				empWithOrds.put(emp, ordersList);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return empWithOrds;
	}
	
}
