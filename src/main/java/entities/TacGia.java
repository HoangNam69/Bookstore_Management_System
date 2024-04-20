package entities;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.C;

import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table(name = "TacGia")
public class TacGia implements Serializable {
    @Id
    @Column(unique = true, nullable = false)
    private String maTacGia;
    @Column(unique = true, nullable = false)
    private String tenTacGia;

//    Moi quan he giua tac gia voi sanpham
    @OneToMany(mappedBy = "tacGia", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Sach> sachs;

    public TacGia(String maTacGia) {
        super();
        this.maTacGia = maTacGia;

    }

    public TacGia(String maTacGia, String tenTacGia) {
        super();
        this.maTacGia = maTacGia;
        this.tenTacGia = tenTacGia;
    }

    public String getMaTacGia() {
        return maTacGia;
    }

    public void setMaTacGia(String maTacGia) {
        this.maTacGia = maTacGia;
    }

    public String getTenTacGia() {
        return tenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia = tenTacGia;
    }

    @Override
    public String toString() {
        return "TacGia [maTacGia=" + maTacGia + ", tenTacGia=" + tenTacGia + "]";
    }

}
