package demo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDP_serverb2 {
    public static void main(String[] args) throws IOException {
        System.out.println("Server Start!");
        //B1: Khai bao DS
        DatagramSocket socket = new DatagramSocket(3000);
        DatagramSocket socketgui = new DatagramSocket();
        //B2: khoi tao DP
        while(true) {
            byte[] buffin = new byte[1024];
            DatagramPacket datain = new DatagramPacket(buffin, buffin.length);
            socket.receive(datain);
            String string_in = new String(datain.getData(), 0, buffin.length);
            if(string_in.equals("q")){
                break;
            }
            string_in = string_in.replaceAll("[0-9]", "");
            String result = new StringBuilder(string_in).reverse().toString();
            //B3: gui tra client
            //B4: xu ly du lieu
            String dataout = "Server response: " + result.trim();
            //B5: gui tra client
            byte[] buffout = dataout.getBytes();
            DatagramPacket datagrout = new DatagramPacket(buffout, buffout.length, datain.getAddress(), datain.getPort());
            socketgui.send(datagrout);
        }
        //B6: dong ket noi
        socket.close();
        socketgui.close();
    }
}
