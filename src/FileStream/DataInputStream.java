package FileStream;

import org.junit.Test;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class DataInputStream {


    @Test
    public void test_DataInputStream() throws Exception {
//        DataOutputStream dos = new DataOutputStream(new FileOutputStream("src/FileStream/DataOutputStream.txt"));
        DataOutputStream  dos = new DataOutputStream(new FileOutputStream("src/FileStream/DataOutputStream.txt"));
        dos.write(6);
        dos.writeUTF("zcw");
        dos.writeBoolean(false);
        dos.close();
    }

    @Test
    public void test_DataOutputStream() throws Exception {
//        DataInputStream dis = new DataInputStream(new FileInputStream("src/FileStream/DataOutputStream.txt"));
        java.io.DataInputStream dis = new java.io.DataInputStream(new FileInputStream("src/FileStream/DataOutputStream.txt"));
        int age = dis.readInt();
        String name = dis.readUTF();
        boolean isMale = dis.readBoolean();
        System.out.println("name = " + name);
        System.out.println("age = " + age);
        System.out.println("isMale = " + isMale);
    }


//    @Test
//    public void test() {
//        int a = 10;
//        double p = 3.1415926;
//        System.out.printf("a = " + String.format("%5d", a) + "\n");
//
//        System.out.printf("a = " + String.format("%o", a) + "\n"); // 8进制
//        System.out.printf("a = " + String.format("%x", a) + "\n"); // 16进制
//        System.out.printf("%s\n", a);
//
//        Formatter f = new Formatter(System.out);
//        f.format("a = %.2f\n", p);
//        f.close();
//
//        new Formatter(System.out).format("p = %.3f" , p); // p = 3.142
//
//    }

}
