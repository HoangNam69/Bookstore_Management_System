/*
 * @ (#) ChiTietHoaDonDoiTraDaoTest.java       21/4/24
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package test;

import dao.HoaDonDoiTraDao;
import dao.SanPhamDao;
import entities.ChiTietHoaDonDoiTra;
import entities.HoaDonDoiTra;
import entities.SanPham;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import service.impl.ChiTietHoaDonDoiTraServiceImpl;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


/*
 * @description: This class is used for testing
 * @author: Nguyen Hong Duc
 * @date:   21/4/24
 */

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ChiTietHoaDonDoiTraDaoTest {
    private ChiTietHoaDonDoiTraServiceImpl chiTietHoaDonDoiTraService;

    @BeforeAll
    public void setUp() {
        chiTietHoaDonDoiTraService = new ChiTietHoaDonDoiTraServiceImpl();
    }

    @Test
    public void testThemChiTietHoaDonDoiTra() {
        // Create a new ChiTietHoaDonDoiTra
        ChiTietHoaDonDoiTra cthddt = new ChiTietHoaDonDoiTra();
        cthddt.setDonGia(10000);
        cthddt.setSoLuong(5);

        // Get the SanPham and HoaDonDoiTra from the database
        SanPhamDao sanPhamDao = new SanPhamDao();
        HoaDonDoiTraDao hoaDonDoiTraDao = new HoaDonDoiTraDao();

        SanPham sanPham = sanPhamDao.timSanPhamTheoMa("SP00002");
        HoaDonDoiTra hoaDonDoiTra = hoaDonDoiTraDao.timHoaDonDoiTraTheoMa("HDDT0002");

        // Set the maSanPham and maHoaDonDoiTra
        cthddt.setSanPham(sanPham);
        cthddt.setHoaDonDoiTra(hoaDonDoiTra);

        int result = 0;
        try {
            result = chiTietHoaDonDoiTraService.themChiTietHoaDonDoiTra(cthddt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Check if the operation was successful
        assertEquals(1, result);

        // Print out a success message along with the details of the ChiTietHoaDonDoiTra that was added
        if (result == 1) {
            System.out.println("ThemChiTietHoaDonDoiTra operation was successful. Details of the added ChiTietHoaDonDoiTra:");
            System.out.println(cthddt.toString());
        }
    }

    @Test
    public void testGetCTHoaDonDoiTraTheoMaHoaDonDoiTra() {
        List<ChiTietHoaDonDoiTra> actualList = chiTietHoaDonDoiTraService.getCTHoaDonDoiTraTheoMaHoaDonDoiTra("HDDT0002");
        assertNotNull(actualList);

        // Print out a message along with the details of the ChiTietHoaDonDoiTra that was found
        if (actualList != null && !actualList.isEmpty()) {
            System.out.println("Found ChiTietHoaDonDoiTra with maHoaDonDoiTra = HDDT0002. Details:");
            for (ChiTietHoaDonDoiTra cthddt : actualList) {
                System.out.println(cthddt.toString());
            }
        }
    }

    @AfterAll
    public void tearDown() {
        chiTietHoaDonDoiTraService = null;
    }
}