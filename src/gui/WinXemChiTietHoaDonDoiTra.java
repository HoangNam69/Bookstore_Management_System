package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class WinXemChiTietHoaDonDoiTra extends JFrame implements ActionListener {
	// String variables
	private String maHDDT;
	private String maHD;
	private String tenNhanVien;
	private String ngayLap;
	private String tenKhachHang;
	private String ghiChu;
	private String tienKhachDua;
	private String tienPhaiTru;

	// JLabel variables
	private JLabel lblMaHoaDonDoiTra;
	private JLabel lblNhanVienLap;
	private JLabel lblTenKhach;
	private JLabel lblNgayLapHoaDon;
	private JLabel lblTieuDe;
	private JLabel lblMaHoaDon;
	private JLabel lblTenKhachHang;
	private JLabel lblNgayLap;
	private JLabel lblNhanVien;
	private JLabel lblGhiChu;
	private JLabel lblTienPhaiTru;
	private JLabel lblTienPhaiTruGiaTri;
	private JLabel lblTienKhachDua;
	private JLabel lblTienKhachDuaGiaTri;
	private JLabel lblMaHDCu;
	private JLabel lblMaHDCuGiaTri;

	// JPanel variables
	private JPanel pnlChiTiet;
	private JPanel pnlThongTin;

	// JButton and JTextArea variables
	private JButton btnThoat;
	private JTextArea txaGhiChu;


	@SuppressWarnings("deprecation")
	public WinXemChiTietHoaDonDoiTra(String maHDDT, String maHD, String tenNV, String tenKH, String ngayLapHD,
									 String ghiChu, String tienKhachDua, String tienPhaiTru) {
		System.out.println("Ma hd cu " + maHD);
		this.maHDDT = maHDDT;
		this.maHD = maHD;
		this.tenNhanVien = tenNV;
		this.ngayLap = ngayLapHD;
		this.tenKhachHang = tenKH;
		this.ghiChu = ghiChu;
		this.tienKhachDua = tienKhachDua;
		this.tienPhaiTru = tienPhaiTru;
		setTitle("Chi tiết hóa đơn đổi trả");
		setResizable(false);
		setSize(785, 700);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		lblMaHoaDonDoiTra = new JLabel("Mã hóa đơn đổi trả:");
		lblMaHoaDonDoiTra.setForeground(new Color(72, 61, 139));
		lblMaHoaDonDoiTra.setFont(new Font("Arial", Font.BOLD, 16));
		lblMaHoaDonDoiTra.setBounds(10, 94, 160, 38);
		getContentPane().add(lblMaHoaDonDoiTra);

		lblNhanVienLap = new JLabel();
		lblNhanVienLap.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNhanVienLap.setBackground(new Color(255, 255, 255));
		lblNhanVienLap.setBounds(236, 195, 273, 38);
		getContentPane().add(lblNhanVienLap);

		lblTenKhach = new JLabel();
		lblTenKhach.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTenKhach.setBackground(Color.WHITE);
		lblTenKhach.setBounds(236, 232, 240, 38);
		getContentPane().add(lblTenKhach);

		lblNgayLapHoaDon = new JLabel();
		lblNgayLapHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNgayLapHoaDon.setBackground(Color.WHITE);
		lblNgayLapHoaDon.setBounds(236, 291, 240, 38);
		getContentPane().add(lblNgayLapHoaDon);

		pnlChiTiet = new JPanel();
		pnlChiTiet.setBounds(10, 38, 285, 46);
		getContentPane().add(pnlChiTiet);
		pnlChiTiet.setLayout(null);

		lblTieuDe = new JLabel("CHI TIẾT HÓA ĐƠN ĐỔI TRẢ");
		lblTieuDe.setForeground(SystemColor.textHighlight);
		lblTieuDe.setVerticalAlignment(SwingConstants.TOP);
		lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDe.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblTieuDe.setBounds(10, 10, 751, 39);
		getContentPane().add(lblTieuDe);

		lblMaHoaDon = new JLabel("maHoaDon");
		lblMaHoaDon.setBorder(BorderFactory.createLineBorder(Color.cyan));
		lblMaHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMaHoaDon.setBounds(236, 94, 240, 38);
		getContentPane().add(lblMaHoaDon);

		lblTenKhachHang = new JLabel("Tên khách hàng:");
		lblTenKhachHang.setForeground(new Color(72, 61, 139));
		lblTenKhachHang.setFont(new Font("Arial", Font.BOLD, 16));
		lblTenKhachHang.setBounds(10, 243, 170, 38);
		getContentPane().add(lblTenKhachHang);

		lblNgayLap = new JLabel("Ngày lập hóa đơn:");
		lblNgayLap.setForeground(new Color(72, 61, 139));
		lblNgayLap.setFont(new Font("Arial", Font.BOLD, 16));
		lblNgayLap.setBounds(10, 291, 196, 38);
		getContentPane().add(lblNgayLap);

		lblNhanVien = new JLabel("Nhân viên lập hóa đơn:");
		lblNhanVien.setForeground(new Color(72, 61, 139));
		lblNhanVien.setFont(new Font("Arial", Font.BOLD, 16));
		lblNhanVien.setBounds(10, 193, 204, 40);
		getContentPane().add(lblNhanVien);

		pnlThongTin = new JPanel();
		pnlThongTin.setBounds(10, 599, 1096, 54);
		getContentPane().add(pnlThongTin);
		pnlThongTin.setLayout(null);

		btnThoat = new JButton("Thoát");
		btnThoat.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnThoat.setBounds(330, 10, 132, 39);
		pnlThongTin.add(btnThoat);

		txaGhiChu = new JTextArea();
		txaGhiChu.setFont(new Font("Courier New", Font.PLAIN, 13));
		txaGhiChu.setBounds(176, 480, 485, 109);
		txaGhiChu.setBorder(BorderFactory.createLineBorder(Color.black));
		getContentPane().add(txaGhiChu);

		lblGhiChu = new JLabel("Ghi chú(Mô tả):");
		lblGhiChu.setForeground(new Color(72, 61, 139));
		lblGhiChu.setFont(new Font("Arial", Font.BOLD, 16));
		lblGhiChu.setBounds(325, 447, 160, 23);
		getContentPane().add(lblGhiChu);

		lblTienPhaiTru = new JLabel("Tiền phải trừ:");
		lblTienPhaiTru.setForeground(new Color(72, 61, 139));
		lblTienPhaiTru.setFont(new Font("Arial", Font.BOLD, 16));
		lblTienPhaiTru.setBounds(10, 387, 184, 38);
		getContentPane().add(lblTienPhaiTru);

		lblTienPhaiTruGiaTri = new JLabel();
		lblTienPhaiTruGiaTri.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTienPhaiTruGiaTri.setBackground(Color.WHITE);
		lblTienPhaiTruGiaTri.setBounds(236, 392, 240, 33);
		getContentPane().add(lblTienPhaiTruGiaTri);

		lblTienKhachDua = new JLabel("Tiền khách đã đưa:");
		lblTienKhachDua.setForeground(new Color(72, 61, 139));
		lblTienKhachDua.setFont(new Font("Arial", Font.BOLD, 16));
		lblTienKhachDua.setBounds(10, 339, 196, 38);
		getContentPane().add(lblTienKhachDua);

		lblTienKhachDuaGiaTri = new JLabel();
		lblTienKhachDuaGiaTri.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTienKhachDuaGiaTri.setBackground(Color.WHITE);
		lblTienKhachDuaGiaTri.setBounds(236, 343, 240, 39);
		getContentPane().add(lblTienKhachDuaGiaTri);

		lblMaHDCu = new JLabel();
		lblMaHDCu.setText("Mã hóa đơn cũ");
		lblMaHDCu.setForeground(new Color(72, 61, 139));
		lblMaHDCu.setFont(new Font("Arial", Font.BOLD, 16));
		lblMaHDCu.setBounds(10, 143, 189, 40);
		getContentPane().add(lblMaHDCu);

		lblMaHDCuGiaTri = new JLabel();
		lblMaHDCuGiaTri.setText(maHD);
		lblMaHDCuGiaTri.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMaHDCuGiaTri.setBackground(Color.WHITE);
		lblMaHDCuGiaTri.setBounds(236, 147, 240, 38);
		getContentPane().add(lblMaHDCuGiaTri);

		btnThoat.addActionListener(this);
	}

	public void setValue() {
		lblMaHoaDonDoiTra.setText(maHDDT);
		lblMaHoaDon.setText(maHD);
		lblNhanVienLap.setText(tenNhanVien);
		lblTenKhach.setText(tenKhachHang);
		lblNgayLapHoaDon.setText(ngayLap);
		lblTienKhachDuaGiaTri.setText(tienKhachDua);
		lblTienPhaiTruGiaTri.setText(tienPhaiTru);
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
}
