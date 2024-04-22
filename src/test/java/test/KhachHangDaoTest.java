/*
 * @ (#) KhachHangDaoTest.java       21/4/24
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package test;

import dao.KhachHangDao;
import entities.KhachHang;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/*
 * @description: This class is used for testing
 * @author: Nguyen Hong Duc
 * @date:   21/4/24
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class KhachHangDaoTest {
    private static KhachHangDao khachHangDao;

    @BeforeAll
    public void setUp() {
        khachHangDao = new KhachHangDao();
    }

    @Test
    public void testThemKhachHang() {
        // Create a new KhachHang
        KhachHang kh = new KhachHang();
        kh.setMaKhachHang(KhachHang.auto_ID());
        kh.setHoTenKhachHang("Nguyen Van A");
        kh.setsDT("0123456789");
        kh.setGioiTinh(true);
        kh.setDiaChi("123 Main Street");

        // Add the new KhachHang to the database
        int result = khachHangDao.themKhachHang(kh);

        // Check if the operation was successful
        assertEquals(1, result);

        // Retrieve the KhachHang from the database
        KhachHang retrievedKhachHang = khachHangDao.timKhachHangTheoMa(kh.getMaKhachHang());

        // Check if the KhachHang was added correctly
        assertNotNull(retrievedKhachHang);
        assertEquals(kh.getMaKhachHang(), retrievedKhachHang.getMaKhachHang());
        assertEquals(kh.getDiaChi(), retrievedKhachHang.getDiaChi());
        assertEquals(kh.isGioiTinh(), retrievedKhachHang.isGioiTinh());
        assertEquals(kh.getHoTenKhachHang(), retrievedKhachHang.getHoTenKhachHang());
        assertEquals(kh.getsDT(), retrievedKhachHang.getsDT());

        // Print the KhachHang
        System.out.println(retrievedKhachHang);
    }

    @Test
    public void testGetDSKhachHang() {
        // Call the method
        List<KhachHang> khachHangList = khachHangDao.getDSKhachHang();

        // Check if the list is not null
        assertNotNull(khachHangList);

        // Print the list
        khachHangList.forEach(System.out::println);
    }

    @Test
    public void testTimKhachHangTheoMa() {
        // Define a known KhachHang ID
        String maKhachHang = "KH210021";

        // Call the method
        KhachHang khachHang = khachHangDao.timKhachHangTheoMa(maKhachHang);

        // Check if the KhachHang is not null
        assertNotNull(khachHang);

        // Check if the returned KhachHang has the correct ID
        assertEquals(maKhachHang, khachHang.getMaKhachHang());

        // Print the KhachHang
        System.out.println(khachHang);
    }

    @Test
    public void testTimKhachHangTheoTen() {
        // Define a known KhachHang name
        String tenKH = "Nguyen Van A";

        // Call the method
        ArrayList<KhachHang> khachHangList = khachHangDao.timKhachHangTheoTen(tenKH);

        // Check if the list is not null
        assertNotNull(khachHangList);

        // Print the list
        khachHangList.forEach(System.out::println);
    }

    @Test
    public void testCapNhatKhachHang() {
        // Define a known KhachHang
        KhachHang kh = khachHangDao.timKhachHangTheoMa("KH210021");

        // Update the KhachHang
        kh.setHoTenKhachHang("Nguyen Van C");
        kh.setsDT("0987654321");
        kh.setGioiTinh(false);
        kh.setDiaChi("456 Secondary Street");

        // Call the method
        int result = khachHangDao.capNhatKhachHang(kh);

        // Check if the operation was successful
        assertEquals(1, result);

        // Print the updated KhachHang
        System.out.println(kh);
    }

    @Test
    public void testTimKhachHangTheoSDT() {
        // Define a known KhachHang phone number
        String sDT = "0987654321";

        // Call the method
        ArrayList<KhachHang> khachHangList = khachHangDao.timKhachHangTheoSDT(sDT);

        // Check if the list is not null
        assertNotNull(khachHangList);

        // Print the list
        khachHangList.forEach(System.out::println);
    }

    @Test
    public void testGetListKhachHangByNameAndSDT() {
        // Define a known KhachHang name and phone number
        String tenKH = "Phạm Hoàng Sơn";
        String sdt = "0323575911";

        // Call the method
        ArrayList<KhachHang> khachHangList = khachHangDao.getListKhachHangByNameAndSDT(tenKH, sdt);

        // Check if the list is not null
        assertNotNull(khachHangList);

        // Print the list
        khachHangList.forEach(System.out::println);
    }

    @Test
    public void testTimKhachHangBangSDT() {
        // Define a known KhachHang phone number
        String sdt = "0323575911";

        // Call the method
        KhachHang khachHang = khachHangDao.timKhachHangBangSDT(sdt);

        // Check if the KhachHang is not null
        assertNotNull(khachHang);

        // Print the KhachHang
        System.out.println(khachHang);
    }

    @AfterAll
    public void tearDown() {
        khachHangDao = null;
    }
}