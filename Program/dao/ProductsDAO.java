package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import tables.Products;

public class ProductsDAO extends DAO<Products> {
	
	private ResultSet rs;
	
	@Override
	public Products create(Products prod) {
		String query = "INSERT INTO products (nameProduct, sizeProduct, categoryProduct, priceETProduct, priceITProduct, userCreate, dateCreate, userModif, dateModif) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement ps = super.getPs(query, PreparedStatement.RETURN_GENERATED_KEYS);
		try {
			ps.setString(1, prod.getNameProduct()); // nameProduct
			ps.setString(2, prod.getSizeProduct()); // sizeProduct
			ps.setString(3, prod.getCategoryProduct()); // categoryProduct
			ps.setDouble(4, prod.getPriceETProduct()); // priceETProduct
			ps.setDouble(5, prod.getPriceITProduct()); // priceITProduct
			ps.setString(6, super.connect.getMetaData().getUserName()); // userCreate
			ps.setTimestamp(7, new Timestamp(System.currentTimeMillis())); // dateCreate
			ps.setString(8, super.connect.getMetaData().getUserName()); // userModif
			ps.setTimestamp(9, new Timestamp(System.currentTimeMillis())); // dateModif
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()){
				long id = rs.getLong(1);
				prod.setIdProduct(id);
			}
			rs.close();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prod;
	}
	
	@Override
	public Products update(Products prod) {
		String query = "UPDATE products SET "
				+ "nameProduct = ?, sizeProduct = ?, categoryProduct = ?, priceETProduct = ?, priceITProduct = ?, userCreate = ?, dateCreate = ?, userModif = ?, dateModif = ? "
				+ "WHERE idProduct = ?;";
		PreparedStatement ps = super.getPs(query);
		try {
			ps.setString(1, prod.getNameProduct()); // nameProduct
			ps.setString(2, prod.getSizeProduct()); // sizeProduct
			ps.setString(3, prod.getCategoryProduct()); // categoryProduct
			ps.setDouble(4, prod.getPriceETProduct()); // priceETProduct
			ps.setDouble(5, prod.getPriceITProduct()); // priceITProduct
			ps.setString(6, prod.getUserCreate()); // userCreate
			ps.setTimestamp(7, prod.getDateCreate()); // dateCreate
			ps.setString(8, super.connect.getMetaData().getUserName()); // userModif
			ps.setTimestamp(9, new Timestamp(System.currentTimeMillis())); // dateModif
			ps.setLong(10, prod.getIdProduct()); // id
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prod;
	}
	
	@Override
	public void delete(Products prod) {
		String query = "DELETE FROM products WHERE idProduct = ?;";
		PreparedStatement ps = super.getPs(query);
		try {
			ps.setLong(1, prod.getIdProduct());
			ps.executeQuery();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Products> readAll() {
		ArrayList<Products> products = new ArrayList<Products>();
		String query = "SELECT * FROM products;";
		Statement stmt = super.getStmt();
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				Products prod = new Products(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDouble(5), rs.getDouble(6), rs.getString(7), rs.getTimestamp(8), rs.getString(9), rs.getTimestamp(10));
				products.add(prod);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}
	
	public Products read(long idProduct) {
		Products prod = null;
		String query = "SELECT * FROM products WHERE idProduct = ?;";
		PreparedStatement ps = super.getPs(query);
		try {
			ps.setLong(1, idProduct);
			rs = ps.executeQuery();
			while (rs.next()) {
				prod = new Products(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDouble(5), rs.getDouble(6), rs.getString(7), rs.getTimestamp(8), rs.getString(9), rs.getTimestamp(10));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prod;
	}
}
