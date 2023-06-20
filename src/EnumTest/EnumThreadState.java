package EnumTest;

import org.junit.Test;

public class EnumThreadState {

    @Test
    //之前说过 线程的状态也是枚举类
    public void test01() {
        Thread.State[] threads = Thread.State.values();
        for (int i = 0; i < threads.length; i++) {
            System.out.println(threads[i].toString());
        }

        for (Thread.State value : Thread.State.values()) {
            System.out.println(value);
        }
        //NEW
        //RUNNABLE
        //BLOCKED
        //WAITING
        //TIMED_WAITING
        //TERMINATED
    }
}
