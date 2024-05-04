package BlockQueueCase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockQueueImpl {

    private List<Integer> list = new ArrayList<>();
    private volatile int size;
    private volatile int capacity;
    private Lock lock = new ReentrantLock();
    private final Condition isFull = lock.newCondition();;
    private final Condition isNull = lock.newCondition();;

    public BlockQueueImpl(int capacity) {
        this.capacity = capacity;
    }

    public void add(int data) {
        try {
            lock.lock();

            try {
                while (size >= capacity) {
                    System.out.println("队列已满! is Full");
                    isFull.await();
                }
            } catch (InterruptedException e) {
                isFull.signal();
                e.printStackTrace();
            }
            ++size;
            list.add(data);
            isNull.signal(); // 唤醒一个等待线程
        } finally {
            lock.unlock();
        }

    }

    public int take() {
        try {
            lock.lock();

            try {
                while (size == 0) {
                    System.out.println("队列为空! is Null");
                    isNull.await();
                }
            } catch (InterruptedException e) {
                isNull.signal();
                e.printStackTrace();
            }
            --size;
            int res = list.get(0);
            list.remove(0);
            isFull.signal();
            return res;
        } finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        BlockQueueImpl blockQueue = new BlockQueueImpl(5);
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                blockQueue.add(i);
                System.out.println("add:" + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (;;) {
                System.out.println("take:" + blockQueue.take());
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
