package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.ChiTietHoaDon;
import entities.HoaDon;

public interface ChiTietHoaDonService {
	public List<ChiTietHoaDon> getCTHoaDonTheoMaHoaDon(String maHD);

	public ArrayList<ChiTietHoaDon> getCTHDTheoHoaDon(HoaDon hoaDon);

	public List<ChiTietHoaDon> getAllCTHD() throws SQLException;

	public int addChiTietHoaDon(ChiTietHoaDon chiTietHoaDon);
	
	public double getTien(String maHD);
}
