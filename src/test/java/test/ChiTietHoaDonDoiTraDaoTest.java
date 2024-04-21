/*
 * @ (#) ChiTietHoaDonDoiTraDaoTest.java       21/4/24
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package test;

import entities.ChiTietHoaDonDoiTra;
import entities.HoaDonDoiTra;
import entities.Sach;
import entities.SanPham;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
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
    public void testThemChiTietHoaDonDoiTra() throws SQLException {
        ChiTietHoaDonDoiTra cthddt = new ChiTietHoaDonDoiTra();
        // Set properties for cthddt
        cthddt.setDonGia(1000);
        cthddt.setSoLuong(1);
        //cthddt.setSanPham(new ConcreteSanPham("SP0001")); // Assuming ConcreteSanPham is a concrete subclass of SanPham
        cthddt.setHoaDonDoiTra(new HoaDonDoiTra("HD0001"));
        int result = chiTietHoaDonDoiTraService.themChiTietHoaDonDoiTra(cthddt);
        assertEquals(1, result);
    }

    @Test
    public void testGetCTHoaDonDoiTraTheoMaHoaDonDoiTra() {
        List<ChiTietHoaDonDoiTra> actualList = chiTietHoaDonDoiTraService.getCTHoaDonDoiTraTheoMaHoaDonDoiTra("HD0001");
        assertNotNull(actualList);
    }

    @AfterAll
    public void tearDown() {
        chiTietHoaDonDoiTraService = null;
    }
}