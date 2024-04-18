package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import entity.Sach;
import entity.VanPhongPham;
import service.impl.SanPhamServiceImpl;

public class WinXemChiTiet extends JFrame implements ActionListener {

	// JLabels
	private JLabel lblDonVi;
	private JLabel lblDonViSpChiTiet;
	private JLabel lblGhiChuMoTa;
	private JLabel lblGiaNhap;
	private JLabel lblGiaNhapChiTiet;
	private JLabel lblImgSP;
	private JLabel lblLoai;
	private JLabel lblMaSP;
	private JLabel lblMauSac;
	private JLabel lblNhaCungCap;
	private JLabel lblNhaCungCapChiTiet;
	private JLabel lblNhaXBOrXuatXu;
	private JLabel lblNamXuatBan;
	private JLabel lblNamXB;
	private JLabel lblProductName;
	private JLabel lblQuantityCount;
	private JLabel lblSoLuong;
	private JLabel lblSoTrang;
	private JLabel lblSoTrangValue;
	private JLabel lblTacGia;
	private JLabel lblTacGiaOrChatLieu;
	private JLabel lblTenSp;
	private JLabel lblTheLoai;
	private JLabel lblTrongLuong;
	private JLabel lblTrongLuongChiTiet;
	private JLabel lblNXB;

	// JPanel
	private JPanel pnlButton;

	// JButton
	private JButton btnThoat;

	// JTextArea
	private JTextArea txaGhiChu;

	// String
	String loaiSanPham;
	String maSanPham;

	// Sach
	Sach sach;

	// VanPhongPham
	VanPhongPham vanPhongPham;

	// SanPhamServiceImpl
	SanPhamServiceImpl sanPhamServiceImpl = new SanPhamServiceImpl();

	@SuppressWarnings("deprecation")
	public WinXemChiTiet (String maSanPham, String loaiSanPham) {
		this.loaiSanPham = loaiSanPham;
		this.maSanPham = maSanPham;
		setTitle("Thêm Sản Phẩm");
		setResizable(false);
		setSize(800, 700);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JLabel lblMaSanPham = new JLabel("Mã Sản Phẩm:");
		lblMaSanPham.setForeground(new Color(72, 61, 139));
		lblMaSanPham.setFont(new Font("Arial", Font.BOLD, 16));
		lblMaSanPham.setBounds(10, 89, 116, 23);
		getContentPane().add(lblMaSanPham);

		lblLoai = new JLabel();
		lblLoai.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLoai.setBackground(new Color(255, 255, 255));
		lblLoai.setBounds(125, 159, 240, 33);
		getContentPane().add(lblLoai);

		lblTacGiaOrChatLieu = new JLabel();
		lblTacGiaOrChatLieu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTacGiaOrChatLieu.setBackground(Color.WHITE);
		lblTacGiaOrChatLieu.setBounds(125, 202, 240, 33);
		getContentPane().add(lblTacGiaOrChatLieu);

		lblNhaXBOrXuatXu = new JLabel();
		lblNhaXBOrXuatXu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNhaXBOrXuatXu.setBackground(Color.WHITE);
		lblNhaXBOrXuatXu.setBounds(125, 245, 240, 33);
		getContentPane().add(lblNhaXBOrXuatXu);

		lblNhaCungCap = new JLabel();
		lblNhaCungCap.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNhaCungCap.setBackground(Color.WHITE);
		lblNhaCungCap.setBounds(125, 364, 328, 33);
		getContentPane().add(lblNhaCungCap);

		lblMauSac = new JLabel();
		lblMauSac.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMauSac.setBackground(Color.WHITE);
		lblMauSac.setBounds(125, 288, 240, 33);
		getContentPane().add(lblMauSac);
		lblMauSac.hide();

		JPanel panel = new JPanel();
		panel.setBounds(10, 38, 285, 46);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblChiTietSanPham = new JLabel("CHI TIẾT SẢN PHẨM");
		lblChiTietSanPham.setVerticalAlignment(SwingConstants.TOP);
		lblChiTietSanPham.setHorizontalAlignment(SwingConstants.CENTER);
		lblChiTietSanPham.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblChiTietSanPham.setBounds(10, 10, 766, 39);
		getContentPane().add(lblChiTietSanPham);

		lblMaSP = new JLabel(maSanPham);
		lblMaSP.setBorder(BorderFactory.createLineBorder(Color.cyan));
		lblMaSP.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMaSP.setBounds(125, 89, 170, 23);
		getContentPane().add(lblMaSP);

		lblTheLoai = new JLabel("Thể loại:");
		lblTheLoai.setForeground(new Color(72, 61, 139));
		lblTheLoai.setFont(new Font("Arial", Font.BOLD, 16));
		lblTheLoai.setBounds(10, 154, 116, 38);
		getContentPane().add(lblTheLoai);

		lblTacGia = new JLabel("Tác giả:");
		lblTacGia.setForeground(new Color(72, 61, 139));
		lblTacGia.setFont(new Font("Arial", Font.BOLD, 16));
		lblTacGia.setBounds(10, 197, 116, 38);
		getContentPane().add(lblTacGia);

		lblNXB = new JLabel("Nhà xuất bản:");
		lblNXB.setForeground(new Color(72, 61, 139));
		lblNXB.setFont(new Font("Arial", Font.BOLD, 16));
		lblNXB.setBounds(10, 240, 116, 38);
		getContentPane().add(lblNXB);

		lblNamXuatBan = new JLabel();
		lblNamXuatBan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNamXuatBan.setBounds(125, 514, 100, 23);
		getContentPane().add(lblNamXuatBan);

		lblNamXB = new JLabel("Năm xuất bản:");
		lblNamXB.setForeground(new Color(72, 61, 139));
		lblNamXB.setFont(new Font("Arial", Font.BOLD, 16));
		lblNamXB.setBounds(10, 514, 116, 23);
		getContentPane().add(lblNamXB);

		lblSoTrangValue = new JLabel();
		lblSoTrangValue.setFont(new Font("Tahoma", Font.PLAIN, 16));

		lblSoTrangValue.setBounds(125, 290, 170, 23);
		getContentPane().add(lblSoTrangValue);

		lblSoTrang = new JLabel("Số trang:");
		lblSoTrang.setForeground(new Color(72, 61, 139));
		lblSoTrang.setFont(new Font("Arial", Font.BOLD, 16));
		lblSoTrang.setBounds(10, 290, 116, 23);
		getContentPane().add(lblSoTrang);

		lblQuantityCount = new JLabel("Số lượng:");
		lblQuantityCount.setForeground(new Color(72, 61, 139));
		lblQuantityCount.setFont(new Font("Arial", Font.BOLD, 16));
		lblQuantityCount.setBounds(10, 331, 116, 23);
		getContentPane().add(lblQuantityCount);

		lblSoLuong = new JLabel();
		lblSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 16));

		lblSoLuong.setBounds(125, 331, 170, 23);
		getContentPane().add(lblSoLuong);

		lblImgSP = new JLabel("");
		lblImgSP.setHorizontalAlignment(SwingConstants.CENTER);
		lblImgSP.setIcon(new ImageIcon(WinThemSP.class.getResource("/gui/icon/user.png")));
		lblImgSP.setBounds(472, 77, 224, 245);
		lblImgSP.setBorder(BorderFactory.createLineBorder(Color.black));
		getContentPane().add(lblImgSP);

		lblProductName = new JLabel("Tên sản phẩm:");
		lblProductName.setForeground(new Color(72, 61, 139));
		lblProductName.setFont(new Font("Arial", Font.BOLD, 16));
		lblProductName.setBounds(10, 122, 116, 23);
		getContentPane().add(lblProductName);

		lblTenSp = new JLabel();
		lblTenSp.setFont(new Font("Tahoma", Font.PLAIN, 16));

		lblTenSp.setBounds(125, 122, 328, 23);
		getContentPane().add(lblTenSp);

		lblNhaCungCapChiTiet = new JLabel("Nhà cung cấp:");
		lblNhaCungCapChiTiet.setForeground(new Color(72, 61, 139));
		lblNhaCungCapChiTiet.setFont(new Font("Arial", Font.BOLD, 16));
		lblNhaCungCapChiTiet.setBounds(10, 359, 116, 38);
		getContentPane().add(lblNhaCungCapChiTiet);

		lblGiaNhapChiTiet = new JLabel("Giá nhập:");
		lblGiaNhapChiTiet.setForeground(new Color(72, 61, 139));
		lblGiaNhapChiTiet.setFont(new Font("Arial", Font.BOLD, 16));
		lblGiaNhapChiTiet.setBounds(10, 405, 116, 23);
		getContentPane().add(lblGiaNhapChiTiet);

		lblGiaNhap = new JLabel();
		lblGiaNhap.setFont(new Font("Tahoma", Font.PLAIN, 16));

		lblGiaNhap.setBounds(125, 405, 170, 23);
		getContentPane().add(lblGiaNhap);

		lblDonViSpChiTiet = new JLabel("Đơn vị sp:");
		lblDonViSpChiTiet.setForeground(new Color(72, 61, 139));
		lblDonViSpChiTiet.setFont(new Font("Arial", Font.BOLD, 16));
		lblDonViSpChiTiet.setBounds(10, 438, 116, 23);
		getContentPane().add(lblDonViSpChiTiet);

		lblTrongLuongChiTiet = new JLabel("Trọng lượng:");
		lblTrongLuongChiTiet.setForeground(new Color(72, 61, 139));
		lblTrongLuongChiTiet.setFont(new Font("Arial", Font.BOLD, 16));
		lblTrongLuongChiTiet.setBounds(10, 481, 116, 23);
		getContentPane().add(lblTrongLuongChiTiet);

		lblTrongLuong = new JLabel();
		lblTrongLuong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTrongLuong.setBounds(125, 480, 170, 23);
		getContentPane().add(lblTrongLuong);

		pnlButton = new JPanel();
		pnlButton.setBounds(10, 558, 766, 54);
		getContentPane().add(pnlButton);
		pnlButton.setLayout(null);

		btnThoat = new JButton("Thoát");
		btnThoat.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnThoat.setBounds(316, 10, 132, 39);
		pnlButton.add(btnThoat);

		txaGhiChu = new JTextArea();
		txaGhiChu.setFont(new Font("Courier New", Font.PLAIN, 13));
		txaGhiChu.setBounds(491, 400, 285, 158);
		txaGhiChu.setBorder(BorderFactory.createLineBorder(Color.black));
		getContentPane().add(txaGhiChu);

		lblGhiChuMoTa = new JLabel("Ghi chú(Mô tả):");
		lblGhiChuMoTa.setForeground(new Color(72, 61, 139));
		lblGhiChuMoTa.setFont(new Font("Arial", Font.BOLD, 16));
		lblGhiChuMoTa.setBounds(490, 374, 127, 23);
		getContentPane().add(lblGhiChuMoTa);

		lblDonVi = new JLabel();
		lblDonVi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDonVi.setBackground(Color.WHITE);
		lblDonVi.setBounds(125, 438, 240, 33);
		getContentPane().add(lblDonVi);
		txaGhiChu.setEditable(false);

		if (loaiSanPham.equals("Sách")) {
			try {
				setRadSach();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				setRadVPP();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		btnThoat.addActionListener(this);
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

	@SuppressWarnings("deprecation")
	public void setRadSach() throws Exception {
		sach = sanPhamServiceImpl.timSanPhamTheoMaSach(maSanPham);
		lblLoai.setText(sach.getTheLoaiSach().getTenLoai());
		lblTacGiaOrChatLieu.setText(sach.getTacGia().getTenTacGia());
		lblNhaXBOrXuatXu.setText(sach.getNhaXuatBan().getTenNXB());
		lblTenSp.setText(sach.getTenSach());
		lblNhaCungCap.setText(sach.getNhaCungCap().getTenNCC());
		lblSoTrang.setText("Số trang: ");
		lblSoTrangValue.setText(sach.getSoTrang() + "");
		ImageIcon icon = new ImageIcon("..\\HieuSachTuNhan\\hinhAnhHieuSach\\bookUnknow.jpg");
		if (new File("..\\HieuSachTuNhan\\hinhAnhHieuSach\\" + sach.getHinhAnh()).exists()) {
			icon = new ImageIcon("..\\HieuSachTuNhan\\hinhAnhHieuSach\\" + sach.getHinhAnh());
		}
		lblImgSP.setIcon(setSizeImageIcon(icon, lblImgSP.getWidth(), lblImgSP.getHeight()));
		lblNamXB.show();
		lblNamXuatBan.show();
		lblNamXuatBan.setText(sach.getNamXuatBan() + "");
		lblMauSac.hide();
		lblDonVi.setText(sach.getDonViSanPham());
		;
		lblGiaNhap.setText(sach.getGiaNhap() + " VNĐ");
		lblSoLuong.setText(sach.getSoLuongTon() + " " + sach.getDonViSanPham());
		lblTrongLuong.setText(sach.getTrongLuong() + "");
		txaGhiChu.setText(sach.getGhiChu());
	}

	@SuppressWarnings("deprecation")
	public void setRadVPP() throws Exception {
		vanPhongPham = sanPhamServiceImpl.timSanPhamTheoMaVPP(maSanPham);
		lblLoai.setText(vanPhongPham.getLoaiVanPhongPham().getTenLoai());
		lblTacGiaOrChatLieu.setText(vanPhongPham.getChatLieu().getTenChatLieu());
		lblNhaXBOrXuatXu.setText(vanPhongPham.getXuatXu().getTenXuatXu());
		lblTenSp.setText(vanPhongPham.getTenVanPhongPham());
		lblNhaCungCap.setText(vanPhongPham.getNhaCungCap().getTenNCC());
		lblSoTrang.setText("Màu sắc");
		lblMauSac.setText(vanPhongPham.getMauSac().getTenMau());
		lblDonVi.setText(vanPhongPham.getDonViSanPham());
		ImageIcon icon = new ImageIcon("..\\HieuSachTuNhan\\hinhAnhHieuSach\\bookUnknow.jpg");
		if (new File("..\\HieuSachTuNhan\\hinhAnhHieuSach\\" + vanPhongPham.getHinhAnh()).exists()) {
			icon = new ImageIcon("..\\HieuSachTuNhan\\hinhAnhHieuSach\\" + vanPhongPham.getHinhAnh());
		}
		lblImgSP.setIcon(setSizeImageIcon(icon, lblImgSP.getWidth(), lblImgSP.getHeight()));

		lblMauSac.show();
		lblNamXB.hide();
		lblSoTrangValue.hide();
		lblNamXuatBan.hide();
		lblGiaNhap.setText(vanPhongPham.getGiaNhap() + " VNĐ");
		lblSoLuong.setText(vanPhongPham.getSoLuongTon() + " " + vanPhongPham.getDonViSanPham());
		lblTrongLuong.setText(vanPhongPham.getTrongLuong() + "");
		txaGhiChu.setText(vanPhongPham.getGhiChu());
	}

	public ImageIcon setSizeImageIcon(ImageIcon icon, int width, int height) {
		ImageIcon image = icon;
		Image imageSet = image.getImage();
		imageSet = imageSet.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		image = new ImageIcon(imageSet);
		return image;
	}

}
