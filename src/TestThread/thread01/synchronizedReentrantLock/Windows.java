package TestThread.thread01.synchronizedReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

class Windows extends Thread{
        private static int ticket = 100;
        //private static Object obj = new Object();
        private  ReentrantLock r = new ReentrantLock();

    /**
     * ���߼�static synchronized��ͬ��������Ϊ�౾��
     */
    @Override
    public synchronized void run() {
            try {
                r.lock();
                while (true) {

                    if (ticket > 0) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(getName() + ": ��Ʊ" + "Ʊ�ţ�" + ticket);
                        ticket--;
                    } else break;

                }
            } finally {
                r.unlock();
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
