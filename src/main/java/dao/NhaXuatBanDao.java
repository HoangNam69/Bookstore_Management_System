package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entities.NhaXuatBan;

public class NhaXuatBanDao {
	private Connection con;

	public NhaXuatBanDao() {
		DBConnection connection = DBConnection.getInstance();
		con = connection.getConnection();
	}

	public List<NhaXuatBan> getListNhaXuatBan() {
		List<NhaXuatBan> list = new ArrayList<>();
		String query = "SELECT maNXB, tenNXB FROM NhaXuatBan";
		try (PreparedStatement ps = con.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				NhaXuatBan nhaXuatBan = new NhaXuatBan(rs.getString("maNXB"), rs.getString("tenNXB"));
				list.add(nhaXuatBan);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean themNhaXuatBan(NhaXuatBan t) {
		String query = "INSERT INTO NhaXuatBan (maNXB, tenNXB) VALUES (?, ?)";
		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, t.getMaNXB());
			ps.setString(2, t.getTenNXB());
			int rsCheck = ps.executeUpdate();
			return rsCheck != 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public NhaXuatBan timNhaXuatBan(String NXB) {
		String query = "SELECT * FROM NhaXuatBan WHERE tenNXB = ?";
		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, NXB);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return new NhaXuatBan(rs.getString("maNXB"), rs.getString("tenNXB"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean kiemTraTonTaiNXB(String ten) {
		String query = "SELECT * FROM NhaXuatBan WHERE tenNXB = ?";
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
