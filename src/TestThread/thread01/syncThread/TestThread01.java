package TestThread.thread01.syncThread;

/**
 * 声明一个数字并赋值10000.然后让1w个线程去减少1，1w个线程去增加1。
 * 理论上说，加一万减一万，最后数字的结果并不会改变
 */
public class TestThread01 {

    public static void main(String[] args) throws InterruptedException {
        //因为synchronized里面要求的是对象，所以需要用Integer声明
        Integer Num = 10000;

        int n = 10000;

        //声明线程数组，在后面使用join让主线程等待所有线程执行完。
        // 不然主线程跑完了，其他线程没执行完就输出结果会不对的。
        Thread[] add = new Thread[n];
        Thread[] reduce = new Thread[n];
        //创建1w个减少1的线程
        for (int i = 0; i < n; i++) {
            Thread t1 = new Thread() {
                public void run() {
                    synchronized (Num) {
                        reduceNum(Num);
                    }
                }
            };
            t1.start();
            reduce[i] = t1;
        }
        //创建1w个增加线程
        for (int i = 0; i < n; i++) {
            Thread t2 = new Thread() {
                public void run() {
                    synchronized (Num) {
                        addNum(Num);
                    }
                }
            };
            t2.start();
            add[i] = t2;
        }

        //让主线程等待所有线程执行完毕
        for (Thread t : add) {
            t.join();
        }
        for (Thread t : reduce) {
            t.join();
        }

        //输出结果
        System.out.println(Num);
        // 9998 (反正不会是10000)
        /**
         * 造成这种错误的原因是：
         *  假设增加线程获取到数字是10000，进行了加一操作，结果是10001。
         *  但是减少线程在返回结果之前也获取到了数字10000。
         *  减少线程最后返回9999。
         *  所以我们原本期望得到的10000变成了9999
         */
        /**
         * <b>第一种:</b> 使用synchronized修饰方法，可以解决这个问题。
         *      synchronized (Num) {
         *           addNum(Num);
         *       }
         *       或者：
         *       public synchronized static int addNum(int Num){
         *         return Num+1;
         *       }
         */


    }


    public static int addNum(int Num){
        return Num+1;
    }
    public static int reduceNum(int Num){
        return Num-1;
    }
}

