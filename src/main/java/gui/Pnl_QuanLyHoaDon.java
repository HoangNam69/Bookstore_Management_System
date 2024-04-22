package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import dao.HoaDonDao;
import dao.HoaDonDoiTraDao;
import entities.HoaDon;
import entities.HoaDonDoiTra;
import entities.KhachHang;
import lombok.SneakyThrows;
import service.*;
import util.Constants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.Naming;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

public class Pnl_QuanLyHoaDon extends JPanel implements ActionListener, MouseListener {

	/**
	 * Create the panel.
	 */

    private static final long serialVersionUID = 1L;
    private DefaultTableModel modelHoaDon;
    private JTable tblHoaDon;
    private JScrollPane scrHoaDon;
    private JTextField txtMaHoaDon;
    private JTextField txtNgayLap;
    private JTextField txtTenNhanVien;
    private JTextField txtTenNVTim;
    private List<HoaDon> dsHoaDon;
    private List<KhachHang> dsKhachHang;
    private WinXemChiTietHoaDon winXemChiTietHoaDon;

    private JButton btnRefresh;
    private JButton btnFind;
    private JButton btnXemChiTiet;
    private JButton btnIn;
    ButtonGroup btnGroup;
    JRadioButton radHoaDonDoiTra;
    JRadioButton radHoaDonThuong;
    private JTextField txtTenKHTim;
    private JLabel lblMaHoaDon;
    private JTextField txtMahoaDonTim;
    private JLabel lblSDTKhachHang;
    private JTextField txtSDTTim;
    //	Flag loại hóa đơn
    int flag = 1; // Mặc định là hóa đơn thường


    private HoaDonDoiTra hoaDonDoiTra;
    private List<HoaDonDoiTra> dsHoaDonDoiTra;
    private static final String URL = "rmi://"+ Constants.IPV4 + ":"+ Constants.PORT + "/";
    private SanPhamService sanPhamService = (SanPhamService) Naming.lookup(URL + "sanPham");
    private HoaDonService hoaDonService = (HoaDonService)Naming.lookup(URL + "hoaDon");
    private ChiTietHoaDonService chiTietHoaDonService = (ChiTietHoaDonService)Naming.lookup(URL + "chiTietHoaDon");
    private HoaDonDoiTraService hoaDonDoiTraService = (HoaDonDoiTraService)Naming.lookup(URL + "hoaDonDoiTra");


    public Pnl_QuanLyHoaDon() throws Exception {
        setBackground(new Color(0, 206, 209));
        setFont(new Font("Dialog", Font.BOLD, 16));
        setSize(1800, 900);
        setLayout(null);

        JLabel lblTitle = new JLabel("QUẢN LÝ HÓA ĐƠN ");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setForeground(Color.DARK_GRAY);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 36));
        lblTitle.setBounds(10, 11, 1460, 55);
        add(lblTitle);

        JPanel pnlAddress = new JPanel();
        pnlAddress.setBorder(new LineBorder(new Color(0, 0, 0)));
        pnlAddress.setBackground(new Color(255, 255, 255));
        pnlAddress.setBounds(10, 126, 418, 600);
        add(pnlAddress);
        pnlAddress.setLayout(null);

        txtTenNVTim = new JTextField();
        txtTenNVTim.setHorizontalAlignment(SwingConstants.CENTER);
        txtTenNVTim.setForeground(new Color(0, 0, 0));
        txtTenNVTim.setFont(new Font("Tahoma", Font.PLAIN, 16));
        txtTenNVTim.setColumns(10);
        txtTenNVTim.setBounds(141, 247, 267, 33);
        pnlAddress.add(txtTenNVTim);

        JLabel lblTenKhachHang = new JLabel("Tên khách hàng:");
        lblTenKhachHang.setHorizontalAlignment(SwingConstants.LEFT);
        lblTenKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblTenKhachHang.setBounds(10, 461, 121, 33);
        pnlAddress.add(lblTenKhachHang);

        JLabel lblSDT = new JLabel("Tên nhân viên:");
        lblSDT.setHorizontalAlignment(SwingConstants.LEFT);
        lblSDT.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblSDT.setBounds(10, 247, 121, 33);
        pnlAddress.add(lblSDT);

        txtTenKHTim = new JTextField();
        txtTenKHTim.setHorizontalAlignment(SwingConstants.CENTER);
        txtTenKHTim.setForeground(Color.BLACK);
        txtTenKHTim.setFont(new Font("Tahoma", Font.PLAIN, 16));
        txtTenKHTim.setColumns(10);
        txtTenKHTim.setBounds(141, 461, 267, 33);
        pnlAddress.add(txtTenKHTim);

        JPanel pnlTimKiem = new JPanel();
        pnlTimKiem.setLayout(null);
        pnlTimKiem.setBorder(null);
        pnlTimKiem.setBackground(Color.LIGHT_GRAY);
        pnlTimKiem.setBounds(0, 63, 418, 46);
        pnlAddress.add(pnlTimKiem);

        JLabel lblTimKiem_1 = new JLabel("Tìm kiếm thông tin hóa đơn");
        lblTimKiem_1.setBounds(0, 0, 418, 46);
        pnlTimKiem.add(lblTimKiem_1);
        lblTimKiem_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblTimKiem_1.setForeground(Color.BLACK);
        lblTimKiem_1.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTimKiem_1.setBackground(Color.LIGHT_GRAY);

        lblMaHoaDon = new JLabel("Mã hóa đơn:");
        lblMaHoaDon.setHorizontalAlignment(SwingConstants.LEFT);
        lblMaHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblMaHoaDon.setBounds(10, 139, 121, 33);
        pnlAddress.add(lblMaHoaDon);

        txtMahoaDonTim = new JTextField();
        txtMahoaDonTim.setHorizontalAlignment(SwingConstants.CENTER);
        txtMahoaDonTim.setForeground(Color.BLACK);
        txtMahoaDonTim.setFont(new Font("Tahoma", Font.PLAIN, 16));
        txtMahoaDonTim.setColumns(10);
        txtMahoaDonTim.setBounds(141, 139, 267, 33);
        pnlAddress.add(txtMahoaDonTim);

        lblSDTKhachHang = new JLabel("Số điện thoại KH:");
        lblSDTKhachHang.setHorizontalAlignment(SwingConstants.LEFT);
        lblSDTKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblSDTKhachHang.setBounds(10, 352, 121, 33);
        pnlAddress.add(lblSDTKhachHang);

        txtSDTTim = new JTextField();
        txtSDTTim.setHorizontalAlignment(SwingConstants.CENTER);
        txtSDTTim.setForeground(Color.BLACK);
        txtSDTTim.setFont(new Font("Tahoma", Font.PLAIN, 16));
        txtSDTTim.setColumns(10);
        txtSDTTim.setBounds(141, 352, 267, 33);
        pnlAddress.add(txtSDTTim);

        radHoaDonThuong = new JRadioButton("Hóa đơn thường");
        radHoaDonThuong.setSelected(true);
        radHoaDonThuong.setFont(new Font("Tahoma", Font.PLAIN, 14));
        radHoaDonThuong.setBounds(41, 12, 135, 37);
        pnlAddress.add(radHoaDonThuong);

        radHoaDonDoiTra = new JRadioButton("Hóa đơn đổi trả");
        radHoaDonDoiTra.setFont(new Font("Tahoma", Font.PLAIN, 14));
        radHoaDonDoiTra.setBounds(231, 12, 128, 37);
        pnlAddress.add(radHoaDonDoiTra);

        btnGroup = new ButtonGroup();
        btnGroup.add(radHoaDonThuong);
        btnGroup.add(radHoaDonDoiTra);

        radHoaDonDoiTra.setMnemonic(KeyEvent.VK_C);
        radHoaDonThuong.setMnemonic(KeyEvent.VK_M);

        radHoaDonThuong.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) {
                    setColumnName("Mã hóa đơn", "Thành tiền");
                    modelHoaDon.setRowCount(0);
                    try {
                        docDuLieuTuArrayListVaoModel();
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    System.out.println("Chọn Hóa đơn thường");
                    flag = 1;
                }

            }
        });
        radHoaDonDoiTra.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) {
                    setColumnName("Mã hóa đơn đổi trả", "Tiền phải trừ");
                    modelHoaDon.setRowCount(0);
                    try {
                        docDuLieuHoaDonDoiTraTuArrayListVaoModel();
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    System.out.println("Chọn Hóa đơn doi");
                    flag = 0;
                }

            }
        });
        if (flag == 1) {
            String[] cols = {"STT", "Mã hóa đơn", "Nhân viên lâp", "Ngày lập", "Tên khách hàng", "Thành tiền"};
            modelHoaDon = new DefaultTableModel(cols, 0);
            tblHoaDon = new JTable(modelHoaDon);
            tblHoaDon.setBorder(new LineBorder(new Color(0, 0, 0)));
            tblHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 14));

            scrHoaDon = new JScrollPane(tblHoaDon);
            scrHoaDon.setBounds(448, 76, 1320, 650);
            tblHoaDon.getTableHeader().setBackground(Color.LIGHT_GRAY);
            tblHoaDon.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 17));
            tblHoaDon.setRowHeight(25);
            tblHoaDon.setBackground(Color.WHITE);
            scrHoaDon.getViewport().setBackground(Color.WHITE);
            tblHoaDon.getTableHeader().setPreferredSize(new Dimension(0, 40));
            add(scrHoaDon);
            try {
                docDuLieuTuArrayListVaoModel();
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        } else {
            String[] cols = {"STT", "Mã hóa đơn", "Nhân viên lâp", "Ngày lập", "Tên khách hàng", "Tiền trừ"};
            modelHoaDon = new DefaultTableModel(cols, 0);
            tblHoaDon = new JTable(modelHoaDon);
            tblHoaDon.setBorder(new LineBorder(new Color(0, 0, 0)));
            tblHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 14));

            scrHoaDon = new JScrollPane(tblHoaDon);
            scrHoaDon.setBounds(448, 76, 1320, 650);
            tblHoaDon.getTableHeader().setBackground(Color.LIGHT_GRAY);
            tblHoaDon.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 17));
            tblHoaDon.setRowHeight(25);
            tblHoaDon.setBackground(Color.WHITE);
            scrHoaDon.getViewport().setBackground(Color.WHITE);
            tblHoaDon.getTableHeader().setPreferredSize(new Dimension(0, 40));
            add(scrHoaDon);
        }

        JPanel pnlTimKiem_2 = new JPanel();
        pnlTimKiem_2.setBackground(Color.LIGHT_GRAY);
        pnlTimKiem_2.setBorder(null);
        pnlTimKiem_2.setBounds(10, 76, 418, 49);
        add(pnlTimKiem_2);
        pnlTimKiem_2.setLayout(null);
        JLabel lblTimKiem = new JLabel("Loại hóa đơn");
        lblTimKiem.setBounds(0, 0, 418, 49);
        pnlTimKiem_2.add(lblTimKiem);
        lblTimKiem.setHorizontalAlignment(SwingConstants.CENTER);
        lblTimKiem.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTimKiem.setForeground(Color.BLACK);
        lblTimKiem.setBackground(Color.LIGHT_GRAY);

        btnRefresh = new JButton("Làm mới");
        btnRefresh.setBounds(859, 750, 190, 40);
        add(btnRefresh);
        btnRefresh.setIcon(new ImageIcon(Pnl_QuanLyKhachHang.class.getResource("/gui/icon/refresh-button.png")));
        btnRefresh.setForeground(Color.BLACK);
        btnRefresh.setFont(new Font("Tahoma", Font.BOLD, 14));

        btnFind = new JButton("Tìm");
        btnFind.setBounds(104, 750, 205, 40);
        add(btnFind);
        btnFind.setIcon(new ImageIcon(Pnl_QuanLyKhachHang.class.getResource("/gui/icon/loupe.png")));
        btnFind.setForeground(Color.BLACK);
        btnFind.setFont(new Font("Tahoma", Font.BOLD, 14));

        btnXemChiTiet = new JButton("Xem chi tiết");
        btnXemChiTiet.setForeground(Color.BLACK);
        btnXemChiTiet.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnXemChiTiet.setIcon(new ImageIcon(Pnl_QuanLyKhachHang.class.getResource("/gui/icon/diskette.png")));
        btnXemChiTiet.setBounds(518, 750, 205, 40);
        add(btnXemChiTiet);

        btnIn = new JButton("In");
        btnIn.setForeground(Color.BLACK);
        btnIn.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnIn.setIcon(new ImageIcon(Pnl_QuanLyKhachHang.class.getResource("/gui/icon/diskette.png")));
        btnIn.setBounds(1204, 750, 205, 40);
        add(btnIn);

        TableColumnModel columnMode = tblHoaDon.getColumnModel();
        columnMode.getColumn(0).setPreferredWidth(10);

        // add action listener
        btnFind.addActionListener(this);
        btnRefresh.addActionListener(this);
        btnXemChiTiet.addActionListener(this);
        radHoaDonDoiTra.addMouseListener(this);
        radHoaDonThuong.addMouseListener(this);
        tblHoaDon.addMouseListener(this);
        btnIn.addActionListener(this);

//		SET FORCUS
        txtMahoaDonTim.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                txtSDTTim.setEditable(false);
                txtTenKHTim.setEditable(false);
                txtTenNVTim.setEditable(false);
            }

            @Override
            public void focusLost(FocusEvent e) {
                txtSDTTim.setEditable(true);
                txtTenKHTim.setEditable(true);
                txtTenNVTim.setEditable(true);
            }
        });

        txtSDTTim.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                txtMahoaDonTim.setEditable(false);
                txtTenKHTim.setEditable(false);
                txtTenNVTim.setEditable(false);
            }

            @Override
            public void focusLost(FocusEvent e) {
                txtMahoaDonTim.setEditable(true);
                txtTenKHTim.setEditable(true);
                txtTenNVTim.setEditable(true);
            }
        });
        txtTenKHTim.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                txtMahoaDonTim.setEditable(false);
                txtSDTTim.setEditable(false);
                txtTenNVTim.setEditable(false);
            }

            @Override
            public void focusLost(FocusEvent e) {
                txtMahoaDonTim.setEditable(true);
                txtSDTTim.setEditable(true);
                txtTenNVTim.setEditable(true);
            }
        });
        txtTenNVTim.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                txtMahoaDonTim.setEditable(false);
                txtSDTTim.setEditable(false);
                txtTenKHTim.setEditable(false);
            }

            @Override
            public void focusLost(FocusEvent e) {
                txtMahoaDonTim.setEditable(true);
                txtSDTTim.setEditable(true);
                txtTenKHTim.setEditable(true);
            }
        });
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object o = e.getSource();
        if (o.equals(radHoaDonDoiTra)) {
            setColumnName("Mã hóa đơn đổi trả", "Tiền phải trừ");
            modelHoaDon.setRowCount(0);
            try {
                docDuLieuHoaDonDoiTraTuArrayListVaoModel();
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        if (o.equals(radHoaDonThuong)) {
            setColumnName("Mã hóa đơn", "Thành tiền");
            modelHoaDon.setRowCount(0);
            try {
                docDuLieuTuArrayListVaoModel();
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
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
        if (flag == 1) {
            if (obj.equals(btnXemChiTiet)) {
                int row = tblHoaDon.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(null, "Chưa chọn dòng nào!!", "Thông báo",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    String maHoaDon = modelHoaDon.getValueAt(row, 1).toString();
                    System.out.println(maHoaDon);
                    String tenNhanVien = modelHoaDon.getValueAt(row, 2).toString();
                    String ngayLap = modelHoaDon.getValueAt(row, 3).toString();
                    String tenKhachHang = modelHoaDon.getValueAt(row, 4).toString();
                    String tienKhachDua = "";
                    String ghiChu = "";
                    String tongTienHoaDon = "";

                    HoaDon hd = hoaDonService.timHoaDonTheoMa(maHoaDon);
                    tienKhachDua = hd.getTienKhachDua() + "";
                    tongTienHoaDon = tongTienHoaDon(maHoaDon) + "";
                    ghiChu = hd.getGhiChu();


                    new WinXemChiTietHoaDon(maHoaDon, tenNhanVien, ngayLap, tenKhachHang, tienKhachDua, tongTienHoaDon,
                            ghiChu).setVisible(true);
                }
            } else if (obj.equals(btnFind)) {

                if (txtMahoaDonTim.getText().equals("") && txtTenKHTim.getText().equals("")
                        && txtSDTTim.getText().equals("") && txtTenNVTim.getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin tìm kiếm");
                } else if (!txtMahoaDonTim.getText().equals("") && txtTenKHTim.getText().equals("")
                        && txtSDTTim.getText().equals("") && txtTenNVTim.getText().equals("")) {
                    String maTim = txtMahoaDonTim.getText().trim();
                    try {
                        docDuLieuTimKiemTuArrayListVaoModelTheoMa(maTim);
                        xoaTrang();
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                } else if (txtMahoaDonTim.getText().equals("") && txtTenKHTim.getText().equals("")
                        && txtSDTTim.getText().equals("") && !txtTenNVTim.getText().equals("")) {
                    String tenTim = txtTenNVTim.getText().trim();
                    try {
                        docDuLieuTimKiemTuArrayListVaoModelTheoTen(tenTim);
                        xoaTrang();
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                } else if (txtMahoaDonTim.getText().equals("") && txtTenKHTim.getText().equals("")
                        && !txtSDTTim.getText().equals("") && txtTenNVTim.getText().equals("")) {
                    String sdtTim = txtSDTTim.getText().trim();
                    try {
                        docDuLieuTimKiemTuArrayListVaoModelTheoSDT(sdtTim);
                        xoaTrang();
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                } else if (txtMahoaDonTim.getText().equals("") && !txtTenKHTim.getText().equals("")
                        && txtSDTTim.getText().equals("") && txtTenNVTim.getText().equals("")) {
                    String tenKHTim = txtTenKHTim.getText().trim();
                    try {
                        docDuLieuTimKiemTuArrayListVaoModelTheoTenKH(tenKHTim);
                        xoaTrang();
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }

            } else if (obj.equals(btnRefresh)) {
                xoaHetDuLieu();
                try {
                    docDuLieuTuArrayListVaoModel();
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            } else if (obj.equals(btnIn)) {
                if (tblHoaDon.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(this, "Phải chọn hóa đơn cần in");
                } else {
                    xuatHoaDon(tblHoaDon.getValueAt(tblHoaDon.getSelectedRow(), 1).toString());
                }
            }

        } else {
            if (obj.equals(btnFind)) {
                if (txtMahoaDonTim.getText().equals("") && txtTenKHTim.getText().equals("")
                        && txtSDTTim.getText().equals("") && txtTenNVTim.getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin tìm kiếm");
                } else if (!txtMahoaDonTim.getText().equals("") && txtTenKHTim.getText().equals("")
                        && txtSDTTim.getText().equals("") && txtTenNVTim.getText().equals("")) {
                    String maTim = txtMahoaDonTim.getText().trim();
                    try {
                        docDuLieuHoaDonDoiTraVaoModelTheoMa(maTim);
                        xoaTrang();
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                } else if (txtMahoaDonTim.getText().equals("") && txtTenKHTim.getText().equals("")
                        && txtSDTTim.getText().equals("") && !txtTenNVTim.getText().equals("")) {
                    String tenTim = txtTenNVTim.getText().trim();
                    try {
                        docDuLieuHoaDonDoiTraVaoModelTheoTenNV(tenTim);
                        xoaTrang();
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                } else if (txtMahoaDonTim.getText().equals("") && txtTenKHTim.getText().equals("")
                        && !txtSDTTim.getText().equals("") && txtTenNVTim.getText().equals("")) {
                    String sdtTim = txtSDTTim.getText().trim();
                    try {
                        docDuLieuHoaDonDoiTraVaoModelTheoSDT(sdtTim);
                        xoaTrang();
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                } else if (txtMahoaDonTim.getText().equals("") && !txtTenKHTim.getText().equals("")
                        && txtSDTTim.getText().equals("") && txtTenNVTim.getText().equals("")) {
                    String tenKHTim = txtTenKHTim.getText().trim();

                    try {
                        docDuLieuHoaDonDoiTraVaoModelTheoTenKH(tenKHTim);
                        xoaTrang();
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            } else if (obj.equals(btnXemChiTiet)) {
                int row = tblHoaDon.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(null, "Chưa chọn dòng nào!!", "Thông báo",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    String maHoaDonDoiTra = modelHoaDon.getValueAt(row, 1).toString();
                    HoaDonDoiTra hoaDonDT = new HoaDonDoiTra();
                    hoaDonDT = hoaDonDoiTraService.timHoaDonDoiTraTheoMa(maHoaDonDoiTra);
                    String maHoaDonDT = hoaDonDT.getMaHoaDonDoiTra();
                    String maHDCu = hoaDonDT.getHoaDon().getMaHoaDon();
                    String tenNV = hoaDonDT.getNhanVien().getHoTenNhanVien();
                    String tenKH = hoaDonDT.getKhachHang().getHoTenKhachHang();
                    String ngayLapHD = hoaDonDT.getNgayLapHoaDon().toString();
                    String tienKhachDua = hoaDonDT.getTienKhachDua() + "";
                    String tienPhaiTru = hoaDonDT.getTienPhaiTru() + "";
                    String ghiChu = hoaDonDT.getGhiChu();
                    System.out.println("Ma hd cu: " + maHDCu);
                    new WinXemChiTietHoaDonDoiTra(maHoaDonDT, maHDCu, tenNV, tenKH, ngayLapHD, ghiChu,
                            tienKhachDua, tienPhaiTru).setVisible(true);
                }
            } else if (obj.equals(btnRefresh)) {
                xoaHetDuLieu();
                try {
                    docDuLieuHoaDonDoiTraTuArrayListVaoModel();
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            } else if (obj.equals(btnIn)) {
                System.out.println("ok");
                if (tblHoaDon.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(this, "Phải chọn hóa đơn cần in");
                } else {
                    xuatHoaDonDoiTra(tblHoaDon.getValueAt(tblHoaDon.getSelectedRow(), 1).toString());
                }
            }

        }

    }

    public void setColumnName(String st1, String st2) {
        JTableHeader HEADER = tblHoaDon.getTableHeader();
        TableColumnModel TMC = HEADER.getColumnModel();
        TableColumn TC = TMC.getColumn(1);
        TC.setHeaderValue(st1);

        TableColumn TC2 = TMC.getColumn(5);
        TC2.setHeaderValue(st2);

        HEADER.repaint();
        tblHoaDon.getTableHeader().repaint();
    }

    public double tongTienHoaDon(String maHoaDon) throws Exception {
        double tongTien = chiTietHoaDonService.getTien(maHoaDon);
        return tongTien;

    }

	public void docDuLieuTuArrayListVaoModel() throws Exception {

		dsHoaDon = hoaDonService.getHoaDonThuong();
		int i = 1;
		for (HoaDon hoaDon : dsHoaDon) {
			String khachHangName = (hoaDon.getKhachHang() != null) ? hoaDon.getKhachHang().getHoTenKhachHang() : "No associated customer";
			if (hoaDon.getNhanVien() == null) {
				modelHoaDon.addRow(new Object[] { i++, hoaDon.getMaHoaDon(), "Đã nghỉ việc", hoaDon.getNgayLapHoaDon(),
						khachHangName, tongTienHoaDon(hoaDon.getMaHoaDon()) });
			} else {
				modelHoaDon.addRow(new Object[] { i++, hoaDon.getMaHoaDon(), hoaDon.getNhanVien().getHoTenNhanVien(),
						hoaDon.getNgayLapHoaDon(), khachHangName,
						tongTienHoaDon(hoaDon.getMaHoaDon()) });
			}
		}
	}

    public void docDuLieuTimKiemTuArrayListVaoModelTheoMa(String maHoaDon) throws Exception {

        dsHoaDon = hoaDonService.getHoaDonTheoMa(maHoaDon);
        if (dsHoaDon.size() == 0) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn phù hợp");
        } else {
            xoaHetDuLieu();
            int i = 1;
            for (HoaDon hoaDon : dsHoaDon) {
                System.out.println(hoaDon);
                modelHoaDon.addRow(new Object[]{i++, hoaDon.getMaHoaDon(), hoaDon.getNhanVien().getHoTenNhanVien(),
                        hoaDon.getNgayLapHoaDon(), hoaDon.getKhachHang().getHoTenKhachHang(),
                        tongTienHoaDon(hoaDon.getMaHoaDon())});
            }
        }
    }

    public void docDuLieuTimKiemTuArrayListVaoModelTheoTen(String tenNV) throws Exception {

        dsHoaDon = hoaDonService.getHoaDonTheoTen(tenNV);
        if (dsHoaDon.size() == 0) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn phù hợp");
        } else {
            xoaHetDuLieu();
            int i = 1;
            for (HoaDon hoaDon : dsHoaDon) {
                modelHoaDon.addRow(new Object[]{i++, hoaDon.getMaHoaDon(), hoaDon.getNhanVien().getHoTenNhanVien(),
                        hoaDon.getNgayLapHoaDon(), hoaDon.getKhachHang().getHoTenKhachHang(),
                        tongTienHoaDon(hoaDon.getMaHoaDon())});
            }
        }

    }

    public void docDuLieuTimKiemTuArrayListVaoModelTheoTenKH(String tenKH) throws Exception {

        dsHoaDon = hoaDonService.timHoaDonTheoTenKH(tenKH);
        if (dsHoaDon.size() == 0) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn phù hợp");
        } else {
            xoaHetDuLieu();
            int i = 1;
            for (HoaDon hoaDon : dsHoaDon) {

                System.out.println(hoaDon);
                modelHoaDon.addRow(new Object[]{i++, hoaDon.getMaHoaDon(), hoaDon.getNhanVien().getHoTenNhanVien(),
                        hoaDon.getNgayLapHoaDon(), hoaDon.getKhachHang().getHoTenKhachHang(),
                        tongTienHoaDon(hoaDon.getMaHoaDon())});
            }
        }

    }

    public void docDuLieuTimKiemTuArrayListVaoModelTheoSDT(String sdt) throws Exception {

        dsHoaDon = hoaDonService.timHoaDonTheoSDT(sdt);
        if (dsHoaDon.size() == 0) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn phù hợp");
        } else {
            xoaHetDuLieu();
            int i = 1;
            for (HoaDon hoaDon : dsHoaDon) {
                modelHoaDon.addRow(new Object[]{i++, hoaDon.getMaHoaDon(), hoaDon.getNhanVien().getHoTenNhanVien(),
                        hoaDon.getNgayLapHoaDon(), hoaDon.getKhachHang().getHoTenKhachHang(),
                        tongTienHoaDon(hoaDon.getMaHoaDon())});
            }
        }

    }

    public void docDuLieuHoaDonDoiTraTuArrayListVaoModel() throws Exception {
        dsHoaDonDoiTra = hoaDonDoiTraService.getToanBoDSHoaDonDoiTra();
        int i = 1;
        for (HoaDonDoiTra hd : dsHoaDonDoiTra) {
            System.out.println(hd);
            modelHoaDon.addRow(new Object[]{i++, hd.getMaHoaDonDoiTra(), hd.getNhanVien().getHoTenNhanVien(),
                    hd.getNgayLapHoaDon(), hd.getKhachHang().getHoTenKhachHang(), hd.getTienPhaiTru()});
        }

    }

    public void docDuLieuHoaDonDoiTraVaoModelTheoMa(String maHoaDon) throws Exception {
        hoaDonDoiTra = hoaDonDoiTraService.timHoaDonDoiTraTheoMa(maHoaDon);
        if (hoaDonDoiTra == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn phù hợp");
        } else {
            xoaHetDuLieu();
            int i = 1;

            System.out.println(hoaDonDoiTra);
            modelHoaDon.addRow(new Object[]{i++, hoaDonDoiTra.getMaHoaDonDoiTra(), hoaDonDoiTra.getNhanVien().getHoTenNhanVien(),
                    hoaDonDoiTra.getNgayLapHoaDon(), hoaDonDoiTra.getKhachHang().getHoTenKhachHang(), hoaDonDoiTra.getTienPhaiTru()});

        }
    }

    public void docDuLieuHoaDonDoiTraVaoModelTheoTenNV(String ten) throws Exception {
        dsHoaDonDoiTra = hoaDonDoiTraService.getHoaDonDoiTraTheoTen(ten);
        if (dsHoaDonDoiTra.size() == 0) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn phù hợp");
        } else {
            xoaHetDuLieu();
            int i = 1;
            for (HoaDonDoiTra hddt : dsHoaDonDoiTra) {
                modelHoaDon.addRow(new Object[]{i++, hddt.getMaHoaDonDoiTra(), hddt.getNhanVien().getHoTenNhanVien(),
                        hddt.getNgayLapHoaDon(), hddt.getKhachHang().getHoTenKhachHang(), hddt.getTienPhaiTru()});
            }
        }

    }

    public void docDuLieuHoaDonDoiTraVaoModelTheoSDT(String sdt) throws Exception {
        dsHoaDonDoiTra = hoaDonDoiTraService.getHoaDonDoiTraTheoSDT(sdt);
        if (dsHoaDonDoiTra.size() == 0) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn phù hợp");
        } else {
            xoaHetDuLieu();
            int i = 1;
            for (HoaDonDoiTra hddt : dsHoaDonDoiTra) {
                modelHoaDon.addRow(new Object[]{i++, hddt.getMaHoaDonDoiTra(), hddt.getNhanVien().getHoTenNhanVien(),
                        hddt.getNgayLapHoaDon(), hddt.getKhachHang().getHoTenKhachHang(), hddt.getTienPhaiTru()});
            }
        }

    }

    public void docDuLieuHoaDonDoiTraVaoModelTheoTenKH(String tenKH) throws Exception {
        dsHoaDonDoiTra = hoaDonDoiTraService.getHoaDonDoiTraTheoTenKH(tenKH);
        if (dsHoaDonDoiTra.size() == 0) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn phù hợp");
        } else {
            xoaHetDuLieu();
            int i = 1;
            for (HoaDonDoiTra hddt : dsHoaDonDoiTra) {
                modelHoaDon.addRow(new Object[]{i++, hddt.getMaHoaDonDoiTra(), hddt.getNhanVien().getHoTenNhanVien(),
                        hddt.getNgayLapHoaDon(), hddt.getKhachHang().getHoTenKhachHang(), hddt.getTienPhaiTru()});
            }
        }

    }

    public void xoaHetDuLieu() {
        DefaultTableModel dtm = (DefaultTableModel) tblHoaDon.getModel();
        dtm.getDataVector().removeAllElements();
    }

    public void xoaTrang() {
        txtMahoaDonTim.setText("");
        txtTenKHTim.setText("");
        txtSDTTim.setText("");
        txtTenNVTim.setText("");
    }

        public void xuatHoaDonDoiTra(String maHDDT) {
//        try {
//            Hashtable map = new Hashtable();
//            JasperReport report = JasperCompileManager.compileReport("src/main/java/gui/HoaDonBanHang.jrxml");
//            map.put("maHDDT", maHDDT);
//            JasperPrint p = JasperFillManager.fillReport(report, map, DBConnection.getInstance().getConnection());
//
//            JasperViewer.viewReport(p, false);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }



    public void xuatHoaDon(String maHD) {
//        try {
//            Hashtable map = new Hashtable();
//            JasperReport report = JasperCompileManager.compileReport("src/main/java/gui/HoaDonBanHang.jrxml");
//            map.put("maHD", maHD);
//            JasperPrint p = JasperFillManager.fillReport(report, map, DBConnection.getInstance().getConnection());
//            JasperViewer.viewReport(p, false);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }





}
