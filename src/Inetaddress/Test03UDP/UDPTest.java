package Inetaddress.Test03UDP;

import org.junit.Test;

import java.io.IOException;
import java.net.*;

public class UDPTest {

    @Test
    public void sender() throws IOException {
        DatagramSocket socket = new DatagramSocket();
        String str = "UDP is here!!";
        byte[] data = str.getBytes();
        InetAddress inet = InetAddress.getLocalHost();
        System.out.println("inet = " + inet);
        DatagramPacket packet = new DatagramPacket(data, 0, data.length, inet,9090);

        socket.send(packet);
        socket.close();
    }

    @Test
    public void receive() throws IOException {
        DatagramSocket socket =  new DatagramSocket(9090);

        byte[] buf = new byte[100];
        DatagramPacket packet = new DatagramPacket(buf, 0, buf.length);

        socket.receive(packet);

        System.out.println(new String(packet.getData(), 0 , packet.getLength()));

        socket.close();
    }

    // 第二次练习
    @Test
    public void send_2() throws IOException {
        DatagramSocket ds = new DatagramSocket();
        String str = "I am send_2";
        DatagramPacket packet = new DatagramPacket(str.getBytes(), 0, str.getBytes().length, InetAddress.getLocalHost(), 8080);
        ds.send(packet);
        ds.close();
    }

    @Test
    public void recevice_2() throws IOException {
        DatagramSocket ds = new DatagramSocket(8080);
        byte[] bytes = new byte[100];
        DatagramPacket packet = new DatagramPacket(bytes, 0, bytes.length);
        ds.receive(packet);
        System.out.println(new String(bytes, 0, bytes.length));
        ds.close();

    }
}
