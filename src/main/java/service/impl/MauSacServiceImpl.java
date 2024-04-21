package service.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.MauSacDao;
import entities.MauSac;
import service.MauSacService;

public class MauSacServiceImpl implements MauSacService {
    MauSacDao mauSacDao = new MauSacDao();

    @Override
    public ArrayList<MauSac> getListMauSac() throws Exception {
        return mauSacDao.getListMauSac();
    }

    @Override
    public boolean themMauSac(MauSac mauSac) throws Exception {
        if (mauSacDao.kiemTraTonTaiMauSac(mauSac))
            return false;
        return mauSacDao.themMauSac(mauSac);
    }

    @Override
    public MauSac timMauSac(String mau) throws SQLException {
        return mauSacDao.timMauSac(mau);
    }

    @Override
    public boolean capNhatMauSac(MauSac mauSac) throws SQLException {
        return mauSacDao.capNhatMauSac(mauSac);
    }

    @Override
    public boolean xoaMauSac(String maMauSac) throws SQLException {
        return mauSacDao.xoaMauSac(maMauSac);
    }

}
