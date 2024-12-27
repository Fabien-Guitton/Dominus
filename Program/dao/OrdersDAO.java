package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import tables.Customers;
import tables.Discounts;
import tables.Orders;

public class OrdersDAO extends DAO<Orders> {
	
	private ResultSet rs;
	private PreparedStatement psBatch;
	
	@Override
	public Orders create(Orders ord) {
		String query = "INSERT INTO orders (nameOrd, typeOrd, payOrdON, reductionOrd, takingDateOrd, readyDateOrd, priceETOrd, priceITOrd, instructionsOrd, idDiscount, idCustomer, userCreate, dateCreate, userModif, dateModif) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement ps = super.getPs(query, PreparedStatement.RETURN_GENERATED_KEYS);
		try {
			ps.setString(1, ord.getNameOrd()); // nameOrd
			ps.setString(2, ord.getTypeOrd()); // typeOrd
			ps.setBoolean(3, ord.isPayOrdON()); // payOrdON
			ps.setDouble(4, ord.getReductionOrd()); // reductionOrd
			ps.setTimestamp(5, ord.getTakingDateOrd()); // takingDateOrd
			ps.setTimestamp(6, ord.getReadyDateOrd()); // readyDateOrd
			ps.setDouble(7, ord.getPriceETOrd()); // priceETOrd
			ps.setDouble(8, ord.getPriceITOrd()); // priceITOrd
			ps.setString(9, ord.getInstructionsOrd()); // instructionsOrd
			ps.setLong(10, ord.getIdDiscount().getIdDiscount()); // idDiscount
			ps.setLong(11, ord.getIdCustomer().getIdCustomer()); // idCustomer
			ps.setString(12, super.connect.getMetaData().getUserName()); // userCreate
			ps.setTimestamp(13, new Timestamp(System.currentTimeMillis())); // dateCreate
			ps.setString(14, super.connect.getMetaData().getUserName()); // userModif
			ps.setTimestamp(15, new Timestamp(System.currentTimeMillis())); // dateModif
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()){
				long id = rs.getLong(1);
				ord.setIdOrder(id);
			}
			rs.close();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ord;
	}
	
	@Override
	public Orders update(Orders ord) {
		String query = "UPDATE orders SET "
				+ "nameOrd = ?, typeOrd = ?, payOrdON = ?, reductionOrd = ?, takingDateOrd = ?, readyDateOrd = ?, priceETOrd = ?, priceITOrd = ?, instructionsOrd = ?, idDiscount = ?, idCustomer = ?, userCreate = ?, dateCreate = ?, userModif = ?, dateModif = ? "
				+ "WHERE idOrder = ?;";
		PreparedStatement ps = super.getPs(query);
		try {
			ps.setString(1, ord.getNameOrd()); // nameOrd
			ps.setString(2, ord.getTypeOrd()); // typeOrd
			ps.setBoolean(3, ord.isPayOrdON()); // payOrdON
			ps.setDouble(4, ord.getReductionOrd()); // reductionOrd
			ps.setTimestamp(5, ord.getTakingDateOrd()); // takingDateOrd
			ps.setTimestamp(6, ord.getReadyDateOrd()); // readyDateOrd
			ps.setDouble(7, ord.getPriceETOrd()); // priceETOrd
			ps.setDouble(8, ord.getPriceITOrd()); // priceITOrd
			ps.setString(9, ord.getInstructionsOrd()); // instructionsOrd
			if (ord.getIdDiscount() != null) {
				ps.setLong(10, ord.getIdDiscount().getIdDiscount()); // idDiscount
			}else {
				ps.setObject(10, null); // idDiscount
			}
			if (ord.getIdCustomer() != null) {
				ps.setLong(11, ord.getIdCustomer().getIdCustomer()); // idCustomer
			}else {
				ps.setObject(11, null); // idCustomer
			}
			ps.setString(12, ord.getUserCreate()); // userCreate
			ps.setTimestamp(13, ord.getDateCreate()); // dateCreate
			ps.setString(14, super.connect.getMetaData().getUserName()); // userModif
			ps.setTimestamp(15, new Timestamp(System.currentTimeMillis())); // dateModif
			ps.setLong(16, ord.getIdOrder()); // id
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ord;
	}
	
	
	public void update(ArrayList<Orders> orders) {
		String query = "UPDATE orders SET "
				+ "nameOrd = ?, typeOrd = ?, payOrdON = ?, reductionOrd = ?, takingDateOrd = ?, readyDateOrd = ?, priceETOrd = ?, priceITOrd = ?, instructionsOrd = ?, idDiscount = ?, idCustomer = ?, userCreate = ?, dateCreate = ?, userModif = ?, dateModif = ? "
				+ "WHERE idOrder = ?;";
		
		if (orders.size() > 0) {
			if (psBatch == null) {
				psBatch = super.getPs(query);
			}
			
			orders.stream().forEach(ord -> {
				try {
					psBatch.setString(1, ord.getNameOrd()); // nameOrd
					psBatch.setString(2, ord.getTypeOrd()); // typeOrd
					psBatch.setBoolean(3, ord.isPayOrdON()); // payOrdON
					psBatch.setDouble(4, ord.getReductionOrd()); // reductionOrd
					psBatch.setTimestamp(5, ord.getTakingDateOrd()); // takingDateOrd
					psBatch.setTimestamp(6, ord.getReadyDateOrd()); // readyDateOrd
					psBatch.setDouble(7, ord.getPriceETOrd()); // priceETOrd
					psBatch.setDouble(8, ord.getPriceITOrd()); // priceITOrd
					psBatch.setString(9, ord.getInstructionsOrd()); // instructionsOrd
					if (ord.getIdDiscount() != null) {
						psBatch.setLong(10, ord.getIdDiscount().getIdDiscount()); // idDiscount
					}else {
						psBatch.setObject(10, null); // idDiscount
					}
					if (ord.getIdCustomer() != null) {
						psBatch.setLong(11, ord.getIdCustomer().getIdCustomer()); // idCustomer
					}else {
						psBatch.setObject(11, null); // idCustomer
					}
					psBatch.setString(12, ord.getUserCreate()); // userCreate
					psBatch.setTimestamp(13, ord.getDateCreate()); // dateCreate
					psBatch.setString(14, super.connect.getMetaData().getUserName()); // userModif
					psBatch.setTimestamp(15, new Timestamp(System.currentTimeMillis())); // dateModif
					psBatch.setLong(16, ord.getIdOrder()); // id
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
	public void delete(Orders ord) {
		String query = "DELETE FROM orders WHERE idOrder = ?;";
		PreparedStatement ps = super.getPs(query);
		try {
			ps.setLong(1, ord.getIdOrder());
			ps.executeQuery();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Orders> readAll() {
		ArrayList<Orders> orders = new ArrayList<Orders>();
		String query = "SELECT * FROM orders;";
		Statement stmt = super.getStmt();
		DiscountsDAO discDAO = new DiscountsDAO();
		Discounts disc = null;
		CustomersDAO custDAO = new CustomersDAO();
		Customers cust = null;
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				disc = discDAO.read(rs.getLong(11));
				cust = custDAO.read(rs.getLong(12));
				Orders ord = new Orders(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getBoolean(4), rs.getDouble(5), rs.getTimestamp(6), rs.getTimestamp(7), rs.getDouble(8), rs.getDouble(9), rs.getString(10), disc, cust, rs.getString(13), rs.getTimestamp(14), rs.getString(15), rs.getTimestamp(16));
				orders.add(ord);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}
	
	public Orders read(long idOrder) {
		Orders ord = null;
		String query = "SELECT * FROM orders WHERE idOrder = ?;";
		PreparedStatement ps = super.getPs(query);
		DiscountsDAO discDAO = new DiscountsDAO();
		Discounts disc = null;
		CustomersDAO custDAO = new CustomersDAO();
		Customers cust = null;
		try {
			ps.setLong(1, idOrder);
			rs = ps.executeQuery();
			while (rs.next()) {
				disc = discDAO.read(rs.getLong(11));
				cust = custDAO.read(rs.getLong(12));
				ord = new Orders(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getBoolean(4), rs.getDouble(5), rs.getTimestamp(6), rs.getTimestamp(7), rs.getDouble(8), rs.getDouble(9), rs.getString(10), disc, cust, rs.getString(13), rs.getTimestamp(14), rs.getString(15), rs.getTimestamp(16));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ord;
	}
	
	public ArrayList<Orders> readToday() {
        LocalDateTime Today = LocalDate.now().atTime(00, 00, 01);
		ArrayList<Orders> orders = new ArrayList<Orders>();
		String query = "SELECT * FROM orders WHERE payOrdON AND takingDateOrd > ? ORDER BY idOrder ASC;";
		PreparedStatement ps = super.getPs(query);
		DiscountsDAO discDAO = new DiscountsDAO();
		Discounts disc = null;
		CustomersDAO custDAO = new CustomersDAO();
		Customers cust = null;
		try {
			ps.setTimestamp(1, Timestamp.valueOf(Today));
			rs = ps.executeQuery();
			while (rs.next()) {
				disc = discDAO.read(rs.getLong(11));
				cust = custDAO.read(rs.getLong(12));
				Orders ord = new Orders(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getBoolean(4), rs.getDouble(5), rs.getTimestamp(6), rs.getTimestamp(7), rs.getDouble(8), rs.getDouble(9), rs.getString(10), disc, cust, rs.getString(13), rs.getTimestamp(14), rs.getString(15), rs.getTimestamp(16));
				orders.add(ord);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}
	
	public ArrayList<Orders> readNotPayed() {
		ArrayList<Orders> orders = new ArrayList<Orders>();
		String query = "SELECT * FROM orders WHERE NOT payOrdON ORDER BY idOrder ASC;";
		Statement stmt = super.getStmt();
		DiscountsDAO discDAO = new DiscountsDAO();
		Discounts disc = null;
		CustomersDAO custDAO = new CustomersDAO();
		Customers cust = null;
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				disc = discDAO.read(rs.getLong(11));
				cust = custDAO.read(rs.getLong(12));
				Orders ord = new Orders(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getBoolean(4), rs.getDouble(5), rs.getTimestamp(6), rs.getTimestamp(7), rs.getDouble(8), rs.getDouble(9), rs.getString(10), disc, cust, rs.getString(13), rs.getTimestamp(14), rs.getString(15), rs.getTimestamp(16));
				orders.add(ord);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}
	
	public ArrayList<Orders> readNotPayed(String sortColumn, String sortType) {
		ArrayList<Orders> orders = new ArrayList<Orders>();
		String query = "SELECT * FROM orders WHERE NOT payOrdON ORDER BY " + sortColumn + " " + sortType + ";";
		Statement stmt = super.getStmt();
		DiscountsDAO discDAO = new DiscountsDAO();
		Discounts disc = null;
		CustomersDAO custDAO = new CustomersDAO();
		Customers cust = null;
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				disc = discDAO.read(rs.getLong(11));
				cust = custDAO.read(rs.getLong(12));
				Orders ord = new Orders(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getBoolean(4), rs.getDouble(5), rs.getTimestamp(6), rs.getTimestamp(7), rs.getDouble(8), rs.getDouble(9), rs.getString(10), disc, cust, rs.getString(13), rs.getTimestamp(14), rs.getString(15), rs.getTimestamp(16));
				orders.add(ord);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}
	
	// To read today with ordered result
	public ArrayList<Orders> readToday(String sortColumn, String sortType) {
        LocalDateTime Today = LocalDate.now().atTime(00, 00, 01);
		ArrayList<Orders> orders = new ArrayList<Orders>();
		String query = "SELECT * FROM orders WHERE payOrdON AND takingDateOrd > ? ORDER BY " + sortColumn + " " + sortType + ";";
		PreparedStatement ps = super.getPs(query);
		DiscountsDAO discDAO = new DiscountsDAO();
		Discounts disc = null;
		CustomersDAO custDAO = new CustomersDAO();
		Customers cust = null;
		try {
			ps.setTimestamp(1, Timestamp.valueOf(Today));
			rs = ps.executeQuery();
			while (rs.next()) {
				disc = discDAO.read(rs.getLong(11));
				cust = custDAO.read(rs.getLong(12));
				Orders ord = new Orders(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getBoolean(4), rs.getDouble(5), rs.getTimestamp(6), rs.getTimestamp(7), rs.getDouble(8), rs.getDouble(9), rs.getString(10), disc, cust, rs.getString(13), rs.getTimestamp(14), rs.getString(15), rs.getTimestamp(16));
				orders.add(ord);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}
	
	// To read delivery order with ordered result and only the order that will be ready soon (7 minutes)
	public ArrayList<Orders> readDeliveryOrderReady() {
		ArrayList<Orders> orders = new ArrayList<Orders>();
		String query = "SELECT * FROM orders ord WHERE ord.typeOrd = 'Delivery' AND ord.readyDateOrd <= DATE_ADD(NOW(), INTERVAL 7 MINUTE) AND ord.idOrder NOT IN (SELECT trf.idOrder FROM takeresponsibilityfor trf WHERE trf.deliveryTakeON) ORDER BY ord.takingDateOrd;";
		Statement stmt = super.getStmt();
		DiscountsDAO discDAO = new DiscountsDAO();
		Discounts disc = null;
		CustomersDAO custDAO = new CustomersDAO();
		Customers cust = null;
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				disc = discDAO.read(rs.getLong(11));
				cust = custDAO.read(rs.getLong(12));
				Orders ord = new Orders(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getBoolean(4), rs.getDouble(5), rs.getTimestamp(6), rs.getTimestamp(7), rs.getDouble(8), rs.getDouble(9), rs.getString(10), disc, cust, rs.getString(13), rs.getTimestamp(14), rs.getString(15), rs.getTimestamp(16));
				orders.add(ord);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}
	
	// To read delivery order with ordered result and only the order that will be ready soon (7 minutes) WITH ID
		public Orders readDeliveryOrderReady(long idOrder) {
			Orders ord = null;
			String query = "SELECT * FROM orders ord WHERE ord.idOrder = ? AND ord.typeOrd = 'Delivery' AND ord.readyDateOrd <= DATE_ADD(NOW(), INTERVAL 7 MINUTE) AND ord.idOrder NOT IN (SELECT trf.idOrder FROM takeresponsibilityfor trf WHERE trf.deliveryTakeON) ORDER BY ord.takingDateOrd;";
			PreparedStatement ps = super.getPs(query);
			DiscountsDAO discDAO = new DiscountsDAO();
			Discounts disc = null;
			CustomersDAO custDAO = new CustomersDAO();
			Customers cust = null;
			try {
				ps.setLong(1, idOrder);
				rs = ps.executeQuery();
				while (rs.next()) {
					disc = discDAO.read(rs.getLong(11));
					cust = custDAO.read(rs.getLong(12));
					ord = new Orders(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getBoolean(4), rs.getDouble(5), rs.getTimestamp(6), rs.getTimestamp(7), rs.getDouble(8), rs.getDouble(9), rs.getString(10), disc, cust, rs.getString(13), rs.getTimestamp(14), rs.getString(15), rs.getTimestamp(16));
				}
				rs.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return ord;
		}
	
	// To calculate order number of the current day
	public Long orderNumberStarting() {
		Long lastMaxId = 0L;
        LocalDate yesterday = LocalDate.now().minusDays(1);
        LocalDateTime lastMomentOfYesterday = yesterday.atTime(23, 59, 59);
		String query = "SELECT idOrder FROM orders WHERE takingDateOrd < ? ORDER BY idOrder DESC LIMIT 1;"; // ? = today
		PreparedStatement ps = super.getPs(query);
		try {
			ps.setTimestamp(1, Timestamp.valueOf(lastMomentOfYesterday));
			rs = ps.executeQuery();
			while (rs.next()) {
				lastMaxId = rs.getLong(1);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lastMaxId;
	}
	
}
