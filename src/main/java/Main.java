/**
 * @ (#) Main.java 1.0 17-Apr-24
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved.
 */

import dao.SanPhamDao;
import entities.SanPham;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.ArrayList;

/**
 * @description:
 * @author: Le Hoang Nam
 * @date: 17-Apr-24
 * @version: 1.0
 */
public class Main {
    public static void main(String[] args) {
//        JPA_ORM_MARIADB
//        JPA_ORM_MSSQL
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_ORM_MARIADB");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }

        em.close();
        emf.close();
//        ---------------------------------------------------- Màu sắc ----------------------------------------------------
//        MauSacDao mauSacDao = new MauSacDao();
//        ArrayList<MauSac> list = mauSacDao.getListMauSac();
//        for (MauSac mauSac : list) {
//            System.out.println(mauSac);
//        }

//        MauSac mauSac = new MauSac("MS003", "Màu đỏ");
//        if (mauSacDao.themMauSac(mauSac)) {
//            System.out.println("Thêm thành công");
//        } else {
//            System.out.println("Thêm thất bại");
//        }

//        MauSac mauSacCapNhat = new MauSac("MS003", "Màu khùng");
//        if (mauSacDao.capNhatMauSac(mauSacCapNhat)) {
//            System.out.println("Cập nhật thành công");
//        } else {
//            System.out.println("Cập nhật thất bại");
//        }

//        if (mauSacDao.xoaMauSac("MS003s")) {
//            System.out.println("Xóa thành công");
//        } else {
//            System.out.println("Xóa thất bại");
//        }

//        ---------------------------------------------------- Nhà cung cấp ----------------------------------------------------
//        NhaCungCapDao nhaCungCapDao = new NhaCungCapDao();
//        nhaCungCapDao.getListNhaCungCapTheoLoaiSanPham("Áo");
//        NhaCungCap ncc = new NhaCungCap("NCC003", "Le Hoang Nam", "DQH-GV-HCM", "hoangnam@gmail.com", "0123456789");
//        if (nhaCungCapDao.themNhaCungCap(ncc)) {
//            System.out.println("Thêm thành công");
//        } else {
//            System.out.println("Thêm thất bại");
//        }
//        ArrayList<NhaCungCap> listNCC = nhaCungCapDao.getAllListNhaCungCap();
//        for (NhaCungCap nhaCungCap : listNCC) {
//            System.out.println(nhaCungCap);
//        }

        SanPhamDao sanPhamDao = new SanPhamDao();
        SanPham sp = sanPhamDao.timSanPhamTheoMa("SP00019");

        System.out.println(sp);

    }
}
