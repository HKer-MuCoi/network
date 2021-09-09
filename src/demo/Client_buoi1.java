package demo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client_buoi1 {
    public static void main(String[] args) {
        try{
            while(true) {
                System.out.println("Client Start");
                Socket socket = new Socket("127.0.0.1", 3000);
                DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());
                DataInputStream dataIn = new DataInputStream(socket.getInputStream());

                System.out.print("Insert data: ");
                Scanner sc = new Scanner(System.in);
                String data = sc.nextLine();
                if(data.equals("q")) {
                    dataOut.writeUTF(data);
                    dataOut.flush();
                    System.out.println("Stop");
                    break;
                }
                else
                {
                    dataOut.writeUTF(data);
                    dataOut.flush();
                }

                String result = dataIn.readUTF();
                System.out.println("Server said: " + result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
