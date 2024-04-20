package entities;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table(name = "XuatXu")
public class XuatXu implements Serializable {
    @Id
    @Column(unique = true, nullable = false)
    private String maXuatXu;
    @Column(unique = true, nullable = false)
    private String tenXuatXu;

    //	moi quan he giua xuat xu voi san pham
    @OneToMany(mappedBy = "xuatXu", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<VanPhongPham> vanPhongPhams;


    public XuatXu(String maXuatXu) {
        super();
        this.maXuatXu = maXuatXu;
    }

    public XuatXu(String maXuatXu, String tenXuatXu) {
        super();
        this.maXuatXu = maXuatXu;
        this.tenXuatXu = tenXuatXu;
    }

    public String getMaXuatXu() {
        return maXuatXu;
    }

    public void setMaXuatXu(String maXuatXu) {
        this.maXuatXu = maXuatXu;
    }

    public String getTenXuatXu() {
        return tenXuatXu;
    }

    public void setTenXuatXu(String tenXuatXu) {
        this.tenXuatXu = tenXuatXu;
    }

    @Override
    public String toString() {
        return "XuatXu [maXuatXu=" + maXuatXu + ", tenXuatXu=" + tenXuatXu + "]";
    }

}
