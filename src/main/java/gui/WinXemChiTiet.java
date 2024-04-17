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

import entities.Sach;

import entities.VanPhongPham;

import service.impl.SanPhamServiceImpl;

public class WinXemChiTiet extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblLoai;
	private JLabel lblTacGiaOrChatLieu;
	private JLabel lblNhaXBorXuatXu;
	private JLabel lblNhaCungCap;
	private JLabel lblMauSac;
	private JLabel lblMasp;
	private JLabel lblNewLabel_2;
	private JLabel lblTacGia;
	private JLabel lblNXB;
	private JLabel lblNamXB;
	private JLabel lblSoTrang;
	private JLabel lblChooserNamXB;
	private JLabel lblNewLabel_7;
	private JLabel lblSoLuong;
	private JLabel lblImgSP;
	private JLabel lblNewLabel_9;
	private JLabel lblTenSp;
	private JLabel lblNewLabel_10;
	private JLabel lblNewLabel_11;
	private JLabel lblGiaNhap;
	private JLabel lblNewLabel_12;
	private JLabel lblNewLabel_13;
	private JLabel lblTrongLuong;
	private JButton btnThoat;
	private SanPhamServiceImpl sanPhamServiceImpl;
	private JTextArea txaGhiChu;
	private JLabel lblDonVi;
	private String loaiSanPham;
	private Sach sach;
	private VanPhongPham vanPhongPham;
	private String maSanPham;

	@SuppressWarnings("deprecation")
	public WinXemChiTiet(String maSanPham, String loaiSanPham) {
		this.loaiSanPham = loaiSanPham;
		this.maSanPham = maSanPham;
		setTitle("Thêm Sản Phẩm");
		setResizable(false);
		setSize(800, 700);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Mã Sản Phẩm:");
		lblNewLabel_1.setForeground(new Color(72, 61, 139));
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_1.setBounds(10, 89, 116, 23);
		getContentPane().add(lblNewLabel_1);

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

		lblNhaXBorXuatXu = new JLabel();
		lblNhaXBorXuatXu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNhaXBorXuatXu.setBackground(Color.WHITE);
		lblNhaXBorXuatXu.setBounds(125, 245, 240, 33);
		getContentPane().add(lblNhaXBorXuatXu);

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

		JLabel lblNewLabel = new JLabel("CHI TIẾT SẢN PHẨM");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabel.setBounds(10, 10, 766, 39);
		getContentPane().add(lblNewLabel);

		lblMasp = new JLabel(maSanPham);
		lblMasp.setBorder(BorderFactory.createLineBorder(Color.cyan));
		lblMasp.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMasp.setBounds(125, 89, 170, 23);
		getContentPane().add(lblMasp);

		lblNewLabel_2 = new JLabel("Thể loại:");
		lblNewLabel_2.setForeground(new Color(72, 61, 139));
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_2.setBounds(10, 154, 116, 38);
		getContentPane().add(lblNewLabel_2);

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

		lblChooserNamXB = new JLabel();
		lblChooserNamXB.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblChooserNamXB.setBounds(125, 514, 100, 23);
		getContentPane().add(lblChooserNamXB);

		lblNamXB = new JLabel("Năm xuất bản:");
		lblNamXB.setForeground(new Color(72, 61, 139));
		lblNamXB.setFont(new Font("Arial", Font.BOLD, 16));
		lblNamXB.setBounds(10, 514, 116, 23);
		getContentPane().add(lblNamXB);

		lblSoTrang = new JLabel();
		lblSoTrang.setFont(new Font("Tahoma", Font.PLAIN, 16));

		lblSoTrang.setBounds(125, 290, 170, 23);
		getContentPane().add(lblSoTrang);

		lblSoTrang = new JLabel("Số trang:");
		lblSoTrang.setForeground(new Color(72, 61, 139));
		lblSoTrang.setFont(new Font("Arial", Font.BOLD, 16));
		lblSoTrang.setBounds(10, 290, 116, 23);
		getContentPane().add(lblSoTrang);

		lblNewLabel_7 = new JLabel("Số lượng:");
		lblNewLabel_7.setForeground(new Color(72, 61, 139));
		lblNewLabel_7.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_7.setBounds(10, 331, 116, 23);
		getContentPane().add(lblNewLabel_7);

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

		lblNewLabel_9 = new JLabel("Tên sản phẩm:");
		lblNewLabel_9.setForeground(new Color(72, 61, 139));
		lblNewLabel_9.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_9.setBounds(10, 122, 116, 23);
		getContentPane().add(lblNewLabel_9);

		lblTenSp = new JLabel();
		lblTenSp.setFont(new Font("Tahoma", Font.PLAIN, 16));

		lblTenSp.setBounds(125, 122, 328, 23);
		getContentPane().add(lblTenSp);

		lblNewLabel_10 = new JLabel("Nhà cung cấp:");
		lblNewLabel_10.setForeground(new Color(72, 61, 139));
		lblNewLabel_10.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_10.setBounds(10, 359, 116, 38);
		getContentPane().add(lblNewLabel_10);

		lblNewLabel_11 = new JLabel("Giá nhập:");
		lblNewLabel_11.setForeground(new Color(72, 61, 139));
		lblNewLabel_11.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_11.setBounds(10, 405, 116, 23);
		getContentPane().add(lblNewLabel_11);

		lblGiaNhap = new JLabel();
		lblGiaNhap.setFont(new Font("Tahoma", Font.PLAIN, 16));

		lblGiaNhap.setBounds(125, 405, 170, 23);
		getContentPane().add(lblGiaNhap);

		lblNewLabel_12 = new JLabel("Đơn vị sp:");
		lblNewLabel_12.setForeground(new Color(72, 61, 139));
		lblNewLabel_12.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_12.setBounds(10, 438, 116, 23);
		getContentPane().add(lblNewLabel_12);

		lblNewLabel_13 = new JLabel("Trọng lượng:");
		lblNewLabel_13.setForeground(new Color(72, 61, 139));
		lblNewLabel_13.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_13.setBounds(10, 481, 116, 23);
		getContentPane().add(lblNewLabel_13);

		lblTrongLuong = new JLabel();
		lblTrongLuong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTrongLuong.setBounds(125, 480, 170, 23);
		getContentPane().add(lblTrongLuong);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 558, 766, 54);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		btnThoat = new JButton("Thoát");
		btnThoat.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnThoat.setBounds(316, 10, 132, 39);
		panel_1.add(btnThoat);

		txaGhiChu = new JTextArea();
		txaGhiChu.setFont(new Font("Courier New", Font.PLAIN, 13));
		txaGhiChu.setBounds(491, 400, 285, 158);
		txaGhiChu.setBorder(BorderFactory.createLineBorder(Color.black));
		getContentPane().add( txaGhiChu);

		JLabel lblNewLabel_6_1 = new JLabel("Ghi chú(Mô tả):");
		lblNewLabel_6_1.setForeground(new Color(72, 61, 139));
		lblNewLabel_6_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_6_1.setBounds(490, 374, 127, 23);
		getContentPane().add(lblNewLabel_6_1);

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
		lblNhaXBorXuatXu.setText(sach.getNhaXuatBan().getTenNXB());
		lblTenSp.setText(sach.getTenSach());
		lblNhaCungCap.setText(sach.getNhaCungCap().getTenNCC());
		lblSoTrang.setText("Số trang: ");
		lblSoTrang.setText(sach.getSoTrang() + "");
		ImageIcon icon = new ImageIcon("..\\HieuSachTuNhan\\hinhAnhHieuSach\\bookUnknow.jpg");
		if (new File("..\\HieuSachTuNhan\\hinhAnhHieuSach\\" + sach.getHinhAnh()).exists()) {
			icon = new ImageIcon("..\\HieuSachTuNhan\\hinhAnhHieuSach\\" + sach.getHinhAnh());
		}
		lblImgSP.setIcon(setSizeImageIcon(icon, lblImgSP.getWidth(), lblImgSP.getHeight()));
		lblNamXB.show();
		lblChooserNamXB.show();
		lblChooserNamXB.setText(sach.getNamXuatBan() + "");
		lblMauSac.hide();
		lblDonVi.setText(sach.getDonViSanPham());
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
		lblNhaXBorXuatXu.setText(vanPhongPham.getXuatXu().getTenXuatXu());
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
		lblSoTrang.hide();
		lblChooserNamXB.hide();
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
