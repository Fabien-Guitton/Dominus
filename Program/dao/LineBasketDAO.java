package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import tables.LineBasket;
import tables.Orders;
import tables.Products;

public class LineBasketDAO extends DAO<LineBasket> {
	
	private ResultSet rs;
	
	@Override
	public LineBasket create(LineBasket lb) {
		String query = "INSERT INTO linebasket (qtyProductLB, priceETLB, priceITLB, idProduct, idOrder, userCreate, dateCreate, userModif, dateModif) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement ps = super.getPs(query, PreparedStatement.RETURN_GENERATED_KEYS);
		try {
			ps.setDouble(1, lb.getQtyProductLB()); // qtyProductLB
			ps.setDouble(2, lb.getPriceETLB()); // priceETLB
			ps.setDouble(3, lb.getPriceITLB()); // priceITLB
			ps.setLong(4, lb.getIdProduct().getIdProduct()); // idProduct
			ps.setLong(5, lb.getIdOrder().getIdOrder()); // idOrder
			ps.setString(6, super.connect.getMetaData().getUserName()); // userCreate
			ps.setTimestamp(7, new Timestamp(System.currentTimeMillis())); // dateCreate
			ps.setString(8, super.connect.getMetaData().getUserName()); // userModif
			ps.setTimestamp(9, new Timestamp(System.currentTimeMillis())); // dateModif
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()){
				long id = rs.getLong(1);
				lb.setIdLineBasket(id);;
			}
			rs.close();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lb;
	}
	
	@Override
	public LineBasket update(LineBasket lb) {
		String query = "UPDATE linebasket SET "
				+ "qtyProductLB = ?, priceETLB = ?, priceITLB = ?, idProduct = ?, idOrder = ?, userCreate = ?, dateCreate = ?, userModif = ?, dateModif = ? "
				+ "WHERE idLineBasket = ?;";
		PreparedStatement ps = super.getPs(query);
		try {
			ps.setDouble(1, lb.getQtyProductLB()); // qtyProductLB
			ps.setDouble(2, lb.getPriceETLB()); // priceETLB
			ps.setDouble(3, lb.getPriceITLB()); // priceITLB
			ps.setLong(4, lb.getIdProduct().getIdProduct()); // idProduct
			ps.setLong(5, lb.getIdOrder().getIdOrder()); // idOrder
			ps.setString(6, lb.getUserCreate()); // userCreate
			ps.setTimestamp(7, lb.getDateCreate()); // dateCreate
			ps.setString(8, super.connect.getMetaData().getUserName()); // userModif
			ps.setTimestamp(9, new Timestamp(System.currentTimeMillis())); // dateModif
			ps.setLong(10, lb.getIdLineBasket()); // id
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lb;
	}
	
	@Override
	public void delete(LineBasket lb) {
		String query = "DELETE FROM linebasket WHERE idLineBasket = ?;";
		PreparedStatement ps = super.getPs(query);
		try {
			ps.setLong(1, lb.getIdLineBasket());
			ps.executeQuery();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<LineBasket> readAll() {
		ArrayList<LineBasket> linebasket = new ArrayList<LineBasket>();
		String query = "SELECT * FROM linebasket;";
		Statement stmt = super.getStmt();
		ProductsDAO prodDAO = new ProductsDAO();
		Products prod = null;
		OrdersDAO ordDAO = new OrdersDAO();
		Orders ord = null;
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				prod = prodDAO.read(rs.getLong(5));
				ord = ordDAO.read(rs.getLong(6));
				LineBasket lb = new LineBasket(rs.getLong(1), rs.getInt(2), rs.getDouble(3), rs.getDouble(4), prod, ord, rs.getString(7), rs.getTimestamp(8), rs.getString(9), rs.getTimestamp(10));
				linebasket.add(lb);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return linebasket;
	}
	
	public LineBasket read(long idLineBasket) {
		LineBasket lb = null;
		String query = "SELECT * FROM linebasket WHERE idLineBasket = ?;";
		PreparedStatement ps = super.getPs(query);
		ProductsDAO prodDAO = new ProductsDAO();
		Products prod = null;
		OrdersDAO ordDAO = new OrdersDAO();
		Orders ord = null;
		try {
			ps.setLong(1, idLineBasket);
			rs = ps.executeQuery();
			while (rs.next()) {
				prod = prodDAO.read(rs.getLong(5));
				ord = ordDAO.read(rs.getLong(6));
				lb = new LineBasket(rs.getLong(1), rs.getInt(2), rs.getDouble(3), rs.getDouble(4), prod, ord, rs.getString(7), rs.getTimestamp(8), rs.getString(9), rs.getTimestamp(10));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lb;
	}
}
