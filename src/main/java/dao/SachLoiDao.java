package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entities.Sach;
import entities.SachLoi;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class SachLoiDao {
    private EntityManager em;

    public SachLoiDao() {
        this.em = Persistence.createEntityManagerFactory("JPA_ORM_MARIADB")
                .createEntityManager();
    }

    public boolean themSachLoi(SachLoi sl) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(sl);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean capNhatSoLuong(SachLoi sl) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(sl);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public List<SachLoi> getAllSachLoi() {
        return em.createNativeQuery("SELECT * FROM SachLoi", SachLoi.class)
                .getResultList();
    }

    public boolean xoaSachLoi(String maSach, String loi) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.createNativeQuery("DELETE FROM SachLoi WHERE maSach = ? AND loiSanPham = ?")
                    .setParameter(1, maSach)
                    .setParameter(2, loi)
                    .executeUpdate();
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return false;
        }
    }
}
