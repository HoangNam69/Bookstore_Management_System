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

    private Connection con;
    private int rsCheck;

    private EntityManager em;

    public NhaCungCapDao() {
        em = Persistence.createEntityManagerFactory("JPA_ORM_MARIADB")
                .createEntityManager();
    }

    public ArrayList<NhaCungCap> getListNhaCungCapTheoLoaiSanPham(String loaiSanPham) {
        return (ArrayList<NhaCungCap>) em.createNativeQuery("SELECT distinct NhaCungCap.maNCC, NhaCungCap.tenNCC FROM NhaCungCap " +
                        "INNER JOIN SanPham ON NhaCungCap.maNCC = SanPham.maNCC " +
                        "WHERE SanPham.loaiSanPham LIKE ?", NhaCungCap.class)
                .setParameter(1, loaiSanPham)
                .getResultList();
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
        return (NhaCungCap) em.createQuery("SELECT n FROM NhaCungCap n WHERE n.tenNCC LIKE :tenNCC", NhaCungCap.class)
                .setParameter("tenNCC", "%" + tenNCC + "%")
                .getSingleResult();
    }

    public boolean kiemTraTonTaiNCC(String ten) {
        return em.createNativeQuery("SELECT * FROM NhaCungCap WHERE tenNCC = ?", NhaCungCap.class)
                .setParameter(1, ten)
                .getResultList().size() > 0;
    }
}
