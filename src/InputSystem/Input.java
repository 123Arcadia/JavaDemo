package InputSystem;

import java.io.Console;
import java.io.IOException;

public class Input {


    public static void main(String[] args) throws IOException {
//        Scanner scanner = new Scanner(System.in);
//        String s = scanner.nextLine();
//        System.out.println("s = " + s);

//        System.out.println("How old are you?");
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        String s = reader.readLine();
//        System.out.println("s = " + s);

        /**
         * �������ֱ�����л��쳣
         * Ҫ�ӿ���̨/�����б�������
         */
//        Console c = System.console();
//        c.printf("What's your name?");
//        String s = c.readLine();
//        c.printf("user:" + s + "\n");
//        System.out.println("s = " + s);
//        char[] passwd = c.readPassword("Password:");
//
//        c.printf(String.valueOf(passwd));
        Console cons=System.console();
        String uname=cons.readLine("User name: ");
//��ȡ����
        char[] pwds=cons.readPassword();
        String pwd=new String(pwds);
        System.out.println( pwd );
    }


}
