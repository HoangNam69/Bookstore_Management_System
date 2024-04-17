package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.toedter.calendar.JDateChooser;

import java.awt.Color;

import entity.NhanVien;
import entity.SanPham;
import entity.TaiKhoan;
import service.impl.HoaDonServiceImpl;
import service.impl.NhanVienServiceImpl;
import service.impl.SanPhamServiceImpl;

import javax.swing.border.LineBorder;

public class Pnl_ThongKeNhanVien extends JPanel implements ActionListener, MouseListener {

	/**
	 * Create the panel.
	 */
	private static DefaultCategoryDataset dataset;
	private JTabbedPane tabThongKe;
	private JPanel pnlThongKeSanPham;
	private JPanel pnlTongSoLuongSach;
	private JLabel lblTongSoLuongSach;
	private JPanel pnlTongSoLuongVanPhongPham;
	private JLabel lblTongSoVanPhongPham;
	private JPanel pnlTongSoSachLoi;
	private JLabel lblTongSoSachLoi;
	private JPanel pnlLocSPBanChay;
	private JPanel pnlSachBanChayNhat;
	private JLabel lblSanPhamBanChayNhat;
	private JButton btnLocSanPham;
	private JLabel lblFromThongKeSP;
	private JDateChooser dateChooserFromThongKeSP;
	private JDateChooser dateChooserToThongKeSP;
	private JLabel lblToThongKeSP;
	private JLabel lblBoLocSPBanChay;
	private JPanel pnlThongKeDoanhThuBanThan;
	private ChartPanel chartPnl;
	private JLabel lblTongHoaDonBanDuoc;
	private JLabel lblTongDoanhThu;
	private JLabel lblTo;
	private JDateChooser dateChooserFromDoanhThu;
	private JLabel lblFrom;
	private JComboBox cmbTieuChiDoanhThu;
	private JDateChooser dateChooserToDoanhThu;
	private JLabel txtTieuDe;
	private JButton btnLocDoanhThuNV;
	private JLabel lblMaSanPham;
	private JLabel lblLoaiSanPham;
	private JLabel lblTenSanPham;
	private JLabel lblGiaBan;
	private JLabel lblSoLuongDaBan;
	private JPanel pnlHinhAnh;
	private JLabel lblHinhAnh;
	private SanPhamServiceImpl sanPhamServiceImpl;
	private HoaDonServiceImpl hoaDonServiceImpl;
	private List<SanPham> dsSanPham;
	private JLabel lblMaSPTop1;
	private JLabel lblLoaiSPTop1;
	private JLabel lblTenSPTop1;
	private JLabel lblGiaSPTop1;
	private JLabel lblSoLuongSPTop1;
	private JLabel lblValueSoLuongSach;
	private JLabel lblValueSoLuongSachLoi;
	private JLabel lblValueSoLuongVPP;
	private JLabel lblGiaTriDoanhThu;
	private JLabel lblGiaTriTongHoaDon;
	private NhanVienServiceImpl nhanVienServiceImpl;
	private NhanVien nv;
	private String tenNV;
	private String maNV;

	public static JFreeChart createChart() {
		JFreeChart barChart = ChartFactory.createBarChart("BIỂU ĐỒ DOANH THU", "Tháng", "Doanh thu", createDataset(),
				PlotOrientation.VERTICAL, false, false, false);
		return barChart;
	}

	private static CategoryDataset createDataset() {
		dataset = new DefaultCategoryDataset();
		dataset.addValue(0, "Doanh thu", "1");
		dataset.addValue(0, "Doanh thu", "2");
		dataset.addValue(0, "Doanh thu", "3");
		dataset.addValue(0, "Doanh thu", "4");
		dataset.addValue(0, "Doanh thu", "5");
		dataset.addValue(0, "Doanh thu", "6");
		dataset.addValue(0, "Doanh thu", "7");
		dataset.addValue(0, "Doanh thu", "8");
		dataset.addValue(0, "Doanh thu", "9");
		dataset.addValue(0, "Doanh thu", "10");
		dataset.addValue(0, "Doanh thu", "11");
		dataset.addValue(0, "Doanh thu", "12");
		return dataset;
	}

	public Pnl_ThongKeNhanVien() throws SQLException {

		setLayout(null);
		setSize(1900, 900);
		tabThongKe = new JTabbedPane(JTabbedPane.TOP);
		tabThongKe.setFont(new Font("Tahoma", Font.BOLD, 14));
		tabThongKe.setBounds(27, 79, 1450, 610);
		add(tabThongKe);

		pnlThongKeSanPham = new JPanel();
		pnlThongKeSanPham.setBackground(new Color(0, 206, 209));
		tabThongKe.addTab("Thống kê sản phẩm", null, pnlThongKeSanPham, null);
		pnlThongKeSanPham.setLayout(null);

		pnlTongSoLuongSach = new JPanel();
		pnlTongSoLuongSach.setBounds(925, 11, 263, 133);
		pnlThongKeSanPham.add(pnlTongSoLuongSach);
		pnlTongSoLuongSach.setLayout(null);

		lblTongSoLuongSach = new JLabel("    Tổng số lượng sách: \r\n");
		lblTongSoLuongSach.setIcon(new ImageIcon(Pnl_ThongKeQuanLy.class.getResource("/gui/icon/books.png")));
		lblTongSoLuongSach.setHorizontalAlignment(SwingConstants.CENTER);
		lblTongSoLuongSach.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTongSoLuongSach.setBounds(0, 0, 263, 36);
		pnlTongSoLuongSach.add(lblTongSoLuongSach);

		lblValueSoLuongSach = new JLabel("New label");
		lblValueSoLuongSach.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblValueSoLuongSach.setHorizontalAlignment(SwingConstants.CENTER);
		lblValueSoLuongSach.setBounds(0, 47, 263, 86);
		pnlTongSoLuongSach.add(lblValueSoLuongSach);

		pnlTongSoLuongVanPhongPham = new JPanel();
		pnlTongSoLuongVanPhongPham.setBounds(925, 372, 263, 133);
		pnlThongKeSanPham.add(pnlTongSoLuongVanPhongPham);
		pnlTongSoLuongVanPhongPham.setLayout(null);

		lblTongSoVanPhongPham = new JLabel("    Tổng số văn phòng phẩm: ");
		lblTongSoVanPhongPham.setIcon(new ImageIcon(Pnl_ThongKeQuanLy.class.getResource("/gui/icon/stationery.png")));
		lblTongSoVanPhongPham.setHorizontalAlignment(SwingConstants.CENTER);
		lblTongSoVanPhongPham.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTongSoVanPhongPham.setBounds(0, 0, 263, 37);
		pnlTongSoLuongVanPhongPham.add(lblTongSoVanPhongPham);

		lblValueSoLuongVPP = new JLabel("New label");
		lblValueSoLuongVPP.setHorizontalAlignment(SwingConstants.CENTER);
		lblValueSoLuongVPP.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblValueSoLuongVPP.setBounds(0, 48, 263, 86);
		pnlTongSoLuongVanPhongPham.add(lblValueSoLuongVPP);

		pnlTongSoSachLoi = new JPanel();
		pnlTongSoSachLoi.setBounds(925, 187, 263, 133);
		pnlThongKeSanPham.add(pnlTongSoSachLoi);
		pnlTongSoSachLoi.setLayout(null);

		lblTongSoSachLoi = new JLabel("    Tổng số sách lỗi: ");
		lblTongSoSachLoi.setIcon(new ImageIcon(Pnl_ThongKeQuanLy.class.getResource("/gui/icon/sachloi.png")));
		lblTongSoSachLoi.setHorizontalAlignment(SwingConstants.CENTER);
		lblTongSoSachLoi.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTongSoSachLoi.setBounds(0, 0, 263, 40);
		pnlTongSoSachLoi.add(lblTongSoSachLoi);

		lblValueSoLuongSachLoi = new JLabel("New label");
		lblValueSoLuongSachLoi.setHorizontalAlignment(SwingConstants.CENTER);
		lblValueSoLuongSachLoi.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblValueSoLuongSachLoi.setBounds(0, 47, 263, 86);
		pnlTongSoSachLoi.add(lblValueSoLuongSachLoi);

		pnlLocSPBanChay = new JPanel();
		pnlLocSPBanChay.setBackground(new Color(173, 255, 47));
		pnlLocSPBanChay.setBounds(155, 23, 551, 523);
		pnlThongKeSanPham.add(pnlLocSPBanChay);
		pnlLocSPBanChay.setLayout(null);

		pnlSachBanChayNhat = new JPanel();
		pnlSachBanChayNhat.setBounds(28, 297, 489, 201);
		pnlLocSPBanChay.add(pnlSachBanChayNhat);
		pnlSachBanChayNhat.setLayout(null);

		lblSanPhamBanChayNhat = new JLabel("    Sản phẩm bán chạy nhất: ");
		lblSanPhamBanChayNhat.setIcon(new ImageIcon(Pnl_ThongKeQuanLy.class.getResource("/gui/icon/sachchay.png")));
		lblSanPhamBanChayNhat.setHorizontalAlignment(SwingConstants.CENTER);
		lblSanPhamBanChayNhat.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSanPhamBanChayNhat.setBounds(0, 0, 489, 42);
		pnlSachBanChayNhat.add(lblSanPhamBanChayNhat);

		lblMaSanPham = new JLabel("Mã sản phẩm:");
		lblMaSanPham.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMaSanPham.setBounds(204, 53, 87, 14);
		pnlSachBanChayNhat.add(lblMaSanPham);

		lblLoaiSanPham = new JLabel("Loại sản phẩm: ");
		lblLoaiSanPham.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLoaiSanPham.setBounds(204, 78, 87, 14);
		pnlSachBanChayNhat.add(lblLoaiSanPham);

		lblTenSanPham = new JLabel("Tên sản phẩm: ");
		lblTenSanPham.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTenSanPham.setBounds(204, 103, 87, 14);
		pnlSachBanChayNhat.add(lblTenSanPham);

		lblGiaBan = new JLabel("Giá bán: ");
		lblGiaBan.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblGiaBan.setBounds(203, 128, 72, 14);
		pnlSachBanChayNhat.add(lblGiaBan);

		lblSoLuongDaBan = new JLabel("Số lượng đã bán: ");
		lblSoLuongDaBan.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSoLuongDaBan.setBounds(204, 153, 104, 14);
		pnlSachBanChayNhat.add(lblSoLuongDaBan);

		pnlHinhAnh = new JPanel();
		pnlHinhAnh.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlHinhAnh.setBounds(10, 44, 159, 146);
		pnlSachBanChayNhat.add(pnlHinhAnh);
		pnlHinhAnh.setLayout(null);

		lblHinhAnh = new JLabel("Hình ảnh");
		lblHinhAnh.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblHinhAnh.setHorizontalAlignment(SwingConstants.CENTER);
		lblHinhAnh.setBounds(0, 0, 159, 146);
		pnlHinhAnh.add(lblHinhAnh);

		lblMaSPTop1 = new JLabel("...");
		lblMaSPTop1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMaSPTop1.setBounds(311, 53, 124, 14);
		pnlSachBanChayNhat.add(lblMaSPTop1);

		lblLoaiSPTop1 = new JLabel("...");
		lblLoaiSPTop1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLoaiSPTop1.setBounds(311, 78, 124, 14);
		pnlSachBanChayNhat.add(lblLoaiSPTop1);

		lblTenSPTop1 = new JLabel("...");
		lblTenSPTop1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTenSPTop1.setBounds(311, 103, 168, 14);
		pnlSachBanChayNhat.add(lblTenSPTop1);

		lblGiaSPTop1 = new JLabel("...");
		lblGiaSPTop1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblGiaSPTop1.setBounds(311, 128, 124, 14);
		pnlSachBanChayNhat.add(lblGiaSPTop1);

		lblSoLuongSPTop1 = new JLabel("...");
		lblSoLuongSPTop1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSoLuongSPTop1.setBounds(311, 153, 124, 14);
		pnlSachBanChayNhat.add(lblSoLuongSPTop1);

		btnLocSanPham = new JButton("Lọc");
		btnLocSanPham.setBounds(157, 237, 92, 35);
		pnlLocSPBanChay.add(btnLocSanPham);
		btnLocSanPham.setHorizontalAlignment(SwingConstants.LEFT);
		btnLocSanPham.setIcon(new ImageIcon(Pnl_ThongKeQuanLy.class.getResource("/gui/icon/filter.png")));
		btnLocSanPham.setFont(new Font("Tahoma", Font.BOLD, 13));

		lblFromThongKeSP = new JLabel("Từ: ");
		lblFromThongKeSP.setBounds(41, 104, 35, 14);
		pnlLocSPBanChay.add(lblFromThongKeSP);
		lblFromThongKeSP.setFont(new Font("Tahoma", Font.BOLD, 13));

		dateChooserFromThongKeSP = new JDateChooser();
		dateChooserFromThongKeSP.setBounds(157, 98, 164, 33);
		pnlLocSPBanChay.add(dateChooserFromThongKeSP);

		dateChooserToThongKeSP = new JDateChooser();
		dateChooserToThongKeSP.setBounds(157, 171, 164, 33);
		pnlLocSPBanChay.add(dateChooserToThongKeSP);

		lblToThongKeSP = new JLabel("Đến: ");
		lblToThongKeSP.setBounds(42, 180, 46, 14);
		pnlLocSPBanChay.add(lblToThongKeSP);
		lblToThongKeSP.setFont(new Font("Tahoma", Font.BOLD, 13));

		lblBoLocSPBanChay = new JLabel("Sản phẩm bán chạy nhất");
		lblBoLocSPBanChay.setHorizontalAlignment(SwingConstants.CENTER);
		lblBoLocSPBanChay.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBoLocSPBanChay.setForeground(new Color(255, 0, 0));
		lblBoLocSPBanChay.setBackground(new Color(255, 0, 0));
		lblBoLocSPBanChay.setBounds(0, 11, 551, 35);
		pnlLocSPBanChay.add(lblBoLocSPBanChay);

		pnlThongKeDoanhThuBanThan = new JPanel();
		pnlThongKeDoanhThuBanThan.setBackground(new Color(0, 206, 209));
		tabThongKe.addTab("Thống kê doanh thu bản thân", null, pnlThongKeDoanhThuBanThan, null);
		pnlThongKeDoanhThuBanThan.setLayout(null);

		chartPnl = new ChartPanel(createChart());
		chartPnl.setBackground(new Color(0, 206, 209));
		chartPnl.setBounds(359, 33, 1065, 490);
		pnlThongKeDoanhThuBanThan.add(chartPnl);
		lblTongDoanhThu = new JLabel("Tổng doanh thu: ");
		lblTongDoanhThu.setIcon(new ImageIcon(Pnl_ThongKeNhanVien.class.getResource("/gui/icon/count.png")));
		lblTongDoanhThu.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTongDoanhThu.setBounds(44, 316, 209, 28);
		pnlThongKeDoanhThuBanThan.add(lblTongDoanhThu);

		lblTongHoaDonBanDuoc = new JLabel("Tổng hóa đơn bán được: ");
		lblTongHoaDonBanDuoc.setHorizontalAlignment(SwingConstants.CENTER);
		lblTongHoaDonBanDuoc.setIcon(new ImageIcon(Pnl_ThongKeNhanVien.class.getResource("/gui/icon/money.png")));
		lblTongHoaDonBanDuoc.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTongHoaDonBanDuoc.setBounds(32, 423, 254, 28);
		pnlThongKeDoanhThuBanThan.add(lblTongHoaDonBanDuoc);

		lblTo = new JLabel("Đến:  ");
		lblTo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTo.setBounds(32, 127, 72, 28);
		pnlThongKeDoanhThuBanThan.add(lblTo);

		lblFrom = new JLabel("Từ:  ");
		lblFrom.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblFrom.setBounds(32, 44, 64, 28);
		pnlThongKeDoanhThuBanThan.add(lblFrom);

		dateChooserFromDoanhThu = new JDateChooser();
		dateChooserFromDoanhThu.setBounds(106, 56, 169, 20);
		pnlThongKeDoanhThuBanThan.add(dateChooserFromDoanhThu);

		dateChooserToDoanhThu = new JDateChooser();
		dateChooserToDoanhThu.setBounds(106, 135, 169, 20);
		pnlThongKeDoanhThuBanThan.add(dateChooserToDoanhThu);

		cmbTieuChiDoanhThu = new JComboBox();
		cmbTieuChiDoanhThu.setBounds(32, 208, 154, 35);
		cmbTieuChiDoanhThu.addItem("3 tháng gần nhất");
		cmbTieuChiDoanhThu.addItem("6 tháng gần nhất");
		cmbTieuChiDoanhThu.addItem("9 tháng gần nhất");
		pnlThongKeDoanhThuBanThan.add(cmbTieuChiDoanhThu);

		btnLocDoanhThuNV = new JButton("Lọc");
		btnLocDoanhThuNV.setIcon(new ImageIcon(Pnl_ThongKeNhanVien.class.getResource("/gui/icon/filter.png")));
		btnLocDoanhThuNV.setBounds(210, 208, 89, 35);
		pnlThongKeDoanhThuBanThan.add(btnLocDoanhThuNV);

		lblGiaTriDoanhThu = new JLabel("0");
		lblGiaTriDoanhThu.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGiaTriDoanhThu.setBounds(44, 373, 231, 28);
		pnlThongKeDoanhThuBanThan.add(lblGiaTriDoanhThu);

		lblGiaTriTongHoaDon = new JLabel("0");
		lblGiaTriTongHoaDon.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGiaTriTongHoaDon.setBounds(44, 487, 209, 28);
		pnlThongKeDoanhThuBanThan.add(lblGiaTriTongHoaDon);

		txtTieuDe = new JLabel("THỐNG KÊ");
		txtTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		txtTieuDe.setForeground(new Color(0, 191, 255));
		txtTieuDe.setFont(new Font("Tahoma", Font.BOLD, 26));
		txtTieuDe.setBounds(0, 11, 1500, 51);
		add(txtTieuDe);

		btnLocDoanhThuNV.addActionListener(this);
		btnLocSanPham.addActionListener(this);

		WinLogin dangNhap = new WinLogin();
		TaiKhoan taiKhoan = dangNhap.getTaiKhoanDangNhapThanhCong();
		nhanVienServiceImpl = new NhanVienServiceImpl();
		nv = new NhanVien();
		nv = nhanVienServiceImpl.timNhanVienTheoMa(taiKhoan.getNhanVien().getMaNhanVien());
		tenNV = nv.getHoTenNhanVien();

		maNV = (nhanVienServiceImpl.timNhanVienTheoTen(tenNV)).getMaNhanVien();
		setChart();
		cmbTieuChiDoanhThu.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				setBieuDoVe0();
				setChart();
			}
		});
		// System.out.println(maNV);
		sanPhamServiceImpl = new SanPhamServiceImpl();
		try {
			if (sanPhamServiceImpl.getSoLuongSachLoi() > 0) {
				lblValueSoLuongSachLoi.setText(String.valueOf(sanPhamServiceImpl.getSoLuongSachLoi()));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			if (sanPhamServiceImpl.getSoLuongSachTon() > 0) {
				lblValueSoLuongSach.setText(String.valueOf(sanPhamServiceImpl.getSoLuongSachTon()));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			if (sanPhamServiceImpl.getSoLuongVPPTon() > 0) {
				lblValueSoLuongVPP.setText(String.valueOf(sanPhamServiceImpl.getSoLuongVPPTon()));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

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
		Object obj = e.getSource();
		if (obj.equals(btnLocSanPham)) {
			sanPhamServiceImpl = new SanPhamServiceImpl();
			if (getNgayFromJDateChooser(dateChooserFromThongKeSP) != null
					&& getNgayFromJDateChooser(dateChooserToThongKeSP) != null) {
				if (sanPhamServiceImpl.getSanPhamBanNhieuNhatTheoNgayTuChon(getNgayFromJDateChooser(dateChooserFromThongKeSP),
						getNgayFromJDateChooser(dateChooserToThongKeSP)) != null) {
					dsSanPham = sanPhamServiceImpl.getSanPhamBanNhieuNhatTheoNgayTuChon(
							getNgayFromJDateChooser(dateChooserFromThongKeSP),
							getNgayFromJDateChooser(dateChooserToThongKeSP));
					for (SanPham sp : dsSanPham) {
						sanPhamServiceImpl = new SanPhamServiceImpl();
						try {
							lblMaSPTop1.setText(sanPhamServiceImpl.timSanPhamTheoMa(sp.getMaSanPham()).getMaSanPham());
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						try {
							lblLoaiSPTop1.setText(sanPhamServiceImpl.timSanPhamTheoMa(sp.getMaSanPham()).getLoaiSanPham());
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						try {
							lblGiaSPTop1.setText(String
									.valueOf(sanPhamServiceImpl.timSanPhamTheoMa(sp.getMaSanPham()).getGiaNhap()
											+ sanPhamServiceImpl.timSanPhamTheoMa(sp.getMaSanPham()).getGiaNhap() * 10 / 100)
									+ "đ");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						try {
							lblSoLuongSPTop1.setText(String.valueOf(sanPhamServiceImpl.getSoLuongBanCuaSanPhamChayNhat(
									getNgayFromJDateChooser(dateChooserFromThongKeSP),
									getNgayFromJDateChooser(dateChooserToThongKeSP))));
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						try {
							if (sanPhamServiceImpl.timSanPhamTheoMa(sp.getMaSanPham()).getLoaiSanPham().equals("Sách")) {
								// System.out.println("dc");
								lblTenSPTop1.setText(sanPhamServiceImpl.getSachTheoMaSP(sp.getMaSanPham()).getTenSach());
							} else if (sanPhamServiceImpl.timSanPhamTheoMa(sp.getMaSanPham()).getLoaiSanPham()
									.equals("Văn phòng phẩm")) {
								// System.out.println("dc");
								lblTenSPTop1.setText(sanPhamServiceImpl.getVPPTheoMaSP(sp.getMaSanPham()).getTenVanPhongPham());
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
				}
			}
		}
		if (obj.equals(btnLocDoanhThuNV)) {
			hoaDonServiceImpl = new HoaDonServiceImpl();
			nhanVienServiceImpl = new NhanVienServiceImpl();
			if (getNgayFromJDateChooser(dateChooserFromDoanhThu) != null
					&& getNgayFromJDateChooser(dateChooserToDoanhThu) != null) {

				try {
					if (hoaDonServiceImpl.getDoanhThuTheoMaNhanVien(getNgayFromJDateChooser(dateChooserFromDoanhThu),
							getNgayFromJDateChooser(dateChooserToDoanhThu),
							((NhanVien) nhanVienServiceImpl.timNhanVienTheoTen(tenNV)).getMaNhanVien()) > 0) {
						lblGiaTriDoanhThu.setText(String.valueOf(
								hoaDonServiceImpl.getDoanhThuTheoMaNhanVien(getNgayFromJDateChooser(dateChooserFromDoanhThu),
										getNgayFromJDateChooser(dateChooserToDoanhThu),
										((NhanVien) nhanVienServiceImpl.timNhanVienTheoTen(tenNV)).getMaNhanVien()))
								+ "đ");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					if (hoaDonServiceImpl.getSoLuongHoaDonTheoMaNV(getNgayFromJDateChooser(dateChooserFromDoanhThu),
							getNgayFromJDateChooser(dateChooserToDoanhThu),
							((NhanVien) nhanVienServiceImpl.timNhanVienTheoTen(tenNV)).getMaNhanVien()) > 0) {
						lblGiaTriTongHoaDon.setText(String.valueOf(
								hoaDonServiceImpl.getSoLuongHoaDonTheoMaNV(getNgayFromJDateChooser(dateChooserFromDoanhThu),
										getNgayFromJDateChooser(dateChooserToDoanhThu),
										((NhanVien) nhanVienServiceImpl.timNhanVienTheoTen(tenNV)).getMaNhanVien())));
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

	public LocalDate getNgayFromJDateChooser(JDateChooser ngay) {
		if (ngay.getDate() == null) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày");
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dayKT = sdf.format(ngay.getDate());
		String dateKT[] = dayKT.split("-");
		int namKT = Integer.parseInt(dateKT[0]);
		int thangKT = Integer.parseInt(dateKT[1]);
		int ngayKT = Integer.parseInt(dateKT[2]);
		LocalDate lcDateKT = LocalDate.of(namKT, thangKT, ngayKT);
		return lcDateKT;
	}

	public LocalDate getNgayHienTai() {
		LocalDate lt = LocalDate.now();
		return lt;
	}

	public void setChart() {
		int count = 0;
		hoaDonServiceImpl = new HoaDonServiceImpl();
		LocalDate nowMinus = null;
		LocalDate now = getNgayHienTai();
		if (cmbTieuChiDoanhThu.getSelectedIndex() == 0) {
			count = 3;
			while (count > 0) {
				nowMinus = now.minusMonths(1);
				try {
					dataset.setValue(hoaDonServiceImpl.getDoanhThuTheoMaNhanVien(nowMinus, now, maNV), "Doanh thu",
							String.valueOf(nowMinus.getMonthValue()));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				now = nowMinus;
				count--;
			}
		}

		else if (cmbTieuChiDoanhThu.getSelectedIndex() == 1) {
			count = 6;
			while (count > 0) {
				nowMinus = now.minusMonths(1);
				try {
					dataset.setValue(hoaDonServiceImpl.getDoanhThuTheoMaNhanVien(nowMinus, now, maNV), "Doanh thu",
							String.valueOf(nowMinus.getMonthValue()));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				now = nowMinus;
				count--;
			}
		}

		else {
			count = 9;
			while (count > 0) {
				nowMinus = now.minusMonths(1);
				try {
					dataset.setValue(hoaDonServiceImpl.getDoanhThuTheoMaNhanVien(nowMinus, now, maNV), "Doanh thu",
							String.valueOf(nowMinus.getMonthValue()));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				now = nowMinus;
				count--;
			}
		}

	}

	public void setBieuDoVe0() {
		dataset.setValue(0, "Doanh thu", "1");
		dataset.setValue(0, "Doanh thu", "2");
		dataset.setValue(0, "Doanh thu", "3");
		dataset.setValue(0, "Doanh thu", "4");
		dataset.setValue(0, "Doanh thu", "5");
		dataset.setValue(0, "Doanh thu", "6");
		dataset.setValue(0, "Doanh thu", "7");
		dataset.setValue(0, "Doanh thu", "8");
		dataset.setValue(0, "Doanh thu", "9");
		dataset.setValue(0, "Doanh thu", "10");
		dataset.setValue(0, "Doanh thu", "11");
		dataset.setValue(0, "Doanh thu", "12");
	}
}
