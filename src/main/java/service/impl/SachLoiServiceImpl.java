package service.impl;

import java.sql.SQLException;
import java.util.List;

import dao.SachLoiDao;
import entities.SachLoi;
import service.SachLoiService;

public class SachLoiServiceImpl implements SachLoiService {
    SachLoiDao sachLoiDao = new SachLoiDao();

    @Override
    public boolean themSachLoi(SachLoi sl) {
        // TODO Auto-generated method stub
        return sachLoiDao.themSachLoi(sl);
    }

    @Override
    public boolean capNhatSoLuong(SachLoi sl) {
        // TODO Auto-generated method stub
        return sachLoiDao.capNhatSoLuong(sl);
    }

    @Override
    public List<SachLoi> getAllSachLoi() throws SQLException {
        // TODO Auto-generated method stub
        return sachLoiDao.getAllSachLoi();
    }

    @Override
    public boolean xoaSachLoi(String maSach, String loi) {
        return sachLoiDao.xoaSachLoi(maSach, loi);
    }

}
