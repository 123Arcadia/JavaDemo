package org.example.cglibProxy;

import java.util.*;

public class test {

   static List<List<Character>> list = new ArrayList<>();
   static List<Character> path = new ArrayList<>();
   static int cnt;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String t = sc.nextLine();
        if (t.length() >= 2) {
            t = t.substring(1, t.length() - 1);
        }
        String[] temp =  t.split(",");
        char[] chars = new char[temp.length];
        for (int i = 0; i < temp.length; i++) {
            chars[i] = temp[i].charAt(0);
        }
        int cnt = Integer.parseInt(sc.nextLine());

        // 窗口1, ... n-1长度
        int ans = 0;
//        for (int i = 1; i < chars.length - 1; i++) {
//            Map<Character, Integer> count = new HashMap<>();
//            int l = 0;
//            while (l < chars.length) {
//                int r = l + i - 1;
//                if (count.containsKey(chars[l]) && count.get(chars[l]) >= cnt) {
//                    l = r;
//                    continue;
//                }
//                count.put(chars[l], count.getOrDefault(chars[l], 0) + 1);
//                ans++;
//            }
//            System.out.println(i + ", l = " + l +  ", r = "+ (i - l + 1));
//        }
//        System.out.println(ans);


        // 全排列
//        Arrays.sort(chars);
        System.out.println(Arrays.toString(chars));
        dfs(chars, 0, ans);
//        System.out.println(res);
        System.out.println(list.size());
        System.out.println(list);
        //[A,B,C,B]
        //1
    }

    private static void dfs(char[] chars, int start, int cnt) {
        if (start >= chars.length) {
            list.add(new ArrayList<>(path));
            return ;
        }

        for (int i = start; i < chars.length; i++) {
//            if (i > start && chars[i] == chars[i-1]) {
//                return ;
//            }
            if (path.contains(chars[i]) && getCount(path, chars[i]) >= cnt) {
                return ;
            }
            path.add(chars[i]);
            dfs(chars, i+1, cnt);
            path.remove(path.size()-1);
        }
    }

    private static int getCount(List<Character> path, char ch) {
        Map<Character, Integer> cntMap = new HashMap<>();
        for (char c : path) {
            cntMap.put(c, cntMap.getOrDefault(c, 0) + 1);
        }
        return cntMap.getOrDefault(ch, 0);
    }
}
