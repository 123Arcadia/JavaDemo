package Inetaddress.URLTest;

import org.junit.Test;

import java.net.URL;

public class urltest01 {
    @Test
    public void test01() throws Exception {
        URL url = new URL("http://127.0.0.1:8080/work/164.jpg?username=subei");
        System.out.println("文件名：" + url.getFile());
        System.out.println("主机名：" + url.getHost());
        System.out.println("路径：" + url.getPath());
        System.out.println("端口号：" + url.getPort());
        System.out.println("协议：" + url.getProtocol());
        System.out.println("查询名" + url.getQuery());
        //文件名：/work/164.jpg?username=subei
        //主机名：127.0.0.1
        //路径：/work/164.jpg
        //端口号：8080
        //协议：http
        //查询名username=subei
    }
}
