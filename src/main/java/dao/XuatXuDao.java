package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;

import entities.XuatXu;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class XuatXuDao {
	private Connection con;
	private PreparedStatement ps = null;
	private ResultSet rs;
	private String query;
	private int rsCheck;

	private EntityManager em;

	public XuatXuDao() {
		this.em = Persistence.createEntityManagerFactory("JPA_ORM_MARIADB").createEntityManager();
	}

	public List<XuatXu> getListXuatXu() {
		return em.createQuery("SELECT xx FROM XuatXu xx", XuatXu.class).getResultList();
	}

	public boolean themXuatXu(XuatXu x) {
		try {
			em.getTransaction().begin();
			em.persist(x);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			e.printStackTrace();
			return false;
		}
	}

	public List<XuatXu> getXuatXu(String maXuatXu) {
		Query query = em.createQuery("SELECT xx FROM XuatXu xx WHERE xx.maXuatXu = :maXuatXu", XuatXu.class);
		query.setParameter("maXuatXu", maXuatXu);
		return query.getResultList();
	}

	public boolean xoaXuatXu(String maXuatXu) {
		try {
			em.getTransaction().begin();
			XuatXu xuatXu = em.find(XuatXu.class, maXuatXu);
			if (xuatXu != null) {
				em.remove(xuatXu);
				em.getTransaction().commit();
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			e.printStackTrace();
			return false;
		}
	}

	public XuatXu timXuatXu(String tenXuatXu) {
		Query query = em.createQuery("SELECT xx FROM XuatXu xx WHERE xx.tenXuatXu = :tenXuatXu", XuatXu.class);
		query.setParameter("tenXuatXu", tenXuatXu);
		List<XuatXu> resultList = query.getResultList();
		return resultList.isEmpty() ? null : resultList.get(0);
	}


	public boolean kiemTraTonTaiXuatXu(String ten) {
		Query query = em.createQuery("SELECT COUNT(xx) FROM XuatXu xx WHERE xx.tenXuatXu = :tenXuatXu");
		query.setParameter("tenXuatXu", ten);
		long count = (Long) query.getSingleResult();
		return count > 0;
	}
}
