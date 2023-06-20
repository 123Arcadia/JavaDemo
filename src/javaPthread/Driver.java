package javaPthread;

public class Driver {
    public static void main(String[] args) {

        String[] s = {"3", "1", "2", "3"};
        if (s.length > 0) {
            if (Integer.parseInt(s[0]) < 0) {
                System.err.println("error");
            } else {
                Sum sumObject = new Sum();
                int upper = Integer.parseInt(s[0]);
                //Summation summation = new Summation(upper, sumObject);
                Thread thrd = new Thread((java.lang.Runnable) new Summation(upper, sumObject));
                thrd.start();
                try {
                    thrd.join();
                    System.out.println("sum = " + sumObject.getSum());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }

        } else {
            System.err.println("error2");
        }
    }
}

class Sum {
    private int sum;

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}

class Summation implements Runnable {
    private int upper;
    private Sum sumValue;

    public Summation(int upper, Sum sumValue) {
        this.upper = upper;
        this.sumValue = sumValue;
    }

    @Override
    public void run() {
        int sum = 0;
        for (int i = 0; i <= upper; i++) {
            sum += i;
        }
        sumValue.setSum(sum);
    }

}