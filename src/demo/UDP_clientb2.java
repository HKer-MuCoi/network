package demo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDP_clientb2 {
    public static void main(String[] args) throws IOException {
        System.out.println("Client start");
        //B1: khai bao DS
        DatagramSocket socket = new DatagramSocket();
        while(true)
        //B2: chuan bi du lieu de gui
        {
            Scanner sc = new Scanner(System.in);
            String str = sc.nextLine();
            if(str.equals("q")){
                break;
            }
            byte[] dataout = str.getBytes();
            InetAddress ip_sv = InetAddress.getByName("localhost");
            //B3: Dong goi va gui du lieu
            DatagramPacket datagrout = new DatagramPacket(dataout, dataout.length, ip_sv, 3000);
            socket.send(datagrout);
            //B4: khai bao DS de nhan
            byte[] buffin = new byte[1024];
            DatagramPacket datain = new DatagramPacket(buffin, buffin.length);
            //B5: nhan du lieu
            socket.receive(datain);
            String result = new String(datain.getData(), 0, buffin.length);
            //B6: hien KQ
            System.out.println("server return: " + result.trim());
        }
        socket.close();
    }
}
