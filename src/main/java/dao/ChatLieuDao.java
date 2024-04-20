package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entities.ChatLieu;

public class ChatLieuDao {
	private Connection con;
	private PreparedStatement ps = null;
	private ResultSet rs;
	private String query;
	private int rsCheck;

	public ChatLieuDao() {
		DBConnection connection = DBConnection.getInstance();
		con = connection.getConnection();
	}

	public ArrayList<ChatLieu> getListChatLieu() {
		ArrayList<ChatLieu> list = new ArrayList<>();
		query = "SELECT maChatLieu, tenChatLieu FROM ChatLieu";
		try (PreparedStatement ps = con.prepareStatement(query);
			 ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				ChatLieu chatLieu = new ChatLieu(rs.getString("maChatLieu"), rs.getString("tenChatLieu"));
				list.add(chatLieu);
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Handle the exception more gracefully according to your application's needs
		}
		return list;
	}

	public boolean themChatLieu(ChatLieu l) {
		query = "INSERT INTO ChatLieu (maChatLieu, tenChatLieu) VALUES (?, ?)";
		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, l.getMaChatLieu());
			ps.setString(2, l.getTenChatLieu());
			rsCheck = ps.executeUpdate();
			return rsCheck != 0;
		} catch (SQLException e) {
			e.printStackTrace(); // Handle the exception more gracefully according to your application's needs
			return false;
		}
	}


	public ChatLieu timChatLieu(String tenChatLieu) throws SQLException {
		query = "select * from ChatLieu where tenChatLieu = ?";
		ps = con.prepareStatement(query);
		ps.setString(1, tenChatLieu);
		rs = ps.executeQuery();
		while (rs.next()) {
			return new ChatLieu(rs.getString("maChatLieu"), rs.getString("tenChatLieu"));
		}
		return null;
	}

	public List<ChatLieu> getChatLieu(String maChatLieu) {
		List<ChatLieu> dsCL = new ArrayList<ChatLieu>();
		try {
			String query = "Select * from ChatLieu where maChatLieu = ?";
			ps = con.prepareStatement(query);
			ps.setString(1, maChatLieu);
			rs = ps.executeQuery();
			while (rs.next()) {
				String macl = rs.getString("maChatLieu");
				String tencl = rs.getString("tenChatLieu");
				ChatLieu cl = new ChatLieu(macl, tencl);
				dsCL.add(cl);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsCL;
	}
	public boolean kiemTraTonTaiChatLieu(String ten) throws SQLException {
		query = "select * from ChatLieu where tenChatLieu = N'"+ten+"'";
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		while (rs.next()) {
			return true;
		}
		return false;
	}
}
