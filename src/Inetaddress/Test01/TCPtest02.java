package Inetaddress.Test01;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class TCPtest02 {
//    客户端给服务端发文本，服务端将文本转化为大写再返回客户端
    @Test
    public void client() throws Exception{
        Socket socket = new Socket(InetAddress.getLocalHost(), 8899);
        OutputStream os = socket.getOutputStream();
        os.write("I am a student! i love you nlp!!!".getBytes());
        socket.shutdownOutput();

        InputStream is = socket.getInputStream();
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        byte[] buff = new byte[100];
//        int len;
//        while ((len = is.read()) != -1) {
//            bos.write(buff, 0, len);
//        }
//        String str = Arrays.toString(bos.toByteArray());
//        System.out.println("客户端str = " + str);
//        System.out.println("bos = " + bos);

        is.close();
        os.close();
        socket.close();
    }

    @Test
    public void server() throws Exception{
        ServerSocket ss = new ServerSocket(8899);
        Socket socket = ss.accept();
        InputStream is = socket.getInputStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        int len;
        while ((len = is.read(buff)) != -1) {
            bos.write(buff, 0, len);
        }
        String str = bos.toString().toUpperCase();
        System.out.println("服务端收到的str = " + str);
//        OutputStream os = socket.getOutputStream();
//        os.write(str.getBytes());
        ss.close();
        is.close();
//        os.close();
        bos.close();
        socket.close();
    }

    @Test
    public void bytetoString() throws Exception{
        byte[] buff = ("zcw".getBytes());
        System.out.println("buff = " + buff);
        System.out.println("toString() = " + Arrays.toString(buff));
    }
}
