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
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
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
    private EntityManager em;

    public SanPhamDao() {
        this.em = Persistence.createEntityManagerFactory("JPA_ORM_MARIADB").createEntityManager();
    }

    /**
     * Hàm lấy danh sách tất cả sách
     *
     * @return ArrayList Sách
     * @throws Exception
     */

    public SanPham timSanPhamTheoMa(String maSP) throws SQLException {
        try {
            SanPham sanPham = em.find(SanPham.class, maSP);
            if (sanPham != null) {
                if (sanPham instanceof Sach) {
                    return em.find(Sach.class, maSP);
                } else if (sanPham instanceof VanPhongPham) {
                    return em.find(VanPhongPham.class, maSP);
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Sach getSachTheoMaSP(String maSP) {
        try {
            TypedQuery<Sach> query = em.createQuery(
                    "SELECT s FROM Sach s WHERE s.maSanPham = :maSP",
                    Sach.class
            );
            query.setParameter("maSP", maSP);

            Sach sach = query.getSingleResult();
            return sach;
        } catch (Exception e) {
            // Xử lý các ngoại lệ khác
            e.printStackTrace();
        }
        return null;
    }

    public String getLoaiSanPhamTheoMa(String maSP) {
        try {
            TypedQuery<String> query = em.createQuery(
                    "SELECT sp.loaiSanPham FROM SanPham sp WHERE sp.maSanPham = :maSP",
                    String.class
            );
            query.setParameter("maSP", maSP);

            String loaiSanPham = query.getSingleResult();
            return loaiSanPham;
        } catch (Exception e) {
            // Xử lý các ngoại lệ khác
            e.printStackTrace();
        }
        return null;
    }

    public SanPham getSanPhamTheoMa(String masp) {
        try {
            TypedQuery<SanPham> query = em.createQuery(
                    "SELECT sp FROM SanPham sp WHERE sp.maSanPham = :masp",
                    SanPham.class
            );
            query.setParameter("masp", masp);

            SanPham sanPham = query.getSingleResult();
            return sanPham;
        } catch (Exception e) {
            // Xử lý các ngoại lệ khác
            e.printStackTrace();
        }
        return null;
    }

    public VanPhongPham getVPPTheoMaSP(String maSP) {
        try {
            TypedQuery<VanPhongPham> query = em.createQuery(
                    "SELECT vpp FROM VanPhongPham vpp WHERE vpp.maSanPham = :maSP",
                    VanPhongPham.class
            );
            query.setParameter("maSP", maSP);

            VanPhongPham vanPhongPham = query.getSingleResult();
            return vanPhongPham;
        } catch (Exception e) {
            // Xử lý các ngoại lệ khác
            e.printStackTrace();
        }
        return null;
    }

    public List<Sach> getListSach(String maSach, String tenSP, String maTheLoai, Long giaTu, Long giaDen,
                                  String maTacGia, String maNXB, String maNCC, boolean hetHang) {
        String query = "SELECT s FROM Sach s WHERE s.maSanPham LIKE :maSach AND s.tenSach LIKE :tenSP AND s.theLoaiSach.maLoai LIKE :maTheLoai " +
                "AND s.giaNhap > :giaTu AND s.giaNhap < :giaDen AND s.tacGia.maTacGia LIKE :maTacGia " +
                "AND s.nhaXuatBan.maNXB LIKE :maNXB AND s.nhaCungCap.maNCC LIKE :maNCC";
        if (hetHang) {
            query += " AND s.soLuongTon = 0";
        }
        TypedQuery<Sach> typedQuery = em.createQuery(query, Sach.class);
        typedQuery.setParameter("maSach", "%" + maSach + "%");
        typedQuery.setParameter("tenSP", "%" + tenSP + "%");
        typedQuery.setParameter("maTheLoai", "%" + maTheLoai + "%");
        typedQuery.setParameter("giaTu", giaTu);
        typedQuery.setParameter("giaDen", giaDen);
        typedQuery.setParameter("maTacGia", "%" + maTacGia + "%");
        typedQuery.setParameter("maNXB", "%" + maNXB + "%");
        typedQuery.setParameter("maNCC", "%" + maNCC + "%");
        return typedQuery.getResultList();
    }

    public List<VanPhongPham> getListVanPhongPham(String maVPP, String tenVPP, String theLoaiVPP, Long giaTu,
                                                  Long giaDen, String maChatLieu, String maXuatXu, String maNCC, boolean hetHang) {
        String query = "SELECT v FROM VanPhongPham v WHERE v.maSanPham LIKE :maVPP AND v.tenVanPhongPham LIKE :tenVPP AND v.loaiVanPhongPham.maLoai LIKE :theLoaiVPP " +
                "AND v.giaNhap > :giaTu AND v.giaNhap < :giaDen AND v.chatLieu.maChatLieu LIKE :maChatLieu " +
                "AND v.xuatXu.maXuatXu LIKE :maXuatXu AND v.nhaCungCap.maNCC LIKE :maNCC";
        if (hetHang) {
            query += " AND v.soLuongTon = 0";
        }
        TypedQuery<VanPhongPham> typedQuery = em.createQuery(query, VanPhongPham.class);
        typedQuery.setParameter("maVPP", "%" + maVPP + "%");
        typedQuery.setParameter("tenVPP", "%" + tenVPP + "%");
        typedQuery.setParameter("theLoaiVPP", "%" + theLoaiVPP + "%");
        typedQuery.setParameter("giaTu", giaTu);
        typedQuery.setParameter("giaDen", giaDen);
        typedQuery.setParameter("maChatLieu", "%" + maChatLieu + "%");
        typedQuery.setParameter("maXuatXu", "%" + maXuatXu + "%");
        typedQuery.setParameter("maNCC", "%" + maNCC + "%");
        return typedQuery.getResultList();
    }


    public boolean themSanPham(SanPham sanPham) throws Exception {
        try {
            em.getTransaction().begin();
            em.persist(sanPham);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        }
    }


    public boolean capNhatSanPham(String maSP, SanPham temp) throws Exception {
        try {
            em.getTransaction().begin();
            if (temp instanceof Sach) {
                Sach s = em.find(Sach.class, maSP);
                if (s != null) {
                    s = (Sach) temp;
                    em.merge(s);
                }
            } else {
                VanPhongPham v = em.find(VanPhongPham.class, maSP);
                if (v != null) {
                    v = (VanPhongPham) temp;
                    em.merge(v);
                }
            }
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        }
    }


    public boolean xoaSanPham(String maSP) {

        return false;
    }

    public String getMaSPMax() {
        try {
            String query = "SELECT MAX(s.maSanPham) FROM SanPham s";
            TypedQuery<String> typedQuery = em.createQuery(query, String.class);
            return typedQuery.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Sach timSanPhamTheoMaSach(String maSach) throws Exception {
        String query = "SELECT s FROM Sach s WHERE s.maSanPham LIKE :maSach";
        TypedQuery<Sach> typedQuery = em.createQuery(query, Sach.class);
        typedQuery.setParameter("maSach", maSach);
        try {
            return typedQuery.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public VanPhongPham timSanPhamTheoMaVPP(String maVPP) {
        String query = "SELECT v FROM VanPhongPham v WHERE v.maSanPham LIKE :maVPP";
        TypedQuery<VanPhongPham> typedQuery = em.createQuery(query, VanPhongPham.class);
        typedQuery.setParameter("maVPP", maVPP);
        try {
            return typedQuery.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<Sach> getAllSach() {
        String query = "SELECT s FROM Sach s";
        TypedQuery<Sach> typedQuery = em.createQuery(query, Sach.class);
        return typedQuery.getResultList();
    }

    public List<VanPhongPham> getAllVPP() {
        String query = "SELECT v FROM VanPhongPham v";
        TypedQuery<VanPhongPham> typedQuery = em.createQuery(query, VanPhongPham.class);
        return typedQuery.getResultList();
    }

    public Sach getSachTheoTen(String ten) {
        String query = "SELECT s FROM Sach s WHERE s.tenSach = :ten";
        TypedQuery<Sach> typedQuery = em.createQuery(query, Sach.class);
        typedQuery.setParameter("ten", ten);
        try {
            return typedQuery.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public VanPhongPham getVPPTheoTen(String ten) {
        String query = "SELECT v FROM VanPhongPham v WHERE v.tenVanPhongPham = :ten";
        TypedQuery<VanPhongPham> typedQuery = em.createQuery(query, VanPhongPham.class);
        typedQuery.setParameter("ten", ten);
        try {
            return typedQuery.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int capNhatSoLuongSanPham(SanPham sanPham) {
        try {
            em.getTransaction().begin();
            em.merge(sanPham);
            em.getTransaction().commit();
            return 1;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0; // Trả về 0 nếu có lỗi xảy ra
    }

    public boolean kiemTraTonTaiSanPham(String tenSP) {
        String querySach = "SELECT s FROM Sach s WHERE s.tenSach = :tenSP";
        String queryVPP = "SELECT v FROM VanPhongPham v WHERE v.tenVanPhongPham = :tenSP";

        TypedQuery<Sach> typedQuerySach = em.createQuery(querySach, Sach.class);
        TypedQuery<VanPhongPham> typedQueryVPP = em.createQuery(queryVPP, VanPhongPham.class);

        typedQuerySach.setParameter("tenSP", tenSP);
        typedQueryVPP.setParameter("tenSP", tenSP);

        try {
            return typedQuerySach.getResultList().size() > 0 || typedQueryVPP.getResultList().size() > 0;
            // Trả về true nếu có kết quả, ngược lại trả về false
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public SanPham timSanPhamTheoMa1(String maSP) {
        String query = "SELECT s FROM SanPham s WHERE s.maSanPham = :maSP";
        TypedQuery<SanPham> typedQuery = em.createQuery(query, SanPham.class);
        typedQuery.setParameter("maSP", maSP);
        try {
            SanPham sp = typedQuery.getSingleResult();
            if (sp.getLoaiSanPham().equals("Sách")) {
                Sach sach = em.find(Sach.class, sp.getMaSanPham());
                return sach;
            } else {
                VanPhongPham vpp = em.find(VanPhongPham.class, sp.getMaSanPham());
                return vpp;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
