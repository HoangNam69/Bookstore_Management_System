/*
 * @ (#) HoaDonDaoTest.java       21/4/24
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package test;

import dao.HoaDonDao;
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
        int result = hoaDonDaoImpl.setNullChoMaNhanVienTrongHoaDon("someMaNV");
        assertEquals(1, result);
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
    void testThemHoaDon() throws SQLException {
        HoaDon hoaDon = new HoaDon();
        hoaDon.setMaHoaDon("HD1300001");
        hoaDon.setGhiChu("someGhiChu");
        hoaDon.setNgayLapHoaDon(LocalDate.now());
        hoaDon.setTinhTrang(true);
        hoaDon.setTienKhachDua(100000);
        // Lấy mã Khách hàng đã có trong cơ sở dữ liệu

        hoaDon.setKhachHang( new KhachHang("KH000001"));
        hoaDon.setNhanVien(new NhanVien("QL000003"));
        int result = hoaDonDaoImpl.themHoaDon(hoaDon);
        assertEquals(1, result);
        System.out.println(hoaDon);
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
    }

    @Test
    void testTimHoaDonTheoSDT() {
        List<HoaDon> hoaDonList = hoaDonDaoImpl.timHoaDonTheoSDT("0337098734");
        assertNotNull(hoaDonList);
        assertFalse(hoaDonList.isEmpty());
    }

    @Test
    void testTimHoaDonTheoTenKH() {
        List<HoaDon> hoaDonList = hoaDonDaoImpl.timHoaDonTheoTenKH("Nguyễn Van An");
        assertNotNull(hoaDonList);
        assertFalse(hoaDonList.isEmpty());
    }

    @AfterAll
    public void tearDown() {
        hoaDonDaoImpl = null;
    }
}