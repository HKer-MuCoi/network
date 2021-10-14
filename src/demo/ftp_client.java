package demo;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ftp_client {
    public static void main(String[] args) throws IOException {
        System.out.println("Client start");
        Socket socket = new Socket("127.0.0.1", 3000);
        DataInputStream datain = new DataInputStream(socket.getInputStream());

        FileOutputStream output = new FileOutputStream("/home/tuancong/Downloads/Báo cáo Tuần 51  Tuấn.pptx");

        int is;
        byte[] data = new byte[10000];

        while((is = datain.read(data)) > 0) {
            output.write(data,0, is);
        }

        System.out.println("Run finished");

        File image = new File("/home/tuancong/Downloads/Báo cáo Tuần 51  Tuấn.pptx");
        if (image.exists()) {
            System.out.println("Ten file: " + image.getName());
            System.out.println("Đường dẫn: " + image.getParent());
            System.out.println("Đường dẫn đầy đủ: " + image.getPath());
            System.out.println("Độ lớn: " + image.length() + " bytes");
        }
        output.close();
        datain.close();
        socket.close();
    }
}
