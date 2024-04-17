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
	private static final long serialVersionUID = 1L;
	private JLabel lblNhanVienLapHD;
	private JLabel lblTenKhachHang;
	private JLabel lblNgayLapHoaDon;
	private JLabel lblMaHoaDonDT;
	private JLabel lblTenKH;
	private JLabel lblTenKhachHang_1 = new JLabel();
	private JLabel lblNhanVien;
	private JButton btnThoat;
	String maHDDT;
	String maHD;
	String ngayLap;
	String tenNhanVien;
	String tenKhachHang;
	String tienKhachDua;
	String ghiChu;
	String tienPhaiTru;
	private JLabel lblTienPhaiTru;
	private JTextArea txaGhiChu;
	private JLabel lblTienKhachDua;
	

	@SuppressWarnings("deprecation")
	public WinXemChiTietHoaDonDoiTra(String maHDDT, String maHD, String tenNV, String tenKH, String ngayLapHD,
									 String ghiChu, String tienKhachDua, String tienPhaiTru) {
		System.out.println("Ma hd cu "+maHD );
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

		JLabel lblMaHDDT = new JLabel("Mã hóa đơn đổi trả:");
		lblMaHDDT.setForeground(new Color(72, 61, 139));
		lblMaHDDT.setFont(new Font("Arial", Font.BOLD, 16));
		lblMaHDDT.setBounds(10, 94, 160, 38);
		getContentPane().add(lblMaHDDT);

		lblNhanVienLapHD = new JLabel();
		lblNhanVienLapHD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNhanVienLapHD.setBackground(new Color(255, 255, 255));
		lblNhanVienLapHD.setBounds(236, 195, 273, 38);
		getContentPane().add(lblNhanVienLapHD);

		lblTenKhachHang = new JLabel();
		lblTenKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTenKhachHang.setBackground(Color.WHITE);
		lblTenKhachHang.setBounds(236, 232, 240, 38);
		getContentPane().add(lblTenKhachHang);

		lblNgayLapHoaDon = new JLabel();
		lblNgayLapHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNgayLapHoaDon.setBackground(Color.WHITE);
		lblNgayLapHoaDon.setBounds(236, 291, 240, 38);
		getContentPane().add(lblNgayLapHoaDon);

		JPanel panel = new JPanel();
		panel.setBounds(10, 38, 285, 46);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("CHI TIẾT HÓA ĐƠN ĐỔI TRẢ");
		lblNewLabel.setForeground(SystemColor.textHighlight);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(10, 10, 751, 39);
		getContentPane().add(lblNewLabel);

		lblMaHoaDonDT = new JLabel("maHoaDon");
		lblMaHoaDonDT.setBorder(BorderFactory.createLineBorder(Color.cyan));
		lblMaHoaDonDT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMaHoaDonDT.setBounds(236, 94, 240, 38);
		getContentPane().add(lblMaHoaDonDT);

		lblTenKH = new JLabel("Tên khách hàng:");
		lblTenKH.setForeground(new Color(72, 61, 139));
		lblTenKH.setFont(new Font("Arial", Font.BOLD, 16));
		lblTenKH.setBounds(10, 243, 170, 38);
		getContentPane().add(lblTenKH);

		lblNgayLapHoaDon = new JLabel("Ngày lập hóa đơn:");
		lblNgayLapHoaDon.setForeground(new Color(72, 61, 139));
		lblNgayLapHoaDon.setFont(new Font("Arial", Font.BOLD, 16));
		lblNgayLapHoaDon.setBounds(10, 291, 196, 38);
		getContentPane().add(lblNgayLapHoaDon);

		lblNhanVien = new JLabel("Nhân viên lập hóa đơn:");
		lblNhanVien.setForeground(new Color(72, 61, 139));
		lblNhanVien.setFont(new Font("Arial", Font.BOLD, 16));
		lblNhanVien.setBounds(10, 193, 204, 40);
		getContentPane().add(lblNhanVien);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 599, 1096, 54);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		btnThoat = new JButton("Thoát");
		btnThoat.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnThoat.setBounds(330, 10, 132, 39);
		panel_1.add(btnThoat);

		txaGhiChu = new JTextArea();
		txaGhiChu.setFont(new Font("Courier New", Font.PLAIN, 13));
		txaGhiChu.setBounds(176, 480, 485, 109);
		txaGhiChu.setBorder(BorderFactory.createLineBorder(Color.black));
		getContentPane().add(txaGhiChu);

		JLabel lblNewLabel_6_1 = new JLabel("Ghi chú(Mô tả):");
		lblNewLabel_6_1.setForeground(new Color(72, 61, 139));
		lblNewLabel_6_1.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_6_1.setBounds(325, 447, 160, 23);
		getContentPane().add(lblNewLabel_6_1);

		lblTienPhaiTru = new JLabel("Tiền phải trừ:");
		lblTienPhaiTru.setForeground(new Color(72, 61, 139));
		lblTienPhaiTru.setFont(new Font("Arial", Font.BOLD, 16));
		lblTienPhaiTru.setBounds(10, 387, 184, 38);
		getContentPane().add(lblTienPhaiTru);

		lblTienPhaiTru = new JLabel();
		lblTienPhaiTru.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTienPhaiTru.setBackground(Color.WHITE);
		lblTienPhaiTru.setBounds(236, 392, 240, 33);
		getContentPane().add(lblTienPhaiTru);

		lblTienKhachDua = new JLabel("Tiền khách đã đưa:");
		lblTienKhachDua.setForeground(new Color(72, 61, 139));
		lblTienKhachDua.setFont(new Font("Arial", Font.BOLD, 16));
		lblTienKhachDua.setBounds(10, 339, 196, 38);
		getContentPane().add(lblTienKhachDua);

		lblTienKhachDua = new JLabel();
		lblTienKhachDua.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTienKhachDua.setBackground(Color.WHITE);
		lblTienKhachDua.setBounds(236, 343, 240, 39);
		getContentPane().add(lblTienKhachDua);
		setValue();

		JLabel lblMaHDCu = new JLabel();
		lblMaHDCu.setText("Mã hóa đơn cũ");
		lblMaHDCu.setForeground(new Color(72, 61, 139));
		lblMaHDCu.setFont(new Font("Arial", Font.BOLD, 16));
		lblMaHDCu.setBounds(10, 143, 189, 40);
		getContentPane().add(lblMaHDCu);

		lblTenKhachHang_1 = new JLabel();
		lblTenKhachHang_1.setText(maHD);
		lblTenKhachHang_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTenKhachHang_1.setBackground(Color.WHITE);
		lblTenKhachHang_1.setBounds(236, 147, 240, 38);
		getContentPane().add(lblTenKhachHang_1);

		btnThoat.addActionListener(this);
	}

	public void setValue() {
		lblMaHoaDonDT.setText(maHDDT);
		lblTenKhachHang_1.setText(maHD);
		lblNhanVienLapHD.setText(tenNhanVien);
		lblTenKhachHang.setText(tenKhachHang);
		lblNgayLapHoaDon.setText(ngayLap);
		lblTienKhachDua.setText(tienKhachDua);
		lblTienPhaiTru.setText(tienPhaiTru);
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