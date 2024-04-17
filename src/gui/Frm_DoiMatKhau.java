package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entity.NhanVien;
import entity.TaiKhoan;
import service.impl.NhanVienServiceImpl;
import service.impl.TaiKhoanServiceImpl;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.Color;

public class Frm_DoiMatKhau extends JFrame implements ActionListener, MouseListener {

	private JPanel pnlContentPane;
	private JLabel lblDoiMatKhau;
	private JLabel lblOldPass;
	private JLabel lblNewPass;
	private JLabel lblNewPassConfirm;
	private JButton btnDoi;
	private JButton btnThoat;
	private JPasswordField txtPasswordFieldCu;
	private JPasswordField txtPasswordFieldMoi;
	private JPasswordField txtPasswordFieldXacNhan;
	private NhanVienServiceImpl iNhanvien;
	private NhanVien nv;
	private JLabel lblTenNV;
	WinLogin dangNhap = new WinLogin();
	TaiKhoan taiKhoan = dangNhap.getTaiKhoanDangNhapThanhCong();
	private TaiKhoanServiceImpl iTaiKhoan;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {
					// frame = new FrmDoiMatKhau();
					// frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public Frm_DoiMatKhau() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 541, 337);
		pnlContentPane = new JPanel();
		pnlContentPane.setBackground(new Color(0, 206, 209));
		pnlContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setResizable(false);

		setContentPane(pnlContentPane);
		pnlContentPane.setLayout(null);

		lblDoiMatKhau = new JLabel("ĐỔI MẬT KHẨU");
		lblDoiMatKhau.setForeground(new Color(255, 0, 0));
		lblDoiMatKhau.setHorizontalAlignment(SwingConstants.CENTER);
		lblDoiMatKhau.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblDoiMatKhau.setBounds(0, 11, 525, 64);
		pnlContentPane.add(lblDoiMatKhau);

		lblOldPass = new JLabel("Nhập mật khẩu cũ:");
		lblOldPass.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblOldPass.setBounds(24, 89, 177, 14);
		pnlContentPane.add(lblOldPass);

		lblNewPass = new JLabel("Nhập mật khẩu mới:");
		lblNewPass.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewPass.setBounds(24, 147, 177, 23);
		pnlContentPane.add(lblNewPass);

		lblNewPassConfirm = new JLabel("Xác nhận mật khẩu mới:");
		lblNewPassConfirm.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewPassConfirm.setBounds(24, 218, 177, 14);
		pnlContentPane.add(lblNewPassConfirm);

		btnDoi = new JButton("Đổi");
		btnDoi.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnDoi.setBounds(112, 264, 89, 23);
		pnlContentPane.add(btnDoi);

		btnThoat = new JButton("Thoát");
		btnThoat.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnThoat.setBounds(300, 264, 89, 23);
		pnlContentPane.add(btnThoat);

		txtPasswordFieldCu = new JPasswordField();
		txtPasswordFieldCu.setBounds(201, 86, 292, 20);
		pnlContentPane.add(txtPasswordFieldCu);

		txtPasswordFieldMoi = new JPasswordField();
		txtPasswordFieldMoi.setBounds(201, 149, 292, 20);
		pnlContentPane.add(txtPasswordFieldMoi);

		txtPasswordFieldXacNhan = new JPasswordField();
		txtPasswordFieldXacNhan.setBounds(201, 215, 292, 20);
		pnlContentPane.add(txtPasswordFieldXacNhan);

		btnDoi.addActionListener(this);
		btnThoat.addActionListener(this);

		lblTenNV = new JLabel();
		pnlContentPane.add(lblTenNV);

		iNhanvien = new NhanVienServiceImpl();
		nv = new NhanVien();
		nv = iNhanvien.timNhanVienTheoMa(taiKhoan.getNhanVien().getMaNhanVien());
		lblTenNV.setText(nv.getHoTenNhanVien());
		lblTenNV.setVisible(false);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnThoat)) {
			this.setVisible(false);
		}
		if (o.equals(btnDoi)) {
			iTaiKhoan = new TaiKhoanServiceImpl();
			char[] pfCu = txtPasswordFieldCu.getPassword();
			String valueCU = new String(pfCu);

			char[] pfMoi = txtPasswordFieldMoi.getPassword();
			String valueMoi = new String(pfMoi);

			char[] pfXacNhan = txtPasswordFieldXacNhan.getPassword();
			String valueXacNhan = new String(pfXacNhan);

			String passCu = taiKhoan.getMatKhau();
			if (passCu.equals(valueCU)) {
				if(!valueMoi.isEmpty()) {
				if (valueMoi.equals(valueXacNhan)) {
					iTaiKhoan.doiMatKhau(valueMoi, taiKhoan.getNhanVien().getMaNhanVien());
					JOptionPane.showMessageDialog(this, "Đổi mật khẩu thành công");
					this.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(this, "Mật khẩu xác nhận không khớp");
				}
				}
				else {
					JOptionPane.showMessageDialog(this, "Mật khẩu không được để trống");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Mật khẩu cũ nhập không đúng");
			}
		}
	}

}
