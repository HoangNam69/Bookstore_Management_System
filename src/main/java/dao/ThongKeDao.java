package dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entities.*;


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

    public ThongKeDao() {
        DBConnection connection = DBConnection.getInstance();
        con = connection.getConnection();
    }

    public List<NhanVien> getNhanVienBanNhieuNhatTheoNgayTuChon(LocalDate ngayBatDau, LocalDate ngayKetThuc) {
        List<NhanVien> dsNV = new ArrayList<>();
        try {
            String query = "SELECT NhanVien.maNhanVien " +
                    "FROM ChiTietHoaDon " +
                    "INNER JOIN HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon " +
                    "INNER JOIN NhanVien ON HoaDon.maNhanVien = NhanVien.maNhanVien " +
                    "WHERE HoaDon.ngayLapHoaDon BETWEEN ? AND ? " +
                    "GROUP BY NhanVien.maNhanVien " +
                    "HAVING COUNT(HoaDon.maHoaDon) >= ALL " +
                    "(SELECT COUNT(HoaDon.maHoaDon) AS 'Tong so luong hoa don' " +
                    " FROM ChiTietHoaDon " +
                    " INNER JOIN HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon " +
                    " INNER JOIN NhanVien ON HoaDon.maNhanVien = NhanVien.maNhanVien " +
                    " WHERE HoaDon.ngayLapHoaDon BETWEEN ? AND ? " +
                    " GROUP BY NhanVien.maNhanVien)";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setDate(1, Date.valueOf(ngayBatDau));
            ps.setDate(2, Date.valueOf(ngayKetThuc));
            ps.setDate(3, Date.valueOf(ngayBatDau));
            ps.setDate(4, Date.valueOf(ngayKetThuc));

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String maNV = rs.getString(1);
                NhanVien nhanVien = new NhanVien(maNV);
                dsNV.add(nhanVien);
            }
            return dsNV;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    //    public List<SanPham> getSanPhamBanNhieuNhatTheoNgayTuChon(LocalDate ngayBatDau, LocalDate ngayKetThuc) {
//        SanPham sp = null;
//        dsSP = new ArrayList<SanPham>();
//        try {
//            String query = "SELECT SanPham.maSanPham\r\n" + "FROM     ChiTietHoaDon INNER JOIN\r\n"
//                    + "HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon INNER JOIN\r\n"
//                    + "					                SanPham ON ChiTietHoaDon.maSanPham = SanPham.maSanPham\r\n"
//                    + "					WHERE  HoaDon.ngayLapHoaDon BETWEEN  ? and ?\r\n"
//                    + "					GROUP BY SanPham.maSanPham\r\n"
//                    + "					HAVING SUM(ChiTietHoaDon.soLuong) >= ALL(SELECT  SUM(ChiTietHoaDon.soLuong) AS 'TongSoLuongDaBan'\r\n"
//                    + "					FROM     ChiTietHoaDon INNER JOIN\r\n"
//                    + "			        HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon INNER JOIN\r\n"
//                    + "					                SanPham ON ChiTietHoaDon.maSanPham = SanPham.maSanPham\r\n"
//                    + "					WHERE  HoaDon.ngayLapHoaDon BETWEEN   ? AND ?\r\n"
//                    + "					GROUP BY SanPham.maSanPham)";
//
//            ps = con.prepareStatement(query);
//            int dayBD = ngayBatDau.getDayOfMonth();
//            int monthBD = ngayBatDau.getMonthValue();
//            int yearBD = ngayBatDau.getYear();
//
//            ps.setString(1, yearBD + "-" + monthBD + "-" + dayBD);
//
//            int dayKT = ngayKetThuc.getDayOfMonth();
//            int monthKT = ngayKetThuc.getMonthValue();
//            int yearKT = ngayKetThuc.getYear();
//
//            ps.setString(2, yearKT + "-" + monthKT + "-" + dayKT);
//            ps.setString(3, yearBD + "-" + monthBD + "-" + dayBD);
//            ps.setString(4, yearKT + "-" + monthKT + "-" + dayKT);
//
//            rs = ps.executeQuery();
//
//            while (rs.next()) {
//                String maSanPham = rs.getString("maSanPham");
//
//
//                SanPhamDao sanPhamDao = new SanPhamDao();
//                sp = sanPhamDao.timSanPhamTheoMa(maSanPham);
//                System.out.println(sp);
//
//
//
//                if (sp.getLoaiSanPham().equals("Sách")) {
//                    sp = (Sach) sanPhamDao.timSanPhamTheoMa(maSanPham);
//                } else {
//                    sp = (VanPhongPham) sanPhamDao.timSanPhamTheoMa(maSanPham);
//                }
//                dsSP.add(sp);
//            }
//            return dsSP;
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
    public List<SanPham> getSanPhamBanNhieuNhatTheoNgayTuChon(LocalDate ngayBatDau, LocalDate ngayKetThuc) {
        List<SanPham> dsSP = new ArrayList<>();
        try {
            String query = "SELECT SanPham.maSanPham " +
                    "FROM ChiTietHoaDon " +
                    "INNER JOIN HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon " +
                    "INNER JOIN SanPham ON ChiTietHoaDon.maSanPham = SanPham.maSanPham " +
                    "WHERE HoaDon.ngayLapHoaDon BETWEEN ? AND ? " +
                    "GROUP BY SanPham.maSanPham " +
                    "HAVING SUM(ChiTietHoaDon.soLuong) >= ALL " +
                    "(SELECT SUM(ChiTietHoaDon.soLuong) AS 'TongSoLuongDaBan' " +
                    "FROM ChiTietHoaDon " +
                    "INNER JOIN HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon " +
                    "INNER JOIN SanPham ON ChiTietHoaDon.maSanPham = SanPham.maSanPham " +
                    "WHERE HoaDon.ngayLapHoaDon BETWEEN ? AND ? " +
                    "GROUP BY SanPham.maSanPham)";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setDate(1, Date.valueOf(ngayBatDau));
            ps.setDate(2, Date.valueOf(ngayKetThuc));
            ps.setDate(3, Date.valueOf(ngayBatDau));
            ps.setDate(4, Date.valueOf(ngayKetThuc));

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String maSanPham = rs.getString("maSanPham");
                SanPhamDao sanPhamDao = new SanPhamDao();
                SanPham sp = sanPhamDao.timSanPhamTheoMa(maSanPham);

                if (sp != null) {
                    if (sp.getLoaiSanPham().equals("Sách")) {
                        sp = (Sach) sp;
                    } else {
                        sp = (VanPhongPham) sp;
                    }
                    dsSP.add(sp);
                }
            }
            return dsSP;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public int getSoLuongSachTon() throws SQLException {
        int soLuongTon = 0;
        String query = "SELECT SUM(soLuongTon) AS total FROM SanPham WHERE loaiSanPham LIKE N'Sách'";
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();
        while (rs.next()) {
            soLuongTon = rs.getInt("total");
            return soLuongTon;
        }
        return 0;
    }


    public int getSoLuongVPPTon() throws SQLException {
        int soLuongTon = 0;
        String query = "SELECT SUM(soLuongTon) AS total FROM SanPham WHERE loaiSanPham LIKE N'Văn phòng phẩm'";
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();
        while (rs.next()) {
            soLuongTon = rs.getInt("total");
            return soLuongTon;
        }
        return 0;
    }


    public int getSoLuongSachLoi() throws SQLException {
        int soLuongLoi = 0;
        String query = "SELECT SUM(soLuongLoi) AS total FROM SachLoi";
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();
        while (rs.next()) {
            soLuongLoi = rs.getInt("total");
            return soLuongLoi;
        }
        return 0;
    }


    //    public int getSoLuongHoaDon(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws SQLException {
//        int soLuongHoaDon = 0;
//        String query = "SELECT COUNT(*)\r\n" + "from HoaDon\r\n" + "WHERE  HoaDon.ngayLapHoaDon between ? and ?";
//        ps = con.prepareStatement(query);
//
//        int dayBD = ngayBatDau.getDayOfMonth();
//        int monthBD = ngayBatDau.getMonthValue();
//        int yearBD = ngayBatDau.getYear();
//
//        ps.setString(1, yearBD + "-" + monthBD + "-" + dayBD);
//
//        int dayKT = ngayKetThuc.getDayOfMonth();
//        int monthKT = ngayKetThuc.getMonthValue();
//        int yearKT = ngayKetThuc.getYear();
//
//        ps.setString(2, yearKT + "-" + monthKT + "-" + dayKT);
//        rs = ps.executeQuery();
//        while (rs.next()) {
//            soLuongHoaDon = rs.getInt("");
//            return soLuongHoaDon;
//        }
//        return 0;
//    }
    public int getSoLuongHoaDon(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws SQLException {
        int soLuongHoaDon = 0;
        String query = "SELECT COUNT(*) AS total " +
                "FROM HoaDon " +
                "WHERE HoaDon.ngayLapHoaDon BETWEEN ? AND ?";
        ps = con.prepareStatement(query);

        ps.setString(1, ngayBatDau.toString());
        ps.setString(2, ngayKetThuc.toString());

        rs = ps.executeQuery();
        while (rs.next()) {
            soLuongHoaDon = rs.getInt("total");
            return soLuongHoaDon;
        }
        return 0;
    }

    //    public double getDoanhThu(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws SQLException {
//        double doanhThu = 0;
//        String query = "SELECT SUM(ChiTietHoaDon.soLuong*ChiTietHoaDon.donGia)\r\n"
//                + "	from ChiTietHoaDon   INNER JOIN\r\n" + "	HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon\r\n"
//                + "WHERE  HoaDon.ngayLapHoaDon between  ? and ?";
//        ps = con.prepareStatement(query);
//
//        int dayBD = ngayBatDau.getDayOfMonth();
//        int monthBD = ngayBatDau.getMonthValue();
//        int yearBD = ngayBatDau.getYear();
//
//        ps.setString(1, yearBD + "-" + monthBD + "-" + dayBD);
//
//        int dayKT = ngayKetThuc.getDayOfMonth();
//        int monthKT = ngayKetThuc.getMonthValue();
//        int yearKT = ngayKetThuc.getYear();
//
//        ps.setString(2, yearKT + "-" + monthKT + "-" + dayKT);
//        rs = ps.executeQuery();
//        while (rs.next()) {
//            doanhThu = rs.getDouble("");
//            return doanhThu;
//        }
//        return 0;
//    }
    public double getDoanhThu(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws SQLException {
        double doanhThu = 0;
        String query = "SELECT SUM(ChiTietHoaDon.soLuong * ChiTietHoaDon.donGia) AS total " +
                "FROM ChiTietHoaDon INNER JOIN HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon " +
                "WHERE HoaDon.ngayLapHoaDon BETWEEN ? AND ?";
        ps = con.prepareStatement(query);

        ps.setString(1, ngayBatDau.toString());
        ps.setString(2, ngayKetThuc.toString());

        rs = ps.executeQuery();
        while (rs.next()) {
            doanhThu = rs.getDouble("total");
            return doanhThu;
        }
        return 0;
    }


    public List<KhachHang> getKhachHangMuaNhieuNhatTheoNgayTuChon(LocalDate ngayBatDau, LocalDate ngayKetThuc) {
        dsKH = new ArrayList<KhachHang>();
        try {
            String query = "SELECT KhachHang.maKhachHang " +
                    "FROM ChiTietHoaDon " +
                    "INNER JOIN HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon " +
                    "INNER JOIN KhachHang ON HoaDon.maKhachHang = KhachHang.maKhachHang " +
                    "WHERE HoaDon.ngayLapHoaDon BETWEEN ? AND ? " +
                    "GROUP BY KhachHang.maKhachHang " +
                    "HAVING COUNT(HoaDon.maHoaDon) >= ALL(SELECT COUNT(HoaDon.maHoaDon) " +
                    "FROM chitiethoadon " +
                    "INNER JOIN HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon " +
                    "INNER JOIN KhachHang ON HoaDon.maKhachHang = KhachHang.maKhachHang " +
                    "WHERE HoaDon.ngayLapHoaDon BETWEEN ? AND ? " +
                    "GROUP BY KhachHang.maKhachHang)";

            ps = con.prepareStatement(query);
            int dayBD = ngayBatDau.getDayOfMonth();
            int monthBD = ngayBatDau.getMonthValue();
            int yearBD = ngayBatDau.getYear();

            ps.setString(1, yearBD + "-" + monthBD + "-" + dayBD);

            int dayKT = ngayKetThuc.getDayOfMonth();
            int monthKT = ngayKetThuc.getMonthValue();
            int yearKT = ngayKetThuc.getYear();

            ps.setString(2, yearKT + "-" + monthKT + "-" + dayKT);
            ps.setString(3, yearBD + "-" + monthBD + "-" + dayBD);
            ps.setString(4, yearKT + "-" + monthKT + "-" + dayKT);

            rs = ps.executeQuery();

            while (rs.next()) {
                String maKH = rs.getString(1);
                KhachHang khachHang = new KhachHang(maKH);
                dsKH.add(khachHang);
            }
            return dsKH;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public int getSoLuongBanCuaSanPhamChayNhat(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws SQLException {
        int soLuongBan = 0;
        String query = "SELECT SUM(ChiTietHoaDon.soLuong) AS TopSoLuong " +
                "FROM SanPham " +
                "INNER JOIN ChiTietHoaDon ON SanPham.maSanPham = ChiTietHoaDon.maSanPham " +
                "INNER JOIN HoaDon ON HoaDon.maHoaDon = ChiTietHoaDon.maHoaDon " +
                "WHERE HoaDon.ngayLapHoaDon BETWEEN ? AND ? " +
                "GROUP BY SanPham.maSanPham " +
                "ORDER BY TopSoLuong DESC " +
                "LIMIT 1";

        ps = con.prepareStatement(query);
        int dayBD = ngayBatDau.getDayOfMonth();
        int monthBD = ngayBatDau.getMonthValue();
        int yearBD = ngayBatDau.getYear();

        ps.setString(1, yearBD + "-" + monthBD + "-" + dayBD);

        int dayKT = ngayKetThuc.getDayOfMonth();
        int monthKT = ngayKetThuc.getMonthValue();
        int yearKT = ngayKetThuc.getYear();

        ps.setString(2, yearKT + "-" + monthKT + "-" + dayKT);

        rs = ps.executeQuery();
        while (rs.next()) {
            soLuongBan = rs.getInt("TopSoLuong");
            return soLuongBan;
        }
        return 0;
    }


    public double getTongTienCuaKhachHangTop1(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws SQLException {
        double tongTien = 0;
        String query = "SELECT SUM(ChiTietHoaDon.soLuong * ChiTietHoaDon.donGia) " +
                "FROM ChiTietHoaDon " +
                "INNER JOIN HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon " +
                "INNER JOIN KhachHang ON HoaDon.maKhachHang = KhachHang.maKhachHang " +
                "WHERE HoaDon.ngayLapHoaDon BETWEEN ? AND ? " +
                "GROUP BY KhachHang.maKhachHang " +
                "ORDER BY SUM(ChiTietHoaDon.soLuong * ChiTietHoaDon.donGia) DESC " +
                "LIMIT 1";

        ps = con.prepareStatement(query);
        int dayBD = ngayBatDau.getDayOfMonth();
        int monthBD = ngayBatDau.getMonthValue();
        int yearBD = ngayBatDau.getYear();

        ps.setString(1, yearBD + "-" + monthBD + "-" + dayBD);

        int dayKT = ngayKetThuc.getDayOfMonth();
        int monthKT = ngayKetThuc.getMonthValue();
        int yearKT = ngayKetThuc.getYear();

        ps.setString(2, yearKT + "-" + monthKT + "-" + dayKT);

        rs = ps.executeQuery();
        while (rs.next()) {
            tongTien = rs.getDouble(1); // Lấy giá trị từ cột đầu tiên
            return tongTien;
        }
        return 0;
    }


    public int getSoLuongHoaDonCuaKhachHangTheoMa(LocalDate ngayBatDau, LocalDate ngayKetThuc, String maKH) throws SQLException {
        int soLuong = 0;
        String query = "SELECT COUNT(HoaDon.maHoaDon) " +
                "FROM HoaDon " +
                "INNER JOIN KhachHang ON HoaDon.maKhachHang = KhachHang.maKhachHang " +
                "WHERE HoaDon.ngayLapHoaDon BETWEEN ? AND ? AND KhachHang.maKhachHang = ?";

        ps = con.prepareStatement(query);

        int dayBD = ngayBatDau.getDayOfMonth();
        int monthBD = ngayBatDau.getMonthValue();
        int yearBD = ngayBatDau.getYear();

        ps.setString(1, yearBD + "-" + monthBD + "-" + dayBD);

        int dayKT = ngayKetThuc.getDayOfMonth();
        int monthKT = ngayKetThuc.getMonthValue();
        int yearKT = ngayKetThuc.getYear();

        ps.setString(2, yearKT + "-" + monthKT + "-" + dayKT);
        ps.setString(3, maKH);

        rs = ps.executeQuery();
        while (rs.next()) {
            soLuong = rs.getInt(1); // Lấy giá trị từ cột đầu tiên
            return soLuong;
        }
        return 0;
    }


    public double getTongTienCuaKhachHangTheoMa(LocalDate ngayBatDau, LocalDate ngayKetThuc, String maKH) throws SQLException {
        double tongTien = 0;
        String query = "SELECT SUM(ChiTietHoaDon.soLuong * ChiTietHoaDon.donGia) " +
                "FROM ChiTietHoaDon " +
                "INNER JOIN HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon " +
                "INNER JOIN KhachHang ON HoaDon.maKhachHang = KhachHang.maKhachHang " +
                "WHERE HoaDon.ngayLapHoaDon BETWEEN ? AND ? AND KhachHang.maKhachHang = ?";

        ps = con.prepareStatement(query);

        int dayBD = ngayBatDau.getDayOfMonth();
        int monthBD = ngayBatDau.getMonthValue();
        int yearBD = ngayBatDau.getYear();

        ps.setString(1, yearBD + "-" + monthBD + "-" + dayBD);

        int dayKT = ngayKetThuc.getDayOfMonth();
        int monthKT = ngayKetThuc.getMonthValue();
        int yearKT = ngayKetThuc.getYear();

        ps.setString(2, yearKT + "-" + monthKT + "-" + dayKT);
        ps.setString(3, maKH);

        rs = ps.executeQuery();
        while (rs.next()) {
            tongTien = rs.getDouble(1); // Lấy giá trị từ cột đầu tiên
            return tongTien;
        }
        return 0;
    }


    public double getDoanhThuTheoMaNhanVien(LocalDate ngayBatDau, LocalDate ngayKetThuc, String maNV) throws SQLException {
        double tongTien = 0;
        String query = "SELECT SUM(ChiTietHoaDon.soLuong * ChiTietHoaDon.donGia) " +
                "FROM ChiTietHoaDon " +
                "INNER JOIN HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon " +
                "WHERE HoaDon.ngayLapHoaDon BETWEEN ? AND ? AND HoaDon.maNhanVien = ?";

        ps = con.prepareStatement(query);

        int dayBD = ngayBatDau.getDayOfMonth();
        int monthBD = ngayBatDau.getMonthValue();
        int yearBD = ngayBatDau.getYear();

        ps.setString(1, yearBD + "-" + monthBD + "-" + dayBD);

        int dayKT = ngayKetThuc.getDayOfMonth();
        int monthKT = ngayKetThuc.getMonthValue();
        int yearKT = ngayKetThuc.getYear();

        ps.setString(2, yearKT + "-" + monthKT + "-" + dayKT);
        ps.setString(3, maNV);

        rs = ps.executeQuery();
        while (rs.next()) {
            tongTien = rs.getDouble(1); // Lấy giá trị từ cột đầu tiên
            return tongTien;
        }
        return 0;
    }


    public int getSoLuongHoaDonTheoMaNV(LocalDate ngayBatDau, LocalDate ngayKetThuc, String maNV) throws SQLException {
        int soLuongHoaDon = 0;
        String query = "SELECT COUNT(*) " +
                "FROM ChiTietHoaDon " +
                "INNER JOIN HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon " +
                "WHERE HoaDon.ngayLapHoaDon BETWEEN ? AND ? AND HoaDon.maNhanVien = ?";

        ps = con.prepareStatement(query);

        int dayBD = ngayBatDau.getDayOfMonth();
        int monthBD = ngayBatDau.getMonthValue();
        int yearBD = ngayBatDau.getYear();

        ps.setString(1, yearBD + "-" + monthBD + "-" + dayBD);

        int dayKT = ngayKetThuc.getDayOfMonth();
        int monthKT = ngayKetThuc.getMonthValue();
        int yearKT = ngayKetThuc.getYear();

        ps.setString(2, yearKT + "-" + monthKT + "-" + dayKT);
        ps.setString(3, maNV);

        rs = ps.executeQuery();
        while (rs.next()) {
            soLuongHoaDon = rs.getInt(1); // Lấy giá trị từ cột đầu tiên
            return soLuongHoaDon;
        }
        return 0;
    }


    public List<NhanVien> getDoanhThuCuaNhanVien(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws SQLException {
        dsNV = new ArrayList<NhanVien>();
        try {
            String query = "SELECT DISTINCT NhanVien.maNhanVien " +
                    "FROM NhanVien " +
                    "INNER JOIN HoaDon ON NhanVien.maNhanVien = HoaDon.maNhanVien " +
                    "WHERE HoaDon.ngayLapHoaDon BETWEEN ? AND ?";

            ps = con.prepareStatement(query);

            int dayBD = ngayBatDau.getDayOfMonth();
            int monthBD = ngayBatDau.getMonthValue();
            int yearBD = ngayBatDau.getYear();

            ps.setString(1, yearBD + "-" + monthBD + "-" + dayBD);

            int dayKT = ngayKetThuc.getDayOfMonth();
            int monthKT = ngayKetThuc.getMonthValue();
            int yearKT = ngayKetThuc.getYear();

            ps.setString(2, yearKT + "-" + monthKT + "-" + dayKT);

            rs = ps.executeQuery();
            while (rs.next()) {
                String maNV = rs.getString(1);
                NhanVien nhanVien = new NhanVien(maNV);
                dsNV.add(nhanVien);
            }
            return dsNV;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<NhanVien> thongKeDoanhThu10NVBanNhieuNhat(LocalDate ngayBatDau, LocalDate ngayKetThuc) {
        dsNV1 = new ArrayList<NhanVien>();
        try {
            String query = "SELECT NhanVien.maNhanVien, SUM(ChiTietHoaDon.donGia*ChiTietHoaDon.soLuong) AS TongDoanhThu " +
                    "FROM NhanVien " +
                    "JOIN HoaDon ON NhanVien.maNhanVien = HoaDon.maNhanVien " +
                    "JOIN ChiTietHoaDon ON HoaDon.maHoaDon = ChiTietHoaDon.maHoaDon " +
                    "WHERE HoaDon.ngayLapHoaDon BETWEEN ? AND ? " +
                    "GROUP BY NhanVien.maNhanVien " +
                    "ORDER BY TongDoanhThu DESC " +
                    "LIMIT 10";

            ps = con.prepareStatement(query);

            int dayBD = ngayBatDau.getDayOfMonth();
            int monthBD = ngayBatDau.getMonthValue();
            int yearBD = ngayBatDau.getYear();

            ps.setString(1, yearBD + "-" + monthBD + "-" + dayBD);

            int dayKT = ngayKetThuc.getDayOfMonth();
            int monthKT = ngayKetThuc.getMonthValue();
            int yearKT = ngayKetThuc.getYear();

            ps.setString(2, yearKT + "-" + monthKT + "-" + dayKT);

            rs = ps.executeQuery();

            while (rs.next()) {
                String maNV = rs.getString(1);
                NhanVien nhanVien = new NhanVien(maNV);
                dsNV1.add(nhanVien);
            }
            return dsNV1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    public List<KhachHang> getTop10KHThanThiet(LocalDate ngayBatDau, LocalDate ngayKetThuc) {
        dsKH = new ArrayList<KhachHang>();
        try {
            String query = "SELECT KhachHang.maKhachHang, SUM(ChiTietHoaDon.soLuong*ChiTietHoaDon.donGia) AS TongDoanhThu " +
                    "FROM ChiTietHoaDon " +
                    "INNER JOIN HoaDon ON ChiTietHoaDon.maHoaDon = HoaDon.maHoaDon " +
                    "INNER JOIN KhachHang ON HoaDon.maKhachHang = KhachHang.maKhachHang " +
                    "WHERE HoaDon.ngayLapHoaDon BETWEEN ? AND ? " +
                    "GROUP BY KhachHang.maKhachHang " +
                    "ORDER BY TongDoanhThu DESC " +
                    "LIMIT 10";

            ps = con.prepareStatement(query);

            int dayBD = ngayBatDau.getDayOfMonth();
            int monthBD = ngayBatDau.getMonthValue();
            int yearBD = ngayBatDau.getYear();

            ps.setString(1, yearBD + "-" + monthBD + "-" + dayBD);

            int dayKT = ngayKetThuc.getDayOfMonth();
            int monthKT = ngayKetThuc.getMonthValue();
            int yearKT = ngayKetThuc.getYear();

            ps.setString(2, yearKT + "-" + monthKT + "-" + dayKT);

            rs = ps.executeQuery();

            while (rs.next()) {
                String maKH = rs.getString(1);
                KhachHang khachHang = new KhachHang(maKH);
                dsKH.add(khachHang);
            }
            return dsKH;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
