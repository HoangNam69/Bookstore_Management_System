package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import dao.ChiTietHoaDonDao;
import dao.SanPhamDao;
import entities.ChiTietHoaDon;
import entities.Sach;
import entities.SanPham;
import entities.VanPhongPham;

import java.awt.SystemColor;

public class WinXemChiTietHoaDon extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblNhanVienLapHoaDon;
	private JLabel lblTenKhachHang;
	private JLabel lblNgayLapHoaDon;
	private JLabel lblMaHoaDon;
	private JLabel lblTenKH;
	private JLabel lblSoTrang;
	private JLabel lblNewLabel_9;
	private JLabel lblTenSp;
	private JLabel lblDanhSachSanPham;

	private JButton btnThoat;
	private ChiTietHoaDonDao chiTietHoaDonDao;
	private List<ChiTietHoaDon> dsHoaDon;
	String maHoaDon;
	String tenNhanVien;
	String ngayLap;
	String tenKhachHang;
	String tienKhachDua;
	String ghiChu;
	String tongTienHoaDon;
	private JLabel lblTongTienKhachDua;
	private JLabel lblTongTienHoaDon;
	private JTextArea txaGhiChu;
	private JLabel lblTienKhachDua;
	JScrollPane sp_ChiTietHD;
	private JTable tbl_ChiTietHD;
	private DefaultTableModel tableModel_chiTietHoaDonDao;
	private SanPham sanPham;
	private SanPhamDao sanPham_dao;

	@SuppressWarnings("deprecation")
	public WinXemChiTietHoaDon(String maHoaDon, String tenNhanVien, String ngayLap, String tenKhachHang,
							   String tienKhachDua, String tongTienHoaDon, String ghiChu) {

		this.maHoaDon = maHoaDon;
		this.tenNhanVien = tenNhanVien;
		this.ngayLap = ngayLap;
		this.tenKhachHang = tenKhachHang;
		this.tienKhachDua = tienKhachDua;
		this.ghiChu = ghiChu;
		this.tongTienHoaDon = tongTienHoaDon;
		setTitle("Chi tiết hóa đơn");
		setResizable(false);
		setSize(1130, 700);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JLabel lblMaHD = new JLabel("Mã hóa đơn:");
		lblMaHD.setForeground(new Color(72, 61, 139));
		lblMaHD.setFont(new Font("Arial", Font.BOLD, 16));
		lblMaHD.setBounds(10, 94, 116, 38);
		getContentPane().add(lblMaHD);

		lblNhanVienLapHoaDon = new JLabel();
		lblNhanVienLapHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNhanVienLapHoaDon.setBackground(new Color(255, 255, 255));
		lblNhanVienLapHoaDon.setBounds(236, 162, 273, 38);
		getContentPane().add(lblNhanVienLapHoaDon);

		lblTenKhachHang = new JLabel();
		lblTenKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTenKhachHang.setBackground(Color.WHITE);
		lblTenKhachHang.setBounds(236, 210, 240, 38);
		getContentPane().add(lblTenKhachHang);

		lblNgayLapHoaDon = new JLabel();
		lblNgayLapHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNgayLapHoaDon.setBackground(Color.WHITE);
		lblNgayLapHoaDon.setBounds(236, 275, 240, 38);
		getContentPane().add(lblNgayLapHoaDon);

		JPanel panel = new JPanel();
		panel.setBounds(10, 38, 285, 46);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("CHI TIẾT HÓA ĐƠN");
		lblNewLabel.setForeground(SystemColor.textHighlight);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(201, 10, 766, 39);
		getContentPane().add(lblNewLabel);

		lblMaHoaDon = new JLabel(maHoaDon);
		lblMaHoaDon.setBorder(BorderFactory.createLineBorder(Color.cyan));
		lblMaHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMaHoaDon.setBounds(236, 94, 240, 38);
		getContentPane().add(lblMaHoaDon);

		lblTenKH = new JLabel("Tên khách hàng:");
		lblTenKH.setForeground(new Color(72, 61, 139));
		lblTenKH.setFont(new Font("Arial", Font.BOLD, 16));
		lblTenKH.setBounds(10, 210, 170, 38);
		getContentPane().add(lblTenKH);

		lblNgayLapHoaDon = new JLabel("Ngày lập hóa đơn:");
		lblNgayLapHoaDon.setForeground(new Color(72, 61, 139));
		lblNgayLapHoaDon.setFont(new Font("Arial", Font.BOLD, 16));
		lblNgayLapHoaDon.setBounds(10, 275, 196, 38);
		getContentPane().add(lblNgayLapHoaDon);

		lblSoTrang = new JLabel();
		lblSoTrang.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSoTrang.setBounds(125, 290, 170, 23);
		getContentPane().add(lblSoTrang);

		lblNewLabel_9 = new JLabel("Nhân viên lập hóa đơn:");
		lblNewLabel_9.setForeground(new Color(72, 61, 139));
		lblNewLabel_9.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_9.setBounds(10, 160, 196, 40);
		getContentPane().add(lblNewLabel_9);

		lblTenSp = new JLabel();
		lblTenSp.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTenSp.setBounds(297, 122, 328, 23);
		getContentPane().add(lblTenSp);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 599, 1096, 54);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		btnThoat = new JButton("Thoát");
		btnThoat.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnThoat.setBounds(405, 10, 132, 39);
		panel_1.add(btnThoat);

		txaGhiChu = new JTextArea();
		txaGhiChu.setFont(new Font("Courier New", Font.PLAIN, 13));
		txaGhiChu.setBounds(10, 480, 485, 109);
		txaGhiChu.setBorder(BorderFactory.createLineBorder(Color.black));
		getContentPane().add(txaGhiChu);

		JLabel lblNewLabel_6_1 = new JLabel("Ghi chú(Mô tả):");
		lblNewLabel_6_1.setForeground(new Color(72, 61, 139));
		lblNewLabel_6_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_6_1.setBounds(158, 447, 160, 23);
		getContentPane().add(lblNewLabel_6_1);

		lblTongTienKhachDua = new JLabel("Tổng tiền hóa đơn:");
		lblTongTienKhachDua.setForeground(new Color(72, 61, 139));
		lblTongTienKhachDua.setFont(new Font("Arial", Font.BOLD, 16));
		lblTongTienKhachDua.setBounds(10, 399, 184, 38);
		getContentPane().add(lblTongTienKhachDua);

		lblTongTienHoaDon = new JLabel();
		lblTongTienHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTongTienHoaDon.setBackground(Color.WHITE);
		lblTongTienHoaDon.setBounds(236, 399, 240, 33);
		getContentPane().add(lblTongTienHoaDon);

		lblDanhSachSanPham = new JLabel("Tiền khách đã đưa:");
		lblDanhSachSanPham.setForeground(new Color(72, 61, 139));
		lblDanhSachSanPham.setFont(new Font("Arial", Font.BOLD, 16));
		lblDanhSachSanPham.setBounds(10, 337, 196, 38);
		getContentPane().add(lblDanhSachSanPham);

		lblTienKhachDua = new JLabel();
		lblTienKhachDua.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTienKhachDua.setBackground(Color.WHITE);
		lblTienKhachDua.setBounds(236, 336, 240, 39);
		getContentPane().add(lblTienKhachDua);

		JPanel panel_right = new JPanel();
		panel_right.setBounds(519, 94, 587, 433);
		getContentPane().add(panel_right);
		setValue();

		panel_right.setLayout(null);
		String header_ChiTietHD[] = { "STT", "Mã sản phẩm", "Tên sản phẩm", "Giá tiền", "Số lượng" };
		tableModel_chiTietHoaDonDao = new DefaultTableModel(header_ChiTietHD, 0);
		tbl_ChiTietHD = new JTable(tableModel_chiTietHoaDonDao);
		sp_ChiTietHD = new JScrollPane(tbl_ChiTietHD, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp_ChiTietHD.setBounds(10, 45, 552, 367);
		tbl_ChiTietHD.setAutoCreateRowSorter(true);
		panel_right.add(sp_ChiTietHD);

		JLabel lblNewLabel_1 = new JLabel("DANH SÁCH SẢN PHẨM");
		lblNewLabel_1.setBounds(128, 2, 360, 33);
		panel_right.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		try {
			docDLVaoTableModel();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		btnThoat.addActionListener(this);
	}

	public void setValue() {

		lblMaHoaDon.setText(maHoaDon);
		lblNhanVienLapHoaDon.setText(tenNhanVien);
		lblTenKhachHang.setText(tenKhachHang);
		lblNgayLapHoaDon.setText(ngayLap);
		lblTienKhachDua.setText(tienKhachDua);
		lblTongTienHoaDon.setText(tongTienHoaDon);
	}

	private void docDLVaoTableModel() throws SQLException {
		chiTietHoaDonDao = new ChiTietHoaDonDao();
		dsHoaDon = chiTietHoaDonDao.getCTHoaDonTheoMaHoaDon(maHoaDon);
		int i = 1;

		for (ChiTietHoaDon chiTietHoaDon : dsHoaDon) {
			String masanPham = chiTietHoaDon.getSanPham().getMaSanPham();
			sanPham_dao = new SanPhamDao();
			sanPham = sanPham_dao.timSanPhamTheoMa1(masanPham);
			if (sanPham.getLoaiSanPham().equals("Sách")) {
				Sach s = sanPham_dao.getSachTheoMaSP(masanPham);
				tableModel_chiTietHoaDonDao.addRow(new Object[] { i++, s.getMaSanPham(), s.getTenSach(),
						chiTietHoaDon.getDonGia(), chiTietHoaDon.getSoLuong() });
			} else {
				VanPhongPham vvp = sanPham_dao.getVPPTheoMaSP(masanPham);
				tableModel_chiTietHoaDonDao.addRow(new Object[] { i++, vvp.getMaSanPham(), vvp.getTenVanPhongPham(),
						chiTietHoaDon.getDonGia(), chiTietHoaDon.getSoLuong() });
			}

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThoat)) {
			if (JOptionPane.showConfirmDialog(null, "Có chắc bạn muốn thoát", "Cảnh báo", JOptionPane.YES_NO_OPTION,
					JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
				this.setVisible(false);
		}
	}
}
