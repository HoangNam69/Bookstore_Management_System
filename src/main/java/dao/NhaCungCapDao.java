package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBConnection;
import entities.NhaCungCap;

public class NhaCungCapDao {

	private Connection con;
	private PreparedStatement ps = null;
	private ResultSet rs;
	private int rsCheck;

	public NhaCungCapDao() {
		DBConnection connection = DBConnection.getInstance();
		con = connection.getConnection();
	}

	public ArrayList<NhaCungCap> getListNhaCungCap(String loaiSanPham) throws SQLException {
		ArrayList<NhaCungCap> list = new ArrayList<>();
		String query = "SELECT distinct NhaCungCap.maNCC, NhaCungCap.tenNCC FROM NhaCungCap " +
				"INNER JOIN SanPham ON NhaCungCap.maNCC = SanPham.maNCC " +
				"WHERE SanPham.loaiSanPham LIKE ?";
		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, loaiSanPham);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					NhaCungCap nhaCungCap = new NhaCungCap(rs.getString("maNCC"), rs.getString("tenNCC"));
					list.add(nhaCungCap);
				}
			}
		}
		return list;
	}

	public boolean themNhaCungCap(NhaCungCap t) {
		try {
			if (kiemTraTonTaiNCC(t.getTenNCC())) {
				System.out.println("Nhà cung cấp đã tồn tại.");
				return false;
			}

			String query = "INSERT INTO NhaCungCap (maNCC, tenNCC, diaChi, email, sdt) VALUES (?, ?, ?, ?, ?)";
			try (PreparedStatement ps = con.prepareStatement(query)) {
				ps.setString(1, t.getMaNCC());
				ps.setString(2, t.getTenNCC());
				ps.setString(3, t.getDiaChi());
				ps.setString(4, t.getEmail());
				ps.setString(5, t.getsDT());
				rsCheck = ps.executeUpdate();
				return rsCheck != 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public ArrayList<NhaCungCap> getAllListNhaCungCap() {
		ArrayList<NhaCungCap> list = new ArrayList<>();
		String query = "SELECT maNCC, tenNCC, diaChi, email, sdt FROM NhaCungCap";
		try (PreparedStatement ps = con.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				NhaCungCap nhaCungCap = new NhaCungCap(rs.getString("maNCC"), rs.getString("tenNCC"),
						rs.getString("diaChi"), rs.getString("email"),
						rs.getString("sdt"));
				list.add(nhaCungCap);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public NhaCungCap timNhaCungCap(String NCC) {
		String query = "SELECT * FROM NhaCungCap WHERE tenNCC = ?";
		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, NCC);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					return new NhaCungCap(rs.getString("maNCC"), rs.getString("tenNCC"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean kiemTraTonTaiNCC(String ten) {
		String query = "SELECT * FROM NhaCungCap WHERE tenNCC = ?";
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
