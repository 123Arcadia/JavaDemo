package AlgorithmNotes;

public class fastPow {

    // 定义一个方法进行快速幂运算
    private static double fastPow(double x, int n) {
        if (n == 0) return 1;
        double res = fastPow(x, n/2);
        return n % 2 == 0 ? res * res : res *res * x;
    }

    public static void main(String[] args) {
        long base = 3; // 基数
        int exponent = 5; // 指数
        System.out.println("Result: " + fastPow(base, exponent)); // 输出结果
        double res = Math.pow(2,3);
    }




}
