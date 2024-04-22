package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.ChatLieu;

public interface ChatLieuService {
	public ArrayList<ChatLieu> getListChatLieu() throws Exception;

	public boolean themChatLieu(ChatLieu l) throws Exception;

	public boolean xoaChatLieu(String maChatLieu) throws Exception;

	public List<ChatLieu> getChatLieu(String maChatLieu) throws Exception;

	public ChatLieu timChatLieu(String tenChatLieu) throws SQLException;
}
