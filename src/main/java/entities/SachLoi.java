package entities;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table(name = "SachLoi")
public class SachLoi implements Serializable {

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maSach", unique = true, nullable = false)
	private Sach sach;

	@Column(nullable = false)
	private String loiSanPham;
	@Column(nullable = false)
	private int soLuong;

	public SachLoi(Sach sach, String loiSanPham, int soLuong) {
		super();
		this.sach = sach;
		this.loiSanPham = loiSanPham;
		this.soLuong = soLuong;
	}

	public SachLoi(Sach sach, String loiSanPham) {
		super();
		this.sach = sach;
		this.loiSanPham = loiSanPham;
	}

	public Sach getSach() {
		return sach;
	}

	public void setSach(Sach sach) {
		this.sach = sach;
	}

	public String getLoiSanPham() {
		return loiSanPham;
	}

	public void setLoiSanPham(String loiSanPham) {
		this.loiSanPham = loiSanPham;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	@Override
	public String toString() {
		return "SachLoi [sach=" + sach + ", loiSanPham=" + loiSanPham + ", soLuong=" + soLuong + "]";
	}

}
