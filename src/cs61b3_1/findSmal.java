package cs61b3_1;


import org.junit.Test;

public class findSmal {
    public static void main(String[] args) {
        String[] x = {"c", "b", "f", "a", "g"};
        System.out.println(findSmallest(x));
    }

    public static int findSmallest(String... x) {
        int smallestIndex = 0;
        for (int i = 0; i < x.length; i++) {
            int cmp = x[i].compareTo(x[smallestIndex]);
            if (cmp < 0) smallestIndex = i;
        }
        return smallestIndex;
    }

    @Test
    public void testfindSmallest() {

    }
}
