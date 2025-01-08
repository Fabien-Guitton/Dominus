package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import tables.Ingredients;
import tables.LineBasket;
import tables.Supplements;

public class SupplementsDAO extends DAO<Supplements> {
	
	private ResultSet rs;
	
	@Override
	public Supplements create(Supplements sup) {
		String query = "INSERT INTO supplements (idIngredient, idLineBasket, qtySup, addSupON, userCreate, dateCreate, userModif, dateModif) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement ps = super.getPs(query, PreparedStatement.RETURN_GENERATED_KEYS);
		try {
			ps.setLong(1, sup.getidIngredient().getidIngredient()); // idIngredient
			ps.setLong(2, sup.getIdLineBasket().getIdLineBasket()); // idLineBasket
			ps.setInt(3, sup.getQtySup()); // qtySup
			ps.setBoolean(4, sup.isAddSupON()); // addSupON
			ps.setString(5, super.connect.getMetaData().getUserName()); // userCreate
			ps.setTimestamp(6, new Timestamp(System.currentTimeMillis())); // dateCreate
			ps.setString(7, super.connect.getMetaData().getUserName()); // userModif
			ps.setTimestamp(8, new Timestamp(System.currentTimeMillis())); // dateModif
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()){
				long id = rs.getLong(1);
				sup.setidSupplement(id);
			}
			rs.close();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sup;
	}
	
	@Override
	public Supplements update(Supplements sup) {
		String query = "UPDATE supplements SET "
				+ "idIngredient = ?, idLineBasket = ?, qtySup = ?, addSupON = ?, userCreate = ?, dateCreate = ?, userModif = ?, dateModif = ? "
				+ "WHERE idSupplement = ?;";
		PreparedStatement ps = super.getPs(query);
		try {
			ps.setLong(1, sup.getidIngredient().getidIngredient()); // idIngredient
			ps.setLong(2, sup.getIdLineBasket().getIdLineBasket()); // idLineBasket
			ps.setInt(3, sup.getQtySup()); // qtySup
			ps.setBoolean(4, sup.isAddSupON()); // addSupON
			ps.setString(5, sup.getUserCreate()); // userCreate
			ps.setTimestamp(6, sup.getDateCreate()); // dateCreate
			ps.setString(7, super.connect.getMetaData().getUserName()); // userModif
			ps.setTimestamp(8, new Timestamp(System.currentTimeMillis())); // dateModif
			ps.setLong(9, sup.getidSupplement()); // id
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sup;
	}
	
	@Override
	public void delete(Supplements sup) {
		String query = "DELETE FROM supplements WHERE idSupplement = ?;";
		PreparedStatement ps = super.getPs(query);
		try {
			ps.setLong(1, sup.getidSupplement());
			ps.executeQuery();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Supplements> readAll() {
		ArrayList<Supplements> supplements = new ArrayList<Supplements>();
		String query = "SELECT * FROM supplements;";
		Statement stmt = super.getStmt();
		IngredientsDAO ingDAO = new IngredientsDAO();
		Ingredients ing = null;
		LineBasketDAO lbDAO = new LineBasketDAO();
		LineBasket lb = null;
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				ing = ingDAO.read(rs.getLong(2));
				lb = lbDAO.read(rs.getLong(3));
				Supplements sup = new Supplements(rs.getLong(1), ing, lb, rs.getInt(4), rs.getBoolean(5), rs.getString(6), rs.getTimestamp(7), rs.getString(8), rs.getTimestamp(9));
				supplements.add(sup);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return supplements;
	}
	
	public Supplements read(long idSupplement) {
		Supplements sup = null;
		String query = "SELECT * FROM supplements WHERE idSupplement = ?;";
		PreparedStatement ps = super.getPs(query);
		IngredientsDAO ingDAO = new IngredientsDAO();
		Ingredients ing = null;
		LineBasketDAO lbDAO = new LineBasketDAO();
		LineBasket lb = null;
		try {
			ps.setLong(1, idSupplement);
			rs = ps.executeQuery();
			while (rs.next()) {
				ing = ingDAO.read(rs.getLong(2));
				lb = lbDAO.read(rs.getLong(3));
				sup = new Supplements(rs.getLong(1), ing, lb, rs.getInt(4), rs.getBoolean(5), rs.getString(6), rs.getTimestamp(7), rs.getString(8), rs.getTimestamp(9));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sup;
	}
}
