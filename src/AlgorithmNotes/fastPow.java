package AlgorithmNotes;

public class fastPow {

    // 定义一个方法进行快速幂运算
    public static long fastPow(long a, int b) {
        if (b == 0) {
            return 1;
        }
        if (b == 1) {
            return a;
        }
        long half = fastPow(a, b / 2);
        if (b % 2 == 0) {
            // 如果b是偶数，则结果为 half 自乘
            return half * half;
        } else {
            // 如果b是奇数，则结果为 a 乘以 half 的平方
            return a * half * half;
        }
    }

    public static void main(String[] args) {
        long base = 3; // 基数
        int exponent = 5; // 指数
        System.out.println("Result: " + fastPow(base, exponent)); // 输出结果
        double res = Math.pow(2,3);
    }

}
