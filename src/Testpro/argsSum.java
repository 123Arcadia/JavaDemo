package Testpro;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class argsSum {
//    public static void main(String[] args) {
//        int sum = 0;
//        for (int i = 0; i < args.length; i++) {
//            sum += Integer.parseInt(args[i]);
//        }
//        System.out.println("sum = " + sum);
//    }

    @Test
    public void equalsTest() {
        Set<Integer> aset = new HashSet<>();
        aset.add(5);
        aset.add(23);
        aset.add(42);

        System.out.println(aset);

        Set<Integer> aset2 = new HashSet<>();
        aset2.add(5);
        aset2.add(23);
        aset2.add(42);

        System.out.println(aset.equals(aset2));

    }
}


