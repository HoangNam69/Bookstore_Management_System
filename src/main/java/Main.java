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

        SanPhamDao sanPhamDao = new SanPhamDao();
        SanPham sp = sanPhamDao.timSanPhamTheoMa("SP00019");

        System.out.println(sp);

    }
}
