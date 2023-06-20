package TestThread;

public class synRunnableTest {
    public static void main(String[] args) {
        Window window = new Window();
        Thread w1 = new Thread(window);
        Thread w2 = new Thread(window);
        Thread w3 = new Thread(window);
        w1.setName("窗口1-");
        w2.setName("窗口2-");
        w3.setName("窗口3-");
        w1.start();
        w2.start();
        w3.start();

    }
}

class Window implements Runnable {
    private int ticket = 20;

    @Override
    public void run() {
        while (true) {
            if (!show())
                break;
        }
    }
    //修饰普通方法
    private synchronized boolean show() {
        if (ticket > 0) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "卖票号" + ticket--);
            return true;
        } else return false;
    }
}
