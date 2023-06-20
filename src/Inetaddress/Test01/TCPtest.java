package Inetaddress.Test01;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPtest {
    //�ͻ���
    @Test
    public void client() {

        Socket socket = null;
        OutputStream os = null;
        try {
            InetAddress inet = InetAddress.getByName("127.0.0.1");
            socket = new Socket(InetAddress.getLocalHost(), 8899);

            os = socket.getOutputStream();
            os.write("��ã����ǿͻ���".getBytes());  //GBK
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    //�����
    @Test
    public void server() throws IOException {
        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            serverSocket = new ServerSocket(8899);
            socket = serverSocket.accept();
            is = socket.getInputStream();

//        byte[] buf = new byte[1024];
//        int len;
//        while ((len = is.read(buf)) != -1) {
//            String str = new String(buf, 0, lne);
//        }

            baos = new ByteArrayOutputStream();
            byte[] buf = new byte[5];
            int len;
            while ((len = is.read(buf)) != -1) {
                baos.write(buf, 0, len);
            }
            System.out.println(baos.toString()); //תΪString
            System.out.println("�յ���" + InetAddress.getLocalHost() + "������");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            baos.close();
            is.close();
            socket.close();
            serverSocket.close();
        }
    }


}
