package AlgorithmNotes.hot100;

import org.junit.Test;


/**
 * 并查集: https://www.jianshu.com/p/ab5028f6917d
 */
public class numIslands200 {

    public int numIslands(char[][] grid) {
        // 1. dfs
//        int count = 0;
//        for (int i = 0; i < grid.length; i++) {
//            for (int j = 0; j < grid[0].length; j++) {
//                if (grid[i][j] == '1') {
//                    dfs(grid, i, j); // 作用就是把i,j的相邻的'1'变为0（已经计数为一个岛屿了）
//                    count++;
//                }
//            }
//        }
//        return count;


        // 2. bfs
//        if (grid == null || grid.length == 0) return  0;
//        int nr = grid.length;
//        int nc = grid[0].length;
//        int num_islands = 0;
//
//        for (int r = 0; r < nr; r++) {
//            for (int c = 0; c < nc; c++) {
//                if (grid[r][c] == '1') {
//                    ++num_islands;
//                    grid[r][c] = '0';
//                    Queue<Integer> neighbors = new LinkedList<>();
//                    neighbors.add(r * nc + c); // 例如坐标是:[3,4] -> 3 * grid.length + 4; 把坐标保存为一个数
//                    while (!neighbors.isEmpty()) {
//                        int id = neighbors.remove();
//                        int row = id / nc;
//                        int col = id % nc;
//                        // up
//                        if (row - 1 >= 0 && grid[row - 1][col] == '1') {
//                            neighbors.add((row - 1) * nc + col);
//                            grid[row - 1][col] = '0';
//                        }
//                        // down
//                        if (row + 1 < nr && grid[row + 1][col] == '1') {
//                            neighbors.add((row + 1) * nc + col);
//                            grid[row + 1][col] = '0';
//                        }
//                        // left
//                        if (col - 1 >= 0 && grid[row][col - 1] == '1') {
//                            neighbors.add(row * nc + col - 1);
//                            grid[row][col - 1] = '0';
//                        }
//                        // right
//                        if (col + 1 < nc && grid[row][col + 1] == '1') {
//                            neighbors.add(row * nc + col + 1);
//                            grid[row][col + 1] = '0';
//                        }
//                    }
//                }
//            }
//        }
//        return num_islands;

        // 3. 并查集(岛屿的数量就是并查集中连通分量的数目)
        if (grid == null || grid.length == 0) return 0;
        int nr = grid.length;
        int nc = grid[0].length;
        int num_islands = 0;
        UnionFind uf = new UnionFind(grid);
        for (int r = 0; r < nr; r++) {
            for (int c = 0; c < nc; c++) {
                if (grid[r][c] == '1') {
                    grid[r][c] = '0';
                    if (r-1 >= 0 && grid[r-1][c] == '1') {
                        uf.union(r * nc + c, (r - 1) * nc + c);
                    }
                    if (r+1 < nr && grid[r+1][c] == '1') {
                        uf.union(r * nc + c, (r + 1) * nc + c);
                    }
                    if (c-1 >= 0 && grid[r][c-1] == '1') {
                        uf.union(r * nc + c, r * nc + c - 1);
                    }
                    if (c+1 < nc && grid[r][c+1] == '1') {
                        uf.union(r * nc + c, r * nc + c + 1);
                    }
                }
            }
        }
        return uf.getCount();
    }

    class UnionFind {
        int count; // 当前的总数
        int[] parent; // 父节点
        int[] rank; // 该i为节点为根节点的节点数量

        public UnionFind(char[][] grid) {
            count = 0;
            int m = grid.length;
            int n = grid[0].length;
            parent = new int[m * n];
            rank = new int[m * n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == '1') {
                        // 表示该'1'有父集
                        // 设定自身的父集是自己
                        parent[i * n + j] = i * n + j; // nums[i] -> i
                        count++;
                    }
                    rank[i * n + j] = 0;
                }
            }
        }

        public  int find(int i ) {
            if (parent[i] != i) {
                parent[i] = find(parent[i]);
            }
            return parent[i];
        }

        // 链接
        public void union(int x,int y) {
            int rootx = find(x);
            int rooty = find(y);
            if (rootx != rooty) {
                if (rank[rootx] <= rank[rooty]) {
                    parent[rootx] = rooty;
                    rank[rooty] += rank[rootx];
                } else {
                    parent[rooty] = rootx;
                    rank[rootx] += rank[rooty];
                }
                // 此时可以合并视为一个集合，count是要减1
                count--;
            }
        }

        public int getCount() {
            return count;
        }
    }

    private void dfs(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0'; //将岛屿所有节点删除，以免之后重复搜索相同岛屿
        dfs(grid, i + 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
        dfs(grid, i - 1, j);
    }


    int[] parent = new int[4];
    @Test
    public void test() {
        int[] nums=  {1,4,2,3};
        for (int i = 0; i < nums.length; i++) {
            parent[nums[i]] = i;

        }
        for (int i = 0; i < nums.length; i++) {
            System.out.println(find(i));
        }
    }
    public  int find(int i ) {
        if (parent[i] != 0) {
            parent[i] = find(parent[i]);
        }
        return parent[i];
    }



}
