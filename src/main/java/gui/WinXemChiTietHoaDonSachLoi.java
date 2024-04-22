package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
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
import entities.Sach;
import entities.SachLoi;
import entities.SanPham;

import lombok.SneakyThrows;
import service.*;
import service.impl.SanPhamServiceImpl;
import util.Constants;

import java.awt.SystemColor;

public class WinXemChiTietHoaDonSachLoi extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblSoTrang;
	private JButton btnThoat;
	private List<SachLoi> dsSachLoi;
	private JTable tblChiTietHD;
	private DefaultTableModel tableModelChiTietHoaDonDao;
	private JScrollPane scrChiTietHD;
	private SanPhamDao sanPhamDao;
	private JButton btnDoi;

	private static final String URL = "rmi://"+ Constants.IPV4 + ":"+ Constants.PORT + "/";
	private SanPhamService sanPhamService = (SanPhamService) Naming.lookup(URL + "sanPham");
	private SachLoiService sachLoiService = (SachLoiService) Naming.lookup(URL + "sachLoi");
	private HoaDonService hoaDonService = (HoaDonService) Naming.lookup(URL + "hoaDon");
	private TaiKhoanService taiKhoanService = (TaiKhoanService) Naming.lookup(URL + "taiKhoan");
	private NhanVienService nhanVienService = (NhanVienService) Naming.lookup(URL + "nhanVien");
	private ChiTietHoaDonService chiTietHoaDonService = (ChiTietHoaDonService) Naming.lookup(URL + "chiTietHoaDon");
	private KhachHangService khachHangService = (KhachHangService) Naming.lookup(URL + "khachHang");
	private TacGiaService tacGiaService = (TacGiaService) Naming.lookup(URL + "tacGia");
//	@SuppressWarnings("deprecation")
	public WinXemChiTietHoaDonSachLoi() throws Exception {

		setTitle("Chi tiết hóa đơn");
		setResizable(false);
		setSize(1130, 700);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JLabel lblTitle = new JLabel("CÁC SÁCH LỖI");
		lblTitle.setForeground(SystemColor.textHighlight);
		lblTitle.setVerticalAlignment(SwingConstants.TOP);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblTitle.setBounds(201, 10, 766, 39);
		getContentPane().add(lblTitle);

		lblSoTrang = new JLabel();
		lblSoTrang.setFont(new Font("Tahoma", Font.PLAIN, 16));

		lblSoTrang.setBounds(125, 290, 170, 23);
		getContentPane().add(lblSoTrang);

		JPanel pnlControl = new JPanel();
		pnlControl.setBounds(28, 599, 1078, 54);
		getContentPane().add(pnlControl);
		pnlControl.setLayout(null);

		btnThoat = new JButton("Thoát");
		btnThoat.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnThoat.setBounds(569, 10, 132, 39);
		pnlControl.add(btnThoat);

		btnDoi = new JButton("Đổi");
		btnDoi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnDoi.setBounds(363, 10, 132, 39);
		pnlControl.add(btnDoi);

		JPanel panel_right = new JPanel();
		panel_right.setBounds(28, 59, 1078, 511);
		getContentPane().add(panel_right);
		setValue();

		panel_right.setLayout(null);
		String header_ChiTietHD[] = { "STT", "Mã sản phẩm", "Tên sản phẩm", "Lỗi sản phẩm", "Số lượng" };
		tableModelChiTietHoaDonDao = new DefaultTableModel(header_ChiTietHD, 0);
		tblChiTietHD = new JTable(tableModelChiTietHoaDonDao);
		scrChiTietHD = new JScrollPane(tblChiTietHD, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrChiTietHD.setBounds(10, 45, 1058, 434);
		tblChiTietHD.setAutoCreateRowSorter(true);
		panel_right.add(scrChiTietHD);

		JLabel lblDSSanPham = new JLabel("DANH SÁCH SẢN PHẨM");
		lblDSSanPham.setBounds(274, 2, 360, 33);
		panel_right.add(lblDSSanPham);
		lblDSSanPham.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDSSanPham.setHorizontalAlignment(SwingConstants.CENTER);
		try {
			docDuLieuSachLoi();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		btnThoat.addActionListener(this);
		btnDoi.addActionListener(this);
	}

	public void setValue() {
	}

	// Doc du lieu sach loi
	public void docDuLieuSachLoi() throws Exception {
		sanPhamDao = new SanPhamDao();
		dsSachLoi =  sachLoiService.getAllSachLoi();
		if (dsSachLoi.size() == 0) {
			JOptionPane.showMessageDialog(this, "Không tìm thấy sách lỗi");
		} else {
			int i = 1;
			for (SachLoi sachLoi : dsSachLoi) {
				Sach sach = sanPhamDao.timSanPhamTheoMaSach(sachLoi.getSach().getMaSanPham());
				System.out.println(new Sach(sachLoi.getSach().getMaSanPham()).getTenSach());
				tableModelChiTietHoaDonDao.addRow(new Object[] { i++, sachLoi.getSach().getMaSanPham(),
						sach.getTenSach(), sachLoi.getLoiSanPham(), sachLoi.getSoLuong() });
			}
		}
	}

	@SneakyThrows
    @Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThoat)) {
			if (JOptionPane.showConfirmDialog(null, "Có chắc bạn muốn thoát", "Cảnh báo", JOptionPane.YES_NO_OPTION,
					JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
				this.setVisible(false);
		}
		if (o.equals(btnDoi)) {
			try {
				int row = tblChiTietHD.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(this, "Chưa chọn sản phẩm cần đổi");
				} else {
					SanPham sanPham = sanPhamService.timSanPhamTheoMa(
							tblChiTietHD.getValueAt(tblChiTietHD.getSelectedRow(), 1).toString());
					sanPham.setSoLuongTon(sanPham.getSoLuongTon() + Integer
							.parseInt(tblChiTietHD.getValueAt(tblChiTietHD.getSelectedRow(), 4).toString()));
					sanPhamService.capNhatSoLuongSanPham(sanPham);
					// Hàm xóa sản phẩm lỗi theo mã
					sachLoiService.xoaSachLoi(sanPham.getMaSanPham(), tblChiTietHD.getValueAt(row, 3).toString());
					JOptionPane.showMessageDialog(this, "Sản phẩm đã được đổi");
					try {
						tableModelChiTietHoaDonDao.setRowCount(0);
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
