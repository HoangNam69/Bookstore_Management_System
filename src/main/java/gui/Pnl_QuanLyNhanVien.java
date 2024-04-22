package gui;

import java.awt.Color;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.URL;
import java.rmi.Naming;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import entities.NhanVien;
import lombok.SneakyThrows;
import service.*;
import service.impl.HoaDonServiceImpl;
import service.impl.NhanVienServiceImpl;
import service.impl.TaiKhoanServiceImpl;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import com.toedter.calendar.JDateChooser;

import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

public class Pnl_QuanLyNhanVien extends JPanel implements ActionListener, MouseListener {
    private JTextField txtMaNhanVien;
    private JTextField txtTenNV;
    private JTextField txtSDT;
    private JTextField txtDiaChi;
    private JScrollPane scrTblNhanVien;
    private JTable tblNhanVien;
    private DefaultTableModel modelNhanVien;
    private JPanel pnlRight;
    private JPanel pnlTitleBoLoc;
    private JLabel lblTitleThongTinNhanVien;
    private JLabel lblTenNhanVien;
    private JLabel lblMaNhanVien;
    private JLabel lblSDT;
    private JLabel lblGioiTinh;
    private JLabel lblCaLam;
    private JLabel lblChucVu;
    private JLabel lblDiaChi;
    private JComboBox cmbGioiTinh;
    private JComboBox cmbCaLam;
    private JComboBox cmbChucVu;
    private JButton btnThemNV;
    private JButton btnSuaNV;
    private JButton btnXoaNV;
    private JButton btnTimKiem;
    private JButton btnLamMoi;
    private JPanel pnlBottom;
    private JButton btnLamMoiBang;
    private JPanel pnlTop;
    private JLabel lblTitle;
    private JButton btnLuu;
    private List<NhanVien> dsNhanVien;
    private JLabel lblCCCD;
    private JTextField txtCCCD;
    private JLabel lblEmail;
    private JTextField txtEmail;
    private JLabel lblNgaySinh;
    private JButton btnChonAnh;
    private JDateChooser dateChooserNgaySinh;

    private JPanel pnlHinhAnh;
    private File file = null;
    private JPanel pnlTim;
    private JLabel lblSoDienThoai;
    private JTextField txtSDTTim;
    private JTextField txtTenTim;
    private JFileChooser fch;
    private JLabel lblHinhAnh;

    private static final String URL = "rmi://192.168.40.54:7878/";
    private SanPhamService sanPhamService = (SanPhamService) Naming.lookup(URL + "sanPham");
    private SachLoiService sachLoiService = (SachLoiService) Naming.lookup(URL + "sachLoi");
    private HoaDonService hoaDonService = (HoaDonService) Naming.lookup(URL + "hoaDon");
    private TaiKhoanService taiKhoanService = (TaiKhoanService) Naming.lookup(URL + "taiKhoan");
    private NhanVienService nhanVienService = (NhanVienService) Naming.lookup(URL + "nhanVien");

    /**
     * Create the panel.
     */
    public Pnl_QuanLyNhanVien() throws Exception {
        setBackground(new Color(0, 206, 209));
        setFont(new Font("Dialog", Font.BOLD, 16));
        setSize(1900, 900);
        setLayout(null);

        pnlRight = new JPanel();
        pnlRight.setBounds(22, 11, 544, 850);
        add(pnlRight);
        pnlRight.setLayout(null);

        pnlTitleBoLoc = new JPanel();
        pnlTitleBoLoc.setBackground(new Color(128, 128, 128));
        pnlTitleBoLoc.setBounds(0, 0, 544, 40);
        pnlRight.add(pnlTitleBoLoc);
        pnlTitleBoLoc.setLayout(null);

        lblTitleThongTinNhanVien = new JLabel("THÔNG TIN NHÂN VIÊN");
        lblTitleThongTinNhanVien.setBackground(new Color(128, 128, 128));
        lblTitleThongTinNhanVien.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitleThongTinNhanVien.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblTitleThongTinNhanVien.setBounds(0, 0, 544, 40);
        pnlTitleBoLoc.add(lblTitleThongTinNhanVien);

        lblTenNhanVien = new JLabel("Tên nhân viên: ");
        lblTenNhanVien.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblTenNhanVien.setBounds(10, 205, 93, 14);
        pnlRight.add(lblTenNhanVien);

        lblMaNhanVien = new JLabel("Mã nhân viên: ");
        lblMaNhanVien.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblMaNhanVien.setBounds(10, 170, 93, 14);
        pnlRight.add(lblMaNhanVien);

        lblSDT = new JLabel("Số điện thoại: ");
        lblSDT.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblSDT.setBounds(10, 249, 104, 14);
        pnlRight.add(lblSDT);

        lblGioiTinh = new JLabel("Giới tính: ");
        lblGioiTinh.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblGioiTinh.setBounds(10, 294, 93, 14);
        pnlRight.add(lblGioiTinh);

        lblCaLam = new JLabel("Ca làm: ");
        lblCaLam.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblCaLam.setBounds(204, 327, 46, 14);
        pnlRight.add(lblCaLam);

        lblChucVu = new JLabel("Chức vụ: ");
        lblChucVu.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblChucVu.setBounds(10, 327, 76, 14);
        pnlRight.add(lblChucVu);

        lblDiaChi = new JLabel("Địa chỉ: ");
        lblDiaChi.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblDiaChi.setBounds(10, 364, 71, 14);
        pnlRight.add(lblDiaChi);

        txtMaNhanVien = new JTextField();
        txtMaNhanVien.setBounds(102, 167, 147, 20);
        pnlRight.add(txtMaNhanVien);
        txtMaNhanVien.setColumns(10);

        txtTenNV = new JTextField();
        txtTenNV.setBounds(102, 202, 147, 20);
        pnlRight.add(txtTenNV);
        txtTenNV.setColumns(10);

        txtSDT = new JTextField();
        txtSDT.setBounds(102, 246, 147, 20);
        pnlRight.add(txtSDT);
        txtSDT.setColumns(10);

        cmbGioiTinh = new JComboBox();
        cmbGioiTinh.setBounds(102, 290, 86, 22);
        cmbGioiTinh.addItem("Nam");
        cmbGioiTinh.addItem("Nữ");
        pnlRight.add(cmbGioiTinh);

        cmbCaLam = new JComboBox();
        cmbCaLam.setBounds(260, 323, 86, 22);
        cmbCaLam.addItem("Sáng");
        cmbCaLam.addItem("Chiều");
        pnlRight.add(cmbCaLam);

        cmbChucVu = new JComboBox();
        cmbChucVu.setBounds(102, 323, 86, 22);
        cmbChucVu.addItem("Quản lý");
        cmbChucVu.addItem("Nhân viên");
        pnlRight.add(cmbChucVu);

        txtDiaChi = new JTextField();
        txtDiaChi.setBounds(91, 360, 433, 23);
        pnlRight.add(txtDiaChi);
        txtDiaChi.setColumns(10);

        btnThemNV = new JButton("   Thêm ");
        btnThemNV.setIcon(new ImageIcon(Pnl_QuanLyNhanVien.class.getResource("/gui/icon/add-user.png")));
        btnThemNV.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnThemNV.setBounds(212, 632, 117, 32);
        pnlRight.add(btnThemNV);

        btnSuaNV = new JButton("   Sửa");
        btnSuaNV.setIcon(new ImageIcon(Pnl_QuanLyNhanVien.class.getResource("/gui/icon/contract.png")));
        btnSuaNV.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnSuaNV.setBounds(44, 632, 117, 32);
        pnlRight.add(btnSuaNV);

        btnXoaNV = new JButton("   Xóa");
        btnXoaNV.setIcon(new ImageIcon(Pnl_QuanLyNhanVien.class.getResource("/gui/icon/delete.png")));
        btnXoaNV.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnXoaNV.setBounds(44, 693, 117, 32);
        pnlRight.add(btnXoaNV);

        btnTimKiem = new JButton("   Tìm");
        btnTimKiem.setIcon(new ImageIcon(Pnl_QuanLyNhanVien.class.getResource("/gui/icon/loupe.png")));
        btnTimKiem.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnTimKiem.setBounds(376, 632, 117, 32);
        pnlRight.add(btnTimKiem);

        btnLamMoi = new JButton("Làm mới");
        btnLamMoi.setIcon(new ImageIcon(Pnl_QuanLyNhanVien.class.getResource("/gui/icon/refresh-button.png")));
        btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnLamMoi.setBounds(212, 693, 117, 32);
        pnlRight.add(btnLamMoi);

        btnLuu = new JButton("   Lưu");
        btnLuu.setIcon(new ImageIcon(Pnl_QuanLyNhanVien.class.getResource("/gui/icon/diskette.png")));
        btnLuu.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnLuu.setBounds(376, 693, 117, 32);
        pnlRight.add(btnLuu);

        lblCCCD = new JLabel("Căn cước công dân: ");
        lblCCCD.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblCCCD.setBounds(266, 170, 111, 14);
        pnlRight.add(lblCCCD);

        txtCCCD = new JTextField();
        txtCCCD.setBounds(387, 167, 147, 20);
        pnlRight.add(txtCCCD);
        txtCCCD.setColumns(10);

        lblEmail = new JLabel("Email: ");
        lblEmail.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblEmail.setBounds(266, 205, 46, 14);
        pnlRight.add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(387, 202, 147, 20);
        pnlRight.add(txtEmail);
        txtEmail.setColumns(10);

        lblNgaySinh = new JLabel("Ngày sinh: ");
        lblNgaySinh.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblNgaySinh.setBounds(266, 249, 76, 14);
        pnlRight.add(lblNgaySinh);

        dateChooserNgaySinh = new JDateChooser();
        dateChooserNgaySinh.setBounds(387, 249, 147, 20);
        pnlRight.add(dateChooserNgaySinh);

        btnChonAnh = new JButton("Chọn ảnh");
        btnChonAnh.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnChonAnh.setBounds(213, 87, 89, 32);
        pnlRight.add(btnChonAnh);

        pnlHinhAnh = new JPanel();
        pnlHinhAnh.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        pnlHinhAnh.setBounds(32, 51, 153, 101);
        pnlRight.add(pnlHinhAnh);
        pnlHinhAnh.setLayout(null);

        lblHinhAnh = new JLabel("Hình ảnh\r\n");
        lblHinhAnh.setIcon(null);
        lblHinhAnh.setBounds(0, 0, 153, 101);
        pnlHinhAnh.add(lblHinhAnh);

        pnlTim = new JPanel();
        pnlTim.setBorder(
                new TitledBorder(null, "T\u00ECm ki\u1EBFm", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        pnlTim.setBounds(44, 401, 449, 120);
        pnlRight.add(pnlTim);
        pnlTim.setLayout(null);

        lblSoDienThoai = new JLabel("Số điện thoại: ");
        lblSoDienThoai.setBounds(44, 34, 81, 14);
        lblSoDienThoai.setFont(new Font("Tahoma", Font.BOLD, 11));
        pnlTim.add(lblSoDienThoai);

        txtSDTTim = new JTextField();
        txtSDTTim.setColumns(10);
        txtSDTTim.setBounds(206, 31, 147, 20);
        pnlTim.add(txtSDTTim);

        txtTenTim = new JTextField();
        txtTenTim.setColumns(10);
        txtTenTim.setBounds(206, 74, 147, 20);
        pnlTim.add(txtTenTim);

        JLabel lblTenNhanVien_1 = new JLabel("Tên nhân viên: ");
        lblTenNhanVien_1.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblTenNhanVien_1.setBounds(44, 77, 93, 14);
        pnlTim.add(lblTenNhanVien_1);

        pnlBottom = new JPanel();
        pnlBottom.setBounds(589, 778, 1220, 82);
        add(pnlBottom);
        pnlBottom.setLayout(null);

        btnLamMoiBang = new JButton("   Làm mới bảng");
        btnLamMoiBang.setBounds(500, 21, 188, 39);
        pnlBottom.add(btnLamMoiBang);
        btnLamMoiBang.setIcon(new ImageIcon(Pnl_QuanLyNhanVien.class.getResource("/gui/icon/refresh-button.png")));
        btnLamMoiBang.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnLamMoiBang.addActionListener(this);

        pnlTop = new JPanel();
        pnlTop.setBounds(589, 42, 1220, 710);
        add(pnlTop);

        lblTitle = new JLabel("QUẢN LÝ NHÂN VIÊN");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 22));
        lblTitle.setBounds(270, 0, 1850, 31);
        add(lblTitle);

        pnlTop.setLayout(null);
        String header_NhanVien[] = {"STT", "Mã nhân viên", "Tên nhân viên", "Ngày sinh", "Số điện thoại", "Giới tính",
                "Ca làm", "Chức vụ", "Địa chỉ"};
        modelNhanVien = new DefaultTableModel(header_NhanVien, 0);
        tblNhanVien = new JTable(modelNhanVien);
        scrTblNhanVien = new JScrollPane(tblNhanVien, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrTblNhanVien.setBounds(10, 34, 1200, 620);
        pnlTop.add(scrTblNhanVien);

        tblNhanVien.getColumnModel().getColumn(0).setPreferredWidth(20);
        tblNhanVien.getColumnModel().getColumn(1).setPreferredWidth(70);
        tblNhanVien.getColumnModel().getColumn(2).setPreferredWidth(120);
        tblNhanVien.getColumnModel().getColumn(3).setPreferredWidth(70);
        tblNhanVien.getColumnModel().getColumn(4).setPreferredWidth(70);
        tblNhanVien.getColumnModel().getColumn(5).setPreferredWidth(40);
        tblNhanVien.getColumnModel().getColumn(6).setPreferredWidth(40);
        tblNhanVien.getColumnModel().getColumn(7).setPreferredWidth(50);
        tblNhanVien.getColumnModel().getColumn(8).setPreferredWidth(220);

        tblNhanVien.addMouseListener(this);
        try {
            docDuLieuTuArrayListVaoModel();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        moKhoaTextfields(false);
        btnLuu.setEnabled(false);
        txtSDTTim.setEditable(true);
        txtTenTim.setEditable(true);
        btnThemNV.addActionListener(this);
        btnChonAnh.addActionListener(this);
        btnSuaNV.addActionListener(this);
        btnLamMoi.addActionListener(this);
        btnLuu.addActionListener(this);
        btnTimKiem.addActionListener(this);
        btnXoaNV.addActionListener(this);

    }

    //-------------------------------------------------//
    @SneakyThrows
    @Override
    public void mouseClicked(MouseEvent e) {
        Object o = e.getSource();
        if (o.equals(tblNhanVien)) {
            // TODO Auto-generated method stub
            // TODO Auto-generated method stub
            int row = tblNhanVien.getSelectedRow();
            try {
                DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String) model.getValueAt(row, 3).toString());

                dateChooserNgaySinh.setDate(date);

                dateChooserNgaySinh.setDateFormatString("yyyy-MM-dd");
            } catch (Exception e2) {
                // TODO: handle exception
                System.out.println("sai");
            }
            txtMaNhanVien.setText(modelNhanVien.getValueAt(row, 1).toString());
            NhanVien nv;
            File file = new File("");
            try {
                nv = nhanVienService.timNhanVienTheoMa(txtMaNhanVien.getText());
                txtCCCD.setText(nv.getcCCD());
                txtEmail.setText(nv.getEmail());
                String hinhAnh = file.getAbsolutePath() + "\\hinhAnhHieuSach\\" + nv.getHinhAnh();
                System.out.println(hinhAnh);
                lblHinhAnh.setIcon(setSizeImageIconString(hinhAnh, lblHinhAnh.getWidth(), lblHinhAnh.getHeight()));

            } catch (SQLException e2) {
                // TODO Auto-generated catch block
                e2.printStackTrace();
            }

            txtTenNV.setText(modelNhanVien.getValueAt(row, 2).toString());
            txtSDT.setText(modelNhanVien.getValueAt(row, 4).toString());
            cmbGioiTinh
                    .setSelectedIndex(modelNhanVien.getValueAt(row, 5).toString().equalsIgnoreCase("Nam") ? 0 : 1);
            cmbCaLam
                    .setSelectedIndex(modelNhanVien.getValueAt(row, 6).toString().equalsIgnoreCase("Sáng") ? 0 : 1);
            cmbChucVu.setSelectedIndex(
                    modelNhanVien.getValueAt(row, 7).toString().equalsIgnoreCase("Quản lý") ? 0 : 1);
            txtDiaChi.setText(modelNhanVien.getValueAt(row, 8).toString());
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
        if (obj.equals(btnThemNV)) {
            if (btnThemNV.getText().equalsIgnoreCase("   Thêm ")) {
                tblNhanVien.removeMouseListener(this);
                btnLuu.setEnabled(true);
                cmbChucVu.setSelectedItem(null);
                cmbChucVu.addItemListener(new ItemListener() {
                    @SneakyThrows
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        // TODO Auto-generated method stub
                        if (e.getStateChange() == ItemEvent.SELECTED) {

                            try {
                                dsNhanVien = nhanVienService.getDSNhanVien();
                            } catch (SQLException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                            String maNVMoi = "";
                            if (cmbChucVu.getSelectedItem().toString().equals("Quản lý")) {
                                maNVMoi = "QL";
                            } else {
                                maNVMoi = "NV";
                            }
                            LocalDate myObj = LocalDate.now();
                            String ngayMaNV = String.valueOf(myObj.getDayOfMonth());
                            String thangMaNV = String.valueOf(myObj.getMonthValue());
                            // String namMaNV = String.valueOf(myObj.getYear());
                            String soLuong = "";
                            if (dsNhanVien.size() < 10) {
                                soLuong = "0" + (dsNhanVien.size() + 1);
                            } else if (dsNhanVien.size() < 100) {
                                soLuong = "" + (dsNhanVien.size() + 1);
                            }
                            maNVMoi = maNVMoi + ngayMaNV + thangMaNV + soLuong;
                            txtMaNhanVien.setText(maNVMoi);
                        }
                    }
                });
                moKhoaTextfields(true);
                moKhoaControls(false);
                txtSDTTim.setEditable(false);
                txtTenTim.setEditable(false);
                btnLuu.setEnabled(true);
                btnThemNV.setEnabled(true);
                clearTxtfields();
                btnThemNV.setText("Hủy");
                txtMaNhanVien.setEditable(false);

            } else if (btnThemNV.getText().equalsIgnoreCase("Hủy")) {
                tblNhanVien.addMouseListener(this);
                btnLuu.setEnabled(false);
                moKhoaTextfields(false);
                moKhoaControls(true);
                clearTxtfields();
                btnLuu.setEnabled(false);
                btnThemNV.setText("   Thêm ");
            }
        } else if (obj.equals(btnLuu) && btnThemNV.getText().equalsIgnoreCase("Hủy")) {

            NhanVien nv = revertNhanVienFromTextfields();

            try {
                if (nhanVienService.themNhanVien(nv)) {
                    updateTableData(nv);
                    // bentaotaikhoanbaothanhcong
                    JOptionPane.showMessageDialog(this, "Thêm thành công 1 nhân viên");
                    btnLuu.setEnabled(false);
                    tblNhanVien.addMouseListener(this);
                    moKhoaControls(true);
                    moKhoaTextfields(false);
                    btnThemNV.setText("   Thêm ");
                    WinThemTaiKhoan frmThemTK = new WinThemTaiKhoan(nv);
                    frmThemTK.setVisible(true);
                } else {
                    System.out.println("Thme k dc");
                    int erThem = NhanVienServiceImpl.errorsThem;
                    if (erThem == 1) {
                        JOptionPane.showMessageDialog(this, "Tên nhân viên phải theo mẫu : Nguyen Van Anh");
                        txtTenNV.setText("");
                        txtTenNV.requestFocus();
                    } else if (erThem == 2) {
                        JOptionPane.showMessageDialog(this, "Số điện thoại cần có 10 chữ số ví dụ: 0384600357");
                        txtSDT.setText("");
                        txtSDT.requestFocus();
                    } else if (erThem == 3) {
                        JOptionPane.showMessageDialog(this, "Căn cước cần có 12 chữ số ví dụ: 038460035712");
                        txtCCCD.setText("");
                        txtCCCD.requestFocus();
                    } else if (erThem == 4) {
                        JOptionPane.showMessageDialog(this, "Email cần dạng canh@gmail.com");
                        txtEmail.setText("");
                        txtEmail.requestFocus();
                    } else if (erThem == 5) {
                        JOptionPane.showMessageDialog(this, "Địa chỉ bao gồm số và chữ : 12 Nguyen Van Bao");
                        txtDiaChi.setText("");
                        txtDiaChi.requestFocus();
                    }
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(this, "Trùng mã");
            }
        } else if (obj.equals(btnLuu) && btnSuaNV.getText().equalsIgnoreCase("Hủy")) {

            NhanVien nv = revertNhanVienFromTextfields();
            try {
                if (nhanVienService.suaNhanVien(nv)) {
                    JOptionPane.showMessageDialog(this, "Sửa thành công 1 nhân viên");
                    btnLuu.setEnabled(false);
                    btnSuaNV.setText("   Sửa");
                    tblNhanVien.addMouseListener(this);
                    moKhoaControls(true);
                    moKhoaTextfields(false);
                    editOnRow();
                } else {
                    int erSua = NhanVienServiceImpl.errorsSua;
                    if (erSua == 1) {
                        JOptionPane.showMessageDialog(this, "Tên nhân viên phải theo mẫu : Nguyen Van Anh");
                        txtTenNV.setText("");
                        txtTenNV.requestFocus();
                    } else if (erSua == 2) {
                        JOptionPane.showMessageDialog(this, "Số điện thoại cần có 10 chữ số ví dụ: 0384600357");
                        txtSDT.setText("");
                        txtSDT.requestFocus();
                    } else if (erSua == 3) {
                        JOptionPane.showMessageDialog(this, "Căn cước cần có 12 chữ số ví dụ: 038460035712");
                        txtCCCD.setText("");
                        txtCCCD.requestFocus();
                    } else if (erSua == 4) {
                        JOptionPane.showMessageDialog(this, "Email cần dạng canh@gmail.com");
                        txtEmail.setText("");
                        txtEmail.requestFocus();
                    } else if (erSua == 5) {
                        JOptionPane.showMessageDialog(this, "Địa chỉ bao gồm số và chữ : 12 Nguyen Van Bao");
                        txtDiaChi.setText("");
                        txtDiaChi.requestFocus();
                    }
                }
            } catch (HeadlessException | SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        } else if (obj.equals(btnXoaNV)) {
            if (tblNhanVien.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(this, "Phải chọn dòng trước khi xóa");
            } else {

                String maXoa = txtMaNhanVien.getText();
                try {
                    hoaDonService.setNullChoMaNhanVienTrongHoaDon(maXoa);
                    taiKhoanService.xoaTaiKhoan(maXoa);

                    int resq = JOptionPane.showConfirmDialog(this, "Bạn có chắc là muốn xóa nhân viên không ?",
                            "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (resq == JOptionPane.NO_OPTION)
                        System.exit(0);

                    else {
                        if (nhanVienService.xoaNhanVien(maXoa)) {

                            JOptionPane.showMessageDialog(this,
                                    "Xóa thành công 1 nhân viên và tài khoản của nhân viên đó");
                            modelNhanVien.removeRow(tblNhanVien.getSelectedRow());
                        }
                    }

                } catch (HeadlessException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }

        } else if (obj.equals(btnSuaNV)) {
            if (tblNhanVien.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(this, "Phải chọn dòng trước khi sửa");
            } else {
                if (btnSuaNV.getText().equalsIgnoreCase("   Sửa")) {
                    btnLuu.setEnabled(true);
                    tblNhanVien.removeMouseListener(this);
                    moKhoaTextfields(true);
                    txtSDTTim.setEditable(false);
                    txtTenTim.setEditable(false);
                    txtMaNhanVien.setEditable(false);
                    moKhoaControls(false);
                    btnLuu.setEnabled(true);
                    btnSuaNV.setEnabled(true);
                    btnSuaNV.setText("Hủy");
                } else if (btnSuaNV.getText().equalsIgnoreCase("Hủy")) {
                    tblNhanVien.addMouseListener(this);
                    moKhoaTextfields(false);
                    moKhoaControls(true);
                    clearTxtfields();
                    btnLuu.setEnabled(false);
                    btnSuaNV.setText("   Sửa");

                }
            }
        } else if (obj.equals(btnTimKiem) && btnTimKiem.getText().equalsIgnoreCase("   Tìm")) {
            txtSDTTim.setEditable(true);
            txtTenTim.setEditable(true);
            moKhoaControls(false);
            btnTimKiem.setEnabled(true);
            NhanVien nv = new NhanVien();
            String sdtTim = txtSDTTim.getText();
            String tenTim = txtTenTim.getText();

            if (!sdtTim.isEmpty() && tenTim.isEmpty()) {
                try {
                    dsNhanVien = nhanVienService.timNhanVienTheoSDT(sdtTim);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                if (dsNhanVien.size() != 0) {

                    xoaHetDuLieu();
                    for (NhanVien nhanVien : dsNhanVien) {
                        try {
                            updateTableData(nhanVien);
                            moKhoaControls(true);
                        } catch (SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy");
                }
            } else if (sdtTim.isEmpty() && !tenTim.isEmpty()) {
                try {
                    dsNhanVien = nhanVienService.timDSNhanVienTheoTen(tenTim);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                if (dsNhanVien.size() != 0) {
                    xoaHetDuLieu();
                    for (NhanVien nhanVien : dsNhanVien) {
                        try {
                            updateTableData(nhanVien);
                            moKhoaControls(true);
                        } catch (SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy");
                }
            } else if (!sdtTim.isEmpty() && !tenTim.isEmpty()) {
                dsNhanVien = nhanVienService.getListNhanVienByNameAndSDT(tenTim, sdtTim);
                if (dsNhanVien.size() != 0) {

                    xoaHetDuLieu();
                    for (NhanVien nhanVien : dsNhanVien) {
                        try {
                            updateTableData(nhanVien);
                            moKhoaControls(true);
                        } catch (SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }

                } else {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy");
                }
            } else if (sdtTim.isEmpty() && tenTim.isEmpty()) {
                xoaHetDuLieu();
                try {
                    docDuLieuTuArrayListVaoModel();
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin tìm kiếm ");
            }

        } else if (obj.equals(btnLamMoi)) {
            clearTxtfields();
        } else if (obj.equals(btnChonAnh)) {
            fch = new JFileChooser("D:\\hinhAnhHieuSach");
            FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("hinh anh", "jpg", "png");
            fch.setFileFilter(imageFilter);
            fch.setMultiSelectionEnabled(false);

            int x = fch.showDialog(this, "Chọn Ảnh");
            if (x == JFileChooser.APPROVE_OPTION) {
                file = fch.getSelectedFile();

                String name = file.getName();
                System.out.println(name);

                lblHinhAnh.setText("");
                lblHinhAnh.setIcon(resizeImage(file.getAbsolutePath()));
            }
        } else if (obj.equals(btnLamMoiBang)) {
            xoaHetDuLieu();
            try {
                docDuLieuTuArrayListVaoModel();
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

    }

    public NhanVien revertNhanVienFromTextfields() {
        String maNV = txtMaNhanVien.getText();
        String tenNV = txtTenNV.getText();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String day = sdf.format(dateChooserNgaySinh.getDate());
        String date[] = day.split("-");
        int nam = Integer.parseInt(date[0]);
        int thang = Integer.parseInt(date[1]);
        int ngay = Integer.parseInt(date[2]);

        LocalDate lcDate = LocalDate.of(nam, thang, ngay);
        String cccd = txtCCCD.getText();
        String diaChi = txtDiaChi.getText();
        String sdt = txtSDT.getText();
        String email = txtEmail.getText();
        boolean gioiTinh = cmbGioiTinh.getSelectedItem().toString() == "Nam" ? true : false;
        boolean chucVu = cmbChucVu.getSelectedItem().toString() == "Quản lý" ? true : false;
        boolean caLam = cmbCaLam.getSelectedItem().toString() == "Sáng" ? true : false;

        String hinhAnh = "";
        if (file != null) {
            hinhAnh = file.getName();
        }

        NhanVien nv = new NhanVien(maNV, tenNV, lcDate, cccd, diaChi, sdt, gioiTinh, email, chucVu, caLam, hinhAnh,
                null, null);
        return nv;
    }

    public void xoaHetDuLieu() {
        DefaultTableModel dtm = (DefaultTableModel) tblNhanVien.getModel();
        dtm.getDataVector().removeAllElements();
    }

    public void docDuLieuTuArrayListVaoModel() throws Exception {
        dsNhanVien = nhanVienService.getDSNhanVien();
        int i = 1;
        for (NhanVien nv : dsNhanVien) {

            modelNhanVien.addRow(new Object[]{i++, nv.getMaNhanVien(), nv.getHoTenNhanVien(), nv.getNgaySinh(),
                    nv.getsDT(), nv.isGioiTinh() == true ? "Nam" : "Nữ", nv.isCaLamViec() == true ? "Sáng" : "Chiều",
                    nv.isChucVu() == true ? "Quản lý" : "Nhân viên", nv.getDiaChi()});
        }
    }

    private void updateTableData(NhanVien nv) throws Exception {
        dsNhanVien = nhanVienService.getDSNhanVien();
        int i = dsNhanVien.size();
        modelNhanVien.addRow(new Object[]{i++, nv.getMaNhanVien(), nv.getHoTenNhanVien(), nv.getNgaySinh(),
                nv.getsDT(), nv.isGioiTinh() == true ? "Nam" : "Nữ", nv.isCaLamViec() == true ? "Sáng" : "Chiều",
                nv.isChucVu() == true ? "Quản lý" : "Nhân viên", nv.getDiaChi()});
    }

    private void moKhoaControls(boolean b) {
        btnThemNV.setEnabled(b);
        btnXoaNV.setEnabled(b);
        btnSuaNV.setEnabled(b);
        btnTimKiem.setEnabled(b);
    }

    private void moKhoaTextfields(boolean b) {
        txtMaNhanVien.setEditable(b);
        txtTenNV.setEditable(b);
        txtDiaChi.setEditable(b);
        txtSDT.setEditable(b);
        cmbChucVu.setEditable(b);
        cmbCaLam.setEditable(b);
        txtSDTTim.setEditable(b);
        txtTenTim.setEditable(b);
        txtCCCD.setEditable(b);
        txtEmail.setEditable(b);

    }

    private void clearTxtfields() {
        txtMaNhanVien.setText("");
        txtTenNV.setText("");
        txtSDT.setText("");
        txtDiaChi.setText("");
        cmbCaLam.setSelectedIndex(0);
        cmbChucVu.setSelectedIndex(0);
        txtSDTTim.setText("");
        txtTenTim.setText("");
        txtCCCD.setText("");
        txtEmail.setText("");
    }

    public ImageIcon resizeImage(String imgPath) {
        ImageIcon myImage = new ImageIcon(imgPath);
        Image img = myImage.getImage();
        Image newImg = img.getScaledInstance(pnlHinhAnh.getWidth(), pnlHinhAnh.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }

    public void editOnRow() {
        int row = tblNhanVien.getSelectedRow();
        NhanVien nv = revertNhanVienFromTextfields();

        tblNhanVien.setValueAt(nv.getMaNhanVien(), row, 1);
        tblNhanVien.setValueAt(nv.getHoTenNhanVien(), row, 2);
        tblNhanVien.setValueAt(nv.getNgaySinh(), row, 3);
        tblNhanVien.setValueAt(nv.getsDT(), row, 4);
        tblNhanVien.setValueAt(nv.isGioiTinh() == true ? "Nam" : "Nữ", row, 5);
        tblNhanVien.setValueAt(nv.isCaLamViec() == true ? "Sáng" : "Chiều", row, 6);
        tblNhanVien.setValueAt(nv.isChucVu() == true ? "Quản lí" : "Nhân viên", row, 7);
        tblNhanVien.setValueAt(nv.getDiaChi(), row, 8);

    }

    public ImageIcon setSizeImageIconURL(URL url, int width, int height) {
        ImageIcon image = new ImageIcon(url);
        Image imageSet = image.getImage();
        imageSet = imageSet.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        image = new ImageIcon(imageSet);
        return image;
    }

    public ImageIcon setSizeImageIconString(String s, int width, int height) {
        ImageIcon image = new ImageIcon(s);
        Image imageSet = image.getImage();
        imageSet = imageSet.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        image = new ImageIcon(imageSet);
        return image;
    }

}
