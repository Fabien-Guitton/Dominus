package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import tables.Ingredients;

public class IngredientsDAO extends DAO<Ingredients> {
	
	private ResultSet rs;
	
	@Override
	public Ingredients create(Ingredients ing) {
		String query = "INSERT INTO ingredients (nameIng, stockIng, unityIng, supplementPossibleON, priceETIng, priceITIng, userCreate, dateCreate, userModif, dateModif) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement ps = super.getPs(query, PreparedStatement.RETURN_GENERATED_KEYS);
		try {
			ps.setString(1, ing.getNameIng()); // nameIng
			ps.setDouble(2, ing.getStockIng()); // stockIng
			ps.setString(3, ing.getUnityIng()); // unityIng
			ps.setBoolean(4, ing.isSupplementPossibleON()); // supplementPossibleON
			ps.setDouble(5, ing.getPriceETIng()); // priceETIng
			ps.setDouble(6, ing.getPriceITIng()); // priceITIng
			ps.setString(7, super.connect.getMetaData().getUserName()); // userCreate
			ps.setTimestamp(8, new Timestamp(System.currentTimeMillis())); // dateCreate
			ps.setString(9, super.connect.getMetaData().getUserName()); // userModif
			ps.setTimestamp(10, new Timestamp(System.currentTimeMillis())); // dateModif
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()){
				long id = rs.getLong(1);
				ing.setidIngredient(id);
			}
			rs.close();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ing;
	}
	
	@Override
	public Ingredients update(Ingredients ing) {
		String query = "UPDATE ingredients SET "
				+ "nameIng = ?, stockIng = ?, unityIng = ?, supplementPossibleON = ?, priceETIng = ?, priceITIng = ?, userCreate = ?, dateCreate = ?, userModif = ?, dateModif = ? "
				+ "WHERE idIngredient = ?;";
		PreparedStatement ps = super.getPs(query);
		try {
			ps.setString(1, ing.getNameIng()); // nameIng
			ps.setDouble(2, ing.getStockIng()); // stockIng
			ps.setString(3, ing.getUnityIng()); // unityIng
			ps.setBoolean(4, ing.isSupplementPossibleON()); // supplementPossibleON
			ps.setDouble(5, ing.getPriceETIng()); // priceETIng
			ps.setDouble(6, ing.getPriceITIng()); // priceITIng
			ps.setString(7, super.connect.getMetaData().getUserName()); // userCreate
			ps.setTimestamp(8, new Timestamp(System.currentTimeMillis())); // dateCreate
			ps.setString(9, super.connect.getMetaData().getUserName()); // userModif
			ps.setTimestamp(10, new Timestamp(System.currentTimeMillis())); // dateModif
			ps.setLong(11, ing.getidIngredient()); // id
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ing;
	}
	
	@Override
	public void delete(Ingredients ing) {
		String query = "DELETE FROM ingredients WHERE idIngredient = ?;";
		PreparedStatement ps = super.getPs(query);
		try {
			ps.setLong(1, ing.getidIngredient());
			ps.executeQuery();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Ingredients> readAll() {
		ArrayList<Ingredients> ingredients = new ArrayList<Ingredients>();
		String query = "SELECT * FROM ingredients;";
		Statement stmt = super.getStmt();
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				Ingredients ing = new Ingredients(rs.getLong(1), rs.getString(2), rs.getLong(3), rs.getString(4), rs.getBoolean(5), rs.getDouble(6), rs.getDouble(7), rs.getString(8), rs.getTimestamp(9), rs.getString(10), rs.getTimestamp(11));
				ingredients.add(ing);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ingredients;
	}
	
	public Ingredients read(long idIngredient) {
		Ingredients ing = null;
		String query = "SELECT * FROM ingredients WHERE idIngredient = ?;";
		PreparedStatement ps = super.getPs(query);
		try {
			ps.setLong(1, idIngredient);
			rs = ps.executeQuery();
			while (rs.next()) {
				ing = new Ingredients(rs.getLong(1), rs.getString(2), rs.getLong(3), rs.getString(4), rs.getBoolean(5), rs.getDouble(6), rs.getDouble(7), rs.getString(8), rs.getTimestamp(9), rs.getString(10), rs.getTimestamp(11));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ing;
	}
	
	public ArrayList<Ingredients> readAllPizzaIng(Long idProduct) {
		ArrayList<Ingredients> ingredients = new ArrayList<Ingredients>();
		String query = "SELECT ing.* FROM ingredients ing INNER JOIN defaultingredients dfIng USING(idIngredient) WHERE supplementPossibleON AND dfIng.idProduct = ? ORDER BY ing.nameIng;";
		PreparedStatement ps = super.getPs(query);
		try {
			ps.setLong(1, idProduct);
			rs = ps.executeQuery();
			while (rs.next()) {
				Ingredients ing = new Ingredients(rs.getLong(1), rs.getString(2), rs.getLong(3), rs.getString(4), rs.getBoolean(5), rs.getDouble(6), rs.getDouble(7), rs.getString(8), rs.getTimestamp(9), rs.getString(10), rs.getTimestamp(11));
				ingredients.add(ing);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ingredients;
	}
	
	public ArrayList<Ingredients> readAllPizzaIng() {
		ArrayList<Ingredients> ingredients = new ArrayList<Ingredients>();
		String query = "SELECT DISTINCT ing.* FROM ingredients ing INNER JOIN defaultingredients dfIng USING(idIngredient) INNER JOIN products prod USING (idProduct) WHERE supplementPossibleON AND prod.categoryProduct = 'pizzas' ORDER BY ing.nameIng;";
		Statement stmt = super.getStmt();
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				Ingredients ing = new Ingredients(rs.getLong(1), rs.getString(2), rs.getLong(3), rs.getString(4), rs.getBoolean(5), rs.getDouble(6), rs.getDouble(7), rs.getString(8), rs.getTimestamp(9), rs.getString(10), rs.getTimestamp(11));
				ingredients.add(ing);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ingredients;
	}
}
