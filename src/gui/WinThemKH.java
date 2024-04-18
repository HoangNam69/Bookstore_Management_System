package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entity.KhachHang;
import service.impl.KhachHangServiceImpl;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class WinThemKH extends JFrame implements ActionListener{

	// JPanel
	private JPanel contentPane;

	// JTextFields
	private JTextField txtMaKhachHang;
	private JTextField txtSDT;
	private JTextField txtTenKhachHang;

	// JLabels
	private JLabel lblTitle;
	private JLabel lblMaKhachHang;
	private JLabel lblTenKhachHang;
	private JLabel lblSDT;
	private JLabel lblGioiTinh;
	private JLabel lblDiaChi;

	// JComboBoxes
	private JComboBox cmbDiaChi;
	private JComboBox cmbGioiTinh;

	// JButtons
	private JButton btnHuy;
	private JButton btnLamMoi;
	private JButton btnThemKhachHang;

	// Other objects
	private List<KhachHang> dsKhachHang;
	private KhachHangServiceImpl khachHangServiceImpl = new KhachHangServiceImpl();


	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws RemoteException 
	 */
	public WinThemKH(){

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTitle = new JLabel("THÊM KHÁCH HÀNG");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 11, 414, 32);
		contentPane.add(lblTitle);

		lblMaKhachHang = new JLabel("Mã khách hàng:");
		lblMaKhachHang.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMaKhachHang.setBounds(20, 55, 140, 25);
		contentPane.add(lblMaKhachHang);
		
		txtMaKhachHang = new JTextField();
		txtMaKhachHang.setBounds(184, 55, 240, 25);
		contentPane.add(txtMaKhachHang);
		txtMaKhachHang.setColumns(10);
		
		lblTenKhachHang = new JLabel("Tên khách hàng:");
		lblTenKhachHang.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTenKhachHang.setBounds(20, 100, 140, 25);
		contentPane.add(lblTenKhachHang);
		
		txtTenKhachHang = new JTextField();
		txtTenKhachHang.setColumns(10);
		txtTenKhachHang.setBounds(184, 100, 240, 25);
		contentPane.add(txtTenKhachHang);
		
		lblSDT = new JLabel("Số điện thoại:");
		lblSDT.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSDT.setBounds(20, 145, 140, 25);
		contentPane.add(lblSDT);
		
		txtSDT = new JTextField();
		txtSDT.setColumns(10);
		txtSDT.setBounds(184, 145, 240, 25);
		contentPane.add(txtSDT);
		
		lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGioiTinh.setBounds(20, 190, 114, 25);
		contentPane.add(lblGioiTinh);
		
		cmbGioiTinh = new JComboBox();
		cmbGioiTinh.setModel(new DefaultComboBoxModel(new String[] {"Nam", "Nữ"}));
		cmbGioiTinh.setBounds(184, 190, 240, 25);
		contentPane.add(cmbGioiTinh);
		
		lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDiaChi.setBounds(20, 235, 100, 25);
		contentPane.add(lblDiaChi);
		
		cmbDiaChi = new JComboBox();
		cmbDiaChi.setBounds(184, 235, 240, 25);
		cmbDiaChi.setModel(new DefaultComboBoxModel(new String[] { "Thủ Đức", "Quận 1", "Quận 2", "Quận 3 ", "Quận 4",
				"Quận 5", "Quận 6", "Quận 7", "Quận 8", "Quận 9", "Quận 10", "Quận 11", "Quận 12", "Gò Vấp", "Tân Bình",
				"Bình Tân", "Bình Thạnh", "Phú Nhuận", "Tân Phú", "Bình Chánh", "Cần Giờ", "Củ Chi", "Hóc Môn",
				"Nhà Bè" }));
		contentPane.add(cmbDiaChi);
		
		btnThemKhachHang = new JButton("Thêm");
		btnThemKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnThemKhachHang.setBounds(20, 300, 100, 35);
		contentPane.add(btnThemKhachHang);
		
		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnLamMoi.setBounds(158, 300, 110, 35);
		contentPane.add(btnLamMoi);
		
		btnHuy = new JButton("Huỷ");
		btnHuy.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnHuy.setBounds(314, 300, 110, 35);
		contentPane.add(btnHuy);
		btnThemKhachHang.addActionListener(this);
		btnHuy.addActionListener(this);
		btnLamMoi.addActionListener(this);
		txtMaKhachHang.setEditable(false);
		try {
			txtMaKhachHang.setText(auto_ID());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if(obj.equals(btnThemKhachHang)) {
			if(validData()) {
				
				try {
					dsKhachHang = khachHangServiceImpl.getDSKhachHang();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				for (KhachHang khg : dsKhachHang) {
					if(khg.getsDT().equalsIgnoreCase(txtSDT.getText().trim())) {
						JOptionPane.showMessageDialog(this, "Số điện thoại đã có người sử dụng, vui lòng nhập số điện thoại khác");
						txtSDT.requestFocus();
						return;
					}
				}
				System.out.println("a");
				KhachHang kh = revertKhachHangFromTextfields();
				try {
					if(khachHangServiceImpl.themKhachHang(kh)>0) {
						JOptionPane.showMessageDialog(this, "Thêm thành công 1 khách hàng, vui lòng tiếp tục thanh toán");
						this.setVisible(false);
					} else {
						JOptionPane.showMessageDialog(this, "Thêm thất bại");
						this.setVisible(false);
					}
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		else if(obj.equals(btnLamMoi)) {
			clearTxtfields();
		}
		else if(obj.equals(btnHuy)) {
			this.setVisible(false);
		}
	}
	public KhachHang revertKhachHangFromTextfields() {
		String maKH = txtMaKhachHang.getText();
		String tenKH = txtTenKhachHang.getText();
		String diaChi = cmbDiaChi.getSelectedItem().toString();
		String sdt = txtSDT.getText();
		boolean gioiTinh = cmbGioiTinh.getSelectedItem().toString() == "Nam" ? true : false;
		KhachHang kh = new KhachHang(maKH, tenKH, sdt, gioiTinh, diaChi);
		return kh;
	}
	private void clearTxtfields() {
		txtTenKhachHang.setText("");
		txtSDT.setText("");
		cmbDiaChi.setSelectedIndex(0);
		cmbGioiTinh.setSelectedIndex(0);
	}
	public  String auto_ID() throws SQLException {
		String idPrefix = "KH";
		LocalDate myObj = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd");
		String formattedString = myObj.format(formatter);
		int length = 0;
		length = khachHangServiceImpl.getDSKhachHang().size();
		String finalId = idPrefix + formattedString + String.format("%04d", length + 1);
		return finalId;
	}
	public boolean validData() {
		String makh = txtMaKhachHang.getText();
		String tenkh =txtTenKhachHang.getText();
		String diaChi = cmbDiaChi.getSelectedItem().toString();
		String sdt = txtSDT.getText();
		boolean gioiTinh = cmbGioiTinh.getSelectedItem().toString() == "Nam" ? true : false;
		
		if(!(tenkh.length()>0 && tenkh.matches("[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊ"
				+ "ỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s]*"))) {
			JOptionPane.showMessageDialog(this, "Tên khách hàng không hợp lệ");
			txtTenKhachHang.setText("");
			txtTenKhachHang.requestFocus();
			return false;
		}
		if(!(sdt.length()>0 && sdt.matches("(84|0[3|5|7|8|9])+([0-9]{8})\\b"))) {
			JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ");
			txtSDT.setText("");
			txtSDT.requestFocus();
			return false;
		}
		return true;
	}
}
