package dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;


public class ThongKeDao {

    private Connection con;
    private PreparedStatement ps = null;
    private ResultSet rs;
    private String query;
    private int rsCheck;
    private List<SanPham> dsSP;
    private List<NhanVien> dsNV;
    private List<KhachHang> dsKH;
    private ArrayList<NhanVien> dsNV1;
    private EntityManager em;

    public ThongKeDao() {
        this.em = Persistence.createEntityManagerFactory("JPA_ORM_MARIADB").createEntityManager();
    }


    public List<NhanVien> getNhanVienBanNhieuNhatTheoNgayTuChon(LocalDate ngayBatDau, LocalDate ngayKetThuc) {
        try {
            TypedQuery<NhanVien> query = em.createQuery(
                    "SELECT DISTINCT h.nhanVien " +
                            "FROM HoaDon h " +
                            "WHERE h.ngayLapHoaDon BETWEEN :ngayBatDau AND :ngayKetThuc " +
                            "GROUP BY h.nhanVien " +
                            "HAVING COUNT(h) >= ALL(" +
                            "    SELECT COUNT(h2) " +
                            "    FROM HoaDon h2 " +
                            "    WHERE h2.ngayLapHoaDon BETWEEN :ngayBatDau AND :ngayKetThuc " +
                            "    GROUP BY h2.nhanVien)",
                    NhanVien.class
            );
            query.setParameter("ngayBatDau", ngayBatDau.atStartOfDay());
            query.setParameter("ngayKetThuc", ngayKetThuc.atStartOfDay());

            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<SanPham> getSanPhamBanNhieuNhatTheoNgayTuChon(LocalDate ngayBatDau, LocalDate ngayKetThuc) {
        try {
            String jpql = "SELECT s FROM SanPham s " +
                    "JOIN s.chiTietHoaDons c " +
                    "JOIN c.hoaDon h " +
                    "WHERE h.ngayLapHoaDon BETWEEN :ngayBatDau AND :ngayKetThuc " +
                    "GROUP BY s " +
                    "HAVING SUM(c.soLuong) >= ALL(" +
                    "   SELECT SUM(c1.soLuong) " +
                    "   FROM ChiTietHoaDon c1 " +
                    "   JOIN c1.hoaDon h1 " +
                    "   WHERE h1.ngayLapHoaDon BETWEEN :ngayBatDau AND :ngayKetThuc " +
                    "   GROUP BY c1.sanPham" +
                    ")";

            Query query = em.createQuery(jpql, SanPham.class);
            query.setParameter("ngayBatDau", ngayBatDau);
            query.setParameter("ngayKetThuc", ngayKetThuc);

            List<SanPham> sanPhams = query.getResultList();
            return sanPhams;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public int getSoLuongSachTon() {
        try {
            TypedQuery<Long> query = em.createQuery(
                    "SELECT SUM(s.soLuongTon) FROM SanPham s WHERE s.loaiSanPham = 'Sách'",
                    Long.class
            );
            Long result = query.getSingleResult();
            return result != null ? result.intValue() : 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSoLuongVPPTon() {
        try {
            TypedQuery<Long> query = em.createQuery(
                    "SELECT SUM(s.soLuongTon) FROM SanPham s WHERE s.loaiSanPham = 'Văn phòng phẩm'",
                    Long.class
            );
            Long result = query.getSingleResult();
            return result != null ? result.intValue() : 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSoLuongSachLoi() {
        try {
            TypedQuery<Long> query = em.createQuery(
                    "SELECT SUM(s.soLuong) FROM SachLoi s",
                    Long.class
            );
            Long result = query.getSingleResult();
            return result != null ? result.intValue() : 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


    public int getSoLuongHoaDon(LocalDate ngayBatDau, LocalDate ngayKetThuc) {
        try {
            TypedQuery<Long> query = em.createQuery(
                    "SELECT COUNT(h) FROM HoaDon h " +
                            "WHERE h.ngayLapHoaDon BETWEEN :ngayBatDau AND :ngayKetThuc", Long.class);
            query.setParameter("ngayBatDau", ngayBatDau.atStartOfDay());
            query.setParameter("ngayKetThuc", ngayKetThuc.atStartOfDay());

            return Math.toIntExact(query.getSingleResult());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public double getDoanhThu(LocalDate ngayBatDau, LocalDate ngayKetThuc) {
        try {
            TypedQuery<Double> query = em.createQuery(
                    "SELECT SUM(c.soLuong * c.donGia) FROM ChiTietHoaDon c " +
                            "INNER JOIN c.hoaDon h " +
                            "WHERE h.ngayLapHoaDon BETWEEN :ngayBatDau AND :ngayKetThuc", Double.class);
            query.setParameter("ngayBatDau", ngayBatDau.atStartOfDay());
            query.setParameter("ngayKetThuc", ngayKetThuc.atStartOfDay());

            Double result = query.getSingleResult();
            return result != null ? result : 0.0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0.0;
    }


    public List<KhachHang> getKhachHangMuaNhieuNhatTheoNgayTuChon(LocalDate ngayBatDau, LocalDate ngayKetThuc) {
        List<KhachHang> dsKH = new ArrayList<>();
        try {
            TypedQuery<KhachHang> query = em.createQuery(
                    "SELECT DISTINCT k FROM HoaDon h " +
                            "JOIN h.khachHang k " +
                            "WHERE h.ngayLapHoaDon BETWEEN :ngayBatDau AND :ngayKetThuc " +
                            "GROUP BY k " +
                            "HAVING COUNT(h) >= ALL(" +
                            "    SELECT COUNT(h2) FROM HoaDon h2 " +
                            "    JOIN h2.khachHang k2 " +
                            "    WHERE h2.ngayLapHoaDon BETWEEN :ngayBatDau AND :ngayKetThuc " +
                            "    GROUP BY k2)",
                    KhachHang.class
            );
            query.setParameter("ngayBatDau", ngayBatDau.atStartOfDay());
            query.setParameter("ngayKetThuc", ngayKetThuc.atStartOfDay());

            dsKH = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsKH;
    }


    public int getSoLuongBanCuaSanPhamChayNhat(LocalDate ngayBatDau, LocalDate ngayKetThuc) {
        try {
            TypedQuery<Integer> query = em.createQuery(
                    "SELECT SUM(cthd.soLuong) " +
                            "FROM ChiTietHoaDon cthd " +
                            "JOIN cthd.sanPham sp " +
                            "JOIN cthd.hoaDon hd " +
                            "WHERE hd.ngayLapHoaDon BETWEEN :ngayBatDau AND :ngayKetThuc " +
                            "GROUP BY sp " +
                            "ORDER BY SUM(cthd.soLuong) DESC",
                    Integer.class
            );
            query.setParameter("ngayBatDau", ngayBatDau.atStartOfDay());
            query.setParameter("ngayKetThuc", ngayKetThuc.atStartOfDay());
            query.setMaxResults(1); // Limit to 1 result

            Integer soLuongBan = query.getSingleResult();
            return soLuongBan != null ? soLuongBan : 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public double getTongTienCuaKhachHangTop1(LocalDate ngayBatDau, LocalDate ngayKetThuc) {
        try {
            TypedQuery<Double> query = em.createQuery(
                    "SELECT SUM(cthd.soLuong * cthd.donGia) " +
                            "FROM ChiTietHoaDon cthd " +
                            "JOIN cthd.hoaDon hd " +
                            "JOIN hd.khachHang kh " +
                            "WHERE hd.ngayLapHoaDon BETWEEN :ngayBatDau AND :ngayKetThuc " +
                            "GROUP BY kh " +
                            "ORDER BY SUM(cthd.soLuong * cthd.donGia) DESC",
                    Double.class
            );
            query.setParameter("ngayBatDau", ngayBatDau.atStartOfDay());
            query.setParameter("ngayKetThuc", ngayKetThuc.atStartOfDay());
            query.setMaxResults(1); // Limit to 1 result

            Double tongTien = query.getSingleResult();
            return tongTien != null ? tongTien : 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


    public int getSoLuongHoaDonCuaKhachHangTheoMa(LocalDate ngayBatDau, LocalDate ngayKetThuc, String maKH) {
        try {
            TypedQuery<Long> query = em.createQuery(
                    "SELECT COUNT(hd) " +
                            "FROM HoaDon hd " +
                            "WHERE hd.ngayLapHoaDon BETWEEN :ngayBatDau AND :ngayKetThuc " +
                            "AND hd.khachHang.maKhachHang = :maKH",
                    Long.class
            );
            query.setParameter("ngayBatDau", ngayBatDau.atStartOfDay());
            query.setParameter("ngayKetThuc", ngayKetThuc.atStartOfDay());
            query.setParameter("maKH", maKH);

            Long soLuong = query.getSingleResult();
            return soLuong != null ? soLuong.intValue() : 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public double getTongTienCuaKhachHangTheoMa(LocalDate ngayBatDau, LocalDate ngayKetThuc, String maKH) {
        try {
            TypedQuery<Double> query = em.createQuery(
                    "SELECT SUM(cthd.soLuong * cthd.donGia) " +
                            "FROM ChiTietHoaDon cthd " +
                            "JOIN cthd.hoaDon hd " +
                            "JOIN hd.khachHang kh " +
                            "WHERE hd.ngayLapHoaDon BETWEEN :ngayBatDau AND :ngayKetThuc " +
                            "AND kh.maKhachHang = :maKH",
                    Double.class
            );
            query.setParameter("ngayBatDau", ngayBatDau.atStartOfDay());
            query.setParameter("ngayKetThuc", ngayKetThuc.atStartOfDay());
            query.setParameter("maKH", maKH);

            Double tongTien = query.getSingleResult();
            return tongTien != null ? tongTien : 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


    public double getDoanhThuTheoMaNhanVien(LocalDate ngayBatDau, LocalDate ngayKetThuc, String maNV) {
        try {
            TypedQuery<Double> query = em.createQuery(
                    "SELECT SUM(cthd.soLuong * cthd.donGia) " +
                            "FROM ChiTietHoaDon cthd " +
                            "JOIN cthd.hoaDon hd " +
                            "WHERE hd.ngayLapHoaDon BETWEEN :ngayBatDau AND :ngayKetThuc " +
                            "AND hd.nhanVien.maNhanVien = :maNV",
                    Double.class
            );
            query.setParameter("ngayBatDau", ngayBatDau.atStartOfDay());
            query.setParameter("ngayKetThuc", ngayKetThuc.atStartOfDay());
            query.setParameter("maNV", maNV);

            Double tongTien = query.getSingleResult();
            return tongTien != null ? tongTien : 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSoLuongHoaDonTheoMaNV(LocalDate ngayBatDau, LocalDate ngayKetThuc, String maNV) {
        try {
            TypedQuery<Long> query = em.createQuery(
                    "SELECT COUNT(hd) " +
                            "FROM HoaDon hd " +
                            "WHERE hd.ngayLapHoaDon BETWEEN :ngayBatDau AND :ngayKetThuc " +
                            "AND hd.nhanVien.maNhanVien = :maNV",
                    Long.class
            );
            query.setParameter("ngayBatDau", ngayBatDau.atStartOfDay());
            query.setParameter("ngayKetThuc", ngayKetThuc.atStartOfDay());
            query.setParameter("maNV", maNV);

            Long soLuong = query.getSingleResult();
            return soLuong != null ? soLuong.intValue() : 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


    public List<NhanVien> getDoanhThuCuaNhanVien(LocalDate ngayBatDau, LocalDate ngayKetThuc) {
        try {
            TypedQuery<NhanVien> query = em.createQuery(
                    "SELECT DISTINCT hd.nhanVien " +
                            "FROM HoaDon hd " +
                            "WHERE hd.ngayLapHoaDon BETWEEN :ngayBatDau AND :ngayKetThuc",
                    NhanVien.class
            );
            query.setParameter("ngayBatDau", ngayBatDau.atStartOfDay());
            query.setParameter("ngayKetThuc", ngayKetThuc.atStartOfDay());

            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<NhanVien> thongKeDoanhThu10NVBanNhieuNhat(LocalDate ngayBatDau, LocalDate ngayKetThuc) {
        try {
            TypedQuery<NhanVien> query = em.createQuery(
                    "SELECT hd.nhanVien, SUM(cthd.donGia * cthd.soLuong) AS TongDoanhThu " +
                            "FROM HoaDon hd " +
                            "JOIN hd.chiTietHoaDons cthd " +
                            "WHERE hd.ngayLapHoaDon BETWEEN :ngayBatDau AND :ngayKetThuc " +
                            "GROUP BY hd.nhanVien " +
                            "ORDER BY TongDoanhThu DESC",
                    NhanVien.class
            );
            query.setParameter("ngayBatDau", ngayBatDau.atStartOfDay());
            query.setParameter("ngayKetThuc", ngayKetThuc.atStartOfDay());
            query.setMaxResults(10);

            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


    public List<KhachHang> getTop10KHThanThiet(LocalDate ngayBatDau, LocalDate ngayKetThuc) {
        try {
            TypedQuery<Object[]> query = em.createQuery(
                    "SELECT hd.khachHang.maKhachHang, SUM(cthd.donGia * cthd.soLuong) AS TongDoanhThu " +
                            "FROM HoaDon hd " +
                            "JOIN hd.chiTietHoaDons cthd " +
                            "WHERE hd.ngayLapHoaDon BETWEEN :ngayBatDau AND :ngayKetThuc " +
                            "GROUP BY hd.khachHang.maKhachHang " +
                            "ORDER BY TongDoanhThu DESC",
                    Object[].class
            );
            query.setParameter("ngayBatDau", ngayBatDau.atStartOfDay());
            query.setParameter("ngayKetThuc", ngayKetThuc.atStartOfDay());
            query.setMaxResults(10);

            List<Object[]> results = query.getResultList();
            List<KhachHang> dsKH = new ArrayList<>();
            for (Object[] result : results) {
                String maKH = (String) result[0];
                KhachHang khachHang = new KhachHang(maKH);
                dsKH.add(khachHang);
            }
            return dsKH;

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
