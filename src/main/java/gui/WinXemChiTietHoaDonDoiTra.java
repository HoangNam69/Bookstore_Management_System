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

	// JLabels
	private JLabel lblMaHoaDonDoiTra;
	private JLabel lblNhanVienLapHoaDon;
	private JLabel lblTenKhachHang;
	private JLabel lblNgayLapHoaDon;
	private JLabel lblTenKhachHangLabel;
	private JLabel lblNgayLapHoaDonLabel;
	private JLabel lblNhanVienLabel;
	private JLabel lblMaHDDT;
	private JLabel lblTienPhaiTruLabel;
	private JLabel lblTieuDe;
	private JLabel lblTienPhaiTru;
	private JLabel lblTienKhachDua;
	private JLabel lblMaHoaDonCu = new JLabel();

	// JPanels
	private JPanel pnlContainerButton;

	// JButton
	private JButton btnThoat;

	// JTextArea
	private JTextArea txaGhiChu;

	// Strings
	String maHDDT;
	String maHD;
	String ngayLap;
	String tenNhanVien;
	String tenKhachHang;
	String tienKhachDua;
	String ghiChu;
	String tienPhaiTru;

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

		lblMaHDDT = new JLabel("Mã hóa đơn đổi trả:");
		lblMaHDDT.setForeground(new Color(72, 61, 139));
		lblMaHDDT.setFont(new Font("Arial", Font.BOLD, 16));
		lblMaHDDT.setBounds(10, 94, 160, 38);
		getContentPane().add(lblMaHDDT);

		lblNhanVienLapHoaDon = new JLabel();
		lblNhanVienLapHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNhanVienLapHoaDon.setBackground(new Color(255, 255, 255));
		lblNhanVienLapHoaDon.setBounds(236, 195, 273, 38);
		getContentPane().add(lblNhanVienLapHoaDon);

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

		lblTieuDe = new JLabel("CHI TIẾT HÓA ĐƠN ĐỔI TRẢ");
		lblTieuDe.setForeground(SystemColor.textHighlight);
		lblTieuDe.setVerticalAlignment(SwingConstants.TOP);
		lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDe.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblTieuDe.setBounds(10, 10, 751, 39);
		getContentPane().add(lblTieuDe);

		lblMaHoaDonDoiTra = new JLabel("maHoaDon");
		lblMaHoaDonDoiTra.setBorder(BorderFactory.createLineBorder(Color.cyan));
		lblMaHoaDonDoiTra.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMaHoaDonDoiTra.setBounds(236, 94, 240, 38);
		getContentPane().add(lblMaHoaDonDoiTra);

		lblTenKhachHangLabel = new JLabel("Tên khách hàng:");
		lblTenKhachHangLabel.setForeground(new Color(72, 61, 139));
		lblTenKhachHangLabel.setFont(new Font("Arial", Font.BOLD, 16));
		lblTenKhachHangLabel.setBounds(10, 243, 170, 38);
		getContentPane().add(lblTenKhachHangLabel);

		lblNgayLapHoaDonLabel = new JLabel("Ngày lập hóa đơn:");
		lblNgayLapHoaDonLabel.setForeground(new Color(72, 61, 139));
		lblNgayLapHoaDonLabel.setFont(new Font("Arial", Font.BOLD, 16));
		lblNgayLapHoaDonLabel.setBounds(10, 291, 196, 38);
		getContentPane().add(lblNgayLapHoaDonLabel);

		lblNhanVienLabel = new JLabel("Nhân viên lập hóa đơn:");
		lblNhanVienLabel.setForeground(new Color(72, 61, 139));
		lblNhanVienLabel.setFont(new Font("Arial", Font.BOLD, 16));
		lblNhanVienLabel.setBounds(10, 193, 204, 40);
		getContentPane().add(lblNhanVienLabel);

		pnlContainerButton = new JPanel();
		pnlContainerButton.setBounds(10, 599, 1096, 54);
		getContentPane().add(pnlContainerButton);
		pnlContainerButton.setLayout(null);

		btnThoat = new JButton("Thoát");
		btnThoat.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnThoat.setBounds(330, 10, 132, 39);
		pnlContainerButton.add(btnThoat);

		txaGhiChu = new JTextArea();
		txaGhiChu.setFont(new Font("Courier New", Font.PLAIN, 13));
		txaGhiChu.setBounds(176, 480, 485, 109);
		txaGhiChu.setBorder(BorderFactory.createLineBorder(Color.black));
		getContentPane().add(txaGhiChu);

		JLabel lblGhiChuMoTa = new JLabel("Ghi chú(Mô tả):");
		lblGhiChuMoTa.setForeground(new Color(72, 61, 139));
		lblGhiChuMoTa.setFont(new Font("Arial", Font.BOLD, 16));
		lblGhiChuMoTa.setBounds(325, 447, 160, 23);
		getContentPane().add(lblGhiChuMoTa);

		lblTienPhaiTruLabel = new JLabel("Tiền phải trừ:");
		lblTienPhaiTruLabel.setForeground(new Color(72, 61, 139));
		lblTienPhaiTruLabel.setFont(new Font("Arial", Font.BOLD, 16));
		lblTienPhaiTruLabel.setBounds(10, 387, 184, 38);
		getContentPane().add(lblTienPhaiTruLabel);

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


		lblMaHoaDonCu = new JLabel();
		lblMaHoaDonCu.setText(maHD);
		lblMaHoaDonCu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMaHoaDonCu.setBackground(Color.WHITE);
		lblMaHoaDonCu.setBounds(236, 147, 240, 38);
		getContentPane().add(lblMaHoaDonCu);

		btnThoat.addActionListener(this);
	}

	public void setValue() {
		lblMaHoaDonDoiTra.setText(maHDDT);
		lblMaHoaDonCu.setText(maHD);
		lblNhanVienLapHoaDon.setText(tenNhanVien);
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
