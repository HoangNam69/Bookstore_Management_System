package service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import entities.HoaDon;

public interface HoaDonService {
	public int getSoLuongHoaDon(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws SQLException;
	public double getDoanhThu(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws SQLException;
	public double getDoanhThuTheoMaNhanVien(LocalDate ngayBatDau, LocalDate ngayKetThuc, String maNV) throws SQLException;
	public int getSoLuongHoaDonTheoMaNV(LocalDate ngayBatDau, LocalDate ngayKetThuc, String maNV) throws SQLException;
	public int setNullChoMaNhanVienTrongHoaDon(String maNV);
	public List<HoaDon> getHoaDonTheoMa(String maHD);
	public List<HoaDon> getDSHoaDon() throws SQLException;
	public int doiThongTinHoaDonSauKhiXoa(String tenNV);
	public int themHoaDon(HoaDon hd) throws SQLException;
	public List<HoaDon> getHoaDonThuong() ;
	public HoaDon timHoaDonTheoMa(String maHoaDon);
	public List<HoaDon> getHoaDonTheoTen(String tenNV);
	public List<HoaDon> timHoaDonTheoSDT(String sdt);
	public List<HoaDon> timHoaDonTheoTenKH(String ten);
}