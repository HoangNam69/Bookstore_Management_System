package entities;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table(name = "TheLoaiSach")
public class TheLoaiSach implements Serializable {
	@Id
	@Column(name = "maTheLoai", unique = true, nullable = false)
	private String maLoai;
	@Column(name = "tenTheLoai", unique = true, nullable = false)
	private String tenLoai;

//	Moi quan he giua the loai sach voi san pham
	@OneToMany(mappedBy = "theLoaiSach", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<SanPham> sanPhams;



	public TheLoaiSach(String maLoai) {
		super();
		this.maLoai = maLoai;

	}

	public TheLoaiSach(String maLoai, String tenLoai) {
		super();
		this.maLoai = maLoai;
		this.tenLoai = tenLoai;
	}

	public String getMaLoai() {
		return maLoai;
	}

	public void setMaLoai(String maLoai) {
		this.maLoai = maLoai;
	}

	public String getTenLoai() {
		return tenLoai;
	}

	public void setTenLoai(String tenLoai) {
		this.tenLoai = tenLoai;
	}

	@Override
	public String toString() {
		return "TheLoaiSach [maLoai=" + maLoai + ", tenLoai=" + tenLoai + "]";
	}

}
