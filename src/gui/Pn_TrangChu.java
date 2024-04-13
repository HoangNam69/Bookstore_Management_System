package gui;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Image;

public class Pn_TrangChu extends JPanel {

	/**
	 * Create the panel.
	 */
	public Pn_TrangChu() {
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

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 206, 209));
		panel.setBounds(0, 0, 500, 1000);
		add(panel);
		panel.setLayout(null);

		JLabel lblTenHieuSach = new JLabel("QUẢN LÝ HIỆU SÁCH ĐTD");
		lblTenHieuSach.setHorizontalAlignment(SwingConstants.CENTER);
		lblTenHieuSach.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTenHieuSach.setBounds(0, 29, 565, 106);
		panel.add(lblTenHieuSach);

		

	}
}
