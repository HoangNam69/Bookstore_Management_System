package entities;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table(name = "SanPham")
public abstract class SanPham implements Serializable  {
    @Id
    @Column(unique = true, nullable = false)
    protected String maSanPham;
    @Column(nullable = false)
    protected String loaiSanPham;
    protected int soLuongTon;
    protected double trongLuong;
    protected long giaNhap;
    protected String ghiChu;
    protected String donViSanPham;
    protected String hinhAnh;

//    Moi quan he voi nha cung cap
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maNCC")
    protected NhaCungCap nhaCungCap;
//    Moi quan he voi chi tiet hoa don
    @OneToMany(mappedBy = "sanPham", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ChiTietHoaDon> chiTietHoaDons;
//    Moi quan he voi chi tiet hoa don doi tra
    @OneToMany(mappedBy = "sanPham", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ChiTietHoaDonDoiTra> chiTietHoaDonDoiTras;

    public SanPham(String maSanPham) {
        super();
        this.maSanPham = maSanPham;

    }

    public SanPham(String maSanPham, String loaiSanPham, int soLuongTon, double trongLuong, NhaCungCap nhaCungCap,
                   long giaNhap, String ghiChu, String donViSanPham, String hinhAnh) {
        super();
        this.maSanPham = maSanPham;
        this.loaiSanPham = loaiSanPham;
        this.soLuongTon = soLuongTon;
        this.trongLuong = trongLuong;
        this.nhaCungCap = nhaCungCap;
        this.giaNhap = giaNhap;
        this.ghiChu = ghiChu;
        this.donViSanPham = donViSanPham;
        this.hinhAnh = hinhAnh;
    }

    public SanPham(String maSanPham, int soLuongTon) {
        super();
        this.maSanPham = maSanPham;
        this.soLuongTon = soLuongTon;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getLoaiSanPham() {
        return loaiSanPham;
    }

    public void setLoaiSanPham(String loaiSanPham) {
        this.loaiSanPham = loaiSanPham;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    public double getTrongLuong() {
        return trongLuong;
    }

    public void setTrongLuong(double trongLuong) {
        this.trongLuong = trongLuong;
    }

    public NhaCungCap getNhaCungCap() {
        return nhaCungCap;
    }

    public void setNhaCungCap(NhaCungCap nhaCungCap) {
        this.nhaCungCap = nhaCungCap;
    }

    public long getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(long giaNhap) {
        this.giaNhap = giaNhap;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getDonViSanPham() {
        return donViSanPham;
    }

    public void setDonViSanPham(String donViSanPham) {
        this.donViSanPham = donViSanPham;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public long tinhGiaBan() {
        return getGiaNhap() + getGiaNhap() * 20 / 100;
    }

    @Override
    public String toString() {
        return "SanPham [maSanPham=" + maSanPham + ", loaiSanPham=" + loaiSanPham + ", soLuongTon=" + soLuongTon
                + ", trongLuong=" + trongLuong + ", nhaCungCap=" + nhaCungCap + ", giaNhap=" + giaNhap + ", ghiChu="
                + ghiChu + ", donViSanPham=" + donViSanPham + ", hinhAnh=" + hinhAnh + "]";
    }
}
