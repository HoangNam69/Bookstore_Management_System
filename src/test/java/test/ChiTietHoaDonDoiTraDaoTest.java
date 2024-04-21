/*
 * @ (#) ChiTietHoaDonDoiTraDaoTest.java       21/4/24
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package test;

import entities.ChiTietHoaDonDoiTra;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import service.impl.ChiTietHoaDonDoiTraServiceImpl;
import java.util.List;
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
    public void testGetCTHoaDonDoiTraTheoMaHoaDonDoiTra() {
        List<ChiTietHoaDonDoiTra> actualList = chiTietHoaDonDoiTraService.getCTHoaDonDoiTraTheoMaHoaDonDoiTra("HD0001");
        assertNotNull(actualList);
    }

    @AfterAll
    public void tearDown() {
        chiTietHoaDonDoiTraService = null;
    }
}