/**
 * @ (#) Main.java 1.0 17-Apr-24
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved.
 */

import dao.MauSacDao;
import dao.NhaCungCapDao;
import dao.NhaXuatBanDao;
import dao.NhanVienDao;
import entities.MauSac;
import entities.NhaCungCap;
import entities.NhaXuatBan;
import entities.NhanVien;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

//        NhaCungCap ncc = nhaCungCapDao.timNhaCungCapTheoTen("Nam");
//        System.out.println(ncc);


//        ---------------------------------------------------- Nhân viên ----------------------------------------------------
//        NhanVienDao nhanVienDao = new NhanVienDao();
//        NhanVien nv = new NhanVien("NV002", "Nguyen Hong Duc",
//                LocalDate.of(2003, 4, 30), "123456786",
//                "416/23/17 DQH-GV-HCM", "0123456786", true, "hongduc@gmail.com", false,
//                false, "hinh ne");
//        if (nhanVienDao.themNhanvien(nv)) {
//            System.out.println("Thêm thành công");
//        } else {
//            System.out.println("Thêm thất bại");
//        }
//        List<NhanVien> list = nhanVienDao.getDSNhanVien();
//        for (NhanVien nhanVien : list) {
//            System.out.println(nhanVien);
//        }

//        List<NhanVien> list = nhanVienDao.timDanhSachNhanVienTheoMa("NV0");
//        for (NhanVien nhanVien : list) {
//            System.out.println(nhanVien);
//        }

//        NhanVien nv = nhanVienDao.timNhanVienTheoMa("NV003");
//        System.out.println(nv);

//        List<NhanVien> list = nhanVienDao.timDSNhanVienTheoTen("L");
//        for (NhanVien nhanVien : list) {
//            System.out.println(nhanVien);
//        }

//        NhanVien nv = nhanVienDao.timNhanVienTheoTen("Le Hoang Nam");
//        System.out.println(nv);

//        List<NhanVien> nvs = nhanVienDao.timNhanVienTheoSDT("786");
//        for (NhanVien nv : nvs) {
//            System.out.println(nv);
//        }

//        List<NhanVien> nvs = nhanVienDao.getListNhanVienByNameAndSDT("V","786");
//        for (NhanVien nv : nvs) {
//            System.out.println(nv);
//        }

//        NhanVien nv = nhanVienDao.getNhanVienByEmail("hoangnam@gmail.com");
//        System.out.println(nv);

//        NhanVien nv = new NhanVien("NV002", "Nguyen Hong Duc",
//                LocalDate.of(2003, 4, 30), "123456786",
//                "416/23/17 DQH-GV-HCM", "0986030412", true, "ngduc6061@gmail.com", false,
//                false, "hinh ne");
//        if (nhanVienDao.suaNhanVien(nv)) {
//            System.out.println("Cập nhật thành công");
//        } else {
//            System.out.println("Cập nhật thất bại");
//        }

//        if (nhanVienDao.xoaNhanVien("NV001")) {
//            System.out.println("Xóa thành công");
//        } else {
//            System.out.println("Xóa thất bại");
//        }

//
//        ---------------------------------------------------- Nhà xuất bản ----------------------------------------------------
        NhaXuatBanDao nhaXuatBanDao = new NhaXuatBanDao();
//        List<NhaXuatBan> list = nhaXuatBanDao.getListNhaXuatBan();
//        for (NhaXuatBan nhaXuatBan : list) {
//            System.out.println(nhaXuatBan);
//        }

//        NhaXuatBan nxb = new NhaXuatBan("NXB003", "NXB Le Hoang Nam");
//        if (nhaXuatBanDao.themNhaXuatBan(nxb)) {
//            System.out.println("Thêm thành công");
//        } else {
//            System.out.println("Thêm thất bại");
//        }

//        NhaXuatBan nxb = nhaXuatBanDao.timNhaXuatBan("NXB Le Hoang Nam");
//        System.out.println(nxb);

    }
}
