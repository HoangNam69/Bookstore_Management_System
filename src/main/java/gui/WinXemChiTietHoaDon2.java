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
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblSoTrang;

	private JButton btnThoat;
	SanPhamServiceImpl sanPhamServiceImpl = new SanPhamServiceImpl();
	private List<SachLoi> dsSachLoi;

	private JTable tblChiTietHD;
	private DefaultTableModel tableModelChiTietHoaDonDao;

	private SachLoiDao sachLoiDao;
	private JScrollPane scrChiTietHD;
	private SanPhamDao sanPhamDao;
	private JButton btnDoi;

//	@SuppressWarnings("deprecation")
	public WinXemChiTietHoaDon2() throws Exception {

		setTitle("Chi tiết hóa đơn");
		setResizable(false);
		setSize(1130, 700);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("CÁC SÁCH LỖI");
		lblNewLabel.setForeground(SystemColor.textHighlight);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(201, 10, 766, 39);
		getContentPane().add(lblNewLabel);

		lblSoTrang = new JLabel();
		lblSoTrang.setFont(new Font("Tahoma", Font.PLAIN, 16));

		lblSoTrang.setBounds(125, 290, 170, 23);
		getContentPane().add(lblSoTrang);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(28, 599, 1078, 54);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		btnThoat = new JButton("Thoát");
		btnThoat.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnThoat.setBounds(569, 10, 132, 39);
		panel_1.add(btnThoat);

		btnDoi = new JButton("Đổi");
		btnDoi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnDoi.setBounds(363, 10, 132, 39);
		panel_1.add(btnDoi);

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

		JLabel lblNewLabel_1 = new JLabel("DANH SÁCH SẢN PHẨM");
		lblNewLabel_1.setBounds(274, 2, 360, 33);
		panel_right.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
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
				tableModelChiTietHoaDonDao.addRow(new Object[] { i++, sachLoi.getSach().getMaSanPham(),
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
		if (o.equals(btnDoi)) {
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