package entities;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table(name = "NhaCungCap")
public class NhaCungCap implements Serializable {
    @Id
    @Column(name = "maNCC", unique = true, nullable = false)
    private String maNCC;
    @Column(unique = true, nullable = false)
    private String tenNCC;
    private String diaChi;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true, nullable = false)
    private String sDT;

    @OneToMany(mappedBy = "nhaCungCap", fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    private Set<SanPham> sanPhams;

    public String getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public String getTenNCC() {
        return tenNCC;
    }

    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getsDT() {
        return sDT;
    }

    public void setsDT(String sDT) {
        this.sDT = sDT;
    }

    public NhaCungCap(String maNCC, String tenNCC, String diaChi, String email, String sDT) {
        super();
        this.maNCC = maNCC;
        this.tenNCC = tenNCC;
        this.diaChi = diaChi;
        this.email = email;
        this.sDT = sDT;
    }

    public NhaCungCap(String maNCC, String tenNCC) {
        super();
        this.maNCC = maNCC;
        this.tenNCC = tenNCC;
    }

    public NhaCungCap(String maNCC) {
        super();
        this.maNCC = maNCC;
    }

    @Override
    public String toString() {
        return "NhaCungCap [maNCC=" + maNCC + ", tenNCC=" + tenNCC + ", diaChi=" + diaChi + ", email=" + email
                + ", sDT=" + sDT + "]";
    }

}
