package TestThread.thread01;

public class ThreadDemo {
    public static void main(String[] args) {
        MyThread01 myThread01 = new MyThread01();
        MyThread02 myThread02 = new MyThread02();

//        MyThread01.currentThread().setName("01-->");
//        MyThread02.currentThread().setName("02-->");


        myThread01.start();
        myThread02.start();

        //new MyThread01().start();  //第3线程

        new Thread() {
            @Override
            public void run() {
                this.setName("03--->");
                for (int i = 0; i < 20; i++) {
                    System.out.println(getName() + "匿名调用Thread" + "-> " + i + "优先级：" + getPriority());
                    if (i == 3) {
                        try {
                            myThread01.join();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                System.out.println("判断是否线程结束:" + this.isAlive());
            }
        }.start();
    }

}
class MyThread01 extends Thread {

    @Override
    public void run() {
        this.setName("01--->");
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
//                try {
//                    sleep(2);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
                System.out.println(Thread.currentThread().getName() + "(01调用):" + i + "优先级：" + getPriority());
                //MyThread01.currentThread().yield();
                //this.yield();

            }


        }
    }
}

class MyThread02 extends Thread {
    @Override
    public void run() {
        this.setName("02--->");
        setPriority(MIN_PRIORITY);
        for (int i = 0; i < 10; i++) {
            if (i % 2 != 0) {
                System.out.println(Thread.currentThread().getName() + "(02调用):" + i + "优先级：" + getPriority());
            }
        }
    }
}