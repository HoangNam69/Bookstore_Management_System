package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entities.HoaDonDoiTra;
import entities.KhachHang;
import entities.NhanVien;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class HoaDonDoiTraDao {
	private EntityManager em;
	private EntityManager em2;

	public HoaDonDoiTraDao() {
		this.em2 = Persistence.createEntityManagerFactory("JPA_ORM_MSSQL").createEntityManager();
		this.em = Persistence.createEntityManagerFactory("JPA_ORM_MARIADB").createEntityManager();
	}

	public int themHoaDonDoiTra(HoaDonDoiTra hddt) {
		em.getTransaction().begin();
		em.persist(hddt);
		em.getTransaction().commit();

		em2.getTransaction().begin();
		em2.persist(hddt);
		em2.getTransaction().commit();
		return 1;
	}

	public List<HoaDonDoiTra> getHoaDonDoiTraTheoMa(String maHDDT) {
		String query = "SELECT h FROM HoaDonDoiTra h WHERE h.maHoaDonDoiTra = :maHDDT";
		return em.createQuery(query, HoaDonDoiTra.class)
				.setParameter("maHDDT", maHDDT)
				.getResultList();
	}

	public List<HoaDonDoiTra> getDSHoaDonDoiTra() {
		String query = "SELECT h FROM HoaDonDoiTra h";
		return em.createQuery(query, HoaDonDoiTra.class).getResultList();
	}

	public int editTienKhachTra(HoaDonDoiTra hddt) {
		em.getTransaction().begin();
		hddt = em.merge(hddt);
		em.getTransaction().commit();
		return 1;
	}

	public int editTienPhaiTru(HoaDonDoiTra hddt) {
		em.getTransaction().begin();
		hddt = em.merge(hddt);
		em.getTransaction().commit();
		return 1;
	}

	public List<HoaDonDoiTra> getMaHoaDonDoiTraByMaHDCu(String maHDCu) {
		String query = "SELECT h FROM HoaDonDoiTra h WHERE h.hoaDon.maHoaDon = :maHDCu";
		return em.createQuery(query, HoaDonDoiTra.class)
				.setParameter("maHDCu", maHDCu)
				.getResultList();
	}

	public List<HoaDonDoiTra> getToanBoDSHoaDonDoiTra() {
		String query = "SELECT h FROM HoaDonDoiTra h";
		return em.createQuery(query, HoaDonDoiTra.class).getResultList();
	}

	public HoaDonDoiTra timHoaDonDoiTraTheoMa(String maHoaDon) {
		HoaDonDoiTra hoaDonDT = em.find(HoaDonDoiTra.class, maHoaDon);
		return hoaDonDT;
	}

	public List<HoaDonDoiTra> getHoaDonDoiTraTheoTen(String tenNV) {
		String query = "SELECT h FROM HoaDonDoiTra h WHERE h.nhanVien.hoTenNhanVien LIKE :tenNV";
		return em.createQuery(query, HoaDonDoiTra.class)
				.setParameter("tenNV", "%" + tenNV + "%")
				.getResultList();
	}

	public List<HoaDonDoiTra> getHoaDonDoiTraTheoSDT(String sdt) {
		String query = "SELECT h FROM HoaDonDoiTra h WHERE h.khachHang.sDT = :sdt";
		return em.createQuery(query, HoaDonDoiTra.class)
				.setParameter("sdt", sdt)
				.getResultList();
	}

	public List<HoaDonDoiTra> getHoaDonDoiTraTheoTenKH(String tenKH) {
		String query = "SELECT h FROM HoaDonDoiTra h WHERE h.khachHang.hoTenKhachHang LIKE :tenKH";
		return em.createQuery(query, HoaDonDoiTra.class)
				.setParameter("tenKH", "%" + tenKH + "%")
				.getResultList();
	}

}
