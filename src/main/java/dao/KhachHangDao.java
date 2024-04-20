package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entities.KhachHang;

public class KhachHangDao {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private ArrayList<KhachHang> khachhang = new ArrayList<KhachHang>();

	public KhachHangDao() {
		DBConnection connection = DBConnection.getInstance();
		con = connection.getConnection();
	}

	public int themKhachHang(KhachHang kh) {
		String insert = "INSERT INTO KhachHang VALUES (?, ?, ?, ?, ?)";
		try (PreparedStatement stmt = con.prepareStatement(insert)) {
			stmt.setString(1, kh.getMaKhachHang());
			stmt.setString(2, kh.getHoTenKhachHang());
			stmt.setBoolean(3, kh.isGioiTinh());
			stmt.setString(4, kh.getsDT());
			stmt.setString(5, kh.getDiaChi());
			return stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public List<KhachHang> getDSKhachHang() {
		List<KhachHang> dskh = new ArrayList<>();
		String query = "SELECT * FROM KhachHang";
		try (PreparedStatement ps = con.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				KhachHang kh = new KhachHang(rs.getString("maKhachHang"), rs.getString("hotenKhachHang"),
						rs.getString("sdt"), rs.getBoolean("gioiTinh"), rs.getString("diaChi"));
				dskh.add(kh);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dskh;
	}

	public KhachHang timKhachHangTheoMa(String maKH) {
		String query = "SELECT * FROM KhachHang WHERE maKhachHang=?";
		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, maKH);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					return new KhachHang(rs.getString("maKhachHang"), rs.getString("hotenKhachHang"),
							rs.getString("sdt"), rs.getBoolean("gioiTinh"), rs.getString("diaChi"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<KhachHang> timKhachHangTheoTen(String tenKH) {
		ArrayList<KhachHang> khachhang = new ArrayList<>();
		String query = "SELECT * FROM KhachHang WHERE hotenKhachHang LIKE CONCAT('%', ?, '%')";
		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, tenKH);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					KhachHang kh = new KhachHang(rs.getString("maKhachHang"), rs.getString("hotenKhachHang"),
							rs.getString("sdt"), rs.getBoolean("gioiTinh"), rs.getString("diaChi"));
					khachhang.add(kh);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return khachhang;
	}

	public int capNhatKhachHang(KhachHang kh) {
		String sql = "UPDATE KhachHang SET hotenKhachHang = ?, gioiTinh = ?, sdt = ?, diaChi = ? WHERE maKhachHang = ?";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, kh.getHoTenKhachHang());
			ps.setBoolean(2, kh.isGioiTinh());
			ps.setString(3, kh.getsDT());
			ps.setString(4, kh.getDiaChi());
			ps.setString(5, kh.getMaKhachHang());
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public ArrayList<KhachHang> timKhachHangTheoSDT(String sDT) {
		ArrayList<KhachHang> khachhang = new ArrayList<>();
		String query = "SELECT * FROM KhachHang WHERE sdt LIKE CONCAT('%', ?, '%')";
		try (PreparedStatement stmt = con.prepareStatement(query)) {
			stmt.setString(1, sDT);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					KhachHang kh = new KhachHang(rs.getString("maKhachHang"), rs.getString("hotenKhachHang"),
							rs.getString("sdt"), rs.getBoolean("gioiTinh"), rs.getString("diaChi"));
					khachhang.add(kh);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return khachhang;
	}

	public ArrayList<KhachHang> getListKhachHangByNameAndSDT(String tenKH, String sdt) {
		ArrayList<KhachHang> khachhang = new ArrayList<>();
		try {
			String sql = "SELECT * FROM KhachHang WHERE hotenKhachHang LIKE CONCAT('%', ?, '%') OR sdt LIKE CONCAT('%', ?, '%')";
			try (PreparedStatement stmt = con.prepareStatement(sql)) {
				stmt.setString(1, tenKH);
				stmt.setString(2, sdt);
				try (ResultSet rs = stmt.executeQuery()) {
					while (rs.next()) {
						KhachHang kh = new KhachHang(rs.getString("maKhachHang"), rs.getString("hotenKhachHang"),
								rs.getString("sdt"), rs.getBoolean("gioiTinh"), rs.getString("diaChi"));
						khachhang.add(kh);
					}
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return khachhang;
	}

	public KhachHang timKhachHangBangSDT(String sdt) {
		String query = "SELECT * FROM KhachHang WHERE sdt=?";
		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, sdt);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					return new KhachHang(rs.getString("maKhachHang"), rs.getString("hotenKhachHang"),
							rs.getString("sdt"), rs.getBoolean("gioiTinh"), rs.getString("diaChi"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
