package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import dao.SachLoiDao;
import dao.SanPhamDao;
import entity.Sach;
import entity.SachLoi;
import entity.SanPham;

import service.impl.SanPhamServiceImpl;
import java.awt.SystemColor;

public class WinXemChiTietHoaDon2 extends JFrame implements ActionListener {

	// Services
	private SanPhamServiceImpl sanPhamServiceImpl;
	private SachLoiDao sachLoiDao;
	private SanPhamDao sanPhamDao;

	// Data
	private List<SachLoi> dsSachLoi;

	// UI Components: Labels
	private JLabel lblTieuDeSachLoi;
	private JLabel lblSoTrang;
	private JLabel lblDanhSachSanPham;

	// UI Components: Buttons
	private JButton btnThoat;
	private JButton btnDoiSach;

	// UI Components: Panels
	private JPanel pnlThongTin;
	private JPanel pnlDanhSachSanPham;

	// UI Components: Tables
	private JTable tblChiTietHD;
	private JTable tblChiTietHoaDon;

	// UI Components: ScrollPanes
	private JScrollPane scrChiTietHoaDon;

	// UI Components: Models
	private DefaultTableModel modelChiTietHoaDonDao;

	public WinXemChiTietHoaDon2() throws Exception {

		setTitle("Chi tiết hóa đơn");
		setResizable(false);
		setSize(1130, 700);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		lblTieuDeSachLoi = new JLabel("CÁC SÁCH LỖI");
		lblTieuDeSachLoi.setForeground(SystemColor.textHighlight);
		lblTieuDeSachLoi.setVerticalAlignment(SwingConstants.TOP);
		lblTieuDeSachLoi.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDeSachLoi.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblTieuDeSachLoi.setBounds(201, 10, 766, 39);
		getContentPane().add(lblTieuDeSachLoi);

		lblSoTrang = new JLabel();
		lblSoTrang.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSoTrang.setBounds(125, 290, 170, 23);
		getContentPane().add(lblSoTrang);

		pnlThongTin = new JPanel();
		pnlThongTin.setBounds(28, 599, 1078, 54);
		getContentPane().add(pnlThongTin);
		pnlThongTin.setLayout(null);

		btnThoat = new JButton("Thoát");
		btnThoat.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnThoat.setBounds(569, 10, 132, 39);
		pnlThongTin.add(btnThoat);

		btnDoiSach = new JButton("Đổi");
		btnDoiSach.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnDoiSach.setBounds(363, 10, 132, 39);
		pnlThongTin.add(btnDoiSach);

		pnlDanhSachSanPham = new JPanel();
		pnlDanhSachSanPham.setBounds(28, 59, 1078, 511);
		getContentPane().add(pnlDanhSachSanPham);
		setValue();

		pnlDanhSachSanPham.setLayout(null);
		String header_ChiTietHD[] = { "STT", "Mã sản phẩm", "Tên sản phẩm", "Lỗi sản phẩm", "Số lượng" };
		modelChiTietHoaDonDao = new DefaultTableModel(header_ChiTietHD, 0);
		tblChiTietHoaDon = new JTable(modelChiTietHoaDonDao);
		scrChiTietHoaDon = new JScrollPane(tblChiTietHoaDon, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrChiTietHoaDon.setBounds(10, 45, 1058, 434);
		tblChiTietHoaDon.setAutoCreateRowSorter(true);
		pnlDanhSachSanPham.add(scrChiTietHoaDon);

		lblDanhSachSanPham = new JLabel("DANH SÁCH SẢN PHẨM");
		lblDanhSachSanPham.setBounds(274, 2, 360, 33);
		pnlDanhSachSanPham.add(lblDanhSachSanPham);
		lblDanhSachSanPham.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDanhSachSanPham.setHorizontalAlignment(SwingConstants.CENTER);
		try {
			docDuLieuSachLoi();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		btnThoat.addActionListener(this);
		btnDoiSach.addActionListener(this);
	}

	public void setValue() {
	}

	// Doc du lieu sach loi
	public void docDuLieuSachLoi() throws Exception {
		sachLoiDao = new SachLoiDao();
		sanPhamDao = new SanPhamDao();
		dsSachLoi =  sachLoiDao.getAllSachLoi();
		if (dsSachLoi.size() == 0) {
			JOptionPane.showMessageDialog(this, "Không tìm thấy sách lỗi");
		} else {
			int i = 1;
			for (SachLoi sachLoi : dsSachLoi) {
				Sach sach = sanPhamDao.timSanPhamTheoMaSach(sachLoi.getSach().getMaSanPham());
				System.out.println(new Sach(sachLoi.getSach().getMaSanPham()).getTenSach());
				modelChiTietHoaDonDao.addRow(new Object[] { i++, sachLoi.getSach().getMaSanPham(),
						sach.getTenSach(), sachLoi.getLoiSanPham(), sachLoi.getSoLuong() });
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
		if (o.equals(btnDoiSach)) {
			try {
				int row = tblChiTietHD.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(this, "Chưa chọn sản phẩm cần đổi");
				} else {
					SanPham sanPham = sanPhamServiceImpl.timSanPhamTheoMa(
							tblChiTietHD.getValueAt(tblChiTietHD.getSelectedRow(), 1).toString());
					sanPham.setSoLuongTon(sanPham.getSoLuongTon() + Integer
							.parseInt(tblChiTietHD.getValueAt(tblChiTietHD.getSelectedRow(), 4).toString()));
					sanPhamServiceImpl.capNhatSoLuongSanPham(sanPham);
					// Hàm xóa sản phẩm lỗi theo mã
					sachLoiDao.xoaSachLoi(sanPham.getMaSanPham(), tblChiTietHD.getValueAt(row, 3).toString());
					JOptionPane.showMessageDialog(this, "Sản phẩm đã được đổi");
					try {
						modelChiTietHoaDonDao.setRowCount(0);
						docDuLieuSachLoi();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
	}
}
