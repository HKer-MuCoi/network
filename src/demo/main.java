package demo;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class main {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress host = InetAddress.getByName("facebook.com");
        System.out.printf("Host Name la %s%n", host.getHostName());
        System.out.printf("Ip address la %s%n", host.getHostAddress());
    }
}
