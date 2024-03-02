package AlgorithmNotes.greed;

public class monotoneIncreasingDigits738 {


    public int monotoneIncreasingDigits(int n) {
        //当发现当前数字 > 下一个数字时，就把当前数字 - 1，后面的所有数字都设置成 9
        String[] strings = String.valueOf(n).split("");
        int flag = strings.length;
        for (int i = strings.length - 1; i >0; i--) {
            if (Integer.parseInt(strings[i-1]) > Integer.parseInt(strings[i])) {
                //大
                flag = i;
                strings[i-1] = String.valueOf(Integer.parseInt(strings[i-1])-1);
            }
        }
        // flag 记录的只是i,而我们遍历会到i-1

        for (int i = flag; i < strings.length; i++) {
            strings[i] = "9";
        }
        return Integer.parseInt(String.join("", strings));
    }
}
