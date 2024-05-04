package AlgorithmNotes.hot100.isLands;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class isLands {
    /**
     * L200. 岛屿数量 （Easy）
     * 463. 岛屿的周长 （Easy）
     * 695. 岛屿的最大面积 （Medium）
     * 827. 最大人工岛 （Hard）
     */
//    int sum = 0;
//    int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
//
//    public int islandPerimeter(int[][] grid) {
//        // 只要计算和水相邻的各自就好
//        int m = grid.length, n = grid[0].length;
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                if (grid[i][j] == 1) {
//                    sum += isLand(grid, i, j);
//                }
//
//            }
//        }
//        return sum;
//    }
//
//    private int isLand(int[][] grid, int i, int j) {
//        int sum = 0;
//        for (int k = 0; k < dir.length; k++) {
//            int x = dir[k][0] + i;
//            int y = dir[k][1] + j;
//            if (x <= 0 || x >= grid.length || y <= 0 || y >= grid[0].length || grid[x][y] == 0) {
//                sum++; // 相邻水的有几面
//            }
//        }
//        return sum;
//    }

    //----------------------------------------------------------------------------------------------------------------

//    int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

//    public int maxAreaOfIsland(int[][] grid) {
//        int maxArea = 0;
//        int m = grid.length, n = grid[0].length;
//        boolean[][] vis = new boolean[m][n];
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                if (grid[i][j] == 1 && !vis[i][j]) {
//                    // 或者搜索过的置0
//                    maxArea = Math.max(maxArea, dfs(grid, i, j, vis));
//                }
//            }
//        }
//        return maxArea;
//
//    }
//
//    private int dfs(int[][] grid, int i, int j, boolean[][] vis) {
//        vis[i][j] = true;
//        int area = 1;
//        for (int k = 0; k < dir.length; k++) {
//            int x = dir[k][0] + i, y = dir[k][1] + j;
//            if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 1 && !vis[x][y]) {
//                vis[x][y] = true;
//                area += dfs(grid, x, y, vis);
//            }
//        }
//        return area;
//    }

//--------------------------------------------------------------------------------------------------------------------

    int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int largestIsland(int[][] grid) {
        int maxArea = 0;
        int m = grid.length, n = grid[0].length;
        Map<Integer, Integer> area = new HashMap<>();
        int[][] tag = new int[m][n];
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 把每个grid[][] = 0变成1在计算area
                if (grid[i][j] == 1 && tag[i][j] == 0) {
                    int t = i * n + j + 1; // 避免0
                    area.put(t, dfs(grid, i, j, tag, t));
                    res = Math.max(res, area.get(t));
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    int z = 1;
                    Set<Integer> conn = new HashSet<>();
                    for (int k = 0; k < 4; k++) {
                        int x = i + dir[k][0], y = j + dir[k][1];
                        // tag == 0 说明原本就不是岛屿
                        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || tag[x][y] == 0 || conn.contains(tag[x][y])) {
                            continue;
                        }

                        z += area.get(tag[x][y]);
                        conn.add(tag[x][y]);

                    }
                    res = Math.max(res, z);
                }
            }
        }
        return res;
    }

    private int dfs(int[][] grid, int i, int j, int[][] tag, int t) {
        int area = 1;
        tag[i][j] = t;
        for (int k = 0; k < dir.length; k++) {
            int x = dir[k][0] + i, y = dir[k][1] + j;
            if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 1
                    && tag[x][y] == 0) {
                area += dfs(grid, x, y, tag, t);
            }
        }
        return area;
    }
}
