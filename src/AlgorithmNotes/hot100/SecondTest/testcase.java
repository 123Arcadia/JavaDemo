package AlgorithmNotes.hot100.SecondTest;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class testcase {

    public int lengthOfLongestSubstring(String s) {
        int ans = 0;
        int idx = 0;
        int n = s.length();
        Set<Character> seen = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (i > 0) {
                // 以i(>0)起始，上去上一个的字符
                seen.remove(s.charAt(i - 1));
            }
            while (idx < n && !seen.contains(s.charAt(idx))) {
                seen.add(s.charAt(idx));
                idx++;
            }
            ans = Math.max(ans, idx - i);
            System.out.println(seen + ", idx=" + idx + ", i=" + i + ", ans=" + ans);
        }
        return ans;

    }


    public long maxArrayValue(int[] nums) {
        int n = nums.length;
        long sum = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            sum = (nums[i] <= sum ? nums[i] + sum : nums[i]);
        }
        return sum;
    }

    public int trap(int[] height) {

//        int n = height.length;
//        if (n <= 2) return 0;
//        Stack<Integer> stk = new Stack<>(); // 存储索引
////        stk.push(0);
//        int sum = 0;
//        for (int i = 0; i < n; i++) {
//            // 出现凸槽
//            while (!stk.isEmpty() && height[i] > height[stk.peek()]) {
//                int mid = stk.pop();
//                if (!stk.isEmpty()) {
//                    int h = Math.min(height[i], height[stk.peek()]) - height[mid]; // 高度差
//                    int w = i - stk.peek() - 1; // 只要中间的宽度
//                    sum += h * w;
//                }
//            }
//            stk.push(i);
//        }
//        return sum;

        // 双指针
        int n = height.length;
        int ans = 0, left = 0, right =n-1;
        int leftMax = 0, rightMax = 0; // 本来是对应的数组，优化空间改为两个数值
        while (left < right) {
            leftMax=Math.max(leftMax, height[left]); // left左边最大的柱子高
            rightMax=Math.max(rightMax, height[right]);
            if (height[left] < height[right]) {
                ans += leftMax - height[left]; // 上面层的雨水
                left++;
            } else {
                ans += rightMax - height[right];
                right--;
            }
        }
        return ans;
    }


    // 由于可能会有大数运算，需要使用long类型，并定义MOD常量
    public static final int MOD = 1000000007;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // 输入数组大小n
        int q = scanner.nextInt(); // 输入操作次数q
        long[] a = new long[n];
        long sum = 0; // 存储数组元素之和

        // 读取数组元素
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
            sum += a[i]; // 计算总和
        }
        System.out.println("sum = " + sum);


        while (q-->0){
            StringBuilder sb = new StringBuilder();
            int idx = scanner.nextInt()-1;
            sb.append("idx=").append(idx).append(", a[idx]=").append(a[idx]).append(", sum=").append(sum);
            sum  = sum * 2 % MOD;
            sb.append(", sum*2=").append(sum);
            sum = (sum - a[idx] + MOD) % MOD;
            sb.append(", sum-a[idx]=").append(sum);
            a[idx] = a[idx] * 2 % MOD;
            sb.append(", a[idx]*2=").append(a[idx]);
            System.out.println(q +","+ sb);
        }

        // 输出最终的总和
        System.out.println(sum);
        scanner.close();
    }

    // 快速幂算法计算(a^b)%mod
    private static long pow(long a, long b, int mod) {
        long result = 1;
        a %= mod;
        while (b > 0) {
            if ((b & 1) == 1) result = (result * a) % mod;
            a = (a * a) % mod;
            b >>= 1;
        }
        return result;
    }




























}
