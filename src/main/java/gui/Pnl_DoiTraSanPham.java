package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Hashtable;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import db.DBConnection;
import entities.ChiTietHoaDon;
import entities.ChiTietHoaDonDoiTra;
import entities.HoaDon;
import entities.HoaDonDoiTra;
import entities.KhachHang;
import entities.NhanVien;
import entities.Sach;
import entities.SachLoi;
import entities.SanPham;
import entities.TaiKhoan;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import service.impl.ChiTietHoaDonDoiTraServiceImpl;
import service.impl.ChiTietHoaDonServiceImpl;
import service.impl.HoaDonDoiTraServiceImpl;
import service.impl.HoaDonServiceImpl;
import service.impl.KhachHangServiceImpl;
import service.impl.NhanVienServiceImpl;
import service.impl.SachLoiServiceImpl;
import service.impl.SanPhamServiceImpl;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.ImageIcon;

public class Pnl_DoiTraSanPham extends JPanel implements ActionListener, MouseListener {
	private JTextField txtTongTienSP;
	private JTextField txtTienKhachTra;
	private JTextField txtVAT;
	private JTextField txtMaHoaDonCu;
	private DefaultTableModel modelChiTietHoaDon;
	private JTable tblChiTietHoaDon;
	private JScrollPane scrChiTietHoaDon;
	private JLabel lblTitle;
	private JPanel pnlTool;
	private JLabel lblTongTienMoi;
	private JLabel lblVAT;
	private JLabel lblTienKhachTra;
	private JLabel lblTienThua;
	private JLabel lblGiaTriTienThua;
	private JPanel pnlTool2;
	private JButton btnHuy;
	private JButton btnIn;
	private JButton btnThanhToan;
	private JComponent componentNgayHienTai;
	private JLabel lblGiaTriNgayHienTai;
	private JLabel lblMaHoaDonCu;
	private JButton btnKiemTra;
	private JPanel pnlDoiTra;
	private JLabel lblChonSPThayThe;
	private JLabel lblGia;
	private JComboBox cmbSP;
	private JLabel lblGiaTriGiaSP;
	private JButton btnDoi;
	private JComponent componentTiTleHoaDonCu;
	private JPanel pnlHoaDonCu;
	private JLabel lblNgayHoaDonCu;
	private JLabel lblMaNhanVien;
	private JComponent componentMaKhachHang;
	private JLabel lblTongTien;
	private JLabel lblGiaTriNgayHoaDonCu;
	private JLabel lblGiaTriMaNV;
	private JLabel lblGiaTriMaKH;
	private JLabel lblGiaTriTongTien;
	private JLabel lblChitiet;
	private JPanel pnlDoi;
	private JButton btnXacNhanDoi;
	private NhanVienServiceImpl nhanVienServiceImpl;
	private ChiTietHoaDonServiceImpl chiTietHoaDonServiceImpl;
	private HoaDonServiceImpl hoaDonServiceImpl;
	private SanPhamServiceImpl sanPhamServiceImpl;
	private List<ChiTietHoaDon> dsCTHD;
	private List<SanPham> dsSP;
	private List<Sach> dsSach;
	private JLabel lblNhapLoi;
	private JTextField txtLoi;
	private JTextField txtSoLuongSPLoi;
	private SachLoiServiceImpl sachLoiSviceImpl;
	private KhachHangServiceImpl khachHangServiceImpl;
	private JLabel lblMaSPLoi;
	private JTextField txtMaSPDoi;
	private SachLoi sachLoi;
	private JLabel txtMaHDDoiTra;
	private JLabel txtGiaTriMaHDDT;
	private NhanVien nv;
	private KhachHang kh;
	private HoaDonDoiTraServiceImpl hoaDonDoiTraServiceImpl;
	private JButton btnXong;
	private SanPham sp;
	private ChiTietHoaDonDoiTraServiceImpl chiTietHoaDonDoiTraServiceImpl;
	private JButton btnLamMoiTatCa;
	private JButton btnTaoHDDoiTra;
	private JButton btnLamMoiHoaDon;
	private List<ChiTietHoaDonDoiTra> dsChiTietHoaDonDoiTRa;
	private List<SachLoi> dsSachLoi;
	private HoaDon hoaDon;
	private double tienPhaiTru = 0;

	/**
	 * Create the panel.
	 */
	public Pnl_DoiTraSanPham() {
		setBackground(new Color(0, 206, 209));
		setFont(new Font("Dialog", Font.BOLD, 16));
		setSize(1800, 900);
		setLayout(null);

		lblTitle = new JLabel("ĐỔI TRẢ SÁCH");
		lblTitle.setIcon(new ImageIcon(Pnl_DoiTraSanPham.class.getResource("/gui/icon/contract.png")));
		lblTitle.setForeground(new Color(255, 69, 0));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(0, 0, 1800, 63);
		add(lblTitle);

		pnlTool = new JPanel();
		pnlTool.setBackground(new Color(144, 238, 144));
		pnlTool.setBounds(0, 700, 1800, 97);
		add(pnlTool);
		pnlTool.setLayout(null);

		lblTongTienMoi = new JLabel("Tổng tiền :");
		lblTongTienMoi.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTongTienMoi.setBounds(34, 11, 164, 27);
		pnlTool.add(lblTongTienMoi);

		lblVAT = new JLabel("VAT: ");
		lblVAT.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblVAT.setBounds(34, 49, 46, 24);
		pnlTool.add(lblVAT);

		txtTongTienSP = new JTextField();
		txtTongTienSP.setBounds(220, 15, 175, 20);
		pnlTool.add(txtTongTienSP);
		txtTongTienSP.setColumns(10);

		lblTienKhachTra = new JLabel("Tiền khách trả: ");
		lblTienKhachTra.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTienKhachTra.setBounds(463, 15, 106, 23);
		pnlTool.add(lblTienKhachTra);

		txtTienKhachTra = new JTextField();
		txtTienKhachTra.setBounds(598, 15, 175, 20);
		pnlTool.add(txtTienKhachTra);
		txtTienKhachTra.setColumns(10);

		lblTienThua = new JLabel("Tiền thừa:");
		lblTienThua.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTienThua.setBounds(463, 50, 95, 23);
		pnlTool.add(lblTienThua);

		txtVAT = new JTextField();
		txtVAT.setBounds(220, 52, 175, 20);
		pnlTool.add(txtVAT);
		txtVAT.setColumns(10);

		lblGiaTriTienThua = new JLabel(".......");
		lblGiaTriTienThua.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGiaTriTienThua.setBounds(598, 49, 175, 24);
		pnlTool.add(lblGiaTriTienThua);

		pnlTool2 = new JPanel();
		pnlTool2.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlTool2.setBounds(1000, 11, 524, 75);
		pnlTool.add(pnlTool2);
		pnlTool2.setLayout(null);

		btnHuy = new JButton("Hủy");
		btnHuy.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnHuy.setBounds(27, 11, 126, 53);
		pnlTool2.add(btnHuy);

		btnIn = new JButton("In");
		btnIn.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnIn.setBounds(204, 11, 126, 53);
		pnlTool2.add(btnIn);

		btnThanhToan = new JButton("Thanh toán");
		btnThanhToan.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnThanhToan.setBounds(366, 11, 133, 53);
		pnlTool2.add(btnThanhToan);

		componentNgayHienTai = new JLabel("Ngày: ");
		componentNgayHienTai.setFont(new Font("Tahoma", Font.BOLD, 13));
		componentNgayHienTai.setBounds(27, 64, 57, 22);
		add(componentNgayHienTai);

		lblGiaTriNgayHienTai = new JLabel("26-10-2022");
		lblGiaTriNgayHienTai.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGiaTriNgayHienTai.setBounds(94, 67, 86, 17);
		add(lblGiaTriNgayHienTai);

		lblMaHoaDonCu = new JLabel("Nhập mã hóa đơn cũ: ");
		lblMaHoaDonCu.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMaHoaDonCu.setBounds(27, 131, 153, 30);
		add(lblMaHoaDonCu);

		txtMaHoaDonCu = new JTextField();
		txtMaHoaDonCu.setBounds(178, 137, 152, 20);
		add(txtMaHoaDonCu);
		txtMaHoaDonCu.setColumns(10);

		btnKiemTra = new JButton("Kiểm tra");
		btnKiemTra.setBounds(340, 136, 89, 23);
		add(btnKiemTra);

		pnlDoiTra = new JPanel();
		pnlDoiTra.setBounds(27, 172, 402, 430);
		add(pnlDoiTra);
		pnlDoiTra.setLayout(null);

		lblChonSPThayThe = new JLabel("Chọn sản phẩm thay thế: ");
		lblChonSPThayThe.setBounds(10, 182, 236, 22);
		pnlDoiTra.add(lblChonSPThayThe);
		lblChonSPThayThe.setFont(new Font("Tahoma", Font.BOLD, 15));

		lblGia = new JLabel("Giá: ");
		lblGia.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGia.setBounds(10, 215, 97, 22);
		pnlDoiTra.add(lblGia);

		cmbSP = new JComboBox();
		cmbSP.setBounds(235, 184, 145, 22);
		pnlDoiTra.add(cmbSP);

		lblGiaTriGiaSP = new JLabel(".............");
		lblGiaTriGiaSP.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGiaTriGiaSP.setBounds(235, 215, 157, 22);
		pnlDoiTra.add(lblGiaTriGiaSP);

		btnDoi = new JButton("Đổi sản phẩm");
		btnDoi.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnDoi.setBounds(10, 262, 145, 35);
		pnlDoiTra.add(btnDoi);

		lblNhapLoi = new JLabel("Nhập lỗi sản phẩm:");
		lblNhapLoi.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNhapLoi.setBounds(10, 64, 183, 22);
		pnlDoiTra.add(lblNhapLoi);

		txtLoi = new JTextField();
		txtLoi.setBounds(235, 64, 145, 22);
		pnlDoiTra.add(txtLoi);
		txtLoi.setColumns(10);

		JLabel lblSoLuongSanPhamLoiCanThayThe = new JLabel("Nhập số lượng sản phẩm lỗi cần thay thế:");
		lblSoLuongSanPhamLoiCanThayThe.setHorizontalAlignment(SwingConstants.LEFT);
		lblSoLuongSanPhamLoiCanThayThe.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSoLuongSanPhamLoiCanThayThe.setBounds(10, 99, 351, 45);
		pnlDoiTra.add(lblSoLuongSanPhamLoiCanThayThe);

		txtSoLuongSPLoi = new JTextField();
		txtSoLuongSPLoi.setBounds(10, 155, 145, 22);
		pnlDoiTra.add(txtSoLuongSPLoi);
		txtSoLuongSPLoi.setColumns(10);

		lblMaSPLoi = new JLabel("Mã sản phẩm lỗi:");
		lblMaSPLoi.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMaSPLoi.setBounds(10, 19, 145, 22);
		pnlDoiTra.add(lblMaSPLoi);

		txtMaSPDoi = new JTextField();
		txtMaSPDoi.setBounds(235, 22, 145, 20);
		pnlDoiTra.add(txtMaSPDoi);
		txtMaSPDoi.setColumns(10);

		componentTiTleHoaDonCu = new JLabel("Hóa đơn cũ");
		componentTiTleHoaDonCu.setFont(new Font("Tahoma", Font.BOLD, 13));
		componentTiTleHoaDonCu.setBounds(510, 138, 96, 17);
		add(componentTiTleHoaDonCu);

		pnlHoaDonCu = new JPanel();
		pnlHoaDonCu.setBounds(480, 175, 400, 430);
		add(pnlHoaDonCu);
		pnlHoaDonCu.setLayout(null);

		lblNgayHoaDonCu = new JLabel("Ngày lập: ");
		lblNgayHoaDonCu.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNgayHoaDonCu.setBounds(10, 34, 78, 30);
		pnlHoaDonCu.add(lblNgayHoaDonCu);

		lblMaNhanVien = new JLabel("Tên nhân viên: ");
		lblMaNhanVien.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMaNhanVien.setBounds(10, 120, 150, 30);
		pnlHoaDonCu.add(lblMaNhanVien);

		componentMaKhachHang = new JLabel("Mã khách hàng: ");
		componentMaKhachHang.setFont(new Font("Tahoma", Font.BOLD, 15));
		componentMaKhachHang.setBounds(10, 194, 150, 30);
		pnlHoaDonCu.add(componentMaKhachHang);

		lblTongTien = new JLabel("Tổng tiền: ");
		lblTongTien.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTongTien.setBounds(10, 278, 78, 30);
		pnlHoaDonCu.add(lblTongTien);

		lblGiaTriNgayHoaDonCu = new JLabel(".............");
		lblGiaTriNgayHoaDonCu.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGiaTriNgayHoaDonCu.setBounds(169, 34, 106, 30);
		pnlHoaDonCu.add(lblGiaTriNgayHoaDonCu);

		lblGiaTriMaNV = new JLabel(".............");
		lblGiaTriMaNV.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGiaTriMaNV.setBounds(169, 120, 150, 22);
		pnlHoaDonCu.add(lblGiaTriMaNV);

		lblGiaTriMaKH = new JLabel(".............");
		lblGiaTriMaKH.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGiaTriMaKH.setBounds(169, 194, 150, 30);
		pnlHoaDonCu.add(lblGiaTriMaKH);

		lblGiaTriTongTien = new JLabel("0");
		lblGiaTriTongTien.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGiaTriTongTien.setBounds(169, 278, 150, 22);
		pnlHoaDonCu.add(lblGiaTriTongTien);

		btnLamMoiHoaDon = new JButton("Làm mới");
		btnLamMoiHoaDon.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLamMoiHoaDon.setBounds(150, 340, 106, 35);
		pnlHoaDonCu.add(btnLamMoiHoaDon);

		lblChitiet = new JLabel("Chi tiết hóa đơn cũ");
		lblChitiet.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblChitiet.setBounds(922, 138, 167, 17);
		add(lblChitiet);

		String header_ChiTiet[] = { "STT", "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá" };
		modelChiTietHoaDon = new DefaultTableModel(header_ChiTiet, 0);
		tblChiTietHoaDon = new JTable(modelChiTietHoaDon);
		scrChiTietHoaDon = new JScrollPane(tblChiTietHoaDon, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrChiTietHoaDon.setBounds(922, 173, 830, 377);
		add(scrChiTietHoaDon);

		pnlDoi = new JPanel();
		pnlDoi.setBounds(922, 568, 830, 35);
		add(pnlDoi);

		btnXacNhanDoi = new JButton("Đổi sản phẩm này");
		btnXacNhanDoi.setFont(new Font("Tahoma", Font.BOLD, 13));
		pnlDoi.add(btnXacNhanDoi);

		lblGiaTriNgayHienTai.setText(LocalDate.now().toString());
		txtMaSPDoi.setEditable(false);

		btnLamMoiTatCa = new JButton("Làm mới");
		btnLamMoiTatCa.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLamMoiTatCa.setBounds(10, 331, 145, 35);
		pnlDoiTra.add(btnLamMoiTatCa);

		btnXong = new JButton("Xong");
		btnXong.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnXong.setBounds(223, 331, 138, 35);
		pnlDoiTra.add(btnXong);

		btnTaoHDDoiTra = new JButton("Tạo");
		btnTaoHDDoiTra.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnTaoHDDoiTra.setBounds(223, 262, 138, 35);
		pnlDoiTra.add(btnTaoHDDoiTra);
		btnThanhToan.setEnabled(false);
		btnXacNhanDoi.addActionListener(this);
		btnDoi.addActionListener(this);
		btnIn.addActionListener(this);
		btnKiemTra.addActionListener(this);
		btnThanhToan.addActionListener(this);
		btnHuy.addActionListener(this);
		btnLamMoiTatCa.addActionListener(this);
		btnXong.addActionListener(this);
		btnTaoHDDoiTra.addActionListener(this);
		btnLamMoiHoaDon.addActionListener(this);
		tblChiTietHoaDon.addMouseListener(this);

		txtMaHDDoiTra = new JLabel("Mã hóa đơn đổi trả: ");
		txtMaHDDoiTra.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtMaHDDoiTra.setBounds(221, 64, 153, 22);
		add(txtMaHDDoiTra);

		txtGiaTriMaHDDT = new JLabel("...");
		txtGiaTriMaHDDT.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtGiaTriMaHDDT.setBounds(384, 64, 142, 22);
		add(txtGiaTriMaHDDT);

		txtMaSPDoi.setEditable(false);
		txtLoi.setEditable(false);
		txtSoLuongSPLoi.setEditable(false);
		btnDoi.setEnabled(false);
		btnXacNhanDoi.setEnabled(false);
		btnXong.setEnabled(false);
		btnTaoHDDoiTra.setEnabled(false);
		btnIn.setEnabled(false);

		cmbSP.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				sanPhamServiceImpl = new SanPhamServiceImpl();
				if (cmbSP.getItemCount() > 0) {
					lblGiaTriGiaSP.setText(
							sanPhamServiceImpl.getSachTheoTen(String.valueOf(cmbSP.getSelectedItem())).getGiaNhap() + "");
				} else {
					return;
				}
			}
		});

		txtTienKhachTra.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				tinhTienThua();
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

	}

	public void xoaHetDuLieuChiTietHoaDon() {
		DefaultTableModel dtm = (DefaultTableModel) tblChiTietHoaDon.getModel();
		dtm.getDataVector().removeAllElements();
	}

	public void xoaHetDuLieuHoaDon() {
		lblGiaTriNgayHoaDonCu.setText("");
		lblGiaTriMaNV.setText("");
		lblGiaTriMaKH.setText("");
		lblGiaTriTongTien.setText("");
	}

	public void DocDuLieuTuArrayListVaoModel() throws Exception {
		hoaDonServiceImpl = new HoaDonServiceImpl();
		chiTietHoaDonServiceImpl = new ChiTietHoaDonServiceImpl();
		sanPhamServiceImpl = new SanPhamServiceImpl();

		if (hoaDonServiceImpl.getHoaDonTheoMa(txtMaHoaDonCu.getText()).size() == 0) {
			return;
		} else {

			HoaDon hd = hoaDonServiceImpl.getHoaDonTheoMa(txtMaHoaDonCu.getText()).get(0);

			long dayGap = ChronoUnit.DAYS.between(hd.getNgayLapHoaDon(), LocalDate.now());

			if (dayGap - 2 > 3) {

				return;
			}

			else {
				dsCTHD = chiTietHoaDonServiceImpl.getCTHoaDonTheoMaHoaDon(txtMaHoaDonCu.getText());
				if (dsCTHD.size() == 0) {

					return;
				} else {
					int count = 0;
					int i = 1;
					for (ChiTietHoaDon cthd : dsCTHD) {
						if (sanPhamServiceImpl.getSachTheoMaSP(cthd.getSanPham().getMaSanPham()).getTenSach() == null) {
							count++;
						}
					}
					if (count < dsCTHD.size()) {
						for (ChiTietHoaDon cthd : dsCTHD) {
							if (sanPhamServiceImpl.getSachTheoMaSP(cthd.getSanPham().getMaSanPham()).getTenSach() != null) {
								modelChiTietHoaDon.addRow(new Object[] { i++,
										sanPhamServiceImpl.getSachTheoMaSP(cthd.getSanPham().getMaSanPham()).getMaSanPham(),
										sanPhamServiceImpl.getSachTheoMaSP(cthd.getSanPham().getMaSanPham()).getTenSach(),
										cthd.getSoLuong(), cthd.getDonGia() });
							}
						}
					} else {
						JOptionPane.showMessageDialog(this, "Hóa đơn này không có sách để đổi");
						return;
					}
					JOptionPane.showMessageDialog(this, "Đã tìm thấy");
					btnTaoHDDoiTra.setEnabled(true);
				}
			}
		}
	}

	private void DocDuLieuHoaDon() throws SQLException {
		try {
			hoaDonServiceImpl = new HoaDonServiceImpl();
			nhanVienServiceImpl = new NhanVienServiceImpl();
			if (hoaDonServiceImpl.getHoaDonTheoMa(txtMaHoaDonCu.getText()).size() == 0) {
				JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn");
				return;
			} else {

				HoaDon hd = hoaDonServiceImpl.getHoaDonTheoMa(txtMaHoaDonCu.getText()).get(0);

				long dayGap = ChronoUnit.DAYS.between(hd.getNgayLapHoaDon(), LocalDate.now());

				if (dayGap - 2 > 3) {
					JOptionPane.showMessageDialog(this, "Hóa đơn này đã quá hạn đổi trả");
					return;
				}

				else {
					lblGiaTriNgayHoaDonCu.setText(hd.getNgayLapHoaDon().toString());
					if (hd.getNhanVien() == null) {
						lblGiaTriMaNV.setText("Đã nghỉ việc");
					} else {
						lblGiaTriMaNV.setText(hd.getNhanVien().getHoTenNhanVien().toString());
					}

					lblGiaTriMaKH.setText(hd.getKhachHang().getMaKhachHang().toString());
					chiTietHoaDonServiceImpl = new ChiTietHoaDonServiceImpl();
					long tongTien = 0;
					dsCTHD = chiTietHoaDonServiceImpl.getCTHoaDonTheoMaHoaDon(txtMaHoaDonCu.getText());

					for (ChiTietHoaDon cthd : dsCTHD) {
						tongTien += cthd.tinhThanhTien();
					}
					lblGiaTriTongTien.setText(String.valueOf(tongTien * 1.08));
				}
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void DocDuLieuVaoCombobox() {
		try {
			sanPhamServiceImpl = new SanPhamServiceImpl();
			dsSach = sanPhamServiceImpl.getAllSach();
			int row = 0;
			row = tblChiTietHoaDon.getSelectedRow();

			DefaultComboBoxModel model = (DefaultComboBoxModel) cmbSP.getModel();
			model.removeAllElements();

			for (Sach sach : dsSach) {

				if (sach.getSoLuongTon() > 0 && (sach.getGiaNhap() * 1.2) >= Double
						.parseDouble(tblChiTietHoaDon.getValueAt(row, 4).toString())) {
					cmbSP.addItem(sach.getTenSach());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void truSLSachKhiDoi() {
		sanPhamServiceImpl = new SanPhamServiceImpl();
		Sach sach = sanPhamServiceImpl.getSachTheoTen(cmbSP.getSelectedItem().toString());
		sach.setSoLuongTon(sach.getSoLuongTon() - Integer.valueOf(txtSoLuongSPLoi.getText().toString()).intValue());

		try {
			sanPhamServiceImpl.capNhatSanPham(sach.getMaSanPham(), sach);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void addVaoSachLoi() {
		sachLoiSviceImpl = new SachLoiServiceImpl();
		Sach sach = new Sach();

		try {
			dsSachLoi = sachLoiSviceImpl.getAllSachLoi();
			for (SachLoi sachLoi : dsSachLoi) {
				if (txtMaSPDoi.getText().equals(sachLoi.getSach().getMaSanPham())
						&& txtLoi.getText().equals(sachLoi.getLoiSanPham())) {
					sachLoi.setSoLuong(sachLoi.getSoLuong() + Integer.parseInt(txtSoLuongSPLoi.getText().toString()));
					sachLoiSviceImpl.capNhatSoLuong(sachLoi);
					return;
				}
			}

			try {
				sach = sanPhamServiceImpl.timSanPhamTheoMaSach(txtMaSPDoi.getText());
				sachLoi = new SachLoi(sach, txtLoi.getText().toString(),
						Integer.parseInt(txtSoLuongSPLoi.getText().toString()));
				sachLoiSviceImpl.themSachLoi(sachLoi);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void addHoaDonDoiTraMoi() throws SQLException {

		String maHDDT = "HDDT";
		LocalDate ngayLapHoaDon = LocalDate.now();
		String ngayLapHD = String.valueOf(ngayLapHoaDon.getDayOfMonth());
		int length = 0;
		hoaDonDoiTraServiceImpl = new HoaDonDoiTraServiceImpl();

		if (hoaDonDoiTraServiceImpl.getMaHoaDonDoiTraByMaHDCu(txtMaHoaDonCu.getText().toString()).size() > 0) {
			JOptionPane.showMessageDialog(this, "Hóa đơn này đã đổi trả rồi");
			btnXacNhanDoi.setEnabled(true);
			return;
		} else {
			JOptionPane.showMessageDialog(this, "Nhập thông tin hóa đơn đổi trả");
			try {
				length = hoaDonDoiTraServiceImpl.getDSHoaDonDoiTra().size();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String finalId = maHDDT + ngayLapHD + String.format("%05d", length + 1);
			txtGiaTriMaHDDT.setText(finalId);

			WinLogin dangNhap = new WinLogin();
			TaiKhoan taiKhoan = dangNhap.getTaiKhoanDangNhapThanhCong();
			nhanVienServiceImpl = new NhanVienServiceImpl();
			khachHangServiceImpl = new KhachHangServiceImpl();

			nv = nhanVienServiceImpl.timNhanVienTheoMa(taiKhoan.getNhanVien().getMaNhanVien());
			kh = khachHangServiceImpl.timKhachHangTheoMa(lblGiaTriMaKH.getText());
			Double tienKhachDua = (double) 0;
			tienPhaiTru = (double) 0;
			String ghiChu = "Không";
			hoaDon = hoaDonServiceImpl.getHoaDonTheoMa(txtMaHoaDonCu.getText().toString()).get(0);
			HoaDonDoiTra hdDoiTra = new HoaDonDoiTra(finalId, nv, kh, ngayLapHoaDon, ghiChu, tienKhachDua, hoaDon,
					tienPhaiTru);
			hoaDonDoiTraServiceImpl = new HoaDonDoiTraServiceImpl();
			hoaDonDoiTraServiceImpl.themHoaDonDoiTra(hdDoiTra);
		}

	}

	public void addCTHDDoiTraMoi() throws SQLException {
		try {
			chiTietHoaDonDoiTraServiceImpl = new ChiTietHoaDonDoiTraServiceImpl();
			hoaDonDoiTraServiceImpl = new HoaDonDoiTraServiceImpl();
			HoaDonDoiTra hddt = hoaDonDoiTraServiceImpl.getHoaDonDoiTraTheoMa(txtGiaTriMaHDDT.getText()).get(0);

			sanPhamServiceImpl = new SanPhamServiceImpl();
			sp = sanPhamServiceImpl.getSachTheoTen(cmbSP.getSelectedItem().toString());

			int soLuong = Integer.parseInt(txtSoLuongSPLoi.getText().toString().trim());
			long donGia = Long.parseLong(lblGiaTriGiaSP.getText().toString().trim());
			ChiTietHoaDonDoiTra cthddt = new ChiTietHoaDonDoiTra(hddt, sp, soLuong, donGia);
			chiTietHoaDonDoiTraServiceImpl.themChiTietHoaDonDoiTra(cthddt);
			int row = 0;
			row = tblChiTietHoaDon.getSelectedRow();
			row = 0;
			row = tblChiTietHoaDon.getSelectedRow();
			tienPhaiTru += Integer.parseInt(txtSoLuongSPLoi.getText().toString())
					* Double.parseDouble(tblChiTietHoaDon.getValueAt(row, 4).toString());
			JOptionPane.showMessageDialog(this, "Đã đổi thành công sản phẩm này");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Sản phẩm này đã đổi rồi");
			return;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnXacNhanDoi)) {
			if (tblChiTietHoaDon.getSelectedRow() == -1) {
				JOptionPane.showMessageDialog(this, "Phải chọn sản phẩm cần đổi");
			} else {
				DocDuLieuVaoCombobox();
				btnXacNhanDoi.setEnabled(false);
				btnDoi.setEnabled(true);

				txtLoi.setEditable(true);
				txtSoLuongSPLoi.setEditable(true);
				txtSoLuongSPLoi.setEditable(true);
				txtLoi.requestFocus();
			}
		}

		if (o.equals(btnKiemTra)) {
			xoaHetDuLieu();
			try {
				DocDuLieuTuArrayListVaoModel();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				DocDuLieuHoaDon();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

		}
		if (o.equals(btnTaoHDDoiTra)) {

			btnXacNhanDoi.setEnabled(true);
			btnTaoHDDoiTra.setEnabled(false);

			try {
				addHoaDonDoiTraMoi();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		if (o.equals(btnDoi)) {
			int row = 0;
			row = tblChiTietHoaDon.getSelectedRow();
			btnXong.setEnabled(true);
			btnXacNhanDoi.setEnabled(true);
			txtSoLuongSPLoi.setEditable(true);
			if (txtSoLuongSPLoi.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Phải nhập thông tin sản phẩm lỗi và sản phẩm muốn đổi");
			} else {
				try {
					if (Integer.parseInt(txtSoLuongSPLoi.getText()) <= 0
							|| Integer.parseInt(txtSoLuongSPLoi.getText()) <= 0) {
						JOptionPane.showMessageDialog(this,
								"Số lượng sản phẩm đổi và sản phẩm thay thế phải lớn hơn 0");
						btnXong.setEnabled(false);
					} else if (Integer.parseInt(txtSoLuongSPLoi.getText()) > Integer
							.parseInt(tblChiTietHoaDon.getValueAt(row, 3).toString())
							|| Integer.parseInt(txtSoLuongSPLoi.getText()) > Integer
									.parseInt(tblChiTietHoaDon.getValueAt(row, 3).toString())) {
						JOptionPane.showMessageDialog(this,
								"Số lượng sản phẩm đổi và sản phẩm thay thế nhỏ hơn hoặc bằng số lượng sản phẩm trong hóa đơn cũ");
						btnXong.setEnabled(false);
					} else {
						addVaoSachLoi();
						btnXong.setEnabled(true);
						try {
							addCTHDDoiTraMoi();
							truSLSachKhiDoi();
							clearAll();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(this, "Số lượng nhập vào phải là số");
				}
			}
		}
		if (o.equals(btnLamMoiTatCa)) {
			clearAll();
		}
		if (o.equals(btnLamMoiHoaDon)) {
			clearHD();
		}
		if (o.equals(btnThanhToan)) {

			txtMaSPDoi.setEditable(false);
			txtLoi.setEditable(false);
			txtSoLuongSPLoi.setEditable(false);
			txtSoLuongSPLoi.setEditable(false);
			btnDoi.setEnabled(false);
			btnXacNhanDoi.setEnabled(false);
			btnXong.setEnabled(false);
			btnTaoHDDoiTra.setEnabled(true);
			if (Double.parseDouble(lblGiaTriTienThua.getText().toString()) > 0
					&& !txtTienKhachTra.getText().isEmpty()) {
				if (Double.parseDouble(txtTienKhachTra.getText().toString()) < 0) {
					JOptionPane.showMessageDialog(this, "Tiền khách đưa phải lớn hơn 0");
				} else {
					try {
						hoaDonDoiTraServiceImpl = new HoaDonDoiTraServiceImpl();

						HoaDonDoiTra hoaDonDoiTra = hoaDonDoiTraServiceImpl
								.getHoaDonDoiTraTheoMa(txtGiaTriMaHDDT.getText().toString()).get(0);
						hoaDonDoiTra.setTienKhachDua(Double.parseDouble(txtTienKhachTra.getText().toString()));
						System.out.println(tienPhaiTru);
						hoaDonDoiTra.setTienPhaiTru(tienPhaiTru);
						hoaDonDoiTraServiceImpl.editTienPhaiTru(hoaDonDoiTra);
						hoaDonDoiTraServiceImpl.editTienKhachTra(hoaDonDoiTra);
						JOptionPane.showMessageDialog(this, "Thanh toán thành công");
						btnThanhToan.setEnabled(false);
						txtMaHoaDonCu.setText("");
						txtTienKhachTra.setText("");
						txtTongTienSP.setText("");
						txtVAT.setText("");
						lblGiaTriTienThua.setText("....");
						clearAll();
						clearHD();
						xoaHetDuLieu();
						tienPhaiTru = 0;
						btnIn.setEnabled(true);
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(this, "Tiền nhập khách đưa phải là số");
					}
				}
			} else {
				JOptionPane.showMessageDialog(this, "Tiền khách đưa không đủ");
			}

		}
		if (o.equals(btnHuy)) {
			txtMaHoaDonCu.setText("");
			txtTienKhachTra.setText("");
			txtTongTienSP.setText("");
			txtVAT.setText("");
			lblGiaTriTienThua.setText("....");
			clearAll();
			clearHD();
			xoaHetDuLieu();
			tienPhaiTru = 0;
		}
		if (o.equals(btnIn)) {
			xuatHoaDon(txtGiaTriMaHDDT.getText().toString());
		}
		if (o.equals(btnXong)) {

			chiTietHoaDonDoiTraServiceImpl = new ChiTietHoaDonDoiTraServiceImpl();
			dsChiTietHoaDonDoiTRa = chiTietHoaDonDoiTraServiceImpl.getCTHoaDonDoiTraTheoMaHoaDonDoiTra(txtGiaTriMaHDDT.getText().toString());
			double tongTien = 0;
			for (ChiTietHoaDonDoiTra chiTietHoaDonDoiTra : dsChiTietHoaDonDoiTRa) {
				tongTien += (chiTietHoaDonDoiTra.getDonGia() * 1.2 * chiTietHoaDonDoiTra.getSoLuong());
			}

			tongTien = tongTien - tienPhaiTru;

			double vat = 0;

			if (tongTien == 0) {
				vat = 5000;
			} else {
				vat = 5000 + tongTien * 0.1 * 1.1;
			}

			txtTongTienSP.setText(String.valueOf(tongTien * 1.1 + vat));
			txtVAT.setText(String.valueOf(vat));
			JOptionPane.showMessageDialog(this, "Vui lòng thanh toán");
			txtTienKhachTra.requestFocus();
			btnThanhToan.setEnabled(true);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = tblChiTietHoaDon.getSelectedRow();
		txtMaSPDoi.setText(modelChiTietHoaDon.getValueAt(row, 1).toString());
		DocDuLieuVaoCombobox();
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

	public void clearAll() {
		txtMaSPDoi.setText("");
		txtLoi.setText("");
		lblGiaTriGiaSP.setText("..........");
		txtSoLuongSPLoi.setText("");

	}

	public void clearHD() {
		lblGiaTriNgayHienTai.setText("...");
		lblGiaTriMaNV.setText("..........");
		lblGiaTriMaKH.setText("..........");
		lblGiaTriTongTien.setText("0");
	}

	public void tinhTienThua() {
		try {
			double tienThua = 0;
			Double tienKhachTra = Double.parseDouble(txtTienKhachTra.getText().toString());
			Double tongTien = Double.parseDouble(txtTongTienSP.getText().toString());
			if (tongTien != 0) {
				tienThua = tienKhachTra - tongTien;
			} else {
				tienThua = tienKhachTra - Double.parseDouble(txtVAT.getText().toString());
			}
			lblGiaTriTienThua.setText(String.valueOf(tienThua));
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Tiền nhập vào phải là số");
		}

	}

	public void xoaHetDuLieu() {
		DefaultTableModel dtm = (DefaultTableModel) tblChiTietHoaDon.getModel();
		dtm.getDataVector().removeAllElements();
	}

	public void xuatHoaDon(String maHDDT) {
		try {
			Hashtable map = new Hashtable();
			JasperReport report = JasperCompileManager.compileReport("src/main/java/gui/HoaDonBanHang.jrxml");
			map.put("maHDDT", maHDDT);
			JasperPrint p = JasperFillManager.fillReport(report, map, DBConnection.getInstance().getConnection());

			JasperViewer.viewReport(p, false);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
