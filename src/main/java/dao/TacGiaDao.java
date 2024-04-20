package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entities.TacGia;

public class TacGiaDao {
	private Connection con;
	private PreparedStatement ps = null;
	private ResultSet rs;
	private String query;
	private int rsCheck;

	public TacGiaDao() {
		DBConnection connection = DBConnection.getInstance();
		con = connection.getConnection();
	}

	public ArrayList<TacGia> getListTacGia() throws Exception {
		ArrayList<TacGia> list = new ArrayList<>();
		query = "SELECT maTacGia, tenTacGia FROM TacGia";
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			TacGia t = new TacGia(rs.getString("maTacGia"), rs.getString("tenTacGia"));
			list.add(t);
		}
		return list;
	}

	public List<TacGia> getTacGia(String maTacGia) {
		List<TacGia> dsTG = new ArrayList<TacGia>();
		try {
			String query = "SELECT * FROM TacGia WHERE maTacGia = ?";
			ps = con.prepareStatement(query);
			ps.setString(1, maTacGia);
			rs = ps.executeQuery();
			while (rs.next()) {
				String matg = rs.getString("maTacGia");
				String tentg = rs.getString("tenTacGia");
				TacGia tg = new TacGia(matg, tentg);
				dsTG.add(tg);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error retrieving authors: " + e.getMessage());
		}
		return dsTG;
	}

	public boolean themTacGia(TacGia t) throws Exception {
		query = "INSERT INTO TacGia (maTacGia, tenTacGia) VALUES (?, ?)";
		ps = con.prepareStatement(query);
		ps.setString(1, t.getMaTacGia());
		ps.setString(2, t.getTenTacGia());
		rsCheck = ps.executeUpdate();
		return rsCheck != 0;
	}

	public boolean xoaTacGia(String maTacGia) {
		// Your implementation to delete author by ID
		return false;
	}

	public TacGia timTacGia(String TacGia) throws SQLException {
		query = "SELECT * FROM TacGia WHERE tenTacGia = ?";
		ps = con.prepareStatement(query);
		ps.setString(1, TacGia);
		rs = ps.executeQuery();
		while (rs.next()) {
			return new TacGia(rs.getString("maTacGia"), rs.getString("tenTacGia"));
		}
		return null;
	}

	public boolean kiemTraTonTaiTacGia(String ten) throws SQLException {
		query = "SELECT * FROM TacGia WHERE tenTacGia = ?";
		ps = con.prepareStatement(query);
		ps.setString(1, ten);
		rs = ps.executeQuery();
		return rs.next();
	}
}
