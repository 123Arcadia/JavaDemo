package FileStream.FileInpuStream;

import org.junit.Test;

import java.io.*;

public class FileinputOut {
    @Test
    public void testFIleInputStream() throws IOException {

        FileInputStream fin = null;
        try {
            File file = new File("D:\\javaProject\\javaTesting\\src\\FileStream\\FileInpuStream\\helloin.txt");
            fin = new FileInputStream(file);
            System.out.println("file.length() = " + file.length()); // file.length() = 65
            byte[] buf = new byte[5];
            int len;
            while ((len = fin.read(buf)) != -1) {
                String str = new String(buf, 0, len);
                System.out.print(str);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            fin.close();
        }
    }

    @Test
    public void testFIleInputOutStream() throws IOException {

        FileInputStream fin = null;
        FileOutputStream fou = null;
        try {
            File file = new File("D:\\javaProject\\javaTesting\\src\\FileStream\\FileInpuStream\\zcw.jpg");
            File dest = new File("D:\\javaProject\\javaTesting\\src\\FileStream\\FileInpuStream\\preview.jpg");

            fin = new FileInputStream(file);
            fou = new FileOutputStream(dest);

            byte[] buf = new byte[5];
            int len;
            while ((len = fin.read(buf)) != -1) {
                fou.write(buf, 0, len);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            fin.close();
            fou.close();
        }
    }

    @Test
    public void testcopyFile() {
        long start = System.currentTimeMillis();
        String f1 = "D:\\javaProject\\javaTesting\\src\\FileStream\\FileInpuStream\\zcw.jpg";
        String f2 = "D:\\javaProject\\javaTesting\\src\\FileStream\\FileInpuStream\\zcw01.jpg";

        copyFile(f1, f2);

        long end = System.currentTimeMillis();
        System.out.println("(end - start) = " + (end - start));

    }


    public void copyFile(String f1, String f2) {

        FileInputStream fin = null;
        FileOutputStream fou = null;
        try {
            File file = new File(f1);
            File dest = new File(f2);

            fin = new FileInputStream(file);
            fou = new FileOutputStream(dest);

            byte[] buf = new byte[1024];
            int len;
            while ((len = fin.read(buf)) != -1) {
                fou.write(buf, 0, len);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                fin.close();
                fou.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    public void Buffered() {
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            br = new BufferedReader(new FileReader(new File("D:\\javaProject\\javaTesting\\src\\FileStream\\FileInpuStream\\helloin.txt")));
            bw = new BufferedWriter(new FileWriter(new File("D:\\javaProject\\javaTesting\\src\\FileStream\\FileInpuStream\\helloin01.txt")));

            char[] buf = new char[1024];
            int len;
            while ((len = br.read(buf)) != -1) {
                bw.write(buf, 0, len);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    @Test
    public void Buffered01() {
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            br = new BufferedReader(new FileReader(new File("D:\\javaProject\\javaTesting\\src\\FileStream\\FileInpuStream\\helloin.txt")));
            bw = new BufferedWriter(new FileWriter(new File("D:\\javaProject\\javaTesting\\src\\FileStream\\FileInpuStream\\helloin01.txt")));

            String s;
            while ((s = br.readLine()) != null) {  //readline没有换行符,readLine()
                bw.write(s + "\n");    //方法一
                //bw.newLine();     //方法二
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    //图片加密
    @Test
    public void testPhotoScrete() throws IOException {

        FileInputStream fin = null;
        FileOutputStream fou = null;
        try {
            File file = new File("D:\\javaProject\\javaTesting\\src\\FileStream\\FileInpuStream\\zcw.jpg");
            File dest = new File("D:\\javaProject\\javaTesting\\src\\FileStream\\FileInpuStream\\preview.jpg");

            fin = new FileInputStream(file);
            fou = new FileOutputStream(dest);

            byte[] buf = new byte[5];
            int len;
            while ((len = fin.read(buf)) != -1) {

                for (int i = 0; i < buf.length; i++) {
                    buf[i] = (byte) (buf[i] ^ 5);
                }
                fou.write(buf, 0, len);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            fin.close();
            fou.close();
        }
    }

    //转换流
    @Test
    public void InputStreamReaderWriter() throws IOException {
        FileInputStream fr = new FileInputStream("D:\\javaProject\\javaTesting\\src\\FileStream\\FileInpuStream\\helloin01.txt");
        FileOutputStream fw = new FileOutputStream("D:\\javaProject\\javaTesting\\src\\FileStream\\FileInpuStream\\helloin.txt");
        //字符集可以根据文件保存的字符集
        InputStreamReader isr = new InputStreamReader(fr, "UTF-8");
        OutputStreamWriter isw = new OutputStreamWriter(fw, "UTF-8");

        char[] buf = new char[30];  //介绍读出需要多少字节
        int len;
        while ((len = isr.read(buf)) != -1) {
            //String str = new String(buf, 0, len);
            isw.write(buf, 0, len);
            //System.out.println("str = " + str + "==");

        }
        isr.close();
        isw.close();

    }

    public static void main(String[] args) throws IOException {
        InputStreamReader fr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(fr);

        try {
            while (true) {
                System.out.println("输入字符串:");
                //String s = br.readLine();
                String s = br.readLine();

                if ("e".equalsIgnoreCase(s) || "exit".equalsIgnoreCase(s)) {
                    System.out.println("程序退出!");
                    break;
                }
                System.out.println("s.toUpperCase() = " + s.toUpperCase());

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            br.close();
        }

    }

    @Test
    public void DataStreamTest() throws IOException {
        DataOutputStream dr = new DataOutputStream(new FileOutputStream("D:\\javaProject\\javaTesting\\src\\FileStream\\FileInpuStream\\helloin02.txt"));
        dr.writeUTF("张晨伟");
        dr.flush();

        dr.writeInt(56);
        dr.flush();

        dr.writeBoolean(true);
        dr.flush();
        dr.close();
    }

    @Test
    public void test() {
        ObjectOutputStream oos = null;
        try {
            //创造流
            oos = new ObjectOutputStream(new FileOutputStream("object.dat"));
            //制造对象
            oos.writeObject(new String("秦始皇陵欢迎你"));

            //刷新操作
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                //3.关闭流
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Test
    public void ObjectInutOutputStream() {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("Object.dat"));
            Object obj = ois.readObject();
            String str = (String) obj;

            System.out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    @Test
    public void RandomAccessFile() {
        RandomAccessFile acf = null;
        RandomAccessFile acf1 = null;
        try {
            acf = new RandomAccessFile(new File("helloin02.txt"), "rw");
            acf1 = new RandomAccessFile(new File("helloin03.txt"), "rw");

            byte[] buf = new byte[1024];
            int len;
            while ((len = acf.read(buf)) != -1) {
                acf1.write(buf, 0, len);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                acf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //追加 + 随机存储流
    @Test
    public void TestRandomAccess() throws IOException {
        //RandomAccessFile acf1 = new RandomAccessFile(("helloin03.txt"), "rw");//
        FileWriter fw = new FileWriter("helloin03.txt", true);
        fw.write("123", 1, 2);
        //System.out.println("\"zcw\".getBytes() = " + "zcw".getBytes());

        fw.close();
    }

    @Test
    public void TestRandomAccess01() throws IOException {
        RandomAccessFile raf1 = new RandomAccessFile("helloin03.txt", "rw");
        raf1.seek(3);
        StringBuilder builder = new StringBuilder((int) new File("helloin03.txt").length());
        byte[] buf = new byte[20];
        int len;
        while ((len = raf1.read(buf)) != -1) {
            builder.append(new String(buf, 0, len));
        }

        raf1.seek(3);
        raf1.write("Myname".getBytes());

        System.out.println("builder: " + builder); // builder: Myname
        raf1.write(builder.toString().getBytes());
        raf1.close();
    }

    @Test
    public void outToFieTxt() throws IOException {
        FileOutputStream outputStream = new FileOutputStream(new File("D:\\javaProject\\javaTesting\\src\\FileStream\\FileInpuStream\\outRes.txt"));
        for (int i = 1001; i <= 1420; i++) {
//            byte[] buf = new byte[]{(byte) i};
            outputStream.write((String.valueOf(i) +"\n".toString()).getBytes() , 0, (String.valueOf(i) +"\n".toString()).length());
        }


    }

}