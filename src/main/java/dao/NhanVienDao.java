package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entities.NhanVien;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class NhanVienDao {
    private EntityManager em;

    public NhanVienDao() {
        this.em = Persistence.createEntityManagerFactory("JPA_ORM_MARIADB").createEntityManager();
    }

    public boolean themNhanvien(NhanVien nv) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(nv);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return false;
        }

    }


    public List<NhanVien> getDSNhanVien() {
        return em.createNativeQuery("SELECT * FROM NhanVien", NhanVien.class).getResultList();
    }

    public List<NhanVien> timDanhSachNhanVienTheoMa(String maNV) {
        try {
            return em.createQuery("SELECT nv FROM NhanVien nv WHERE nv.maNhanVien LIKE :maNV", NhanVien.class)
                    .setParameter("maNV", "%" + maNV + "%")
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public NhanVien timNhanVienTheoMa(String maNV) {
        try {
            return (NhanVien) em.createNativeQuery("SELECT * FROM NhanVien WHERE maNhanVien=?", NhanVien.class)
                    .setParameter(1, maNV)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<NhanVien> timDSNhanVienTheoTen(String tenNV) {
        try {
            return em.createQuery("SELECT nv FROM NhanVien nv WHERE nv.hoTenNhanVien LIKE :tenNV", NhanVien.class)
                    .setParameter("tenNV", "%" + tenNV + "%")
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public NhanVien timNhanVienTheoTen(String tenNV) {
        try {
            return (NhanVien) em.createNativeQuery("SELECT * FROM NhanVien WHERE hoTenNhanVien LIKE ?", NhanVien.class)
                    .setParameter(1, tenNV)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<NhanVien> timNhanVienTheoSDT(String sdt) {
        try {
            return em.createQuery("SELECT nv FROM NhanVien nv WHERE nv.sDT LIKE :sdt", NhanVien.class)
                    .setParameter("sdt", "%" + sdt + "%")
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public List<NhanVien> getListNhanVienByNameAndSDT(String tenNV, String sdt) {
        return em.createQuery("SELECT nv FROM NhanVien nv WHERE nv.hoTenNhanVien LIKE :tenNV OR nv.sDT LIKE :sdt", NhanVien.class)
                .setParameter("tenNV", "%" + tenNV + "%")
                .setParameter("sdt", "%" + sdt + "%")
                .getResultList();
    }

    public boolean xoaNhanVien(String maNV) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            NhanVien nv = em.find(NhanVien.class, maNV);
            em.remove(nv);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean suaNhanVien(NhanVien nv) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            NhanVien nv1 = em.find(NhanVien.class, nv.getMaNhanVien());
            em.merge(nv1);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    public NhanVien getNhanVienByEmail(String email) {
        try {
            return (NhanVien) em.createNativeQuery("SELECT * FROM NhanVien WHERE email = ?", NhanVien.class)
                    .setParameter(1, email)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    public boolean updateOTP(String email, String OTP, Timestamp hetHanOTP) {
//        EntityTransaction tx = em.getTransaction();
//        try {
//            tx.begin();
//            int rs = em.createNativeQuery("UPDATE NhanVien SET OTP = ?, hetHanOTP = ? WHERE email = ?")
//                    .setParameter(1, OTP)
//                    .setParameter(2, hetHanOTP)
//                    .setParameter(3, email)
//                    .executeUpdate();
//            tx.commit();
//            return true;
//        } catch (Exception e) {
//            tx.rollback();
//            e.printStackTrace();
//            return false;
//        }
//    }

}
