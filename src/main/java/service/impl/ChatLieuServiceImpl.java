package service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dao.ChatLieuDao;
import entities.ChatLieu;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import service.ChatLieuService;

public class ChatLieuServiceImpl implements ChatLieuService {
	private ChatLieuDao chatLieuDao;

	public ChatLieuServiceImpl() {
		this.chatLieuDao = new ChatLieuDao();
	}

	@Override
	public ArrayList<ChatLieu> getListChatLieu() throws Exception {
		return chatLieuDao.getListChatLieu();
	}

	@Override
	public boolean themChatLieu(ChatLieu l) throws Exception {
		return chatLieuDao.themChatLieu(l);
	}

	@Override
	public boolean xoaChatLieu(String maChatLieu) {
		return false;
	}

	@Override
	public List<ChatLieu> getChatLieu(String maChatLieu) {
		return chatLieuDao.getChatLieu(maChatLieu);
	}

	@Override
	public ChatLieu timChatLieu(String tenChatLieu) throws SQLException {
		return chatLieuDao.timChatLieu(tenChatLieu);
	}
}