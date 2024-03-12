package AlgorithmNotes.hot100;

import org.junit.Test;

import java.util.*;

public class sortColors {
    public void sortColors(int[] nums) {
        //0、 1 和 2 分别表示红色、白色和蓝色
        // 冒泡
//        for (int i = 0; i < nums.length; i++) {
//            for (int j = 0; j < nums.length - 1; j++) {
//                if (nums[j] > nums[j + 1]) {
//                    int temp = nums[j];
//                    nums[j] = nums[j + 1];
//                    nums[j + 1] = temp;
//                }
//            }
//        }

        int n = nums.length;
//         int ptr = 0; // 0的素银
//        // 把0置前
//        for (int i = 0; i < n; i++) {
//            if (nums[i] == 0) {
//                int t = nums[i];
//                nums[i] = nums[ptr];
//                nums[ptr] = t;
//                ptr++;
//            }
//        }
//        // 把1置0后
//        for (int i = 0; i < n; i++) {
//            if (nums[i] == 1) {
//                int t = nums[i];
//                nums[i] = nums[ptr];
//                nums[ptr] = t;
//                ++ptr;
//            }
//        }
//        int p0 = 0, p1 = 0;
//        for (int i = 0; i < n; i++) {
//            if (nums[i] == 0) {
//                int t = nums[i];
//                nums[i] = nums[p0];
//                nums[p0] = t;
//                if (p0 < p1) { // 此时交换后nums[i] != 0
//                    t = nums[i];// nums[i] 、 nums[p1]: 此时nums[p1]是第一个2的索引上
//                    nums[i] = nums[p1];
//                    nums[p1] = t;
//                }
//                p0++;
//                p1++;
//            } else if (nums[i] == 1) {
//                int t = nums[i];
//                nums[i] = nums[p1];
//                nums[p1] = t;
//                p1++;
//            }
//        }
        int l = -1, r = n;
        int i = 0;
        while (i < r) {
            if (nums[i] == 1) {
                i++;
            } else if (nums[i] == 0) {
                swap(nums, l + 1, i);
                l++;
                i++;
            } else {
                swap(nums, r - 1, i);
                r--;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;

    }

    public String[] shortestSubstrings(String[] arr) {
        String[] ans = new String[arr.length];
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            String s = arr[i], t = "";
            List<String> list = getAllSubStrings(s);
            list.sort((a, b) -> {
                return a.length() - b.length();
            });
            for (String l : list) {
                if (check(l, arr, i)) {
                    if (t.length() == 0) {
                        t = l;
                    } else {
                        // 有限长度短的，在字典序选取
                        if (l.length() < t.length()) t = l;
                        else if (t.compareTo(l) > 0) {
                            // 字典序
                            t = l;
                        }
                    }
                }
            }
            ans[i] = t;
        }
        return ans;

    }

    private boolean check(String l, String[] arr, int i) {
        for (int j = 0; j < arr.length; j++) {
            if (j == i) continue;
            if (arr[i].contains(l)) {
                return false;
            }
        }
        return true;
    }

    private List<String> getAllSubStrings(String s) {

        List<String> res= new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            for (int j = i+1; j <=s.length(); j++) {
                res.add(s.substring(i, j));
            }
        }
        return res;
    }


    @Test
    public void test() {
        String[] str = {"cab","ad","bad","c"};
        Arrays.sort(str, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
               return  o2.length() - o1.length();
            }
        });
        System.out.println(Arrays.toString(str));
    }



}
