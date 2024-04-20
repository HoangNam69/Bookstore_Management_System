package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entities.TheLoaiSach;
import entities.TheLoaiVanPhongPham;

public class TheLoaiDao {
	private Connection con;
	private PreparedStatement ps = null;
	private ResultSet rs;
	private String query;
	private int rsCheck;

	public TheLoaiDao() {
		DBConnection connection = DBConnection.getInstance();
		con = connection.getConnection();
	}

	public ArrayList<TheLoaiSach> getListTheLoaiSach() {
		ArrayList<TheLoaiSach> list = new ArrayList<>();
		try {
			query = "SELECT maTheLoai, tenTheLoai FROM TheLoaiSach";
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				TheLoaiSach s = new TheLoaiSach(rs.getString("maTheLoai"), rs.getString("tenTheLoai"));
				list.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}


	public ArrayList<TheLoaiVanPhongPham> getListTheLoaiVanPhongPham() {
		ArrayList<TheLoaiVanPhongPham> list = new ArrayList<>();
		try {
			query = "SELECT maLoaiVanPhongPham, tenTheLoai FROM LoaiVanPhongPham";
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				TheLoaiVanPhongPham s = new TheLoaiVanPhongPham(rs.getString("maLoaiVanPhongPham"),
						rs.getString("tenTheLoai"));
				list.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}


	public boolean themTheLoaiSach(TheLoaiSach t) {
		try {
			query = "INSERT INTO TheLoaiSach (maTheLoai, tenTheLoai) VALUES (?, N'" + t.getTenLoai() + "')";
			ps = con.prepareStatement(query);
			ps.setString(1, t.getMaLoai());
			rsCheck = ps.executeUpdate();
			if (rsCheck != 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}


	public boolean themTheLoaiVanPhongPham(TheLoaiVanPhongPham t) {
		try {
			query = "INSERT INTO LoaiVanPhongPham (maLoaiVanPhongPham, tenTheLoai) VALUES (?, N'" + t.getTenLoai() + "')";
			ps = con.prepareStatement(query);
			ps.setString(1, t.getMaLoai());
			rsCheck = ps.executeUpdate();
			if (rsCheck != 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}


	public TheLoaiSach timTheLoaiSach(String tenTheLoai) {
		try {
			query = "SELECT * FROM TheLoaiSach WHERE tenTheLoai = ?";
			ps = con.prepareStatement(query);
			ps.setString(1, tenTheLoai);
			rs = ps.executeQuery();
			while (rs.next()) {
				return new TheLoaiSach(rs.getString("maTheLoai"), rs.getString("tenTheLoai"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	public TheLoaiVanPhongPham timTheLoaiVanPhongPham(String tenTheLoai) {
		try {
			query = "SELECT * FROM LoaiVanPhongPham WHERE tenTheLoai = ?";
			ps = con.prepareStatement(query);
			ps.setString(1, tenTheLoai);
			rs = ps.executeQuery();
			while (rs.next()) {
				return new TheLoaiVanPhongPham(rs.getString("maLoaiVanPhongPham"), rs.getString("tenTheLoai"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	public List<TheLoaiSach> getSachTheoTheLoai(String maTL) {
		List<TheLoaiSach> dsTLS = new ArrayList<>();
		try {
			String query = "SELECT * FROM TheLoaiSach WHERE maTheLoai = ?";
			ps = con.prepareStatement(query);
			ps.setString(1, maTL);
			rs = ps.executeQuery();
			while (rs.next()) {
				String maTheLoai = rs.getString("maTheLoai");
				String tenTheLoai = rs.getString("tenTheLoai");
				TheLoaiSach tls = new TheLoaiSach(maTheLoai, tenTheLoai);
				dsTLS.add(tls);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsTLS;
	}


	public boolean kiemTraTonTaiTheLoaiVPP(String ten) throws SQLException {
		query = "SELECT * FROM LoaiVanPhongPham WHERE tenTheLoai = N'" + ten + "'";
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			return true;
		}
		return false;
	}

	public boolean kiemTraTonTaiTheLoaiSach(String ten) throws SQLException {
		query = "SELECT * FROM TheLoaiSach WHERE tenTheLoai = N'" + ten + "'";
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			return true;
		}
		return false;
	}


}
