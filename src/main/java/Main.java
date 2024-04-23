/**
 * @ (#) Main.java 1.0 17-Apr-24
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved.
 */

import dao.*;
import entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import service.ChatLieuService;
import service.KhachHangService;
import service.NhanVienService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * @description:
 * @author: Le Hoang Nam
 * @date: 17-Apr-24
 * @version: 1.0
 */
public class Main {
    private static final String URL = "rmi://172.20.10.2:7878/";

    public static void main(String[] args) throws Exception {
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

        NhanVienService nhanVienService = (NhanVienService) Naming.lookup(URL + "nhanVien");
        NhanVien nv = new NhanVien("NV000002", "Jack 5 củ",
                LocalDate.of(2002, 11, 9), "02120120462",
                "Ho Chi Minh thanh pho khong ngu", "0969828711", true, "leminhbao@gmail.com", false,
                false, "nvduy.jpg");
        nhanVienService.suaNhanVien(nv);
    }
}
