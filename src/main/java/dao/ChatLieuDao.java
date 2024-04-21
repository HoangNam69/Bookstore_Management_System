package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import entities.ChatLieu;

import java.util.ArrayList;
import java.util.List;

public class ChatLieuDao {
	private EntityManager em;

	public ChatLieuDao() {
		em = Persistence.createEntityManagerFactory("JPA_ORM_MARIADB").createEntityManager();
	}

	public ArrayList<ChatLieu> getListChatLieu() {
		TypedQuery<ChatLieu> query = em.createQuery("SELECT c FROM ChatLieu c", ChatLieu.class);
		return new ArrayList<>(query.getResultList());
	}

	public boolean themChatLieu(ChatLieu l) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(l);
			tx.commit();
			return true;
		} catch (Exception e) {
			if (tx.isActive()) tx.rollback();
			e.printStackTrace();
			return false;
		}
	}

	public ChatLieu timChatLieu(String tenChatLieu) {
		TypedQuery<ChatLieu> query = em.createQuery("SELECT c FROM ChatLieu c WHERE c.tenChatLieu = :tenChatLieu", ChatLieu.class);
		query.setParameter("tenChatLieu", tenChatLieu);
		return query.getSingleResult();
	}

	public List<ChatLieu> getChatLieu(String maChatLieu) {
		TypedQuery<ChatLieu> query = em.createQuery("SELECT c FROM ChatLieu c WHERE c.maChatLieu = :maChatLieu", ChatLieu.class);
		query.setParameter("maChatLieu", maChatLieu);
		return query.getResultList();
	}

	public boolean kiemTraTonTaiChatLieu(String ten) {
		TypedQuery<ChatLieu> query = em.createQuery("SELECT c FROM ChatLieu c WHERE c.tenChatLieu = :tenChatLieu", ChatLieu.class);
		query.setParameter("tenChatLieu", ten);
		return !query.getResultList().isEmpty();
	}
}