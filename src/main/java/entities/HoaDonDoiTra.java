package entities;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table(name = "HoaDonDoiTra")
public class HoaDonDoiTra implements Serializable {
	@Id
	@Column(unique = true, nullable = false)
	private String maHoaDonDoiTra;
	@Column(nullable = false)
	private LocalDate ngayLapHoaDon;
	private String ghiChu;
	private double tienKhachDua;
	private double tienPhaiTru;
//	moi quan he giua hoa don doi tra - nhan vien
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maNhanVien")
	private NhanVien nhanVien;
//	moi quan he giua hoa don doi tra - khach hang
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maKhachHang")
	private KhachHang khachHang;
//	moi quan he giua hoa don doi tra - chi tiet hoa don doi tra
	@OneToMany(mappedBy = "hoaDonDoiTra", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<ChiTietHoaDonDoiTra> chiTietHoaDonDoiTras;

//	moi quan he giua hoa don doi tra - hoa don
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maHoaDon")
	private HoaDon hoaDon;

	public String getMaHoaDonDoiTra() {
		return maHoaDonDoiTra;
	}

	public void setMaHoaDonDoiTra(String maHoaDonDoiTra) {
		this.maHoaDonDoiTra = maHoaDonDoiTra;
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

	public HoaDon getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}

	public double getTienPhaiTru() {
		return tienPhaiTru;
	}

	public void setTienPhaiTru(double tienPhaiTru) {
		this.tienPhaiTru = tienPhaiTru;
	}

	@Override
	public String toString() {
		return "HoaDonDoiTra [maHoaDonDoiTra=" + maHoaDonDoiTra + ", nhanVien=" + nhanVien + ", khachHang=" + khachHang
				+ ", ngayLapHoaDon=" + ngayLapHoaDon + ", ghiChu=" + ghiChu + ", tienKhachDua=" + tienKhachDua
				+ ", hoaDon=" + hoaDon + ", tienPhaiTru=" + tienPhaiTru + "]";
	}

	public HoaDonDoiTra(String maHoaDonDoiTra, NhanVien nhanVien, KhachHang khachHang, LocalDate ngayLapHoaDon,
			String ghiChu, double tienKhachDua, HoaDon hoaDon, double tienPhaiTru) {
		super();
		this.maHoaDonDoiTra = maHoaDonDoiTra;
		this.nhanVien = nhanVien;
		this.khachHang = khachHang;
		this.ngayLapHoaDon = ngayLapHoaDon;
		this.ghiChu = ghiChu;
		this.tienKhachDua = tienKhachDua;
		this.hoaDon = hoaDon;
		this.tienPhaiTru = tienPhaiTru;
	}

}
