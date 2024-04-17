package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Image;

public class Pnl_TrangChu extends JPanel {

	/**
	 * Create the panel.
	 */
	public Pnl_TrangChu() {
		setSize(1500, 850);
		setLayout(null);

		ImageIcon icon = new ImageIcon(getClass().getResource("/gui/icon/TrangChu.jpg"));
		Image image = icon.getImage();
		Image newImage = image.getScaledInstance(1700, 1000, Image.SCALE_SMOOTH);
		ImageIcon newIcon = new ImageIcon(newImage);
		JLabel lblBackGround = new JLabel(newIcon);
		lblBackGround.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblBackGround.setOpaque(true);
		lblBackGround.setBounds(500, 0, 1700, 1000);
		add(lblBackGround);

		JPanel pnl = new JPanel();
		pnl.setBackground(new Color(0, 206, 209));
		pnl.setBounds(0, 0, 500, 1000);
		add(pnl);
		pnl.setLayout(null);

		JLabel lblTenHieuSach = new JLabel("QUẢN LÝ HIỆU SÁCH ĐTD");
		lblTenHieuSach.setHorizontalAlignment(SwingConstants.CENTER);
		lblTenHieuSach.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTenHieuSach.setBounds(0, 29, 565, 106);
		pnl.add(lblTenHieuSach);

		

	}
}
