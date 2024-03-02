package AlgorithmNotes.backTrack;

import java.util.*;

public class findItinerary332 {
    /**
     * 332. 重新安排行程
     */

    /**
     * 该解法最后一个用例失败
     */
    LinkedList<String> res ;
    LinkedList<String> path = new LinkedList<>();
    public List<String> findItinerary(List<List<String>> tickets) {
        // 去重
        Collections.sort(tickets, (a, b) -> a.get(1).compareTo(b.get(1)));
        path.add("JFK");
        boolean[] used = new boolean[tickets.size()];
        //  不是tickets.size() + 1? 因为"JFK"不用判断
        dfs(tickets, used);
        return res;
    }



    private boolean dfs(List<List<String>> tickets, boolean[] used) {
        if (path.size() == tickets.size() + 1) {
            res = new LinkedList<>(path);
            return true;
        }
        for (int i = 0; i < tickets.size(); i++) {
            if (!used[i] && tickets.get(i).get(0).equals(path.getLast())) {
                used[i] = true;
                path.add(tickets.get(i).get(1));
                if (dfs(tickets, used)) { // 一旦找到了，就无序在找了，已经排序过，所以找到第一个path是最近的路径
                    return true;
                }
                path.removeLast();
                used[i] = false;
            }
        }
        return false;
    }

    Map<String, PriorityQueue<String>> map = new HashMap<>();
    List<String> ans = new LinkedList<>();
    public List<String> solution_2(List<List<String>> tickets) {
        for (List<String> ticket : tickets) {
            String src = ticket.get(0), dst = ticket.get(1);
            if (!map.containsKey(src)) {
                map.put(src, new PriorityQueue<String>());
            }
            map.get(src).offer(dst);
        }
        dfs1("JFK");
        System.out.println("ans = " + ans);
        Collections.reverse(ans);
        return ans;
    }

    private void dfs1(String cur) {
        while (map.containsKey(cur) && map.get(cur).size() > 0) {
            String poll = map.get(cur).poll();
            dfs1(poll);
        }
    }
}
