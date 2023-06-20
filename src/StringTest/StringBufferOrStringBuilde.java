package StringTest;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

public class StringBufferOrStringBuilde {

    /**
     *  divide(BigDecimal，保留小数点后几位小数，舍入模式)
     *  BigDecimal.ROUND_HALF_UP: 四舍五入
     */
    @Test
    public void NullAndEquals() {
        BigInteger bi = new BigInteger("1243324112234324324325235245346567657653");
        BigDecimal bd = new BigDecimal("12435.351");
        BigDecimal bd2 = new BigDecimal("11");
        System.out.println(bi);
        System.out.println(bd.divide(bd2, BigDecimal.ROUND_HALF_UP));
        System.out.println(bd.divide(bd2, 25, BigDecimal.ROUND_HALF_UP));
        System.out.println("bd.divide = " + bd.divide(bd2, 5, BigDecimal.ROUND_HALF_UP));
        //bd.divide(bd, 25, 5) = 1130.48645

    }

}
