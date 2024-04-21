package test;

import dao.ThongKeDao;
import entities.KhachHang;
import entities.NhanVien;
import entities.NhanVienDoanhThu;
import entities.SanPham;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ThongKeDaoTest {
    private ThongKeDao thongKeDao;
    @BeforeAll
    void setUp() {
        thongKeDao = new ThongKeDao();
    }


    @Test
    void testGetNhanVienBanNhieuNhatTheoNgayTuChon(){
        List<NhanVien> list = thongKeDao.getNhanVienBanNhieuNhatTheoNgayTuChon(LocalDate.of(2000, 1, 1), LocalDate.of(2030, 12, 31));
        assert list.size() > 0;
    }

    @Test
    void testGetSanPhamBanNhieuNhatTheoNgayTuChon(){
        List<SanPham> list = thongKeDao.getSanPhamBanNhieuNhatTheoNgayTuChon(LocalDate.of(2000, 1, 1), LocalDate.of(2030, 12, 31));
        System.out.println(list);
        assert list.size() > 0;
    }


    @Test
    void testGetSoLuongSachTon(){
        int soLuongSachTon = thongKeDao.getSoLuongSachTon();
        assert soLuongSachTon >= 0;
    }

    @Test
    void testGetSoLuongVPPTon(){
        int soLuongVPPTon = thongKeDao.getSoLuongVPPTon();
        assert soLuongVPPTon >= 0;
    }


    @Test
    void testGetSoLuongSachLoi(){
        int soLuongSachLoi = thongKeDao.getSoLuongSachLoi();
        assert soLuongSachLoi >= 0;
    }


    @Test
    void testGetSoLuongHoaDon(){
        int soLuongHoaDon = thongKeDao.getSoLuongHoaDon(LocalDate.of(2000, 1, 1), LocalDate.of(2030, 12, 31));
        assert soLuongHoaDon >= 0;
    }


    @Test
    void testGetDoanhThu(){
        double doanhThu = thongKeDao.getDoanhThu(LocalDate.of(2000, 1, 1), LocalDate.of(2030, 12, 31));
        System.out.println(doanhThu);
        assert doanhThu >= 0;
    }


    @Test
    void testGetKhachHangMuaNhieuNhatTheoNgayTuChon(){
        List<KhachHang> list = thongKeDao.getKhachHangMuaNhieuNhatTheoNgayTuChon(LocalDate.of(2000, 1, 1), LocalDate.of(2030, 12, 31));
        System.out.println(list);
        assert list.size() > 0;
    }


    @Test
    void testGetSoLuongBanCuaSanPhamChayNhat(){
        int soLuongBan = thongKeDao.getSoLuongBanCuaSanPhamChayNhat(LocalDate.of(2000, 1, 1), LocalDate.of(2030, 12, 31));
        System.out.println(soLuongBan);
        assert soLuongBan >= 0;
    }

    @Test
    void testGetTongTienCuaKhachHangTop1(){
        double tongTien = thongKeDao.getTongTienCuaKhachHangTop1(LocalDate.of(2000, 1, 1), LocalDate.of(2030, 12, 31));
        System.out.println(tongTien);
        assert tongTien >= 0;
    }


    @Test
    void testGetSoLuongHoaDonCuaKhachHangTheoMa(){
        int soLuongHoaDon = thongKeDao.getSoLuongHoaDonCuaKhachHangTheoMa(LocalDate.of(2000, 1, 1), LocalDate.of(2030, 12, 31), "KH180010");
        System.out.println(soLuongHoaDon);
        assert soLuongHoaDon >= 0;
    }

    @Test
    void testGetTongTienCuaKhachHangTheoMa(){
        double tongTien = thongKeDao.getTongTienCuaKhachHangTheoMa(LocalDate.of(2000, 1, 1), LocalDate.of(2030, 12, 31), "KH180010");
        System.out.println(tongTien);
        assert tongTien >= 0;
    }

    @Test
    void testGetDoanhThuTheoMaNhanVien(){
        double doanhThu = thongKeDao.getDoanhThuTheoMaNhanVien(LocalDate.of(2000, 1, 1), LocalDate.of(2030, 12, 31), "NV000001");
        System.out.println(doanhThu);
        assert doanhThu >= 0;
    }


    @Test
    void testGetSoLuongHoaDonTheoMaNV(){
        int soLuongHoaDon = thongKeDao.getSoLuongHoaDonTheoMaNV(LocalDate.of(2000, 1, 1), LocalDate.of(2030, 12, 31), "NV000001");
        System.out.println(soLuongHoaDon);
        assert soLuongHoaDon >= 0;
    }


    @Test
    void testGetDoanhThuCuaNhanVien(){
        List<NhanVien> list = thongKeDao.getDoanhThuCuaNhanVien(LocalDate.of(2000, 1, 1), LocalDate.of(2030, 12, 31));
        System.out.println(list);
        assert list.size() > 0;
    }


    @Test
    void testThongKeDoanhThu10NVBanNhieuNhat(){
        List<NhanVien> list = thongKeDao.thongKeDoanhThu10NVBanNhieuNhat(LocalDate.of(2000, 1, 1), LocalDate.of(2030, 12, 31));
        System.out.println(list);
        assert list.size() > 0;
    }

    @Test
    void testGetTop10KHThanThiet(){
        List<KhachHang> list = thongKeDao.getTop10KHThanThiet(LocalDate.of(2000, 1, 1), LocalDate.of(2030, 12, 31));
        System.out.println(list);
        assert list.size() > 0;
    }

    @AfterAll
    void tearDown() {
        thongKeDao = null;
    }


}
