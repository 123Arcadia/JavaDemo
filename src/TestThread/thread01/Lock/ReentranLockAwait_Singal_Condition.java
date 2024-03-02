package TestThread.thread01.Lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentranLockAwait_Singal_Condition {
    private static boolean hasCigarette = false;
    private static boolean hasTakeout = false;
    private static final ReentrantLock lock = new ReentrantLock();

    // �ȴ��̵���Ϣ�ң�����������
    static Condition waitCigaretteSet = lock.newCondition();
    // ����������Ϣ�ң�����������
    static Condition waitTakeoutSet = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            lock.lock();
            try {
                System.out.printf("����û��[%b]\n", hasCigarette);
                while (!hasCigarette) {
                    System.out.println("û�̣���Ъ�ᣡ");
                    try {
                        System.out.println("[�ͷ�ǰBEdemo1]" + toStringReentanLock(lock, waitCigaretteSet, Thread.currentThread()));
                        // ��ʱС�Ͻ��뵽 ���̵���Ϣ��
                        waitCigaretteSet.await();
                        System.out.println("[�ͷź�AFdemo1]" + toStringReentanLock(lock, waitCigaretteSet, Thread.currentThread()));

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("������, ���Կ�ʼ�ɻ���");
            } finally {
                lock.unlock();
            }
        }, "С��").start();

        new Thread(() -> {
            lock.lock();
            try {
                System.out.printf("�����͵�û��[%b]\n", hasTakeout);
                while (!hasTakeout) {
                    System.out.println("û��������Ъ�ᣡ");
                    try {
                        System.out.println("[�ͷ�ǰBEdemo2]" + toStringReentanLock(lock, waitTakeoutSet, Thread.currentThread()));

                        // ��ʱСŮ���뵽 ����������Ϣ��
                        waitTakeoutSet.await();
                        System.out.println("[�ͷ�ǰAFdemo2]" + toStringReentanLock(lock, waitTakeoutSet, Thread.currentThread()));

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("��������, ���Կ�ʼ�ɻ���");
            } finally {
                lock.unlock();
            }
        }, "СŮ").start();

        Thread.sleep(1000);
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("������������~");
                hasTakeout = true;
                // ���ѵ�������СŮ�߳�
                waitTakeoutSet.signal();
            } finally {
                lock.unlock();
            }
        }, "��������").start();

        Thread.sleep(1000);
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("���̵�����~");
                hasCigarette = true;
                // ���ѵ��̵�С���߳�
                waitCigaretteSet.signal();
            } finally {
                lock.unlock();
            }
        }, "���̵�").start();
        //����û��[false]
        //û�̣���Ъ�ᣡ
        //[�ͷ�ǰBEdemo1]java.util.concurrent.locks.ReentrantLock@482ddaf9[Locked by thread С��], isFair: false, isHeldByCurrentThread: true, HoldCount=1, QueueLength=1, hasQueuedThreads=true, hasWaiters(lockCondition)=false, hasQueuedThread(curThread)=false, hasQueuedThread(curThread)=false
        //�����͵�û��[false]
        //û��������Ъ�ᣡ
        //[�ͷ�ǰBEdemo2]java.util.concurrent.locks.ReentrantLock@482ddaf9[Locked by thread СŮ], isFair: false, isHeldByCurrentThread: true, HoldCount=1, QueueLength=0, hasQueuedThreads=false, hasWaiters(lockCondition)=false, hasQueuedThread(curThread)=false, hasQueuedThread(curThread)=false
        //������������~
        //[�ͷ�ǰAFdemo2]java.util.concurrent.locks.ReentrantLock@482ddaf9[Locked by thread СŮ], isFair: false, isHeldByCurrentThread: true, HoldCount=1, QueueLength=0, hasQueuedThreads=false, hasWaiters(lockCondition)=false, hasQueuedThread(curThread)=false, hasQueuedThread(curThread)=false
        //��������, ���Կ�ʼ�ɻ���
        //���̵�����~
        //[�ͷź�AFdemo1]java.util.concurrent.locks.ReentrantLock@482ddaf9[Locked by thread С��], isFair: false, isHeldByCurrentThread: true, HoldCount=1, QueueLength=0, hasQueuedThreads=false, hasWaiters(lockCondition)=false, hasQueuedThread(curThread)=false, hasQueuedThread(curThread)=false
        //������, ���Կ�ʼ�ɻ���
    }

    /**
     *
     * @param lock
     * @param lockCondition
     * @param curThread
     * @return
     */
    public static String toStringReentanLock(ReentrantLock lock, Condition lockCondition, Thread curThread) {
        StringBuilder sb = new StringBuilder();
        sb.append(lock.toString()).append(", isFair: ").append(lock.isFair())
                .append(", isHeldByCurrentThread: ").append(lock.isHeldByCurrentThread())
                .append(", HoldCount=").append(lock.getHoldCount())
                .append(", QueueLength=").append(lock.getQueueLength())
                .append(", hasQueuedThreads=").append(lock.hasQueuedThreads());


        if (lockCondition!=null) {
            sb.append(", hasWaiters(lockCondition)=").append(lock.hasWaiters(lockCondition));
            if (curThread!=null) {
                sb.append(", hasQueuedThread(curThread)=").append(lock.hasQueuedThread(curThread))
                        .append(", hasQueuedThread(curThread)=").append(lock.hasQueuedThread(curThread));
            }
        }
        return sb.toString();
    }

}
