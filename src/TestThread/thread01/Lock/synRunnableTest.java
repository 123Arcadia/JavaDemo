package TestThread.thread01.Lock;

public class synRunnableTest {
    public static void main(String[] args) {
        WindowSync window = new WindowSync();
        Thread w1 = new Thread(window);
        Thread w2 = new Thread(window);
        Thread w3 = new Thread(window);
        // 3��������Ʊ: һ��20��Ʊ
        w1.setName("����1-");
        w2.setName("����2-");
        w3.setName("����3-");
        w1.start();
        w2.start();
        w3.start();
        // ����һ����������

    }
}

class WindowSync implements Runnable {
    // ����static ������ľ�̬����
    private static int ticket = 100;

    @Override
    public void run() {

        while (true) {
//            synchronized (Window.class) {
                // Ʊ�Ų�Ϊ0����ÿ��10ms��Ʊ
                if (!show())
                    break;
//            }
        }
    }

    //������ͨ����
    private synchronized  boolean show() {
        if (ticket > 0) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " ��Ʊ�� " + ticket--);
            return true;
        } else {
            return false;
        }
    }
}
