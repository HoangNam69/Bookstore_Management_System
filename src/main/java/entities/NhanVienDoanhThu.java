package entities;

public class NhanVienDoanhThu {
    private NhanVien nhanVien;
    private Long tongDoanhThu;

    public NhanVienDoanhThu(NhanVien nhanVien, Long tongDoanhThu) {
        this.nhanVien = nhanVien;
        this.tongDoanhThu = tongDoanhThu;
    }

    public NhanVienDoanhThu() {
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public void setTongDoanhThu(Long tongDoanhThu) {
        this.tongDoanhThu = tongDoanhThu;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public Long getTongDoanhThu() {
        return tongDoanhThu;
    }

    @Override
    public String toString() {
        return "NhanVienDoanhThu{" +
                "nhanVien=" + nhanVien +
                ", tongDoanhThu=" + tongDoanhThu +
                '}';
    }
}
