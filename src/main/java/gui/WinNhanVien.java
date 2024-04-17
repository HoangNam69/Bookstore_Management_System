package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entity.NhanVien;
import entity.TaiKhoan;
import service.impl.NhanVienServiceImpl;

import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;

public class WinNhanVien extends JFrame implements MouseListener {
	private boolean flag_TK = false;
	private boolean flag_HD = false;
	private JPanel pnlContainer;
	private JPanel pnlTrangChu;
	private JLabel lblIconHome;
	private JPanel pnlKhachHang;
	private JLabel lblIconKhachHang;
	private JPanel pnlHoaDon;
	private JLabel lblIconHoaDon;
	private JPanel pnlSanPham;
	private JLabel lblIconSanPham;
	private JPanel pnlThongKe;
	private JLabel lblIconThongKe;
	private JPanel pnlTroGiup;
	private JLabel lblIconTroGiup;
	private JPanel pnlTaiKhoan;
	private JPanel pnlDoiMatKhau;
	private JLabel lblDoiMatKhau;
	private JPanel pnlDangXuat;
	private JLabel lblDangXuat;
	private JPanel pnlMenuHoaDon;
	private JPanel pnlTaoHoaDonMoi;
	private JLabel lblHoaDonMoi;
	private JPanel pnlQuanLyHoaDon;
	private JLabel lblQuanLyHoaDon;
	private JLabel lblTenNV;
	private Pnl_QuanLyKhachHang pnl_QuanLyKhachHang;
	private Pnl_QuanLySanPham pnl_QuanLySanPham;
	private JPanel pnlTaoXuLyDoiTra;
	private JLabel lblXuLyDoiTra;
	private NhanVienServiceImpl nhanVienServiceImpl;
	private NhanVien nv;
	private Pnl_TrangChu pnl_TrangChu;
	private Pnl_ThongKeNhanVien pnl_ThongKeNhanVien;
	private Pnl_TaoHoaDon pnl_TaoHoaDon;
	private WinDoiMatKhau win_DoiMatKhau;
	private WinLogin frm_Login = new WinLogin();
	private Pnl_QuanLyHoaDon pnl_QuanLyHoaDon;
	// private static FrmNhanVien frame;
	private JPanel pnlMenuTaiKhoan;
	private Pnl_DoiTraSanPham pnl_DoiTraSanPham;
	private static WinNhanVien winNhanVien;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					frame = new FrmNhanVien();
//					frame.setVisible(true);
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
		return 1950;
	}

	public int GetMaxHeight() {
		return 1000;
	}

	public WinNhanVien() throws Exception {

		setSize(1536, 816);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("QUAN LY HIEU SACH ĐTD");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		pnlContainer = new JPanel();
		pnlContainer.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnlContainer);
		pnlContainer.setLayout(null);

		// home
		pnlTrangChu = new JPanel();
		pnlTrangChu.setBounds(0, 0, GetMaxWidth() / 7, GetMaxHeight() / 20);
		pnlTrangChu.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		pnlContainer.add(pnlTrangChu);
		pnlTrangChu.setLayout(null);

		lblIconHome = new JLabel("Trang chủ");
		lblIconHome.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconHome.setIcon(new ImageIcon(WinQuanLy.class.getResource("/gui/icon/house.png")));
		lblIconHome.setBounds(0, 0, GetMaxWidth() / 7, GetMaxHeight() / 20);
		pnlTrangChu.add(lblIconHome);
		// khachhang
		pnlKhachHang = new JPanel();
		pnlKhachHang.setLayout(null);
		pnlKhachHang.setBounds(GetMaxWidth() / 7, 0, GetMaxWidth() / 7, GetMaxHeight() / 20);
		pnlKhachHang.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		pnlContainer.add(pnlKhachHang);

		lblIconKhachHang = new JLabel("Quản lý khách hàng");
		lblIconKhachHang.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconKhachHang.setIcon(new ImageIcon(WinQuanLy.class.getResource("/gui/icon/rating.png")));
		lblIconKhachHang.setBounds(0, 0, GetMaxWidth() / 7, GetMaxHeight() / 20);
		pnlKhachHang.add(lblIconKhachHang);
		// hoadon
		pnlHoaDon = new JPanel();
		pnlHoaDon.setLayout(null);
		pnlHoaDon.setBounds(GetMaxWidth() * 2 / 7, 0, GetMaxWidth() / 7, GetMaxHeight() / 20);
		pnlHoaDon.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		pnlContainer.add(pnlHoaDon);

		lblIconHoaDon = new JLabel("Quản lý hóa đơn");
		lblIconHoaDon.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconHoaDon.setIcon(new ImageIcon(WinQuanLy.class.getResource("/gui/icon/package.png")));
		lblIconHoaDon.setBounds(0, 0, GetMaxWidth() / 7, GetMaxHeight() / 20);
		pnlHoaDon.add(lblIconHoaDon);
		// sanpham
		pnlSanPham = new JPanel();
		pnlSanPham.setLayout(null);
		pnlSanPham.setBounds(GetMaxWidth() * 3 / 7, 0, GetMaxWidth() / 7, GetMaxHeight() / 20);
		pnlSanPham.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		pnlContainer.add(pnlSanPham);

		lblIconSanPham = new JLabel("Quản lý sản phẩm");
		lblIconSanPham.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconSanPham.setIcon(new ImageIcon(WinQuanLy.class.getResource("/gui/icon/product.png")));
		lblIconSanPham.setBounds(0, 0, GetMaxWidth() / 7, GetMaxHeight() / 20);
		pnlSanPham.add(lblIconSanPham);
		// thongke
		pnlThongKe = new JPanel();
		pnlThongKe.setLayout(null);
		pnlThongKe.setBounds(GetMaxWidth() * 4 / 7, 0, GetMaxWidth() / 7, GetMaxHeight() / 20);
		pnlThongKe.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		pnlContainer.add(pnlThongKe);

		lblIconThongKe = new JLabel("Thống kê");
		lblIconThongKe.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconThongKe.setIcon(new ImageIcon(WinQuanLy.class.getResource("/gui/icon/pie-chart.png")));
		lblIconThongKe.setBounds(0, 0, GetMaxWidth() / 7, GetMaxHeight() / 20);
		pnlThongKe.add(lblIconThongKe);

		// trogiup
		pnlTroGiup = new JPanel();
		pnlTroGiup.setLayout(null);
		pnlTroGiup.setBounds(GetMaxWidth() * 5 / 7, 0, GetMaxWidth() / 7, GetMaxHeight() / 20);
		pnlTroGiup.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		pnlContainer.add(pnlTroGiup);

		lblIconTroGiup = new JLabel("Trợ giúp");
		lblIconTroGiup.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconTroGiup.setIcon(new ImageIcon(WinQuanLy.class.getResource("/gui/icon/help-desk.png")));
		lblIconTroGiup.setBounds(0, 0, GetMaxWidth() / 7, GetMaxHeight() / 20);
		pnlTroGiup.add(lblIconTroGiup);

		// taikhoan
		pnlTaiKhoan = new JPanel();
		pnlTaiKhoan.setLayout(null);
		pnlTaiKhoan.setBounds(GetMaxWidth() * 6 / 7, 0, GetMaxWidth() / 7, GetMaxHeight() / 20);
		pnlTaiKhoan.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		pnlContainer.add(pnlTaiKhoan);

		lblTenNV = new JLabel("Ten  NV");
		lblTenNV.setIcon(new ImageIcon(WinNhanVien.class.getResource("/gui/icon/user.png")));
		lblTenNV.setBounds(0, 0, 193, 40);
		pnlTaiKhoan.add(lblTenNV);

		// menu taikhoan
		pnlMenuTaiKhoan = new JPanel();
		pnlMenuTaiKhoan.setBounds(GetMaxWidth() * 6 / 7, GetMaxHeight() / 20, GetMaxWidth() / 7, GetMaxHeight() / 16);
		pnlContainer.add(pnlMenuTaiKhoan);
		pnlMenuTaiKhoan.setLayout(null);

		pnlDoiMatKhau = new JPanel();
		pnlDoiMatKhau.setBounds(0, 0, GetMaxWidth() / 7, GetMaxHeight() / 32);
		pnlMenuTaiKhoan.add(pnlDoiMatKhau);
		pnlDoiMatKhau.setLayout(null);
		pnlDoiMatKhau.setBorder(BorderFactory.createLineBorder(Color.black, 1));

		lblDoiMatKhau = new JLabel("Đổi mật khẩu");
		lblDoiMatKhau.setBounds(0, 0, GetMaxWidth() / 7, GetMaxHeight() / 32);
		pnlDoiMatKhau.add(lblDoiMatKhau);
		lblDoiMatKhau.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDoiMatKhau.setHorizontalAlignment(SwingConstants.CENTER);

		pnlDangXuat = new JPanel();
		pnlDangXuat.setBounds(0, GetMaxHeight() / 32, GetMaxWidth() / 7, GetMaxHeight() / 32);
		pnlMenuTaiKhoan.add(pnlDangXuat);
		pnlDangXuat.setLayout(null);
		pnlDangXuat.setBorder(BorderFactory.createLineBorder(Color.black, 1));

		lblDangXuat = new JLabel("Đăng xuất");
		lblDangXuat.setBounds(0, 0, GetMaxWidth() / 7, GetMaxHeight() / 32);
		pnlDangXuat.add(lblDangXuat);
		lblDangXuat.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDangXuat.setHorizontalAlignment(SwingConstants.CENTER);

		// menu hoadon
		pnlMenuHoaDon = new JPanel();
		pnlMenuHoaDon.setLayout(null);
		pnlMenuHoaDon.setBounds(557, 45, 278, 90);
		pnlContainer.add(pnlMenuHoaDon);

		pnlTaoXuLyDoiTra = new JPanel();
		pnlTaoXuLyDoiTra.setLayout(null);
		pnlTaoXuLyDoiTra.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		pnlTaoXuLyDoiTra.setBounds(0, 61, 278, 30);
		pnlMenuHoaDon.add(pnlTaoXuLyDoiTra);

		lblXuLyDoiTra = new JLabel("Đổi trả sách");
		lblXuLyDoiTra.setHorizontalAlignment(SwingConstants.CENTER);
		lblXuLyDoiTra.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblXuLyDoiTra.setBounds(0, 0, 270, 25);
		pnlTaoXuLyDoiTra.add(lblXuLyDoiTra);
		pnlTaoHoaDonMoi = new JPanel();
		pnlTaoHoaDonMoi.setBounds(0, 0, GetMaxWidth() / 7, GetMaxHeight() / 32);
		pnlMenuHoaDon.add(pnlTaoHoaDonMoi);
		pnlTaoHoaDonMoi.setLayout(null);
		pnlTaoHoaDonMoi.setBorder(BorderFactory.createLineBorder(Color.black, 1));

		lblHoaDonMoi = new JLabel("Tạo hóa đơn mới");
		lblHoaDonMoi.setBounds(0, 0, GetMaxWidth() / 7, GetMaxHeight() / 32);
		pnlTaoHoaDonMoi.add(lblHoaDonMoi);
		lblHoaDonMoi.setHorizontalAlignment(SwingConstants.CENTER);
		lblHoaDonMoi.setFont(new Font("Tahoma", Font.BOLD, 11));

		pnlQuanLyHoaDon = new JPanel();
		pnlQuanLyHoaDon.setBounds(0, GetMaxHeight() / 32, GetMaxWidth() / 7, GetMaxHeight() / 32);
		pnlMenuHoaDon.add(pnlQuanLyHoaDon);
		pnlQuanLyHoaDon.setLayout(null);
		pnlQuanLyHoaDon.setBorder(BorderFactory.createLineBorder(Color.black, 1));

		lblQuanLyHoaDon = new JLabel("Quản lí hóa đơn");
		lblQuanLyHoaDon.setBounds(0, 0, GetMaxWidth() / 7, GetMaxHeight() / 32);
		pnlQuanLyHoaDon.add(lblQuanLyHoaDon);
		lblQuanLyHoaDon.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuanLyHoaDon.setFont(new Font("Tahoma", Font.BOLD, 11));

		pnl_ThongKeNhanVien = new Pnl_ThongKeNhanVien();
		pnl_ThongKeNhanVien.setBounds(10, 102, 1850, 900);
		getContentPane().add(pnl_ThongKeNhanVien);
		pnl_ThongKeNhanVien.setVisible(false);

		pnl_QuanLyKhachHang = new Pnl_QuanLyKhachHang();
		pnl_QuanLyKhachHang.setBounds(10, 102, 2000, 1000);
		getContentPane().add(pnl_QuanLyKhachHang);
		pnl_QuanLyKhachHang.setVisible(false);

		pnl_QuanLySanPham = new Pnl_QuanLySanPham();
		pnl_QuanLySanPham.setBounds(0, 102, 1900, 900);
		getContentPane().add(pnl_QuanLySanPham);
		pnl_QuanLySanPham.setVisible(false);

		pnl_TrangChu = new Pnl_TrangChu();
		pnl_TrangChu.setBounds(0, 102, 2000, 1000);
		getContentPane().add(pnl_TrangChu);
		pnl_TrangChu.setVisible(true);

		pnl_TaoHoaDon = new Pnl_TaoHoaDon();
		pnl_TaoHoaDon.setBounds(0, 102, 1900, 950);
		getContentPane().add(pnl_TaoHoaDon);
		pnl_TaoHoaDon.setVisible(false);

		pnl_QuanLyHoaDon = new Pnl_QuanLyHoaDon();
		pnl_QuanLyHoaDon.setBounds(60, 102, 1800, 900);
		getContentPane().add(pnl_QuanLyHoaDon);
		pnl_QuanLyHoaDon.setVisible(false);

		pnl_DoiTraSanPham = new Pnl_DoiTraSanPham();
		pnl_DoiTraSanPham.setBounds(60, 102, 1800, 900);
		getContentPane().add(pnl_DoiTraSanPham);
		pnl_DoiTraSanPham.setVisible(false);

		pnl_DoiTraSanPham = new Pnl_DoiTraSanPham();
		pnl_DoiTraSanPham.setBounds(60, 102, 1800, 900);
		getContentPane().add(pnl_DoiTraSanPham);
		pnl_DoiTraSanPham.setVisible(false);

		win_DoiMatKhau = new WinDoiMatKhau();
		win_DoiMatKhau.setVisible(false);

		// hienthiten
		WinLogin dangNhap = new WinLogin();
		TaiKhoan taiKhoan = dangNhap.getTaiKhoanDangNhapThanhCong();
		nhanVienServiceImpl = new NhanVienServiceImpl();
		nv = new NhanVien();
		nv = nhanVienServiceImpl.timNhanVienTheoMa(taiKhoan.getNhanVien().getMaNhanVien());
		lblTenNV.setBackground(Color.CYAN);
		lblTenNV.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTenNV.setHorizontalAlignment(SwingConstants.CENTER);
		lblTenNV.setText(nv.getHoTenNhanVien());
		pnlMenuHoaDon.setVisible(false);
		pnlMenuTaiKhoan.setVisible(false);

		pnlDangXuat.addMouseListener(this);
		pnlDoiMatKhau.addMouseListener(this);
		pnlTaoHoaDonMoi.addMouseListener(this);
		pnlTroGiup.addMouseListener(this);

		pnlThongKe.addMouseListener(this);
		pnlSanPham.addMouseListener(this);
		pnlKhachHang.addMouseListener(this);
		pnlTrangChu.addMouseListener(this);
		pnlTaoXuLyDoiTra.addMouseListener(this);
		pnlQuanLyHoaDon.addMouseListener(this);
		pnlContainer.addMouseListener(this);

		pnlTaiKhoan.addMouseListener(this);
		pnlHoaDon.addMouseListener(this);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

		Object o = e.getSource();
		if (o.equals(pnlDangXuat)) {
			this.setVisible(false);
			frm_Login.setVisible(true);
		}
		if (o.equals(pnlDoiMatKhau)) {
			flag_HD = false;
			flag_TK = false;
			pnlMenuTaiKhoan.setVisible(false);
			pnlMenuHoaDon.setVisible(false);
			pnlKhachHang.setBackground(Color.getColor("#f0f0f0"));
			pnlThongKe.setBackground(Color.getColor("#f0f0f0"));
			pnlTaiKhoan.setBackground(Color.getColor("#f0f0f0"));

			pnlHoaDon.setBackground(Color.getColor("#f0f0f0"));
			pnlTrangChu.setBackground(Color.getColor("#f0f0f0"));
			pnlSanPham.setBackground(Color.getColor("#f0f0f0"));
			pnlTroGiup.setBackground(Color.getColor("#f0f0f0"));
			win_DoiMatKhau.setVisible(true);
		}
		if (o.equals(pnlTaoHoaDonMoi)) {
			flag_HD = false;
			flag_TK = false;
			pnlMenuHoaDon.setVisible(false);
			pnlMenuTaiKhoan.setVisible(false);
			pnlKhachHang.setBackground(Color.getColor("#f0f0f0"));
			pnlThongKe.setBackground(Color.getColor("#f0f0f0"));
			pnlTaiKhoan.setBackground(Color.getColor("#f0f0f0"));

			pnlHoaDon.setBackground(Color.getColor("#f0f0f0"));
			pnlTrangChu.setBackground(Color.getColor("#f0f0f0"));
			pnlSanPham.setBackground(Color.getColor("#f0f0f0"));
			pnlTroGiup.setBackground(Color.getColor("#f0f0f0"));

			pnl_ThongKeNhanVien.setVisible(false);
			pnl_QuanLyKhachHang.setVisible(false);
			pnl_QuanLySanPham.setVisible(false);
			pnl_DoiTraSanPham.setVisible(false);
			pnl_QuanLyHoaDon.setVisible(false);
			pnl_TrangChu.setVisible(false);
			pnl_TaoHoaDon.setVisible(true);
		}
		if (o.equals(pnlTroGiup)) {
			flag_HD = false;
			flag_TK = false;
			pnlMenuHoaDon.setVisible(false);
			pnlMenuTaiKhoan.setVisible(false);
			pnlKhachHang.setBackground(Color.getColor("#f0f0f0"));
			pnlThongKe.setBackground(Color.getColor("#f0f0f0"));
			pnlTaiKhoan.setBackground(Color.getColor("#f0f0f0"));

			pnlHoaDon.setBackground(Color.getColor("#f0f0f0"));
			pnlTrangChu.setBackground(Color.getColor("#f0f0f0"));
			pnlSanPham.setBackground(Color.getColor("#f0f0f0"));
			pnlTroGiup.setBackground(Color.LIGHT_GRAY);
		}

		if (o.equals(pnlThongKe)) {
			flag_HD = false;
			flag_TK = false;
			pnlMenuHoaDon.setVisible(false);
			pnlMenuTaiKhoan.setVisible(false);
			pnlKhachHang.setBackground(Color.getColor("#f0f0f0"));
			pnlTroGiup.setBackground(Color.getColor("#f0f0f0"));

			pnlTaiKhoan.setBackground(Color.getColor("#f0f0f0"));
			pnlHoaDon.setBackground(Color.getColor("#f0f0f0"));
			pnlTrangChu.setBackground(Color.getColor("#f0f0f0"));
			pnlSanPham.setBackground(Color.getColor("#f0f0f0"));
			pnlThongKe.setBackground(Color.LIGHT_GRAY);

			pnl_ThongKeNhanVien.setVisible(false);
			pnl_QuanLyKhachHang.setVisible(false);
			pnl_QuanLySanPham.setVisible(false);
			pnl_DoiTraSanPham.setVisible(false);
			pnl_QuanLyHoaDon.setVisible(false);

			pnl_TrangChu.setVisible(false);
			pnl_TaoHoaDon.setVisible(false);
		}
		if (o.equals(pnlSanPham)) {
			flag_HD = false;
			flag_TK = false;
			pnlMenuHoaDon.setVisible(false);
			pnlMenuTaiKhoan.setVisible(false);
			pnlKhachHang.setBackground(Color.getColor("#f0f0f0"));
			pnlThongKe.setBackground(Color.getColor("#f0f0f0"));

			pnlTroGiup.setBackground(Color.getColor("#f0f0f0"));
			pnlTaiKhoan.setBackground(Color.getColor("#f0f0f0"));
			pnlHoaDon.setBackground(Color.getColor("#f0f0f0"));
			pnlTrangChu.setBackground(Color.getColor("#f0f0f0"));
			pnlSanPham.setBackground(Color.LIGHT_GRAY);
			pnl_ThongKeNhanVien.setVisible(false);
			pnl_QuanLyKhachHang.setVisible(false);
			pnl_QuanLySanPham.setVisible(true);
			pnl_DoiTraSanPham.setVisible(false);
			pnl_QuanLyHoaDon.setVisible(false);
			pnl_TrangChu.setVisible(false);
			pnl_TaoHoaDon.setVisible(false);
		}
		if (o.equals(pnlKhachHang)) {
			flag_HD = false;
			flag_TK = false;
			pnlMenuHoaDon.setVisible(false);
			pnlMenuTaiKhoan.setVisible(false);
			pnlThongKe.setBackground(Color.getColor("#f0f0f0"));
			pnlTroGiup.setBackground(Color.getColor("#f0f0f0"));

			pnlTaiKhoan.setBackground(Color.getColor("#f0f0f0"));
			pnlHoaDon.setBackground(Color.getColor("#f0f0f0"));
			pnlTrangChu.setBackground(Color.getColor("#f0f0f0"));
			pnlSanPham.setBackground(Color.getColor("#f0f0f0"));
			pnlKhachHang.setBackground(Color.LIGHT_GRAY);

			pnl_ThongKeNhanVien.setVisible(false);
			pnl_QuanLyKhachHang.setVisible(true);
			pnl_QuanLySanPham.setVisible(false);
			pnl_DoiTraSanPham.setVisible(false);
			pnl_TrangChu.setVisible(false);
		}
		if (o.equals(pnlTrangChu)) {
			flag_HD = false;
			flag_TK = false;
			pnlMenuHoaDon.setVisible(false);
			pnlMenuTaiKhoan.setVisible(false);
			pnlKhachHang.setBackground(Color.getColor("#f0f0f0"));

			pnlThongKe.setBackground(Color.getColor("#f0f0f0"));
			pnlTroGiup.setBackground(Color.getColor("#f0f0f0"));
			pnlTaiKhoan.setBackground(Color.getColor("#f0f0f0"));
			pnlHoaDon.setBackground(Color.getColor("#f0f0f0"));
			pnlSanPham.setBackground(Color.getColor("#f0f0f0"));
			pnlTrangChu.setBackground(Color.LIGHT_GRAY);

			pnl_ThongKeNhanVien.setVisible(false);
			pnl_QuanLyKhachHang.setVisible(false);
			pnl_QuanLySanPham.setVisible(false);
			pnl_DoiTraSanPham.setVisible(false);
			pnl_TrangChu.setVisible(true);
			pnl_TaoHoaDon.setVisible(false);
		}
		if (o.equals(pnlContainer)) {
			flag_HD = false;
			flag_TK = false;
			pnlMenuHoaDon.setVisible(false);
			pnlMenuTaiKhoan.setVisible(false);

			pnlTaiKhoan.setBackground(Color.getColor("#f0f0f0"));
			pnlHoaDon.setBackground(Color.getColor("#f0f0f0"));
			pnlKhachHang.setBackground(Color.getColor("#f0f0f0"));
			pnlThongKe.setBackground(Color.getColor("#f0f0f0"));

			pnlTroGiup.setBackground(Color.getColor("#f0f0f0"));
			pnlHoaDon.setBackground(Color.getColor("#f0f0f0"));
			pnlSanPham.setBackground(Color.getColor("#f0f0f0"));
			pnlTrangChu.setBackground(Color.getColor("#f0f0f0"));
		}
		if (o.equals(pnlTaiKhoan)) {
			pnlMenuTaiKhoan.setVisible(true);
			pnlMenuHoaDon.setVisible(false);

			if (flag_TK == false) {
				flag_TK = true;
				pnlMenuTaiKhoan.setVisible(true);
				pnlTaiKhoan.setBackground(Color.LIGHT_GRAY);
				pnlHoaDon.setBackground(Color.getColor("#f0f0f0"));
				pnlKhachHang.setBackground(Color.getColor("#f0f0f0"));
				pnlThongKe.setBackground(Color.getColor("#f0f0f0"));
				pnlTroGiup.setBackground(Color.getColor("#f0f0f0"));

				pnlHoaDon.setBackground(Color.getColor("#f0f0f0"));
				pnlSanPham.setBackground(Color.getColor("#f0f0f0"));
				pnlTrangChu.setBackground(Color.getColor("#f0f0f0"));

			} else {
				flag_TK = false;
				pnlMenuTaiKhoan.setVisible(false);
				pnlTaiKhoan.setBackground(Color.getColor("#f0f0f0"));
			}
		}
		if (o.equals(pnlHoaDon)) {
			pnlMenuHoaDon.setVisible(true);
			pnlMenuTaiKhoan.setVisible(false);
			if (flag_HD == false) {
				flag_HD = true;
				pnlMenuHoaDon.setVisible(true);
				pnlHoaDon.setBackground(Color.LIGHT_GRAY);
				pnlTaiKhoan.setBackground(Color.getColor("#f0f0f0"));
				pnlKhachHang.setBackground(Color.getColor("#f0f0f0"));
				pnlThongKe.setBackground(Color.getColor("#f0f0f0"));

				pnlTroGiup.setBackground(Color.getColor("#f0f0f0"));
				pnlSanPham.setBackground(Color.getColor("#f0f0f0"));
				pnlTrangChu.setBackground(Color.getColor("#f0f0f0"));

			} else {
				flag_HD = false;
				pnlMenuHoaDon.setVisible(false);
				pnlHoaDon.setBackground(Color.getColor("#f0f0f0"));
			}
		}
		if (o.equals(pnlQuanLyHoaDon)) {
			flag_HD = false;
			flag_TK = false;
			pnlMenuHoaDon.setVisible(false);
			pnlMenuTaiKhoan.setVisible(false);
			pnlKhachHang.setBackground(Color.getColor("#f0f0f0"));

			pnlThongKe.setBackground(Color.getColor("#f0f0f0"));
			pnlTroGiup.setBackground(Color.getColor("#f0f0f0"));
			pnlTaiKhoan.setBackground(Color.getColor("#f0f0f0"));
			pnlHoaDon.setBackground(Color.getColor("#f0f0f0"));
			pnlSanPham.setBackground(Color.getColor("#f0f0f0"));
			pnlTrangChu.setBackground(Color.LIGHT_GRAY);

			pnl_ThongKeNhanVien.setVisible(false);
			pnl_QuanLyKhachHang.setVisible(false);
			pnl_QuanLySanPham.setVisible(false);
			pnl_TaoHoaDon.setVisible(false);
			pnl_DoiTraSanPham.setVisible(false);
			pnl_DoiTraSanPham.setVisible(false);
			pnl_QuanLyHoaDon.setVisible(true);
		}
		if (o.equals(pnlTaoXuLyDoiTra)) {
			flag_HD = false;
			flag_TK = false;
			pnlMenuHoaDon.setVisible(false);
			pnlMenuTaiKhoan.setVisible(false);
			pnlKhachHang.setBackground(Color.getColor("#f0f0f0"));
			pnlThongKe.setBackground(Color.getColor("#f0f0f0"));
			pnlTaiKhoan.setBackground(Color.getColor("#f0f0f0"));

			pnlHoaDon.setBackground(Color.getColor("#f0f0f0"));
			pnlTrangChu.setBackground(Color.getColor("#f0f0f0"));
			pnlSanPham.setBackground(Color.getColor("#f0f0f0"));
			pnlTroGiup.setBackground(Color.getColor("#f0f0f0"));

			pnl_ThongKeNhanVien.setVisible(false);
			pnl_QuanLyKhachHang.setVisible(false);
			pnl_QuanLySanPham.setVisible(false);
			pnl_QuanLyHoaDon.setVisible(false);
			pnl_TrangChu.setVisible(false);
			pnl_TaoHoaDon.setVisible(false);
			pnl_DoiTraSanPham.setVisible(true);
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(pnlTrangChu)) {
			hoverIn(pnlTrangChu, lblIconHome);
		}
		if (o.equals(pnlKhachHang)) {
			hoverIn(pnlKhachHang, lblIconKhachHang);
		}
		if (o.equals(pnlHoaDon)) {
			hoverIn(pnlHoaDon, lblIconHoaDon);
		}
		if (o.equals(pnlSanPham)) {
			hoverIn(pnlSanPham, lblIconSanPham);
		}
		if (o.equals(pnlThongKe)) {
			hoverIn(pnlThongKe, lblIconThongKe);
		}
		if (o.equals(pnlTroGiup)) {
			hoverIn(pnlTroGiup, lblIconTroGiup);
		}
		if (o.equals(pnlTaiKhoan)) {
			hoverIn(pnlTaiKhoan, lblTenNV);
		}
		if (o.equals(pnlQuanLyHoaDon)) {
			hoverIn(pnlQuanLyHoaDon, lblQuanLyHoaDon);
		}
		if (o.equals(pnlTaoHoaDonMoi)) {
			hoverIn(pnlTaoHoaDonMoi, lblIconHoaDon);
		}
		if (o.equals(pnlDoiMatKhau)) {
			hoverIn(pnlDoiMatKhau, lblDoiMatKhau);
		}
		if (o.equals(pnlDangXuat)) {
			hoverIn(pnlDangXuat, lblDangXuat);
		}
		if (o.equals(pnlQuanLyHoaDon)) {
			hoverIn(pnlQuanLyHoaDon, lblQuanLyHoaDon);
		}
		if (o.equals(pnlTaoXuLyDoiTra)) {
			hoverIn(pnlTaoXuLyDoiTra, lblXuLyDoiTra);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(pnlTrangChu)) {
			hoverOut(pnlTrangChu, lblIconHome);
		}
		if (o.equals(pnlKhachHang)) {
			hoverOut(pnlKhachHang, lblIconKhachHang);
		}
		if (o.equals(pnlHoaDon)) {
			hoverOut(pnlHoaDon, lblIconHoaDon);
		}
		if (o.equals(pnlSanPham)) {
			hoverOut(pnlSanPham, lblIconSanPham);
		}
		if (o.equals(pnlThongKe)) {
			hoverOut(pnlThongKe, lblIconThongKe);
		}
		if (o.equals(pnlTroGiup)) {
			hoverOut(pnlTroGiup, lblIconTroGiup);
		}
		if (o.equals(pnlTaiKhoan)) {
			hoverOut(pnlTaiKhoan, lblTenNV);
		}
		if (o.equals(pnlQuanLyHoaDon)) {
			hoverOut(pnlQuanLyHoaDon, lblQuanLyHoaDon);
		}
		if (o.equals(pnlTaoHoaDonMoi)) {
			hoverOut(pnlTaoHoaDonMoi, lblIconHoaDon);
		}
		if (o.equals(pnlDoiMatKhau)) {
			hoverOut(pnlDoiMatKhau, lblDoiMatKhau);
		}
		if (o.equals(pnlDangXuat)) {
			hoverOut(pnlDangXuat, lblDangXuat);
		}
		if (o.equals(pnlQuanLyHoaDon)) {
			hoverOut(pnlQuanLyHoaDon, lblQuanLyHoaDon);
		}
		if (o.equals(pnlTaoXuLyDoiTra)) {
			hoverOut(pnlTaoXuLyDoiTra, lblXuLyDoiTra);
		}
	}

	private void hoverIn(JPanel pn_Container, JLabel lbl_Title) {
		pn_Container.setBackground(new Color(0, 206, 209));
		lbl_Title.setForeground(new Color(128, 0, 0));
	}

	private void hoverOut(JPanel pn_Container, JLabel lbl_Title) {
		pn_Container.setBackground(new Color(240, 240, 240));
		lbl_Title.setForeground(new Color(0, 0, 0));
	}
}
