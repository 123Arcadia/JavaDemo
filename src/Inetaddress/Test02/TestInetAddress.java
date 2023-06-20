package Inetaddress.Test02;

import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TestInetAddress {
    //����2���ͻ��˷����ļ�������ˣ�����˽��ļ������ڱ��ء�
//    @Test
//    public void TCPtest01() throws IOException {
//        Socket socket = null;
//        OutputStream os = null;
//        FileInputStream fis = null;
//        try {
//            socket = new Socket("10.86.70.202", 8080);
//            os = socket.getOutputStream();
//            fis = new FileInputStream(new File("helloin03.txt"));
//
//            byte[] buf = new byte[1024];
//            int len;
//            while ((len = fis.read(buf)) != -1) {
//                os.write(buf, 0, len);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } finally {
//
//            fis.close();
//            os.close();
//            socket.close();
//        }
//    }
//
//    @Test
//    public void serverTest() throws IOException {
//        ServerSocket serverSocket = null;
//        Socket socket = null;
//        InputStream ins = null;
//        FileOutputStream fos = null;
//        try {
//            serverSocket = new ServerSocket(8080);
//            socket = serverSocket.accept();
//
//            ins = socket.getInputStream();
//            fos = new FileOutputStream(new File("helloin033.txt"));
//            byte[] buf = new byte[1024];
//            int len;
//            while ((len = ins.read(buf)) != -1) {
//                fos.write(buf, 0, len);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } finally {
//            fos.close();
//            ins.close();
//            socket.close();
//            serverSocket.close();
//        }
//
//    }

    //����2���ͻ��˷����ļ�������ˣ�����˽��ļ������ڱ���,���ҷ������������յ���Ϣ
    @Test
    public void TCPtest03() throws IOException {

        Socket socket = new Socket("10.86.70.202", 8080);
        OutputStream os = socket.getOutputStream();
        FileInputStream fs = new FileInputStream(new File("helloin03.txt"));

        byte[] buf = new byte[10];
        int len;
        while ((len = fs.read(buf)) != -1) {
            os.write(buf, 0, len);
        }
        //�ر����ݵ����
        socket.shutdownOutput();

            //���շ���
            InputStream ins = socket.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buf1 = new byte[10];
            int len1;
            while ((len1 = ins.read(buf1)) != -1) {
                baos.write(buf1, 0, len1);
            }
            System.out.println("baos.toString() = " + baos.toString());

        fs.close();
        os.close();
        socket.close();
        baos.close();
    }

    @Test
    public void serverTest03() throws IOException {

        ServerSocket serverSocket = new ServerSocket(8080);
        Socket socket = serverSocket.accept();

        InputStream ins = socket.getInputStream();
        FileOutputStream fos = new FileOutputStream(new File("helloin033.txt"));
            byte[] buf = new byte[10];
            int len;
            while ((len = ins.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            //���з���
            OutputStream os = socket.getOutputStream();
            os.write("���,���Ѿ��յ�!".getBytes());


            fos.close();
            ins.close();
            socket.close();
            serverSocket.close();

            os.close();


    }
}
