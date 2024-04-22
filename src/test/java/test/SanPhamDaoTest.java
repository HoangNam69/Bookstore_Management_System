/**
 * @ (#) SanPhamDaoTest.java 1.0 21-Apr-24
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package test;

import dao.SanPhamDao;
import entities.*;
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
public class SanPhamDaoTest {
    private SanPhamDao sanPhamDao;

    @BeforeAll
    void setUp() {
        sanPhamDao = new SanPhamDao();
    }

    @Test
    void testTimSanPhamTheoMa() {
        SanPham sanPham = sanPhamDao.timSanPhamTheoMa("SP00019");
        assertNotNull(sanPham);
    }

    @Test
    void testTimSanPhamTheoMaKhongTonTai() {
        SanPham sanPham = sanPhamDao.timSanPhamTheoMa("SSP00019");
        assertNull(sanPham);
    }

    @Test
    void testGetSachTheoMa() {
        Sach sach = sanPhamDao.getSachTheoMaSP("SP00027");
        System.out.println(sach);
        assertNotNull(sach);
    }

    @Test
    void testGetSachTheoMaKhongTonTai() {
        Sach sach = sanPhamDao.getSachTheoMaSP("SSP00019");
        System.out.println(sach);
        assertNull(sach);
    }

    @Test
    void testGetSachTheoMaKhongPhaiSach() {
        Sach sach = sanPhamDao.getSachTheoMaSP("SP00019");
        System.out.println(sach);
        assertNull(sach);
    }

    @Test
    void testGetSanPhamTheoMa() {
        SanPham sanPham = sanPhamDao.getSanPhamTheoMa("SP00019");
        System.out.println(sanPham);
        assertNotNull(sanPham);
    }

    @Test
    void testGetSanPhamTheoMaKhongTonTai() {
        SanPham sanPham = sanPhamDao.getSanPhamTheoMa("SSP00019");
        System.out.println(sanPham);
        assertNull(sanPham);
    }

    @Test
    void testGetVPPTheoMa() {
        VanPhongPham vpp = sanPhamDao.getVPPTheoMaSP("SP00019");
        System.out.println(vpp);
        assertNotNull(vpp);
    }

    @Test
    void testGetVPPTheoMaKhongTonTai() {
        VanPhongPham vpp = sanPhamDao.getVPPTheoMaSP("SSP00019");
        System.out.println(vpp);
        assertNull(vpp);
    }

    @Test
    void testGetVPPTheoMaKhongPhaiVanPhongPham() {
        VanPhongPham vpp = sanPhamDao.getVPPTheoMaSP("SP00027");
        System.out.println(vpp);
        assertNull(vpp);
    }

    @Test
    void testGetListSachTheoYeuCau() {
        List<Sach> listSach = sanPhamDao.getListSachTheoYeuCau("SP00006",
                "l", "L001", 0L, 200000L, "TG",
                "NXB0", "NCC0", false);
        for (Sach sach : listSach) {
            System.out.println(sach);
            assertNotNull(sach);
        }
        assertTrue(listSach.size() > 0);
    }

    @Test
    void testGetListSachTheoYeuCauSachkhongTonTai() {
        List<Sach> listSach = sanPhamDao.getListSachTheoYeuCau("SP0001",
                "Bé", "L001", 0L, 100000L, "TG006",
                "NXB002", "NCC005", false);
        for (Sach sach : listSach) {
            assertNull(sach);
        }
        assertFalse(listSach.size() > 0);
    }

    @Test
    void testGetListVanPhongPhamTheoYeuCau() {
        List<VanPhongPham> listVPP = sanPhamDao.getListVanPhongPhamTheoYeuCau("SP0000",
                "C", "TL00", 0L, 200000L, "CL0",
                "XX0", "NCC0", false);
        for (VanPhongPham vpp : listVPP) {
            System.out.println(vpp);
            assertNotNull(vpp);
        }
        assertTrue(listVPP.size() > 0);
    }

    @Test
    void testGetListVanPhongPhamTheoYeuCauKhongTonTaiSanPham() {
        List<VanPhongPham> listVPP = sanPhamDao.getListVanPhongPhamTheoYeuCau("SP0000ps",
                "C", "TL00", 0L, 200000L, "CL0",
                "XX0", "NCC0", false);
        for (VanPhongPham vpp : listVPP) {
            System.out.println(vpp);
            assertNull(vpp);
        }
        assertFalse(listVPP.size() > 0);
    }


    @Test
    void testThemSanPhamSach() {
        Sach sach = new Sach("SP00031", "Sách", 100, 0.5, new NhaCungCap("NCC005"),
                100000, "Ghi chú", "Quyển", "Hinh anh", "Ten sach",
                new TacGia("TG007"), new NhaXuatBan("NXB006"), 0, 0, new TheLoaiSach("L001"));
        boolean result = sanPhamDao.themSanPham(sach);
        assertTrue(result);
    }

    @Test
    void testThemSanPhamSachTrungMa() {
        Sach sach = new Sach("SP00031", "Sách", 100, 0.5, new NhaCungCap("NCC005"),
                100000, "Ghi chú", "Quyển", "Hinh anh", "Ten sach",
                new TacGia("TG007"), new NhaXuatBan("NXB006"), 0, 0, new TheLoaiSach("L001"));
        assertFalse(sanPhamDao.themSanPham(sach));
    }

    @Test
    void testThemSanPhamVanPhongPham() {

        VanPhongPham vpp = new VanPhongPham("SP00044", "Văn phòng phẩm", 10, 0.5, new NhaCungCap("NCC005"),
                100000, "Ghi chú", "Cái", "Hinh anh", "Ten văn phòng phẩm",
                new TheLoaiVanPhongPham("TL001"), new MauSac("MS001"), new ChatLieu("CL001"), new XuatXu("XX001"));

        boolean result = sanPhamDao.themSanPham(vpp);
        assertTrue(result);
    }

    @Test
    void testThemSanPhamVanPhongPhamTrungMa() {

        VanPhongPham vpp = new VanPhongPham("SP00044", "Văn phòng phẩm", 10, 0.5, new NhaCungCap("NCC005"),
                100000, "Ghi chú", "Cái", "Hinh anh", "Ten văn phòng phẩm",
                new TheLoaiVanPhongPham("TL001"), new MauSac("MS001"), new ChatLieu("CL001"), new XuatXu("XX001"));

        assertFalse(sanPhamDao.themSanPham(vpp));
    }

    @Test
    void testCapNhatSanPhamSach() {
        Sach sach = new Sach("SP00031", "Sách", 100, 0.5, new NhaCungCap("NCC005"),
                100000, "An trong", "Quyển", "Hinh anh", "Dua An Tiem",
                new TacGia("TG007"), new NhaXuatBan("NXB006"), 0, 0, new TheLoaiSach("L001"));
        assertTrue(sanPhamDao.capNhatSanPham("SP00031", sach));
    }

    @Test
    void testCapNhatSanPhamSachCoMaKhongTonTai() {
        Sach sach = new Sach("SSPP00031", "Sách", 100, 0.5, new NhaCungCap("NCC005"),
                100000, "Ghi chú", "Quyển", "Hinh anh", "Ten sach",
                new TacGia("TG007"), new NhaXuatBan("NXB006"), 0, 0, new TheLoaiSach("L001"));
        assertFalse(sanPhamDao.capNhatSanPham("SSPP00031", sach));
    }

    @Test
    void testCapNhatSanPhamVanPhongPham() {
        VanPhongPham vpp = new VanPhongPham("SP00044", "Văn phòng phẩm", 155, 0.5, new NhaCungCap("NCC005"),
                100000, "An trong", "Cái", "Hinh anh", "Ten văn phòng phẩm",
                new TheLoaiVanPhongPham("TL001"), new MauSac("MS001"), new ChatLieu("CL001"), new XuatXu("XX001"));
        assertTrue(sanPhamDao.capNhatSanPham("SP00044", vpp));
    }

    @Test
    void testCapNhatSanPhamVanPhongPhamCoMaKhongTonTai() {
        VanPhongPham vpp = new VanPhongPham("SSP00044", "Văn phòng phẩm", 155, 0.5, new NhaCungCap("NCC005"),
                100000, "An trong", "Cái", "Hinh anh", "Ten văn phòng phẩm",
                new TheLoaiVanPhongPham("TL001"), new MauSac("MS001"), new ChatLieu("CL001"), new XuatXu("XX001"));
        assertFalse(sanPhamDao.capNhatSanPham("SSP00044", vpp));
    }


    @Test
    void testGetMaSPMax() {
        String maSP = sanPhamDao.getMaSPMax();
        System.out.println(maSP);
        System.out.println("SSP00031".compareTo("SP00044") > 0 ? "ssp lon hon" : "ssp nho hon");
        assertNotNull(maSP);
    }

    @Test
    void testTimSanPhamTheoMaVPP() {
        VanPhongPham vpp = sanPhamDao.getVPPTheoMaSP("SP00044");
        System.out.println(vpp);
        assertNotNull(vpp);
    }

    @Test
    void testTimSanPhamTheoMaVPPKhongTonTai() {
        VanPhongPham vpp = sanPhamDao.getVPPTheoMaSP("SSP00044");
        System.out.println(vpp);
        assertNull(vpp);
    }

    @Test
    void testGetAllSach() {
        List<Sach> listSach = sanPhamDao.getAllSach();
        for (Sach sach : listSach) {
            System.out.println(sach);
            assertNotNull(sach);
        }
        assertTrue(listSach.size() > 0);
    }

    @Test
    void testGetAllVPP() {
        List<VanPhongPham> listVPP = sanPhamDao.getAllVPP();
        for (VanPhongPham vpp : listVPP) {
            System.out.println(vpp);
            assertNotNull(vpp);
        }
        assertTrue(listVPP.size() > 0);
    }

    @Test
    void testGetSachTheoTen() {
        Sach sach = sanPhamDao.getSachTheoTen("Dua An Tiem");
        System.out.println(sach);
        assertNotNull(sach);
    }

    @Test
    void testGetSachTheoTenKhongTonTai() {
        Sach sach = sanPhamDao.getSachTheoTen("Dua An");
        System.out.println(sach);
        assertNull(sach);
    }

    @Test
    void testGetVPPTheoTen() {
        VanPhongPham vpp = sanPhamDao.getVPPTheoTen("Khăn quàng");
        System.out.println(vpp);
        assertNotNull(vpp);
    }

    @Test
    void testGetVPPTheoTenKhongTonTai() {
        VanPhongPham vpp = sanPhamDao.getVPPTheoTen("Khăn");
        System.out.println(vpp);
        assertNull(vpp);
    }

    @Test
    void testCapNhatSoLuongSanPham() {
        Sach sach = new Sach("SP00031", 156);
        int result = sanPhamDao.capNhatSoLuongSanPham(sach);
        assertEquals(1, result);
    }

    @Test
    void testCapNhatSoLuongSanPhamChoSanPhamKhongTonTai() {
        Sach sach = new Sach("SSP00033", "Sách", 180, 0.5, new NhaCungCap("NCC005"),
                100000, "An trong", "Quyển", "Hinh anh", "Dua An Tiem",
                new TacGia("TG007"), new NhaXuatBan("NXB006"), 0, 0, new TheLoaiSach("L001"));
        int result = sanPhamDao.capNhatSoLuongSanPham(sach);
        assertEquals(0, result);
    }


    @Test
    void testKiemTraTonTaiSanPham() {
        boolean result = sanPhamDao.kiemTraTonTaiSanPham("Tư duy sâu");
        assertTrue(result);
    }

    @Test
    void testKiemTraTonTaiSanPhamVoiTenKhongTonTai() {
        boolean result = sanPhamDao.kiemTraTonTaiSanPham("Tư duy sâu sắc");
        assertFalse(result);
    }

    @Test
    void testTimSanPhamTheoMa1() {
        SanPham sanPham = sanPhamDao.timSanPhamTheoMa1("SP00027");
        System.out.println(sanPham);
        assertNotNull(sanPham);
    }

    @Test
    void testTimSanPhamTheoMa1KhongTonTai() {
        SanPham sanPham = sanPhamDao.timSanPhamTheoMa1("SPS00027");
        System.out.println(sanPham);
        assertNull(sanPham);
    }

    @AfterAll
    void tearDown() {
        sanPhamDao = null;
    }

}
