/*
 * @ (#) HoaDonDoiTraDaoTest.java       21/4/24
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package test;

import dao.HoaDonDao;
import dao.HoaDonDoiTraDao;
import dao.KhachHangDao;
import dao.NhanVienDao;
import entities.HoaDon;
import entities.HoaDonDoiTra;
import entities.KhachHang;
import entities.NhanVien;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

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
public class HoaDonDoiTraDaoTest {
    private static HoaDonDoiTraDao hoaDonDoiTraDao;
    private KhachHangDao khachHangDao = new KhachHangDao();
    private NhanVienDao nhanVienDao = new NhanVienDao();
    private HoaDonDao hoaDonDao = new HoaDonDao();
    @BeforeAll
    public void setUp() {
        hoaDonDoiTraDao = new HoaDonDoiTraDao();
    }

    @Test
    public void testThemHoaDonDoiTra() throws SQLException {
        // Tạo một đối tượng HoaDonDoiTra mới
        HoaDonDoiTra hoaDonDoiTra = new HoaDonDoiTra();
        hoaDonDoiTra.setMaHoaDonDoiTra("HDDT0003");
        hoaDonDoiTra.setGhiChu("Test GhiChu");
        hoaDonDoiTra.setNgayLapHoaDon(LocalDate.now());
        hoaDonDoiTra.setTienKhachDua(100000);
        hoaDonDoiTra.setTienPhaiTru(50000);

        // Tìm HoaDon, KhachHang và NhanVien theo mã từ cơ sở dữ liệu
        HoaDon hoaDon = hoaDonDao.timHoaDonTheoMa("HD1500005");
        KhachHang khachHang = khachHangDao.timKhachHangTheoMa("KH110001");
        NhanVien nhanVien = nhanVienDao.timNhanVienTheoMa("QL000003");

        // Thiết lập HoaDon, KhachHang và NhanVien cho HoaDonDoiTra
        hoaDonDoiTra.setHoaDon(hoaDon);
        hoaDonDoiTra.setKhachHang(khachHang);
        hoaDonDoiTra.setNhanVien(nhanVien);

        // Gọi phương thức themHoaDonDoiTra
        int result = hoaDonDoiTraDao.themHoaDonDoiTra(hoaDonDoiTra);

        // Kiểm tra xem liệu HoaDonDoiTra có được thêm vào cơ sở dữ liệu thành công hay không
        assertEquals(1, result);

        // In ra thông báo và thông tin HoaDonDoiTra nếu thêm thành công
        if (result == 1) {
            System.out.println("Thêm thành công");
            System.out.println(hoaDonDoiTra);
        }
    }

    @Test
    public void testGetHoaDonDoiTraTheoMa() {
        List<HoaDonDoiTra> result = hoaDonDoiTraDao.getHoaDonDoiTraTheoMa("HDDT0001");
        assertNotNull(result);
        if (!result.isEmpty()) {
            result.forEach(System.out::println);
        }
    }

    @Test
    public void testGetDSHoaDonDoiTra() {
        List<HoaDonDoiTra> result = hoaDonDoiTraDao.getDSHoaDonDoiTra();
        assertNotNull(result);
        if (!result.isEmpty()) {
            result.forEach(System.out::println);
        }
    }

    @Test
    public void testEditTienKhachTra() {
        // Assume we are working with a known HoaDonDoiTra
        List<HoaDonDoiTra> hddtList = hoaDonDoiTraDao.getHoaDonDoiTraTheoMa("HDDT0001");
        assertNotNull(hddtList);
        assertFalse(hddtList.isEmpty());
        HoaDonDoiTra hddt = hddtList.get(0);

        // Change the tienKhachDua property
        double oldTienKhachDua = hddt.getTienKhachDua();
        double newTienKhachDua = oldTienKhachDua + 10000;
        hddt.setTienKhachDua(newTienKhachDua);

        // Call the method under test
        int result = hoaDonDoiTraDao.editTienKhachTra(hddt);
        assertEquals(1, result);

        // Retrieve the updated HoaDonDoiTra object from the database
        List<HoaDonDoiTra> updatedHddtList = hoaDonDoiTraDao.getHoaDonDoiTraTheoMa("HDDT0001");
        assertNotNull(updatedHddtList);
        assertFalse(updatedHddtList.isEmpty());
        HoaDonDoiTra updatedHddt = updatedHddtList.get(0);

        // Print the updated HoaDonDoiTra
        System.out.println(updatedHddt);
    }

    @Test
    public void testEditTienPhaiTru() {
        // Assume we are working with a known HoaDonDoiTra
        List<HoaDonDoiTra> hddtList = hoaDonDoiTraDao.getHoaDonDoiTraTheoMa("HDDT0001");
        assertNotNull(hddtList);
        assertFalse(hddtList.isEmpty());
        HoaDonDoiTra hddt = hddtList.get(0);

        // Change the tienPhaiTru property
        double oldTienPhaiTru = hddt.getTienPhaiTru();
        double newTienPhaiTru = oldTienPhaiTru + 10000;
        hddt.setTienPhaiTru(newTienPhaiTru);

        // Call the method under test
        int result = hoaDonDoiTraDao.editTienPhaiTru(hddt);
        assertEquals(1, result);

        // Retrieve the updated HoaDonDoiTra object from the database
        List<HoaDonDoiTra> updatedHddtList = hoaDonDoiTraDao.getHoaDonDoiTraTheoMa("HDDT0001");
        assertNotNull(updatedHddtList);
        assertFalse(updatedHddtList.isEmpty());
        HoaDonDoiTra updatedHddt = updatedHddtList.get(0);

        // Check if the tienPhaiTru property was updated correctly
        assertEquals(newTienPhaiTru, updatedHddt.getTienPhaiTru());
    }

    @Test
    public void testGetMaHoaDonDoiTraByMaHDCu() {
        List<HoaDonDoiTra> result = hoaDonDoiTraDao.getMaHoaDonDoiTraByMaHDCu("HD1500005");
        assertNotNull(result);

        // Nếu tìm thấy, in ra HoaDonDoiTra
        if (!result.isEmpty()) {
            result.forEach(System.out::println);
        }
    }

    @Test
    public void testGetToanBoDSHoaDonDoiTra() {
        List<HoaDonDoiTra> result = hoaDonDoiTraDao.getToanBoDSHoaDonDoiTra();
        assertNotNull(result);

        // Nếu tìm thấy, in ra danh sách HoaDonDoiTra
        if (!result.isEmpty()) {
            result.forEach(System.out::println);
        }
    }

    @Test
    public void testTimHoaDonDoiTraTheoMa() {
        HoaDonDoiTra result = hoaDonDoiTraDao.timHoaDonDoiTraTheoMa("HDDT0003");
        assertNotNull(result);

        // If the result is not null, print the HoaDonDoiTra
        if (result != null) {
            System.out.println(result);
        }
    }

    @Test
    public void testGetHoaDonDoiTraTheoTen() {
        List<HoaDonDoiTra> result = hoaDonDoiTraDao.getHoaDonDoiTraTheoTen("Đức");
        assertNotNull(result);

        // Nếu tìm thấy, in ra HoaDonDoiTra
        if (!result.isEmpty()) {
            System.out.println("Đã tìm thấy hóa đơn đổi trả:");
            result.forEach(System.out::println);
        } else {
            System.out.println("Không tìm thấy hóa đơn đổi trả nào.");
        }
    }

    @Test
    public void testGetHoaDonDoiTraTheoSDT() {
        List<HoaDonDoiTra> result = hoaDonDoiTraDao.getHoaDonDoiTraTheoSDT("0337098734");
        assertNotNull(result);

        // Nếu tìm thấy, in ra HoaDonDoiTra
        if (!result.isEmpty()) {
            result.forEach(System.out::println);
        }
    }

    @Test
    public void testGetHoaDonDoiTraTheoTenKH() {
        List<HoaDonDoiTra> result = hoaDonDoiTraDao.getHoaDonDoiTraTheoTenKH("Nguyen Van A");
        assertNotNull(result);

        // Nếu tìm thấy, in ra HoaDonDoiTra
        if (!result.isEmpty()) {
            result.forEach(System.out::println);
        }
    }

    @AfterAll
    public void tearDown() {
        hoaDonDoiTraDao = null;
    }
}