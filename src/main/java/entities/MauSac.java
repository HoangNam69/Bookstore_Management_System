package entities;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table(name = "MauSac")
public class MauSac implements Serializable {
    @Id
    @Column(name = "maMauSac", unique = true, nullable = false)
    private String maMau;
    @Column(unique = true, nullable = false)
    private String tenMau;

    //	Moi quan he giua mau sac voi san pham
    @OneToMany(mappedBy = "mauSac", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<VanPhongPham> vanPhongPhams;

    public MauSac(String maMau) {
        super();
        this.maMau = maMau;

    }

    public MauSac(String maMau, String tenMau) {
        super();
        this.maMau = maMau;
        this.tenMau = tenMau;
    }

    public String getMaMau() {
        return maMau;
    }

    public void setMaMau(String maMau) {
        this.maMau = maMau;
    }

    public String getTenMau() {
        return tenMau;
    }

    public void setTenMau(String tenMau) {
        this.tenMau = tenMau;
    }

    @Override
    public String toString() {
        return "MauSac [maMau=" + maMau + ", tenMau=" + tenMau + "]";
    }

}
