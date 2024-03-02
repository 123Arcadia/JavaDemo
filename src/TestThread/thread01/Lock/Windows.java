package TestThread.thread01.Lock;

import java.util.concurrent.locks.ReentrantLock;

class Windows2 extends Thread {
    // ����static����ticket = 0ʱҲ��1
//    private int ticket = 100;
    private int ticket = 100;
    //private static Object obj = new Object();
    private ReentrantLock r = new ReentrantLock();

    /**
     * ���߼�static synchronized��ͬ��������Ϊ�౾��
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
                    System.out.println(Thread.currentThread().getName() + ": ��ƱƱ�ţ�" + ticket);
                    ticket--;
                } else {
                    System.out.println(Thread.currentThread().getName() + " Ʊ�Ѿ�������");
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
            new Thread(windows, i + "�Ŵ��� ").start();
        }
//        Thread thread1 = new Thread(windows);
//        Thread thread2 = new Thread(windows);
//        Thread thread3 = new Thread(windows);
//        thread1.setName("1�Ŵ��� ");
//        thread2.setName("2�Ŵ��� ");
//        thread3.setName("3�Ŵ��� ");
//
//        thread1.start();
//        thread2.start();
//        thread3.start();
    }
}
