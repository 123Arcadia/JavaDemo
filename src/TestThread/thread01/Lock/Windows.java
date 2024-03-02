package TestThread.thread01.Lock;

import java.util.concurrent.locks.ReentrantLock;

class Windows2 extends Thread {
    // 加上static会在ticket = 0时也减1
//    private int ticket = 100;
    private int ticket = 100;
    //private static Object obj = new Object();
    private ReentrantLock r = new ReentrantLock();

    /**
     * 或者加static synchronized：同步监视器为类本身
     */
    @Override
    public void run() {
        while (true) {
            try {
                r.lock();
                if (ticket > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ": 卖票票号：" + ticket);
                    ticket--;
                } else {
                    System.out.println(Thread.currentThread().getName() + " 票已经卖完了");
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                r.unlock();
            }
        }


    }
}

class windowsTest {
    public static void main(String[] args) {

        Windows2 windows = new Windows2();

        for (int i = 0; i < 3; i++) {
            new Thread(windows, i + "号窗口 ").start();
        }
//        Thread thread1 = new Thread(windows);
//        Thread thread2 = new Thread(windows);
//        Thread thread3 = new Thread(windows);
//        thread1.setName("1号窗口 ");
//        thread2.setName("2号窗口 ");
//        thread3.setName("3号窗口 ");
//
//        thread1.start();
//        thread2.start();
//        thread3.start();
    }
}
