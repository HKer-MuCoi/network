package demo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Sever_buoi1 {
    public static void main(String[] args) {
        try {
            while(true) {
                System.out.println("Server Start");
                ServerSocket serverSocket = new ServerSocket(3000);
                Socket socket = serverSocket.accept();
                DataInputStream dataIn = new DataInputStream(socket.getInputStream());
                DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());

                String data = dataIn.readUTF();
                System.out.println(data.equals("q"));
                if(data.equals("q"))
                    break;
                String result = "Server received " + data;
                dataOut.writeUTF(result);

                dataOut.flush();
                serverSocket.close();
                socket.close();
                dataIn.close();
                dataOut.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
