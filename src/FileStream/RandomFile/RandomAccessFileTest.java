package FileStream.RandomFile;

import org.junit.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

public class RandomAccessFileTest {

    @Test
    public void writeAndRead() {
        RandomAccessFile file =  null;
        try {
            file = new RandomAccessFile("src/FileStream/RandomFile/randomFile.txt", "rw");
            file.write(65); // A
            file.write(97);
            file.write(98);
            file.write(99);
            byte[] buff = {101, 102, 103, 104, 105,
                    106, 107, 108, 109, 110};
            file.write(buff);
            file.write(buff, 6, 3);
            // 从数组下标 6 开始，输出 3 个，也就是 107、108、109。分别转为十六进制：
//            107–>6b
//            108–>6c
//            109–>6d
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                file.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    @Test
    public void readOne() {
        /**
         * read单个：
         */
        RandomAccessFile file1 = null;
        try {
            file1 = new RandomAccessFile("src/FileStream/RandomFile/randomFile.txt", "r");
            file1.seek(0);
            int b = 0;
            while ((b = file1.read()) != -1) {
                System.out.println(b);
            }
            //随着读取，下标也会向后移动
            file1.seek(0);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (file1 != null) {
                    file1.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 批量读取
     */
    @Test
    public void readOnes() {
        /**
         * read单个：
         */
        RandomAccessFile file1 = null;
        try {
            file1 = new RandomAccessFile("src/FileStream/RandomFile/randomFile.txt", "r");
            file1.seek(0);
            byte[] buff = new byte[5];
            int n = 0;
            while ((n = file1.read(buff)) != -1) {
                System.out.println(n + "->" + Arrays.toString(buff));
            }
            //随着读取，下标也会向后移动
            file1.seek(0);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (file1 != null) {
                    file1.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        //5->[65, 97, 98, 99, 101]
        //5->[102, 103, 104, 105, 106]
        //5->[107, 108, 109, 110, 107]
        //2->[108, 109, 109, 110, 107]
        //最后一行值覆盖了前两个数字
    }
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }




}
