package service;

import java.sql.SQLException;
import java.util.List;

import entities.HoaDonDoiTra;

public interface HoaDonDoiTraService {
	public int themHoaDonDoiTra(HoaDonDoiTra hddt) throws SQLException;

	public List<HoaDonDoiTra> getHoaDonDoiTraTheoMa(String maHDDT);

	public List<HoaDonDoiTra> getDSHoaDonDoiTra() throws SQLException;

	public int editTienKhachTra(HoaDonDoiTra hddt);

	public List<HoaDonDoiTra> getMaHoaDonDoiTraByMaHDCu(String maHDCu);

	public int editTienPhaiTru(HoaDonDoiTra hddt);
	
	public List<HoaDonDoiTra> getToanBoDSHoaDonDoiTra() throws SQLException ;

	// Tim hoa don doi tra theo ma
	public HoaDonDoiTra timHoaDonDoiTraTheoMa(String maHoaDon);
	

// Tim hóa đơn đổi trả theo tên nhân viên
	public List<HoaDonDoiTra> getHoaDonDoiTraTheoTen(String tenNV) throws SQLException ;
	

	public List<HoaDonDoiTra> getHoaDonDoiTraTheoSDT(String sdt);

	public List<HoaDonDoiTra> getHoaDonDoiTraTheoTenKH(String tenKH);
}
