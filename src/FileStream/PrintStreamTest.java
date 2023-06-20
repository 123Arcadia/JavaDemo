package FileStream;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;

public class PrintStreamTest {

    /**
     * 输出到文件中
     * @throws Exception
     */
    @Test
    public void test_PrintStream () throws Exception {
        PrintStream o = System.out;
        PrintStream ps = new PrintStream(new File("src/FileStream/printStream.txt"));
        if (ps != null) {
            System.setOut(ps);
        }
        ps.println(6);
        ps.println("zcw");

//        o.println("console");

        // 切换输出到控制台
        System.setOut(o);
        o.println("end...(o)");
        System.out.println("end...");
//        System.setOut();
//        if (ps != null) {
//            System.out.println("gbips"); // 这里也会输入金文件中
//            ps.close();
//        }
        /**
         * 因为这里ps关闭，把System.out也关闭了
         */
    }

    @Test
    public void test_PrintWrite() {
        try (PrintWriter pw = new PrintWriter("src/FileStream/stream.txt")) {
//            System.  // 把标准输出流(控制台输出)改成文件
            for (int i = 0; i <= 100; i++) {
                pw.print(i + " ");
                if (i % 40 == 0) System.out.println();    //40个换一行, i = 0是也换行
            }
            if (pw != null) {
                System.out.println("关闭pw");
                pw.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("关闭后after dis pw!");
    }

    @Test
    public void test() {
        int a = 5;
        System.out.println("Integer.toBinaryString(a) = " + Integer.toBinaryString(a));
        // Integer.toBinaryString(a) = 101
    }
}
