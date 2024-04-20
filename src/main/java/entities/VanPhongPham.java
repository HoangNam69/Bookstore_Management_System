package entities;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@DiscriminatorValue("VanPhongPham")
@Entity
public class VanPhongPham extends SanPham implements Serializable {
    @Column(nullable = false)
    private String tenVanPhongPham;

    //    Moi quan he giua van phong pham voi loai van phong pham
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maLoaiVanPhongPham")
    private TheLoaiVanPhongPham loaiVanPhongPham;
    //    Moi quan he giua san pham voi mau sac
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maMauSac")
    private MauSac mauSac;
    //    Moi quan he giua san pham voi chat lieu
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maChatLieu")
    private ChatLieu chatLieu;

    //    Moi quan he giua san pham voi xuat xu
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maXuatXu")
    private XuatXu xuatXu;

    public VanPhongPham(String maSanPham) {
        super(maSanPham);
    }

    public VanPhongPham(String maSanPham, int soLuongTon) {
        super(maSanPham, soLuongTon);
    }

    public VanPhongPham(String maSanPham, String loaiSanPham, int soLuongTon, double trongLuong, NhaCungCap nhaCungCap,
                        long giaNhap, String ghiChu, String donViSanPham, String hinhAnh, String tenVanPhongPham,
                        TheLoaiVanPhongPham loaiVanPhongPham, MauSac mauSac, ChatLieu chatLieu, XuatXu xuatXu) {
        super(maSanPham, loaiSanPham, soLuongTon, trongLuong, nhaCungCap, giaNhap, ghiChu, donViSanPham, hinhAnh);
        this.tenVanPhongPham = tenVanPhongPham;
        this.loaiVanPhongPham = loaiVanPhongPham;
        this.mauSac = mauSac;
        this.chatLieu = chatLieu;
        this.xuatXu = xuatXu;
    }

    public String getTenVanPhongPham() {
        return tenVanPhongPham;
    }

    public void setTenVanPhongPham(String tenVanPhongPham) {
        this.tenVanPhongPham = tenVanPhongPham;
    }

    public TheLoaiVanPhongPham getLoaiVanPhongPham() {
        return loaiVanPhongPham;
    }

    public void setLoaiVanPhongPham(TheLoaiVanPhongPham loaiVanPhongPham) {
        this.loaiVanPhongPham = loaiVanPhongPham;
    }

    public MauSac getMauSac() {
        return mauSac;
    }

    public void setMauSac(MauSac mauSac) {
        this.mauSac = mauSac;
    }

    public ChatLieu getChatLieu() {
        return chatLieu;
    }

    public void setChatLieu(ChatLieu chatLieu) {
        this.chatLieu = chatLieu;
    }

    public XuatXu getXuatXu() {
        return xuatXu;
    }

    public void setXuatXu(XuatXu xuatXu) {
        this.xuatXu = xuatXu;
    }

    @Override
    public String toString() {
        return "VanPhongPham [tenVanPhongPham=" + tenVanPhongPham + ", loaiVanPhongPham=" + loaiVanPhongPham
                + ", mauSac=" + mauSac + ", chatLieu=" + chatLieu + ", xuatXu=" + xuatXu + "]";
    }

}
