package org.example.cglibProxy;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class test3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String t =sc.nextLine();
        t = t.substring(1, t.length()-1);
        String[] numsStr  = t.split(",");
        int[] nums = new int[numsStr.length];
        for (int i = 0; i < nums.length; i++){
            nums[i] = Integer.parseInt(numsStr[i]);
        }
        int k = Integer.parseInt(sc.nextLine());
        Map<Integer, Integer> map= new HashMap<>();
        map.put(0, -1);
        int sum =0;
        // 求余数
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (k != 0) {
                sum %= k;
            }
            if (map.containsKey(sum)) {
                if (i - map.get(sum) > 1) {
                    System.out.println(true);
                    return ;
                }
            } else {
                map.put(sum, i);
            }
        }
        System.out.println(false);

    }


}
