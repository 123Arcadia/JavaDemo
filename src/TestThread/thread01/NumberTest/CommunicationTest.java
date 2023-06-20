package TestThread.thread01.NumberTest;

public class CommunicationTest {
    public static void main(String[] args) {
        Number number = new Number();
        Thread thread1 = new Thread(number);
        Thread thread2 = new Thread(number);


        thread1.setName("线程1");
        thread2.setName("线程2");

        thread1.start();
        thread2.start();
    }
}

class Number implements Runnable {
    private int number = 1;

    @Override
    public void run() {
       while (true) {

           synchronized (this) {
               if (number <= 50) {

                   notify();
//                   try {
//                       Thread.sleep(10);
//                   } catch (InterruptedException e) {
//                       e.printStackTrace();
//                   }
                   System.out.println(Thread.currentThread().getName() + ":" + number);
                   number++;

                   try {
                       wait();
                   } catch (InterruptedException e) {
                      e.printStackTrace();
                   }
               } else {
                   break;
               }
           }
       }
    }
}
