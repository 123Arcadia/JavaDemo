package AlgorithmNotes.hot100;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class canFinish207 {


    List<List<Integer>> edges;
    /**
     * 0: 「未搜索」：我们还没有搜索到这个节点；
     * 1: 「搜索中」：我们搜索过这个节点，但还没有回溯到该节点，即该节点还没有入栈，还有相邻的节点没有搜索完成）；
     * 2: 「已完成」：我们搜索过并且回溯过这个节点，即该节点已经入栈，并且所有该节点的相邻节点都出现在栈的更底部的位置，满足拓扑排序的要求
     */
    int[] visited;
    boolean valid = true;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        visited = new int[numCourses];
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
        }
        for (int i = 0; i < numCourses && valid; i++) {
            if (visited[i] == 0) { // 未搜索
                dfs(i);
            }
        }
        return valid;
    }

    private void dfs(int u) {
        visited[u] = 1; // 搜索中
        for (int v : edges.get(u)) {
            if (visited[v] == 0) {
                dfs(v);
                if (!valid) {
                    return;
                }
            } else if (visited[v] == 1) {
                // 如果遍历过: 表示有环
                valid = false;
                return;
            }
        }
        visited[u] = 2; // 搜索完
    }


    //-----------------------------------------------------------


    List<List<Integer>> edges2;
    int[] indge; // 索引: 其所有的邻接点的值 val: 入度

    /**
     * 我们将 u 放入答案中；
     * 我们移除 u 的所有出边，也就是将 u 的所有相邻节点的入度减少 1。如果某个相邻节点 v 的入度变为 0，那么我们就将 v 放入队列中。
     */

    public boolean canFinish_bfs(int numCourses, int[][] prerequisites) {
        edges2 = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            edges2.add(new ArrayList<>());
        }
        indge = new int[numCourses];
        for (int[] info : prerequisites) {
            edges2.get(info[1]).add(info[0]);
            indge[info[0]]++;
        }

        Queue<Integer> queue=  new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indge[i] == 0) { // 入度=0
                queue.offer(i);
            }
        }
        //
        int visited = 0;
        while (!queue.isEmpty()) {
            ++visited;
            int u = queue.poll();
            for(int v : edges2.get(u)) {
                indge[v]--; // 入度-1
                if (indge[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        // 没有环下的节点数量
        return visited == numCourses;
    }
}
