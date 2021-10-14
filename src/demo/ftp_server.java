package demo;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ftp_server {
    public static void main(String[] args) throws IOException {
        System.out.println("Server start");
        //B1: khai báo server socket
        ServerSocket serversk = new ServerSocket(3000);
        // chờ kết nối từ client
        Socket socket = serversk.accept();
        //lấy dữ liệu từ sv trả về client
        FileInputStream in = new FileInputStream("/home/tuancong/Downloads/Báo cáo Tuần 5  Tuấn.pptx");
        // chuẩn bị dữ liệu phía client
        int ibyte = in.available();
        byte[] buffer = new byte[ibyte];
        in.read(buffer,0,ibyte);

        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        out.write(buffer,0,ibyte);
        in.close();
        serversk.close();
        out.close();
    }
}
