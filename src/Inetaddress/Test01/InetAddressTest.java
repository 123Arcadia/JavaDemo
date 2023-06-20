package Inetaddress.Test01;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InetAddressTest {
    @Test
    public void testInetAdress() throws UnknownHostException {
        InetAddress inet1 = InetAddress.getByName("192.168.04.04");
        System.out.println("inet1 = " + inet1);

        InetAddress inet2 = InetAddress.getByName("www.baidu.com");
        System.out.println("inet2 = " + inet2);

        InetAddress inet3 = InetAddress.getLocalHost();
        System.out.println("inet3 = " + inet3);

        InetAddress inet4 = InetAddress.getByName("10.86.44.10");
        System.out.println("inet4 = " + inet4);

        System.out.println("inet3.getHostName() = " + inet3.getHostName());
        System.out.println("inet3.getHostAddress() = " + inet3.getHostAddress());
        System.out.println("inet3.getAddress() = " + inet3.getAddress().toString());
        // inet1 = /192.168.4.4
        // inet2 = www.baidu.com/180.101.50.188
        // inet3 = LAPTOP-89JUTCMV/10.86.44.10
        // inet4 = /10.86.44.10

        // inet3.getHostName() = LAPTOP-89JUTCMV
        //inet3.getHostAddress() = 192.168.0.103
        //inet3.getAddress() = [B@6debcae2
    }

    @Test
    public void t() {
        List<Stu> list = new ArrayList<>();
        Map<String, List<Stu>> map = new HashMap<>();
        list.add(new Stu("1", "11"));
        list.add(new Stu("2", "22"));
        list.add(null);
        map.put("1", null);
        map.put("2", list);
        list.add(new Stu());

        map.put("2", list);

        List<Stu> list1 = new ArrayList<>();
//        for (int i = 0; i < map.size(); i++) {
////            System.out.println(map.getOrDefault(String.valueOf(i + 1)), );
//            list1.addAll(map.get(String.valueOf(i + 1)));
//        }
        System.out.println("list1 = " + list1);
        Stu stu = new Stu();
        System.out.println(stu == null); // false

        System.out.println("map.values() = " + map.values());
        for (List<Stu> value : map.values()) {
            System.out.println("value [] = " + value);
            if (value != null) {
                List<Stu> stuList = value.stream().filter(s -> s != null).collect(Collectors.toList());
                System.out.println("value ---= " + stuList);
                list1.addAll(stuList);
                System.out.println("value = " + stuList);
            }
        }
        System.out.println("list1 = " + list1);
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Stu {
    private String name;
    private String age;

}
