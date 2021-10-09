package btl_hou;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class client_bai1 {
    public static void main(String[] args) {
        try{
            System.out.println("Client Start");
            Socket socket = new Socket("127.0.0.1", 2015);
            DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());
            DataInputStream dataIn = new DataInputStream(socket.getInputStream());
            System.out.println("CONNECTED TO SERVER");
            while(true) {
                System.out.print("Insert data: ");
                Scanner sc = new Scanner(System.in);
                String data = sc.nextLine();
                if(data.equals("EXIT")) {
                    dataOut.writeUTF(data);
                    dataOut.flush();
                    System.out.println("EXIT");
                    break;
                }
                else
                {
                    dataOut.writeUTF(data);
                    dataOut.flush();
                    String result = dataIn.readUTF();
                    System.out.println("SERVER: " + result);
                }
            }
            socket.close();
            dataIn.close();
            dataOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
