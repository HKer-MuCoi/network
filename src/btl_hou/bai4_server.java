package btl_hou;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class bai4_server {
	public static void main(String args[]) throws Exception {
		ServerSocket soc = new ServerSocket(3000);
		System.out.println("FTP Server Started on Port Number 3000");
		while (true) {
			System.out.println("Waiting for Connection ...");
			transferfile t = new transferfile(soc.accept());

		}
	}
}

class transferfile extends Thread {
	Socket ClientSoc;

	DataInputStream din;
	DataOutputStream dout;
	private String user = "tuancong";
	private String pass = "123456";

	transferfile(Socket soc) {
		try {
			ClientSoc = soc;
			din = new DataInputStream(ClientSoc.getInputStream());
			dout = new DataOutputStream(ClientSoc.getOutputStream());
			System.out.println("FTP Client Connected ...");
			start();

		} catch (Exception ex) {
		}
	}

	void SendFile() throws Exception {
//		String filename = din.readUTF();
//		File f = new File(filename);
//		if (!f.exists()) {
//			dout.writeUTF("File Not Found");
//			return;
//		} else {
		dout.writeUTF("READY");
		FileInputStream fin = new FileInputStream("/home/tuancong/Downloads/input.jpg");
		int ch;
		do {
			ch = fin.read();
			dout.writeUTF(String.valueOf(ch));
		} while (ch != -1);
		fin.close();
		dout.writeUTF("File Receive Successfully");
//		}
	}

	void Login() throws Exception {
		String result = "0";
		String login = din.readUTF();
//		if (filename.compareTo("File not found") == 0) {
//			return;
//		}
//		File f = new File(filename);
		String[] parts = login.split("-");
		if (parts[0].equals(user) && parts[1].equals(pass)) {
			result = "1";
		}
		dout.writeUTF(result);
		if (result.equals("1")) {
			SendFile();
		}
//		String option;
//
//		if (f.exists()) {
//			dout.writeUTF("File Already Exists");
//			option = din.readUTF();
//		} else {
//			dout.writeUTF("SendFile");
//			option = "Y";
//		}
//
//		if (option.compareTo("Y") == 0) {
//			FileOutputStream fout = new FileOutputStream(f);
//			int ch;
//			String temp;
//			do {
//				temp = din.readUTF();
//				ch = Integer.parseInt(temp);
//				if (ch != -1) {
//					fout.write(ch);
//				}
//			} while (ch != -1);
//			fout.close();
//			dout.writeUTF("File Send Successfully");
//		} else {
//			return;
//		}

	}

	public void run() {
		while (true) {
			try {
				System.out.println("Waiting for Command ...");
				String Command = din.readUTF();
				if (Command.compareTo("GET") == 0) {
					System.out.println("\tGET Command Received ...");
					SendFile();
					continue;
				} else if (Command.compareTo("Login") == 0) {
					System.out.println("\tLogin Command Receiced ...");
					Login();
					continue;
				} else if (Command.compareTo("DISCONNECT") == 0) {
					System.out.println("\tDisconnect Command Received ...");
					System.exit(1);
				}
			} catch (Exception ex) {
			}
		}
	}
}