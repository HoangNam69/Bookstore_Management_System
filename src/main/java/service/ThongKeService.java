/**
 * @ (#) ThongKeService.java 1.0 22-Apr-24
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package service;

import entities.KhachHang;
import entities.NhanVien;
import entities.SanPham;

import java.time.LocalDate;
import java.util.List;

/**
 * @description:
 * @author: Le Hoang Nam
 * @date: 22-Apr-24
 * @version: 1.0
 */
public interface ThongKeService {
    public List<NhanVien> getNhanVienBanNhieuNhatTheoNgayTuChon(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws Exception;

    public List<SanPham> getSanPhamBanNhieuNhatTheoNgayTuChon(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws Exception;

    public int getSoLuongSachTon() throws Exception;

    public int getSoLuongVPPTon() throws Exception;

    public int getSoLuongSachLoi() throws Exception;

    public int getSoLuongHoaDon(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws Exception;

    public double getDoanhThu(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws Exception;

    public List<KhachHang> getKhachHangMuaNhieuNhatTheoNgayTuChon(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws Exception;

    public int getSoLuongBanCuaSanPhamChayNhat(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws Exception;

    public double getTongTienCuaKhachHangTop1(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws Exception;

    public int getSoLuongHoaDonCuaKhachHangTheoMa(LocalDate ngayBatDau, LocalDate ngayKetThuc, String maKH) throws Exception;

    public double getTongTienCuaKhachHangTheoMa(LocalDate ngayBatDau, LocalDate ngayKetThuc, String maKH) throws Exception;

    public double getDoanhThuTheoMaNhanVien(LocalDate ngayBatDau, LocalDate ngayKetThuc, String maNV) throws Exception;

    public int getSoLuongHoaDonTheoMaNV(LocalDate ngayBatDau, LocalDate ngayKetThuc, String maNV) throws Exception;

    public List<NhanVien> getDoanhThuCuaNhanVien(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws Exception;

    public List<NhanVien> thongKeDoanhThu10NVBanNhieuNhat(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws Exception;

    public List<KhachHang> getTop10KHThanThiet(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws Exception;
}
