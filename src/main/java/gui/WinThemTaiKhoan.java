package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;

import dao.TaiKhoanDao;
import entities.NhanVien;
import entities.TaiKhoan;
import service.*;
import service.impl.TaiKhoanServiceImpl;

import java.awt.Color;

public class WinThemTaiKhoan extends JFrame implements ActionListener {

	private JPanel pnlContainer;
	private JTextField txtTenDangNhap;
	private JPasswordField pwd;
	private JPasswordField pwdXacNhan;
	private JLabel lblTenDangNhap;
	private JLabel lblMatKhau;
	private JLabel lblXacNhanMK;
	private JButton btnTaoTaiKhoan;
	private JComboBox cmbQuyen;
	private JLabel lblLoaiTaiKhoan;
	private NhanVien nv;

	private String matKhau;
	private ArrayList<TaiKhoan> dsTaiKhoan;

	private static final String URL = "rmi://192.168.40.54:7878/";
	private SanPhamService sanPhamService = (SanPhamService) Naming.lookup(URL + "sanPham");
	private SachLoiService sachLoiService = (SachLoiService) Naming.lookup(URL + "sachLoi");
	private HoaDonService hoaDonService = (HoaDonService) Naming.lookup(URL + "hoaDon");
	private TaiKhoanService taiKhoanService = (TaiKhoanService) Naming.lookup(URL + "taiKhoan");
	private NhanVienService nhanVienService = (NhanVienService) Naming.lookup(URL + "nhanVien");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					FrmThemTaiKhoan frame = new FrmThemTaiKhoan(null);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
	}

	/**
	 * Create the frame.
	 */
	public WinThemTaiKhoan(NhanVien nv) throws Exception {
		//
		setResizable(false);
		this.nv = nv;
		setTitle("Tạo tài khoản\r\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 557, 351);
		pnlContainer = new JPanel();
		pnlContainer.setBackground(new Color(0, 206, 209));
		pnlContainer.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(pnlContainer);
		pnlContainer.setLayout(null);
		setLocationRelativeTo(null);

		lblTenDangNhap = new JLabel("Tên đăng nhập: ");
		lblTenDangNhap.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTenDangNhap.setBounds(28, 63, 124, 19);
		pnlContainer.add(lblTenDangNhap);

		lblMatKhau = new JLabel("Mật khẩu: ");
		lblMatKhau.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMatKhau.setBounds(28, 125, 91, 14);
		pnlContainer.add(lblMatKhau);

		lblXacNhanMK = new JLabel("Xác nhận mật khẩu: ");
		lblXacNhanMK.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblXacNhanMK.setBounds(28, 191, 158, 19);
		pnlContainer.add(lblXacNhanMK);

		txtTenDangNhap = new JTextField();
		txtTenDangNhap.setBounds(245, 64, 210, 20);
		pnlContainer.add(txtTenDangNhap);
		txtTenDangNhap.setColumns(10);

		pwd = new JPasswordField();
		pwd.setBounds(245, 124, 210, 20);
		pnlContainer.add(pwd);

		pwdXacNhan = new JPasswordField();
		pwdXacNhan.setBounds(245, 192, 210, 20);
		pnlContainer.add(pwdXacNhan);

		btnTaoTaiKhoan = new JButton("Tạo");
		btnTaoTaiKhoan.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnTaoTaiKhoan.setBounds(170, 266, 158, 35);
		pnlContainer.add(btnTaoTaiKhoan);

		cmbQuyen = new JComboBox();
		cmbQuyen.setBounds(245, 11, 210, 22);
		cmbQuyen.addItem("Quản lý");
		cmbQuyen.addItem("Nhân viên");
		cmbQuyen.setSelectedItem(nv.isChucVu() == true ? "Quản lý" : "Nhân viên");
		cmbQuyen.setEditable(false);
		pnlContainer.add(cmbQuyen);

		lblLoaiTaiKhoan = new JLabel("Loại tài khoản: ");
		lblLoaiTaiKhoan.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLoaiTaiKhoan.setBounds(28, 11, 124, 22);
		pnlContainer.add(lblLoaiTaiKhoan);

		btnTaoTaiKhoan.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if (obj.equals(btnTaoTaiKhoan)) {
			try {
				if (taiKhoanService.insertAccount(revertTaiKhoanFromTextfields()) > 0)
					this.setVisible(false);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, "Tên đăng nhập có dạng: TK001, không được trùng");
			}
		}

	}

	public TaiKhoan revertTaiKhoanFromTextfields() throws Exception {
		if (taiKhoanService.getList() != null) {
			dsTaiKhoan = taiKhoanService.getList();
			String tenDN = txtTenDangNhap.getText();
			if (tenDN.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Tên đăng nhập không được rỗng");
			} else {
				char[] pwdBanDauEncode = this.pwd.getPassword();
				String pwdBanDau = new String(pwdBanDauEncode);
				char[] pwdXacNhanEncode = this.pwdXacNhan.getPassword();
				String pwdXacNhanValue = new String(pwdXacNhanEncode);
				if (pwdBanDau.isEmpty()) {
					JOptionPane.showMessageDialog(this, "Mật khẩu không được rỗng");
				} else {
					if (pwdBanDau.equals(pwdXacNhanValue)) {
						matKhau = pwdXacNhanValue;
						boolean quyen = cmbQuyen.getSelectedItem().toString() == "Quản lý" ? true : false;
						TaiKhoan tk = new TaiKhoan(tenDN, matKhau, nv, quyen);
						return tk;
					} else {
						JOptionPane.showMessageDialog(this, "Mật khẩu xác nhận không đúng");
						this.pwd.setText("");
						pwdXacNhan.setText("");
						this.pwd.requestFocus();
						return null;
					}
				}

			}

		}
		return null;

	}
}
