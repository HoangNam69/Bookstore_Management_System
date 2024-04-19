package gui;

import java.awt.EventQueue;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.TaiKhoanDao;
import entities.TaiKhoan;

import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;

public class WinLogin extends JFrame implements ActionListener, MouseListener {

	private JPanel pnlContainer;
	private JTextField txtTenDangNhap;
	private JTextField txtMatKhau;
	private JPasswordField pwdLogin;
	private JLabel lblTieuDe;
	private JButton btnDangNhap;
	private JButton btnQuenMatKhau;
	private JPanel pnlIconUser;
	private JLabel lblIconUser;
	private JPanel pnlIconPassword;
	private JLabel lblIconPassword;
	private JLabel lblBackground;
	public static TaiKhoan taiKhoan;
	public TaiKhoanDao taiKhoanDao;
	public WinQuenMatKhau winQuenMatKhau = new WinQuenMatKhau();

	// public static NhanVien nv;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinLogin frame = new WinLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public int GetMaxWidth() {
		return GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
	}

	public int GetMaxHeight() {
		return GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
	}

	// set the MAXIMUM size....

	public WinLogin() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setSize();
		setBounds(GetMaxWidth() / 4, GetMaxHeight() / 4, 965, 537);
		pnlContainer = new JPanel();
		pnlContainer.setBackground(new Color(255, 255, 255));
		pnlContainer.setForeground(new Color(0, 0, 0));
		pnlContainer.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(pnlContainer);
		pnlContainer.setLayout(null);

		lblTieuDe = new JLabel("HỆ THỐNG QUẢN LÝ NHÀ SÁCH");
		lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDe.setForeground(Color.RED);
		lblTieuDe.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblTieuDe.setBounds(0, 0, 944, 55);
		pnlContainer.add(lblTieuDe);

		btnDangNhap = new JButton("Đăng nhập");
		btnDangNhap.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnDangNhap.setForeground(new Color(255, 255, 0));
		btnDangNhap.setBackground(new Color(30, 144, 255));
		btnDangNhap.setBounds(592, 342, 137, 48);
		pnlContainer.add(btnDangNhap);

		btnQuenMatKhau = new JButton("Quên mật khẩu");
		btnQuenMatKhau.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnQuenMatKhau.setBackground(new Color(30, 144, 255));
		btnQuenMatKhau.setForeground(new Color(255, 255, 0));
		btnQuenMatKhau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnQuenMatKhau.setBounds(750, 342, 137, 48);
		pnlContainer.add(btnQuenMatKhau);

		txtTenDangNhap = new JTextField();
		txtTenDangNhap.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtTenDangNhap.setForeground(new Color(255, 255, 0));
		txtTenDangNhap.setBackground(new Color(0, 0, 128));
		txtTenDangNhap.setText("duc");

		txtTenDangNhap.setBounds(650, 136, 237, 55);
		pnlContainer.add(txtTenDangNhap);
		txtTenDangNhap.setColumns(10);

		txtMatKhau = new JPasswordField();
		txtMatKhau.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtMatKhau.setForeground(new Color(255, 255, 0));
		txtMatKhau.setBackground(new Color(0, 0, 128));
		txtMatKhau.setText("123456");
		txtMatKhau.setBounds(650, 236, 237, 55);
		pnlContainer.add(txtMatKhau);
		txtMatKhau.setColumns(10);

		pnlIconUser = new JPanel();
		pnlIconUser.setBackground(new Color(0, 0, 128));
		pnlIconUser.setBounds(591, 136, 61, 55);
		pnlContainer.add(pnlIconUser);
		pnlIconUser.setLayout(null);

		lblIconUser = new JLabel("");
		lblIconUser.setForeground(new Color(0, 0, 0));
		lblIconUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconUser.setIcon(new ImageIcon(WinLogin.class.getResource("/gui/icon/woman.png")));
		lblIconUser.setBounds(10, 11, 41, 33);

		pnlIconUser.add(lblIconUser);

		pnlIconPassword = new JPanel();
		pnlIconPassword.setBackground(new Color(0, 0, 128));
		pnlIconPassword.setBounds(592, 236, 60, 55);
		pnlContainer.add(pnlIconPassword);
		pnlIconPassword.setLayout(null);

		lblIconPassword = new JLabel("");
		lblIconPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconPassword.setIcon(new ImageIcon(WinLogin.class.getResource("/gui/icon/lock.png")));
		lblIconPassword.setBounds(10, 11, 40, 33);
		pnlIconPassword.add(lblIconPassword);

		lblBackground = new JLabel("");
		lblBackground.setIcon(
				new ImageIcon(WinLogin.class.getResource("/gui/icon/login_1.jpg")));
		lblBackground.setBounds(28, 45, 630, 450);
		pnlContainer.add(lblBackground);
		btnDangNhap.addActionListener(this);
		btnQuenMatKhau.addActionListener(this);
		btnDangNhap.addMouseListener(this);

		btnQuenMatKhau.addMouseListener(this);
		pnlContainer.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if (txtMatKhau.getText().isEmpty()) {
					txtMatKhau.setText("Mật khẩu");
				}
				if (txtTenDangNhap.getText().isEmpty()) {
					txtTenDangNhap.setText("Tên đăng nhập");
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		txtMatKhau.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if (txtMatKhau.getText().equals("Mật khẩu")) {
					txtMatKhau.setText("");
				}

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		txtTenDangNhap.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method st
				if (txtTenDangNhap.getText().trim().equals("Tên đăng nhập")) {
					txtTenDangNhap.setText("");
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		String userName = txtTenDangNhap.getText();
		char[] chPassWord = ((JPasswordField) txtMatKhau).getPassword();
		String password = new String(chPassWord);
		boolean status = false;
		TaiKhoanDao accControl = new TaiKhoanDao();
		List<TaiKhoan> list = accControl.getList();

		if (obj.equals(btnDangNhap)) {
			// System.out.println("ok");
			for (TaiKhoan account : list) {
				if (userName.equalsIgnoreCase(account.getTenDangNhap())) {
					if (password.trim().equalsIgnoreCase(account.getMatKhau())) {
						if (account.isQuyen() == true) {
							// System.out.println(account);
							taiKhoan = account;
							this.dispose();
							try {
								new WinQuanLy().setVisible(true);
							} catch (Exception e1) {
								// TODO Auto-generated catch blockTK
								e1.printStackTrace();
							}
							status = true;
							break;
						}
						if (account.isQuyen() == false) {
							taiKhoan = account;
							this.dispose();
							try {
								new WinNhanVien().setVisible(true);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							status = true;
							break;
						}
					}
				}
			}
			if (status == false) {
				JOptionPane.showMessageDialog(null, "Đăng nhập thất bại");
				txtTenDangNhap.selectAll();
				txtTenDangNhap.requestFocus();
			}
		}
		if (obj.equals(btnQuenMatKhau)) {
			int resq = JOptionPane.showConfirmDialog(this, "Bạn có chắc là muốn đổi mật khẩu không ?", "Confirm",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (resq == JOptionPane.NO_OPTION)
				System.exit(0);
			else {
				// this.setVisible(false);
				winQuenMatKhau.setVisible(true);
			}
		}
	}

	public TaiKhoan getTaiKhoanDangNhapThanhCong() {
		return taiKhoan;
	}

	private void hoverIn(JButton button) {
		// System.out.println("in ok");
		button.setBackground(new Color(255, 255, 0));
		button.setForeground(new Color(128, 0, 0));
	}

	private void hoverOut(JButton button) {
		// System.out.println("in ok");
		button.setBackground(new Color(30, 144, 255));
		button.setForeground(new Color(255, 255, 0));
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
		Object o = e.getSource();
		if (o.equals(btnDangNhap)) {
			hoverIn(btnDangNhap);
		}
		if (o.equals(btnQuenMatKhau)) {
			hoverIn(btnQuenMatKhau);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnDangNhap)) {
			hoverOut(btnDangNhap);
		}
		if (o.equals(btnQuenMatKhau)) {
			hoverOut(btnQuenMatKhau);
		}
	}

}
