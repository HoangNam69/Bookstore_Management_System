package test;

import dao.XuatXuDao;
import entities.XuatXu;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class XuatXuDaoTest {
    private XuatXuDao xuatXuDao;

    @BeforeAll
    void setUp() {
        xuatXuDao = new XuatXuDao();
    }

    @Test
    void testGetListXuatXu() {
        ArrayList<XuatXu> list = (ArrayList<XuatXu>) xuatXuDao.getListXuatXu();
        for (XuatXu xuatXu : list) {
            assertNotNull(xuatXu);
        }
        assertTrue(list.size() > 0);
    }

    @Test
    void testThemXuatXu() {
        XuatXu xuatXu = new XuatXu("XX009", "Vinh Long");
        assertTrue(xuatXuDao.themXuatXu(xuatXu));
    }

    //getXuatXy by ma
    @Test
    void testGetXuatXu() {
        XuatXu xuatXu = (XuatXu) xuatXuDao.getXuatXu("XX009");
        assertNotNull(xuatXu);
    }

    //timXuatXu by ten
    @Test
    void testTimXuatXu() {
        XuatXu xuatXu = xuatXuDao.timXuatXu("Vinh Long");
        assertNotNull(xuatXu);
    }

    //xoaXuatXu by ma
    @Test
    void testXoaXuatXu() {
        assertTrue(xuatXuDao.xoaXuatXu("XX009"));
    }


    @AfterAll
    void tearDown() {
        xuatXuDao = null;
    }

}
