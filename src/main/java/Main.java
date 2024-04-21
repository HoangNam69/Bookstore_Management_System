/**
 * @ (#) Main.java 1.0 17-Apr-24
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved.
 */

import dao.MauSacDao;
import entities.MauSac;
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
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_ORM_MARIADB");
//        EntityManager em = emf.createEntityManager();
//        EntityTransaction tx = em.getTransaction();
//
//        try {
//            tx.begin();
//            tx.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            tx.rollback();
//        }
//
//        em.close();
//        emf.close();

        MauSacDao mauSacDao = new MauSacDao();
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

        if (mauSacDao.xoaMauSac("MS003s")) {
            System.out.println("Xóa thành công");
        } else {
            System.out.println("Xóa thất bại");
        }
    }
}
