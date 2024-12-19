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
		String query = "INSERT INTO ingredients (nameIng, stockIng, unityIng, priceETIng, priceITIng, userCreate, dateCreate, userModif, dateModif) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement ps = super.getPs(query, PreparedStatement.RETURN_GENERATED_KEYS);
		try {
			ps.setString(1, ing.getNameIng()); // nameIng
			ps.setDouble(2, ing.getStockIng()); // stockIng
			ps.setString(3, ing.getUnityIng()); // unityIng
			ps.setDouble(4, ing.getPriceETIng()); // priceETIng
			ps.setDouble(5, ing.getPriceITIng()); // priceITIng
			ps.setString(6, super.connect.getMetaData().getUserName()); // userCreate
			ps.setTimestamp(7, new Timestamp(System.currentTimeMillis())); // dateCreate
			ps.setString(8, super.connect.getMetaData().getUserName()); // userModif
			ps.setTimestamp(9, new Timestamp(System.currentTimeMillis())); // dateModif
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
				+ "nameIng = ?, stockIng = ?, unityIng = ?, priceETIng = ?, priceITIng = ?, userCreate = ?, dateCreate = ?, userModif = ?, dateModif = ? "
				+ "WHERE idIgredient = ?;";
		PreparedStatement ps = super.getPs(query);
		try {
			ps.setString(1, ing.getNameIng()); // nameIng
			ps.setDouble(2, ing.getStockIng()); // stockIng
			ps.setString(3, ing.getUnityIng()); // unityIng
			ps.setDouble(4, ing.getPriceETIng()); // priceETIng
			ps.setDouble(5, ing.getPriceITIng()); // priceITIng
			ps.setString(6, ing.getUserCreate()); // userCreate
			ps.setTimestamp(7, ing.getDateCreate()); // dateCreate
			ps.setString(8, super.connect.getMetaData().getUserName()); // userModif
			ps.setTimestamp(9, new Timestamp(System.currentTimeMillis())); // dateModif
			ps.setLong(10, ing.getidIngredient()); // id
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ing;
	}
	
	@Override
	public void delete(Ingredients ing) {
		String query = "DELETE FROM ingredients WHERE idIgredient = ?;";
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
				Ingredients ing = new Ingredients(rs.getLong(1), rs.getString(2), rs.getLong(3), rs.getString(4), rs.getDouble(5), rs.getDouble(6), rs.getString(7), rs.getTimestamp(8), rs.getString(9), rs.getTimestamp(10));
				ingredients.add(ing);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ingredients;
	}
	
	public Ingredients read(long idIgredient) {
		Ingredients ing = null;
		String query = "SELECT * FROM ingredients WHERE idIgredient = ?;";
		PreparedStatement ps = super.getPs(query);
		try {
			ps.setLong(1, idIgredient);
			rs = ps.executeQuery();
			while (rs.next()) {
				ing = new Ingredients(rs.getLong(1), rs.getString(2), rs.getLong(3), rs.getString(4), rs.getDouble(5), rs.getDouble(6), rs.getString(7), rs.getTimestamp(8), rs.getString(9), rs.getTimestamp(10));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ing;
	}
}
