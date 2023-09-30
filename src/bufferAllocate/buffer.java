package bufferAllocate;

import org.junit.Test;

import java.nio.ByteBuffer;

public class buffer {

    @Test
    public void test_BufferAllocate (){

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
        //before alocate:257949696
        //buffer = java.nio.HeapByteBuffer[pos=0 lim=102400 cap=102400]
        //after alocate:257486232
        //directBuffer = java.nio.DirectByteBuffer[pos=0 lim=102400 cap=102400]
        //after direct alocate:257486232

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
}
