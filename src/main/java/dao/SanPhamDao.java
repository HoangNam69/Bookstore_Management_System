package dao;


import java.util.List;

import entities.Sach;
import entities.SanPham;
import entities.VanPhongPham;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class SanPhamDao {

    private EntityManager em;

    public SanPhamDao() {
        this.em = Persistence.createEntityManagerFactory("JPA_ORM_MARIADB").createEntityManager();
    }


    public SanPham timSanPhamTheoMa(String maSP) {
        try {
            SanPham sanPham = em.find(SanPham.class, maSP);
            if (sanPham != null) {
                if (sanPham instanceof Sach) {
                    return em.find(Sach.class, maSP);
                } else if (sanPham instanceof VanPhongPham) {
                    return em.find(VanPhongPham.class, maSP);
                }
            }
            return sanPham;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Sach getSachTheoMaSP(String maSP) {
        try {
            return em.find(Sach.class, maSP);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public SanPham getSanPhamTheoMa(String masp) {
        try {
            return em.find(SanPham.class, masp);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public VanPhongPham getVPPTheoMaSP(String maSP) {
        try {
            return em.find(VanPhongPham.class, maSP);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Sach> getListSachTheoYeuCau(String maSach, String tenSP, String maTheLoai, Long giaTu, Long giaDen,
                                            String maTacGia, String maNXB, String maNCC, boolean hetHang) {
        try {
            String query = "SELECT s FROM Sach s WHERE s.maSanPham LIKE :maSach AND s.tenSach LIKE :tenSP AND s.theLoaiSach.maLoai LIKE :maTheLoai " +
                    "AND s.giaNhap >= :giaTu AND s.giaNhap <= :giaDen AND s.tacGia.maTacGia LIKE :maTacGia " +
                    "AND s.nhaXuatBan.maNXB LIKE :maNXB AND s.nhaCungCap.maNCC LIKE :maNCC ";
            if (hetHang) {
                query += " AND s.soLuongTon = 0";
            }
            return em.createQuery(query, Sach.class)
                    .setParameter("maSach", "%" + maSach + "%")
                    .setParameter("tenSP", "%" + tenSP + "%")
                    .setParameter("maTheLoai", "%" + maTheLoai + "%")
                    .setParameter("giaTu", giaTu)
                    .setParameter("giaDen", giaDen)
                    .setParameter("maTacGia", "%" + maTacGia + "%")
                    .setParameter("maNXB", "%" + maNXB + "%")
                    .setParameter("maNCC", "%" + maNCC + "%")
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<VanPhongPham> getListVanPhongPhamTheoYeuCau(String maVPP, String tenVPP, String theLoaiVPP, Long giaTu,
                                                            Long giaDen, String maChatLieu, String maXuatXu, String maNCC, boolean hetHang) {
        String query = "SELECT v FROM VanPhongPham v WHERE v.maSanPham LIKE :maVPP AND v.tenVanPhongPham LIKE :tenVPP AND v.loaiVanPhongPham.maLoai LIKE :theLoaiVPP " +
                "AND v.giaNhap > :giaTu AND v.giaNhap < :giaDen AND v.chatLieu.maChatLieu LIKE :maChatLieu " +
                "AND v.xuatXu.maXuatXu LIKE :maXuatXu AND v.nhaCungCap.maNCC LIKE :maNCC";
        if (hetHang) {
            query += " AND v.soLuongTon = 0";
        }
        try {
            return em.createQuery(query, VanPhongPham.class)
                    .setParameter("maVPP", "%" + maVPP + "%")
                    .setParameter("tenVPP", "%" + tenVPP + "%")
                    .setParameter("theLoaiVPP", "%" + theLoaiVPP + "%")
                    .setParameter("giaTu", giaTu)
                    .setParameter("giaDen", giaDen)
                    .setParameter("maChatLieu", "%" + maChatLieu + "%")
                    .setParameter("maXuatXu", "%" + maXuatXu + "%")
                    .setParameter("maNCC", "%" + maNCC + "%")
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public boolean themSanPham(SanPham sanPham) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(sanPham);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            return false;
        }
    }


    public boolean capNhatSanPham(String maSP, SanPham temp) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            if (temp instanceof Sach) {
                Sach s = em.find(Sach.class, maSP);
                if (s != null) {
                    s = (Sach) temp;
                    em.merge(s);
                } else {
                    tx.rollback();
                    return false;
                }
            } else if (temp instanceof VanPhongPham) {
                VanPhongPham v = em.find(VanPhongPham.class, maSP);
                if (v != null) {
                    v = (VanPhongPham) temp;
                    em.merge(v);
                } else {
                    tx.rollback();
                    return false;
                }
            } else {
                tx.rollback();
                return false;
            }
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            return false;
        }
    }

    public String getMaSPMax() {
        String query = "SELECT MAX(s.maSanPham) FROM SanPham s";
        try {
            return em.createQuery(query, String.class).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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
        String query = "SELECT v FROM VanPhongPham v WHERE v.maSanPham = :maVPP";
        try {
            return em.createQuery(query, VanPhongPham.class)
                    .setParameter("maVPP", maVPP)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public List<Sach> getAllSach() {
        try {
            return em.createQuery("SELECT s FROM Sach s", Sach.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<VanPhongPham> getAllVPP() {
        try {

            return em.createQuery("SELECT v FROM VanPhongPham v", VanPhongPham.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Sach getSachTheoTen(String ten) {

        try {
            return em.createQuery("SELECT s FROM Sach s WHERE s.tenSach = :ten", Sach.class)
                    .setParameter("ten", ten)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public VanPhongPham getVPPTheoTen(String ten) {
        try {
            return em.createQuery("SELECT v FROM VanPhongPham v WHERE v.tenVanPhongPham = :ten", VanPhongPham.class)
                    .setParameter("ten", ten)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int capNhatSoLuongSanPham(SanPham sanPham) {
        EntityTransaction tx = em.getTransaction();
        String query = "UPDATE SanPham s SET s.soLuongTon = :soLuongTon WHERE s.maSanPham = :maSP";
        try {
            if (em.find(SanPham.class, sanPham.getMaSanPham()) != null) {
                tx.begin();
                em.createQuery(query)
                        .setParameter("soLuongTon", sanPham.getSoLuongTon())
                        .setParameter("maSP", sanPham.getMaSanPham())
                        .executeUpdate();
                tx.commit();
                return 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            return 0;
        }
    }

    public boolean kiemTraTonTaiSanPham(String tenSP) {
        String querySach = "SELECT s FROM Sach s WHERE s.tenSach = :tenSP";
        String queryVPP = "SELECT v FROM VanPhongPham v WHERE v.tenVanPhongPham = :tenSP";

        try {
            List<Sach> sachList = em.createQuery(querySach, Sach.class)
                    .setParameter("tenSP", tenSP)
                    .getResultList();
            List<VanPhongPham> vppList = em.createQuery(queryVPP, VanPhongPham.class)
                    .setParameter("tenSP", tenSP)
                    .getResultList();
            return sachList.size() > 0 || vppList.size() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public SanPham timSanPhamTheoMa1(String maSP) {
        try {
            return em.find(SanPham.class, maSP);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
