package Testpro;

import static java.lang.Integer.valueOf;

public class mainArgs {
    int n1 = 1000;
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            System.out.print(args[i] + " -");
        }
        mainArgs main = new mainArgs();
        main.call();
        String s="456";
        System.out.println("number = " + valueOf(s));
        System.out.println("Integer.valueOf(s) = " + Integer.valueOf(s));
        //zcw
        //number = 456
        //Integer.valueOf(s) = 456
        int x = 456;
        System.out.println("String.valueOf(x) = " + String.valueOf(x).getClass());
        //String.valueOf(x) = 456  (456是String类型)
    }

    public void call() {
        System.out.println("zcw");
    }
 }
