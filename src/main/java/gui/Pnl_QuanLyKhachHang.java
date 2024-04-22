package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.Naming;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import dao.KhachHangDao;
import entities.KhachHang;
import lombok.SneakyThrows;
import service.*;
import service.impl.KhachHangServiceImpl;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;

public class Pnl_QuanLyKhachHang extends JPanel implements MouseListener, ActionListener {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private DefaultTableModel modelKhachHang;
    private JTable tblKhachHang;
    private JScrollPane scrKhachHang;
    private JTextField txtMaKhachHang;
    private JTextField txtTenKhachHang;
    private JTextField txtSDT;
    private JComboBox cmbDiaChi;
    private JButton btnLamMoiDanhSach;
    private JButton btnThemKhachHang;
    private JButton btnCapNhat;
    private JComboBox<Object> cmbGioiTinh;
    private List<KhachHang> dsKhachHang;

    private JButton btnRefresh;
    private JLabel lblDiaChi;
    private JButton btnFind;
    private JButton btnLuu;

    private static final String URL = "rmi://192.168.40.54:7878/";
    private SanPhamService sanPhamService = (SanPhamService) Naming.lookup(URL + "sanPham");
    private SachLoiService sachLoiService = (SachLoiService) Naming.lookup(URL + "sachLoi");
    private HoaDonService hoaDonService = (HoaDonService) Naming.lookup(URL + "hoaDon");
    private TaiKhoanService taiKhoanService = (TaiKhoanService) Naming.lookup(URL + "taiKhoan");
    private NhanVienService nhanVienService = (NhanVienService) Naming.lookup(URL + "nhanVien");
    private ChiTietHoaDonService chiTietHoaDonService = (ChiTietHoaDonService) Naming.lookup(URL + "chiTietHoaDon");
    private KhachHangService khachHangService = (KhachHangService) Naming.lookup(URL + "khachHang");

    public Pnl_QuanLyKhachHang() throws Exception {
        setBackground(new Color(0, 206, 209));
        setFont(new Font("Dialog", Font.BOLD, 16));
        setSize(1720, 630);

        setLayout(null);

        JLabel lblTitle = new JLabel("QUẢN LÝ KHÁCH HÀNG");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setForeground(Color.DARK_GRAY);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 36));
        lblTitle.setBounds(10, 11, 1700, 55);
        add(lblTitle);

        JPanel pnlAddress = new JPanel();
        pnlAddress.setBorder(new LineBorder(new Color(0, 0, 0)));
        pnlAddress.setBackground(new Color(255, 255, 255));
        pnlAddress.setBounds(20, 140, 418, 534);
        add(pnlAddress);
        pnlAddress.setLayout(null);

        txtMaKhachHang = new JTextField();
        txtMaKhachHang.setHorizontalAlignment(SwingConstants.CENTER);
        txtMaKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 16));
        txtMaKhachHang.setBounds(141, 50, 267, 33);
        pnlAddress.add(txtMaKhachHang);
        txtMaKhachHang.setColumns(10);
        txtMaKhachHang.setForeground(new Color(0, 0, 0));

        txtTenKhachHang = new JTextField();
        txtTenKhachHang.setHorizontalAlignment(SwingConstants.CENTER);
        txtTenKhachHang.setForeground(new Color(0, 0, 0));
        txtTenKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 16));
        txtTenKhachHang.setColumns(10);
        txtTenKhachHang.setBounds(141, 120, 267, 33);
        pnlAddress.add(txtTenKhachHang);

        txtSDT = new JTextField();
        txtSDT.setHorizontalAlignment(SwingConstants.CENTER);
        txtSDT.setForeground(new Color(0, 0, 0));
        txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 16));
        txtSDT.setColumns(10);
        txtSDT.setBounds(141, 190, 267, 33);
        pnlAddress.add(txtSDT);
        String[] tinh = {"Thủ Đức", "Quận 1", "Quận 2", "Quận 3 ", "Quận 4", "Quận 5", "Quận 6", "Quận 7", "Quận 8",
                "Quận 9", "Quận 10", "Quận 11", "Quận 12", "Gò Vấp", "Tân Bình", "Bình Tân", "Bình Thạnh", "Phú Nhuận",
                "Tân Phú", "Bình Chánh", "Cần Giờ", "Củ Chi", "Hóc Môn", "Nhà Bè"};
        cmbDiaChi = new JComboBox<Object>(tinh);
        cmbDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 14));
        cmbDiaChi.setModel(new DefaultComboBoxModel(new String[]{"Thủ Đức", "Quận 1", "Quận 2", "Quận 3 ", "Quận 4",
                "Quận 5", "Quận 6", "Quận 7", "Quận 8", "Quận 9", "Quận 10", "Quận 11", "Quận 12", "Gò Vấp", "Tân Bình",
                "Bình Tân", "Bình Thạnh", "Phú Nhuận", "Tân Phú", "Bình Chánh", "Cần Giờ", "Củ Chi", "Hóc Môn",
                "Nhà Bè"}));
        cmbDiaChi.setBounds(141, 330, 267, 33);
        pnlAddress.add(cmbDiaChi);

        JLabel lblGioiTinh = new JLabel("Giới tính:");
        lblGioiTinh.setHorizontalAlignment(SwingConstants.LEFT);
        lblGioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblGioiTinh.setBounds(10, 260, 121, 33);
        pnlAddress.add(lblGioiTinh);

        JLabel lblMaKhachHang = new JLabel("Mã khách hàng:");
        lblMaKhachHang.setHorizontalAlignment(SwingConstants.LEFT);
        lblMaKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblMaKhachHang.setBounds(10, 50, 121, 33);
        pnlAddress.add(lblMaKhachHang);

        JLabel lblTenKhachHang = new JLabel("Tên khách hàng:");
        lblTenKhachHang.setHorizontalAlignment(SwingConstants.LEFT);
        lblTenKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblTenKhachHang.setBounds(10, 120, 121, 33);
        pnlAddress.add(lblTenKhachHang);

        JLabel lblSDT = new JLabel("Số điện thoại:");
        lblSDT.setHorizontalAlignment(SwingConstants.LEFT);
        lblSDT.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblSDT.setBounds(10, 190, 121, 33);
        pnlAddress.add(lblSDT);

        cmbGioiTinh = new JComboBox<Object>(new Object[]{});
        cmbGioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 14));
        cmbGioiTinh.setModel(new DefaultComboBoxModel(new String[]{"Nam", "Nữ"}));
        cmbGioiTinh.setSelectedIndex(0);
        cmbGioiTinh.setBounds(141, 260, 267, 33);
        pnlAddress.add(cmbGioiTinh);

        lblDiaChi = new JLabel("Địa chỉ:");
        lblDiaChi.setHorizontalAlignment(SwingConstants.LEFT);
        lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblDiaChi.setBounds(10, 330, 121, 33);
        pnlAddress.add(lblDiaChi);

        btnRefresh = new JButton("Làm mới");
        btnRefresh.setIcon(new ImageIcon(Pnl_QuanLyKhachHang.class.getResource("/gui/icon/refresh-button.png")));
        btnRefresh.setForeground(Color.BLACK);
        btnRefresh.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnRefresh.setBounds(10, 430, 190, 40);
        pnlAddress.add(btnRefresh);

        btnFind = new JButton("Tìm");
        btnFind.setIcon(new ImageIcon(Pnl_QuanLyKhachHang.class.getResource("/gui/icon/loupe.png")));
        btnFind.setForeground(Color.BLACK);
        btnFind.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnFind.setBounds(218, 430, 190, 40);
        pnlAddress.add(btnFind);

        String[] cols = {"STT", "Mã khách hàng", "Tên khách hàng", "Số điện thoại", "Giới tính", "Địa chỉ"};
        modelKhachHang = new DefaultTableModel(cols, 0);
        tblKhachHang = new JTable(modelKhachHang);
        tblKhachHang.setBorder(new LineBorder(new Color(0, 0, 0)));
        tblKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 14));

        scrKhachHang = new JScrollPane(tblKhachHang);
        scrKhachHang.setBounds(448, 77, 1400, 600);
        tblKhachHang.getTableHeader().setBackground(Color.LIGHT_GRAY);
        tblKhachHang.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 17));
        tblKhachHang.setRowHeight(25);
        tblKhachHang.setBackground(Color.WHITE);
        scrKhachHang.getViewport().setBackground(Color.WHITE);
        tblKhachHang.getTableHeader().setPreferredSize(new Dimension(0, 40));
        add(scrKhachHang);

        JPanel pnlTimKiem = new JPanel();
        pnlTimKiem.setBackground(Color.LIGHT_GRAY);
        pnlTimKiem.setBorder(null);
        pnlTimKiem.setBounds(20, 77, 418, 64);
        add(pnlTimKiem);
        pnlTimKiem.setLayout(null);
        JLabel lblTimKiem = new JLabel("Tìm kiếm thông tin khách hàng");
        lblTimKiem.setBounds(0, 0, 418, 64);
        pnlTimKiem.add(lblTimKiem);
        lblTimKiem.setHorizontalAlignment(SwingConstants.CENTER);
        lblTimKiem.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTimKiem.setForeground(Color.BLACK);
        lblTimKiem.setBackground(Color.LIGHT_GRAY);

        btnLamMoiDanhSach = new JButton("Làm mới bảng");
        btnLamMoiDanhSach.setIcon(new ImageIcon(Pnl_QuanLyKhachHang.class.getResource("/gui/icon/refresh-button.png")));
        btnLamMoiDanhSach.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnLamMoiDanhSach.setForeground(Color.BLACK);
        btnLamMoiDanhSach.setFont(new Font("Dialog", Font.BOLD, 16));
        btnLamMoiDanhSach.setBounds(1225, 700, 230, 40);
        add(btnLamMoiDanhSach);

        btnThemKhachHang = new JButton("Thêm khách hàng");
        btnThemKhachHang.setIcon(new ImageIcon(Pnl_QuanLyKhachHang.class.getResource("/gui/icon/add-user.png")));
        btnThemKhachHang.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnThemKhachHang.setForeground(Color.BLACK);
        btnThemKhachHang.setFont(new Font("Dialog", Font.BOLD, 16));
        btnThemKhachHang.setBounds(715, 700, 230, 40);
        add(btnThemKhachHang);

        btnCapNhat = new JButton("Cập nhật khách hàng");
        btnCapNhat.setIcon(new ImageIcon(Pnl_QuanLyKhachHang.class.getResource("/gui/icon/icons-update.png")));
        btnCapNhat.setForeground(Color.BLACK);
        btnCapNhat.setFont(new Font("Dialog", Font.BOLD, 16));
        btnCapNhat.setBounds(970, 700, 235, 40);
        add(btnCapNhat);

        btnLuu = new JButton("Lưu");
        btnLuu.setIcon(new ImageIcon(Pnl_QuanLyKhachHang.class.getResource("/gui/icon/diskette.png")));
        btnLuu.setFont(new Font("Dialog", Font.BOLD, 16));
        btnLuu.setBounds(465, 700, 230, 40);
        add(btnLuu);
        try {
            DocDuLieuTuArrayListVaoModel();
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        moKhoaTextfields(false);
        btnLuu.setEnabled(false);
        tblKhachHang.addMouseListener(this);
        btnThemKhachHang.addActionListener(this);
        btnLamMoiDanhSach.addActionListener(this);
        btnRefresh.addActionListener(this);
        btnFind.addActionListener(this);
        btnLuu.addActionListener(this);
        btnCapNhat.addActionListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        int row = tblKhachHang.getSelectedRow();
        txtMaKhachHang.setText(modelKhachHang.getValueAt(row, 1).toString());
        txtTenKhachHang.setText(modelKhachHang.getValueAt(row, 2).toString());
        txtSDT.setText(modelKhachHang.getValueAt(row, 3).toString());
        cmbGioiTinh.setSelectedIndex(modelKhachHang.getValueAt(row, 4).toString().equalsIgnoreCase("Nam") ? 0 : 1);
        cmbDiaChi.setSelectedItem(modelKhachHang.getValueAt(row, 5).toString());
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

    public void DocDuLieuTuArrayListVaoModel() throws Exception {
        dsKhachHang = khachHangService.getDSKhachHang();
        int i = 1;
        for (KhachHang khachHang : dsKhachHang) {
            modelKhachHang.addRow(new Object[]{i++, khachHang.getMaKhachHang(), khachHang.getHoTenKhachHang(),
                    khachHang.getsDT(), khachHang.isGioiTinh() == true ? "Nam" : "Nữ", khachHang.getDiaChi()});
        }
    }

    public void xoaHetDuLieu() {
        DefaultTableModel dtm = (DefaultTableModel) tblKhachHang.getModel();
        dtm.getDataVector().removeAllElements();
    }

    @SneakyThrows
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        Object obj = e.getSource();
        if (obj.equals(btnThemKhachHang)) {
            if (btnThemKhachHang.getText().equalsIgnoreCase("Thêm khách hàng")) {
                tblKhachHang.removeMouseListener(this);
                moKhoaTextfields(true);
                moKhoaControls(false);
                clearTxtfields();
                btnThemKhachHang.setEnabled(true);
                btnLuu.setEnabled(true);
                btnRefresh.setEnabled(true);
                btnThemKhachHang.setText("Huỷ");
                txtMaKhachHang.setEditable(false);
                txtMaKhachHang.setText(auto_ID());

            } else if (btnThemKhachHang.getText().equalsIgnoreCase("Huỷ")) {
                tblKhachHang.addMouseListener(this);
                moKhoaTextfields(false);
                moKhoaControls(true);
                clearTxtfields();
                btnLuu.setEnabled(false);
                btnFind.setText("Tìm");
                btnThemKhachHang.setText("Thêm khách hàng");
            }
        } else if (obj.equals(btnRefresh)) {
            clearTxtfields();
        } else if (obj.equals(btnLuu)) {

            if (btnLuu.getText().equalsIgnoreCase("Lưu") && btnThemKhachHang.getText().equalsIgnoreCase("Huỷ")) {

                KhachHang kh = revertKhachHangFromTextField();
                if (!(txtTenKhachHang.getText().length() > 0)) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập tên khách hàng");
                    txtTenKhachHang.requestFocus();
                    return;
                }
                if (!(txtSDT.getText().length() > 0)) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại khách hàng");
                    txtSDT.requestFocus();
                    return;
                }
                try {
                    dsKhachHang = khachHangService.getDSKhachHang();
                } catch (SQLException e2) {
                    // TODO Auto-generated catch block
                    e2.printStackTrace();
                }
                for (KhachHang khg : dsKhachHang) {
                    if (khg.getsDT().equalsIgnoreCase(txtSDT.getText().trim())) {
                        JOptionPane.showMessageDialog(this, "Số điện thoại đã có người sử dụng, vui lòng nhập số điện thoại khác");
                        txtSDT.requestFocus();
                        return;
                    }
                }

                try {
                    if (khachHangService.themKhachHang(kh) > 0) {
                        updateTableData(kh);
                        JOptionPane.showMessageDialog(this, "Thêm khách hàng thành công");
                        btnThemKhachHang.setText("Thêm khách hàng");
                        btnLuu.setEnabled(false);
                        moKhoaControls(true);
                        moKhoaTextfields(false);
                    } else {

                        int er = KhachHangServiceImpl.errors;
                        System.out.println(er);
                        if (er == 1) {
                            JOptionPane.showMessageDialog(this, "Tên khách hàng phải theo mẫu, ví dụ: Nguyễn Văn An");
                            txtTenKhachHang.setText("");
                            txtTenKhachHang.requestFocus();
                        } else if (er == 2) {
                            JOptionPane.showMessageDialog(this, "Số điện thoại cần có 10 chữ số và bắt đầu bằng 03, 05, 07, 08, 09 ví dụ: 0384600357");
                            txtSDT.setText("");
                            txtSDT.requestFocus();
                        }
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Trùng mã");
                }
            } else if ((btnLuu.getText().equalsIgnoreCase("Lưu") && btnCapNhat.getText().equalsIgnoreCase("Huỷ"))) {
                KhachHang kh = revertKhachHangFromTextField();
                if (!(txtTenKhachHang.getText().length() > 0)) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập tên khách hàng");
                    txtTenKhachHang.requestFocus();
                    return;
                }
                if (!(txtSDT.getText().length() > 0)) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại khách hàng");
                    txtSDT.requestFocus();
                    return;
                }
                try {
                    dsKhachHang = khachHangService.getDSKhachHang();
                } catch (SQLException e2) {
                    // TODO Auto-generated catch block
                    e2.printStackTrace();
                }
                for (KhachHang khg : dsKhachHang) {
                    if (khg.getsDT().equalsIgnoreCase(txtSDT.getText().trim())) {
                        JOptionPane.showMessageDialog(this, "Số điện thoại đã có người sử dụng, vui lòng nhập số điện thoại khác");
                        txtSDT.requestFocus();
                        return;
                    }
                }
                try {
                    // if(validData()) {
                    if (khachHangService.capNhatKhachHang(kh) > 0) {
                        JOptionPane.showMessageDialog(this, "Sửa thành công 1 nhân viên");
                        editOnRow();
                        moKhoaTextfields(false);
                        moKhoaControls(true);
                        clearTxtfields();
                        btnLuu.setEnabled(false);
                        btnCapNhat.setText("Cập nhật khách hàng");
                    } else {
                        int erSua = KhachHangServiceImpl.errorsThem;
                        if (erSua == 1) {
                            JOptionPane.showMessageDialog(this, "Tên khách hàng phải theo mẫu : Nguyen Van Anh");
                            txtTenKhachHang.setText("");
                            txtTenKhachHang.requestFocus();
                        } else if (erSua == 2) {
                            JOptionPane.showMessageDialog(this, "Số điện thoại cần có 10 chữ số ví dụ: 0384600357");
                            txtSDT.setText("");
                            txtSDT.requestFocus();
                        }
                    }

                } catch (HeadlessException | SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            } else if (btnLuu.getText().equalsIgnoreCase("Huỷ")) {
                tblKhachHang.addMouseListener(this);
                moKhoaTextfields(false);
                moKhoaControls(true);
                clearTxtfields();
                btnLuu.setEnabled(false);
                btnLuu.setText("Lưu");
                btnFind.setText("Tìm");
            }

        } else if (obj.equals(btnFind)) {
            if (btnFind.getText().equalsIgnoreCase("Tìm")) {
                tblKhachHang.removeMouseListener(this);
                try {
                    DocDuLieuTuArrayListVaoModel();
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                clearTxtfields();
                btnThemKhachHang.setEnabled(false);
                btnCapNhat.setEnabled(true);
                btnLuu.setEnabled(true);
                btnLuu.setText("Huỷ");
                btnFind.setText("Tìm kiếm");
                txtSDT.setEditable(true);
                txtMaKhachHang.setEditable(false);
                txtTenKhachHang.setEditable(true);
            } else if (btnFind.getText().equalsIgnoreCase("Tìm kiếm")) {
                tblKhachHang.addMouseListener(this);
                int i = 0;
                ArrayList<KhachHang> kh = new ArrayList<KhachHang>();
                String sdt = txtSDT.getText();
                String tenTim = txtTenKhachHang.getText();
                if (!sdt.isEmpty() && tenTim.isEmpty()) {
                    if (!sdt.isEmpty() && tenTim.isEmpty()) {
                        try {
                            kh = khachHangService.timKhachHangTheoSDT(sdt);
                        } catch (SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                        if (kh != null) {
                            xoaHetDuLieu();
                            for (KhachHang khh : kh) {
                                try {

                                    dsKhachHang = khachHangService.getDSKhachHang();
                                    modelKhachHang.addRow(new Object[]{++i, khh.getMaKhachHang(),
                                            khh.getHoTenKhachHang(), khh.getsDT(),
                                            khh.isGioiTinh() == true ? "Nam" : "Nữ", khh.getDiaChi()});
                                } catch (SQLException e1) {
                                    // TODO Auto-generated catch block
                                    e1.printStackTrace();
                                }
                            }
                            txtSDT.setEditable(false);
                            txtTenKhachHang.setEditable(false);
                            btnFind.setText("Tìm");
                            btnLuu.setText("Lưu");
                            btnLuu.setEnabled(false);
                            moKhoaControls(true);
                            moKhoaTextfields(false);

                        } else {
                            JOptionPane.showMessageDialog(this, "Không tìm thấy");
                            revertKhachHangFromTextField();
                            xoaHetDuLieu();
                            clearTxtfields();
                            btnFind.setText("Tìm");
                            btnLuu.setText("Lưu");
                            btnLuu.setEnabled(false);
                            moKhoaControls(true);
                            moKhoaTextfields(false);
                        }
                    }
                } else if (sdt.isEmpty() && !tenTim.isEmpty()) {
                    if (!tenTim.isEmpty() && sdt.isEmpty()) {

                        try {
                            kh = khachHangService.timKhachHangTheoTen(tenTim);

                        } catch (SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                        if (kh.size() > 0) {
                            xoaHetDuLieu();
                            for (KhachHang khh : kh) {
                                try {

                                    dsKhachHang = khachHangService.getDSKhachHang();
                                    modelKhachHang.addRow(new Object[]{++i, khh.getMaKhachHang(),
                                            khh.getHoTenKhachHang(), khh.getsDT(),
                                            khh.isGioiTinh() == true ? "Nam" : "Nữ", khh.getDiaChi()});
                                } catch (SQLException e1) {
                                    // TODO Auto-generated catch block
                                    e1.printStackTrace();
                                }
                            }
                            txtSDT.setEditable(false);
                            txtTenKhachHang.setEditable(false);
                            JOptionPane.showMessageDialog(this, "Đã tìm thấy");
                            btnFind.setText("Tìm");
                            btnLuu.setText("Lưu");
                            btnLuu.setEnabled(false);
                            moKhoaControls(true);
                            moKhoaTextfields(false);

                        } else {
                            JOptionPane.showMessageDialog(this, "Không tìm thấy");
                            revertKhachHangFromTextField();
                            modelKhachHang.setRowCount(0);
                            clearTxtfields();
                            btnFind.setText("Tìm");
                            btnLuu.setText("Lưu");
                            btnLuu.setEnabled(false);
                            moKhoaControls(true);
                            moKhoaTextfields(false);
                        }
                    }
                } else if (!sdt.isEmpty() && !tenTim.isEmpty()) {
                    ArrayList<KhachHang> dsKhachHang = new ArrayList<KhachHang>();
                    try {
                        dsKhachHang = khachHangService.getListKhachHangByNameAndSDT(tenTim, sdt);
                    } catch (Exception e2) {
                        // TODO: handle exception
                        e2.printStackTrace();
                    }
                    if (dsKhachHang != null) {
                        xoaHetDuLieu();
                        for (KhachHang khh : dsKhachHang) {
                            try {
                                this.dsKhachHang = khachHangService.getDSKhachHang();
                                modelKhachHang.addRow(new Object[]{++i, khh.getMaKhachHang(), khh.getHoTenKhachHang(),
                                        khh.getsDT(), khh.isGioiTinh() == true ? "Nam" : "Nữ", khh.getDiaChi()});
                            } catch (SQLException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                        }
                        txtSDT.setEditable(false);
                        txtTenKhachHang.setEditable(false);
                        JOptionPane.showMessageDialog(this, "Đã tìm thấy");
                        btnFind.setText("Tìm");
                        btnLuu.setText("Lưu");
                        btnLuu.setEnabled(false);
                        moKhoaControls(true);
                        moKhoaTextfields(false);
                    } else {
                        JOptionPane.showMessageDialog(this, "Không tìm thấy");
                        revertKhachHangFromTextField();
                        modelKhachHang.setRowCount(0);
                        clearTxtfields();
                        btnFind.setText("Tìm");
                        btnLuu.setText("Lưu");
                        btnLuu.setEnabled(false);
                        moKhoaControls(true);
                        moKhoaTextfields(false);
                    }

                } else if (sdt.isEmpty() && tenTim.isEmpty()) {
                    xoaHetDuLieu();
                    try {
                        DocDuLieuTuArrayListVaoModel();
                        JOptionPane.showMessageDialog(this,
                                "Vui lòng nhập tên khách hàng hoặc số điện thoại để tìm kiếm");
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            }
        } else if (obj.equals(btnLamMoiDanhSach)) {
            xoaHetDuLieu();
            try {
                DocDuLieuTuArrayListVaoModel();
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        } else if (obj.equals(btnCapNhat)) {
            if (tblKhachHang.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(this, "Phải chọn dòng trước khi sửa");
            } else {
                if (btnCapNhat.getText().equalsIgnoreCase("Cập nhật khách hàng")) {
                    moKhoaTextfields(true);
                    moKhoaControls(false);
                    btnLuu.setEnabled(true);
                    btnCapNhat.setEnabled(true);
                    btnRefresh.setEnabled(true);
                    btnCapNhat.setText("Huỷ");
                } else if (btnCapNhat.getText().equalsIgnoreCase("Huỷ")) {
                    moKhoaTextfields(false);
                    moKhoaControls(true);
                    clearTxtfields();
                    txtMaKhachHang.setEnabled(false);
                    btnLuu.setEnabled(false);
                    btnCapNhat.setText("Cập nhật khách hàng");
                }
            }
        }
    }

    private void updateTableData(KhachHang kh) throws Exception {

        dsKhachHang = khachHangService.getDSKhachHang();
        int i = dsKhachHang.size();
        modelKhachHang.addRow(new Object[]{i++, kh.getMaKhachHang(), kh.getHoTenKhachHang(), kh.getsDT(),
                kh.isGioiTinh() == true ? "Nam" : "Nữ", kh.getDiaChi()});
    }

    private void moKhoaControls(boolean b) {
        btnCapNhat.setEnabled(b);
        btnFind.setEnabled(b);
        btnLamMoiDanhSach.setEnabled(b);
        btnRefresh.setEnabled(b);
        btnThemKhachHang.setEnabled(b);
    }

    private void moKhoaTextfields(boolean b) {
        txtMaKhachHang.setEditable(b);
        txtTenKhachHang.setEditable(b);
        txtSDT.setEditable(b);
        cmbDiaChi.setEditable(b);
        cmbGioiTinh.setEditable(b);
    }

    private void clearTxtfields() {
        txtMaKhachHang.setText("");
        txtTenKhachHang.setText("");
        txtSDT.setText("");
        cmbDiaChi.setSelectedIndex(0);
        cmbGioiTinh.setSelectedIndex(0);
    }

    public KhachHang revertKhachHangFromTextField() {
        String maKH = txtMaKhachHang.getText();
        String tenKH = txtTenKhachHang.getText();
        String sdt = txtSDT.getText();
        boolean gioiTinh = cmbGioiTinh.getSelectedItem().toString() == "Nam" ? true : false;
        String diaChi = cmbDiaChi.getSelectedItem().toString();
        KhachHang kh = new KhachHang(maKH, tenKH, sdt, gioiTinh, diaChi);
        return kh;
    }

    public void editOnRow() {
        int row = tblKhachHang.getSelectedRow();
        KhachHang kh = revertKhachHangFromTextField();
        tblKhachHang.setValueAt(kh.getMaKhachHang(), row, 1);
        tblKhachHang.setValueAt(kh.getHoTenKhachHang(), row, 2);
        tblKhachHang.setValueAt(kh.getsDT(), row, 3);
        tblKhachHang.setValueAt(kh.isGioiTinh() == true ? "Nam" : "Nữ", row, 4);
    }

    private void showMessage(String message, JTextField txt) {
        // TODO Auto-generated method stub
        txt.requestFocus();
        JOptionPane.showMessageDialog(null, message);
    }

    public String auto_ID() throws Exception {
        String idPrefix = "KH";
        LocalDate myObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd");
        String formattedString = myObj.format(formatter);
        int length = 0;
        length = khachHangService.getDSKhachHang().size();
        String finalId = idPrefix + formattedString + String.format("%04d", length + 1);
        return finalId;
    }

}
