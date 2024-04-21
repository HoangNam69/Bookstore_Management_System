package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entities.HoaDon;
import entities.KhachHang;
import entities.NhanVien;
import jakarta.persistence.*;

public class HoaDonDao {
	private EntityManager em;

	public HoaDonDao() {
		em = Persistence.createEntityManagerFactory("JPA_ORM_MARIADB").createEntityManager();
	}

	public int setNullChoMaNhanVienTrongHoaDon(String maNV) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Query query = em.createQuery("UPDATE HoaDon h SET h.nhanVien = NULL WHERE h.nhanVien.maNhanVien = :maNV");
		query.setParameter("maNV", maNV);
		int result = query.executeUpdate();
		transaction.commit();
		return result;
	}

	public List<HoaDon> getDSHoaDon() {
		TypedQuery<HoaDon> query = em.createQuery("SELECT h FROM HoaDon h ORDER BY h.ngayLapHoaDon DESC", HoaDon.class);
		return query.getResultList();
	}

	public int doiThongTinHoaDonSauKhiXoa(String tenNV) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		Query query = em.createQuery("UPDATE HoaDon h SET h.nhanVien = NULL WHERE h.nhanVien IS NULL");
		int result = query.executeUpdate();
		transaction.commit();
		return result;
	}


	public int themHoaDon(HoaDon hd) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(hd);
		transaction.commit();
		return 1;
	}

	public List<HoaDon> getHoaDonTheoMa(String maHD) {
		TypedQuery<HoaDon> query = em.createQuery("SELECT h FROM HoaDon h WHERE h.maHoaDon = :maHD", HoaDon.class);
		query.setParameter("maHD", maHD);
		return query.getResultList();
	}

	public List<HoaDon> getHoaDonThuong() {
		TypedQuery<HoaDon> query = em.createQuery("SELECT h FROM HoaDon h", HoaDon.class);
		return query.getResultList();
	}

	public HoaDon timHoaDonTheoMa(String maHoaDon) {
		return em.find(HoaDon.class, maHoaDon);
	}

	public List<HoaDon> getHoaDonTheoTen(String tenNV) {
		TypedQuery<HoaDon> query = em.createQuery("SELECT h FROM HoaDon h WHERE h.nhanVien.hotenNhanVien LIKE :tenNV", HoaDon.class);
		query.setParameter("tenNV", "%" + tenNV + "%");
		return query.getResultList();
	}

	public List<HoaDon> timHoaDonTheoSDT(String sdt) {
		TypedQuery<HoaDon> query = em.createQuery("SELECT h FROM HoaDon h WHERE h.khachHang.sdt = :sdt", HoaDon.class);
		query.setParameter("sdt", sdt);
		return query.getResultList();
	}

	public List<HoaDon> timHoaDonTheoTenKH(String ten) {
		TypedQuery<HoaDon> query = em.createQuery("SELECT h FROM HoaDon h JOIN h.nhanVien n JOIN h.khachHang k WHERE k.hotenKhachHang LIKE :ten", HoaDon.class);
		query.setParameter("ten", "%" + ten + "%");
		return query.getResultList();
	}


}
