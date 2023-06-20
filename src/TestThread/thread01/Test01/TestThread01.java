package TestThread.thread01.Test01;

public class TestThread01 {
    public static void main(String[] args) {
        Number number0 = new Number();
        Number number1 = new Number();

//        number0.start();
//        number1.start();

        String s = "abc";
        String s1 = new String("abc");

        StringBuffer stringBuffer = new StringBuffer();  //char[16]
        String s2 = new String("zcw");

        Number number = new Number();
        //System.out.println(System.currentTimeMillis().toString());
        System.out.println(s == s1);
        System.out.println(s.equals(s1));
    }
}

class Number extends Thread {
    private static int num = 20;
    private int count = 0;
    @Override
    public synchronized void run() {
        for (int i = 0; i < 20 && num > 0; i++) {
            num--;
            System.out.println(Thread.currentThread().getName() + ": count -> " + count + " ==>" + num);
            count++;
        }

        }
    }

