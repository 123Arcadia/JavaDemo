package TestThread.thread01.synchronizedReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

class Windows extends Thread {
    // ����static����ticket = 0ʱҲ��1
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
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(getName() + ": ��ƱƱ�ţ�" + ticket);
                    ticket--;
                } else {
                    System.out.println(getName() + " Ʊ�Ѿ�������");
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
        Windows windows1 = new Windows();
        Windows windows2 = new Windows();
        Windows windows3 = new Windows();


        windows1.setName("1 -->");
        windows2.setName("2 -->");
        windows3.setName("3 -->");

        windows1.start();
        windows2.start();
        windows3.start();

        //new Windows(windows1).start();
    }
}
