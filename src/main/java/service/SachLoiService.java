package service;

import java.sql.SQLException;
import java.util.List;

import entities.SachLoi;

public interface SachLoiService {
	public boolean themSachLoi(SachLoi sl) throws SQLException;

	public boolean capNhatSoLuong(SachLoi sl);

	public List<SachLoi> getAllSachLoi() throws SQLException;
	public boolean xoaSachLoi(String maSach, String loi);
}
