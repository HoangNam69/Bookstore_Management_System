package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import dao.ChiTietHoaDonDao;
import dao.HoaDonDao;
import dao.SanPhamDao;
import dao.TacGiaDao;
import dao.TheLoaiDao;
import entities.ChiTietHoaDon;
import entities.HoaDon;
import entities.KhachHang;
import entities.NhanVien;
import entities.Sach;
import entities.SanPham;
import entities.TacGia;
import entities.TaiKhoan;
import entities.VanPhongPham;
import lombok.SneakyThrows;
import service.*;
import service.impl.ChiTietHoaDonServiceImpl;
import service.impl.HoaDonServiceImpl;
import service.impl.KhachHangServiceImpl;
import service.impl.NhanVienServiceImpl;
import service.impl.SanPhamServiceImpl;
import util.Constants;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.Naming;
import java.sql.SQLException;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.TitledBorder;

public class Pnl_TaoHoaDon extends JPanel implements ActionListener, MouseListener {
    private JTextField txtSDT;
    private JTextField txtTenKhachHang;
    private JTextField txtMaSach;
    private JTextField txtTenSach;
    private JTable tblSach;
    private JTextField txtGiaBanSach;
    private JTextField txtSoLuongSach;
    private JTextField txtTenVPP;
    private JTextField txtGiaBanVPP;
    private JComboBox<Object> cmbXuatXu;
    private JComboBox<Object> cmbChatLieu;
    private JTabbedPane tabSanPham;
    private JComboBox<Object> cmbTheLoai;
    private JComboBox<Object> cmbTacGia;
    private JButton btnThemKhachHang;
    private JButton btnRefresh;
    private JButton btnTimKhachHang;
    private JTable tblHoaDon;
    private JTextField txtMaHoaDon;
    private JTextField txtTongTienHD;
    private JTextField txtVAT;
    private JTextField txtNhanVien;
    private JTextField txtTienKhachDua;
    private JTextField txtTienThua;
    private JButton btnHuyHoaDon;
    private JButton btnThemHangCho;
    private JButton btnThanhToan;
    private JButton btnHangCho;
    private JButton btnXoa;
    private DefaultTableModel modelSach;
    private JScrollPane scrSach;
    private JLabel lblSoLuongVPP;
    private JTextField txtSoLuongVPP;
    private JButton btnThemVPP;
    private JTable tableVPP;
    private DefaultTableModel modelVPP;
    private JScrollPane scrVPP;
    private List<Sach> dsSach;
    private TheLoaiDao theLoaiSach;
    private List<VanPhongPham> dsVPP;
    private JTextField txtMaVPP;
    private DefaultTableModel modelHoaDon;
    private JScrollPane scrHoaDon;

    private NhanVien nv;
    private JButton btnThemSach;
    private String sdt;
    private JButton btnLamMoiBang;
    private List<ChiTietHoaDon> dsChiTietHoaDon;
    private List<TacGia> dsTacGia;
    private static HashMap<String, ArrayList<SanPham>> dsHoaDonCho = new HashMap<String, ArrayList<SanPham>>();
    WinHangCho winHangCho;
    private JLabel lblMaHD;
    private JTextField txtTimKiemMaSP;
    private JTextField txtTimKiemTenSP;
    private JTextField txtXoa;

    private static final String URL = "rmi://"+ Constants.IPV4 + ":"+ Constants.PORT + "/";
    private SanPhamService sanPhamService = (SanPhamService) Naming.lookup(URL + "sanPham");
    private SachLoiService sachLoiService = (SachLoiService) Naming.lookup(URL + "sachLoi");
    private HoaDonService hoaDonService = (HoaDonService) Naming.lookup(URL + "hoaDon");
    private TaiKhoanService taiKhoanService = (TaiKhoanService) Naming.lookup(URL + "taiKhoan");
    private NhanVienService nhanVienService = (NhanVienService) Naming.lookup(URL + "nhanVien");
    private ChiTietHoaDonService chiTietHoaDonService = (ChiTietHoaDonService) Naming.lookup(URL + "chiTietHoaDon");
    private KhachHangService khachHangService = (KhachHangService) Naming.lookup(URL + "khachHang");
    private TacGiaService tacGiaService = (TacGiaService) Naming.lookup(URL + "tacGia");

    /**
     * Create the panel.
     */

    public Pnl_TaoHoaDon() throws Exception {
        setBackground(new Color(0, 206, 209));
        setFont(new Font("Dialog", Font.BOLD, 16));
        setSize(1493, 650);
        setLayout(null);

        JPanel pnlMaHD = new JPanel();
        pnlMaHD.setBounds(10, 40, 191, 30);
        add(pnlMaHD);
        pnlMaHD.setLayout(null);

        JLabel lblMa = new JLabel("MÃ HOÁ ĐƠN:");
        lblMa.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblMa.setHorizontalAlignment(SwingConstants.CENTER);
        lblMa.setBounds(10, 0, 92, 30);
        pnlMaHD.add(lblMa);

        lblMaHD = new JLabel("");
        lblMaHD.setText(auto_ID());
        lblMaHD.setBounds(99, 0, 92, 30);
        pnlMaHD.add(lblMaHD);
        lblMaHD.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblMaHD.setHorizontalAlignment(SwingConstants.CENTER);
        lblMaHD.setForeground(new Color(255, 0, 0));

        JPanel pnlTitle = new JPanel();
        pnlTitle.setBounds(0, 0, 1900, 30);
        add(pnlTitle);
        pnlTitle.setLayout(null);

        JLabel lblTitle = new JLabel("LẬP HOÁ ĐƠN");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 22));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(0, 0, 1900, 30);
        pnlTitle.add(lblTitle);

        tblSach = new JTable(modelSach);
        tblSach.setBounds(10, 307, 780, 316);
        String[] cols = {"STT", "Mã sách", "Tên sách", "Thể loại", "Tác giả", "Giá bán", "Số lượng"};
        modelSach = new DefaultTableModel(cols, 0);
        tblSach.setBorder(new LineBorder(new Color(0, 0, 0)));
        tblSach.setFont(new Font("Tahoma", Font.PLAIN, 12));

        scrSach = new JScrollPane();
        scrSach.setViewportView(tblSach = new JTable(modelSach));
        scrSach.setBounds(10, 322, 950, 500);
        tblSach.getTableHeader().setBackground(Color.LIGHT_GRAY);
        tblSach.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 12));
        tblSach.setRowHeight(25);
        tblSach.setBackground(Color.WHITE);
        scrSach.getViewport().setBackground(Color.WHITE);
        tblSach.getTableHeader().setPreferredSize(new Dimension(0, 20));
        add(scrSach);
        tblSach.addMouseListener(this);
        String[] headers = {"STT", "Mã Văn phòng phẩm", "Tên Văn phòng phẩm", "Chất liệu", "Xuất xứ", "Giá bán",
                "Số lượng"};
        modelVPP = new DefaultTableModel(headers, 0);

        scrVPP = new JScrollPane();
        scrVPP.setViewportView(tableVPP = new JTable(modelVPP));
        scrVPP.setBounds(10, 322, 950, 500);
        tableVPP.getTableHeader().setBackground(Color.LIGHT_GRAY);
        tableVPP.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 12));
        tableVPP.setRowHeight(25);
        tableVPP.setBackground(Color.WHITE);
        scrVPP.getViewport().setBackground(Color.WHITE);
        tableVPP.getTableHeader().setPreferredSize(new Dimension(0, 20));
        add(scrVPP);

        JPanel pnlHoaDon = new JPanel();
        pnlHoaDon.setBounds(1000, 40, 870, 780);
        add(pnlHoaDon);
        pnlHoaDon.setLayout(null);

        JLabel lblTitleHoaDon = new JLabel("HOÁ ĐƠN BÁN HÀNG");
        lblTitleHoaDon.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblTitleHoaDon.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitleHoaDon.setBounds(0, 0, 870, 35);
        pnlHoaDon.add(lblTitleHoaDon);

        JLabel lblNgayLap = new JLabel("Ngày lập");
        lblNgayLap.setHorizontalAlignment(SwingConstants.CENTER);
        lblNgayLap.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNgayLap.setBounds(286, 46, 90, 20);
        pnlHoaDon.add(lblNgayLap);

        JLabel lblNgayThang = new JLabel("");
        lblNgayThang.setText(auto_Date());
        lblNgayThang.setHorizontalAlignment(SwingConstants.CENTER);
        lblNgayThang.setForeground(new Color(255, 0, 51));
        lblNgayThang.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
        lblNgayThang.setBounds(386, 46, 107, 20);
        pnlHoaDon.add(lblNgayThang);

        txtSDT = new JTextField();
        txtSDT.setBounds(110, 110, 150, 20);
        pnlHoaDon.add(txtSDT);
        txtSDT.setColumns(10);

        JLabel lblSDT = new JLabel("Số điện thoại");
        lblSDT.setBounds(10, 110, 90, 20);
        pnlHoaDon.add(lblSDT);
        lblSDT.setFont(new Font("Tahoma", Font.BOLD, 12));

        txtTenKhachHang = new JTextField();
        txtTenKhachHang.setEditable(false);
        txtTenKhachHang.setBounds(120, 150, 197, 20);
        pnlHoaDon.add(txtTenKhachHang);
        txtTenKhachHang.setColumns(10);

        JLabel lblTenKhachHang = new JLabel("Tên khách hàng");
        lblTenKhachHang.setBounds(10, 150, 110, 20);
        pnlHoaDon.add(lblTenKhachHang);
        lblTenKhachHang.setFont(new Font("Tahoma", Font.BOLD, 12));

        btnThemKhachHang = new JButton("Thêm khách hàng");
        btnThemKhachHang.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnThemKhachHang.setBounds(500, 105, 202, 30);
        pnlHoaDon.add(btnThemKhachHang);
        btnThemKhachHang.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnThemKhachHang.setIcon(new ImageIcon(Pnl_TaoHoaDon.class.getResource("/gui/icon/add-user.png")));

        btnRefresh = new JButton("");
        btnRefresh.setBounds(270, 105, 30, 30);
        pnlHoaDon.add(btnRefresh);
        btnRefresh.setIcon(new ImageIcon(Pnl_TaoHoaDon.class.getResource("/gui/icon/refresh-button.png")));
        btnRefresh.setSelectedIcon(new ImageIcon(Pnl_TaoHoaDon.class.getResource("/gui/icon/refresh-button.png")));

        btnTimKhachHang = new JButton("Tìm");
        btnTimKhachHang.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnTimKhachHang.setBounds(350, 105, 100, 30);
        pnlHoaDon.add(btnTimKhachHang);
        btnTimKhachHang.setIcon(new ImageIcon(Pnl_TaoHoaDon.class.getResource("/gui/icon/loupe.png")));

        JLabel lblMaHoaDon = new JLabel("Mã hoá đơn:");
        lblMaHoaDon.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblMaHoaDon.setBounds(10, 75, 90, 20);
        pnlHoaDon.add(lblMaHoaDon);

        txtMaHoaDon = new JTextField();
        txtMaHoaDon.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
        txtMaHoaDon.setText(auto_ID());
        txtMaHoaDon.setEditable(false);
        txtMaHoaDon.setForeground(Color.RED);
        txtMaHoaDon.setBounds(110, 75, 150, 20);
        pnlHoaDon.add(txtMaHoaDon);
        txtMaHoaDon.setColumns(10);

        JLabel lblTongTien = new JLabel("Tổng tiền:");
        lblTongTien.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblTongTien.setBounds(10, 600, 75, 20);
        pnlHoaDon.add(lblTongTien);

        txtTongTienHD = new JTextField();
        txtTongTienHD.setFont(new Font("Tahoma", Font.PLAIN, 12));
        txtTongTienHD.setEditable(false);
        txtTongTienHD.setBounds(130, 600, 130, 20);
        pnlHoaDon.add(txtTongTienHD);
        txtTongTienHD.setColumns(10);

        JLabel lblThanhTienHD = new JLabel("Thành tiền (10% VAT):");
        lblThanhTienHD.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblThanhTienHD.setBounds(292, 600, 150, 20);
        pnlHoaDon.add(lblThanhTienHD);

        txtVAT = new JTextField();
        txtVAT.setFont(new Font("Tahoma", Font.PLAIN, 12));
        txtVAT.setEditable(false);
        txtVAT.setColumns(10);
        txtVAT.setBounds(452, 600, 150, 20);
        pnlHoaDon.add(txtVAT);

        JLabel lblTienKhachDua = new JLabel("Tiền khách đưa:");
        lblTienKhachDua.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblTienKhachDua.setBounds(10, 650, 110, 20);
        pnlHoaDon.add(lblTienKhachDua);

        JLabel lblNhanVien = new JLabel("Nhân viên:");
        lblNhanVien.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNhanVien.setBounds(290, 75, 90, 20);
        pnlHoaDon.add(lblNhanVien);

        txtNhanVien = new JTextField();
        txtNhanVien.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
        txtNhanVien.setEditable(false);
        txtNhanVien.setForeground(Color.RED);
        txtNhanVien.setColumns(10);
        txtNhanVien.setBounds(396, 75, 226, 20);

        WinLogin dangNhap = new WinLogin();
        TaiKhoan taiKhoan = dangNhap.getTaiKhoanDangNhapThanhCong();

        nv = new NhanVien();
        try {
            nv = nhanVienService.timNhanVienTheoMa(taiKhoan.getNhanVien().getMaNhanVien());
        } catch (SQLException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }
        txtNhanVien.setText(nv.getHoTenNhanVien());
        pnlHoaDon.add(txtNhanVien);

        txtTienKhachDua = new JTextField();
        txtTienKhachDua.setFont(new Font("Tahoma", Font.PLAIN, 12));
        txtTienKhachDua.setColumns(10);
        txtTienKhachDua.setBounds(130, 650, 130, 20);
        pnlHoaDon.add(txtTienKhachDua);
        txtTienKhachDua.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void keyReleased(KeyEvent e) {
                // TODO Auto-generated method stub
                if (!(txtTienKhachDua.getText().matches("[0-9]*"))) {
                    JOptionPane.showMessageDialog(null, "Không được nhập chữ");
                    txtTienKhachDua.setText("");
                    txtTienThua.setText("");
                    txtTienKhachDua.requestFocus();

                    return;
                } else {
                    tinhTienThua();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // TODO Auto-generated method stub

            }
        });

        JLabel lblTienThua = new JLabel("Tiền thừa:");
        lblTienThua.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblTienThua.setBounds(340, 650, 110, 20);
        pnlHoaDon.add(lblTienThua);

        txtTienThua = new JTextField();
        txtTienThua.setFont(new Font("Tahoma", Font.PLAIN, 12));
        txtTienThua.setEditable(false);
        txtTienThua.setColumns(10);
        txtTienThua.setBounds(452, 650, 150, 20);
        pnlHoaDon.add(txtTienThua);

        btnHuyHoaDon = new JButton("Huỷ hoá đơn");
        btnHuyHoaDon.setIcon(new ImageIcon(Pnl_TaoHoaDon.class.getResource("/gui/icon/delete.png")));
        btnHuyHoaDon.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnHuyHoaDon.setBounds(10, 700, 150, 35);
        pnlHoaDon.add(btnHuyHoaDon);

        btnThemHangCho = new JButton("Thêm hàng chờ");
        btnThemHangCho.setIcon(new ImageIcon(Pnl_TaoHoaDon.class.getResource("/gui/icon/icons-add.png")));
        btnThemHangCho.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnThemHangCho.setBounds(300, 700, 170, 35);
        pnlHoaDon.add(btnThemHangCho);

        btnThanhToan = new JButton("Thanh toán");
        btnThanhToan.setIcon(new ImageIcon(Pnl_TaoHoaDon.class.getResource("/gui/icon/money.png")));
        btnThanhToan.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnThanhToan.setBounds(620, 700, 150, 35);
        pnlHoaDon.add(btnThanhToan);

        btnHangCho = new JButton("Hàng chờ");
        btnHangCho.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnHangCho.setBounds(327, 150, 103, 30);
        pnlHoaDon.add(btnHangCho);

        btnXoa = new JButton("Xoá");
        btnXoa.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnXoa.setBounds(440, 150, 89, 30);
        pnlHoaDon.add(btnXoa);

        scrHoaDon = new JScrollPane();
        scrHoaDon.setBounds(0, 191, 850, 400);
        pnlHoaDon.add(scrHoaDon);
        String[] header = {"STT", "Mã sản phẩm", "Tên sản phẩm", "Giá bán", "Số lượng"};
        modelHoaDon = new DefaultTableModel(header, 0);
        scrHoaDon.setViewportView(tblHoaDon = new JTable(modelHoaDon));

        txtXoa = new JTextField();
        txtXoa.setBounds(539, 150, 83, 30);
        pnlHoaDon.add(txtXoa);
        txtXoa.setColumns(10);
        tblHoaDon.getTableHeader().setBackground(Color.LIGHT_GRAY);
        tblHoaDon.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 12));
        tblHoaDon.setRowHeight(25);
        tblHoaDon.setBackground(Color.WHITE);
        scrHoaDon.getViewport().setBackground(Color.WHITE);
        tblHoaDon.getTableHeader().setPreferredSize(new Dimension(0, 20));
        btnTimKhachHang.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnRefresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        tabSanPham = new JTabbedPane(JTabbedPane.TOP);
        tabSanPham.setBounds(10, 77, 820, 178);
        add(tabSanPham);

        JPanel pnlSach = new JPanel();
        tabSanPham.addTab("Sách", null, pnlSach, null);
        pnlSach.setLayout(null);
        System.out.println(tabSanPham.getSelectedIndex());

        tabSanPham.addChangeListener(new ChangeListener() {


            @Override
            public void stateChanged(ChangeEvent e) {
                // TODO Auto-generated method stub
                if (tabSanPham.getSelectedIndex() == 0) {
                    scrSach.setVisible(true);
                    scrVPP.setVisible(false);

                } else {
                    scrSach.setVisible(false);
                    scrVPP.setVisible(true);
                    txtTimKiemMaSP.addKeyListener(new KeyListener() {

                        @Override
                        public void keyTyped(KeyEvent e) {
                            // TODO Auto-generated method stub

                        }

                        @SneakyThrows
                        @Override
                        public void keyReleased(KeyEvent e) {
                            // TODO Auto-generated method stub
                            tableDanhSachVPPWithFilter();
                        }

                        @Override
                        public void keyPressed(KeyEvent e) {
                            // TODO Auto-generated method stub

                        }
                    });
                    txtTimKiemTenSP.addKeyListener(new KeyListener() {

                        @Override
                        public void keyTyped(KeyEvent e) {
                            // TODO Auto-generated method stub

                        }
                        @SneakyThrows
                        @Override
                        public void keyReleased(KeyEvent e) {
                            // TODO Auto-generated method stub
                            tableDanhSachVPPWithFilter();
                        }

                        @Override
                        public void keyPressed(KeyEvent e) {
                            // TODO Auto-generated method stub

                        }
                    });
                }
            }
        });

        JLabel lblMaSach = new JLabel("Mã sách");
        lblMaSach.setHorizontalAlignment(SwingConstants.LEFT);
        lblMaSach.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblMaSach.setBounds(10, 10, 75, 20);
        pnlSach.add(lblMaSach);

        txtMaSach = new JTextField();
        txtMaSach.setFont(new Font("Tahoma", Font.PLAIN, 12));
        txtMaSach.setBounds(170, 10, 250, 25);
        pnlSach.add(txtMaSach);
        txtMaSach.setColumns(10);

        JLabel lblTenSach = new JLabel("Tên sách");
        lblTenSach.setHorizontalAlignment(SwingConstants.LEFT);
        lblTenSach.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblTenSach.setBounds(465, 10, 75, 20);
        pnlSach.add(lblTenSach);

        txtTenSach = new JTextField();
        txtTenSach.setFont(new Font("Tahoma", Font.PLAIN, 12));
        txtTenSach.setColumns(10);
        txtTenSach.setBounds(550, 10, 250, 25);
        pnlSach.add(txtTenSach);

        JLabel lblTacGia = new JLabel("Tác giả");
        lblTacGia.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblTacGia.setHorizontalAlignment(SwingConstants.LEFT);
        lblTacGia.setBounds(465, 60, 75, 20);
        pnlSach.add(lblTacGia);

        cmbTacGia = new JComboBox();
        cmbTacGia.setFont(new Font("Tahoma", Font.PLAIN, 12));

        try {
            dsTacGia = tacGiaService.getListTacGia();
        } catch (Exception e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }
        cmbTacGia.addItem("Tất cả");
        for (TacGia tg : dsTacGia) {
            cmbTacGia.addItem(tg.getTenTacGia());
        }
        cmbTacGia.setEditable(true);
        cmbTacGia.setBounds(550, 60, 250, 25);
        pnlSach.add(cmbTacGia);

        JLabel lblTheLoai = new JLabel("Thể loại");
        lblTheLoai.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblTheLoai.setHorizontalAlignment(SwingConstants.LEFT);
        lblTheLoai.setBounds(10, 60, 75, 20);
        pnlSach.add(lblTheLoai);

        JLabel lblGiaBanSach = new JLabel("Giá bán");
        lblGiaBanSach.setHorizontalAlignment(SwingConstants.LEFT);
        lblGiaBanSach.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblGiaBanSach.setBounds(10, 110, 75, 20);
        pnlSach.add(lblGiaBanSach);

        txtGiaBanSach = new JTextField();
        txtGiaBanSach.setFont(new Font("Tahoma", Font.PLAIN, 12));
        txtGiaBanSach.setEditable(false);
        txtGiaBanSach.setColumns(10);
        txtGiaBanSach.setBounds(170, 110, 250, 25);
        pnlSach.add(txtGiaBanSach);

        cmbTheLoai = new JComboBox();
        cmbTheLoai.setFont(new Font("Tahoma", Font.PLAIN, 12));
        cmbTheLoai.setModel(new DefaultComboBoxModel(
                new String[]{"Tất cả", "SGK", "Truyện", "Tiểu thuyết", "Kĩ năng sống", "Kinh doanh", "Thiếu nhi"}));
        cmbTheLoai.setEditable(true);
        cmbTheLoai.setBounds(170, 60, 250, 25);
        pnlSach.add(cmbTheLoai);

        btnThemSach = new JButton("Thêm");
        btnThemSach.setIcon(new ImageIcon(Pnl_TaoHoaDon.class.getResource("/gui/icon/icons-add.png")));
        btnThemSach.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnThemSach.setBounds(641, 108, 126, 30);
        pnlSach.add(btnThemSach);

        txtSoLuongSach = new JTextField("1");
        txtSoLuongSach.setFont(new Font("Tahoma", Font.PLAIN, 12));
        txtSoLuongSach.setBounds(550, 110, 69, 25);
        pnlSach.add(txtSoLuongSach);
        txtSoLuongSach.setColumns(10);
        txtSoLuongSach.addFocusListener(new FocusListener() {

            @Override
            public void focusLost(FocusEvent e) {
                // TODO Auto-generated method stub
            }

            @Override
            public void focusGained(FocusEvent e) {
                // TODO Auto-generated method stub
                txtSoLuongSach.setText("");
            }
        });

        JLabel lblSoLuongSach = new JLabel("Số lượng");
        lblSoLuongSach.setBounds(465, 105, 77, 25);
        pnlSach.add(lblSoLuongSach);
        lblSoLuongSach.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblSoLuongSach.setHorizontalAlignment(SwingConstants.LEFT);

        JPanel pnlVPP = new JPanel();
        tabSanPham.addTab("Văn phòng phẩm", null, pnlVPP, null);
        pnlVPP.setLayout(null);

        JLabel lblMaVPP = new JLabel("Mã văn phòng phẩm");
        lblMaVPP.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblMaVPP.setBounds(10, 10, 150, 25);
        pnlVPP.add(lblMaVPP);

        JLabel lblTenVPP = new JLabel("Tên văn phòng phẩm");
        lblTenVPP.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblTenVPP.setBounds(10, 60, 150, 25);
        pnlVPP.add(lblTenVPP);

        txtTenVPP = new JTextField();
        txtTenVPP.setFont(new Font("Tahoma", Font.PLAIN, 12));
        txtTenVPP.setBounds(170, 60, 250, 25);
        pnlVPP.add(txtTenVPP);
        txtTenVPP.setColumns(10);

        JLabel lblChatLieu = new JLabel("Chất liệu");
        lblChatLieu.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblChatLieu.setBounds(465, 10, 72, 25);
        pnlVPP.add(lblChatLieu);

        cmbChatLieu = new JComboBox();
        cmbChatLieu.setModel(new DefaultComboBoxModel(new String[]{"Nhựa", "Giấy", "Tẩy", "Màu", "Vải"}));
        cmbChatLieu.setEditable(true);
        cmbChatLieu.setFont(new Font("Tahoma", Font.PLAIN, 14));
        cmbChatLieu.setBounds(550, 10, 254, 25);
        pnlVPP.add(cmbChatLieu);

        JLabel lblXuatXu = new JLabel("Xuất xứ");
        lblXuatXu.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblXuatXu.setBounds(465, 60, 72, 25);
        pnlVPP.add(lblXuatXu);

        cmbXuatXu = new JComboBox<Object>();
        cmbXuatXu.setModel(new DefaultComboBoxModel(new String[]{"Việt Nam", "Hoa Kì", "Trung Quốc"}));
        cmbXuatXu.setFont(new Font("Tahoma", Font.PLAIN, 12));
        cmbXuatXu.setEditable(true);
        cmbXuatXu.setBounds(550, 60, 254, 25);
        pnlVPP.add(cmbXuatXu);

        JLabel lblGiaBanVPP = new JLabel("Giá bán");
        lblGiaBanVPP.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblGiaBanVPP.setBounds(10, 110, 150, 25);
        pnlVPP.add(lblGiaBanVPP);

        txtGiaBanVPP = new JTextField();
        txtGiaBanVPP.setFont(new Font("Tahoma", Font.PLAIN, 12));
        txtGiaBanVPP.setEditable(false);
        txtGiaBanVPP.setColumns(10);
        txtGiaBanVPP.setBounds(170, 110, 250, 25);
        pnlVPP.add(txtGiaBanVPP);

        txtMaVPP = new JTextField();
        txtMaVPP.setFont(new Font("Tahoma", Font.PLAIN, 12));
        txtMaVPP.setColumns(10);
        txtMaVPP.setBounds(170, 10, 250, 25);
        pnlVPP.add(txtMaVPP);

        lblSoLuongVPP = new JLabel("Số lượng");
        lblSoLuongVPP.setBounds(465, 110, 77, 25);
        pnlVPP.add(lblSoLuongVPP);
        lblSoLuongVPP.setHorizontalAlignment(SwingConstants.LEFT);
        lblSoLuongVPP.setFont(new Font("Tahoma", Font.BOLD, 14));

        txtSoLuongVPP = new JTextField("1");
        txtSoLuongVPP.setFont(new Font("Tahoma", Font.PLAIN, 12));
        txtSoLuongVPP.setBounds(550, 110, 69, 25);
        pnlVPP.add(txtSoLuongVPP);
        txtSoLuongVPP.setColumns(10);
        txtSoLuongVPP.addFocusListener(new FocusListener() {

            @Override
            public void focusLost(FocusEvent e) {
                // TODO Auto-generated method stub
            }

            @Override
            public void focusGained(FocusEvent e) {
                // TODO Auto-generated method stub
                txtSoLuongVPP.setText("");
            }
        });

        btnThemVPP = new JButton("Thêm");
        btnThemVPP.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnThemVPP.setIcon(new ImageIcon(Pnl_TaoHoaDon.class.getResource("/gui/icon/icons-add.png")));
        btnThemVPP.setBounds(641, 108, 126, 30);
        pnlVPP.add(btnThemVPP);

        tblSach.getColumnModel().getColumn(0).setPreferredWidth(20);
        tblSach.getColumnModel().getColumn(1).setPreferredWidth(70);
        tblSach.getColumnModel().getColumn(2).setPreferredWidth(120);
        tblSach.getColumnModel().getColumn(3).setPreferredWidth(70);
        tblSach.getColumnModel().getColumn(4).setPreferredWidth(70);
        tblSach.getColumnModel().getColumn(5).setPreferredWidth(40);

        tableVPP.getColumnModel().getColumn(0).setPreferredWidth(20);
        tableVPP.getColumnModel().getColumn(1).setPreferredWidth(120);
        tableVPP.getColumnModel().getColumn(2).setPreferredWidth(120);
        tableVPP.getColumnModel().getColumn(3).setPreferredWidth(70);
        tableVPP.getColumnModel().getColumn(4).setPreferredWidth(70);
        tableVPP.getColumnModel().getColumn(5).setPreferredWidth(40);
        tableVPP.getColumnModel().getColumn(5).setPreferredWidth(40);
        tableVPP.addMouseListener(this);

        try {
            docDuLieuSach();
            docDuLieuVPP();
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        btnThemSach.addActionListener(this);
        btnHangCho.addActionListener(this);
        btnHuyHoaDon.addActionListener(this);
        btnRefresh.addActionListener(this);
        btnThanhToan.addActionListener(this);
        btnThemHangCho.addActionListener(this);
        btnThemKhachHang.addActionListener(this);
        btnThemVPP.addActionListener(this);
        btnTimKhachHang.addActionListener(this);
        btnXoa.addActionListener(this);

        JPanel pnlTimKiem = new JPanel();
        pnlTimKiem.setBorder(
                new TitledBorder(null, "T\u00ECm ki\u1EBFm", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pnlTimKiem.setBounds(10, 266, 820, 45);
        add(pnlTimKiem);
        pnlTimKiem.setLayout(null);

        JLabel lblTimKiemMaSP = new JLabel("Mã sản phẩm:");
        lblTimKiemMaSP.setBounds(90, 11, 115, 25);
        lblTimKiemMaSP.setHorizontalAlignment(SwingConstants.LEFT);
        lblTimKiemMaSP.setFont(new Font("Tahoma", Font.BOLD, 14));
        pnlTimKiem.add(lblTimKiemMaSP);

        txtTimKiemMaSP = new JTextField();
        txtTimKiemMaSP.setBounds(210, 11, 130, 25);
        pnlTimKiem.add(txtTimKiemMaSP);
        txtTimKiemMaSP.setColumns(10);

        JLabel lblTImKiemTenSP = new JLabel("Tên sản phẩm:");
        lblTImKiemTenSP.setHorizontalAlignment(SwingConstants.LEFT);
        lblTImKiemTenSP.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblTImKiemTenSP.setBounds(375, 11, 115, 25);
        pnlTimKiem.add(lblTImKiemTenSP);

        txtTimKiemTenSP = new JTextField();
        txtTimKiemTenSP.setBounds(500, 11, 130, 25);
        pnlTimKiem.add(txtTimKiemTenSP);
        txtTimKiemTenSP.setColumns(10);

        btnLamMoiBang = new JButton("Làm mới bảng");
        btnLamMoiBang.setIcon(new ImageIcon(Pnl_TaoHoaDon.class.getResource("/gui/icon/refresh-button.png")));
        btnLamMoiBang.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnLamMoiBang.setBounds(640, 9, 170, 30);
        pnlTimKiem.add(btnLamMoiBang);
        btnLamMoiBang.addActionListener(this);
        if (tabSanPham.getSelectedIndex() == 0) {
            txtTimKiemMaSP.addKeyListener(new KeyListener() {

                @Override
                public void keyTyped(KeyEvent e) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void keyReleased(KeyEvent e) {
                    // TODO Auto-generated method stub
                    try {
                        tableDanhSachSachWithFilter();
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    // TODO Auto-generated method stub

                }
            });
            txtTimKiemTenSP.addKeyListener(new KeyListener() {

                @Override
                public void keyTyped(KeyEvent e) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void keyReleased(KeyEvent e) {
                    // TODO Auto-generated method stub
                    try {
                        tableDanhSachSachWithFilter();
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    // TODO Auto-generated method stub

                }
            });
        }
        txtMaSach.setEditable(false);
        txtTenSach.setEditable(false);
        cmbTheLoai.setEditable(false);
        cmbTacGia.setEditable(false);
        txtMaVPP.setEditable(false);
        txtTenVPP.setEditable(false);
        cmbChatLieu.setEditable(false);
        cmbXuatXu.setEditable(false);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        Object obj = e.getSource();
        if (obj.equals(tblSach)) {
            int row = tblSach.getSelectedRow();
            modelSach = (DefaultTableModel) tblSach.getModel();
            txtMaSach.setText(modelSach.getValueAt(row, 1).toString());
            txtTenSach.setText(modelSach.getValueAt(row, 2).toString());
            cmbTheLoai.setSelectedItem(modelSach.getValueAt(row, 3).toString());
            cmbTacGia.setSelectedItem(modelSach.getValueAt(row, 4).toString());
            txtGiaBanSach.setText(modelSach.getValueAt(row, 5).toString());
        } else if (obj.equals(tableVPP)) {
            int row1 = tableVPP.getSelectedRow();
            txtMaVPP.setText(modelVPP.getValueAt(row1, 1).toString());
            txtTenVPP.setText(modelVPP.getValueAt(row1, 2).toString());
            cmbChatLieu.setSelectedItem(modelVPP.getValueAt(row1, 3).toString());
            cmbXuatXu.setSelectedItem(modelVPP.getValueAt(row1, 4).toString());
            txtGiaBanVPP.setText(modelVPP.getValueAt(row1, 5).toString());
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @SneakyThrows
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        Object obj = e.getSource();
        if (obj.equals(btnRefresh)) {
            clearTxtFieldsSDT();
        } else if (obj.equals(btnTimKhachHang)) {
            KhachHang kh = new KhachHang();
            sdt = txtSDT.getText().toString();
            try {
                kh = khachHangService.timKhachHangBangSDT(sdt);
            } catch (Exception e2) {
                // TODO: handle exception
                e2.printStackTrace();
            }
            if (sdt.length() <= 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại khách hàng");
                return;
            }
            if (kh != null) {
                txtTenKhachHang.setText(kh.getHoTenKhachHang());
                return;
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng, vui lòng thêm khách hàng mới");
                WinThemKH winThemKH = new WinThemKH();
                winThemKH.setVisible(true);
                return;
            }
        } else if (obj.equals(btnLamMoiBang)) {

            if (tabSanPham.getSelectedIndex() == 0) {
                try {
                    clearTableSach();
                    docDuLieuSach();
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            } else {
                clearTableVPP();
                docDuLieuVPP();
            }

        } else if (obj.equals(btnThemSach)) {
            int row = tblSach.getSelectedRow();
            if (txtSoLuongSach.getText().isEmpty() || !(txtSoLuongSach.getText().matches("[0-9]*"))) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng cần thêm phù hợp");
                return;
            }

            if (tblSach.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần mua");
                return;
            }
            int soLuongThem = Integer.parseInt(txtSoLuongSach.getText());
            if ((Integer.parseInt(modelSach.getValueAt(row, 6).toString()) - soLuongThem) < 0) {
                JOptionPane.showMessageDialog(this, "Số lượng sách trong kho không đủ, vui lòng chọn sản phẩm khác");
                return;
            } else {
                try {
                    themSachVaoGioHang();
                    truSLSachKhiThem();
                    txtSoLuongSach.setText("1");
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        } else if (obj.equals(btnThemVPP)) {
            int row = tblSach.getSelectedRow();
            if (txtSoLuongVPP.getText().isEmpty() || !(txtSoLuongVPP.getText().matches("[0-9]*"))) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng cần thêm phù hợp");
                return;
            }
            if (tableVPP.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần mua");
                return;
            }
            int soLuongThem = Integer.parseInt(txtSoLuongVPP.getText());
            if ((Integer.parseInt(modelVPP.getValueAt(row, 6).toString()) - soLuongThem) < 0) {
                JOptionPane.showMessageDialog(this, "Số lượng văn phòng phẩm trong kho không đủ, vui lòng chọn sản phẩm khác");
                return;
            } else {
                try {
                    themHDVPP();
                    truSLVPPKhiThem();
                    txtSoLuongVPP.setText("1");

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }

        } else if (obj.equals(btnThanhToan)) {
            if (txtTenKhachHang.getText().length() <= 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại khách hàng để hoàn tất thanh toán");
                return;
            }
            if (txtTienThua.getText().length() <= 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập tiền khách đưa");
                return;
            }
            if (Double.parseDouble(txtTienThua.getText()) < 0) {
                JOptionPane.showMessageDialog(this, "Số tiền nhận vào chưa đủ, vui lòng kiểm tra lại");
                return;
            } else {
                try {
                    themHoaDon();
                    themCTHD();
                    JOptionPane.showMessageDialog(this, "Thanh toán hoàn tất!");
                    clearTableCTHD();
                    clearTxtFieldsSDT();
                    lblMaHD.setText(auto_ID());
                    txtMaHoaDon.setText(auto_ID());
                    clearTongTien();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        } else if (obj.equals(btnThemHangCho)) {
            // kiểm tra điều kiện
            if (txtTenKhachHang.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập thông tin khách hàng", "Báo lỗi",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (modelHoaDon.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm", "Báo lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // xử lý
            System.out.println("debug");
            if (dsHoaDonCho.containsKey(txtSDT.getText().trim())) {
                // đã có người dùng trong hàng chờ
                for (int i = 0; i < modelHoaDon.getRowCount(); i++) {
                    boolean kiemTraTrung = false;

//					mã sản phẩm và số lượng tồn

//					lấy được sản phẩm qua mã sp lọc kiểm tra sách hay văn phòng phẩm khởi tạo constructor tương ứng và gán giá trị

                    SanPham spTimDuoc = sanPhamService.timSanPhamTheoMa(modelHoaDon.getValueAt(i, 1).toString());
                    SanPham sp;
                    if (spTimDuoc.getLoaiSanPham().equals("Sách")) {
                        sp = new Sach(modelHoaDon.getValueAt(i, 1).toString(), Integer.parseInt(modelHoaDon.getValueAt(i, 4).toString()));
                    } else {
                        sp = new VanPhongPham(modelHoaDon.getValueAt(i, 1).toString(), Integer.parseInt(modelHoaDon.getValueAt(i, 4).toString()));
                    }

                    for (int j = 0; j < dsHoaDonCho.get(txtSDT.getText().trim()).size(); j++) {
                        // trùng sản phẩm -> số lượng tăng
                        if (sp.getMaSanPham().equals(dsHoaDonCho.get(txtSDT.getText().trim()).get(j).getMaSanPham())) {
                            int soLuong = dsHoaDonCho.get(txtSDT.getText().trim()).get(j).getSoLuongTon();
                            dsHoaDonCho.get(txtSDT.getText().trim()).get(j)
                                    .setSoLuongTon(sp.getSoLuongTon() + soLuong);
                            kiemTraTrung = true;
                            break;
                        }
                    }
                    if (!kiemTraTrung) {
                        dsHoaDonCho.get(txtSDT.getText().trim()).add(sp);
                    }
                }
            } else {
                // chưa có người dùng trong hàng chờ
                ArrayList<SanPham> dsSanPhamCho = new ArrayList<>();
                for (int i = 0; i < modelHoaDon.getRowCount(); i++) {
                    SanPham spTimDuoc = sanPhamService.timSanPhamTheoMa(modelHoaDon.getValueAt(i, 1).toString());
                    SanPham sp;
                    if (spTimDuoc.getLoaiSanPham().equals("Sách")) {
                        sp = new Sach(modelHoaDon.getValueAt(i, 1).toString(), Integer.parseInt(modelHoaDon.getValueAt(i, 4).toString()));
                    } else {
                        sp = new VanPhongPham(modelHoaDon.getValueAt(i, 1).toString(), Integer.parseInt(modelHoaDon.getValueAt(i, 4).toString()));
                    }
                    dsSanPhamCho.add(sp);
                }
                dsHoaDonCho.put(txtSDT.getText().trim(), dsSanPhamCho);
            }
            clearTxtFieldsSDT();
            setTongTienRong();
            modelHoaDon.setRowCount(0);
            JOptionPane.showMessageDialog(null, "Thêm vào hàng chờ thành công!!", "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);
        } else if (obj.equals(btnHangCho)) {
            openFrmHoaDonCho();
        } else if (obj.equals(btnThemKhachHang)) {
            WinThemKH winThemKH = new WinThemKH();
            winThemKH.setVisible(true);
        } else if (obj.equals(btnXoa)) {
            int row = tblHoaDon.getSelectedRow();
            if (txtXoa.getText().length() <= 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng xoá");
                return;
            }
            int soLuongXoa = Integer.parseInt(txtXoa.getText());
            if ((Integer.parseInt(modelHoaDon.getValueAt(row, 4).toString()) - soLuongXoa) <= 0) {
                modelHoaDon.removeRow(row);
                return;
            } else {
                truSoLuongTrongGioHang();
                tongTienHandler();
                tongTienVAT();
                txtXoa.setText("1");
            }


        } else if (obj.equals(btnHuyHoaDon)) {
            clearTableCTHD();
            clearTxtFieldsSDT();
            setTongTienRong();
            try {
                docDuLieuSach();
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            docDuLieuVPP();
        }
    }

    public void docDuLieuSach() throws Exception {
        clearTableSach();

        dsSach = sanPhamService.getAllSach();
        String tacGia = "";
        int i = 1;
        for (Sach sach : dsSach) {
            if (sach.getTacGia() == null) {
                tacGia = "Không có tác giả";
            } else {
                tacGia = sach.getTacGia().getTenTacGia();
            }
            modelSach.addRow(new Object[]{i++, sach.getMaSanPham(), sach.getTenSach(),
                    sach.getTheLoaiSach().getTenLoai(), tacGia, sach.tinhGiaBan(), sach.getSoLuongTon()});
        }
    }

    public void docDuLieuVPP() throws Exception {
        clearTableVPP();
        dsVPP = sanPhamService.getAllVPP();
        int i = 1;
        for (VanPhongPham vpp : dsVPP) {
            modelVPP.addRow(new Object[]{i++, vpp.getMaSanPham(), vpp.getTenVanPhongPham(),
                    vpp.getChatLieu().getTenChatLieu(), vpp.getXuatXu().getTenXuatXu(), vpp.tinhGiaBan(),
                    vpp.getSoLuongTon()});
        }
    }

    public void docDuLieuHoaDon() throws Exception {
        if (hoaDonService.getHoaDonTheoMa(txtMaHoaDon.getText()).size() == 0) {
            return;
        } else {
            HoaDon hd = hoaDonService.getHoaDonTheoMa(txtMaHoaDon.getText()).get(0);
            dsChiTietHoaDon = chiTietHoaDonService.getCTHoaDonTheoMaHoaDon(txtMaHoaDon.getText());
            if (dsChiTietHoaDon.size() == 0) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn");
                return;
            } else {
                int i = 1;
                for (ChiTietHoaDon cthd : dsChiTietHoaDon) {
                    if (sanPhamService.getSachTheoMaSP(cthd.getSanPham().getMaSanPham()).getTenSach() != null) {
                        modelHoaDon.addRow(new Object[]{i++,
                                sanPhamService.getSachTheoMaSP(cthd.getSanPham().getMaSanPham()).getMaSanPham(),
                                sanPhamService.getSachTheoMaSP(cthd.getSanPham().getMaSanPham()).getTenSach(),
                                cthd.getSoLuong(), cthd.getDonGia()});
                    } else {
                        JOptionPane.showMessageDialog(this, "Hóa đơn này không có sách để đổi");
                        return;
                    }
                }
                JOptionPane.showMessageDialog(this, "Đã tìm thấy");
            }
        }

    }

    public void truSLSachKhiDoi() throws Exception {
        Sach s = sanPhamService.getSachTheoTen(txtTenSach.getText());
        s.setSoLuongTon(s.getSoLuongTon() - Integer.parseInt(txtSoLuongSach.getText().toString()));

        try {
            sanPhamService.capNhatSanPham(s.getMaSanPham(), s);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public boolean tonTaiSanPhamTrongCTHD(SanPham sp) {
        int soLuongS = 0;
        int soLuongVPP = 0;
        if (tblHoaDon.getRowCount() < 1) {
            return false;
        }
        for (int i = 0; i < tblHoaDon.getRowCount(); i++) {
            if (tblHoaDon.getValueAt(i, 1).equals(sp.getMaSanPham())) {
                if (!(txtSoLuongSach.getText().trim().isEmpty())) {
                    soLuongS = Integer.parseInt(txtSoLuongSach.getText());
                } else if (!(txtSoLuongVPP.getText().trim().isEmpty())) {
                    soLuongVPP = Integer.parseInt(txtSoLuongVPP.getText());
                } else
                    return false;

                int tongSP = Integer.parseInt(tblHoaDon.getValueAt(i, 4).toString()) + soLuongS + soLuongVPP;
                tblHoaDon.setValueAt(tongSP, i, 4);
                return true;
            }
        }
        return false;
    }

    public void truSLVPPKhiDoi() throws Exception {
        VanPhongPham v = sanPhamService.getVPPTheoTen(txtTenVPP.getText());
        v.setSoLuongTon(v.getSoLuongTon() - Integer.parseInt(txtSoLuongVPP.getText().toString()));
        try {
            sanPhamService.capNhatSanPham(v.getMaSanPham(), v);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void themHoaDon() throws Exception {
        String mahd = txtMaHoaDon.getText();
        WinLogin dangNhap = new WinLogin();
        TaiKhoan taiKhoan = dangNhap.getTaiKhoanDangNhapThanhCong();

        nv = nhanVienService.timNhanVienTheoMa(taiKhoan.getNhanVien().getMaNhanVien());
        KhachHang kh = khachHangService.timKhachHangBangSDT(txtSDT.getText());
        String sdt = txtSDT.getText();
        LocalDate ngayLapHoaDon = LocalDate.now();
        String ghiChu = "Không";
        Long tienKhachDua = Long.parseLong(txtTienKhachDua.getText().trim());
        Boolean tinhTrang = true;
        HoaDon hd = new HoaDon(mahd, nv, kh, ngayLapHoaDon, ghiChu, tienKhachDua, tinhTrang);
        hoaDonService.themHoaDon(hd);

    }

    public void themCTHD() throws Exception {

        HoaDon hd = hoaDonService.getHoaDonTheoMa(txtMaHoaDon.getText()).get(0);
        List<ChiTietHoaDon> listCTHD = new ArrayList<ChiTietHoaDon>();
        for (int i = 0; i < tblHoaDon.getRowCount(); i++) {
            sanPhamService = new SanPhamServiceImpl();
            String masp = tblHoaDon.getValueAt(i, 1).toString();
            SanPham sp = sanPhamService.getSanPhamTheoMa(masp);
            int soLuong = Integer.parseInt(modelHoaDon.getValueAt(i, 4).toString());
            long giaBan = Long.parseLong(modelHoaDon.getValueAt(i, 3).toString());
            ChiTietHoaDon cthd = new ChiTietHoaDon(hd, sp, soLuong, giaBan);
            listCTHD.add(cthd);
            if (chiTietHoaDonService.addChiTietHoaDon(cthd))
                return;
        }

    }

    public void themSachVaoGioHang() throws Exception {
        int soLuongSach = Integer.parseInt(txtSoLuongSach.getText());
        dsChiTietHoaDon = new ArrayList<ChiTietHoaDon>();
        modelSach = (DefaultTableModel) tblSach.getModel();
        int row = tblSach.getSelectedRow();
        String maS = modelSach.getValueAt(row, 1).toString();
        Sach s = sanPhamService.getSachTheoMaSP(maS);
        if (s != null) {
            if (!tonTaiSanPhamTrongCTHD(s)) {
                modelHoaDon = (DefaultTableModel) tblHoaDon.getModel();
                modelHoaDon.addRow(new Object[]{modelHoaDon.getRowCount() + 1, s.getMaSanPham(), s.getTenSach(),
                        s.tinhGiaBan(), soLuongSach});
            }
        }
        tongTienHandler();
        tongTienVAT();
    }

    public void themHDVPP() throws Exception {
        int soLuongV = Integer.parseInt(txtSoLuongVPP.getText());
        dsChiTietHoaDon = new ArrayList<ChiTietHoaDon>();
        modelVPP = (DefaultTableModel) tableVPP.getModel();
        int row = tableVPP.getSelectedRow();
        String maV = modelVPP.getValueAt(row, 1).toString();
        VanPhongPham v = sanPhamService.getVPPTheoMaSP(maV);
        if (v != null) {
            if (!tonTaiSanPhamTrongCTHD(v)) {
                modelHoaDon = (DefaultTableModel) tblHoaDon.getModel();
                modelHoaDon.addRow(new Object[]{modelHoaDon.getRowCount() + 1, v.getMaSanPham(),
                        v.getTenVanPhongPham(), v.tinhGiaBan(), soLuongV});
            }
        }
        tongTienHandler();
        tongTienVAT();
    }

    public void tongTienHandler() {
        long tongTien = 0;

        for (int i = 0; i < tblHoaDon.getRowCount(); i++) {
            long thanhTien = Long.parseLong(tblHoaDon.getValueAt(i, 3).toString())
                    * Long.parseLong(tblHoaDon.getValueAt(i, 4).toString());
            tongTien += thanhTien;
        }

        txtTongTienHD.setText(tongTien + "");
    }

    public void tongTienVAT() {
        double thanhTienVAT = 0;
        thanhTienVAT = Long.parseLong(txtTongTienHD.getText().trim())
                + Long.parseLong(txtTongTienHD.getText().trim()) * 0.08;

        txtVAT.setText(thanhTienVAT + "");

    }

    public void tinhTienThua() {
        double tienThua = 0;
        tienThua = Double.parseDouble(txtTienKhachDua.getText().trim()) - Double.parseDouble(txtVAT.getText().trim());
        txtTienThua.setText(tienThua + "");
    }

    public void clearTxtFieldsSach() {
        txtMaSach.setText("");
        txtTenSach.setText("");
        cmbTheLoai.setSelectedIndex(0);
        cmbTacGia.setSelectedIndex(0);
        txtGiaBanSach.setText("");
        txtSoLuongSach.setText("1");
    }

    public void clearTxtFieldsVPP() {
        txtMaVPP.setText("");
        txtTenVPP.setText("");
        cmbChatLieu.setSelectedIndex(0);
        cmbXuatXu.setSelectedIndex(0);
        txtGiaBanVPP.setText("");
        txtSoLuongVPP.setText("1");
    }

    public void clearTxtFieldsSDT() {
        txtSDT.setText("");
        txtTenKhachHang.setText("");
    }

    public void clearTongTien() {
        txtTongTienHD.setText("");
        txtVAT.setText("");
        txtTienKhachDua.setText("");
        txtTienThua.setText("");
    }

    public void tableDanhSachSachWithFilter() throws Exception {
        clearTableSach();
        DefaultTableModel model = (DefaultTableModel) tblSach.getModel();
        String maSach = txtTimKiemMaSP.getText().trim();
        String tenSach = txtTimKiemTenSP.getText().trim();

        List<Sach> dsSach = sanPhamService.getAllSach();
        String tacGia = "";
        int i = 0;

        for (Sach s : dsSach) {
            if (s.getTacGia() == null) {
                tacGia = "Không có tác giả";
            } else {
                tacGia = s.getTacGia().getTenTacGia();
            }
            if (removeAccent(s.getMaSanPham()).toLowerCase().contains(removeAccent(maSach).toLowerCase())
                    && removeAccent(s.getTenSach()).toLowerCase().contains(removeAccent(tenSach).toLowerCase())) {

                Object[] rowData = {i++, s.getMaSanPham(), s.getTenSach(), s.getTheLoaiSach().getTenLoai(),
                        tacGia, s.tinhGiaBan(), s.getSoLuongTon()};
                model.addRow(rowData);
            }
        }
    }

    public void tableDanhSachVPPWithFilter() throws Exception{
        tableVPP.clearSelection();
        clearTableVPP();
        DefaultTableModel model = (DefaultTableModel) tableVPP.getModel();
        String mavpp = txtTimKiemMaSP.getText().trim();
        String tenvpp = txtTimKiemTenSP.getText().trim();


        List<VanPhongPham> dsVPP = sanPhamService.getAllVPP();
        int i = 1;

        for (VanPhongPham v : dsVPP) {
            if (removeAccent(v.getMaSanPham()).toLowerCase().contains(removeAccent(mavpp).toLowerCase())
                    && removeAccent(v.getTenVanPhongPham()).toLowerCase()
                    .contains(removeAccent(tenvpp).toLowerCase())) {
                Object[] rowData = {i++, v.getMaSanPham(), v.getTenVanPhongPham(), v.getChatLieu().getTenChatLieu(),
                        v.getXuatXu().getTenXuatXu(), v.tinhGiaBan(), v.getSoLuongTon()};
                model.addRow(rowData);
            }
        }
    }

    public void clearTableSach() {
        DefaultTableModel model = (DefaultTableModel) tblSach.getModel();
        model.setRowCount(0);

    }

    public void clearTableVPP() {
        DefaultTableModel model = (DefaultTableModel) tableVPP.getModel();
        model.setRowCount(0);
    }

    public void clearTableCTHD() {
        DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
        model.setRowCount(0);
    }

    private static String removeAccent(String s) {

        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }

    public static HashMap<String, ArrayList<SanPham>> getDsHoaDonCho() {
        return dsHoaDonCho;
    }

    public static void getListHoaDonCho(HashMap<String, ArrayList<SanPham>> listHD) {
        dsHoaDonCho = listHD;
    }

    public void openFrmHoaDonCho() {
        new Thread(() -> {
            ShareData shareData = new ShareData(dsHoaDonCho);

            synchronized (shareData) {
                try {
                    winHangCho = new WinHangCho(dsHoaDonCho, shareData);
                    winHangCho.setVisible(true);
                    shareData.wait();
                    dsHoaDonCho = shareData.getDsHoaDonCho();
                    if (shareData.isThanhToan()) {
                        modelHoaDon.setRowCount(0);
                        String sdt = shareData.getSdtThanhToan();
                        try {
                            KhachHang khachHang = khachHangService.timKhachHangBangSDT(sdt);
                            txtSDT.setText(khachHang.getsDT());
                            txtTenKhachHang.setText(khachHang.getHoTenKhachHang());
                            ArrayList<SanPham> dsSanPham = new ArrayList<>();
                            dsSanPham = shareData.getDsSanPhamThanhToanTiep();
                            for (int i = 0; i < dsSanPham.size(); i++) {
                                Sach sach = null;
                                VanPhongPham vanPhongPham = null;
                                sach = sanPhamService.timSanPhamTheoMaSach(dsSanPham.get(i).getMaSanPham());
                                vanPhongPham = sanPhamService
                                        .timSanPhamTheoMaVPP(dsSanPham.get(i).getMaSanPham());

                                if (sach != null) {
                                    Object[] o = {i + 1 + "", sach.getMaSanPham(), sach.getTenSach(),
                                            sach.tinhGiaBan() + "", dsSanPham.get(i).getSoLuongTon()};
                                    modelHoaDon.addRow(o);
                                }
                                if (vanPhongPham != null) {
                                    Object[] o = {i + 1 + "", vanPhongPham.getMaSanPham(),
                                            vanPhongPham.getTenVanPhongPham(), vanPhongPham.tinhGiaBan() + "",
                                            dsSanPham.get(i).getSoLuongTon()};
                                    modelHoaDon.addRow(o);
                                }
                            }
                            tongTienHandler();
                            tongTienVAT();
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }

                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    public void setTongTienRong() {
        txtTongTienHD.setText("");
        txtVAT.setText("");
    }

    public String auto_ID() throws Exception {

        String idPrefix = "HD";
        LocalDate myObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd");
        String formattedString = myObj.format(formatter);
        int length = 0;
        length = hoaDonService.getDSHoaDon().size();
        String finalId = idPrefix + formattedString + String.format("%05d", length + 1);
        return finalId;
    }

    public static String auto_Date() {
        LocalDate myObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd - LL - yyyy");
        String formattedString = myObj.format(formatter);
        return formattedString;
    }

    public void truSLSachKhiThem() {
        int soLuongThem = Integer.parseInt(txtSoLuongSach.getText());
        int row = tblSach.getSelectedRow();
        modelSach = (DefaultTableModel) tblSach.getModel();
        modelSach.setValueAt(Integer.parseInt(modelSach.getValueAt(row, 6).toString()) - soLuongThem, row, 6);


    }

    public void truSLVPPKhiThem() {
        int soLuongThem = Integer.parseInt(txtSoLuongVPP.getText());
        int row = tableVPP.getSelectedRow();
        modelVPP = (DefaultTableModel) tableVPP.getModel();
        modelVPP.setValueAt(Integer.parseInt(modelVPP.getValueAt(row, 6).toString()) - soLuongThem, row, 6);
    }

    public void truSoLuongTrongGioHang() {
        int soLuongCanXoa = Integer.parseInt(txtXoa.getText());

        int row = tblHoaDon.getSelectedRow();
        modelHoaDon = (DefaultTableModel) tblHoaDon.getModel();
        modelSach = (DefaultTableModel) tblSach.getModel();
        modelVPP = (DefaultTableModel) tableVPP.getModel();
        int tongSP = Integer.parseInt(tblHoaDon.getValueAt(row, 4).toString()) - soLuongCanXoa;
        tblHoaDon.setValueAt(tongSP, row, 4);
        for (int i = 0; i < modelSach.getRowCount(); i++) {
            if (modelSach.getValueAt(i, 1).toString().equals(modelHoaDon.getValueAt(row, 1).toString())) {
                modelSach.setValueAt(Integer.parseInt(modelSach.getValueAt(i, 6).toString()) + soLuongCanXoa, i, 6);
            }
        }
        for (int j = 0; j < modelVPP.getRowCount(); j++) {
            if (modelVPP.getValueAt(j, 1).toString().equals(modelHoaDon.getValueAt(row, 1).toString())) {
                modelVPP.setValueAt(Integer.parseInt(modelVPP.getValueAt(j, 6).toString()) + soLuongCanXoa, j, 6);
            }
        }
        if (tongSP <= 0) {
            modelHoaDon.removeRow(row);
        }
    }

    public void themSoLuongSanPhamVaoKho() throws Exception {
        int soLuongCanXoa = Integer.parseInt(txtXoa.getText());
        modelSach = (DefaultTableModel) tblSach.getModel();
        modelVPP = (DefaultTableModel) tableVPP.getModel();
        modelHoaDon = (DefaultTableModel) tblHoaDon.getModel();
        int row = tblHoaDon.getSelectedRow();
        for (int i = 0; i < modelSach.getRowCount(); i++) {
            if (modelSach.getValueAt(i, 1).toString().equals(modelHoaDon.getValueAt(row, 1).toString())) {
                SanPham sp = sanPhamService.timSanPhamTheoMa(modelHoaDon.getValueAt(row, 1).toString());
                int soLuongBanDau = sp.getSoLuongTon();
                sp.setSoLuongTon(soLuongBanDau + soLuongCanXoa);
                sanPhamService.capNhatSoLuongSanPham(sp);
            }
            if (modelVPP.getValueAt(i, 1).toString().equals(modelHoaDon.getValueAt(row, 1).toString())) {
                SanPham sp = sanPhamService.timSanPhamTheoMa(modelHoaDon.getValueAt(row, 1).toString());
                int soLuongBanDau = sp.getSoLuongTon();
                sp.setSoLuongTon(soLuongBanDau + soLuongCanXoa);
                sanPhamService.capNhatSoLuongSanPham(sp);
            }
        }
    }

    public void truSoLuongKhiThanhToan() throws Exception {
        modelHoaDon = (DefaultTableModel) tblHoaDon.getModel();
        for (int i = 0; i < modelHoaDon.getRowCount(); i++) {
            SanPham sp = sanPhamService.timSanPhamTheoMa(modelHoaDon.getValueAt(i, 1).toString());
            int soLuongBanDau = sp.getSoLuongTon();
            sp.setSoLuongTon(soLuongBanDau - Integer.parseInt(modelHoaDon.getValueAt(i, 4).toString()));
            sanPhamService.capNhatSoLuongSanPham(sp);
        }

    }
}
