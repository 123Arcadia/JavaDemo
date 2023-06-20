package TestThread.thread01.MThreadTest;

class MThread implements  Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) System.out.println(i);
        }
    }
}

public class ThreadTest1 {
    public static void main(String[] args) {
        MThread mThread = new MThread();
        Thread thread = new Thread(mThread);
        thread.start();

        new Thread("int :1") {
            @Override
            public void run() {
                System.out.println(" = ");
            }
        }.start();
    }
}
