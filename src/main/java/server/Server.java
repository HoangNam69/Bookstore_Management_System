/**
 * @ (#) Server.java 1.0 21-Apr-24
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package server;

import java.io.DataInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

/**
 * @description:
 * @author: Le Hoang Nam
 * @date: 21-Apr-24
 * @version: 1.0
 */
public class Server {
    public static void main(String[] args) {
//        Port vào thi hoặc kiểm tra sẽ là 4 số cuối mã số sinh viên
//        Nếu có lỗi báo ngay với giảng viên

        try (ServerSocket serverSocket = new ServerSocket(2211)) {
//            Server luôn chạy và chờ client connect
//            khi server chạy thì sẽ tạo ra 1 server socket và chờ client connect
//            Khi client connect sẽ đợi server accept
            while (true) {
                // mỗi socket tương ứng với 1 client khi được accept
                Socket socket = serverSocket.accept();
                // in ra thông tin client
                System.out.println("Client connected: " + socket.getInetAddress().getHostName());
//                Với mỗi client sẽ tạo ra 1 thread để xử lý
                // khởi tạo task
                Server server = new Server(); // Nested class
                ClientHandler clientHandler = server.new ClientHandler(socket);
                Thread thread = new Thread(clientHandler);
                thread.start();


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class ClientHandler implements Runnable {
        private Socket socket;


        public ClientHandler(Socket socket) {
            this.socket = socket;

        }

        @Override
        public void run() {
//            Các công việc cần làm với client sẽ được xử lý ở đây
            try {
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                int choose;
                while (true) {
                    // xử lý dữ liệu từ client
                    choose = dis.readInt();
                    switch (choose) {
                        case 1:
                            // xử lý tìm kiếm theo title
                            String title = dis.readUTF();
//                            List<Course> courses = this.courseImpl.findCourseByTitle(title);
//                            oos.writeObject(courses);
                            break;
                        case 2:
                            // xử lý tìm kiếm theo id
                            break;
                        case 3:
                            // xử lý lấy ra danh sách các course có số tín chỉ lớn nhất
                            break;
                        default:
                            System.out.println("Invalid choose - Server");
                            break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
