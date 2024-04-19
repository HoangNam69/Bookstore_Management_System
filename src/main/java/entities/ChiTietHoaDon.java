package entities;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Entity
@Table(name = "ChiTietHoaDon")
public class ChiTietHoaDon implements Serializable {

	private int soLuong;
	private long donGia;

	@Id
	@ManyToOne
	@JoinColumn(name = "maHoaDon")
	private HoaDon hoaDon;

	@Id
	@ManyToOne
	@JoinColumn(name = "maSanPham")
	private SanPham sanPham;


	public ChiTietHoaDon(HoaDon hoaDon, SanPham sanPham, int soLuong, long donGia) {
		super();
		this.hoaDon = hoaDon;
		this.sanPham = sanPham;
		this.soLuong = soLuong;
		this.donGia = donGia;
	}

	public long tinhThanhTien() {
		return soLuong * donGia;
	}

	public HoaDon getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
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

	@Override
	public String toString() {
		return "ChiTietHoaDon [hoaDon=" + hoaDon + ", sanPham=" + sanPham + ", soLuong=" + soLuong + ", donGia="
				+ donGia + "]";
	}

}
