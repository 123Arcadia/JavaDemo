package EnumTest;

import java.util.Arrays;

public enum Operation {
    PLUS{ double eval(double x, double y) {return x+y;}},
    MINUS{ double eval(double x, double y) {return x-y;}},
    TIMES{ double eval(double x, double y) {return x*y;}},
    DIVIDE{ double eval(double x, double y) {return x/y;}};

    abstract double eval(double x, double y);

    public static void main(String[] args) {
        System.out.println(Arrays.toString(Operation.values()));
        // [PLUS, MINUS, TIMES, DIVIDE]
        String[] arg = new String[]{"1", "2"};
        double x = Double.parseDouble(arg[0]);
        double y = Double.parseDouble(arg[1]);
        for (Operation op : Operation.values()) {
            System.out.printf("%f %s %f = %f%n", x, op, y, op.eval(x, y));
        }
        //1.000000 PLUS 2.000000 = 3.000000
        //1.000000 MINUS 2.000000 = -1.000000
        //1.000000 TIMES 2.000000 = 2.000000
        //1.000000 DIVIDE 2.000000 = 0.500000

        System.out.println(Operation.DIVIDE); // DIVIDE
        System.out.println(Operation.DIVIDE.eval(2, 3)); // 0.6666666666666666
        System.out.println(Operation.DIVIDE.name()); // DIVIDE

    }
}