package entities;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table(name = "LoaiVanPhongPham")
public class TheLoaiVanPhongPham implements Serializable {
	@Id
	@Column(name = "maLoaiVanPhongPham", unique = true, nullable = false)
	private String maLoai;
	@Column(name = "tenTheLoai", unique = true, nullable = false)
	private String tenLoai;

	//	Moi quan he giua loai van phong pham voi van phong pham
	@OneToMany(mappedBy = "loaiVanPhongPham", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<VanPhongPham> vanPhongPhams;


	public TheLoaiVanPhongPham(String maLoai) {
		super();
		this.maLoai = maLoai;

	}

	public TheLoaiVanPhongPham(String maLoai, String tenLoai) {
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
		return "TheLoaiVanPhongPham [maLoai=" + maLoai + ", tenLoai=" + tenLoai + "]";
	}

}
