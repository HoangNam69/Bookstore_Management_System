package entities;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "ChiTietHoaDonDoiTra")
public class ChiTietHoaDonDoiTra {

    private int soLuong;
    private long donGia;
//    moi quan he voi hoa don doi tra
    @Id
    @ManyToOne
    @JoinColumn(name = "maHoaDonDoiTra")
    private HoaDonDoiTra hoaDonDoiTra;

//    moi quan he voi san pham
    @Id
    @ManyToOne
    @JoinColumn(name = "maSanPham")
    private SanPham sanPham;

    public HoaDonDoiTra getHoaDonDoiTra() {
        return hoaDonDoiTra;
    }

    public void setHoaDonDoiTra(HoaDonDoiTra hoaDonDoiTra) {
        this.hoaDonDoiTra = hoaDonDoiTra;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public long getDonGia() {
        return donGia;
    }

    public void setDonGia(long donGia) {
        this.donGia = donGia;
    }

    public ChiTietHoaDonDoiTra(HoaDonDoiTra hoaDonDoiTra, SanPham sanPham, int soLuong, long donGia) {
        super();
        this.hoaDonDoiTra = hoaDonDoiTra;
        this.sanPham = sanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    @Override
    public String toString() {
        return "ChiTietHoaDonDoiTra [hoaDon=" + hoaDonDoiTra + ", sanPham=" + sanPham + ", soLuong=" + soLuong
                + ", donGia=" + donGia + "]";
    }

}
