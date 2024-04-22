/**
 * @ (#) ThongKeServiceImpl.java 1.0 22-Apr-24
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package service.impl;

import dao.ThongKeDao;
import entities.KhachHang;
import entities.NhanVien;
import entities.SanPham;
import service.ThongKeService;

import java.time.LocalDate;
import java.util.List;

/**
 * @description:
 * @author: Le Hoang Nam
 * @date: 22-Apr-24
 * @version: 1.0
 */
public class ThongKeServiceImpl implements ThongKeService {
    private ThongKeDao thongKeDao;

    public ThongKeServiceImpl() {
        this.thongKeDao = new ThongKeDao();
    }

    @Override
    public List<NhanVien> getNhanVienBanNhieuNhatTheoNgayTuChon(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws Exception {
        return thongKeDao.getNhanVienBanNhieuNhatTheoNgayTuChon(ngayBatDau, ngayKetThuc);
    }

    @Override
    public List<SanPham> getSanPhamBanNhieuNhatTheoNgayTuChon(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws Exception {
        return thongKeDao.getSanPhamBanNhieuNhatTheoNgayTuChon(ngayBatDau, ngayKetThuc);
    }

    @Override
    public int getSoLuongSachTon() throws Exception {
        return thongKeDao.getSoLuongSachTon();
    }

    @Override
    public int getSoLuongVPPTon() throws Exception {
        return thongKeDao.getSoLuongVPPTon();
    }

    @Override
    public int getSoLuongSachLoi() throws Exception {
        return thongKeDao.getSoLuongSachLoi();
    }

    @Override
    public int getSoLuongHoaDon(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws Exception {
        return thongKeDao.getSoLuongHoaDon(ngayBatDau, ngayKetThuc);
    }

    @Override
    public double getDoanhThu(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws Exception {
        return thongKeDao.getDoanhThu(ngayBatDau, ngayKetThuc);
    }

    @Override
    public List<KhachHang> getKhachHangMuaNhieuNhatTheoNgayTuChon(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws Exception {
        return thongKeDao.getKhachHangMuaNhieuNhatTheoNgayTuChon(ngayBatDau, ngayKetThuc);
    }

    @Override
    public int getSoLuongBanCuaSanPhamChayNhat(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws Exception {
        return thongKeDao.getSoLuongBanCuaSanPhamChayNhat(ngayBatDau, ngayKetThuc);
    }

    @Override
    public double getTongTienCuaKhachHangTop1(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws Exception {
        return thongKeDao.getTongTienCuaKhachHangTop1(ngayBatDau, ngayKetThuc);
    }

    @Override
    public int getSoLuongHoaDonCuaKhachHangTheoMa(LocalDate ngayBatDau, LocalDate ngayKetThuc, String maKH) throws Exception {
        return thongKeDao.getSoLuongHoaDonCuaKhachHangTheoMa(ngayBatDau, ngayKetThuc, maKH);
    }

    @Override
    public double getTongTienCuaKhachHangTheoMa(LocalDate ngayBatDau, LocalDate ngayKetThuc, String maKH) throws Exception {
        return thongKeDao.getTongTienCuaKhachHangTheoMa(ngayBatDau, ngayKetThuc, maKH);
    }

    @Override
    public double getDoanhThuTheoMaNhanVien(LocalDate ngayBatDau, LocalDate ngayKetThuc, String maNV) throws Exception {
        return thongKeDao.getDoanhThuTheoMaNhanVien(ngayBatDau, ngayKetThuc, maNV);
    }

    @Override
    public int getSoLuongHoaDonTheoMaNV(LocalDate ngayBatDau, LocalDate ngayKetThuc, String maNV) throws Exception {
        return thongKeDao.getSoLuongHoaDonTheoMaNV(ngayBatDau, ngayKetThuc, maNV);
    }

    @Override
    public List<NhanVien> getDoanhThuCuaNhanVien(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws Exception {
        return thongKeDao.getDoanhThuCuaNhanVien(ngayBatDau, ngayKetThuc);
    }

    @Override
    public List<NhanVien> thongKeDoanhThu10NVBanNhieuNhat(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws Exception {
        return thongKeDao.thongKeDoanhThu10NVBanNhieuNhat(ngayBatDau, ngayKetThuc);
    }

    @Override
    public List<KhachHang> getTop10KHThanThiet(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws Exception {
        return thongKeDao.getTop10KHThanThiet(ngayBatDau, ngayKetThuc);
    }
}
