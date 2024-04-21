/**
 * @ (#) MauSacDaoTest.java 1.0 21-Apr-24
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package test;

import dao.MauSacDao;
import entities.MauSac;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @description:
 * @author: Le Hoang Nam
 * @date: 21-Apr-24
 * @version: 1.0
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MauSacDaoTest {
    private MauSacDao mauSacDao;

    @BeforeAll
    void setUp() {
        mauSacDao = new MauSacDao();
    }

    @Test
    void testGetListMauSac() {
        ArrayList<MauSac> list = mauSacDao.getListMauSac();
        for (MauSac mauSac : list) {
            assertNotNull(mauSac);
        }
        assertTrue(list.size() > 0);
    }

    @Test
    void testThemMauSac() {
        MauSac mauSac = new MauSac("MS009", "Màu xanh duong");
        assertTrue(mauSacDao.themMauSac(mauSac));
    }

    @Test
    void testThemMauSacTrungTenMau() {
        MauSac mauSac = new MauSac("MS010", "Màu xanh duong");
        assertFalse(mauSacDao.themMauSac(mauSac));
    }

    @Test
    void testCapNhatMauSac() {
        MauSac mauSac = new MauSac("MS003", "Màu xanh lá");
        assertTrue(mauSacDao.capNhatMauSac(mauSac));
    }

    @Test
    void testCapNhatMauSacTrungTen() {
        MauSac mauSac = new MauSac("MS009", "Màu xanh lá");
        assertFalse(mauSacDao.capNhatMauSac(mauSac));
    }

    @AfterAll
    void tearDown() {
        mauSacDao = null;
    }
}
