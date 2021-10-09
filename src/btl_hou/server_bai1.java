package btl_hou;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class server_bai1 {
    public static void main(String[] args) {
        try {
            System.out.println("Server Start");
            ServerSocket serverSocket = new ServerSocket(2015);
            Socket socket = serverSocket.accept();
            DataInputStream dataIn = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());
            while(true) {
                String data = dataIn.readUTF();
                System.out.println("CLIENT CONNECTED");
                if(data.equals("EXIT")) {
                    System.out.println(data);
                    break;
                }
                System.out.println("CLIENT: "+ data);
                String result = data.toUpperCase();
                dataOut.writeUTF(result);
                dataOut.flush();
            }
            serverSocket.close();
            socket.close();
            dataIn.close();
            dataOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
