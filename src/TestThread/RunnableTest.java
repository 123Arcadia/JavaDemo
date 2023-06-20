package TestThread;

public class RunnableTest{
    public static void main(String[] args) {
        RunnableClass r = new RunnableClass();
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        Thread t3 = new Thread(r);
        t1.setName("线程1->");
        t2.setName("线程2->");
        t3.setName("线程3->");
        t1.start();
        t2.start();
        t3.start();
    }
}
class RunnableClass implements Runnable {
    private int ticket = 20;

    @Override
    public void run() {
        while (true) {
            if (ticket > 0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (this) {
                    System.out.println(Thread.currentThread().getName() + " " + ticket--);
                }
            } else {
                break;
            }
        }
    }
}