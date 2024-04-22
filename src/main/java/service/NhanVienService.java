package service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import entities.NhanVien;

public interface NhanVienService {
    public boolean themNhanVien(NhanVien nv) throws SQLException;

    public NhanVien timNhanVienTheoMa(String maNV) throws SQLException;

    public boolean xoaNhanVien(String maNV) throws SQLException;

    public List<NhanVien> getDSNhanVien() throws SQLException;

    public List<NhanVien> timDSNhanVienTheoTen(String tenNV) throws SQLException;

    public boolean suaNhanVien(NhanVien nv) throws SQLException;

    public List<NhanVien> getNhanVienBanNhieuNhatTheoNgayTuChon(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws Exception;

    public List<NhanVien> getDoanhThuCuaNhanVien(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws Exception;

    public List<NhanVien> timDanhSachNhanVienTheoMa(String maNV) throws SQLException;

    public List<NhanVien> timNhanVienTheoSDT(String sDT) throws SQLException;

    public List<NhanVien> getListNhanVienByNameAndSDT(String tenNV, String sdt);

    public NhanVien timNhanVienTheoTen(String tenNV) throws SQLException;

    public NhanVien getNhanVienByEmail(String email);

//	public boolean updateOTP(String gmail, String OTP, Timestamp hetHanOTP);

    public List<NhanVien> thongKeDoanhThu10NVBanNhieuNhat(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws Exception;
}
