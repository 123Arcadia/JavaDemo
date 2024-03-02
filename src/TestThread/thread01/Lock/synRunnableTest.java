package TestThread.thread01.Lock;

public class synRunnableTest {
    public static void main(String[] args) {
        WindowSync window = new WindowSync();
        Thread w1 = new Thread(window);
        Thread w2 = new Thread(window);
        Thread w3 = new Thread(window);
        // 3个窗口卖票: 一共20张票
        w1.setName("窗口1-");
        w2.setName("窗口2-");
        w3.setName("窗口3-");
        w1.start();
        w2.start();
        w3.start();
        // 任意一个窗口卖完

    }
}

class WindowSync implements Runnable {
    // 加上static 才是类的静态变量
    private static int ticket = 100;

    @Override
    public void run() {

        while (true) {
//            synchronized (Window.class) {
                // 票号不为0，就每隔10ms卖票
                if (!show())
                    break;
//            }
        }
    }

    //修饰普通方法
    private synchronized  boolean show() {
        if (ticket > 0) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " 卖票号 " + ticket--);
            return true;
        } else {
            return false;
        }
    }
}
