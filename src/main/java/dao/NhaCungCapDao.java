package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.NhaCungCap;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class NhaCungCapDao {

    private EntityManager em;

    public NhaCungCapDao() {
        em = Persistence.createEntityManagerFactory("JPA_ORM_MARIADB")
                .createEntityManager();
    }

    public ArrayList<NhaCungCap> getListNhaCungCapTheoLoaiSanPham(String loaiSanPham) {
        try {
            return (ArrayList<NhaCungCap>) em.createNativeQuery("SELECT ncc.* FROM NhaCungCap as ncc " +
                            "INNER JOIN SanPham as sp ON ncc.maNCC = sp.maNCC " +
                            "WHERE sp.loaiSanPham LIKE ?", NhaCungCap.class)
                    .setParameter(1, loaiSanPham)
                    .getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean themNhaCungCap(NhaCungCap ncc) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            em.persist(ncc);

            tx.commit();

            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<NhaCungCap> getAllListNhaCungCap() {
        return (ArrayList<NhaCungCap>) em.createNativeQuery("SELECT * FROM NhaCungCap", NhaCungCap.class).getResultList();
    }

    public NhaCungCap timNhaCungCapTheoTen(String tenNCC) {
        try {
            return (NhaCungCap) em.createNativeQuery("SELECT * FROM NhaCungCap WHERE tenNCC = ?", NhaCungCap.class)
                    .setParameter(1, tenNCC)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean kiemTraTonTaiNCC(String ten) {
        return em.createNativeQuery("SELECT * FROM NhaCungCap WHERE tenNCC = ?", NhaCungCap.class)
                .setParameter(1, ten)
                .getResultList().size() > 0;
    }
}
