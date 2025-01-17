package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.toedter.calendar.JYearChooser;

import entity.ChatLieu;
import entity.MauSac;
import entity.NhaCungCap;
import entity.NhaXuatBan;
import entity.Sach;
import entity.TacGia;
import entity.TheLoaiSach;
import entity.TheLoaiVanPhongPham;
import entity.VanPhongPham;
import entity.XuatXu;
import service.impl.ChatLieuServiceImpl;
import service.impl.MauSacServiceImpl;
import service.impl.NhaCungCapServiceImpl;
import service.impl.NhaXuatBanServiceImpl;
import service.impl.SanPhamServiceImpl;
import service.impl.TacGiaServiceImpl;
import service.impl.TheLoaiServiceImpl;
import service.impl.XuatXuServiceImpl;

public class Frm_CapNhatSP extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<Object> cbbLoai;
	private JComboBox<Object> cbbTacGiaorChatLieu;
	private JComboBox<Object> cbbNhaXBorXuatXu;
	private JComboBox<String> cbbNhaCungCap;
	private JComboBox<String> cbbMauSac;
	private JTextField txtMasp;
	private JLabel lblNewLabel_2;
	private JLabel lblTacGia;
	private JLabel lblNXB;
	private JLabel lblnamXB;
	private JTextField txtSoTrang;
	private JLabel lblSoTrang;
	private JYearChooser chooserNamXB;
	private JLabel lblNewLabel_7;
	private JTextField txtSoLuong;
	private JLabel lblImgSP;
	private JButton btnChooser;
	private JLabel lblNewLabel_9;
	private JTextField txtTenSp;
	private JLabel lblNewLabel_10;
	private JLabel lblNewLabel_11;
	private JTextField txtGiaNhap;
	private JLabel lblNewLabel_12;
	private JLabel lblNewLabel_13;
	private JTextField txtTrongLuong;
	private JButton btnLamMoi;
	private JButton btnHuy;
	private JButton btnCapNhatSP;
	SanPhamServiceImpl sanPhamServiceImpl = new SanPhamServiceImpl();
	TheLoaiServiceImpl theLoaiServiceImpl = new TheLoaiServiceImpl();
	TacGiaServiceImpl tacGiaServiceImpl = new TacGiaServiceImpl();
	NhaXuatBanServiceImpl nhaXuatBanServiceImpl = new NhaXuatBanServiceImpl();
	NhaCungCapServiceImpl nhaCungCapServiceImpl = new NhaCungCapServiceImpl();
	ChatLieuServiceImpl chatLieuServiceImpl = new ChatLieuServiceImpl();
	XuatXuServiceImpl xuatXuServiceImpl = new XuatXuServiceImpl();
	MauSacServiceImpl mauSacServiceImpl = new MauSacServiceImpl();
	private ArrayList<TheLoaiSach> theLoaiSachs;
	private ArrayList<TacGia> tacGias;
	private ArrayList<NhaXuatBan> nhaXuatBans;
	private ArrayList<NhaCungCap> nhaCungCaps;
	private ArrayList<TheLoaiVanPhongPham> theLoaiVanPhongPhams;
	private ArrayList<ChatLieu> chatLieus;
	private ArrayList<XuatXu> xuatXus;
	private ArrayList<MauSac> mauSacs;
	private JTextArea txtAreaGhiChu;
	private JComboBox<String> cbbDonVi;
	String loaiSanPham;
	Sach sach;
	VanPhongPham vanPhongPham;
	String maSanPham;
	private String hinhAnh;
	private JFileChooser filechoose;

	@SuppressWarnings("deprecation")
	public Frm_CapNhatSP(String maSanPham, String loaiSanPham) {
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

		cbbLoai = new JComboBox<Object>();
		cbbLoai.setBackground(new Color(255, 255, 255));
		cbbLoai.setBounds(125, 159, 240, 33);
		getContentPane().add(cbbLoai);

		cbbTacGiaorChatLieu = new JComboBox<Object>();
		cbbTacGiaorChatLieu.setBackground(Color.WHITE);
		cbbTacGiaorChatLieu.setBounds(125, 202, 240, 33);
		getContentPane().add(cbbTacGiaorChatLieu);

		cbbNhaXBorXuatXu = new JComboBox<Object>();
		cbbNhaXBorXuatXu.setBackground(Color.WHITE);
		cbbNhaXBorXuatXu.setBounds(125, 245, 240, 33);
		getContentPane().add(cbbNhaXBorXuatXu);

		cbbNhaCungCap = new JComboBox<String>();
		cbbNhaCungCap.setBackground(Color.WHITE);
		cbbNhaCungCap.setBounds(125, 364, 328, 33);
		getContentPane().add(cbbNhaCungCap);

		cbbMauSac = new JComboBox<String>();
		cbbMauSac.setBackground(Color.WHITE);
		cbbMauSac.setBounds(125, 288, 240, 33);
		getContentPane().add(cbbMauSac);
		cbbMauSac.hide();

		JPanel panel = new JPanel();
		panel.setBounds(10, 38, 285, 46);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("CẬP NHẬT SẢN PHẨM");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabel.setBounds(10, 10, 766, 39);
		getContentPane().add(lblNewLabel);

		txtMasp = new JTextField(maSanPham);
		txtMasp.setEditable(false);
		txtMasp.setBorder(BorderFactory.createLineBorder(Color.cyan));
		txtMasp.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMasp.setBounds(125, 89, 170, 23);
		getContentPane().add(txtMasp);
		txtMasp.setColumns(10);

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

		chooserNamXB = new JYearChooser();
		chooserNamXB.setFont(new Font("Tahoma", Font.PLAIN, 16));
		chooserNamXB.setBounds(125, 514, 100, 23);
		getContentPane().add(chooserNamXB);

		lblnamXB = new JLabel("Năm xuất bản:");
		lblnamXB.setForeground(new Color(72, 61, 139));
		lblnamXB.setFont(new Font("Arial", Font.BOLD, 16));
		lblnamXB.setBounds(10, 514, 116, 23);
		getContentPane().add(lblnamXB);

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

		lblNewLabel_7 = new JLabel("Số lượng:");
		lblNewLabel_7.setForeground(new Color(72, 61, 139));
		lblNewLabel_7.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_7.setBounds(10, 331, 116, 23);
		getContentPane().add(lblNewLabel_7);

		txtSoLuong = new JTextField();
		txtSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSoLuong.setColumns(10);
		txtSoLuong.setBounds(125, 331, 170, 23);
		getContentPane().add(txtSoLuong);

		lblImgSP = new JLabel("");
		lblImgSP.setHorizontalAlignment(SwingConstants.CENTER);
		lblImgSP.setIcon(new ImageIcon(Frm_ThemSP.class.getResource("/gui/icon/user.png")));
		lblImgSP.setBounds(472, 77, 224, 245);
		lblImgSP.setBorder(BorderFactory.createLineBorder(Color.black));
		getContentPane().add(lblImgSP);

		btnChooser = new JButton("Chọn File");
		btnChooser.setBounds(535, 332, 109, 29);
		getContentPane().add(btnChooser);

		lblNewLabel_9 = new JLabel("Tên sản phẩm:");
		lblNewLabel_9.setForeground(new Color(72, 61, 139));
		lblNewLabel_9.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_9.setBounds(10, 122, 116, 23);
		getContentPane().add(lblNewLabel_9);

		txtTenSp = new JTextField();
		txtTenSp.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTenSp.setColumns(10);
		txtTenSp.setBounds(125, 122, 328, 23);
		getContentPane().add(txtTenSp);

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

		txtGiaNhap = new JTextField();
		txtGiaNhap.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtGiaNhap.setColumns(10);
		txtGiaNhap.setBounds(125, 405, 170, 23);
		getContentPane().add(txtGiaNhap);

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

		txtTrongLuong = new JTextField();
		txtTrongLuong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTrongLuong.setColumns(10);
		txtTrongLuong.setBounds(125, 480, 85, 23);
		getContentPane().add(txtTrongLuong);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 558, 766, 54);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		btnCapNhatSP = new JButton("Cập nhật");
		btnCapNhatSP.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCapNhatSP.setBounds(316, 10, 132, 39);
		panel_1.add(btnCapNhatSP);

		btnHuy = new JButton("Hủy");
		btnHuy.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnHuy.setBounds(540, 10, 132, 39);
		panel_1.add(btnHuy);

		btnLamMoi = new JButton("Khôi phục");
		btnLamMoi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnLamMoi.setBounds(92, 10, 132, 39);
		panel_1.add(btnLamMoi);

		txtAreaGhiChu = new JTextArea();
		txtAreaGhiChu.setFont(new Font("Courier New", Font.PLAIN, 13));
		txtAreaGhiChu.setBounds(491, 400, 285, 158);
		txtAreaGhiChu.setBorder(BorderFactory.createLineBorder(Color.black));
		getContentPane().add(txtAreaGhiChu);

		JLabel lblNewLabel_6_1 = new JLabel("Ghi chú(Mô tả):");
		lblNewLabel_6_1.setForeground(new Color(72, 61, 139));
		lblNewLabel_6_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_6_1.setBounds(490, 374, 127, 23);
		getContentPane().add(lblNewLabel_6_1);

		cbbDonVi = new JComboBox<String>();
		cbbDonVi.addItem("Cái");
		cbbDonVi.addItem("Cuốn");
		cbbDonVi.addItem("Chiếc");
		cbbDonVi.setBackground(Color.WHITE);
		cbbDonVi.setBounds(125, 438, 240, 33);
		getContentPane().add(cbbDonVi);
		
		JLabel lblNewLabel_13_1 = new JLabel("kg");
		lblNewLabel_13_1.setForeground(new Color(72, 61, 139));
		lblNewLabel_13_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_13_1.setBounds(220, 481, 116, 23);
		getContentPane().add(lblNewLabel_13_1);

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
		btnChooser.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnCapNhatSP.addActionListener(this);
		btnHuy.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnHuy)) {
			if (JOptionPane.showConfirmDialog(null, "Có chắc bạn muốn thoát", "Cảnh báo", JOptionPane.YES_NO_OPTION,
					JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
				this.setVisible(false);
		}
		if (o.equals(btnLamMoi)) {
			if (loaiSanPham.equals("Sách")) {
				try {
					setRadSach();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				try {
					setRadVPP();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		if (o.equals(btnCapNhatSP)) {
			Sach s = new Sach();
			VanPhongPham v = new VanPhongPham();
			if (loaiSanPham.equals("Sách")) {
				s = taoSach();
				if (s == null) {
					return;
				}
				try {
					boolean kt = sanPhamServiceImpl.capNhatSanPham(s.getMaSanPham(), s);
					if (kt == true) {
						JOptionPane.showMessageDialog(null, "Cập nhật thành công !!!");
					} else {
						JOptionPane.showMessageDialog(null,
								"Số trang phải lớn hơn 0!\nSố lượng phải lớn hơn 0!\nTrọng lượng phải lớn hơn 0!\n Giá nhập lớn hơn 1000!",
								"Báo lỗi", JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				v = taoVanPhongPham();
				if (v == null)
					return;
				try {
					boolean kt = sanPhamServiceImpl.capNhatSanPham(v.getMaSanPham(), v);
					if (kt == true) {
						JOptionPane.showMessageDialog(null, "Cập nhật thành công !!!");
					} else {
						JOptionPane.showMessageDialog(null,
								"Số lượng phải lớn hơn 0!\nTrọng lượng phải lớn hơn 0!\n Giá nhập lớn hơn 1000!",
								"Báo lỗi", JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		if (o.equals(btnChooser)) {
			filechoose = new JFileChooser("../HieuSachTuNhan/hinhAnhHieuSach");
			filechoose.setMultiSelectionEnabled(false);
			int x = filechoose.showDialog(this, "Chọn Ảnh");
			if (x == JFileChooser.APPROVE_OPTION) {
				String file = filechoose.getSelectedFile().getAbsolutePath();
				if (!file.matches(".*(\\.jpg|\\.png|\\.PNG)")) {
					file = "..\\HieuSachTuNhan\\hinhAnhHieuSach\\bookUnknow.jpg";
				}
				lblImgSP.setIcon(setSizeImageIcon(new ImageIcon(file), lblImgSP.getWidth(), lblImgSP.getHeight()));
				hinhAnh = getPathHinhAnh(file);
			}
		}

	}

	@SuppressWarnings("deprecation")
	public void setRadSach() throws Exception {
		sach = sanPhamServiceImpl.timSanPhamTheoMaSach(maSanPham);
		txtTenSp.setText(sach.getTenSach());
		// the loai
		theLoaiSachs = new ArrayList<TheLoaiSach>();
		try {
			theLoaiSachs = theLoaiServiceImpl.getListTheLoaiSach();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		;
		cbbLoai.removeAllItems();
		for (TheLoaiSach theLoaiSach : theLoaiSachs) {
			cbbLoai.addItem(theLoaiSach.getTenLoai());
		}

		cbbLoai.setSelectedItem(sach.getTheLoaiSach().getTenLoai());
		// tac gia
		tacGias = new ArrayList<TacGia>();
		try {
			tacGias = tacGiaServiceImpl.getListTacGia();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		lblTacGia.setText("Tác giả: ");
		cbbTacGiaorChatLieu.removeAllItems();
		for (TacGia tacGia : tacGias) {
			cbbTacGiaorChatLieu.addItem(tacGia.getTenTacGia());
		}
		cbbTacGiaorChatLieu.setSelectedItem(sach.getTacGia().getTenTacGia());
		// nxb
		nhaXuatBans = new ArrayList<NhaXuatBan>();
		try {
			nhaXuatBans = nhaXuatBanServiceImpl.getListNhaXuatBan();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		lblNXB.setText("Nhà xuất bản: ");
		cbbNhaXBorXuatXu.removeAllItems();
		for (NhaXuatBan nhaXuatBan : nhaXuatBans) {
			cbbNhaXBorXuatXu.addItem(nhaXuatBan.getTenNXB());
		}
		cbbNhaXBorXuatXu.setSelectedItem(sach.getNhaXuatBan().getTenNXB());
		// ncc
		nhaCungCaps = new ArrayList<NhaCungCap>();
		try {
			nhaCungCaps = nhaCungCapServiceImpl.getListNhaCungCap("Sách");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cbbNhaCungCap.removeAllItems();
		for (NhaCungCap nhaCungCap : nhaCungCaps) {
			cbbNhaCungCap.addItem(nhaCungCap.getTenNCC());
		}
		ImageIcon icon = new ImageIcon("..\\HieuSachTuNhan\\hinhAnhHieuSach\\bookUnknow.jpg");
		if (new File("..\\HieuSachTuNhan\\hinhAnhHieuSach\\" + sach.getHinhAnh()).exists()) {
			icon = new ImageIcon("..\\HieuSachTuNhan\\hinhAnhHieuSach\\" + sach.getHinhAnh());
		}
		lblImgSP.setIcon(setSizeImageIcon(icon, lblImgSP.getWidth(), lblImgSP.getHeight()));
		cbbNhaCungCap.setSelectedItem(sach.getNhaCungCap().getTenNCC());
		lblSoTrang.setText("Số trang: ");
		txtSoTrang.setText(sach.getSoTrang() + "");
		lblnamXB.show();
		chooserNamXB.show();
		chooserNamXB.setYear(sach.getNamXuatBan());
		cbbMauSac.hide();
		cbbDonVi.setSelectedItem(sach.getDonViSanPham());
		txtGiaNhap.setText(sach.getGiaNhap() + "");
		txtSoLuong.setText(sach.getSoLuongTon() + "");
		txtTrongLuong.setText(sach.getTrongLuong() + "");
		txtAreaGhiChu.setText(sach.getGhiChu());
	}

	@SuppressWarnings("deprecation")
	public void setRadVPP() throws Exception {
		// the loai
		vanPhongPham = sanPhamServiceImpl.timSanPhamTheoMaVPP(maSanPham);
		txtTenSp.setText(vanPhongPham.getTenVanPhongPham());
		theLoaiVanPhongPhams = new ArrayList<TheLoaiVanPhongPham>();
		try {
			theLoaiVanPhongPhams = theLoaiServiceImpl.getListTheLoaiVanPhongPham();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cbbLoai.removeAllItems();
		for (TheLoaiVanPhongPham theLoaiVanPhongPham : theLoaiVanPhongPhams) {
			cbbLoai.addItem(theLoaiVanPhongPham.getTenLoai());
		}
		cbbLoai.setSelectedItem(vanPhongPham.getLoaiVanPhongPham());
		cbbDonVi.setSelectedItem(vanPhongPham.getDonViSanPham());
		// chat lieu
		chatLieus = new ArrayList<ChatLieu>();
		try {
			chatLieus = chatLieuServiceImpl.getListChatLieu();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		lblTacGia.setText("Chất liệu: ");
		cbbTacGiaorChatLieu.removeAllItems();
		for (ChatLieu chatLieu : chatLieus) {
			cbbTacGiaorChatLieu.addItem(chatLieu.getTenChatLieu());
		}
		cbbTacGiaorChatLieu.setSelectedItem(vanPhongPham.getChatLieu().getTenChatLieu());
		// Xuat xu
		xuatXus = new ArrayList<XuatXu>();
		try {
			xuatXus = xuatXuServiceImpl.getListXuatXu();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		lblNXB.setText("Xuất xứ: ");
		cbbNhaXBorXuatXu.removeAllItems();
		for (XuatXu xuatXu : xuatXus) {
			cbbNhaXBorXuatXu.addItem(xuatXu.getTenXuatXu());
		}
		cbbNhaXBorXuatXu.setSelectedItem(vanPhongPham.getXuatXu().getTenXuatXu());
		// ncc
		nhaCungCaps = new ArrayList<NhaCungCap>();
		try {
			nhaCungCaps = nhaCungCapServiceImpl.getListNhaCungCap("Văn phòng phẩm");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cbbNhaCungCap.removeAllItems();
		for (NhaCungCap nhaCungCap : nhaCungCaps) {
			cbbNhaCungCap.addItem(nhaCungCap.getTenNCC());
		}
		cbbNhaCungCap.setSelectedItem(vanPhongPham.getNhaCungCap().getTenNCC());
		// mau sac
		mauSacs = new ArrayList<MauSac>();
		try {
			mauSacs = mauSacServiceImpl.getListMauSac();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		lblSoTrang.setText("Màu sắc: ");
		cbbMauSac.show();
		cbbMauSac.removeAllItems();
		for (MauSac mauSac : mauSacs) {
			cbbMauSac.addItem(mauSac.getTenMau());
		}
		ImageIcon icon = new ImageIcon("..\\HieuSachTuNhan\\hinhAnhHieuSach\\bookUnknow.jpg");
		if (new File("..\\HieuSachTuNhan\\hinhAnhHieuSach\\" + vanPhongPham.getHinhAnh()).exists()) {
			icon = new ImageIcon("..\\HieuSachTuNhan\\hinhAnhHieuSach\\" + vanPhongPham.getHinhAnh());
		}
		lblImgSP.setIcon(setSizeImageIcon(icon, lblImgSP.getWidth(), lblImgSP.getHeight()));

		cbbMauSac.setSelectedItem(vanPhongPham.getMauSac().getTenMau());
		lblnamXB.hide();
		txtSoTrang.hide();
		chooserNamXB.hide();
		txtGiaNhap.setText(vanPhongPham.getGiaNhap() + "");
		txtSoLuong.setText(vanPhongPham.getSoLuongTon() + "");
		txtTrongLuong.setText(vanPhongPham.getTrongLuong() + "");
		txtAreaGhiChu.setText(vanPhongPham.getGhiChu());
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
		NhaCungCap nhaCungCap = nhaCungCaps.get(cbbNhaCungCap.getSelectedIndex());
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
		String ghiChu = txtAreaGhiChu.getText().trim();
		String donViSanPham = cbbDonVi.getSelectedItem().toString();
		String tenSach = txtTenSp.getText().trim();
		TacGia tacGia = tacGias.get(cbbTacGiaorChatLieu.getSelectedIndex());
		NhaXuatBan nhaXuatBan = nhaXuatBans.get(cbbNhaXBorXuatXu.getSelectedIndex());
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
		TheLoaiSach theLoaiSach = theLoaiSachs.get(cbbLoai.getSelectedIndex());
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
		NhaCungCap nhaCungCap = nhaCungCaps.get(cbbNhaCungCap.getSelectedIndex());
		long giaNhap;
		try {
			giaNhap = Long.parseLong(txtGiaNhap.getText().trim());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Giá nhập không được là chữ!!", "Báo lỗi", JOptionPane.ERROR_MESSAGE);
			txtGiaNhap.requestFocus();
			txtGiaNhap.selectAll();
			return null;
		}
		String ghiChu = txtAreaGhiChu.getText().trim();
		String donViSanPham = cbbDonVi.getSelectedItem().toString();
		String tenVPP = txtTenSp.getText().trim();
		TheLoaiVanPhongPham loaiVanPhongPham = theLoaiVanPhongPhams.get(cbbLoai.getSelectedIndex());
		MauSac mauSac = mauSacs.get(cbbMauSac.getSelectedIndex());
		ChatLieu chatLieu = chatLieus.get(cbbTacGiaorChatLieu.getSelectedIndex());
		XuatXu xuatXu = xuatXus.get(cbbNhaXBorXuatXu.getSelectedIndex());
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

	public ImageIcon setSizeImageIcon(ImageIcon icon, int width, int height) {
		ImageIcon image = icon;
		Image imageSet = image.getImage();
		imageSet = imageSet.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		image = new ImageIcon(imageSet);
		return image;
	}

	public String getPathHinhAnh(String filePathImg) {
		StringBuffer buffer = new StringBuffer(filePathImg);
		String hinhAnh = new StringBuffer(buffer.reverse().toString().split("\\\\")[0]).reverse().toString();
		return hinhAnh;
	}
}
