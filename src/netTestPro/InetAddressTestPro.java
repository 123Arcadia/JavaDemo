package netTestPro;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class InetAddressTestPro {
    public static void main(String[] args) {
        try {
            InetAddress inet1 = InetAddress.getByName("192.168.1.1");
            InetAddress inet2 = InetAddress.getByName("www.baidu.com");
            InetAddress inet3 = InetAddress.getLocalHost();
            System.out.println("inet1 = " + inet1);
            System.out.println("inet2 = " + inet2);
            System.out.println("inet3 getLocalHost = " + inet3);
            String inet2HostAddress = inet2.getHostAddress();
            String inet2Name = inet2.getHostName();
            byte[] inet2Address = inet2.getAddress();
            System.out.println("inet2的主机名 = " + inet2Name);
            System.out.println("inet2的IP地址 = " + inet2HostAddress);
            System.out.println("inet2Address = " + Arrays.toString(inet2Address));
//            inet1 = /192.168.1.1
//            inet2 = www.baidu.com/180.101.49.14
//            inet3 getLocalHost = LAPTOP-89JUTCMV/169.254.53.46
//            inet2的主机名 = www.baidu.com
//            inet2的IP地址 = 180.101.49.14
//            inet2Address = [-76, 101, 49, 14]
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
