package entities;

import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Set;

import dao.HoaDonDao;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "HoaDon")
public class HoaDon implements Serializable {
    @Id
    @Column(unique = true, nullable = false)
    private String maHoaDon;

    private LocalDate ngayLapHoaDon;
    private String ghiChu;
    private double tienKhachDua;
    private boolean tinhTrang;
//    moi quan he giua hoa don - nhan vien
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maNhanVien")
    private NhanVien nhanVien;
//    moi quan he giua hoa don - khach hang
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maKhachHang")
    private KhachHang khachHang;

//    hoa don voi chi tiet hoa don
    @OneToMany(mappedBy = "hoaDon", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ChiTietHoaDon> chiTietHoaDons;

//    hoa don voi hoa don doi tra
    @OneToMany(mappedBy = "hoaDon", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<HoaDonDoiTra> hoaDonDoiTras;

    public HoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public LocalDate getNgayLapHoaDon() {
        return ngayLapHoaDon;
    }

    public void setNgayLapHoaDon(LocalDate ngayLapHoaDon) {
        this.ngayLapHoaDon = ngayLapHoaDon;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public double getTienKhachDua() {
        return tienKhachDua;
    }

    public void setTienKhachDua(double tienKhachDua) {
        this.tienKhachDua = tienKhachDua;
    }

    public boolean isTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(boolean tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public static String auto_ID() {
        HoaDonDao hoadon_dao = new HoaDonDao();
        String idPrefix = "HD";
        LocalDate myObj = LocalDate.now();
        String ngayHD = String.valueOf(myObj.getDayOfMonth());
        int length = 0;
        length = hoadon_dao.getDSHoaDon().size();
        String finalId = idPrefix + ngayHD + String.format("%05d", length + 1);
        return finalId;
    }

    public static String auto_Date() {
        LocalDate myObj = LocalDate.now();
        String ngay = String.valueOf(myObj.getDayOfMonth());
        String thang = String.valueOf(myObj.getMonthValue());
        String nam = String.valueOf(myObj.getYear());
        String finalDate = ngay + "-" + thang + "-" + nam;
        return finalDate;
    }

    public HoaDon(String maHoaDon, String ghiChu, double tienKhachDua, boolean tinhTrang, NhanVien nhanVien, KhachHang khachHang, Set<ChiTietHoaDon> chiTietHoaDons, Set<HoaDonDoiTra> hoaDonDoiTras) {
        this.maHoaDon = maHoaDon;
        this.ghiChu = ghiChu;
        this.tienKhachDua = tienKhachDua;
        this.tinhTrang = tinhTrang;
        this.nhanVien = nhanVien;
        this.khachHang = khachHang;
        this.chiTietHoaDons = chiTietHoaDons;
        this.hoaDonDoiTras = hoaDonDoiTras;
    }

    public HoaDon(String maHoaDon, NhanVien nhanVien, KhachHang khachHang, LocalDate ngayLapHoaDon, String ghiChu,
                  double tienKhachDua, boolean tinhTrang) {
        super();
        this.maHoaDon = maHoaDon;
        this.nhanVien = nhanVien;
        this.khachHang = khachHang;
        this.ngayLapHoaDon = ngayLapHoaDon;
        this.ghiChu = ghiChu;
        this.tienKhachDua = tienKhachDua;
        this.tinhTrang = tinhTrang;
    }

    @Override
    public String toString() {
        return "HoaDon [maHoaDon=" + maHoaDon + ", nhanVien=" + nhanVien + ", khachHang=" + khachHang
                + ", ngayLapHoaDon=" + ngayLapHoaDon + ", ghiChu=" + ghiChu + ", tienKhachDua=" + tienKhachDua
                + ", tinhTrang=" + tinhTrang + "]";
    }

}
