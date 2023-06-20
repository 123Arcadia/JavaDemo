package Inetaddress.URLTest;

import org.junit.Test;

import java.net.URL;

public class urltest01 {
    @Test
    public void test01() throws Exception {
        URL url = new URL("http://127.0.0.1:8080/work/164.jpg?username=subei");
        System.out.println("�ļ�����" + url.getFile());
        System.out.println("��������" + url.getHost());
        System.out.println("·����" + url.getPath());
        System.out.println("�˿ںţ�" + url.getPort());
        System.out.println("Э�飺" + url.getProtocol());
        System.out.println("��ѯ��" + url.getQuery());
        //�ļ�����/work/164.jpg?username=subei
        //��������127.0.0.1
        //·����/work/164.jpg
        //�˿ںţ�8080
        //Э�飺http
        //��ѯ��username=subei
    }
}
