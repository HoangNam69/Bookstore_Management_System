package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBConnection;
import entities.TaiKhoan;

public class TaiKhoanDao {

	private Connection con;
	private PreparedStatement ps = null;
	private ResultSet rs;
	private String query;
	private int rsCheck;

	public TaiKhoanDao() {
		DBConnection connection = DBConnection.getInstance();
		con = connection.getConnection();
	}

	public ArrayList<TaiKhoan> getList() {
		try {
			query = "SELECT * FROM TaiKhoan";
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			NhanVienDao nhanVienDao = new NhanVienDao();
			ArrayList<TaiKhoan> listAcc = new ArrayList<>();
			while (rs.next()) {
				TaiKhoan a = new TaiKhoan(rs.getString(1), rs.getString(2),
						nhanVienDao.timNhanVienTheoMa(rs.getString(3)), rs.getBoolean(4));
				listAcc.add(a);
			}
			return listAcc;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error retrieving account list: " + e.getMessage());
		}
		return null;
	}

	public int insertAccount(TaiKhoan taiKhoan) {
		try {
			query = "INSERT INTO TaiKhoan VALUES(?,?,?,?)";
			ps = con.prepareStatement(query);
			ps.setString(1, taiKhoan.getTenDangNhap());
			ps.setString(2, taiKhoan.getMatKhau());
			ps.setString(3, taiKhoan.getNhanVien().getMaNhanVien());
			ps.setBoolean(4, taiKhoan.isQuyen());
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error inserting account: " + e.getMessage());
		}
		return -1;
	}

	public int xoaTaiKhoan(String maNhanVien) {
		query = "DELETE FROM TaiKhoan WHERE maNhanVien = ?";
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, maNhanVien);
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error deleting account: " + e.getMessage());
		}
		return -1;
	}

	public TaiKhoan getTaiKhoanTheoMaNV(String maNV) {
		TaiKhoan tk = new TaiKhoan();
		NhanVienDao nhanVienDao = new NhanVienDao();
		query = "SELECT * FROM TaiKhoan WHERE maNhanVien =?";
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, maNV);
			rs = ps.executeQuery();
			while (rs.next()) {
				tk = new TaiKhoan(rs.getString(1), rs.getString(2), nhanVienDao.timNhanVienTheoMa(rs.getString(3)),
						rs.getBoolean(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error retrieving account: " + e.getMessage());
		}
		return tk;
	}

	public int doiMatKhau(String passMoi, String maNV) {
		try {
			query = "UPDATE TaiKhoan SET matKhau = ? WHERE maNhanVien = ?";
			ps = con.prepareStatement(query);
			ps.setString(1, passMoi);
			ps.setString(2, maNV);
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error updating password: " + e.getMessage());
		}
		return -1;
	}
}
