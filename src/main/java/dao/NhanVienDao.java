package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entities.NhanVien;

public class NhanVienDao {
	private Connection con;
	private PreparedStatement ps = null;
	private ResultSet rs;
	private String query;
	private int rsCheck;

	public NhanVienDao() {
		DBConnection connection = DBConnection.getInstance();
		con = connection.getConnection();
	}

	public int themNhanvien(NhanVien nv) throws SQLException {
		String insert = "INSERT INTO NhanVien VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, null, null)";
		try (PreparedStatement stmt = con.prepareStatement(insert)) {
			stmt.setString(1, nv.getMaNhanVien());
			stmt.setString(2, nv.getHoTenNhanVien());
			stmt.setDate(3, java.sql.Date.valueOf(nv.getNgaySinh()));
			stmt.setString(4, nv.getcCCD());
			stmt.setString(5, nv.getDiaChi());
			stmt.setString(6, nv.getsDT());
			stmt.setBoolean(7, nv.isGioiTinh());
			stmt.setString(8, nv.getEmail());
			stmt.setBoolean(9, nv.isChucVu());
			stmt.setBoolean(10, nv.isCaLamViec());
			stmt.setString(11, nv.getHinhAnh());
			stmt.executeUpdate();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e; // Ném lại exception để báo lỗi lên lớp gọi
		}
	}


	public List<NhanVien> getDSNhanVien() throws SQLException {
		List<NhanVien> dsnv = new ArrayList<>();
		String query = "SELECT * FROM NhanVien";
		try (PreparedStatement ps = con.prepareStatement(query);
			 ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				NhanVien nv = new NhanVien(rs.getString("maNhanVien"), rs.getString("hoTenNhanVien"),
						rs.getDate("ngaySinh").toLocalDate(), rs.getString("cCCD"), rs.getString("diaChi"),
						rs.getString("sdt"), rs.getBoolean("gioiTinh"), rs.getString("email"), rs.getBoolean("chucVu"),
						rs.getBoolean("caLamViec"), rs.getString("hinhAnh"), null, null);
				dsnv.add(nv);
			}
		}
		return dsnv;
	}

	public List<NhanVien> timDanhSachNhanVienTheoMa(String maNV) throws SQLException {
		List<NhanVien> dsnv = new ArrayList<>();
		String query = "SELECT * FROM NhanVien WHERE maNhanVien LIKE CONCAT('%', ?, '%')";
		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, maNV);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					NhanVien nv = new NhanVien(rs.getString("maNhanVien"), rs.getString("hoTenNhanVien"),
							rs.getDate("ngaySinh").toLocalDate(), rs.getString("cCCD"), rs.getString("diaChi"),
							rs.getString("sdt"), rs.getBoolean("gioiTinh"), rs.getString("email"), rs.getBoolean("chucVu"),
							rs.getBoolean("caLamViec"), rs.getString("hinhAnh"), null, null);
					dsnv.add(nv);
				}
			}
		}
		return dsnv;
	}

	public NhanVien timNhanVienTheoMa(String maNV) throws SQLException {
		String query = "SELECT * FROM NhanVien WHERE maNhanVien=?";
		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, maNV);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return new NhanVien(rs.getString("maNhanVien"), rs.getString("hoTenNhanVien"),
							rs.getDate("ngaySinh").toLocalDate(), rs.getString("cCCD"), rs.getString("diaChi"),
							rs.getString("sdt"), rs.getBoolean("gioiTinh"), rs.getString("email"), rs.getBoolean("chucVu"),
							rs.getBoolean("caLamViec"), rs.getString("hinhAnh"), null, null);
				}
			}
		}
		// Trả về null nếu không tìm thấy
		return null;
	}


	public List<NhanVien> timDSNhanVienTheoTen(String tenNV) throws SQLException {
		List<NhanVien> dsnv = new ArrayList<>();
		String query = "SELECT * FROM NhanVien WHERE hoTenNhanVien LIKE CONCAT('%', ?, '%')";
		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, tenNV);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					NhanVien nv = new NhanVien(rs.getString("maNhanVien"), rs.getString("hoTenNhanVien"),
							rs.getDate("ngaySinh").toLocalDate(), rs.getString("cCCD"), rs.getString("diaChi"),
							rs.getString("sdt"), rs.getBoolean("gioiTinh"), rs.getString("email"), rs.getBoolean("chucVu"),
							rs.getBoolean("caLamViec"), rs.getString("hinhAnh"), null, null);
					dsnv.add(nv);
				}
			}
		}
		return dsnv;
	}

	public NhanVien timNhanVienTheoTen(String tenNV) throws SQLException {
		String query = "SELECT * FROM NhanVien WHERE hoTenNhanVien LIKE CONCAT('%', ?, '%')";
		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, tenNV);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return new NhanVien(rs.getString("maNhanVien"), rs.getString("hoTenNhanVien"),
							rs.getDate("ngaySinh").toLocalDate(), rs.getString("cCCD"), rs.getString("diaChi"),
							rs.getString("sdt"), rs.getBoolean("gioiTinh"), rs.getString("email"), rs.getBoolean("chucVu"),
							rs.getBoolean("caLamViec"), rs.getString("hinhAnh"), null, null);
				}
			}
		}
		// Trả về null nếu không tìm thấy
		return null;
	}

	public List<NhanVien> timNhanVienTheoSDT(String sDT) throws SQLException {
		List<NhanVien> dsnv = new ArrayList<>();
		String query = "SELECT * FROM NhanVien WHERE sdt LIKE CONCAT('%', ?, '%')";
		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, sDT);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					NhanVien nv = new NhanVien(rs.getString("maNhanVien"), rs.getString("hoTenNhanVien"),
							rs.getDate("ngaySinh").toLocalDate(), rs.getString("cCCD"), rs.getString("diaChi"),
							rs.getString("sdt"), rs.getBoolean("gioiTinh"), rs.getString("email"), rs.getBoolean("chucVu"),
							rs.getBoolean("caLamViec"), rs.getString("hinhAnh"), null, null);
					dsnv.add(nv);
				}
			}
		}
		return dsnv;
	}


	public List<NhanVien> getListNhanVienByNameAndSDT(String tenNV, String sdt) {
		List<NhanVien> dsnv = new ArrayList<>();
		String sql = "SELECT * FROM NhanVien WHERE hoTenNhanVien LIKE CONCAT('%', ?, '%') OR sdt LIKE CONCAT('%', ?, '%')";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, tenNV);
			ps.setString(2, sdt);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					NhanVien nv = new NhanVien(rs.getString("maNhanVien"), rs.getString("hoTenNhanVien"),
							rs.getDate("ngaySinh").toLocalDate(), rs.getString("cCCD"), rs.getString("diaChi"),
							rs.getString("sdt"), rs.getBoolean("gioiTinh"), rs.getString("email"), rs.getBoolean("chucVu"),
							rs.getBoolean("caLamViec"), rs.getString("hinhAnh"), null, null);
					dsnv.add(nv);
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return dsnv;
	}

	public int xoaNhanVien(String maNV) {
		String query = "DELETE FROM NhanVien WHERE maNhanVien = ?";
		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, maNV);
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int suaNhanVien(NhanVien nv) {
		String sql = "UPDATE NhanVien SET hoTenNhanVien = ?, ngaySinh = ? ,cCCD = ?, diaChi =?, sdt = ?, gioiTinh =?,  email = ?, chucVu= ?, caLamViec = ?, hinhAnh=?  WHERE maNhanVien =?";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, nv.getHoTenNhanVien());

			int day = nv.getNgaySinh().getDayOfMonth();
			int month = nv.getNgaySinh().getMonthValue();
			int year = nv.getNgaySinh().getYear();

			ps.setString(2, year + "-" + month + "-" + day);

			ps.setString(3, nv.getcCCD());
			ps.setString(4, nv.getDiaChi());
			ps.setString(5, nv.getsDT());
			ps.setBoolean(6, nv.isGioiTinh());
			ps.setString(7, nv.getEmail());
			ps.setBoolean(8, nv.isChucVu());
			ps.setBoolean(9, nv.isCaLamViec());
			ps.setString(10, nv.getHinhAnh());
			ps.setString(11, nv.getMaNhanVien());

			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public NhanVien getNhanVienByEmail(String email) {
		try (PreparedStatement ps = con.prepareStatement("SELECT * FROM NhanVien WHERE email = ?")) {
			ps.setString(1, email);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					return new NhanVien(
							rs.getString("maNhanVien"),
							rs.getString("hoTenNhanVien"),
							rs.getDate("ngaySinh").toLocalDate(),
							rs.getString("cCCD"),
							rs.getString("diaChi"),
							rs.getString("sdt"),
							rs.getBoolean("gioiTinh"),
							rs.getString("email"),
							rs.getBoolean("chucVu"),
							rs.getBoolean("caLamViec"),
							rs.getString("hinhAnh"),
							rs.getString("OTP"),
							rs.getTimestamp("hetHanOTP")
					);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int updateOTP(String email, String OTP, Timestamp hetHanOTP) {
		try (PreparedStatement ps = con.prepareStatement("UPDATE NhanVien SET OTP = ?, hetHanOTP = ? WHERE email = ?")) {
			ps.setString(1, OTP);
			ps.setTimestamp(2, hetHanOTP);
			ps.setString(3, email);
			return ps.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return -1;
	}

}
