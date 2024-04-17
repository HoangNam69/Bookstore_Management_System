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
import entity.ChiTietHoaDon;
import entity.Sach;
import entity.SanPham;
import entity.VanPhongPham;

import java.awt.SystemColor;

public class WinXemChiTietHoaDon extends JFrame implements ActionListener {
	// Data
	private String maHoaDon;
	private String tenNhanVien;
	private String ngayLap;
	private String tenKhachHang;
	private String tienKhachDua;
	private String ghiChu;
	private String tongTienHoaDon;

	// Entities
	private SanPham sanPham;
	private List<ChiTietHoaDon> dsHoaDon;

	// DAOs
	private SanPhamDao sanPham_dao;
	private ChiTietHoaDonDao chiTietHoaDonDao;

	// UI Components: Labels
	private JLabel lblMaHoaDon;
	private JLabel lblNhanVienLap;
	private JLabel lblTenKhachHang;
	private JLabel lblNgayLapHoaDon;
	private JLabel lblTieuDeHoaDon;
	private JLabel lblSoTrang;
	private JLabel lblNhanVienLapHoaDon;
	private JLabel lblTenSanPham;
	private JLabel lblGhiChu;
	private JLabel lblTongTienHoaDon;
	private JLabel lblGiaTriTongTienHoaDon;
	private JLabel lblTienKhachDua;
	private JLabel lblGiaTriTienKhachDua;

	// UI Components: Panels
	private JPanel pnlChiTietHoaDon;
	private JPanel pnlThongTin;

	// UI Components: Button
	private JButton btnThoat;

	// UI Components: TextArea
	private JTextArea txaGhiChu;

	// UI Components: Table
	private JTable tbl_ChiTietHD;

	// UI Components: ScrollPane
	private JScrollPane scrChiTietHD;

	// UI Components: Table Model
	private DefaultTableModel modelChiTietHoaDon;

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

		lblMaHoaDon = new JLabel("Mã hóa đơn:");
		lblMaHoaDon.setForeground(new Color(72, 61, 139));
		lblMaHoaDon.setFont(new Font("Arial", Font.BOLD, 16));
		lblMaHoaDon.setBounds(10, 94, 116, 38);
		getContentPane().add(lblMaHoaDon);

		lblNhanVienLap = new JLabel();
		lblNhanVienLap.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNhanVienLap.setBackground(new Color(255, 255, 255));
		lblNhanVienLap.setBounds(236, 162, 273, 38);
		getContentPane().add(lblNhanVienLap);

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

		pnlChiTietHoaDon = new JPanel();
		pnlChiTietHoaDon.setBounds(10, 38, 285, 46);
		getContentPane().add(pnlChiTietHoaDon);
		pnlChiTietHoaDon.setLayout(null);

		lblTieuDeHoaDon = new JLabel("CHI TIẾT HÓA ĐƠN");
		lblTieuDeHoaDon.setForeground(SystemColor.textHighlight);
		lblTieuDeHoaDon.setVerticalAlignment(SwingConstants.TOP);
		lblTieuDeHoaDon.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDeHoaDon.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblTieuDeHoaDon.setBounds(201, 10, 766, 39);
		getContentPane().add(lblTieuDeHoaDon);

		lblMaHoaDon = new JLabel(maHoaDon);
		lblMaHoaDon.setBorder(BorderFactory.createLineBorder(Color.cyan));
		lblMaHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMaHoaDon.setBounds(236, 94, 240, 38);
		getContentPane().add(lblMaHoaDon);

		lblTenKhachHang = new JLabel("Tên khách hàng:");
		lblTenKhachHang.setForeground(new Color(72, 61, 139));
		lblTenKhachHang.setFont(new Font("Arial", Font.BOLD, 16));
		lblTenKhachHang.setBounds(10, 210, 170, 38);
		getContentPane().add(lblTenKhachHang);

		lblNgayLapHoaDon = new JLabel("Ngày lập hóa đơn:");
		lblNgayLapHoaDon.setForeground(new Color(72, 61, 139));
		lblNgayLapHoaDon.setFont(new Font("Arial", Font.BOLD, 16));
		lblNgayLapHoaDon.setBounds(10, 275, 196, 38);
		getContentPane().add(lblNgayLapHoaDon);

		lblSoTrang = new JLabel();
		lblSoTrang.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSoTrang.setBounds(125, 290, 170, 23);
		getContentPane().add(lblSoTrang);

		lblNhanVienLapHoaDon = new JLabel("Nhân viên lập hóa đơn:");
		lblNhanVienLapHoaDon.setForeground(new Color(72, 61, 139));
		lblNhanVienLapHoaDon.setFont(new Font("Arial", Font.BOLD, 16));
		lblNhanVienLapHoaDon.setBounds(10, 160, 196, 40);
		getContentPane().add(lblNhanVienLapHoaDon);

		lblTenSanPham = new JLabel();
		lblTenSanPham.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTenSanPham.setBounds(297, 122, 328, 23);
		getContentPane().add(lblTenSanPham);

		pnlThongTin = new JPanel();
		pnlThongTin.setBounds(10, 599, 1096, 54);
		getContentPane().add(pnlThongTin);
		pnlThongTin.setLayout(null);

		btnThoat = new JButton("Thoát");
		btnThoat.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnThoat.setBounds(405, 10, 132, 39);
		pnlThongTin.add(btnThoat);

		txaGhiChu = new JTextArea();
		txaGhiChu.setFont(new Font("Courier New", Font.PLAIN, 13));
		txaGhiChu.setBounds(10, 480, 485, 109);
		txaGhiChu.setBorder(BorderFactory.createLineBorder(Color.black));
		getContentPane().add(txaGhiChu);

		lblGhiChu = new JLabel("Ghi chú(Mô tả):");
		lblGhiChu.setForeground(new Color(72, 61, 139));
		lblGhiChu.setFont(new Font("Arial", Font.BOLD, 16));
		lblGhiChu.setBounds(158, 447, 160, 23);
		getContentPane().add(lblGhiChu);

		lblTongTienHoaDon = new JLabel("Tổng tiền hóa đơn:");
		lblTongTienHoaDon.setForeground(new Color(72, 61, 139));
		lblTongTienHoaDon.setFont(new Font("Arial", Font.BOLD, 16));
		lblTongTienHoaDon.setBounds(10, 399, 184, 38);
		getContentPane().add(lblTongTienHoaDon);

		lblGiaTriTongTienHoaDon = new JLabel();
		lblGiaTriTongTienHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGiaTriTongTienHoaDon.setBackground(Color.WHITE);
		lblGiaTriTongTienHoaDon.setBounds(236, 399, 240, 33);
		getContentPane().add(lblGiaTriTongTienHoaDon);

		lblTienKhachDua = new JLabel("Tiền khách đã đưa:");
		lblTienKhachDua.setForeground(new Color(72, 61, 139));
		lblTienKhachDua.setFont(new Font("Arial", Font.BOLD, 16));
		lblTienKhachDua.setBounds(10, 337, 196, 38);
		getContentPane().add(lblTienKhachDua);

		lblGiaTriTienKhachDua = new JLabel();
		lblGiaTriTienKhachDua.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGiaTriTienKhachDua.setBackground(Color.WHITE);
		lblGiaTriTienKhachDua.setBounds(236, 336, 240, 39);
		getContentPane().add(lblGiaTriTienKhachDua);

		JPanel pnlDanhSachSanPham = new JPanel();
		pnlDanhSachSanPham.setBounds(519, 94, 587, 433);
		getContentPane().add(pnlDanhSachSanPham);
		setValue();

		pnlDanhSachSanPham.setLayout(null);
		String[] tieuDeChiTietHD = { "STT", "Mã sản phẩm", "Tên sản phẩm", "Giá tiền", "Số lượng" };
		modelChiTietHoaDon = new DefaultTableModel(tieuDeChiTietHD, 0);
		tbl_ChiTietHD = new JTable(modelChiTietHoaDon);
		scrChiTietHD = new JScrollPane(tbl_ChiTietHD, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrChiTietHD.setBounds(10, 45, 552, 367);
		tbl_ChiTietHD.setAutoCreateRowSorter(true);
		pnlDanhSachSanPham.add(scrChiTietHD);

		JLabel lblDanhSachSanPham = new JLabel("DANH SÁCH SẢN PHẨM");
		lblDanhSachSanPham.setBounds(128, 2, 360, 33);
		pnlDanhSachSanPham.add(lblDanhSachSanPham);
		lblDanhSachSanPham.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDanhSachSanPham.setHorizontalAlignment(SwingConstants.CENTER);
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
				modelChiTietHoaDon.addRow(new Object[] { i++, s.getMaSanPham(), s.getTenSach(),
						chiTietHoaDon.getDonGia(), chiTietHoaDon.getSoLuong() });
			} else {
				VanPhongPham vvp = sanPham_dao.getVPPTheoMaSP(masanPham);
				modelChiTietHoaDon.addRow(new Object[] { i++, vvp.getMaSanPham(), vvp.getTenVanPhongPham(),
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
