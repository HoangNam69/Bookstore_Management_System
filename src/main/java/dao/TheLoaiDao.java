package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entities.TheLoaiSach;
import entities.TheLoaiVanPhongPham;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class TheLoaiDao {
    private Connection con;
    private PreparedStatement ps = null;
    private ResultSet rs;
    private String query;
    private int rsCheck;
    private EntityManager em;

    public TheLoaiDao() {
        this.em = Persistence.createEntityManagerFactory("JPA_ORM_MARIADB").createEntityManager();
    }

    public List<TheLoaiSach> getListTheLoaiSach() {
        TypedQuery<TheLoaiSach> query = em.createQuery("SELECT t FROM TheLoaiSach t", TheLoaiSach.class);
        return query.getResultList();
    }

    public List<TheLoaiVanPhongPham> getListTheLoaiVanPhongPham() {
        TypedQuery<TheLoaiVanPhongPham> query = em.createQuery("SELECT t FROM TheLoaiVanPhongPham t", TheLoaiVanPhongPham.class);
        return query.getResultList();
    }

    public boolean themTheLoaiSach(TheLoaiSach t) {
        try {
            em.getTransaction().begin();
            em.persist(t);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    public boolean themTheLoaiVanPhongPham(TheLoaiVanPhongPham t) {
        try {
            em.getTransaction().begin();
            em.persist(t);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        }
    }


    public TheLoaiSach timTheLoaiSach(String tenTheLoai) {
        return (TheLoaiSach) em.createNativeQuery("SELECT * FROM TheLoaiSach WHERE tenTheLoai = ?", TheLoaiSach.class).setParameter(1, tenTheLoai).getSingleResult();
    }


	public TheLoaiVanPhongPham timTheLoaiVanPhongPham(String tenTheLoai) {
		return (TheLoaiVanPhongPham) em.createNativeQuery("SELECT * FROM TheLoaiVanPhongPham WHERE tenTheLoai = ?", TheLoaiVanPhongPham.class).setParameter(1, tenTheLoai).getSingleResult();
	}

	public List<TheLoaiSach> getSachTheoTheLoai(String maTL) {
		return em.createNativeQuery("SELECT * FROM TheLoaiSach WHERE maTheLoai = ?", TheLoaiSach.class).setParameter(1, maTL).getResultList();
	}

	public boolean kiemTraTonTaiTheLoaiVPP(String ten) {
		//neu ton tai thi tra ve true
		return em.createNativeQuery("SELECT COUNT(*) FROM TheLoaiVanPhongPham WHERE tenTheLoai = ?", TheLoaiVanPhongPham.class).setParameter(1, ten).getSingleResult() != null;
	}

	public boolean kiemTraTonTaiTheLoaiSach(String ten) {
		//neu ton tai thi tra ve true
		return em.createNativeQuery("SELECT COUNT(*) FROM TheLoaiSach WHERE tenTheLoai = ?", TheLoaiSach.class).setParameter(1, ten).getSingleResult() != null;
	}

}
