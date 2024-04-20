package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBConnection;
import entities.MauSac;

public class MauSacDao {
	private Connection con;
	private PreparedStatement ps = null;
	private ResultSet rs;
	private int rsCheck;

	public MauSacDao() {
		DBConnection connection = DBConnection.getInstance();
		con = connection.getConnection();
	}

	public ArrayList<MauSac> getListMauSac() {
		ArrayList<MauSac> list = new ArrayList<>();
		String query = "SELECT maMauSac, tenMau FROM MauSac";
		try (PreparedStatement ps = con.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				MauSac mauSac = new MauSac(rs.getString("maMauSac"), rs.getString("tenMau"));
				list.add(mauSac);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean themMauSac(MauSac mauSac) {
		try {
			if (kiemTraTonTaiMauSac(mauSac.getTenMau())) {
				System.out.println("Màu sắc đã tồn tại.");
				return false;
			}
			String query = "INSERT INTO MauSac (maMauSac, tenMau) VALUES (?, ?)";
			try (PreparedStatement ps = con.prepareStatement(query)) {
				ps.setString(1, mauSac.getMaMau());
				ps.setString(2, mauSac.getTenMau());
				rsCheck = ps.executeUpdate();
				return rsCheck != 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public MauSac timMauSac(String mau) {
		String query = "SELECT * FROM MauSac WHERE tenMau = ?";
		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, mau);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					return new MauSac(rs.getString("maMauSac"), rs.getString("tenMau"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean kiemTraTonTaiMauSac(String ten) {
		String query = "SELECT * FROM MauSac WHERE tenMau = ?";
		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, ten);
			try (ResultSet rs = ps.executeQuery()) {
				return rs.next();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
