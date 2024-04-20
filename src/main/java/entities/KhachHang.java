package entities;

import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Set;

import dao.KhachHangDao;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "KhachHang")
public class KhachHang implements Serializable {
    @Id
    @Column(unique = true, nullable = false)
    private String maKhachHang;
    @Column(nullable = false)
    private String hoTenKhachHang;
    @Column(name = "sdt", unique = true, nullable = false)
    private String sDT;
    private boolean gioiTinh;
    private String diaChi;

    @OneToMany(mappedBy = "khachHang", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<HoaDon> hoaDons;


    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getHoTenKhachHang() {
        return hoTenKhachHang;
    }

    public void setHoTenKhachHang(String hoTenKhachHang) {
        this.hoTenKhachHang = hoTenKhachHang;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getsDT() {
        return sDT;
    }

    public void setsDT(String sDT) {
        this.sDT = sDT;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public static String auto_ID() {
        KhachHangDao khachhang_dao = new KhachHangDao();
        String idPrefix = "KH";
        LocalDate myObj = LocalDate.now();
        String ngayMaKH = String.valueOf(myObj.getDayOfMonth());
        int length = 0;
        length = khachhang_dao.getDSKhachHang().size();
        String finalId = idPrefix + ngayMaKH + String.format("%04d", length + 1);
        return finalId;
    }

    public KhachHang(String maKhachHang) {
        super();
        this.maKhachHang = maKhachHang;
    }

    public KhachHang(String maKhachHang, String hoTenKhachHang, String sDT, boolean gioiTinh, String diaChi) {
        super();
        this.maKhachHang = maKhachHang;
        this.hoTenKhachHang = hoTenKhachHang;
        this.gioiTinh = gioiTinh;
        this.sDT = sDT;
        this.diaChi = diaChi;
    }

    @Override
    public String toString() {
        return "KhachHang [maKhachHang=" + maKhachHang + ", hoTenKhachHang=" + hoTenKhachHang + ", sDT=" + sDT
                + ", gioiTinh=" + gioiTinh + ", diaChi=" + diaChi + "]";
    }

}
