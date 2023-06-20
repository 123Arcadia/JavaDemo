package Testpro;

public class PasswordTest {
    public static void main(String[] args) {
        String name = "jack";
        String pwd = "123456";
        String email = "jack@sohu.com";


        try {
            userRegister(name, pwd, email);
            System.out.println("Successfully!");
        } catch (Exception e) {
            System.out.println("Fail!");
        }
    }


    public static void userRegister(String name, String pwd, String email) {


        if (!(name != null && pwd != null && email != null)) {
            throw new RuntimeException("参数错误！！！");
        }

        int userlength = name.length();
        if (!(userlength >= 2 && userlength <= 4)) {
            throw new RuntimeException("用户名长度为2~4");
        }

        if (!(pwd.length() == 6 && isDigital(pwd))) {
            throw new RuntimeException("密码长度为6，且须是数字");
        }

        int i = email.indexOf('@');
        int j = email.indexOf('.');
        if (!(i > 0 && j > i)) {
            throw new RuntimeException("@在'.'前,不能在前头");
        }
    }


    public static boolean isDigital(String str) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] < '0' || chars[i] > '9') {
                return false;
            }
        }
        return true;
    }

}
