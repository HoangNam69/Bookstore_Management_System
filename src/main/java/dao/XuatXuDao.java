package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;

import entities.XuatXu;

public class XuatXuDao {
	private Connection con;
	private PreparedStatement ps = null;
	private ResultSet rs;
	private String query;
	private int rsCheck;

	public XuatXuDao() {
		DBConnection connection = DBConnection.getInstance();
		con = connection.getConnection();
	}

	public ArrayList<XuatXu> getListXuatXu() throws Exception {
		ArrayList<XuatXu> list = new ArrayList<>();
		query = "SELECT maXuatXu, tenXuatXu FROM XuatXu";
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			XuatXu xuatXu = new XuatXu(rs.getString("maXuatXu"), rs.getString("tenXuatXu"));
			list.add(xuatXu);
		}
		return list;
	}

	public boolean themXuatXu(XuatXu x) throws Exception {
		query = "INSERT INTO XuatXu (maXuatXu, tenXuatXu) VALUES (?, ?)";
		ps = con.prepareStatement(query);
		ps.setString(1, x.getMaXuatXu());
		ps.setString(2, x.getTenXuatXu());
		rsCheck = ps.executeUpdate();
		if (rsCheck != 0)
			return true;
		return false;
	}

	public List<XuatXu> getXuatXu(String maXuatXu) {
		List<XuatXu> dsXX = new ArrayList<XuatXu>();
		try {
			String query = "SELECT * FROM XuatXu WHERE maXuatXu = ?";
			ps = con.prepareStatement(query);
			ps.setString(1, maXuatXu);
			rs = ps.executeQuery();
			while (rs.next()) {
				String maxx = rs.getString("maXuatXu");
				String tenxx = rs.getString("tenXuatXu");
				XuatXu xx = new XuatXu(maxx, tenxx);
				dsXX.add(xx);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsXX;
	}

	public boolean xoaXuatXu(String maXuatXu) {
		// Viết logic xóa ở đây
		return false;
	}

	public XuatXu timXuatXu(String XuatXu) throws SQLException {
		query = "SELECT * FROM XuatXu WHERE tenXuatXu = ?";
		ps = con.prepareStatement(query);
		ps.setString(1, XuatXu);
		rs = ps.executeQuery();
		while (rs.next()) {
			return new XuatXu(rs.getString("maXuatXu"), rs.getString("tenXuatXu"));
		}
		return null;
	}

	public boolean kiemTraTonTaiXuatXu(String ten) throws SQLException {
		query = "SELECT * FROM XuatXu WHERE tenXuatXu = ?";
		ps = con.prepareStatement(query);
		ps.setString(1, ten);
		rs = ps.executeQuery();
		while (rs.next()) {
			return true;
		}
		return false;
	}
}
