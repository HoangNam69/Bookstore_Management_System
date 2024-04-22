package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entities.NhaXuatBan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class NhaXuatBanDao {
    private EntityManager em;

    public NhaXuatBanDao() {
        this.em = Persistence.createEntityManagerFactory("JPA_ORM_MARIADB").createEntityManager();
    }

    public List<NhaXuatBan> getListNhaXuatBan() {
        return em.createNativeQuery("SELECT * FROM NhaXuatBan", NhaXuatBan.class).getResultList();
    }

    public boolean themNhaXuatBan(NhaXuatBan nxb) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(nxb);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public NhaXuatBan timNhaXuatBan(String nxb) {
        try {
            return (NhaXuatBan) em.createNativeQuery("SELECT * FROM NhaXuatBan WHERE tenNXB = ?", NhaXuatBan.class)
                    .setParameter(1, nxb)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean kiemTraTonTaiNXB(String ten) {
        return em.createNativeQuery("SELECT * FROM NhaXuatBan WHERE tenNXB = ?", NhaXuatBan.class)
                .setParameter(1, ten)
                .getResultList().size() > 0;
    }
}
