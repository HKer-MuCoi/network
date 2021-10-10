package btl_hou;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.StringTokenizer;

public class server_bai3 {
    public static void main(String[] args) throws IOException {
        System.out.println("Server Start!");
        //B1: Khai bao DS
        DatagramSocket socket = new DatagramSocket(3000);
        DatagramSocket socketgui = new DatagramSocket();
        //B2: khoi tao DP
        while (true) {
            byte[] buffin = new byte[1024];
            DatagramPacket datain = new DatagramPacket(buffin, buffin.length);
            socket.receive(datain);
            String string_in = new String(datain.getData(), 0, buffin.length);
            if (string_in.trim().equals("q")) {
                break;
            }
            System.out.println("Client: " + string_in.trim());
            String[] arr = string_in.split(" "); // tách chuỗi

            StringBuilder sb = new StringBuilder(); // chuỗi có thể thay đổi
            // Character.toUpperCase(s.charAt(0)): viết hoa chữ đầu
            // append(s.substring(1)): nối phần còn lại của từ vào chữ đầu tiên
            for (String s : arr) {
                sb.append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).append(" ");
            }
            String upperCase = sb.toString().trim(); // bỏ khoảng trắng


            StringBuilder sb1 = new StringBuilder(sb);
            String result = sb1.reverse().toString().trim(); // đảo chuỗi

            StringTokenizer count = new StringTokenizer(result); // đếm chuỗi bằng string token
            int countWord = count.countTokens();

            String dataout = "Server trả về:" + "\nViết hoa chữ cái đầu: " + upperCase + "\nĐảo ngược chuỗi:" + result + "\n" +
                    "Số từ có trong chuỗi là: " + countWord + " từ\n";
            System.out.println(dataout);
            byte[] buffout = dataout.getBytes();
            DatagramPacket datagrout = new DatagramPacket(buffout, buffout.length, datain.getAddress(), datain.getPort());
            socketgui.send(datagrout);
        }
        //B6: dong ket noi
        socket.close();
        socketgui.close();
    }
}
