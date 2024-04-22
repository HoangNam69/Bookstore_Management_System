package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.Naming;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.toedter.calendar.JYearChooser;

import entities.ChatLieu;
import entities.MauSac;
import entities.NhaCungCap;
import entities.NhaXuatBan;
import entities.Sach;
import entities.TacGia;
import entities.TheLoaiSach;
import entities.TheLoaiVanPhongPham;
import entities.VanPhongPham;
import entities.XuatXu;
import service.*;
import service.impl.ChatLieuServiceImpl;
import service.impl.MauSacServiceImpl;
import service.impl.NhaCungCapServiceImpl;
import service.impl.NhaXuatBanServiceImpl;
import service.impl.SanPhamServiceImpl;
import service.impl.TacGiaServiceImpl;
import service.impl.TheLoaiServiceImpl;
import service.impl.XuatXuServiceImpl;
import util.Constants;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;

public class WinThemSP extends JFrame implements ActionListener, MouseListener {

	// JComboBox
	private JComboBox<Object> cmbLoai;
	private JComboBox<Object> cmbTacGiaOrChatLieu;
	private JComboBox<Object> cmbNhaXbOrXuatXu;
	private JComboBox<String> cmbNhaCungCap;
	private JComboBox<String> cmbMauSac;
	private JComboBox<String> cmbDonVi;

	// JTextField
	private JTextField txtMasp;
	private JTextField txtSoTrang;
	private JTextField txtSoLuong;
	private JTextField txtTenSp;
	private JTextField txtGiaNhap;
	private JTextField txtTrongLuong;

	// JRadioButton
	private JRadioButton radSach;
	private JRadioButton radVPP;

	// JLabel
	private JLabel lblTacGia;
	private JLabel lblNxb;
	private JLabel lblNamXb;
	private JLabel lblSoTrang;
	private JLabel lblSoLuong;
	private JLabel lblImgSanPham;
	private JLabel lblTenSanPham;
	private JLabel lblNhaCungCap;
	private JLabel lblGiaNhap;
	private JLabel lblDonViSanPham;
	private JLabel lblTrongLuong;
	private JLabel lblTrongLuongKg;
	private JLabel lblMaSanPham;
	private JLabel lblTieuDe;
	private JLabel lblTheLoai;
	private JLabel lblGhiChuMoTa;
	
	// JPanel
	private JPanel pnlButtonGroup;
	
	// JButton
	private JButton btnChooser;
	private JButton btnLamMoi;
	private JButton btnHuy;
	private JButton btnThemSp;

	// JTextArea
	private JTextArea txaGhiChu;

	// JFileChooser
	private JFileChooser fchChonFile;

	// String
	private String hinhAnh;

	// ArrayList
	private ArrayList<TheLoaiSach> theLoaiSachs;
	private ArrayList<TacGia> tacGias;
	private ArrayList<NhaXuatBan> nhaXuatBans;
	private ArrayList<NhaCungCap> nhaCungCaps;
	private ArrayList<TheLoaiVanPhongPham> theLoaiVanPhongPhams;
	private ArrayList<ChatLieu> chatLieus;
	private ArrayList<XuatXu> xuatXus;
	private ArrayList<MauSac> mauSacs;


	private static final String URL = "rmi://"+ Constants.IPV4 + ":"+ Constants.PORT + "/";
	private SanPhamService sanPhamService = (SanPhamService) Naming.lookup(URL + "sanPham");
	private TheLoaiService theLoaiService = (TheLoaiService) Naming.lookup(URL + "theLoai");
	private TacGiaService tacGiaService = (TacGiaService) Naming.lookup(URL + "tacGia");
	private NhaXuatBanService nhaXuatBanService = (NhaXuatBanService) Naming.lookup(URL + "nhaXuatBan");
	private NhaCungCapService nhaCungCapService = (NhaCungCapService) Naming.lookup(URL + "nhaCungCap");
	private ChatLieuService chatLieuService = (ChatLieuService) Naming.lookup(URL + "chatLieu");
	private XuatXuService xuatXuService = (XuatXuService) Naming.lookup(URL + "xuatXu");
	private MauSacService mauSacService = (MauSacService) Naming.lookup(URL + "mauSac");
	// JYearChooser
	private JYearChooser chooserNamXB;

	@SuppressWarnings("deprecation")
	public WinThemSP() throws Exception {
		setTitle("Thêm Sản Phẩm");
		setResizable(false);
		setSize(800, 700);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		lblMaSanPham = new JLabel("Mã Sản Phẩm:");
		lblMaSanPham.setForeground(new Color(72, 61, 139));
		lblMaSanPham.setFont(new Font("Arial", Font.BOLD, 16));
		lblMaSanPham.setBounds(10, 89, 116, 23);
		getContentPane().add(lblMaSanPham);

		cmbLoai = new JComboBox<Object>();
		cmbLoai.setBackground(new Color(255, 255, 255));
		cmbLoai.setBounds(125, 159, 240, 33);
		getContentPane().add(cmbLoai);

		cmbTacGiaOrChatLieu = new JComboBox<Object>();
		cmbTacGiaOrChatLieu.setBackground(Color.WHITE);
		cmbTacGiaOrChatLieu.setBounds(125, 202, 240, 33);
		getContentPane().add(cmbTacGiaOrChatLieu);

		cmbNhaXbOrXuatXu = new JComboBox<Object>();
		cmbNhaXbOrXuatXu.setBackground(Color.WHITE);
		cmbNhaXbOrXuatXu.setBounds(125, 245, 240, 33);
		getContentPane().add(cmbNhaXbOrXuatXu);

		cmbNhaCungCap = new JComboBox<String>();
		cmbNhaCungCap.setBackground(Color.WHITE);
		cmbNhaCungCap.setBounds(125, 364, 328, 33);
		getContentPane().add(cmbNhaCungCap);

		cmbMauSac = new JComboBox<String>();
		cmbMauSac.setBackground(Color.WHITE);
		cmbMauSac.setBounds(125, 288, 240, 33);
		getContentPane().add(cmbMauSac);
		cmbMauSac.hide();

		JPanel panel = new JPanel();
		panel.setBounds(10, 38, 285, 46);
		getContentPane().add(panel);
		panel.setLayout(null);

		radSach = new JRadioButton("Sách");
		radSach.setSelected(true);
		radSach.setFont(new Font("Tahoma", Font.PLAIN, 15));
		radSach.setBounds(6, 6, 71, 21);
		panel.add(radSach);

		radVPP = new JRadioButton("Văn phòng phẩm");
		radVPP.setHorizontalAlignment(SwingConstants.LEFT);
		radVPP.setFont(new Font("Tahoma", Font.PLAIN, 15));
		radVPP.setBounds(79, 6, 157, 21);
		panel.add(radVPP);

		ButtonGroup group = new ButtonGroup();
		group.add(radSach);
		group.add(radVPP);

		lblTieuDe = new JLabel("THÊM SẢN PHẨM");
		lblTieuDe.setVerticalAlignment(SwingConstants.TOP);
		lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDe.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblTieuDe.setBounds(10, 10, 766, 39);
		getContentPane().add(lblTieuDe);

		txtMasp = new JTextField();
		txtMasp.setEditable(false);
		txtMasp.setBorder(BorderFactory.createLineBorder(Color.cyan));
		txtMasp.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMasp.setBounds(125, 89, 170, 23);
		getContentPane().add(txtMasp);
		txtMasp.setColumns(10);

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

		lblNxb = new JLabel("Nhà xuất bản:");
		lblNxb.setForeground(new Color(72, 61, 139));
		lblNxb.setFont(new Font("Arial", Font.BOLD, 16));
		lblNxb.setBounds(10, 240, 116, 38);
		getContentPane().add(lblNxb);

		chooserNamXB = new JYearChooser();
		chooserNamXB.setFont(new Font("Tahoma", Font.PLAIN, 16));
		chooserNamXB.setBounds(125, 514, 100, 23);
		getContentPane().add(chooserNamXB);

		lblNamXb = new JLabel("Năm xuất bản:");
		lblNamXb.setForeground(new Color(72, 61, 139));
		lblNamXb.setFont(new Font("Arial", Font.BOLD, 16));
		lblNamXb.setBounds(10, 514, 116, 23);
		getContentPane().add(lblNamXb);

		txtSoTrang = new JTextField();
		txtSoTrang.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSoTrang.setColumns(10);
		txtSoTrang.setBounds(125, 290, 170, 23);
		getContentPane().add(txtSoTrang);

		lblSoTrang = new JLabel("Số trang:");
		lblSoTrang.setForeground(new Color(72, 61, 139));
		lblSoTrang.setFont(new Font("Arial", Font.BOLD, 16));
		lblSoTrang.setBounds(10, 290, 116, 23);
		getContentPane().add(lblSoTrang);

		lblSoLuong = new JLabel("Số lượng:");
		lblSoLuong.setForeground(new Color(72, 61, 139));
		lblSoLuong.setFont(new Font("Arial", Font.BOLD, 16));
		lblSoLuong.setBounds(10, 331, 116, 23);
		getContentPane().add(lblSoLuong);

		txtSoLuong = new JTextField();
		txtSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSoLuong.setColumns(10);
		txtSoLuong.setBounds(125, 331, 170, 23);
		getContentPane().add(txtSoLuong);

		lblImgSanPham = new JLabel("");
		lblImgSanPham.setHorizontalAlignment(SwingConstants.CENTER);
		lblImgSanPham.setBounds(472, 77, 224, 245);
		lblImgSanPham.setIcon(setSizeImageIcon(new ImageIcon("..\\HieuSachTuNhan\\hinhAnhHieuSach\\bookUnknow.jpg"),
				lblImgSanPham.getWidth(), lblImgSanPham.getHeight()));
		lblImgSanPham.setBorder(BorderFactory.createLineBorder(Color.black));
		getContentPane().add(lblImgSanPham);

		btnChooser = new JButton("Chọn File");
		btnChooser.setBounds(535, 332, 109, 29);
		getContentPane().add(btnChooser);

		lblTenSanPham = new JLabel("Tên sản phẩm:");
		lblTenSanPham.setForeground(new Color(72, 61, 139));
		lblTenSanPham.setFont(new Font("Arial", Font.BOLD, 16));
		lblTenSanPham.setBounds(10, 122, 116, 23);
		getContentPane().add(lblTenSanPham);

		txtTenSp = new JTextField();
		txtTenSp.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTenSp.setColumns(10);
		txtTenSp.setBounds(125, 122, 328, 23);
		getContentPane().add(txtTenSp);

		lblNhaCungCap = new JLabel("Nhà cung cấp:");
		lblNhaCungCap.setForeground(new Color(72, 61, 139));
		lblNhaCungCap.setFont(new Font("Arial", Font.BOLD, 16));
		lblNhaCungCap.setBounds(10, 359, 116, 38);
		getContentPane().add(lblNhaCungCap);

		lblGiaNhap = new JLabel("Giá nhập:");
		lblGiaNhap.setForeground(new Color(72, 61, 139));
		lblGiaNhap.setFont(new Font("Arial", Font.BOLD, 16));
		lblGiaNhap.setBounds(10, 405, 116, 23);
		getContentPane().add(lblGiaNhap);

		txtGiaNhap = new JTextField();
		txtGiaNhap.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtGiaNhap.setColumns(10);
		txtGiaNhap.setBounds(125, 405, 170, 23);
		getContentPane().add(txtGiaNhap);

		lblDonViSanPham = new JLabel("Đơn vị sp:");
		lblDonViSanPham.setForeground(new Color(72, 61, 139));
		lblDonViSanPham.setFont(new Font("Arial", Font.BOLD, 16));
		lblDonViSanPham.setBounds(10, 438, 116, 23);
		getContentPane().add(lblDonViSanPham);

		lblTrongLuong = new JLabel("Trọng lượng:");
		lblTrongLuong.setForeground(new Color(72, 61, 139));
		lblTrongLuong.setFont(new Font("Arial", Font.BOLD, 16));
		lblTrongLuong.setBounds(10, 481, 116, 23);
		getContentPane().add(lblTrongLuong);	

		txtTrongLuong = new JTextField();
		txtTrongLuong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTrongLuong.setColumns(10);
		txtTrongLuong.setBounds(125, 480, 86, 23);
		getContentPane().add(txtTrongLuong);

		pnlButtonGroup = new JPanel();
		pnlButtonGroup.setBounds(10, 558, 766, 54);
		getContentPane().add(pnlButtonGroup);
		pnlButtonGroup.setLayout(null);

		btnThemSp = new JButton("Thêm sản phẩm");
		btnThemSp.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnThemSp.setBounds(316, 10, 132, 39);
		pnlButtonGroup.add(btnThemSp);

		btnHuy = new JButton("Hủy");
		btnHuy.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnHuy.setBounds(540, 10, 132, 39);
		pnlButtonGroup.add(btnHuy);

		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnLamMoi.setBounds(92, 10, 132, 39);
		pnlButtonGroup.add(btnLamMoi);

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

		cmbDonVi = new JComboBox<String>();
		cmbDonVi.addItem("Cái");
		cmbDonVi.addItem("Cuốn");
		cmbDonVi.addItem("Chiếc");
		cmbDonVi.setBackground(Color.WHITE);
		cmbDonVi.setBounds(125, 438, 240, 33);
		getContentPane().add(cmbDonVi);

		setRadSach();
		try {
			txtMasp.setText(tangMa());
			
			lblTrongLuongKg = new JLabel("kg");
			lblTrongLuongKg.setForeground(new Color(72, 61, 139));
			lblTrongLuongKg.setFont(new Font("Arial", Font.BOLD, 16));
			lblTrongLuongKg.setBounds(215, 481, 116, 23);
			getContentPane().add(lblTrongLuongKg);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.hinhAnh = getPathHinhAnh("..\\HieuSachTuNhan\\hinhAnhHieuSach\\bookUnknow.jpg");
		radSach.addMouseListener(this);
		radVPP.addMouseListener(this);
		btnChooser.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnThemSp.addActionListener(this);
		btnHuy.addActionListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if (o.equals(radSach)) {
			setRadSach();
		}
		if (o.equals(radVPP)) {
			setRadVPP();
		}

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
		Object o = e.getSource();
		if (o.equals(btnHuy)) {
			this.setVisible(false);
		}
		if (o.equals(btnLamMoi)) {
			lamMoi();
		}
		if (o.equals(btnThemSp)) {
			themSP();

		}
		if (o.equals(btnChooser)) {
			fchChonFile = new JFileChooser("../HieuSachTuNhan/hinhAnhHieuSach");
			fchChonFile.setMultiSelectionEnabled(false);
			int x = fchChonFile.showDialog(this, "Chọn Ảnh");
			if (x == JFileChooser.APPROVE_OPTION) {
				String file = fchChonFile.getSelectedFile().getAbsolutePath();
				if (!file.matches(".*(\\.jpg|\\.png|\\.PNG)")) {
					file = "..\\HieuSachTuNhan\\hinhAnhHieuSach\\bookUnknow.jpg";
				}
				lblImgSanPham.setIcon(setSizeImageIcon(new ImageIcon(file), lblImgSanPham.getWidth(), lblImgSanPham.getHeight()));
				hinhAnh = getPathHinhAnh(file);
			}
		}
	}

	public ImageIcon setSizeImageIcon(ImageIcon icon, int width, int height) {
		ImageIcon image = icon;
		Image imageSet = image.getImage();
		imageSet = imageSet.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		image = new ImageIcon(imageSet);
		return image;
	}

	public void themSP() {
		if (txtTenSp.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Tên sản phẩm không được để rỗng", "Báo lỗi",
					JOptionPane.ERROR_MESSAGE);
			txtTenSp.requestFocus();
			txtTenSp.selectAll();
			return;
		}
		Sach s = new Sach();
		VanPhongPham v = new VanPhongPham();
		if (radSach.isSelected()) {
			s = taoSach();
			if (s == null) {
				return;
			}
			try {
				boolean kt = sanPhamService.themSanPham(s);
				if (kt == true) {
					JOptionPane.showMessageDialog(null, "Thêm thành công !!!");
					lamMoi();
					txtMasp.setText(tangMa());
				} else {
					JOptionPane.showMessageDialog(null,
							"Sản phẩm đã tồn tại",
							"Báo lỗi", JOptionPane.ERROR_MESSAGE);
					txtTenSp.requestFocus();
					txtTenSp.selectAll();
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else {
			try {
				v = taoVanPhongPham();
				if (v == null) {
					return;
				}
				boolean kt = sanPhamService.themSanPham(v);
				if (kt == true) {
					JOptionPane.showMessageDialog(null, "Thêm thành công !!!");
					lamMoi();
					txtMasp.setText(tangMa());
				} else {
					JOptionPane.showMessageDialog(null,
							"Sản phẩm đã tồn tại", "Báo lỗi",
							JOptionPane.ERROR_MESSAGE);
					txtTenSp.requestFocus();
					txtTenSp.selectAll();
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public void lamMoi() {
		txtTenSp.setText("");
		txtSoTrang.setText("");
		txtSoLuong.setText("");
		txtGiaNhap.setText("");
		txtTrongLuong.setText("");
	}

	@SuppressWarnings("deprecation")
	public void setRadSach() {
		// the loai
		theLoaiSachs = new ArrayList<TheLoaiSach>();
		try {
			theLoaiSachs = theLoaiService.getListTheLoaiSach();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cmbLoai.removeAllItems();
		;
		for (TheLoaiSach theLoaiSach : theLoaiSachs) {
			cmbLoai.addItem(theLoaiSach.getTenLoai());
		}
		// tac gia
		tacGias = new ArrayList<TacGia>();
		try {
			tacGias = tacGiaService.getListTacGia();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		lblTacGia.setText("Tác giả: ");
		cmbTacGiaOrChatLieu.removeAllItems();
		for (TacGia tacGia : tacGias) {
			cmbTacGiaOrChatLieu.addItem(tacGia.getTenTacGia());
		}
		// nxb
		nhaXuatBans = new ArrayList<NhaXuatBan>();
		try {
			nhaXuatBans = nhaXuatBanService.getListNhaXuatBan();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		lblNxb.setText("Nhà xuất bản: ");
		cmbNhaXbOrXuatXu.removeAllItems();
		for (NhaXuatBan nhaXuatBan : nhaXuatBans) {
			cmbNhaXbOrXuatXu.addItem(nhaXuatBan.getTenNXB());
		}
		// ncc
		nhaCungCaps = new ArrayList<NhaCungCap>();
		try {
			nhaCungCaps = nhaCungCapService.getListNhaCungCap("Sách");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cmbNhaCungCap.removeAllItems();
		for (NhaCungCap nhaCungCap : nhaCungCaps) {
			cmbNhaCungCap.addItem(nhaCungCap.getTenNCC());
		}
		lblSoTrang.setText("Số trang: ");
		lblNamXb.show();
		chooserNamXB.show();
		cmbMauSac.hide();
		cmbDonVi.setSelectedIndex(1);
	}

	@SuppressWarnings("deprecation")
	public void setRadVPP() {
		// the loai
		theLoaiVanPhongPhams = new ArrayList<TheLoaiVanPhongPham>();
		try {
			theLoaiVanPhongPhams = theLoaiService.getListTheLoaiVanPhongPham();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cmbLoai.removeAllItems();
		for (TheLoaiVanPhongPham theLoaiVanPhongPham : theLoaiVanPhongPhams) {
			cmbLoai.addItem(theLoaiVanPhongPham.getTenLoai());
		}
		cmbDonVi.setSelectedIndex(0);
		// chat lieu
		chatLieus = new ArrayList<ChatLieu>();
		try {
			chatLieus = chatLieuService.getListChatLieu();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		lblTacGia.setText("Chất liệu: ");
		cmbTacGiaOrChatLieu.removeAllItems();
		for (ChatLieu chatLieu : chatLieus) {
			cmbTacGiaOrChatLieu.addItem(chatLieu.getTenChatLieu());
		}
		// Xuat xu
		xuatXus = new ArrayList<XuatXu>();
		try {
			xuatXus = xuatXuService.getListXuatXu();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		lblNxb.setText("Xuất xứ: ");
		cmbNhaXbOrXuatXu.removeAllItems();
		for (XuatXu xuatXu : xuatXus) {
			cmbNhaXbOrXuatXu.addItem(xuatXu.getTenXuatXu());
		}
		// ncc
		nhaCungCaps = new ArrayList<NhaCungCap>();
		try {
			nhaCungCaps = nhaCungCapService.getListNhaCungCap("Văn phòng phẩm");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cmbNhaCungCap.removeAllItems();
		for (NhaCungCap nhaCungCap : nhaCungCaps) {
			cmbNhaCungCap.addItem(nhaCungCap.getTenNCC());
		}
		// mau sac
		mauSacs = new ArrayList<MauSac>();
		try {
			mauSacs = mauSacService.getListMauSac();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		lblSoTrang.setText("Màu sắc: ");
		cmbMauSac.show();
		cmbMauSac.removeAllItems();
		for (MauSac mauSac : mauSacs) {
			cmbMauSac.addItem(mauSac.getTenMau());
		}
		lblNamXb.hide();
		chooserNamXB.hide();
	}

	public Sach taoSach() {
		Sach s = null;
		String maSanPham = txtMasp.getText().toString().trim();
		String loaiSanPham = "Sách";
		int soTrang;
		try {
			soTrang = Integer.parseInt(txtSoTrang.getText().trim());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Số trang không được là chữ!!", "Báo lỗi", JOptionPane.ERROR_MESSAGE);
			txtSoTrang.requestFocus();
			txtSoTrang.selectAll();
			return null;
		}
		if(soTrang <=0 || soTrang >=10000) {
			JOptionPane.showMessageDialog(null, "Số trang lớn hơn 0 và bé hơn 10000!!", "Báo lỗi", JOptionPane.ERROR_MESSAGE);
			txtSoTrang.requestFocus();
			txtSoTrang.selectAll();
			return null;
		}
		int soLuongTon;
		try {
			soLuongTon = Integer.parseInt(txtSoLuong.getText().trim());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Số lượng không được là chữ!!", "Báo lỗi", JOptionPane.ERROR_MESSAGE);
			txtSoLuong.requestFocus();
			txtSoLuong.selectAll();
			return null;
		}
		if(soLuongTon <=0|| soLuongTon >= 10000) {
			JOptionPane.showMessageDialog(null, "Số lượng lớn hơn 0 và bé hơn 10000!!", "Báo lỗi", JOptionPane.ERROR_MESSAGE);
			txtSoLuong.requestFocus();
			txtSoLuong.selectAll();
			return null;
		}
		NhaCungCap nhaCungCap = nhaCungCaps.get(cmbNhaCungCap.getSelectedIndex());
		long giaNhap;
		try {
			giaNhap = Long.parseLong(txtGiaNhap.getText().trim());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Giá nhập không được là chữ!!", "Báo lỗi", JOptionPane.ERROR_MESSAGE);
			txtGiaNhap.requestFocus();
			txtGiaNhap.selectAll();
			return null;
		}
		if(giaNhap <1000 || giaNhap >=10000000) {
			JOptionPane.showMessageDialog(null, "Giá nhập lớn hơn 1000 và bé hơn 10000000!!", "Báo lỗi", JOptionPane.ERROR_MESSAGE);
			txtGiaNhap.requestFocus();
			txtGiaNhap.selectAll();
			return null;
		}
		String ghiChu = txaGhiChu.getText().trim();
		String donViSanPham = cmbDonVi.getSelectedItem().toString();
		String tenSach = txtTenSp.getText().trim();
		TacGia tacGia = tacGias.get(cmbTacGiaOrChatLieu.getSelectedIndex());
		NhaXuatBan nhaXuatBan = nhaXuatBans.get(cmbNhaXbOrXuatXu.getSelectedIndex());
		int namXuatBan = chooserNamXB.getYear();
		int namHienTai = LocalDate.now().getYear();
		if(namXuatBan > namHienTai || namXuatBan<1980) {
			JOptionPane.showMessageDialog(null, "Năm xuất bản phải lớn hơn 1980 và bé hơn năm "+namHienTai+"!!", "Báo lỗi", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		double trongLuong;
		try {
			trongLuong = Double.parseDouble(txtTrongLuong.getText().trim());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Trọng lượng không được là chữ!!", "Báo lỗi",
					JOptionPane.ERROR_MESSAGE);
			txtTrongLuong.requestFocus();
			txtTrongLuong.selectAll();
			return null;
		}
		if(trongLuong <=0 || trongLuong >=5) {
			JOptionPane.showMessageDialog(null, "Trọng lượng lớn hơn 0 và bé hơn 5kg!!", "Báo lỗi",
					JOptionPane.ERROR_MESSAGE);
			txtTrongLuong.requestFocus();
			txtTrongLuong.selectAll();
			return null;
		}
		TheLoaiSach theLoaiSach = theLoaiSachs.get(cmbLoai.getSelectedIndex());
		s = new Sach(maSanPham, loaiSanPham, soLuongTon, trongLuong, nhaCungCap, giaNhap, ghiChu, donViSanPham, hinhAnh,
				tenSach, tacGia, nhaXuatBan, namXuatBan, soTrang, theLoaiSach);
		return s;
	}

	public VanPhongPham taoVanPhongPham() {
		VanPhongPham v = null;
		String maSanPham = txtMasp.getText().toString().trim();
		String loaiSanPham = "Văn phòng phẩm";
		int soLuongTon;
		try {
			soLuongTon = Integer.parseInt(txtSoLuong.getText().trim());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Số lượng không được là chữ!!", "Báo lỗi", JOptionPane.ERROR_MESSAGE);
			txtSoLuong.requestFocus();
			txtSoLuong.selectAll();
			return null;
		}
		if(soLuongTon<=0 || soLuongTon >10000) {
			JOptionPane.showMessageDialog(null, "Số lượng lớn hơn 0 và bé hơn 10000", "Báo lỗi", JOptionPane.ERROR_MESSAGE);
			txtSoLuong.requestFocus();
			txtSoLuong.selectAll();
			return null;
		}
		NhaCungCap nhaCungCap = nhaCungCaps.get(cmbNhaCungCap.getSelectedIndex());
		long giaNhap;
		try {
			giaNhap = Long.parseLong(txtGiaNhap.getText().trim());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Giá nhập không được là chữ!!", "Báo lỗi", JOptionPane.ERROR_MESSAGE);
			txtGiaNhap.requestFocus();
			txtGiaNhap.selectAll();
			return null;
		}
		String ghiChu = txaGhiChu.getText().trim();
		String donViSanPham = cmbDonVi.getSelectedItem().toString();
		String tenVPP = txtTenSp.getText().trim();
		TheLoaiVanPhongPham loaiVanPhongPham = theLoaiVanPhongPhams.get(cmbLoai.getSelectedIndex());
		MauSac mauSac = mauSacs.get(cmbMauSac.getSelectedIndex());
		ChatLieu chatLieu = chatLieus.get(cmbTacGiaOrChatLieu.getSelectedIndex());
		XuatXu xuatXu = xuatXus.get(cmbNhaXbOrXuatXu.getSelectedIndex());
		double trongLuong;
		try {
			trongLuong = Double.parseDouble(txtTrongLuong.getText().trim());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Trọng lượng không được là chữ!!", "Báo lỗi",
					JOptionPane.ERROR_MESSAGE);
			txtTrongLuong.requestFocus();
			txtTrongLuong.selectAll();
			return null;
		}
		if(trongLuong <=0 || trongLuong >=10) {
			JOptionPane.showMessageDialog(null, "Trọng lượng lớn hơn 0kg và bé hơn 10kg!!", "Báo lỗi",
					JOptionPane.ERROR_MESSAGE);
			txtTrongLuong.requestFocus();
			txtTrongLuong.selectAll();
			return null;
		}
		v = new VanPhongPham(maSanPham, loaiSanPham, soLuongTon, trongLuong, nhaCungCap, giaNhap, ghiChu, donViSanPham,
				hinhAnh, tenVPP, loaiVanPhongPham, mauSac, chatLieu, xuatXu);
		return v;
	}

	public String tangMa() throws Exception {
		String s = sanPhamService.getMaSPMax();
		int n = Integer.parseInt(s.substring(2));
		if ((n + 1) % 10 == 0) {
			s = s.replaceAll("0" + n + "", n + 1 + "");
		} else
			s = s.replaceAll(n + "", n + 1 + "");
		return s;
	}

	public String getPathHinhAnh(String filePathImg) {
		StringBuffer buffer = new StringBuffer(filePathImg);
		String hinhAnh = new StringBuffer(buffer.reverse().toString().split("\\\\")[0]).reverse().toString();
		return hinhAnh;
	}
}
