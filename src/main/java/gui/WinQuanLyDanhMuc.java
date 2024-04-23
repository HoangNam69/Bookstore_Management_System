package gui;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import entities.ChatLieu;
import entities.MauSac;
import entities.NhaCungCap;
import entities.NhaXuatBan;
import entities.TacGia;
import entities.TheLoaiSach;
import entities.TheLoaiVanPhongPham;
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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JRadioButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

public class WinQuanLyDanhMuc extends JFrame implements ActionListener, MouseListener {

	// JTextField
	private JTextField txtDiaChi;
	private JTextField txtEmail;
	private JTextField txtMa;
	private JTextField txtSdt;
	private JTextField txtTen;

	// JLabel
	private JLabel lblDiaChi;
	private JLabel lblEmail;
	private JLabel lblMa;
	private JLabel lblSdt;
	private JLabel lblTen;
	private JLabel lblTitle;

	// JRadioButton
	private JRadioButton radMauSac;
	private JRadioButton radNhaCungCap;
	private JRadioButton radNxbOrXuatXu;
	private JRadioButton radTacGiaOrChatLieu;
	private JRadioButton radTheLoai;

	// JButton
	private JButton btnHuy;
	private JButton btnThem;

	// Other objects
	private ArrayList<ChatLieu> chatLieus;
	private ArrayList<MauSac> mauSacs;
	private ArrayList<NhaCungCap> nhaCungCaps;
	private ArrayList<NhaXuatBan> nhaXuatBans;
	private ArrayList<TacGia> tacGias;
	private ArrayList<TheLoaiSach> theLoaiSachs;
	private ArrayList<TheLoaiVanPhongPham> theLoaiVanPhongPhams;
	private ArrayList<XuatXu> xuatXus;
	private ButtonGroup group;
	private DefaultTableModel mdlThuocTinh;
	private JScrollPane scrThuocTinh;
	private JTable tblThuocTinh;
	private String loaiSanPham;

	private static final String URL = "rmi://"+ Constants.IPV4 + ":"+ Constants.PORT + "/";
	private TheLoaiService theLoaiService = (TheLoaiService) Naming.lookup(URL + "theLoai");
	private TacGiaService tacGiaService = (TacGiaService) Naming.lookup(URL + "tacGia");
	private NhaXuatBanService nhaXuatBanService = (NhaXuatBanService) Naming.lookup(URL + "nhaXuatBan");
	private NhaCungCapService nhaCungCapService = (NhaCungCapService) Naming.lookup(URL + "nhaCungCap");
	private ChatLieuService chatLieuService = (ChatLieuService) Naming.lookup(URL + "chatLieu");
	private XuatXuService xuatXuService = (XuatXuService) Naming.lookup(URL + "xuatXu");
	private MauSacService mauSacService = (MauSacService) Naming.lookup(URL + "mauSac");

	public WinQuanLyDanhMuc(String loai) throws Exception {
		loaiSanPham = loai;
		setResizable(false);
		setSize(1000, 600);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		txtMa = new JTextField();
		txtMa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtMa.setBounds(237, 54, 233, 28);
		getContentPane().add(txtMa);
		txtMa.setColumns(10);


		lblTitle = new JLabel("QUẢN LÝ DANH MỤC");
		lblTitle.setVerticalAlignment(SwingConstants.TOP);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 10, 966, 28);
		getContentPane().add(lblTitle);

		lblMa = new JLabel("Mã thể loại:");
		lblMa.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMa.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblMa.setBounds(20, 54, 218, 28);
		getContentPane().add(lblMa);

		txtTen = new JTextField();
		txtTen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTen.setColumns(10);
		txtTen.setBounds(237, 95, 233, 28);
		txtTen.setEditable(true);
		getContentPane().add(txtTen);

		lblTen = new JLabel("Tên thể loại:");
		lblTen.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTen.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTen.setBounds(20, 95, 218, 28);
		getContentPane().add(lblTen);
		String[] cols = { "Mã", "Tên" };
		mdlThuocTinh = new DefaultTableModel(cols, 0);
		tblThuocTinh = new JTable(mdlThuocTinh);
		tblThuocTinh.setBorder(new LineBorder(new Color(0, 0, 0)));
		tblThuocTinh.setFont(new Font("Tahoma", Font.PLAIN, 15));
		scrThuocTinh = new JScrollPane(tblThuocTinh);
		scrThuocTinh.setBounds(10, 256, 966, 307);

		tblThuocTinh.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 14));
		tblThuocTinh.setAutoCreateRowSorter(true);
		tblThuocTinh.setRowHeight(25);
		scrThuocTinh.getViewport().setBackground(Color.WHITE);
		tblThuocTinh.getTableHeader().setPreferredSize(new Dimension(0, 40));
		getContentPane().add(scrThuocTinh);

		radTheLoai = new JRadioButton("Thể loại sách");
		radTheLoai.setFont(new Font("Tahoma", Font.PLAIN, 16));
		radTheLoai.setBounds(525, 54, 211, 21);
		getContentPane().add(radTheLoai);

		radNxbOrXuatXu = new JRadioButton("Nhà xuất bản");
		radNxbOrXuatXu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		radNxbOrXuatXu.setBounds(525, 95, 157, 21);
		getContentPane().add(radNxbOrXuatXu);

		radTacGiaOrChatLieu = new JRadioButton("Tác giả");
		radTacGiaOrChatLieu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		radTacGiaOrChatLieu.setBounds(738, 54, 91, 21);
		getContentPane().add(radTacGiaOrChatLieu);

		radNhaCungCap = new JRadioButton("Nhà cung cấp");
		radNhaCungCap.setFont(new Font("Tahoma", Font.PLAIN, 16));
		radNhaCungCap.setBounds(738, 95, 141, 21);
		getContentPane().add(radNhaCungCap);

		radMauSac = new JRadioButton("Màu sắc");
		radMauSac.setFont(new Font("Tahoma", Font.PLAIN, 16));
		radMauSac.setBounds(870, 54, 91, 21);
		getContentPane().add(radMauSac);

		btnThem = new JButton("Thêm");
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnThem.setBounds(525, 133, 122, 28);
		getContentPane().add(btnThem);
		txtMa.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		txtTen.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		setEditTxt(false);
		radTheLoai.setSelected(true);
		group = new ButtonGroup();
		group.add(radNxbOrXuatXu);
		group.add(radTheLoai);
		group.add(radMauSac);
		group.add(radNhaCungCap);
		group.add(radTacGiaOrChatLieu);

		btnHuy = new JButton("Thoát");
		btnHuy.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnHuy.setBounds(668, 133, 122, 28);
		getContentPane().add(btnHuy);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtEmail.setEditable(false);
		txtEmail.setColumns(10);
		txtEmail.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		txtEmail.setBounds(237, 171, 233, 28);
		getContentPane().add(txtEmail);

		lblEmail = new JLabel("Email:");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblEmail.setBounds(20, 171, 218, 28);
		getContentPane().add(lblEmail);

		txtSdt = new JTextField();
		txtSdt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtSdt.setEditable(false);
		txtSdt.setColumns(10);
		txtSdt.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		txtSdt.setBounds(237, 133, 233, 28);
		getContentPane().add(txtSdt);

		lblSdt = new JLabel("Số điện thoại:");
		lblSdt.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSdt.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSdt.setBounds(20, 133, 218, 28);
		getContentPane().add(lblSdt);
		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtDiaChi.setEditable(false);
		txtDiaChi.setColumns(10);
		txtDiaChi.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		txtDiaChi.setBounds(237, 209, 400, 28);
		getContentPane().add(txtDiaChi);

		lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblDiaChi.setBounds(20, 209, 218, 28);
		getContentPane().add(lblDiaChi);
		lblEmail.setVisible(false);
		lblSdt.setVisible(false);
		lblDiaChi.setVisible(false);
		txtEmail.setVisible(false);
		txtSdt.setVisible(false);
		txtDiaChi.setVisible(false);
		try {
			if (loaiSanPham.equals("Sách")) {
				loadThuocTinhSach();
				for (TheLoaiSach loaiSach : theLoaiSachs) {
					Object[] o1 = { loaiSach.getMaLoai(), loaiSach.getTenLoai() };
					mdlThuocTinh.addRow(o1);
				}

			} else {
				loadThuocTinhVPP();
				for (TheLoaiVanPhongPham loaiVanPhongPham : theLoaiVanPhongPhams) {
					Object[] o1 = { loaiVanPhongPham.getMaLoai(), loaiVanPhongPham.getTenLoai() };
					mdlThuocTinh.addRow(o1);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		btnThem.addActionListener(this);
		btnHuy.addActionListener(this);
		radNxbOrXuatXu.addMouseListener(this);
		radTheLoai.addMouseListener(this);
		radMauSac.addMouseListener(this);
		radNhaCungCap.addMouseListener(this);
		radTacGiaOrChatLieu.addMouseListener(this);
		tblThuocTinh.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if (o.equals(radTheLoai)) {
			setlbl("Mã thể loại:", "Tên thể loại:");
			if (loaiSanPham.equals("Sách")) {
				mdlThuocTinh.setRowCount(0);
				for (TheLoaiSach loaiSach : theLoaiSachs) {
					Object[] o1 = { loaiSach.getMaLoai(), loaiSach.getTenLoai() };
					mdlThuocTinh.addRow(o1);
				}
				setTxt("", "");
			} else {
				String[] cols = { "Mã thể loại", "Tên thể loại" };
				mdlThuocTinh = new DefaultTableModel(cols, 0);
				tblThuocTinh = new JTable(mdlThuocTinh);
				mdlThuocTinh.setRowCount(0);
				for (TheLoaiVanPhongPham loaiVanPhongPham : theLoaiVanPhongPhams) {
					Object[] o1 = { loaiVanPhongPham.getMaLoai(), loaiVanPhongPham.getTenLoai() };
					mdlThuocTinh.addRow(o1);
				}
				setTxt("", "");
			}

		}
		if (o.equals(radNxbOrXuatXu)) {
			if (loaiSanPham.equals("Sách")) {
				setlbl("Mã nhà xuất bản:", "Tên nhà xuất bản:");
				mdlThuocTinh.setRowCount(0);
				for (NhaXuatBan nhaXuatBan : nhaXuatBans) {
					Object[] o1 = { nhaXuatBan.getMaNXB(), nhaXuatBan.getTenNXB() };
					mdlThuocTinh.addRow(o1);
				}
				setTxt("", "");
			} else {
				setlbl("Mã xuất xứ:", "Tên xuất xứ:");
				mdlThuocTinh.setRowCount(0);
				for (XuatXu xuatXu : xuatXus) {
					Object[] o1 = { xuatXu.getMaXuatXu(), xuatXu.getTenXuatXu() };
					mdlThuocTinh.addRow(o1);
				}
				setTxt("", "");
			}

		}
		if (o.equals(radMauSac)) {
			setlbl("Mã màu:", "Tên màu:");
			mdlThuocTinh.setRowCount(0);
			for (MauSac mauSac : mauSacs) {
				Object[] o1 = { mauSac.getMaMau(), mauSac.getTenMau() };
				mdlThuocTinh.addRow(o1);
			}
			setTxt("", "");
		}
		if (o.equals(radTacGiaOrChatLieu)) {
			if (loaiSanPham.equals("Sách")) {
				setlbl("Mã tác giả:", "Tên tác giả:");
				mdlThuocTinh.setRowCount(0);
				for (TacGia tacGia : tacGias) {
					Object[] o1 = { tacGia.getMaTacGia(), tacGia.getTenTacGia() };
					mdlThuocTinh.addRow(o1);
				}
				setTxt("", "");
			} else {
				setlbl("Mã chất liệu: ", "Tên chất liệu:");
				mdlThuocTinh.setRowCount(0);
				for (ChatLieu chatLieu : chatLieus) {
					Object[] o1 = { chatLieu.getMaChatLieu(), chatLieu.getTenChatLieu() };
					mdlThuocTinh.addRow(o1);
				}
				setTxt("", "");
			}

		}
		if (o.equals(radNhaCungCap)) {
			setlbl("Mã nhà cung cấp:", "Tên nhà cung cấp:");
			lblEmail.setVisible(true);
			lblSdt.setVisible(true);
			txtEmail.setVisible(true);
			txtSdt.setVisible(true);
			lblDiaChi.setVisible(true);
			txtDiaChi.setVisible(true);
			txtDiaChi.setText("");
			txtEmail.setText("");
			txtSdt.setText("");
			mdlThuocTinh.setRowCount(0);
			for (NhaCungCap nhaCungCap : nhaCungCaps) {
				Object[] o1 = { nhaCungCap.getMaNCC(), nhaCungCap.getTenNCC() };
				mdlThuocTinh.addRow(o1);
			}
			setTxt("", "");
		} else {
			lblEmail.setVisible(false);
			lblSdt.setVisible(false);
			txtEmail.setVisible(false);
			txtSdt.setVisible(false);
			lblDiaChi.setVisible(false);
			txtDiaChi.setVisible(false);
		}
		if (o.equals(tblThuocTinh)) {
			int row = tblThuocTinh.getSelectedRow();
			setTxt(mdlThuocTinh.getValueAt(row, 0).toString().trim(),
					mdlThuocTinh.getValueAt(row, 1).toString().trim());
			if (radNhaCungCap.isSelected()) {
				lblEmail.setVisible(true);
				lblSdt.setVisible(true);
				txtEmail.setVisible(true);
				txtSdt.setVisible(true);
				lblDiaChi.setVisible(true);
				txtDiaChi.setVisible(true);
				txtDiaChi.setText(nhaCungCaps.get(row).getDiaChi());
				txtEmail.setText(nhaCungCaps.get(row).getEmail());
				txtSdt.setText(nhaCungCaps.get(row).getsDT());
			}
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
		if (o.equals(btnThem)) {
			if (btnThem.getText().equals("Thêm")) {
				String ma = "";
				setRadEnable(false);
				if (loaiSanPham.equals("Sách")) {
					if (radTheLoai.isSelected())
						ma = tangMa(theLoaiSachs.get(theLoaiSachs.size() - 1).getMaLoai(), 1);
					else if (radNxbOrXuatXu.isSelected())
						ma = tangMa(nhaXuatBans.get(nhaXuatBans.size() - 1).getMaNXB(), 3);
					else if (radTacGiaOrChatLieu.isSelected())
						ma = tangMa(tacGias.get(tacGias.size() - 1).getMaTacGia(), 2);
				} else {
					if (radTheLoai.isSelected())
						ma = tangMa(theLoaiVanPhongPhams.get(theLoaiVanPhongPhams.size() - 1).getMaLoai(), 2);
					else if (radNxbOrXuatXu.isSelected())
						ma = tangMa(xuatXus.get(xuatXus.size() - 1).getMaXuatXu(), 2);
					else if (radTacGiaOrChatLieu.isSelected())
						ma = tangMa(chatLieus.get(chatLieus.size() - 1).getMaChatLieu(), 2);
					else if (radMauSac.isSelected())
						ma = tangMa(mauSacs.get(mauSacs.size() - 1).getMaMau(), 2);
				}
				if (radNhaCungCap.isSelected()) {
					ma = tangMa(nhaCungCaps.get(nhaCungCaps.size() - 1).getMaNCC(), 3);
					txtDiaChi.setEditable(true);
					txtEmail.setEditable(true);
					txtSdt.setEditable(true);
				}
				setTxt(ma, "");
				setEditTxt(true);
				txtMa.setEditable(false);
				btnThem.setText("Xác nhận");
				btnHuy.setText("Hủy");

			} else {
				if (JOptionPane.showConfirmDialog(null, "Đồng ý thêm !!", "Xác nhận", JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
					if (loaiSanPham.equals("Sách")) {
						if (radTheLoai.isSelected()) {
							try {
								if (!checkDuLieuRong(txtTen, "Tên thể loại sản phẩm không được để trống")) {
									return;
								}
								theLoaiService.themTheLoaiSach(
										new TheLoaiSach(txtMa.getText().trim(), txtTen.getText().trim()));
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} else if (radNhaCungCap.isSelected())
							try {
								if (!checkDuLieuRong(txtTen, "Tên thể loại sản phẩm không được để trống")) {
									return;
								}
								if (!txtSdt.getText().trim().matches("(09|07|08|01)[0-9]{8}")) {
									JOptionPane.showMessageDialog(null,
											"Số điện thoại bắt đầu bằng những đầu số: 09, 07, 08, 01\nSố điện thoại phải gồm 10 số",
											"Báo lỗi", JOptionPane.ERROR_MESSAGE);
									txtSdt.requestFocus();
									txtSdt.selectAll();
									return;
								}
								if (!txtEmail.getText().trim().matches(".*@.*$")) {
									JOptionPane.showMessageDialog(null, "Email phải có dạng ncc@gmail.com", "Báo lỗi",
											JOptionPane.ERROR_MESSAGE);
									return;
								}

								if (txtDiaChi.getText().trim().equals("")) {
									JOptionPane.showMessageDialog(null, "Địa chỉ không được để trống", "Báo lỗi",
											JOptionPane.ERROR_MESSAGE);
									return;
								}
								nhaCungCapService.themNhaCungCap(new NhaCungCap(txtMa.getText().trim(),
										txtTen.getText().trim(), txtDiaChi.getText().trim(), txtEmail.getText().trim(),
										txtSdt.getText().trim()));
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						else if (radNxbOrXuatXu.isSelected())
							try {
								if (!checkDuLieuRong(txtTen, "Tên nhà xuất bản không được để trống")) {
									return;
								}
								nhaXuatBanService.themNhaXuatBan(
										new NhaXuatBan(txtMa.getText().trim(), txtTen.getText().trim()));
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						else if (radTacGiaOrChatLieu.isSelected())
							try {
								if (!checkDuLieuRong(txtTen, "Tên tác giả không được để trống")) {
									return;
								}
								tacGiaService
										.themTacGia(new TacGia(txtMa.getText().trim(), txtTen.getText().trim()));
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						try {
							loadThuocTinhSach();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						if (radTheLoai.isSelected())
							try {
								if (!checkDuLieuRong(txtTen, "Tên thể loại sản phẩm không được để trống")) {
									return;
								}
								theLoaiService.themTheLoaiVanPhongPham(
										new TheLoaiVanPhongPham(txtMa.getText().trim(), txtTen.getText().trim()));
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						else if (radNhaCungCap.isSelected())
							try {
								if (!checkDuLieuRong(txtTen, "Tên nhà cung cấp không được để trống")) {
									return;
								}
								if (!txtSdt.getText().trim().matches("(09|07|08|01)[0-9]{8}")) {
									JOptionPane.showMessageDialog(null,
											"Số điện thoại bắt đầu bằng những đầu số: 09, 07, 08, 01\nSố điện thoại phải gồm 10 số",
											"Báo lỗi", JOptionPane.ERROR_MESSAGE);
									txtSdt.requestFocus();
									txtSdt.selectAll();
									return;
								}
								if (!txtEmail.getText().trim().matches(".*@.*$")) {
									JOptionPane.showMessageDialog(null, "Email phải có dạng ncc@gmail.com", "Báo lỗi",
											JOptionPane.ERROR_MESSAGE);
									return;
								}
								if (txtDiaChi.getText().trim().equals("")) {
									JOptionPane.showMessageDialog(null, "Địa chỉ không được để trống", "Báo lỗi",
											JOptionPane.ERROR_MESSAGE);
									return;
								}
								nhaCungCapService.themNhaCungCap(new NhaCungCap(txtMa.getText().trim(),
										txtTen.getText().trim(), txtDiaChi.getText().trim(), txtEmail.getText().trim(),
										txtSdt.getText().trim()));
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						else if (radNxbOrXuatXu.isSelected())
							try {
								if (!checkDuLieuRong(txtTen, "Tên xuất xứ sản phẩm không được để trống")) {
									return;
								}
								xuatXuService
										.themXuatXu(new XuatXu(txtMa.getText().trim(), txtTen.getText().trim()));
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						else if (radTacGiaOrChatLieu.isSelected())
							try {
								if (!checkDuLieuRong(txtTen, "Tên chất liệu không được để trống")) {
									return;
								}
								chatLieuService
										.themChatLieu(new ChatLieu(txtMa.getText().trim(), txtTen.getText().trim()));
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						else if (radMauSac.isSelected())
							try {
								if (!checkDuLieuRong(txtTen, "Tên màu sản phẩm không được để trống")) {
									return;
								}
								mauSacService
										.themMauSac(new MauSac(txtMa.getText().trim(), txtTen.getText().trim()));
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						try {
							loadThuocTinhVPP();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					loadDuLieu();
					setRadEnable(true);
					setEditTxt(false);
					txtDiaChi.setEditable(false);
					txtEmail.setEditable(false);
					txtSdt.setEditable(false);
					txtDiaChi.setText("");
					txtEmail.setText("");
					txtSdt.setText("");
					btnThem.setText("Thêm");
					btnHuy.setText("Thoát");
					setTxt("", "");
				}
			}

		}
		if (o.equals(btnHuy)) {
			if (btnHuy.getText().equals("Hủy")) {
				setRadEnable(true);
				setEditTxt(false);
				setTxt("", "");
				btnHuy.setText("Thoát");
				btnThem.setText("Thêm");
			} else {
				if (JOptionPane.showConfirmDialog(null, "Có chắc bạn muốn thoát", "Cảnh báo", JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
					this.setVisible(false);
			}
		}

	}

	public void loadThuocTinhSach() throws Exception {
		lblTitle.setText("QUẢN LÝ THUỘC TÍNH SÁCH");
		theLoaiSachs = new ArrayList<>();
		theLoaiSachs = theLoaiService.getListTheLoaiSach();
		nhaXuatBans = new ArrayList<>();
		nhaXuatBans = nhaXuatBanService.getListNhaXuatBan();
		tacGias = new ArrayList<>();
		tacGias = tacGiaService.getListTacGia();
		nhaCungCaps = new ArrayList<>();
		nhaCungCaps = nhaCungCapService.getAllListNhaCungCap();
		radMauSac.setVisible(false);
		radNxbOrXuatXu.setText("Nhà xuất bản");
		radTacGiaOrChatLieu.setText("Tác giả");
		radTheLoai.setText("Thể loại sách");
	}

	public void loadThuocTinhVPP() throws Exception {
		lblTitle.setText("QUẢN LÝ THUỘC TÍNH VĂN PHÒNG PHẨM");
		theLoaiVanPhongPhams = new ArrayList<>();
		theLoaiVanPhongPhams = theLoaiService.getListTheLoaiVanPhongPham();
		xuatXus = new ArrayList<>();
		xuatXus = xuatXuService.getListXuatXu();
		chatLieus = new ArrayList<>();
		chatLieus = chatLieuService.getListChatLieu();
		nhaCungCaps = new ArrayList<>();
		nhaCungCaps = nhaCungCapService.getAllListNhaCungCap();
		mauSacs = new ArrayList<>();
		mauSacs = mauSacService.getListMauSac();
		radMauSac.setVisible(true);
		radNxbOrXuatXu.setText("Xuất xứ");
		radTacGiaOrChatLieu.setText("Chất liệu");
		radTheLoai.setText("Thể loại văn phòng phẩm");
	}

	public void setTxt(String ma, String ten) {
		txtMa.setText(ma);
		txtTen.setText(ten);
	}

	public void setlbl(String ma, String ten) {
		lblMa.setText(ma);
		lblTen.setText(ten);
	}

	public void setEditTxt(boolean edit) {
		txtMa.setEditable(edit);
		txtTen.setEditable(edit);
	}

	public String tangMa(String maHT, int indexLastAlphabet) {
		String s = maHT;
		int n = Integer.parseInt(s.substring(indexLastAlphabet));
		if ((n + 1) % 10 == 0) {
			s = s.replaceAll("0" + n + "", n + 1 + "");
		} else
			s = s.replaceAll(n + "", n + 1 + "");
		return s;
	}

	public void setRadEnable(boolean enable) {
		radMauSac.setEnabled(enable);
		radNhaCungCap.setEnabled(enable);
		radNxbOrXuatXu.setEnabled(enable);
		radTacGiaOrChatLieu.setEnabled(enable);
		radTheLoai.setEnabled(enable);
		if (enable == true) {
			addMouseRad();
		} else {
			removeMouseRad();
		}
	}

	public void removeMouseRad() {
		radMauSac.removeMouseListener(this);
		radNhaCungCap.removeMouseListener(this);
		radNxbOrXuatXu.removeMouseListener(this);
		radTacGiaOrChatLieu.removeMouseListener(this);
		radTheLoai.removeMouseListener(this);

	}

	public void addMouseRad() {
		radNxbOrXuatXu.addMouseListener(this);
		radTheLoai.addMouseListener(this);
		radMauSac.addMouseListener(this);
		radNhaCungCap.addMouseListener(this);
		radTacGiaOrChatLieu.addMouseListener(this);
	}

	public void loadDuLieu() {
		if (radTheLoai.isSelected()) {
			setlbl("Mã thể loại:", "Tên thể loại:");
			if (loaiSanPham.equals("Sách")) {
				mdlThuocTinh.setRowCount(0);
				for (TheLoaiSach loaiSach : theLoaiSachs) {
					Object[] o1 = { loaiSach.getMaLoai(), loaiSach.getTenLoai() };
					mdlThuocTinh.addRow(o1);
				}
				setTxt("", "");
			} else {
				mdlThuocTinh.setRowCount(0);
				for (TheLoaiVanPhongPham loaiVanPhongPham : theLoaiVanPhongPhams) {
					Object[] o1 = { loaiVanPhongPham.getMaLoai(), loaiVanPhongPham.getTenLoai() };
					mdlThuocTinh.addRow(o1);
				}
				setTxt("", "");
			}

		}
		if (radNxbOrXuatXu.isSelected()) {
			if (loaiSanPham.equals("Sách")) {
				setlbl("Mã nhà xuất bản:", "Tên nhà xuất bản:");
				mdlThuocTinh.setRowCount(0);
				for (NhaXuatBan nhaXuatBan : nhaXuatBans) {
					Object[] o1 = { nhaXuatBan.getMaNXB(), nhaXuatBan.getTenNXB() };
					mdlThuocTinh.addRow(o1);
				}
				setTxt("", "");
			} else {
				setlbl("Mã xuất xứ:", "Tên xuất xứ:");
				mdlThuocTinh.setRowCount(0);
				for (XuatXu xuatXu : xuatXus) {
					Object[] o1 = { xuatXu.getMaXuatXu(), xuatXu.getTenXuatXu() };
					mdlThuocTinh.addRow(o1);
				}
				setTxt("", "");
			}

		}
		if (radMauSac.isSelected()) {
			setlbl("Mã màu:", "Tên màu:");
			mdlThuocTinh.setRowCount(0);
			for (MauSac mauSac : mauSacs) {
				Object[] o1 = { mauSac.getMaMau(), mauSac.getTenMau() };
				mdlThuocTinh.addRow(o1);
			}
			setTxt("", "");
		}
		if (radTacGiaOrChatLieu.isSelected()) {
			if (loaiSanPham.equals("Sách")) {
				setlbl("Mã tác giả:", "Tên tác giả:");
				mdlThuocTinh.setRowCount(0);
				for (TacGia tacGia : tacGias) {
					Object[] o1 = { tacGia.getMaTacGia(), tacGia.getTenTacGia() };
					mdlThuocTinh.addRow(o1);
				}
				setTxt("", "");
			} else {
				setlbl("Mã chất liệu: ", "Tên chất liệu:");
				mdlThuocTinh.setRowCount(0);
				for (ChatLieu chatLieu : chatLieus) {
					Object[] o1 = { chatLieu.getMaChatLieu(), chatLieu.getTenChatLieu() };
					mdlThuocTinh.addRow(o1);
				}
				setTxt("", "");
			}

		}
		if (radNhaCungCap.isSelected()) {
			setlbl("Mã nhà cung cấp:", "Tên nhà cung cấp:");
			mdlThuocTinh.setRowCount(0);
			for (NhaCungCap nhaCungCap : nhaCungCaps) {
				Object[] o1 = { nhaCungCap.getMaNCC(), nhaCungCap.getTenNCC() };
				mdlThuocTinh.addRow(o1);
			}
			setTxt("", "");
		}
	}

	public boolean checkDuLieuRong(JTextField j, String noiDungThongBao) {
		if (j.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, noiDungThongBao, "Báo lỗi", JOptionPane.ERROR_MESSAGE);
			j.requestFocus();
			return false;
		}
		return true;
	}
}
