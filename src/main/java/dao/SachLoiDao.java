package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entities.Sach;
import entities.SachLoi;

public class SachLoiDao {
	private Connection con;

	public SachLoiDao() {
		DBConnection connection = DBConnection.getInstance();
		con = connection.getConnection();
	}

	public int themSachLoi(SachLoi sl) {
		String insert = "INSERT INTO SachLoi VALUES (?, ?, ?)";
		try (PreparedStatement stmt = con.prepareStatement(insert)) {
			stmt.setString(1, sl.getSach().getMaSanPham());
			stmt.setString(2, sl.getLoiSanPham());
			stmt.setInt(3, sl.getSoLuong());
			return stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int capNhatSoLuong(SachLoi sl) {
		String sql = "UPDATE SachLoi SET soLuongLoi = ? WHERE maSanPham = ? AND loiSanPham = ?";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, sl.getSoLuong());
			ps.setString(2, sl.getSach().getMaSanPham());
			ps.setString(3, sl.getLoiSanPham());
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<SachLoi> getAllSachLoi() {
		List<SachLoi> dssl = new ArrayList<>();
		String query = "SELECT * FROM SachLoi";
		try (PreparedStatement ps = con.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				SachLoi sl = new SachLoi(new Sach(rs.getString("maSanPham")), rs.getString("loiSanPham"),
						rs.getInt("soLuongLoi"));
				dssl.add(sl);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dssl;
	}

	public void xoaSachLoi(String maSach, String loi) {
		String query = "DELETE FROM SachLoi WHERE maSanPham = ? AND loiSanPham = ?";
		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, maSach);
			ps.setString(2, loi);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
