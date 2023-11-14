package bytes;

import org.junit.Test;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class CharSet {

    /**
     * 字符串指字符转换:
     *  Charset.forName("utf-8")、getBytes(StandardCharsets.UTF_8);
     */
    @Test
    public void StrToCharSet() {
        String str = "abc";
        byte[] utf8 = str.getBytes(StandardCharsets.UTF_8);
        System.out.println("utf8 = " + utf8);
        byte[] utf8Assign = str.getBytes(Charset.forName("utf-8"));
        System.out.println("utf8Assign = " + utf8Assign);
//        utf8 = [B@23223dd8
//        utf8Assign = [B@4ec6a292
        System.out.println(Arrays.toString(utf8));
        // [97, 98, 99]  因为append接收参数是int
        System.out.println(new String(utf8)); // abc

    }
}
