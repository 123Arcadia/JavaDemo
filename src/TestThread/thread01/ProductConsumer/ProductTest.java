package TestThread.thread01.ProductConsumer;

public class ProductTest {

    public static void main(String[] args) {
        Clerk clerk = new Clerk();

        Producer p1 = new Producer(clerk);
        p1.setName("������1");

        Consumer c1 = new Consumer(clerk);
        c1.setName("������1");

        Consumer c2 = new Consumer(clerk);
        c2.setName("������2");

        p1.start();
        c1.start();
        c2.start();
    }
}


class Clerk {

    private int productCount = 0;

    public synchronized void produceProduct() {
        if (productCount < 20) {
            productCount++;
            System.out.println(Thread.currentThread().getName() + ":��ʼ������" + productCount + "����Ʒ");
            notify();//����
        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void consumeProduct() {
        if (productCount > 0) {
            System.out.println(Thread.currentThread().getName() + ":��ʼ���ѵ�" + productCount + "����Ʒ");
            productCount--;
            notify();//����
        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class Producer extends Thread{
    private Clerk clerk;

    public Producer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println(getName() + ":��ʼ����...");

        while (true) {

            try {
                sleep(10);  //10��������һ��
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.produceProduct();
        }
    }
}

class Consumer extends  Thread{
    private Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println(getName() + ":��ʼ����...");

        while (true) {

            try {
                sleep(20); //20��������һ��
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.consumeProduct();
        }
    }
}