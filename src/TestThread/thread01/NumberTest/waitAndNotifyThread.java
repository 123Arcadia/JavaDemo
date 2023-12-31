package TestThread.thread01.NumberTest;

/**
 * wait: 让占用了这个同步对象的线程，临时释放当前的占用，并且等待。
 *      所以调用wait是有前提条件的，一定是在synchronized块里，否则就会出错。
 *<br>
 *  notify: 通知一个等待在这个同步对象上的线程，你可以苏醒过来了，
 *      有机会重新占用当前对象了。当这个线程执行完再次释放后，之前等待的线程会重
 *      新开始占用，并不会一直等下去。
 *<br>
 *  notifyAll: 通知所有的等待在这个同步对象上的线程，你们可以苏醒过来了，
 *      有机会重新占用当前对象了。
 *<br>
 * Java中sleep和wait的区别在于：
 * 	• sleep是Thread类的静态方法，可以让当前线程暂停执行一段时间，<b>但不会释放锁</b>；而wait是Object类的方法，可以让当前线程暂停执行，同时释放锁，等待其他线程调用notify或notifyAll方法唤醒。
 * 	• sleep方法可以在任何地方调用，而wait方法必须在synchronized块或方法中调用，因为wait方法需要先获得对象的锁才能执行。
 * 	• sleep方法的调用不需要被唤醒，时间到了自动恢复执行；而wait方法必须被其他线程调用notify或notifyAll方法唤醒才能继续执行。
 */
public class waitAndNotifyThread{
    public static void main(String[] args) {
        // 交替打印数字
        Number number = new Number();
        Thread thread1 = new Thread(number);
        Thread thread2 = new Thread(number);


        thread1.setName("Thread 1");
        thread2.setName("Thread 2");

        thread1.start();
        thread2.start();
        System.out.println("--------------END--------------");
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
