package bufferAllocate;

import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 * Buffer的几个常用方法：
 *
 * allocate() - 初始化一块缓冲区
 * put() - 向缓冲区写入数据
 * get() - 向缓冲区读数据
 * filp() - 将缓冲区的读写模式转换
 * clear() - 这个并不是把缓冲区里的数据清除，而是利用后来写入的数据来覆盖原来写入的数据，以达到类似清除了老的数据的效果
 * compact() - 从读数据切换到写模式，数据不会被清空，会将所有未读的数据copy到缓冲区头部，后续写数据不会覆盖，而是在这些数据之后写数据
 * mark() - 对position做出标记，配合reset使用
 * reset() - 将position置为标记值
 * Buffer的几个核心属性：
 *
 * capacity - 缓冲区大小，缓冲区一旦创建出来以后，这个属性就不会再变化了
 * position - 读写数据的定位指针，用来标识当前读取到了哪一个位置。
 * limit - 读写的边界，用于限制指针的最大指向位置。当指针走到边界上的时候就要停住，否则就会抛出BufferUnderflowException
 */
public class buffer {

    @Test
    public void test_BufferAllocate() {

        System.out.println("----------Test allocate--------");

        System.out.println("before alocate:"
                + Runtime.getRuntime().freeMemory());

        // 如果分配的内存过小，调用Runtime.getRuntime().freeMemory()大小不会变化？
        // 要超过多少内存大小JVM才能感觉到？
        ByteBuffer buffer = ByteBuffer.allocate(102400);
        System.out.println("buffer = " + buffer);

        System.out.println("after alocate:"
                + Runtime.getRuntime().freeMemory());

        // 这部分直接用的系统内存，所以对JVM的内存没有影响
        ByteBuffer directBuffer = ByteBuffer.allocateDirect(102400);
        System.out.println("directBuffer = " + directBuffer);
        System.out.println("after direct alocate:"
                + Runtime.getRuntime().freeMemory());
        //----------Test allocate--------
        //before alocate:525459000
        //buffer = java.nio.HeapByteBuffer[pos=0 lim=102400 cap=102400]
        //after alocate:525459000
        //directBuffer = java.nio.DirectByteBuffer[pos=0 lim=102400 cap=102400]
        //after direct alocate:525459000

        System.out.println("----------Test wrap--------");
        byte[] bytes = new byte[32];
        buffer = ByteBuffer.wrap(bytes);
        System.out.println(buffer);

        buffer = ByteBuffer.wrap(bytes, 10, 10);
        System.out.println(buffer);
        //----------Test wrap--------
        //java.nio.HeapByteBuffer[pos=0 lim=32 cap=32]
        //java.nio.HeapByteBuffer[pos=10 lim=20 cap=32]
    }

    /**
     * put: 会把position指针向后移动，如果position指针超过了limit，则抛出BufferOverflowException
     * 所以每次put后，需要把src使用flip置回pos = 0
     */
    @Test
    public void test_BufferPut() {
        IntBuffer src = IntBuffer.allocate(5);
        for (int i = 0; i < 5; i++) src.put(i);
        IntBuffer buffer = IntBuffer.allocate(10);
        System.out.println("buffer = " + buffer); //[pos=0 lim=10 cap=10]
        System.out.println("src = " + src + ", " + Arrays.toString(src.array()));// [pos=5 lim=5 cap=5]
        src.flip();
        System.out.println("src = " + src);// [pos=5 lim=5 cap=5]
        buffer.put(src);
        src.flip();   //我们可以通过flip来翻转缓冲区
//        src.rewind();
        System.out.println("src = " + src + ", " + Arrays.toString(src.array()));// [pos=5 lim=5 cap=5]

        buffer.put(src);
//        buffer.flip();
        System.out.println("buffer = " + buffer); // [pos=10 lim=10 cap=10]
        System.out.println(Arrays.toString(buffer.array()));
    }

    /**
     *
     * get(): 会把position指针向后移动1个位置
     * get(int i): position 位置不变
     * get(int[] dst, int offset, int length): 会把position指针向后移动 offset + length个位置
     * get(int[] dst):  会把position指针向后移动 offset + dst.length个位置
     * get(index, dst) 等效 get(index, dst, 0, dst.length): position 位置不变
     */
    @Test
    public void test_get() {
        IntBuffer src = IntBuffer.allocate(5);
        for (int i = 0; i < 5; i++) src.put(i); // [0 1 2 3 4]
        System.out.println("src = " + src); //[pos=5 lim=5 cap=5]

        int getFirst = src.get(3); // 3
        System.out.println("getFirst = " + getFirst);
        System.out.println("src = " + src); // [pos=5 lim=5 cap=5]
        src.flip();
        System.out.println("-------get(int[] dst, int offset, int length)----------");
        int[] getDesArr = new int[3];
        // 将src读取到getDesArr数组中，从getDesArr下标为1开始，长度为2
        int[] second = src.get(getDesArr, 1, 2).array(); // (length > limit() - pos)
        System.out.println("Arrays.toString(second) = " + Arrays.toString(second) + ", getDesArr = " + Arrays.toString(getDesArr));
        // Arrays.toString(second) = [0, 1, 2, 3, 4], getDesArr = [0, 0, 1]
        System.out.println("src = " + src); //[pos=2 lim=5 cap=5]
        System.out.println("src = " + src +", pso = " + src.position() +  ", offset = " + src.arrayOffset());
        //src = java.nio.HeapIntBuffer[pos=2 lim=5 cap=5], pso = 2, offset = 0

        System.out.println("-------get()----------");
        IntBuffer src1 = IntBuffer.allocate(5);
        for (int i = 0; i < 5; i++) src1.put(i); // [0 1 2 3 4]
        src1.flip();
        System.out.println("src1 = " + src1 + ", pos = " + src1.position() +  ", offset = " + src1.arrayOffset());
        //src1 = java.nio.HeapIntBuffer[pos=0 lim=5 cap=5], pso = 0, offset = 0
        int first1 = src1.get();
        System.out.println("src1 = " + src1 + ", first = " + first1+ ", pos = " + src1.position() +  ", offset = " + src1.arrayOffset());
        //src1 = java.nio.HeapIntBuffer[pos=1 lim=5 cap=5], first = 0, pos = 1, offset = 0

        System.out.println("-------get(int i)----------");
        IntBuffer src2 = IntBuffer.allocate(5);
        for (int i = 0; i < 5; i++) src2.put(i); // [0 1 2 3 4]
        src2.flip();
        System.out.println("src2 = " + src2);
        //src2 = java.nio.HeapIntBuffer[pos=0 lim=5 cap=5]
        int first2 = src2.get(1);
        System.out.println("src2 = " + src2 + ", first = " + first2 + ", pos = " + src2.position() +  ", offset = " + src2.arrayOffset());
        //src2 = java.nio.HeapIntBuffer[pos=0 lim=5 cap=5], first = 1, pos = 0, offset = 0

        System.out.println("-------get(int[] dst)----------");
        IntBuffer src3 = IntBuffer.allocate(5);
        for (int i = 0; i < 5; i++) src3.put(i); // [0 1 2 3 4]
        src3.flip();
        System.out.println("src3 = " + src3);
        //src3 = java.nio.HeapIntBuffer[pos=0 lim=5 cap=5]
        int[] dst3 = new int[3];
        int[] array = src3.get(dst3).array();
        System.out.println("src3 = " + src3 + ", pos = " + src3.position() +  ", offset = " + src3.arrayOffset());
        //src3 = java.nio.HeapIntBuffer[pos=3 lim=5 cap=5], pos = 3, offset = 0
        System.out.println(Arrays.toString(dst3) + ", array = " + Arrays.toString(array));
        //[0, 1, 2], array = [0, 1, 2, 3, 4]

        System.out.println("-------get(int[] dst) 等效 get(index, dst, 0, dst.length);----------");
        IntBuffer src4 = IntBuffer.allocate(5);
        for (int i = 0; i < 5; i++) src4.put(i); // [0 1 2 3 4]
        src4.flip();
        System.out.println("src4 = " + src4);
        //src4 = java.nio.HeapIntBuffer[pos=0 lim=5 cap=5]
        int[] dst4 = new int[3];
        /**
         * get(index, dst) 等效 get(index, dst, 0, dst.length);
         */
        int[] array4 = src4.get(1, dst4, 1, 2).array();
        System.out.println("src4 = " + src4 + ", pos = " + src4.position() +  ", offset = " + src4.arrayOffset());
        //src4 = java.nio.HeapIntBuffer[pos=0 lim=5 cap=5], pos = 0, offset = 0
        System.out.println(Arrays.toString(dst4) + ", array = " + Arrays.toString(array4));
        //[0, 1, 2], array = [0, 1, 2, 3, 4]
    }


    @Test
    public void test() {
        System.out.printf("%x\n", 525459000);
        //1f51de38
        System.out.println(Integer.toHexString(257949696));
        // f600000

        //生成一个长度为10的缓冲区
        IntBuffer intBuffer = IntBuffer.allocate(10);
        for (int i = 0; i < intBuffer.capacity(); ++i) {
            int randomNum = new SecureRandom().nextInt(20);
            intBuffer.put(randomNum);
        }
        System.out.println(Arrays.toString(intBuffer.array()));
        //[2, 0, 8, 8, 1, 18, 0, 4, 2, 17]
        //状态翻转为读模式
        System.out.println("flip之前：limit = "+ intBuffer);
        //flip涔嬪墠锛歭imit = java.nio.HeapIntBuffer[pos=10 lim=10 cap=10]
        intBuffer.flip();
        System.out.println("flip之后：limit = "+ intBuffer);
        //flip涔嬪悗锛歭imit = java.nio.HeapIntBuffer[pos=0 lim=10 cap=10]
        System.out.println("pos = " + intBuffer.position());
        //pos = 0
        while (intBuffer.hasRemaining()) {
            //读取数据
            System.out.print(intBuffer.get() + ",");
        }
        System.out.println("\npos = " + intBuffer.position());
        //pos = 10

        //clear方法本质上并不是删除数据
        intBuffer.clear();
        System.out.println("-----------------------------");
        System.out.println("pos = " + intBuffer); // [pos=0 lim=10 cap=10]
        while (intBuffer.hasRemaining()) {
            System.out.print(intBuffer.get() + ",");
        }
        System.out.println("pos = " + intBuffer); // [pos=10 lim=10 cap=10]

        //17,19,15,10,18,5,0,0,13,4,
        //-----------------------------
        //17,19,15,10,18,5,0,0,13,4,

    }
}
