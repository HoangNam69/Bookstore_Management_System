package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entities.ChatLieu;
import entities.MauSac;
import entities.NhaCungCap;
import entities.NhaXuatBan;
import entities.Sach;
import entities.SanPham;
import entities.TacGia;
import entities.TheLoaiSach;
import entities.TheLoaiVanPhongPham;
import entities.VanPhongPham;
import entities.XuatXu;
import org.apache.poi.ss.formula.functions.T;

public class SanPhamDao {
    private static Connection con;
    private static PreparedStatement ps = null;
    private static ResultSet rs;
    private String query;
    private int rsCheck;
    private ChatLieuDao chatLieuDao;
    private XuatXuDao xuatXuDao;
    private TheLoaiDao theloaiDao;
    private TacGiaDao tacgiaDao;

    public SanPhamDao() {
        DBConnection connection = DBConnection.getInstance();
        con = connection.getConnection();
    }

    /**
     * Hàm lấy danh sách tất cả sách
     *
     * @return ArrayList Sách
     * @throws Exception
     */

    public static SanPham timSanPhamTheoMa(String maSP) throws SQLException {
        String query = "Select * from SanPham where maSanPham=?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, maSP);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String maSanPham = rs.getString("maSanPham");
                    String loaiSanPham = rs.getString("loaiSanPham");
                    int soLuongTon = rs.getInt("soLuongTon");
                    double trongLuong = rs.getDouble("trongLuong");
                    NhaCungCap ncc = new NhaCungCap(rs.getString("maNCC"));
                    long giaNhap = rs.getLong("giaNhap");
                    String ghiChu = rs.getString("ghiChu");
                    String donViSanPham = rs.getString("donViSanPham");
                    String hinhAnh = rs.getString("hinhAnh");
                    String tenSach = rs.getString("tenSach");
                    TacGia tacGia = new TacGia(rs.getString("maTacGia"));
                    NhaXuatBan nhaXuatBan = new NhaXuatBan(rs.getString("maNXB"));
                    int namXuatBan = rs.getInt("namXB");
                    int soTrang = rs.getInt("soTrang");
                    TheLoaiSach theLoaiSach = new TheLoaiSach(rs.getString("maTheLoai"));
                    String tenVanPhongPham = rs.getString("tenVanPhongPham");
                    TheLoaiVanPhongPham loaiVanPhongPham = new TheLoaiVanPhongPham(rs.getString("maLoaiVanPhongPham"));
                    MauSac mauSac = new MauSac(rs.getString("maMauSac"));
                    ChatLieu chatLieu = new ChatLieu(rs.getString("maChatLieu"));
                    XuatXu xuatXu = new XuatXu(rs.getString("maXuatXu"));

                    if (loaiSanPham.equals("Sách")) {
                        return new Sach(maSanPham, loaiSanPham, soLuongTon, trongLuong, ncc, giaNhap, ghiChu, donViSanPham, hinhAnh,
                                tenSach, tacGia, nhaXuatBan, namXuatBan, soTrang, theLoaiSach);
                    } else if (loaiSanPham.equals("Văn phòng phẩm")) {
                        return new VanPhongPham(maSanPham, loaiSanPham, soLuongTon, trongLuong, ncc, giaNhap, ghiChu, donViSanPham, hinhAnh, tenVanPhongPham, loaiVanPhongPham, mauSac, chatLieu, xuatXu);
                    }
                }
            }
        }
        return null;
    }

    public static Sach getSachTheoMaSP(String maSP) throws SQLException {
        String query = "Select * from SanPham where maSanPham=?";
        ps = con.prepareStatement(query);
        ps.setString(1, maSP);
        rs = ps.executeQuery();
        while (rs.next()) {
            String maSanPham = rs.getString("maSanPham");
            String loaiSanPham = rs.getString("loaiSanPham");
            int soLuongTon = rs.getInt("soLuongTon");
            double trongLuong = rs.getDouble("trongLuong");
            NhaCungCap ncc = new NhaCungCap(rs.getString("maNCC"));
            long giaNhap = rs.getLong("giaNhap");
            String ghiChu = rs.getString("ghiChu");
            String donVi = rs.getString("soLuongTon");
            String hinhAnh = rs.getString("hinhAnh");
            String tenSach = rs.getString("tenSach");
            TacGia tacGia = new TacGia(rs.getString("maTacGia"));
            NhaXuatBan nhaXuatBan = new NhaXuatBan(rs.getString("maNXB"));
            int namXuatBan = rs.getInt("namXB");
            int soTrang = rs.getInt("soTrang");
            TheLoaiSach theLoaiSach = new TheLoaiSach(rs.getString("maTheLoai"));
            return new Sach(maSanPham, loaiSanPham, soLuongTon, trongLuong, ncc, giaNhap, ghiChu, donVi, hinhAnh, tenSach,
                    tacGia, nhaXuatBan, namXuatBan, soTrang, theLoaiSach);
        }
        return null;
    }

    public String getLoaiSanPhamTheoMa(String maSP) throws SQLException {
        String query = "Select loaiSanPham from SanPham where maSanPham = ?";
        ps = con.prepareStatement(query);
        ps.setString(1, maSP);
        rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getString("loaiSanPham");
        }
        return null;
    }

    public SanPham getSanPhamTheoMa(String masp) throws SQLException {
        String query = "Select * from SanPham where maSanPham=?";
        ps = con.prepareStatement(query);
        ps.setString(1, masp);
        rs = ps.executeQuery();
        if (rs.next()) {
            String maSanPham = rs.getString("maSanPham");
            String loaiSanPham = rs.getString("loaiSanPham");
            int soLuongTon = rs.getInt("soLuongTon");
            double trongLuong = rs.getDouble("trongLuong");
            NhaCungCap ncc = new NhaCungCap(rs.getString("maNCC"));
            long giaNhap = rs.getLong("giaNhap");
            String ghiChu = rs.getString("ghiChu");
            String donViSanPham = rs.getString("donViSanPham");
            String hinhAnh = rs.getString("hinhAnh");
            String tenSach = rs.getString("tenSach");
            TacGia tacGia = new TacGia(rs.getString("maTacGia"));
            NhaXuatBan nhaXuatBan = new NhaXuatBan(rs.getString("maNXB"));
            int namXuatBan = rs.getInt("namXB");
            int soTrang = rs.getInt("soTrang");
            TheLoaiSach theLoaiSach = new TheLoaiSach(rs.getString("maTheLoai"));
            String tenVanPhongPham = rs.getString("tenVanPhongPham");
            TheLoaiVanPhongPham loaiVanPhongPham = new TheLoaiVanPhongPham(rs.getString("maLoaiVanPhongPham"));
            MauSac mauSac = new MauSac(rs.getString("maMauSac"));
            ChatLieu chatLieu = new ChatLieu(rs.getString("maChatLieu"));
            XuatXu xuatXu = new XuatXu(rs.getString("maXuatXu"));

            if (loaiSanPham.equals("Sách")) {
                return new Sach(maSanPham, loaiSanPham, soLuongTon, trongLuong, ncc, giaNhap, ghiChu, donViSanPham, hinhAnh, tenSach, tacGia, nhaXuatBan, namXuatBan, soTrang, theLoaiSach);
            } else {
                return new VanPhongPham(maSanPham, loaiSanPham, soLuongTon, trongLuong, ncc, giaNhap, ghiChu, donViSanPham, hinhAnh, tenVanPhongPham, loaiVanPhongPham, mauSac, chatLieu, xuatXu);
            }
        }
        return null;
    }


    public VanPhongPham getVPPTheoMaSP(String maSP) throws SQLException {
        String query = "SELECT * FROM SanPham WHERE maSanPham=?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, maSP);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String maSanPham = rs.getString("maSanPham");
                    String loaiSanPham = rs.getString("loaiSanPham");
                    int soLuongTon = rs.getInt("soLuongTon");
                    double trongLuong = rs.getDouble("trongLuong");
                    NhaCungCap ncc = new NhaCungCap(rs.getString("maNCC"));
                    long giaNhap = rs.getLong("giaNhap");
                    String ghiChu = rs.getString("ghiChu");
                    String donVi = rs.getString("soLuongTon");
                    String hinhAnh = rs.getString("hinhAnh");
                    String tenVanPhongPham = rs.getString("tenVanPhongPham");
                    TheLoaiVanPhongPham loaiVanPhongPham = new TheLoaiVanPhongPham(rs.getString("maLoaiVanPhongPham"));
                    MauSac mauSac = new MauSac(rs.getString("maMauSac"));
                    ChatLieu chatLieu = new ChatLieu(rs.getString("maChatLieu"));
                    XuatXu xuatXu = new XuatXu(rs.getString("maXuatXu"));
                    TheLoaiSach theLoaiSach = new TheLoaiSach(rs.getString("maTheLoai"));
                    return new VanPhongPham(maSanPham, loaiSanPham, soLuongTon, trongLuong, ncc, giaNhap, ghiChu, donVi, hinhAnh,
                            tenVanPhongPham, loaiVanPhongPham, mauSac, chatLieu, xuatXu);
                }
            }
        }
        return null;
    }

    public ArrayList<Sach> getListSach(String maSach, String tenSP, String maTheLoai, Long giaTu, Long giaDen,
                                       String maTacGia, String maNXB, String maNCC, boolean hetHang) throws SQLException {
        ArrayList<Sach> listSach = new ArrayList<>();
        String query = "SELECT SanPham.maSanPham, SanPham.soLuongTon, SanPham.loaiSanPham, NhaCungCap.maNCC, NhaCungCap.tenNCC, " +
                "SanPham.giaNhap, SanPham.ghiChu, SanPham.trongLuong, SanPham.donViSanPham, SanPham.hinhAnh, SanPham.tenSach, " +
                "TacGia.maTacGia, TacGia.tenTacGia, NhaXuatBan.maNXB, NhaXuatBan.tenNXB, SanPham.namXB, SanPham.soTrang, " +
                "TheLoaiSach.maTheLoai, TheLoaiSach.tenTheLoai " +
                "FROM SanPham " +
                "INNER JOIN NhaCungCap ON SanPham.maNCC = NhaCungCap.maNCC " +
                "INNER JOIN NhaXuatBan ON SanPham.maNXB = NhaXuatBan.maNXB " +
                "INNER JOIN TacGia ON SanPham.maTacGia = TacGia.maTacGia " +
                "INNER JOIN TheLoaiSach ON SanPham.maTheLoai = TheLoaiSach.maTheLoai " +
                "WHERE maSanPham LIKE ? AND tenSach LIKE ? AND SanPham.maTheLoai LIKE ? " +
                "AND SanPham.giaNhap > ? AND SanPham.giaNhap < ? AND SanPham.maTacGia LIKE ? " +
                "AND SanPham.maNXB LIKE ? AND SanPham.maNCC LIKE ?";
        if (hetHang) {
            query += " AND soLuongTon = 0";
        }
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, "%" + maSach + "%");
            ps.setString(2, "%" + tenSP + "%");
            ps.setString(3, "%" + maTheLoai + "%");
            ps.setLong(4, giaTu);
            ps.setLong(5, giaDen);
            ps.setString(6, "%" + maTacGia + "%");
            ps.setString(7, "%" + maNXB + "%");
            ps.setString(8, "%" + maNCC + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Sach s = new Sach(rs.getString("maSanPham"), rs.getString("loaiSanPham"), rs.getInt("soLuongTon"),
                            rs.getDouble("trongLuong"), new NhaCungCap(rs.getString("maNCC"), rs.getString("tenNCC")),
                            rs.getLong("giaNhap"), rs.getString("ghiChu"), rs.getString("donViSanPham"),
                            rs.getString("hinhAnh"), rs.getString("tenSach"),
                            new TacGia(rs.getString("maTacGia"), rs.getString("tenTacGia")),
                            new NhaXuatBan(rs.getString("maNXB"), rs.getString("tenNXB")), rs.getInt("namXB"),
                            rs.getInt("soTrang"), new TheLoaiSach(rs.getString("maTheLoai"), rs.getString("tenTheLoai")));
                    listSach.add(s);
                }
            }
        }
        return listSach;
    }


//    public ArrayList<VanPhongPham> getListVanPhongPham(String maVPP, String tenVPP, String theLoaiVPP, Long giaTu,
//                                                       Long giaDen, String maChatLieu, String maXuatXu, String maNCC, boolean hetHang) throws Exception {
//        ArrayList<VanPhongPham> list = new ArrayList<>();
//        query = "SELECT SanPham.maSanPham, SanPham.loaiSanPham, SanPham.soLuongTon, SanPham.trongLuong, "
//                + "	NhaCungCap.maNCC, NhaCungCap.tenNCC, SanPham.giaNhap, SanPham.ghiChu, SanPham.donViSanPham, SanPham.hinhAnh, "
//                + "	SanPham.tenVanPhongPham, LoaiVanPhongPham.maLoaiVanPhongPham, "
//                + " LoaiVanPhongPham.tenTheLoai, MauSac.maMauSac, MauSac.tenMau, ChatLieu.maChatLieu, ChatLieu.tenChatLieu, XuatXu.maXuatXu, XuatXu.tenXuatXu "
//                + "	FROM SanPham INNER JOIN"
//                + "	LoaiVanPhongPham ON SanPham.maLoaiVanPhongPham = LoaiVanPhongPham.maLoaiVanPhongPham INNER JOIN "
//                + "	ChatLieu ON SanPham.maChatLieu = ChatLieu.maChatLieu INNER JOIN "
//                + " MauSac ON SanPham.maMauSac = MauSac.maMauSac INNER JOIN "
//                + "	 XuatXu ON SanPham.maXuatXu = XuatXu.maXuatXu INNER JOIN "
//                + "	NhaCungCap ON SanPham.maNCC = NhaCungCap.maNCC\r\n" + "	where maSanPham like '%" + maVPP
//                + "%' and tenVanPhongPham like N'%" + tenVPP + "%' and SanPham.maLoaiVanPhongPham like '%" + theLoaiVPP
//                + "%' \r\n" + "	and SanPham.giaNhap > ? and SanPham.giaNhap < ? and SanPham.maChatLieu like '%"
//                + maChatLieu + "%' \r\n" + "	and SanPham.maXuatXu like '%" + maXuatXu
//                + "%' and SanPham.maNCC like '%" + maNCC + "%' ";
//        if (hetHang) {
//            query = query + " and soLuongTon = 0";
//        }
//        ps = con.prepareStatement(query);
//        ps.setLong(1, giaTu);
//        ps.setLong(2, giaDen);
//        rs = ps.executeQuery();
//        while (rs.next()) {
//            VanPhongPham vanPhongPham = new VanPhongPham(rs.getString("maSanPham"), rs.getString("loaiSanPham"),
//                    rs.getInt("soLuongTon"), rs.getDouble("trongLuong"),
//                    new NhaCungCap(rs.getString("maNCC"), rs.getString("tenNCC")), rs.getLong("giaNhap"),
//                    rs.getString("ghiChu"), rs.getString("donViSanPham"), rs.getString("hinhAnh"),
//                    rs.getString("tenVanPhongPham"),
//                    new TheLoaiVanPhongPham(rs.getString("maLoaiVanPhongPham"), rs.getString("tenTheLoai")),
//                    new MauSac(rs.getString("maMauSac"), rs.getString("tenMau")),
//                    new ChatLieu(rs.getString("maChatLieu"), rs.getString("tenChatLieu")),
//                    new XuatXu(rs.getString("maXuatXu"), rs.getString("tenXuatXu")));
//            list.add(vanPhongPham);
//        }
//        return list;
//    }

    public ArrayList<VanPhongPham> getListVanPhongPham(String maVPP, String tenVPP, String theLoaiVPP, Long giaTu,
                                                       Long giaDen, String maChatLieu, String maXuatXu, String maNCC, boolean hetHang) throws Exception {
        ArrayList<VanPhongPham> list = new ArrayList<>();
        String query = "SELECT SanPham.maSanPham, SanPham.loaiSanPham, SanPham.soLuongTon, SanPham.trongLuong, " +
                "NhaCungCap.maNCC, NhaCungCap.tenNCC, SanPham.giaNhap, SanPham.ghiChu, SanPham.donViSanPham, SanPham.hinhAnh, " +
                "SanPham.tenVanPhongPham, LoaiVanPhongPham.maLoaiVanPhongPham, LoaiVanPhongPham.tenTheLoai, " +
                "MauSac.maMauSac, MauSac.tenMau, ChatLieu.maChatLieu, ChatLieu.tenChatLieu, XuatXu.maXuatXu, XuatXu.tenXuatXu " +
                "FROM SanPham " +
                "INNER JOIN LoaiVanPhongPham ON SanPham.maLoaiVanPhongPham = LoaiVanPhongPham.maLoaiVanPhongPham " +
                "INNER JOIN ChatLieu ON SanPham.maChatLieu = ChatLieu.maChatLieu " +
                "INNER JOIN MauSac ON SanPham.maMauSac = MauSac.maMauSac " +
                "INNER JOIN XuatXu ON SanPham.maXuatXu = XuatXu.maXuatXu " +
                "INNER JOIN NhaCungCap ON SanPham.maNCC = NhaCungCap.maNCC " +
                "WHERE maSanPham LIKE ? AND tenVanPhongPham LIKE ? AND SanPham.maLoaiVanPhongPham LIKE ? " +
                "AND SanPham.giaNhap > ? AND SanPham.giaNhap < ? AND SanPham.maChatLieu LIKE ? " +
                "AND SanPham.maXuatXu LIKE ? AND SanPham.maNCC LIKE ?";
        if (hetHang) {
            query += " AND soLuongTon = 0";
        }
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, "%" + maVPP + "%");
            ps.setString(2, "%" + tenVPP + "%");
            ps.setString(3, "%" + theLoaiVPP + "%");
            ps.setLong(4, giaTu);
            ps.setLong(5, giaDen);
            ps.setString(6, "%" + maChatLieu + "%");
            ps.setString(7, "%" + maXuatXu + "%");
            ps.setString(8, "%" + maNCC + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    VanPhongPham vanPhongPham = new VanPhongPham(rs.getString("maSanPham"), rs.getString("loaiSanPham"),
                            rs.getInt("soLuongTon"), rs.getDouble("trongLuong"),
                            new NhaCungCap(rs.getString("maNCC"), rs.getString("tenNCC")), rs.getLong("giaNhap"),
                            rs.getString("ghiChu"), rs.getString("donViSanPham"), rs.getString("hinhAnh"),
                            rs.getString("tenVanPhongPham"),
                            new TheLoaiVanPhongPham(rs.getString("maLoaiVanPhongPham"), rs.getString("tenTheLoai")),
                            new MauSac(rs.getString("maMauSac"), rs.getString("tenMau")),
                            new ChatLieu(rs.getString("maChatLieu"), rs.getString("tenChatLieu")),
                            new XuatXu(rs.getString("maXuatXu"), rs.getString("tenXuatXu")));
                    list.add(vanPhongPham);
                }
            }
        }
        return list;
    }


    //    public boolean themSanPham(SanPham sanPham) throws Exception {
//        Sach s = new Sach();
//        VanPhongPham v = new VanPhongPham();
//        if (sanPham instanceof Sach) {
//            s = (Sach) sanPham;
//            query = "INSERT [dbo].[SanPham] VALUES " + "(?, ?, ?, ?, ?, ?, ?, ?," + "?, ?, ?, ?, ?, "
//                    + "?, ?, null, null, null, null, null)";
//            ps = con.prepareStatement(query);
//            ps.setString(1, s.getMaSanPham());
//            ps.setString(2, s.getLoaiSanPham());
//            ps.setInt(3, s.getSoLuongTon());
//            ps.setDouble(4, s.getTrongLuong());
//            ps.setString(5, s.getNhaCungCap().getMaNCC());
//            ps.setLong(6, s.getGiaNhap());
//            ps.setString(7, s.getGhiChu());
//            ps.setString(8, s.getDonViSanPham());
//            ps.setString(9, s.getHinhAnh());
//            ps.setString(10, s.getTenSach());
//            ps.setString(11, s.getTacGia().getMaTacGia());
//            ps.setString(12, s.getNhaXuatBan().getMaNXB());
//            ps.setInt(13, s.getNamXuatBan());
//            ps.setInt(14, s.getSoTrang());
//            ps.setString(15, s.getTheLoaiSach().getMaLoai());
//        } else {
//            v = (VanPhongPham) sanPham;
//            query = "INSERT [dbo].[SanPham] VALUES (?,?,?,?,?,?,?,?,?, "
//                    + "null, null, null, null, null, null, ?,?,?,?,?)";
//            ps = con.prepareStatement(query);
//            ps.setString(1, v.getMaSanPham());
//            ps.setString(2, v.getLoaiSanPham());
//            ps.setInt(3, v.getSoLuongTon());
//            ps.setDouble(4, v.getTrongLuong());
//            ps.setString(5, v.getNhaCungCap().getMaNCC());
//            ps.setLong(6, v.getGiaNhap());
//            ps.setString(7, v.getGhiChu());
//            ps.setString(8, v.getDonViSanPham());
//            ps.setString(9, v.getHinhAnh());
//            ps.setString(10, v.getTenVanPhongPham());
//            ps.setString(11, v.getLoaiVanPhongPham().getMaLoai());
//            ps.setString(12, v.getMauSac().getMaMau());
//            ps.setString(13, v.getChatLieu().getMaChatLieu());
//            ps.setString(14, v.getXuatXu().getMaXuatXu());
//        }
//        rsCheck = ps.executeUpdate();
//        if (rsCheck == 0) {
//            return false;
//        }
//        return true;
//    }
    public boolean themSanPham(SanPham sanPham) {
        String query;
        int rowsAffected = 0;

        try {
            if (sanPham instanceof Sach) {
                Sach s = (Sach) sanPham;
                query = "INSERT INTO SanPham VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NULL, NULL, NULL, NULL, NULL)";
                try (PreparedStatement ps = con.prepareStatement(query)) {
                    ps.setString(1, s.getMaSanPham());
                    ps.setString(2, s.getLoaiSanPham());
                    ps.setInt(3, s.getSoLuongTon());
                    ps.setDouble(4, s.getTrongLuong());
                    ps.setString(5, s.getNhaCungCap().getMaNCC());
                    ps.setLong(6, s.getGiaNhap());
                    ps.setString(7, s.getGhiChu());
                    ps.setString(8, s.getDonViSanPham());
                    ps.setString(9, s.getHinhAnh());
                    ps.setString(10, s.getTenSach());
                    ps.setString(11, s.getTacGia().getMaTacGia());
                    ps.setString(12, s.getNhaXuatBan().getMaNXB());
                    ps.setInt(13, s.getNamXuatBan());
                    ps.setInt(14, s.getSoTrang());
                    ps.setString(15, s.getTheLoaiSach().getMaLoai());
                    rowsAffected = ps.executeUpdate();
                }
            } else if (sanPham instanceof VanPhongPham) {
                VanPhongPham v = (VanPhongPham) sanPham;
                query = "INSERT INTO SanPham VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, NULL, NULL, NULL, NULL, NULL, NULL, ?, ?, ?, ?)";
                try (PreparedStatement ps = con.prepareStatement(query)) {
                    ps.setString(1, v.getMaSanPham());
                    ps.setString(2, v.getLoaiSanPham());
                    ps.setInt(3, v.getSoLuongTon());
                    ps.setDouble(4, v.getTrongLuong());
                    ps.setString(5, v.getNhaCungCap().getMaNCC());
                    ps.setLong(6, v.getGiaNhap());
                    ps.setString(7, v.getGhiChu());
                    ps.setString(8, v.getDonViSanPham());
                    ps.setString(9, v.getHinhAnh());
                    ps.setString(10, v.getTenVanPhongPham());
                    ps.setString(11, v.getLoaiVanPhongPham().getMaLoai());
                    ps.setString(12, v.getMauSac().getMaMau());
                    ps.setString(13, v.getChatLieu().getMaChatLieu());
                    ps.setString(14, v.getXuatXu().getMaXuatXu());
                    rowsAffected = ps.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Trả về false nếu có lỗi xảy ra
        }

        return rowsAffected > 0;
    }


    //    public boolean capNhatSanPham(String maSP, SanPham temp) throws Exception {
//        Sach s = new Sach();
//        VanPhongPham v = new VanPhongPham();
//        if (temp instanceof Sach) {
//            s = (Sach) temp;
//            query = "update SanPham\r\n" + "set maTheLoai = ?, soLuongTon = ? , trongLuong = ? ,"
//                    + " maNCC = ? , giaNhap = ? , ghiChu = ? , donViSanPham = ? , hinhAnh = ? ,"
//                    + "tenSach = ? , maTacGia = ? ,maNXB = ? , namXB = ? , soTrang = ? " + "where maSanPham like ?";
//            ps = con.prepareStatement(query);
//            ps.setString(14, s.getMaSanPham());
//            ps.setInt(2, s.getSoLuongTon());
//            ps.setDouble(3, s.getTrongLuong());
//            ps.setString(4, s.getNhaCungCap().getMaNCC());
//            ps.setLong(5, s.getGiaNhap());
//            ps.setString(6, s.getGhiChu());
//            ps.setString(7, s.getDonViSanPham());
//            ps.setString(8, s.getHinhAnh());
//            ps.setString(9, s.getTenSach());
//            ps.setString(10, s.getTacGia().getMaTacGia());
//            ps.setString(11, s.getNhaXuatBan().getMaNXB());
//            ps.setInt(12, s.getNamXuatBan());
//            ps.setInt(13, s.getSoTrang());
//            ps.setString(1, s.getTheLoaiSach().getMaLoai());
//        } else {
//            v = (VanPhongPham) temp;
//            query = "update SanPham\r\n" + "set maLoaiVanPhongPham = ?, soLuongTon = ? , trongLuong = ? ,"
//                    + " maNCC = ? , giaNhap = ? , ghiChu = ? , donViSanPham = ? , hinhAnh = ? ,"
//                    + "tenVanPhongPham = ? , maMauSac = ? ,maChatLieu = ? , maXuatXu = ? " + "where maSanPham like ?";
//            ps = con.prepareStatement(query);
//            ps.setString(13, v.getMaSanPham());
//            ps.setInt(2, v.getSoLuongTon());
//            ps.setDouble(3, v.getTrongLuong());
//            ps.setString(4, v.getNhaCungCap().getMaNCC());
//            ps.setLong(5, v.getGiaNhap());
//            ps.setString(6, v.getGhiChu());
//            ps.setString(7, v.getDonViSanPham());
//            ps.setString(9, v.getHinhAnh());
//            ps.setString(9, v.getTenVanPhongPham());
//            ps.setString(1, v.getLoaiVanPhongPham().getMaLoai());
//            ps.setString(10, v.getMauSac().getMaMau());
//            ps.setString(11, v.getChatLieu().getMaChatLieu());
//            ps.setString(12, v.getXuatXu().getMaXuatXu());
//        }
//        rsCheck = ps.executeUpdate();
//        if (rsCheck == 0) {
//            return false;
//        }
//        return true;
//    }
    public boolean capNhatSanPham(String maSP, SanPham temp) throws Exception {
        try {
            String query;
            PreparedStatement ps;

            if (temp instanceof Sach) {
                Sach s = (Sach) temp;
                // Tạo câu lệnh SQL để cập nhật thông tin sách
                query = "UPDATE SanPham SET maTheLoai = ?, soLuongTon = ?, trongLuong = ?, maNCC = ?, giaNhap = ?, ghiChu = ?, donViSanPham = ?, hinhAnh = ?, tenSach = ?, maTacGia = ?, maNXB = ?, namXB = ?, soTrang = ? WHERE maSanPham LIKE ?";
                // Chuẩn bị câu lệnh SQL
                ps = con.prepareStatement(query);
                // Thiết lập các giá trị cho các tham số trong câu lệnh SQL
                ps.setString(1, s.getTheLoaiSach().getMaLoai());
                ps.setInt(2, s.getSoLuongTon());
                ps.setDouble(3, s.getTrongLuong());
                ps.setString(4, s.getNhaCungCap().getMaNCC());
                ps.setLong(5, s.getGiaNhap());
                ps.setString(6, s.getGhiChu());
                ps.setString(7, s.getDonViSanPham());
                ps.setString(8, s.getHinhAnh());
                ps.setString(9, s.getTenSach());
                ps.setString(10, s.getTacGia().getMaTacGia());
                ps.setString(11, s.getNhaXuatBan().getMaNXB());
                ps.setInt(12, s.getNamXuatBan());
                ps.setInt(13, s.getSoTrang());
                ps.setString(14, maSP);
            } else if (temp instanceof VanPhongPham) {
                VanPhongPham v = (VanPhongPham) temp;
                // Tạo câu lệnh SQL để cập nhật thông tin văn phòng phẩm
                query = "UPDATE SanPham SET maLoaiVanPhongPham = ?, soLuongTon = ?, trongLuong = ?, maNCC = ?, giaNhap = ?, ghiChu = ?, donViSanPham = ?, hinhAnh = ?, tenVanPhongPham = ?, maMauSac = ?, maChatLieu = ?, maXuatXu = ? WHERE maSanPham LIKE ?";
                // Chuẩn bị câu lệnh SQL
                ps = con.prepareStatement(query);
                // Thiết lập các giá trị cho các tham số trong câu lệnh SQL
                ps.setString(1, v.getLoaiVanPhongPham().getMaLoai());
                ps.setInt(2, v.getSoLuongTon());
                ps.setDouble(3, v.getTrongLuong());
                ps.setString(4, v.getNhaCungCap().getMaNCC());
                ps.setLong(5, v.getGiaNhap());
                ps.setString(6, v.getGhiChu());
                ps.setString(7, v.getDonViSanPham());
                ps.setString(8, v.getHinhAnh());
                ps.setString(9, v.getTenVanPhongPham());
                ps.setString(10, v.getMauSac().getMaMau());
                ps.setString(11, v.getChatLieu().getMaChatLieu());
                ps.setString(12, v.getXuatXu().getMaXuatXu());
                ps.setString(13, maSP);
            } else {
                // Trả về false nếu không thể xác định loại sản phẩm
                return false;
            }

            // Thực thi câu lệnh SQL để cập nhật sản phẩm
            int rowsAffected = ps.executeUpdate();
            // Trả về true nếu có bản ghi sản phẩm đã được cập nhật
            return rowsAffected > 0;
        } catch (SQLException e) {
            // Xử lý ngoại lệ nếu có lỗi xảy ra và in ra thông tin lỗi
            e.printStackTrace();
        }

        // Trả về false nếu có lỗi xảy ra khi thực hiện cập nhật
        return false;
    }

    public boolean xoaSanPham(String maSP) {

        return false;
    }

    public String getMaSPMax() {
        try {
            // Tạo câu lệnh SQL để lấy mã sản phẩm lớn nhất từ bảng SanPham
            String query = "SELECT MAX(maSanPham) AS maSP FROM SanPham";

            // Chuẩn bị câu lệnh SQL
            PreparedStatement ps = con.prepareStatement(query);

            // Thực thi câu lệnh SQL và lấy kết quả trả về
            ResultSet rs = ps.executeQuery();

            // Biến để lưu trữ mã sản phẩm lớn nhất
            String maxMaSP = null;

            // Duyệt qua kết quả trả về từ câu lệnh SQL
            while (rs.next()) {
                // Lấy giá trị của cột "maSP" từ kết quả trả về
                maxMaSP = rs.getString("maSP");
            }

            // Trả về mã sản phẩm lớn nhất
            return maxMaSP;
        } catch (SQLException e) {
            // Xử lý ngoại lệ nếu có lỗi xảy ra và in ra thông tin lỗi
            e.printStackTrace();
        }

        // Trả về null nếu có lỗi xảy ra hoặc không có mã sản phẩm nào được tìm thấy
        return null;
    }


//    public Sach timSanPhamTheoMaSach(String maSach) throws Exception {
//        query = "SELECT SanPham.maSanPham, SanPham.soLuongTon,SanPham.loaiSanPham, NhaCungCap.maNCC, NhaCungCap.tenNCC, SanPham.giaNhap, SanPham.ghiChu, SanPham.trongLuong, SanPham.donViSanPham, SanPham.hinhAnh, SanPham.tenSach, TacGia.maTacGia, \r\n"
//                + "                  TacGia.tenTacGia, NhaXuatBan.maNXB, NhaXuatBan.tenNXB, SanPham.namXB, SanPham.soTrang, TheLoaiSach.maTheLoai, TheLoaiSach.tenTheLoai\r\n"
//                + "FROM     SanPham INNER JOIN\r\n"
//                + "                  NhaCungCap ON SanPham.maNCC = NhaCungCap.maNCC INNER JOIN\r\n"
//                + "                  NhaXuatBan ON SanPham.maNXB = NhaXuatBan.maNXB INNER JOIN\r\n"
//                + "                  TacGia ON SanPham.maTacGia = TacGia.maTacGia INNER JOIN\r\n"
//                + "                  TheLoaiSach ON SanPham.maTheLoai = TheLoaiSach.maTheLoai"
//                + " where SanPham.maSanPham like ?";
//        ps = con.prepareStatement(query);
//        ps.setString(1, maSach);
//        rs = ps.executeQuery();
//        while (rs.next()) {
//            Sach s = new Sach(rs.getString("maSanPham"), rs.getString("loaiSanPham"), rs.getInt("soLuongTon"),
//                    rs.getDouble("trongLuong"), new NhaCungCap(rs.getString("maNCC"), rs.getString("tenNCC")),
//                    rs.getLong("giaNhap"), rs.getString("ghiChu"), rs.getString("donViSanPham"),
//                    rs.getString("hinhAnh"), rs.getString("tenSach"),
//                    new TacGia(rs.getString("maTacGia"), rs.getString("tenTacGia")),
//                    new NhaXuatBan(rs.getString("maNXB"), rs.getString("tenNXB")), rs.getInt("namXB"),
//                    rs.getInt("soTrang"), new TheLoaiSach(rs.getString("maTheLoai"), rs.getString("tenTheLoai")));
//            return s;
//        }
//        return null;
//    }
    public Sach timSanPhamTheoMaSach(String maSach) {
        try {
            // Tạo câu lệnh SQL để tìm sách theo mã sách
            String query = "SELECT SanPham.maSanPham, SanPham.soLuongTon, SanPham.loaiSanPham, NhaCungCap.maNCC, NhaCungCap.tenNCC, "
                    + "SanPham.giaNhap, SanPham.ghiChu, SanPham.trongLuong, SanPham.donViSanPham, SanPham.hinhAnh, "
                    + "SanPham.tenSach, TacGia.maTacGia, TacGia.tenTacGia, NhaXuatBan.maNXB, NhaXuatBan.tenNXB, "
                    + "SanPham.namXB, SanPham.soTrang, TheLoaiSach.maTheLoai, TheLoaiSach.tenTheLoai "
                    + "FROM SanPham "
                    + "INNER JOIN NhaCungCap ON SanPham.maNCC = NhaCungCap.maNCC "
                    + "INNER JOIN NhaXuatBan ON SanPham.maNXB = NhaXuatBan.maNXB "
                    + "INNER JOIN TacGia ON SanPham.maTacGia = TacGia.maTacGia "
                    + "INNER JOIN TheLoaiSach ON SanPham.maTheLoai = TheLoaiSach.maTheLoai "
                    + "WHERE SanPham.maSanPham LIKE ?";

            // Chuẩn bị câu lệnh SQL
            PreparedStatement ps = con.prepareStatement(query);

            // Thiết lập tham số cho câu lệnh SQL
            ps.setString(1, maSach);

            // Thực thi câu lệnh SQL và lấy kết quả trả về
            ResultSet rs = ps.executeQuery();

            // Duyệt qua kết quả trả về từ câu lệnh SQL
            while (rs.next()) {
                // Tạo đối tượng sách từ kết quả trả về
                Sach s = new Sach(rs.getString("maSanPham"), rs.getString("loaiSanPham"), rs.getInt("soLuongTon"),
                        rs.getDouble("trongLuong"), new NhaCungCap(rs.getString("maNCC"), rs.getString("tenNCC")),
                        rs.getLong("giaNhap"), rs.getString("ghiChu"), rs.getString("donViSanPham"),
                        rs.getString("hinhAnh"), rs.getString("tenSach"),
                        new TacGia(rs.getString("maTacGia"), rs.getString("tenTacGia")),
                        new NhaXuatBan(rs.getString("maNXB"), rs.getString("tenNXB")), rs.getInt("namXB"),
                        rs.getInt("soTrang"), new TheLoaiSach(rs.getString("maTheLoai"), rs.getString("tenTheLoai")));

                // Trả về sách đã tìm được
                return s;
            }
        } catch (SQLException e) {
            // Xử lý ngoại lệ nếu có lỗi xảy ra và in ra thông tin lỗi
            e.printStackTrace();
        }

        // Trả về null nếu có lỗi xảy ra hoặc không tìm thấy sách
        return null;
    }


    public VanPhongPham timSanPhamTheoMaVPP(String maVPP) {
        try {
            // Tạo câu lệnh SQL để tìm văn phòng phẩm theo mã VPP
            String query = "SELECT SanPham.maSanPham, SanPham.loaiSanPham, SanPham.soLuongTon, SanPham.trongLuong, "
                    + "NhaCungCap.maNCC, NhaCungCap.tenNCC, SanPham.giaNhap, SanPham.ghiChu, SanPham.donViSanPham, SanPham.hinhAnh, "
                    + "SanPham.tenVanPhongPham, LoaiVanPhongPham.maLoaiVanPhongPham, LoaiVanPhongPham.tenTheLoai, "
                    + "MauSac.maMauSac, MauSac.tenMau, ChatLieu.maChatLieu, ChatLieu.tenChatLieu, XuatXu.maXuatXu, XuatXu.tenXuatXu "
                    + "FROM SanPham "
                    + "INNER JOIN LoaiVanPhongPham ON SanPham.maLoaiVanPhongPham = LoaiVanPhongPham.maLoaiVanPhongPham "
                    + "INNER JOIN ChatLieu ON SanPham.maChatLieu = ChatLieu.maChatLieu "
                    + "INNER JOIN MauSac ON SanPham.maMauSac = MauSac.maMauSac "
                    + "INNER JOIN XuatXu ON SanPham.maXuatXu = XuatXu.maXuatXu "
                    + "INNER JOIN NhaCungCap ON SanPham.maNCC = NhaCungCap.maNCC "
                    + "WHERE SanPham.maSanPham LIKE ?";

            // Chuẩn bị câu lệnh SQL
            PreparedStatement ps = con.prepareStatement(query);

            // Thiết lập tham số cho câu lệnh SQL
            ps.setString(1, maVPP);

            // Thực thi câu lệnh SQL và lấy kết quả trả về
            ResultSet rs = ps.executeQuery();

            // Duyệt qua kết quả trả về từ câu lệnh SQL
            while (rs.next()) {
                // Tạo đối tượng văn phòng phẩm từ kết quả trả về
                VanPhongPham vanPhongPham = new VanPhongPham(rs.getString("maSanPham"), rs.getString("loaiSanPham"),
                        rs.getInt("soLuongTon"), rs.getDouble("trongLuong"),
                        new NhaCungCap(rs.getString("maNCC"), rs.getString("tenNCC")), rs.getLong("giaNhap"),
                        rs.getString("ghiChu"), rs.getString("donViSanPham"), rs.getString("hinhAnh"),
                        rs.getString("tenVanPhongPham"),
                        new TheLoaiVanPhongPham(rs.getString("maLoaiVanPhongPham"), rs.getString("tenTheLoai")),
                        new MauSac(rs.getString("maMauSac"), rs.getString("tenMau")),
                        new ChatLieu(rs.getString("maChatLieu"), rs.getString("tenChatLieu")),
                        new XuatXu(rs.getString("maXuatXu"), rs.getString("tenXuatXu")));

                // Trả về văn phòng phẩm đã tìm được
                return vanPhongPham;
            }
        } catch (SQLException e) {
            // In ra thông báo lỗi nếu có lỗi xảy ra trong quá trình thực thi câu lệnh SQL
            e.printStackTrace();
        }

        // Trả về null nếu không tìm thấy văn phòng phẩm hoặc có lỗi xảy ra
        return null;
    }


    public List<Sach> getAllSach() {
        List<Sach> dsS = new ArrayList<>();
        theloaiDao = new TheLoaiDao();
        tacgiaDao = new TacGiaDao();
        try {
            String query = "SELECT * FROM SanPham WHERE loaiSanPham=?";
            ps = con.prepareStatement(query);
            ps.setString(1, "Sách");
            rs = ps.executeQuery();
            while (rs.next()) {
                String maSanPham = rs.getString("maSanPham");
                String loaiSanPham = rs.getString("loaiSanPham");
                int soLuongTon = rs.getInt("soLuongTon");
                double trongLuong = rs.getDouble("trongLuong");

                NhaCungCap ncc = new NhaCungCap(rs.getString("maNCC"));
                long giaNhap = rs.getLong("giaNhap");
                String ghiChu = rs.getString("ghiChu");
                String donVi = rs.getString("donViSanPham");
                String hinhAnh = rs.getString("hinhAnh");
                String tenSach = rs.getString("tenSach");

                TacGia tacGia = null;
                String maTacGia = rs.getString("maTacGia");
                if (maTacGia != null && !maTacGia.isEmpty()) {
                    List<TacGia> tacGiaList = tacgiaDao.getTacGia(maTacGia);
                    if (!tacGiaList.isEmpty()) {
                        tacGia = tacGiaList.get(0);
                    }
                }

                NhaXuatBan nhaXuatBan = new NhaXuatBan(rs.getString("maNXB"));
                int namXuatBan = rs.getInt("namXB");
                int soTrang = rs.getInt("soTrang");
                TheLoaiSach theLoaiSach = theloaiDao.getSachTheoTheLoai(rs.getString("maTheLoai")).get(0);

                Sach s = new Sach(maSanPham, loaiSanPham, soLuongTon, trongLuong, ncc, giaNhap, ghiChu, donVi, hinhAnh,
                        tenSach, tacGia, nhaXuatBan, namXuatBan, soTrang, theLoaiSach);
                dsS.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsS;
    }


    public List<VanPhongPham> getAllVPP() {
        List<VanPhongPham> dsVPP = new ArrayList<>();
        chatLieuDao = new ChatLieuDao();
        xuatXuDao = new XuatXuDao();
        try {
            String query = "SELECT * FROM SanPham WHERE loaiSanPham=?";
            ps = con.prepareStatement(query);
            ps.setString(1, "Văn phòng phẩm");
            rs = ps.executeQuery();
            while (rs.next()) {
                String maSanPham = rs.getString("maSanPham");
                String loaiSanPham = rs.getString("loaiSanPham");
                int soLuongTon = rs.getInt("soLuongTon");
                double trongLuong = rs.getDouble("trongLuong");
                NhaCungCap ncc = new NhaCungCap(rs.getString("maNCC"));
                long giaNhap = rs.getLong("giaNhap");
                String ghiChu = rs.getString("ghiChu");
                String donVi = rs.getString("donViSanPham");
                String hinhAnh = rs.getString("hinhAnh");
                String tenVPP = rs.getString("tenVanPhongPham");
                TheLoaiVanPhongPham theLoaiVPP = new TheLoaiVanPhongPham(rs.getString("maLoaiVanPhongPham"));
                MauSac mauSac = new MauSac(rs.getString("maMauSac"));
                ChatLieu chatLieu = chatLieuDao.getChatLieu(rs.getString("maChatLieu")).get(0);
                XuatXu xuatXu = xuatXuDao.getXuatXu(rs.getString("maXuatXu")).get(0);
                VanPhongPham vpp = new VanPhongPham(maSanPham, loaiSanPham, soLuongTon, trongLuong, ncc, giaNhap,
                        ghiChu, donVi, hinhAnh, tenVPP, theLoaiVPP, mauSac, chatLieu, xuatXu);
                dsVPP.add(vpp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsVPP;
    }

    public Sach getSachTheoTen(String ten) {
        Sach sach = null;
        try {
            String query = "SELECT * FROM SanPham WHERE tenSach=?";
            ps = con.prepareStatement(query);
            ps.setString(1, ten);
            rs = ps.executeQuery();
            if (rs.next()) {
                String maSanPham = rs.getString("maSanPham");
                String loaiSanPham = rs.getString("loaiSanPham");
                int soLuongTon = rs.getInt("soLuongTon");
                double trongLuong = rs.getDouble("trongLuong");
                NhaCungCap ncc = new NhaCungCap(rs.getString("maNCC"));
                long giaNhap = rs.getLong("giaNhap");
                String ghiChu = rs.getString("ghiChu");
                String donVi = rs.getString("donViSanPham");
                String hinhAnh = rs.getString("hinhAnh");
                TacGia tacGia = new TacGia(rs.getString("maTacGia"));
                NhaXuatBan nhaXuatBan = new NhaXuatBan(rs.getString("maNXB"));
                int namXuatBan = rs.getInt("namXB");
                int soTrang = rs.getInt("soTrang");
                TheLoaiSach theLoaiSach = new TheLoaiSach(rs.getString("maTheLoai"));
                sach = new Sach(maSanPham, loaiSanPham, soLuongTon, trongLuong, ncc, giaNhap, ghiChu, donVi, hinhAnh,
                        ten, tacGia, nhaXuatBan, namXuatBan, soTrang, theLoaiSach);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sach;
    }


    public VanPhongPham getVPPTheoTen(String ten) {
        VanPhongPham vpp = null;
        try {
            String query = "SELECT * FROM SanPham WHERE tenVanPhongPham=?";
            ps = con.prepareStatement(query);
            ps.setString(1, ten);
            rs = ps.executeQuery();
            if (rs.next()) {
                String maSanPham = rs.getString("maSanPham");
                String loaiSanPham = rs.getString("loaiSanPham");
                int soLuongTon = rs.getInt("soLuongTon");
                double trongLuong = rs.getDouble("trongLuong");
                NhaCungCap ncc = new NhaCungCap(rs.getString("maNCC"));
                long giaNhap = rs.getLong("giaNhap");
                String ghiChu = rs.getString("ghiChu");
                String donVi = rs.getString("donViSanPham");
                String hinhAnh = rs.getString("hinhAnh");
                TheLoaiVanPhongPham theLoaiVPP = new TheLoaiVanPhongPham(rs.getString("maLoaiVanPhongPham"));
                MauSac mauSac = new MauSac(rs.getString("maMauSac"));
                ChatLieu chatLieu = chatLieuDao.getChatLieu(rs.getString("maChatLieu")).get(0);
                XuatXu xuatXu = xuatXuDao.getXuatXu(rs.getString("maXuatXu")).get(0);
                vpp = new VanPhongPham(maSanPham, loaiSanPham, soLuongTon, trongLuong, ncc, giaNhap, ghiChu, donVi, hinhAnh,
                        ten, theLoaiVPP, mauSac, chatLieu, xuatXu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vpp;
    }

    public int capNhatSoLuongSanPham(SanPham sanPham) {
        String sql = "UPDATE SanPham SET soLuongTon = ? WHERE maSanPham = ?";
        try {
            PreparedStatement stmt = con.prepareCall(sql);
            stmt.setInt(1, sanPham.getSoLuongTon());
            stmt.setString(2, sanPham.getMaSanPham());

            return stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0; // Trả về 0 nếu có lỗi xảy ra
    }


    public boolean kiemTraTonTaiSanPham(String tenSP) {
        String query = "SELECT * FROM SanPham WHERE tenSach = ? OR tenVanPhongPham = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, tenSP);
            ps.setString(2, tenSP);
            rs = ps.executeQuery();
            return rs.next(); // Trả về true nếu có kết quả, ngược lại trả về false
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public SanPham timSanPhamTheoMa1(String maSP) throws SQLException {
        SanPham sp = null;
        String query = "SELECT * FROM SanPham WHERE maSanPham=?";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, maSP);
            rs = ps.executeQuery();

            if (rs.next()) {
                String loaiSanPham = rs.getString("loaiSanPham");
                int soLuongTon = rs.getInt("soLuongTon");
                double trongLuong = rs.getDouble("trongLuong");
                NhaCungCap ncc = new NhaCungCap(rs.getString("maNCC"));
                long giaNhap = rs.getLong("giaNhap");
                String ghiChu = rs.getString("ghiChu");
                String donViSanPham = rs.getString("donViSanPham");
                String hinhAnh = rs.getString("hinhAnh");

                if (loaiSanPham.equals("Sách")) {
                    String tenSach = rs.getString("tenSach");
                    TacGia tacGia = new TacGia(rs.getString("maTacGia"));
                    NhaXuatBan nhaXuatBan = new NhaXuatBan(rs.getString("maNXB"));
                    int namXuatBan = rs.getInt("namXB");
                    int soTrang = rs.getInt("soTrang");
                    TheLoaiSach theLoaiSach = new TheLoaiSach(rs.getString("maTheLoai"));
                    sp = new Sach(maSP, loaiSanPham, soLuongTon, trongLuong, ncc, giaNhap, ghiChu, donViSanPham, hinhAnh,
                            tenSach, tacGia, nhaXuatBan, namXuatBan, soTrang, theLoaiSach);
                } else {
                    String tenVanPhongPham = rs.getString("tenVanPhongPham");
                    TheLoaiVanPhongPham loaiVanPhongPham = new TheLoaiVanPhongPham(rs.getString("maLoaiVanPhongPham"));
                    MauSac mauSac = new MauSac(rs.getString("maMauSac"));
                    ChatLieu chatLieu = new ChatLieu(rs.getString("maChatLieu"));
                    XuatXu xuatXu = new XuatXu(rs.getString("maXuatXu"));
                    sp = new VanPhongPham(maSP, loaiSanPham, soLuongTon, trongLuong, ncc, giaNhap, ghiChu, donViSanPham, hinhAnh,
                            tenVanPhongPham, loaiVanPhongPham, mauSac, chatLieu, xuatXu);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sp;
    }

}
