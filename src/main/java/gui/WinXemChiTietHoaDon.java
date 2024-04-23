package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
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
import service.*;
import util.Constants;

import java.awt.SystemColor;

public class WinXemChiTietHoaDon extends JFrame implements ActionListener {
	
	// String variables
	String maHoaDon;
	String tenNhanVien;
	String ngayLap;
	String tenKhachHang;
	String tienKhachDua;
	String ghiChu;
	String tongTienHoaDon;

	// JLabels
	private JLabel lblNhanVienLapHoaDon;
	private JLabel lblTenKhachHang;
	private JLabel lblNgayLapHoaDonValue;
	private JLabel lblMaHoaDon;
	private JLabel lblTenKH;
	private JLabel lblNgayLapHoaDon;
	private JLabel lblSoTrang;
	private JLabel lblNhanVienLapHoaDonDetail;
	private JLabel lblTenSp;
	private JLabel lblDanhSachSanPham;
	private JLabel lblDanhSachSanPhamDetail;
	private JLabel lblTongTienKhachDua;
	private JLabel lblTongTienHoaDon;
	private JLabel lblTienKhachDua;
	
	// JPanel
	private JPanel pnlRight;
	
	// JButton
	private JButton btnThoat;

	// JTextArea
	private JTextArea txaGhiChu;

	// JScrollPane
	JScrollPane scrChiTietHD;

	// JTable
	private JTable tblChiTietHoaDon;

	// DefaultTableModel
	private DefaultTableModel modelChiTietHoaDonDao;

	// SanPham
	private SanPham sanPham;

	// List of ChiTietHoaDon
	private List<ChiTietHoaDon> dsHoaDon;


	private static final String URL = "rmi://"+ Constants.IPV4 + ":"+ Constants.PORT + "/";
	private SanPhamService sanPhamService = (SanPhamService) Naming.lookup(URL + "sanPham");
	private ChiTietHoaDonService chiTietHoaDonService = (ChiTietHoaDonService) Naming.lookup(URL + "chiTietHoaDon");


	@SuppressWarnings("deprecation")
	public WinXemChiTietHoaDon(String maHoaDon, String tenNhanVien, String ngayLap, String tenKhachHang,
								String tienKhachDua, String tongTienHoaDon, String ghiChu) throws Exception {

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

		lblNgayLapHoaDonValue = new JLabel();
		lblNgayLapHoaDonValue.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNgayLapHoaDonValue.setBackground(Color.WHITE);
		lblNgayLapHoaDonValue.setBounds(236, 275, 240, 38);
		getContentPane().add(lblNgayLapHoaDonValue);

		JPanel panel = new JPanel();
		panel.setBounds(10, 38, 285, 46);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblChiTietHoaDon = new JLabel("CHI TIẾT HÓA ĐƠN");
		lblChiTietHoaDon.setForeground(SystemColor.textHighlight);
		lblChiTietHoaDon.setVerticalAlignment(SwingConstants.TOP);
		lblChiTietHoaDon.setHorizontalAlignment(SwingConstants.CENTER);
		lblChiTietHoaDon.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblChiTietHoaDon.setBounds(201, 10, 766, 39);
		getContentPane().add(lblChiTietHoaDon);

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

		lblNhanVienLapHoaDonDetail = new JLabel("Nhân viên lập hóa đơn:");
		lblNhanVienLapHoaDonDetail.setForeground(new Color(72, 61, 139));
		lblNhanVienLapHoaDonDetail.setFont(new Font("Arial", Font.BOLD, 16));
		lblNhanVienLapHoaDonDetail.setBounds(10, 160, 196, 40);
		getContentPane().add(lblNhanVienLapHoaDonDetail);

		lblTenSp = new JLabel();
		lblTenSp.setFont(new Font("Tahoma", Font.PLAIN, 16));

		lblTenSp.setBounds(297, 122, 328, 23);
		getContentPane().add(lblTenSp);

		JPanel pnlButtonContainer = new JPanel();
		pnlButtonContainer.setBounds(10, 599, 1096, 54);
		getContentPane().add(pnlButtonContainer);
		pnlButtonContainer.setLayout(null);

		btnThoat = new JButton("Thoát");
		btnThoat.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnThoat.setBounds(405, 10, 132, 39);
		pnlButtonContainer.add(btnThoat);

		txaGhiChu = new JTextArea();
		txaGhiChu.setFont(new Font("Courier New", Font.PLAIN, 13));
		txaGhiChu.setBounds(10, 480, 485, 109);
		txaGhiChu.setBorder(BorderFactory.createLineBorder(Color.black));
		getContentPane().add(txaGhiChu);

		JLabel lblGhiChuMoTa = new JLabel("Ghi chú(Mô tả):");
		lblGhiChuMoTa.setForeground(new Color(72, 61, 139));
		lblGhiChuMoTa.setFont(new Font("Arial", Font.BOLD, 16));
		lblGhiChuMoTa.setBounds(158, 447, 160, 23);
		getContentPane().add(lblGhiChuMoTa);

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

		pnlRight = new JPanel();
		pnlRight.setBounds(519, 94, 587, 433);
		getContentPane().add(pnlRight);
		setValue();

		pnlRight.setLayout(null);
		String[] tieuDeChiTietHoaDon = { "STT", "Mã sản phẩm", "Tên sản phẩm", "Giá tiền", "Số lượng" };
		modelChiTietHoaDonDao = new DefaultTableModel(tieuDeChiTietHoaDon, 0);
		tblChiTietHoaDon = new JTable(modelChiTietHoaDonDao);
		scrChiTietHD = new JScrollPane(tblChiTietHoaDon, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrChiTietHD.setBounds(10, 45, 552, 367);
		tblChiTietHoaDon.setAutoCreateRowSorter(true);
		pnlRight.add(scrChiTietHD);

		lblDanhSachSanPhamDetail = new JLabel("DANH SÁCH SẢN PHẨM");
		lblDanhSachSanPhamDetail.setBounds(128, 2, 360, 33);
		pnlRight.add(lblDanhSachSanPhamDetail);
		lblDanhSachSanPhamDetail.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDanhSachSanPhamDetail.setHorizontalAlignment(SwingConstants.CENTER);
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
		lblNgayLapHoaDonValue.setText(ngayLap);
		lblTienKhachDua.setText(tienKhachDua);
		lblTongTienHoaDon.setText(tongTienHoaDon);
	}

	private void docDLVaoTableModel() throws Exception {

		dsHoaDon = chiTietHoaDonService.getCTHoaDonTheoMaHoaDon(maHoaDon);
		int i = 1;

		for (ChiTietHoaDon chiTietHoaDon : dsHoaDon) {
			String masanPham = chiTietHoaDon.getSanPham().getMaSanPham();
			sanPham = sanPhamService.timSanPhamTheoMa1(masanPham);
			if (sanPham.getLoaiSanPham().equals("Sách")) {
				Sach s = sanPhamService.getSachTheoMaSP(masanPham);
				modelChiTietHoaDonDao.addRow(new Object[] { i++, s.getMaSanPham(), s.getTenSach(),
						chiTietHoaDon.getDonGia(), chiTietHoaDon.getSoLuong() });
			} else {
				VanPhongPham vvp = sanPhamService.getVPPTheoMaSP(masanPham);
				modelChiTietHoaDonDao.addRow(new Object[] { i++, vvp.getMaSanPham(), vvp.getTenVanPhongPham(),
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
