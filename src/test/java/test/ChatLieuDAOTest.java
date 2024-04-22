package test;

import entities.ChatLieu;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import service.impl.ChatLieuServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ChatLieuDAOTest {
    private ChatLieuServiceImpl chatLieuService;

    @BeforeAll
    public void setUp() {
        chatLieuService = new ChatLieuServiceImpl();
    }

    @Test
    public void testGetListChatLieu() throws Exception {
        ArrayList<ChatLieu> chatLieus = chatLieuService.getListChatLieu();
        assertNotNull(chatLieus, "Expected non-null list of ChatLieu");
        chatLieus.forEach(System.out::println);
    }

    @Test
    public void testThemChatLieu() throws Exception {
        ChatLieu chatLieu = new ChatLieu();
        chatLieu.setMaChatLieu("CL0012");
        chatLieu.setTenChatLieu("Test5");
        boolean result = chatLieuService.themChatLieu(chatLieu);
        assertTrue(result, "Expected true when adding a valid ChatLieu");
        if (result) {
            System.out.println("Thêm Chất Liệu thành công");
            System.out.println(chatLieu);
        }
    }

    @Test
    public void testTimChatLieu() throws Exception {
        String tenChatLieu = "Giấy";
        ChatLieu result = chatLieuService.timChatLieu(tenChatLieu);
        assertNotNull(result, "Expected non-null ChatLieu");
        if (result != null) {
            System.out.println("Tìm Chất Liệu thành công");
            System.out.println(result);
        }
    }

    @Test
    public void testGetChatLieu() {
        String maChatLieu = "CL002";
        List<ChatLieu> result = chatLieuService.getChatLieu(maChatLieu);
        assertNotNull(result, "Expected non-null list of ChatLieu");
        if (result != null) {
            System.out.println("Lấy Chất Liệu thành công");
            result.forEach(System.out::println);
        }
    }

    @AfterAll
    public void tearDown() {
        chatLieuService = null;
    }
}