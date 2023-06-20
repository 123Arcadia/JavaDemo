package Exception;

import java.util.Scanner;

public class inoutexception {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num_end = 0;
        String  inputStr = "";
        while (true) {
            inputStr = scanner.next();

            try {
                num_end = Integer.parseInt(inputStr);
                break;
            } catch (NumberFormatException e) {
                System.out.println("输入错误...");
            } finally {
                System.out.println("res (num) = " + num_end);
            }
        }

    }
}
