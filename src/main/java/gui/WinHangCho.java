package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import entity.KhachHang;
import entity.SanPham;
import service.impl.KhachHangServiceImpl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.JButton;

public class WinHangCho extends JFrame implements ActionListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel mdlKhachHang;
	private JTable tblKhachHang;
	private JScrollPane scrKhachHang;
	private JButton btnThanhToan;
	private KhachHangServiceImpl khachHangServiceImpl = new KhachHangServiceImpl();
	private JLabel lblTitle;
	private JButton btnThoat;
	private ButtonGroup group;
	private JButton btnXoa;
	Pnl_TaoHoaDon pnl_TaoHoaDon;
	ArrayList<KhachHang> listKhachHang;
	public HashMap<String, ArrayList<SanPham>> dsHoaDonCho = new HashMap<String, ArrayList<SanPham>>();
	private String sdt = "";
	private ShareData shareData;

	public WinHangCho(HashMap<String, ArrayList<SanPham>> dsHoaDonCho, ShareData data){
		this.shareData = data;
		this.dsHoaDonCho = dsHoaDonCho;
		setResizable(false);
		setSize(630, 600);
		
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		lblTitle = new JLabel("HÓA ĐƠN CHỜ");
		lblTitle.setVerticalAlignment(SwingConstants.TOP);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 6, 586, 28);
		getContentPane().add(lblTitle);
		String[] cols = { "Tên khách hàng", "Số điện thoại khách hàng" };
		mdlKhachHang = new DefaultTableModel(cols, 0);
		tblKhachHang = new JTable(mdlKhachHang);
		tblKhachHang.setBorder(new LineBorder(new Color(0, 0, 0)));
		tblKhachHang.setFont(new Font("Tahoma", Font.CENTER_BASELINE, 17));
		scrKhachHang = new JScrollPane(tblKhachHang);
		scrKhachHang.setBounds(10, 117, 596, 436);

		tblKhachHang.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 14));
		tblKhachHang.setAutoCreateRowSorter(true);
		tblKhachHang.setRowHeight(25);
		scrKhachHang.getViewport().setBackground(Color.WHITE);
		tblKhachHang.getTableHeader().setPreferredSize(new Dimension(0, 40));
		getContentPane().add(scrKhachHang);

		btnThanhToan = new JButton("Thanh toán tiếp");
		btnThanhToan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnThanhToan.setBounds(82, 61, 207, 28);
		getContentPane().add(btnThanhToan);
		group = new ButtonGroup();

		btnThoat = new JButton("Thoát");
		btnThoat.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnThoat.setBounds(508, 10, 98, 28);
		getContentPane().add(btnThoat);
		
		btnXoa = new JButton("Xóa hàng chờ");
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnXoa.setBounds(329, 61, 207, 28);
		getContentPane().add(btnXoa);
		loadDuLieu();
		
		btnXoa.addActionListener(this);
		btnThanhToan.addActionListener(this);
		btnThoat.addActionListener(this);
		tblKhachHang.addMouseListener(this);
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
		Object o = e.getSource();
		if(o.equals(btnThanhToan)) {
			thanhToan();
		}
		if(o.equals(btnThoat)) {
			Thoat();
		}
		if(o.equals(btnXoa)) {
			xoaHoaDon();
		}
		
	}

	public HashMap<String, ArrayList<SanPham>> getDsHoaDonCho() {
		return dsHoaDonCho;
	}
	
	public void setDsHoaDonCho(HashMap<String, ArrayList<SanPham>> dsHoaDon) {
		dsHoaDonCho = dsHoaDon;
		
	}
	public void loadDuLieu() {
		listKhachHang = new ArrayList<>();
		for (String sdtKH : dsHoaDonCho.keySet()) {
			KhachHang khachHang = null;
			try {
				khachHang = khachHangServiceImpl.timKhachHangBangSDT(sdtKH);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			listKhachHang.add(khachHang);
		}
		
		for (KhachHang khachHang : listKhachHang) {
			Object [] o = {khachHang.getHoTenKhachHang(),khachHang.getsDT()};
			mdlKhachHang.addRow(o);
		}
	}
	public void thanhToan() {
		int row = tblKhachHang.getSelectedRow();
		if(row == -1) {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm thanh toán","Báo lỗi",JOptionPane.ERROR_MESSAGE);
			return;
		}
		sdt = mdlKhachHang.getValueAt(row, 1).toString();
		ArrayList<SanPham> listSPThanhToan = dsHoaDonCho.get(sdt);
		dsHoaDonCho.remove(sdt);
		new Thread(()->{
			synchronized (shareData) {
				shareData.setThanhToan(true);
				shareData.setSdtThanhToan(sdt);
				shareData.setDsSanPhamThanhToanTiep(listSPThanhToan);
				shareData.setDsHoaDonCho(dsHoaDonCho);
				shareData.notifyAll();
				this.setVisible(false);	
			}
		}).start();
	}
	public void xoaHoaDon() {
		int row = tblKhachHang.getSelectedRow();
		if(row == -1) {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm xóa","Báo lỗi",JOptionPane.ERROR_MESSAGE);
			return;
		}
		dsHoaDonCho.remove(sdt);
		shareData.setDsHoaDonCho(dsHoaDonCho);
		mdlKhachHang.removeRow(row);
	}
	public void Thoat() {
		new Thread(()->{
			synchronized (shareData) {
				shareData.notifyAll();
				this.setVisible(false);
			}
		}).start();
	}
}