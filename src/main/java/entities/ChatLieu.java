package entities;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table(name = "ChatLieu")
public class ChatLieu  implements Serializable {
	@Id
	@Column(unique = true, nullable = false)
	private String maChatLieu;
	@Column(unique = true, nullable = false)
	private String tenChatLieu;

//	Moi quan he giua chat lieu voi san pham
	@OneToMany(mappedBy = "chatLieu", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<SanPham> sanPhams;


	public ChatLieu(String maChatLieu) {
		super();
		this.maChatLieu = maChatLieu;
	}

	public ChatLieu(String maChatLieu, String tenChatLieu) {
		super();
		this.maChatLieu = maChatLieu;
		this.tenChatLieu = tenChatLieu;
	}

	public String getMaChatLieu() {
		return maChatLieu;
	}

	public void setMaChatLieu(String maChatLieu) {
		this.maChatLieu = maChatLieu;
	}

	public String getTenChatLieu() {
		return tenChatLieu;
	}

	public void setTenChatLieu(String tenChatLieu) {
		this.tenChatLieu = tenChatLieu;
	}

	@Override
	public String toString() {
		return "ChatLieu [maChatLieu=" + maChatLieu + ", tenChatLieu=" + tenChatLieu + "]";
	}

}
