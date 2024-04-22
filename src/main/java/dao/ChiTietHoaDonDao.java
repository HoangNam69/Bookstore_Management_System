package dao;

import java.util.ArrayList;
import java.util.List;

import entities.ChiTietHoaDon;
import entities.HoaDon;
import jakarta.persistence.*;

public class ChiTietHoaDonDao {
    private EntityManager em;
    private EntityManager em2;

    public ChiTietHoaDonDao() {
        em2 = Persistence.createEntityManagerFactory("JPA_ORM_MSSQL").createEntityManager();
        em = Persistence.createEntityManagerFactory("JPA_ORM_MARIADB").createEntityManager();
    }

    public List<ChiTietHoaDon> getCTHoaDonTheoMaHoaDon(String maHD) {
        TypedQuery<ChiTietHoaDon> query = em.createQuery("SELECT c FROM ChiTietHoaDon c WHERE c.hoaDon.maHoaDon = :maHD", ChiTietHoaDon.class);
        query.setParameter("maHD", maHD);
        return query.getResultList();
    }

    public ArrayList<ChiTietHoaDon> getCTHDTheoHoaDon(HoaDon hoaDon) {
        TypedQuery<ChiTietHoaDon> query = em.createQuery("SELECT c FROM ChiTietHoaDon c WHERE c.hoaDon.maHoaDon = :maHD", ChiTietHoaDon.class);
        query.setParameter("maHD", hoaDon.getMaHoaDon());
        return (ArrayList<ChiTietHoaDon>) query.getResultList();
    }

    public List<ChiTietHoaDon> getAllCTHD() {
        TypedQuery<ChiTietHoaDon> query = em.createQuery("SELECT c FROM ChiTietHoaDon c", ChiTietHoaDon.class);
        return query.getResultList();
    }

    public boolean addChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) {
        EntityTransaction tx = em.getTransaction();
        EntityTransaction tx2 = em2.getTransaction();
        try {
            tx.begin();
            em.persist(chiTietHoaDon);
            tx.commit();


            tx2.begin();
            em2.persist(chiTietHoaDon);
            tx2.commit();
            return true;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public double getTien(String maHD) {
        TypedQuery<Long> query = em.createQuery("SELECT SUM(c.soLuong * c.donGia) FROM ChiTietHoaDon c WHERE c.hoaDon.maHoaDon = :maHD", Long.class);
        query.setParameter("maHD", maHD);
        Long tong = query.getSingleResult();
        tong = (tong == null) ? 0L : tong;
        double vat = 0.08;
        double tongTien = tong + tong * vat;
        System.out.println("Tong tien" + tongTien);
        return tongTien;
    }
}
