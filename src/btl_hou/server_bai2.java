package btl_hou;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Scanner;

public class server_bai2 {
	private static Scanner sc;
	private static DatagramSocket socket;
	public static void main(String[] args) {
		try{
			System.out.println("Server đang chạy!");
			socket = new DatagramSocket(3000);
			while(true) {
				byte[] bufIn = new byte[1024];
				DatagramPacket dataIn = new DatagramPacket(bufIn,bufIn.length);
				socket.receive(dataIn);
				String str = new String(dataIn.getData(),0,dataIn.getLength());
				System.out.println("Client: "+str);

				DatagramSocket socketout = new DatagramSocket();
				sc = new Scanner(System.in);
				System.out.print("Reply Client: ");
				String strOut = sc.nextLine();
				if(strOut.equalsIgnoreCase("exit")) {
					break;
				}
				byte[] bufOut = strOut.getBytes();
				DatagramPacket dataOut= new DatagramPacket(bufOut,bufOut.length,dataIn.getAddress(),dataIn.getPort());
				socketout.send(dataOut);
                socketout.close();
				}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}