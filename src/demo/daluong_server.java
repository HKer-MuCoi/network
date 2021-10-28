package demo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class daluong_server {
	public static void main(String[] args) throws IOException {
		System.out.println("Server đã chạy");
		ServerSocket serverSocket = new ServerSocket(3000);
		// Khởi tạo biến đếm số luồng
		int id_luong = 0;
		while (true) {
			// Chờ kết nối từ phía Client
			Socket clientSocket = serverSocket.accept();
			// Mỗi lần kết nối chúng ta sẽ khởi tạo luồng mới với 2 thông số clientSocket,id
			// luồng
			ThreadReply threadreply = new ThreadReply(clientSocket, id_luong++);
			// Khởi chạy luồng
			threadreply.start();
		}
	}
}

class ThreadReply extends Thread {
	Socket clientSocket;
	int id;
	boolean dangchay = true;

	public ThreadReply(Socket s, int i) {
		clientSocket = s;
		id = i;

	}

	public void run() {
		System.out.println("Của ta id-" + id + " Của máy id-" + Thread.currentThread().getId());

		try {
			DataInputStream in = new DataInputStream(clientSocket.getInputStream());
			DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
			while (dangchay) {
				String noidung = in.readUTF();
				System.out.println("id-" + id + " Trả lời: " + noidung);
				if (noidung.equals("q")) {
					dangchay = false;
					System.out.println("id-" + id + " đã dừng");

				}
				out.writeUTF("Sever trả về: " + noidung);
				out.flush();
			}
			in.close();
			out.close();
			clientSocket.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}