/*
 * @ (#) HoaDonDaoTest.java       21/4/24
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package test;

import dao.HoaDonDao;
import dao.KhachHangDao;
import dao.NhanVienDao;
import entities.HoaDon;
import entities.KhachHang;
import entities.NhanVien;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import service.impl.HoaDonServiceImpl;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
 * @description: This class is used for testing
 * @author: Nguyen Hong Duc
 * @date:   21/4/24
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HoaDonDaoTest {
   private static HoaDonServiceImpl hoaDonDaoImpl;

    @BeforeAll
    public void setUp() {
        hoaDonDaoImpl = new HoaDonServiceImpl();
    }

    @Test
    void testSetNullChoMaNhanVienTrongHoaDon() {
        int result = hoaDonDaoImpl.setNullChoMaNhanVienTrongHoaDon("NV000002");
        assertEquals(1, result);

        // In ra thông báo nếu set Null thành công
        if (result == 1) {
            System.out.println("Set Null thành công");
        }
    }

    @Test
    void testGetDSHoaDon() {
        List<HoaDon> hoaDonList = null;
        try {
            hoaDonList = hoaDonDaoImpl.getDSHoaDon();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (hoaDonList != null && !hoaDonList.isEmpty()) {
            hoaDonList.forEach(System.out::println);
        } else {
            System.out.println("Danh sách HoaDon là null");
        }
    }

    @Test
    void testDoiThongTinHoaDonSauKhiXoa() {
        int result = hoaDonDaoImpl.doiThongTinHoaDonSauKhiXoa("someTenNV");
        assertEquals(1, result);
    }

    @Test
    void testThemHoaDon() {
        try {
            // Tạo một đối tượng HoaDon mới
            HoaDon hoaDon = new HoaDon();
            hoaDon.setMaHoaDon("HD1500005");
            hoaDon.setGhiChu("Test GhiChu");
            hoaDon.setNgayLapHoaDon(LocalDate.now());
            hoaDon.setTinhTrang(true);
            hoaDon.setTienKhachDua(100000);

            // Lấy đối tượng KhachHang từ cơ sở dữ liệu
            KhachHangDao khachHangDao = new KhachHangDao();
            KhachHang khachHang = khachHangDao.timKhachHangTheoMa("KH110001");

            // Kiểm tra xem liệu KhachHang có tồn tại hay không
            assertNotNull(khachHang);

            // Lấy đối tượng NhanVien từ cơ sở dữ liệu
            NhanVienDao nhanVienDao = new NhanVienDao();
            NhanVien nhanVien = nhanVienDao.timNhanVienTheoMa("QL000003");

            // Kiểm tra xem liệu NhanVien có tồn tại hay không
            assertNotNull(nhanVien);

            // Gán KhachHang và NhanVien cho HoaDon
            hoaDon.setKhachHang(khachHang);
            hoaDon.setNhanVien(nhanVien);

            // Gọi phương thức themHoaDon
            int result = hoaDonDaoImpl.themHoaDon(hoaDon);

            // Kiểm tra xem liệu HoaDon có được thêm vào cơ sở dữ liệu thành công hay không
            assertEquals(1, result);

            // In ra hóa đơn nếu thêm thành công
            if (result == 1) {
                System.out.println("Hóa đơn đã được thêm thành công: " + hoaDon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetHoaDonTheoMa() {
        List<HoaDon> hoaDonList = hoaDonDaoImpl.getHoaDonTheoMa("HD1300001");
        assertNotNull(hoaDonList);
        assertFalse(hoaDonList.isEmpty());
    }

    @Test
    void testGetHoaDonThuong() {
        List<HoaDon> hoaDonList = hoaDonDaoImpl.getHoaDonThuong();
        assertNotNull(hoaDonList);
        assertFalse(hoaDonList.isEmpty());
    }

    @Test
    void testTimHoaDonTheoMa() {
        HoaDon hoaDon = hoaDonDaoImpl.timHoaDonTheoMa("HD1300001");
        assertNotNull(hoaDon);
    }

    @Test
    void testGetHoaDonTheoTen() {
        List<HoaDon> hoaDonList = hoaDonDaoImpl.getHoaDonTheoTen("Đức");
        assertNotNull(hoaDonList);
        assertFalse(hoaDonList.isEmpty());

        // In ra HoaDon nếu tìm thấy
        if (!hoaDonList.isEmpty()) {
            hoaDonList.forEach(System.out::println);
        }
    }

    @Test
    void testTimHoaDonTheoSDT() {
        List<HoaDon> hoaDonList = hoaDonDaoImpl.timHoaDonTheoSDT("0337098734");
        assertNotNull(hoaDonList);
        assertFalse(hoaDonList.isEmpty());

        // In ra danh sách HoaDon nếu tìm thấy
        if (!hoaDonList.isEmpty()) {
            hoaDonList.forEach(System.out::println);
        }
    }

    @Test
    void testTimHoaDonTheoTenKH() {
        List<HoaDon> hoaDonList = hoaDonDaoImpl.timHoaDonTheoTenKH("Nguyễn Van An");
        assertNotNull(hoaDonList);
        assertFalse(hoaDonList.isEmpty());

        // In ra HoaDon nếu tìm thấy
        if (!hoaDonList.isEmpty()) {
            hoaDonList.forEach(System.out::println);
        }
    }

    @AfterAll
    public void tearDown() {
        hoaDonDaoImpl = null;
    }
}