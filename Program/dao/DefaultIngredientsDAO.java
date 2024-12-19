package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import tables.DefaultIngredients;
import tables.Ingredients;
import tables.Products;

public class DefaultIngredientsDAO extends DAO<DefaultIngredients> {
	
	private ResultSet rs;
	
	@Override
	public DefaultIngredients create(DefaultIngredients defaultIng) {
		String query = "INSERT INTO defaultingredients (idIngredient, idProduct, userCreate, dateCreate, userModif, dateModif) "
				+ "VALUES(?, ?, ?, ?, ?, ?);";
		PreparedStatement ps = super.getPs(query, PreparedStatement.RETURN_GENERATED_KEYS);
		try {
			ps.setDouble(1, defaultIng.getidIngredient().getidIngredient()); // idIngredient
			ps.setDouble(2, defaultIng.getIdProduct().getIdProduct()); // idProduct
			ps.setString(3, super.connect.getMetaData().getUserName()); // userCreate
			ps.setTimestamp(4, new Timestamp(System.currentTimeMillis())); // dateCreate
			ps.setString(5, super.connect.getMetaData().getUserName()); // userModif
			ps.setTimestamp(6, new Timestamp(System.currentTimeMillis())); // dateModif
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()){
				long id = rs.getLong(1);
				defaultIng.setidDefaultIngredient(id);
			}
			rs.close();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return defaultIng;
	}
	
	@Override
	public DefaultIngredients update(DefaultIngredients defaultIng) {
		String query = "UPDATE defaultingredients SET "
				+ "idIngredient = ?, idProduct = ?, userCreate = ?, dateCreate = ?, userModif = ?, dateModif = ? "
				+ "WHERE idLineBasket = ?;";
		PreparedStatement ps = super.getPs(query);
		try {
			ps.setDouble(1, defaultIng.getidIngredient().getidIngredient()); // idIngredient
			ps.setDouble(2, defaultIng.getIdProduct().getIdProduct()); // idProduct
			ps.setString(3, defaultIng.getUserCreate()); // userCreate
			ps.setTimestamp(4, defaultIng.getDateCreate()); // dateCreate
			ps.setString(5, super.connect.getMetaData().getUserName()); // userModif
			ps.setTimestamp(6, new Timestamp(System.currentTimeMillis())); // dateModif
			ps.setLong(7, defaultIng.getidDefaultIngredient()); // id
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return defaultIng;
	}
	
	@Override
	public void delete(DefaultIngredients defaultIng) {
		String query = "DELETE FROM defaultingredients WHERE idDefaultIng = ?;";
		PreparedStatement ps = super.getPs(query);
		try {
			ps.setLong(1, defaultIng.getidDefaultIngredient());
			ps.executeQuery();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<DefaultIngredients> readAll() {
		ArrayList<DefaultIngredients> defaultingredients = new ArrayList<DefaultIngredients>();
		String query = "SELECT * FROM defaultingredients;";
		Statement stmt = super.getStmt();
		IngredientsDAO ingDAO = new IngredientsDAO();
		Ingredients ing = null;
		ProductsDAO prodDAO = new ProductsDAO();
		Products prod = null;
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				ing = ingDAO.read(rs.getLong(2));
				prod = prodDAO.read(rs.getLong(3));
				DefaultIngredients defaulting = new DefaultIngredients(rs.getLong(1), ing, prod, rs.getString(4), rs.getTimestamp(5), rs.getString(6), rs.getTimestamp(7));
				defaultingredients.add(defaulting);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return defaultingredients;
	}
	
	public DefaultIngredients read(long idDefaultIng) {
		DefaultIngredients defaulting = null;
		String query = "SELECT * FROM defaultingredients WHERE idDefaultIng = ?;";
		PreparedStatement ps = super.getPs(query);
		IngredientsDAO ingDAO = new IngredientsDAO();
		Ingredients ing = null;
		ProductsDAO prodDAO = new ProductsDAO();
		Products prod = null;
		try {
			ps.setLong(1, idDefaultIng);
			rs = ps.executeQuery();
			while (rs.next()) {
				ing = ingDAO.read(rs.getLong(2));
				prod = prodDAO.read(rs.getLong(3));
				defaulting = new DefaultIngredients(rs.getLong(1), ing, prod, rs.getString(4), rs.getTimestamp(5), rs.getString(6), rs.getTimestamp(7));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return defaulting;
	}
}
