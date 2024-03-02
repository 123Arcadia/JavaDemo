package TestThread.thread01.Lock;

import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.locks.StampedLock;

/**
 * StampedLock������3��ģʽ��
 * <p>
 * 1. ���۶�������ReadWriteLock�Ķ������ƣ�����Ķ����������룩������߳̿���ͬʱ��ȡ���۶��������۶�����һ����������
 * 2. �ֹ۶������൱��ֱ�Ӳ������ݣ������κ���������������Ҫ���ڲ�������ǰ��û��ͨ��CAS ��������״̬������ͨ��λ������ԡ������ǰû���̳߳���д�� ����򵥵ط��� һ���� 0 �� stamp �汾��Ϣ ������0��˵�����̳߳���д���� ��ȡ�� stamp ���ھ����������ǰ����Ҫ����validate ������֤�� s tamp �Ƿ񼺾�������
 * 3. д������ReadWriteLock��д�����ƣ�д���ͱ��۶����ǻ���ġ���Ȼд�����ֹ۶������ụ�⣬���������ݱ�����֮��֮ǰͨ���ֹ۶�����õ������Ѿ�����������ݡ��� һ �����������߶�ռ����ĳʱֻ�� һ ���߳̿��Ի�ȡ�������������̻߳�ȡ�������������������д�����̱߳��� �ȴ� ����������ReentrantReadWriteLock ��д������ͬ�����������д���������룩
 * <p>
 * <p>
 * StampedLock �Ķ�д������[����������]�������ڻ�ȡ�����ͷ���ǰ��Ӧ���ٵ��û��ȡ���Ĳ������Ա�����ɵ����̱߳�����
 */
public class StampedLockTest {
    //����1��map ����������
    final static HashMap<String, String> MAP = new HashMap<>();
    //����һ��ӡ����
    final static StampedLock STAMPED_LOCK = new StampedLock();

    /**
     * �Թ������ݵ�д����
     */
    public static Object put(String key, String value) {
        long stamp = STAMPED_LOCK.writeLock();
        try {
            System.out.println(Thread.currentThread().getName()+", "+ getNowTime() + ": ��ռ��д������ʼд����");
            String put = MAP.put(key, value);
            return put;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName()+", "+ getNowTime() + ": �ͷ���д��");
            STAMPED_LOCK.unlock(stamp);
        }
        return null;
    }

    /**
     * �Թ������ݵı��۶�����
     */
    public static Object pessimisticRead(String key) {
        System.out.println(Thread.currentThread().getName()+", "+ getNowTime() + ":  �����дģʽ��ֻ�ܱ��۶�");
        long stamp = STAMPED_LOCK.readLock();
        try {
            System.out.println(Thread.currentThread().getName()+", "+ getNowTime() + ": ��ȡ�˶���");
            String value = MAP.get(key);
            return value;
        } finally {
            System.out.println(Thread.currentThread().getName()+", "+ getNowTime() + ": �ͷ��˶���");
            STAMPED_LOCK.unlockRead(stamp);
        }
    }

    /**
     * �Թ������ݵ��ֹ۶�����
     */
    public static Object optimisticRead(String key) {
        String value = null;
        /**
         * ����1��stamp==0˵����ǰΪд��ģʽ��ֻ��ʹ�ñ��۶�
         * ����2���� �ֹ۶��Ѿ�����һ��ʱ�䣬�ڼ���ܷ���д�룬������֤�ֹ۶���ӡ��ֵ�Ƿ���Ч�����ж�LOCK�Ƿ�����дģʽ
         */
        long stamp = STAMPED_LOCK.tryOptimisticRead();
        if (stamp != 0) {
            System.out.println(Thread.currentThread().getName()+", "+ getNowTime() + ":  �ֹ�����ӡ��ֵ��ȡ�ɹ�");
            value = MAP.get(key);
        } else if (stamp == 0) { //����1
            System.out.println(Thread.currentThread().getName()+", "+ getNowTime() + ":  �ֹ�����ӡ��ֵ��ȡʧ�ܣ���ʼʹ�ñ��۶�");
            return pessimisticRead(key);
        }
        if (!STAMPED_LOCK.validate(stamp)) {//����2��
            System.out.println(Thread.currentThread().getName()+", "+ getNowTime() + ":  �ֹ۶���ӡ��ֵ�Ѿ�����");
            return pessimisticRead(key);
        } else {
            System.out.println(Thread.currentThread().getName()+", "+ getNowTime() + ":  �ֹ۶���ӡ��ֵû�й���");
            return value;
        }
    }

    public static Date getNowTime() {
        return new Date();
    }


    public static void main(String[] args) throws InterruptedException {
        MAP.put("initKey", "initValue");
        Thread t1 = new Thread(() -> {
            // �ֹ۶�
            System.out.println(optimisticRead("initKey"));
        }, "���߳�1");
        Thread t2 = new Thread(() -> {
            put("key1", "value1");
        }, "д�߳�1");
        // д֮�����ֹ۶�
        Thread t3 = new Thread(() -> {
            System.out.println(optimisticRead("initKey"));
        }, "���߳�2");
        t1.start();
        t1.join();
        t2.start();

        t3.start();
        Thread.sleep(1000);
        //���߳�1, Sat Mar 02 21:08:01 CST 2024:  �ֹ�����ӡ��ֵ��ȡ�ɹ�
        //���߳�1, Sat Mar 02 21:08:01 CST 2024:  �ֹ۶���ӡ��ֵû�й���
        //initValue
        //д�߳�1, Sat Mar 02 21:08:01 CST 2024: ��ռ��д������ʼд����
        //���߳�2, Sat Mar 02 21:08:01 CST 2024:  �ֹ�����ӡ��ֵ��ȡʧ�ܣ���ʼʹ�ñ��۶�
        //д�߳�1, Sat Mar 02 21:08:01 CST 2024: �ͷ���д��
        //���߳�2, Sat Mar 02 21:08:01 CST 2024:  �����дģʽ��ֻ�ܱ��۶�
        //���߳�2, Sat Mar 02 21:08:01 CST 2024: ��ȡ�˶���
        //���߳�2, Sat Mar 02 21:08:01 CST 2024: �ͷ��˶���
        //initValue
        //
        //Process finished with exit code 0
    }

}
