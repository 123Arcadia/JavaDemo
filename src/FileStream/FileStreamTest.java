package FileStream;

import org.junit.Test;

import java.io.*;

public class FileStreamTest {
    public static void main(String[] args) {

        File file = new File("hello.txt");
        System.out.println("file = " + file.getAbsolutePath());
    }

    @Test
    public void testFileWriterTest () throws IOException {

        File file = new File("D:\\javaProject\\javaTesting\\src\\FileStream\\hello.txt");
        FileReader fr = new FileReader(file);

        int data = fr.read();
        while (data != -1) {
            System.out.print((char)data);
            data = fr.read();
        }
        fr.close();

    }

    @Test
    public void testFileWriterTest01 () throws IOException {

        File file = new File("D:\\javaProject\\javaTesting\\src\\FileStream\\hello.txt");
        FileReader fr = new FileReader(file);

        char[] cbuf = new char[5];
        int len;
        while ((len = fr.read(cbuf)) != -1) {
            for (int i = 0; i < len; i++) {
                System.out.print(cbuf[i] + " ");
            }
        }
        if (fr != null)
            fr.close();

    }

    /**
     * FileWrite(file, [boolean])
     *       true:追加
     *       false:覆盖
     * @throws IOException
     */
    @Test
    public void testFileWriterTest02 () throws IOException {

        File file = new File("D:\\javaProject\\javaTesting\\src\\FileStream\\hello1.txt");
        FileWriter fr = new FileWriter(file,false);

        fr.write("My name is zcw!\n");
        fr.write("You must be stanger!\n");

        fr.close();
    }

    //read and write
    @Test
    public void testFileWriterTest03 () throws IOException {

        File file01 = new File("D:\\javaProject\\javaTesting\\src\\FileStream\\hello3.txt");
        File file02 = new File("D:\\javaProject\\javaTesting\\src\\FileStream\\hello1.txt");

        FileWriter fw = new FileWriter(file01);
        FileReader fr = new FileReader(file02);

        char[] buf = new char[5];
        int len;
        while ((len = fr.read(buf)) != -1) {
            fw.write(buf, 0, len);
        }

        fw.flush();
        fw.write("hello, Arcadia\n");
        fw.close();
        fr.close();
    }

    @Test
    public void test_InputStrem() throws IOException {
        File file = new File("src/FileStream/hello.txt");
        FileInputStream fi = new FileInputStream(file);

        byte[] bytes = new byte[13];
        int len;
        while ((len = fi.read(bytes)) != -1) {
            System.out.println(new String(bytes, 0, len));
        }
    }


    @Test
    public void test_BUfferStream() throws Exception{
        FileInputStream fin = new FileInputStream("src/FileStream/hello1.txt");
        FileOutputStream fou = new FileOutputStream("src/FileStream/hello1_out.txt");
        BufferedInputStream bin = new BufferedInputStream(fin);
        BufferedOutputStream bou = new BufferedOutputStream(fou);

        byte[] buffer = new byte[10];
        int len;
        while ((len=bin.read(buffer))!=-1){
            System.out.print(new String(buffer, 0, len) + "-");
            bou.write(buffer,0,len);

        }
        bou.flush();
//        if (fou != null || fin != null) {
//            fou.close();
//            fin.close();
//        }
        fou.close();
        fin.close();
    }

}
