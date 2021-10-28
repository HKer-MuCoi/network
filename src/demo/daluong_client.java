package demo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class daluong_client {
	public static void main(String[] args) {
		System.out.println("Client đã chạy");
		try {
			Socket socket = new Socket("127.0.0.1", 3000);
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			DataInputStream in = new DataInputStream(socket.getInputStream());

			Scanner sc = new Scanner(System.in);
			while (true) {
				System.out.print("Mời bạn nhập: ");
				String nhap = sc.nextLine();
				out.writeUTF(nhap);
				out.flush();
				if (nhap.equals("q")) {
					break;
				}
				String nhan = in.readUTF();
				System.out.println(nhan);
			}
			sc.close();
			in.close();
			out.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}