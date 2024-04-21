package test;

import entities.ChiTietHoaDon;
import entities.HoaDon;
import entities.SanPham;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import service.impl.ChiTietHoaDonServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ChiTietHoaDonDaoTest {
    private ChiTietHoaDonServiceImpl chiTietHoaDonService;

    @BeforeAll
    public void setUp() {
        chiTietHoaDonService = new ChiTietHoaDonServiceImpl();
    }

    @Test
    public void testGetCTHoaDonTheoMaHoaDon() {
        String maHD = "HD1300001";
        List<ChiTietHoaDon> result = chiTietHoaDonService.getCTHoaDonTheoMaHoaDon(maHD);
        assertNotNull(result, "Expected non-null list of ChiTietHoaDon");
        result.forEach(System.out::println);
    }

    @Test
    public void testGetCTHDTheoHoaDon() {
        HoaDon hoaDon = new HoaDon();
        hoaDon.setMaHoaDon("HD1300001");
        ArrayList<ChiTietHoaDon> result = chiTietHoaDonService.getCTHDTheoHoaDon(hoaDon);
        assertNotNull(result, "Expected non-null ArrayList of ChiTietHoaDon");
        result.forEach(System.out::println);
    }

    @Test
    public void testGetAllCTHD() {
        List<ChiTietHoaDon> result = chiTietHoaDonService.getAllCTHD();
        assertNotNull(result, "Expected non-null list of ChiTietHoaDon");
        result.forEach(System.out::println);
    }

//    @Test
//    public void testAddChiTietHoaDon() {
//        ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
//        chiTietHoaDon.setHoaDon(new HoaDon("HD1300001"));
//        chiTietHoaDon.setSanPham(new SanPham("SP00001"));
//        chiTietHoaDon.setSoLuong(2);
//        chiTietHoaDon.setDonGia(50000);
//        boolean result = chiTietHoaDonService.addChiTietHoaDon(chiTietHoaDon);
//        assertTrue(result, "Expected true when adding a valid ChiTietHoaDon");
//    }

    @Test
    public void testGetTien() {
        String maHD = "HD1300001";
        double result = chiTietHoaDonService.getTien(maHD);
        assertTrue(result > 0, "Expected positive amount");
        System.out.println("Tổng tiền: " + result);
    }

    @AfterAll
    public void tearDown() {
        chiTietHoaDonService = null;
    }
}