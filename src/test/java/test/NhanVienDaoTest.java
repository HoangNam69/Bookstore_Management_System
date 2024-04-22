/**
 * @ (#) NhanVienDaoTest.java 1.0 21-Apr-24
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package test;

import dao.NhanVienDao;
import entities.NhanVien;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @description:
 * @author: Le Hoang Nam
 * @date: 21-Apr-24
 * @version: 1.0
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NhanVienDaoTest {
    private NhanVienDao nhanVienDao;

    @BeforeAll
    public void setUp() {
        this.nhanVienDao = new NhanVienDao();
    }

    @Test
    void testThemNhanVien() {
        NhanVien nv = new NhanVien("NV000003", "Le Hoang Nam",
                LocalDate.of(2003, 4, 30), "096203000820",
                "416/23/10 DQH-GV-HCM", "0858484522", true, "lehoangnam.31123@gmail.com", true,
                false, "hinh ne");
        assertTrue(nhanVienDao.themNhanvien(nv));
    }

    @Test
    void testThemNhanVienTrungMa() {
        NhanVien nv = new NhanVien("NV000003", "Le Hoang Nam",
                LocalDate.of(2003, 4, 30), "096203000820",
                "416/23/10 DQH-GV-HCM city", "0858484522", true, "lehoangnam.31123@gmail.com", true,
                false, "hinh ne");
        assertFalse(nhanVienDao.themNhanvien(nv));
    }

    @Test
    void testThemNhanVienTrungEmail() {
        NhanVien nv = new NhanVien("NV000004", "Le Hoang Nam",
                LocalDate.of(2003, 4, 30), "096203000822",
                "416/23/10 DQH-GV-HCM city", "0858484521", true, "lehoangnam.31123@gmail.com", true,
                false, "hinh ne");
        assertFalse(nhanVienDao.themNhanvien(nv));
    }

    @Test
    void testThemNhanVienTrungSDT() {
        NhanVien nv = new NhanVien("NV000004", "Le Hoang Nam",
                LocalDate.of(2003, 4, 30), "096203000822",
                "416/23/10 DQH-GV-HCM city", "0858484522", true, "lehoangnam.311203@gmail.com", true,
                false, "hinh ne");
        assertFalse(nhanVienDao.themNhanvien(nv));
    }

    @Test
    void testThemNhanVienTrungCCCD() {
        NhanVien nv = new NhanVien("NV000004", "Le Hoang Nam",
                LocalDate.of(2003, 4, 30), "096203000820",
                "416/23/10 DQH-GV-HCM city", "0858484522", true, "lehoangnam.311203@gmail.com", true,
                false, "hinh ne");
        assertFalse(nhanVienDao.themNhanvien(nv));
    }

    @Test
    void testGetDsNhanVien() {
        List<NhanVien> list = nhanVienDao.getDSNhanVien();
        for (NhanVien nhanVien : list) {
            assertNotNull(nhanVien);
        }
        assertTrue(list.size() > 0);
    }

    @Test
    void testTimDanhSachNhanVienTheoMa() {
        List<NhanVien> list = nhanVienDao.timDanhSachNhanVienTheoMa("NV0");
        for (NhanVien nhanVien : list) {
            assertNotNull(nhanVien);
        }
        assertTrue(list.size() > 0);
    }

    @Test
    void testTimDanhSachNhanVienTheoMaKhongTonTai() {
        List<NhanVien> list = nhanVienDao.timDanhSachNhanVienTheoMa("VN0");
        for (NhanVien nhanVien : list) {
            assertNull(nhanVien);
        }
        assertFalse(list.size() > 0);
    }

    @Test
    void testTimNhanVienTheoMa() {
        NhanVien nv = nhanVienDao.timNhanVienTheoMa("NV000001");
        assertNotNull(nv);
    }

    @Test
    void testTimNhanVienTheoMaKhongTonTai() {
        NhanVien nv = nhanVienDao.timNhanVienTheoMa("Vn000001");
        assertNull(nv);
    }

    @Test
    void testTimDSNhanVienTheoTen() {
        List<NhanVien> list = nhanVienDao.timDSNhanVienTheoTen("L");
        for (NhanVien nhanVien : list) {
            assertNotNull(nhanVien);
        }
        assertTrue(list.size() > 0);
    }

    @Test
    void testTimDSNhanVienTheoTenKhongTonTai() {
        List<NhanVien> list = nhanVienDao.timDSNhanVienTheoTen("S");
        for (NhanVien nhanVien : list) {
            assertNull(nhanVien);
        }
        assertFalse(list.size() > 0);
    }

    @Test
    void testTimNhanVienTheoTen() {
        NhanVien nv = nhanVienDao.timNhanVienTheoTen("Nguyễn Hồng Đức");
        assertNotNull(nv);
    }

    @Test
    void testTimNhanVienTheoTenKhongTonTai() {
        NhanVien nv = nhanVienDao.timNhanVienTheoTen("Lê Hoàng Huy");
        assertNull(nv);
    }

    @Test
    void testTimNhanVienTheoSDT() {
        List<NhanVien> nvs = nhanVienDao.timNhanVienTheoSDT("09");
        for (NhanVien nv : nvs) {
            assertNotNull(nv);
        }
        assertTrue(nvs.size() > 0);
    }

    @Test
    void testTimNhanVienTheoSDTKhongTonTai() {
        List<NhanVien> nvs = nhanVienDao.timNhanVienTheoSDT("09s");
        for (NhanVien nv : nvs) {
            assertNull(nv);
        }
        assertFalse(nvs.size() > 0);
    }

    @Test
    void testGetListNhanVienByNameAndSDT() {
        List<NhanVien> nvs = nhanVienDao.getListNhanVienByNameAndSDT("L", "4522");
        for (NhanVien nv : nvs) {
            assertNotNull(nv);
        }
        assertTrue(nvs.size() > 0);
    }

    @Test
    void testGetListNhanVienByNameAndSDTKhongTonTai() {
        List<NhanVien> nvs = nhanVienDao.getListNhanVienByNameAndSDT("S", "4532");
        for (NhanVien nv : nvs) {
            assertNull(nv);
        }
        assertFalse(nvs.size() > 0);
    }

    @Test
    void testGetNhanVienByEmail() {
        NhanVien nv = nhanVienDao.getNhanVienByEmail("lehoangnam.31123@gmail.com");
        assertNotNull(nv);
    }

    @Test
    void testGetNhanVienByEmailKhongTonTai() {
        NhanVien nv = nhanVienDao.getNhanVienByEmail("lehoangnam.310123@gmail.com");
        assertNull(nv);
    }

    @Test
    void testSuaNhanVien() {
        NhanVien nv = new NhanVien("NV000002", "Lê Minh Bao",
                LocalDate.of(2002, 11, 9), "02120120462",
                "416/23/17 DQH-GV-HCM", "0969828711", true, "pxcpaze@gmail.com", false,
                false, "nvduy.jpg");
        assertTrue(nhanVienDao.suaNhanVien(nv));
    }

    @Test
    void testSuaNhanVienKhongTonTai() {
        NhanVien nv = new NhanVien("vn000002", "Lê Minh Bao",
                LocalDate.of(2002, 11, 9), "02120120462",
                "416/23/17 DQH-GV-HCM", "0969828711", true, "pxcpaze@gmail.com", false,
                false, "nvduy.jpg");
        assertFalse(nhanVienDao.suaNhanVien(nv));
    }

    @AfterAll
    public void tearDown() {
        this.nhanVienDao = null;
    }
}
