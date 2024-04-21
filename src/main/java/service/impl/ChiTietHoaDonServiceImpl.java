package service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ChiTietHoaDonDao;
import entities.ChiTietHoaDon;
import entities.HoaDon;
import service.ChiTietHoaDonService;

public class ChiTietHoaDonServiceImpl implements ChiTietHoaDonService {
	private ChiTietHoaDonDao chiTietHoaDonDao;

	public ChiTietHoaDonServiceImpl() {
		this.chiTietHoaDonDao = new ChiTietHoaDonDao();
	}


	@Override
	public List<ChiTietHoaDon> getCTHoaDonTheoMaHoaDon(String maHD) {
		return chiTietHoaDonDao.getCTHoaDonTheoMaHoaDon(maHD);
	}

	@Override
	public ArrayList<ChiTietHoaDon> getCTHDTheoHoaDon(HoaDon hoaDon) {
		return chiTietHoaDonDao.getCTHDTheoHoaDon(hoaDon);
	}

	@Override
	public List<ChiTietHoaDon> getAllCTHD() {
		return chiTietHoaDonDao.getAllCTHD();
	}

	@Override
	public boolean addChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) {
		return  chiTietHoaDonDao.addChiTietHoaDon(chiTietHoaDon);
	}

	@Override
	public double getTien(String maHD) {
		return chiTietHoaDonDao.getTien(maHD);
	}
}
