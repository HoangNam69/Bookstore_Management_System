package entities;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.C;

import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table(name = "NhaXuatBan")
public class NhaXuatBan implements Serializable {
    @Id
    @Column(unique = true, nullable = false)
    private String maNXB;
    @Column(unique = true, nullable = false)
    private String tenNXB;

//    Moi quan he giua nha xuat ban voi san pham
    @OneToMany(mappedBy = "nhaXuatBan", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<SanPham> sanPhams;

    public NhaXuatBan(String maNXB) {
        super();
        this.maNXB = maNXB;
    }

    public NhaXuatBan(String maNXB, String tenNXB) {
        super();
        this.maNXB = maNXB;
        this.tenNXB = tenNXB;
    }

    public String getMaNXB() {
        return maNXB;
    }

    public void setMaNXB(String maNXB) {
        this.maNXB = maNXB;
    }

    public String getTenNXB() {
        return tenNXB;
    }

    public void setTenNXB(String tenNXB) {
        this.tenNXB = tenNXB;
    }

    @Override
    public String toString() {
        return "NhaXuatBan [maNXB=" + maNXB + ", tenNXB=" + tenNXB + "]";
    }

}
