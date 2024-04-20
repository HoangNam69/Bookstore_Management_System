package entities;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Entity
@Table(name = "TaiKhoan")
public class TaiKhoan implements Serializable {
    @Id
    @Column(unique = true, nullable = false)
    private String tenDangNhap;
    @Column(nullable = false)
    private String matKhau;

    @OneToOne
    @JoinColumn(name = "maNhanVien")

    private NhanVien nhanVien;
    @Column(nullable = false)
    private boolean quyen;

    public TaiKhoan(String tenDangNhap) {
        super();
        this.tenDangNhap = tenDangNhap;
    }

    public TaiKhoan(String tenDangNhap, String matKhau, NhanVien nhanVien, boolean quyen) {
        super();
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.nhanVien = nhanVien;
        this.quyen = quyen;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public boolean isQuyen() {
        return quyen;
    }

    public void setQuyen(boolean quyen) {
        this.quyen = quyen;
    }

    @Override
    public String toString() {
        return "TaiKhoan [tenDangNhap=" + tenDangNhap + ", matKhau=" + matKhau + ", nhanVien=" + nhanVien + ", quyen="
                + quyen + "]";
    }

}
