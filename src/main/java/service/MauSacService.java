package service;

import java.sql.SQLException;
import java.util.ArrayList;

import entities.MauSac;

public interface MauSacService {
    public ArrayList<MauSac> getListMauSac() throws Exception;

    public boolean themMauSac(MauSac mauSac) throws Exception;

    public MauSac timMauSac(String Mau) throws SQLException;

    public boolean capNhatMauSac(MauSac mauSac) throws SQLException;

    public boolean xoaMauSac(String maMauSac) throws SQLException;
}
