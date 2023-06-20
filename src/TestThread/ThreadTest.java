package TestThread;

public class ThreadTest{
    public static void main(String[] args)
    {
        MyThread t1 = new MyThread();
//        MyThread t2 = new MyThread();
//
        t1.setName("线程1(偶): ");
//        t2.setName("Thread 2 : ");
        t1.start();
//        t2.start();
        //t1.run();
//
//        for (int i = 0; i < 10; i++) {
//            if (i%2 == 0) {
//                System.out.println(i + "**main**");
//            }
//        }
        System.out.println("hello");
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                if (i % 2 != 0) {
                    System.out.println(Thread.currentThread().getName() +"线程2(奇):" + i);
                }
            }
        }).start();

        //将main()命名为主线程-
        Thread.currentThread().setName("主线程(奇)-");
        for (int i = 0; i < 10; i++) {
            if(i%2!=0){
                System.out.println(Thread.currentThread().getName()+i);
            }
        }
    }
}
class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            if (i%2 == 0) {
                System.out.println(Thread.currentThread().getName() +":" +i);
            }
        }
    }
}
