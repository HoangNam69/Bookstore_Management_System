/**
 * @ (#) SachLoiDaoTest.java 1.0 21-Apr-24
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package test;

import dao.SachLoiDao;
import entities.Sach;
import entities.SachLoi;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @description:
 * @author: Le Hoang Nam
 * @date: 21-Apr-24
 * @version: 1.0
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SachLoiDaoTest {
    private SachLoiDao sachLoiDao;

    @BeforeAll
    void setUp() {
        sachLoiDao = new SachLoiDao();
    }

    @Test
    void testThemSachLoi() {
        SachLoi sachLoi = new SachLoi(new Sach("SP00001"), "Bị Dính Trang", 5);
        assertTrue(sachLoiDao.themSachLoi(sachLoi));
    }

    @Test
    void testThemSachLoiBiTrung() {
        SachLoi sachLoi = new SachLoi(new Sach("SP00002"), "Bị Dính Trang", 5);
        assertFalse(sachLoiDao.themSachLoi(sachLoi));
    }

    @Test
    void testGetAllSachLoi() {
        List<SachLoi> list = sachLoiDao.getAllSachLoi();
        for (SachLoi sachLoi : list) {
            assertNotNull(sachLoi);
        }
        assertTrue(list.size() > 0);
    }

    @Test
    void testCapNhatSoLuongSachLoi() {
        SachLoi sachLoi = new SachLoi(new Sach("SP00002"), "Bị Dính Trang", 11);
        assertTrue(sachLoiDao.capNhatSoLuong(sachLoi));
    }

    @Test
    void testXoaSachLoi() {
        assertTrue(sachLoiDao.xoaSachLoi("SP00002", "Bị Dính Trang"));
    }

    @Test
    void testXoaSachLoiKhongTonTai() {
        assertFalse(sachLoiDao.xoaSachLoi("SP0000p", "Rách bìa"));
    }

    @AfterAll
    void tearDown() {
        sachLoiDao = null;
    }
}
