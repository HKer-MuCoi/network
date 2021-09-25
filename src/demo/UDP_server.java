package demo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDP_server {
    public static void main(String[] args) throws IOException {
        System.out.println("Server Start!");
        //B1: Khai bao DS
        DatagramSocket socket = new DatagramSocket(3000);
        //B2: khoi tao DP
        byte[] buffin = new byte[1024];
        DatagramPacket datain = new DatagramPacket(buffin, buffin.length);
        socket.receive(datain);
        String result = new String(datain.getData(),0, buffin.length);
        System.out.println(result);
        //B3: gui tra client
        DatagramSocket socketgui = new DatagramSocket();
        //B4: xu ly du lieu
        String dataout = "Server receive "+ result;
        //B5: gui tra client
        byte[] buffout = dataout.getBytes();
        DatagramPacket datagrout = new DatagramPacket(buffout, buffout.length, datain.getAddress(), datain.getPort());
        socketgui.send(datagrout);
        //B6: dong ket noi
        socket.close();
        socketgui.close();
    }
}
