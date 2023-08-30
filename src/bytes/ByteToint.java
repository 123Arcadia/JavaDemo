package bytes;

import org.junit.Test;

public class ByteToint {


    public int bytesToInt(byte[] ary, int offset) {
        int value;
        value = (int) ((ary[offset] & 0xFF) | ((ary[offset + 1] << 8) & 0xFF00) | ((ary[offset + 2] << 16) & 0xFF0000)
                | ((ary[offset + 3] << 24) & 0xFF000000));
        return value;
    }


    @Test
    public void test_onint() {
        byte[] arr = new byte[4];
       int res =  byteToint(arr, 0);
        System.out.println("res = " + res);

    }

    public int byteToint(byte[] ary, int offset) {
        int value;
        value = (int) ((ary[offset] & 0xFF) | ((ary[offset + 1] << 8) & 0xFF00) | ((ary[offset + 2] << 16) & 0xFF0000)
                | ((ary[offset + 3] << 24) & 0xFF000000));
        return value;

    }

}
