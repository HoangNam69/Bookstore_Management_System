package service.impl;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import dao.HoaDonDao;
import dao.ThongKeDao;
import entities.HoaDon;
import service.HoaDonService;

public class HoaDonServiceImpl implements HoaDonService {
	HoaDonDao hoaDonDao = new HoaDonDao();
	ThongKeDao thongKeDao = new ThongKeDao();

	@Override
	public int getSoLuongHoaDon(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws SQLException {
		// TODO Auto-generated method stub
		return thongKeDao.getSoLuongHoaDon(ngayBatDau, ngayKetThuc);
	}

	@Override
	public double getDoanhThu(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws SQLException {
		// TODO Auto-generated method stub
		return thongKeDao.getDoanhThu(ngayBatDau, ngayKetThuc);
	}

	@Override
	public double getDoanhThuTheoMaNhanVien(LocalDate ngayBatDau, LocalDate ngayKetThuc, String maNV)
			throws SQLException {
		// TODO Auto-generated method stub
		return thongKeDao.getDoanhThuTheoMaNhanVien(ngayBatDau, ngayKetThuc, maNV);
	}

	@Override
	public int getSoLuongHoaDonTheoMaNV(LocalDate ngayBatDau, LocalDate ngayKetThuc, String maNV) throws SQLException {
		// TODO Auto-generated method stub
		return thongKeDao.getSoLuongHoaDonTheoMaNV(ngayBatDau, ngayKetThuc, maNV);
	}

	@Override
	public int setNullChoMaNhanVienTrongHoaDon(String maNV) {
		// TODO Auto-generated method stub
		return hoaDonDao.setNullChoMaNhanVienTrongHoaDon(maNV);
	}

	@Override
	public List<HoaDon> getHoaDonTheoMa(String maHD) {
		// TODO Auto-generated method stub
		return hoaDonDao.getHoaDonTheoMa(maHD);
	}

	@Override
	public List<HoaDon> getDSHoaDon() throws SQLException {
		// TODO Auto-generated method stub
		return hoaDonDao.getDSHoaDon();
	}

	@Override
	public int doiThongTinHoaDonSauKhiXoa(String tenNV) {
		// TODO Auto-generated method stub
		return hoaDonDao.doiThongTinHoaDonSauKhiXoa(tenNV);
	}

	@Override
	public int themHoaDon(HoaDon hd) throws SQLException {
		// TODO Auto-generated method stub
		return hoaDonDao.themHoaDon(hd);
	}

	@Override
	public List<HoaDon> getHoaDonThuong() {
		// TODO Auto-generated method stub
		return hoaDonDao.getHoaDonThuong();
	}

	@Override
	public HoaDon timHoaDonTheoMa(String maHoaDon) {
		return hoaDonDao.timHoaDonTheoMa(maHoaDon);
	}

	@Override
	public List<HoaDon> getHoaDonTheoTen(String tenNV) {
			return hoaDonDao.getHoaDonTheoTen(tenNV);
	}

	@Override
	public List<HoaDon> timHoaDonTheoSDT(String sdt) {
		return hoaDonDao.timHoaDonTheoSDT(sdt);
	}

	@Override
	public List<HoaDon> timHoaDonTheoTenKH(String ten) {
			return hoaDonDao.timHoaDonTheoTenKH(ten);
	}


}
