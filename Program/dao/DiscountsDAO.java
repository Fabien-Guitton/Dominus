package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import tables.Discounts;

public class DiscountsDAO extends DAO<Discounts> {
	
	private ResultSet rs;
	
	@Override
	public Discounts create(Discounts dist) {
		String query = "INSERT INTO discounts (nameDist, valueDist, codeDist, accredDistON, userCreate, dateCreate, userModif, dateModif) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement ps = super.getPs(query, PreparedStatement.RETURN_GENERATED_KEYS);
		try {
			ps.setString(1, dist.getNameDist()); // nameDist
			ps.setDouble(2, dist.getValueDist()); // valueDist
			ps.setString(3, dist.getCodeDist()); // codeDist
			ps.setBoolean(4, dist.isAccredDistON()); // accredDistON
			ps.setString(5, super.connect.getMetaData().getUserName()); // userCreate
			ps.setTimestamp(6, new Timestamp(System.currentTimeMillis())); // dateCreate
			ps.setString(7, super.connect.getMetaData().getUserName()); // userModif
			ps.setTimestamp(8, new Timestamp(System.currentTimeMillis())); // dateModif
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()){
				long id = rs.getLong(1);
				dist.setIdDiscount(id);
			}
			rs.close();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dist;
	}
	
	@Override
	public Discounts update(Discounts dist) {
		String query = "UPDATE discounts SET "
				+ "nameDist = ?, valueDist = ?, codeDist = ?, accredDistON = ?, userCreate = ?, dateCreate = ?, userModif = ?, dateModif = ? "
				+ "WHERE idDiscount = ?;";
		PreparedStatement ps = super.getPs(query);
		try {
			ps.setString(1, dist.getNameDist()); // nameDist
			ps.setDouble(2, dist.getValueDist()); // valueDist
			ps.setString(3, dist.getCodeDist()); // codeDist
			ps.setBoolean(4, dist.isAccredDistON()); // accredDistON
			ps.setString(5, dist.getUserCreate()); // userCreate
			ps.setTimestamp(6, dist.getDateCreate()); // dateCreate
			ps.setString(7, super.connect.getMetaData().getUserName()); // userModif
			ps.setTimestamp(8, new Timestamp(System.currentTimeMillis())); // dateModif
			ps.setLong(9, dist.getIdDiscount()); // id
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dist;
	}
	
	@Override
	public void delete(Discounts dist) {
		String query = "DELETE FROM discounts WHERE idDiscount = ?;";
		PreparedStatement ps = super.getPs(query);
		try {
			ps.setLong(1, dist.getIdDiscount());
			ps.executeQuery();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Discounts> readAll() {
		ArrayList<Discounts> discounts = new ArrayList<Discounts>();
		String query = "SELECT * FROM discounts;";
		Statement stmt = super.getStmt();
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				Discounts emp = new Discounts(rs.getLong(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getBoolean(5), rs.getString(6), rs.getTimestamp(7), rs.getString(8), rs.getTimestamp(9));
				discounts.add(emp);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return discounts;
	}
	
	public Discounts read(long idDiscount) {
		Discounts dist = null;
		String query = "SELECT * FROM discounts WHERE idDiscount = ?;";
		PreparedStatement ps = super.getPs(query);
		try {
			ps.setLong(1, idDiscount);
			rs = ps.executeQuery();
			while (rs.next()) {
				dist = new Discounts(rs.getLong(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getBoolean(5), rs.getString(6), rs.getTimestamp(7), rs.getString(8), rs.getTimestamp(9));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dist;
	}
}
