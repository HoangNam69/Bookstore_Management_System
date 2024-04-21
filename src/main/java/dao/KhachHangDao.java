package dao;

import java.util.ArrayList;
import java.util.List;

import entities.KhachHang;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class KhachHangDao {
	private EntityManager em;

	public KhachHangDao() {
		em = Persistence.createEntityManagerFactory("JPA_ORM_MARIADB").createEntityManager();
	}

	public int  themKhachHang(KhachHang kh) {
		em.getTransaction().begin();
		em.persist(kh);
		em.getTransaction().commit();
		return 1;
	}

	public List<KhachHang> getDSKhachHang() {
		String query = "SELECT k FROM KhachHang k";
		return em.createQuery(query, KhachHang.class).getResultList();
	}

	public KhachHang timKhachHangTheoMa(String maKH) {
		return em.find(KhachHang.class, maKH);
	}

	public ArrayList<KhachHang> timKhachHangTheoTen(String tenKH) {
		String query = "SELECT k FROM KhachHang k WHERE k.hoTenKhachHang LIKE :tenKH";
		return (ArrayList<KhachHang>) em.createQuery(query, KhachHang.class)
				.setParameter("tenKH", "%" + tenKH + "%")
				.getResultList();
	}

	public int capNhatKhachHang(KhachHang kh) {
		em.getTransaction().begin();
		kh = em.merge(kh);
		em.getTransaction().commit();
		return 1;
	}

	public ArrayList<KhachHang> timKhachHangTheoSDT(String sDT) {
		String query = "SELECT k FROM KhachHang k WHERE k.sDT LIKE :sDT";
		return (ArrayList<KhachHang>) em.createQuery(query, KhachHang.class)
				.setParameter("sDT", "%" + sDT + "%")
				.getResultList();
	}

	public ArrayList<KhachHang> getListKhachHangByNameAndSDT(String tenKH, String sDT) {
		String query = "SELECT k FROM KhachHang k WHERE k.hoTenKhachHang LIKE :tenKH OR k.sDT LIKE :sDT";
		return (ArrayList<KhachHang>) em.createQuery(query, KhachHang.class)
				.setParameter("tenKH", "%" + tenKH + "%")
				.setParameter("sDT", "%" + sDT + "%")
				.getResultList();
	}

	public KhachHang timKhachHangBangSDT(String sDT) {
		String query = "SELECT k FROM KhachHang k WHERE k.sDT = :sDT";
		List<KhachHang> results = em.createQuery(query, KhachHang.class)
				.setParameter("sDT", sDT)
				.getResultList();
		return results.isEmpty() ? null : results.get(0);
	}

}
