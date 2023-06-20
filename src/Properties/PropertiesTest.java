package Properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class PropertiesTest {
    public static void main(String[] args) throws IOException {
        Properties pros = new Properties();
        FileInputStream fis = new FileInputStream("helloin01.txt");
        pros.load(fis);

        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        System.out.println("user , password = " + user + "," + password);
        System.out.println("pros = " + pros);
    }

    @Test
    public void readCsv() {
        Properties pros = null;
        try {
            pros = new Properties();
            pros.load(new FileInputStream("D:\\javaProject\\javaTesting\\src\\Properties\\userPass.csv"));
        } catch (IOException e) {
            throw new RuntimeException("read error! " + e);
        }
        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        System.out.println("user , password = " + user + "," + password);
        System.out.println("pros = " + pros);


    }

    @Test
    public void read() {
        File file = new File(System.getProperty("user.dir") + "/Properties/params.csv");
        List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3));
        Map<Integer, List<Integer>> map =  new HashMap<>();
        map.put(1, list);
        System.out.println("map.get(1) = " + map.get(1));
//        List<Integer> list1 = map.getOrDefault(2, new ArrayList<>());
        List<Integer> list1 = null;
        try {
            list1 = map.get(2);
            list1.add(1);
        } catch (Exception e) {
            System.out.println("空指针error");
//            e.printStackTrace();
        }
        System.out.println("list1: " + list1);
        int n = 30;
        int t  =1;
        while (n > 0) {
            System.out.println("t &&&= " + t);
            if ((t & 1) == 1) {
                System.out.println("[if]n = " + n);
//                n--;

                if (t % 3 == 0)
                {
                    t+=2;
                }
                n--;
            }
            System.out.println("n = " + n);
            t+=2;
        }
//        System.out.println("t = " + t);
        System.out.println("t = " + t);
        System.out.println("++t = " + ++t);
        System.out.println("t = " + t);

    }



}

