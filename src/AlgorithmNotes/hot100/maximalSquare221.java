package AlgorithmNotes.hot100;

public class maximalSquare221 {


    public int maximalSquare(char[][] matrix) {
        // dp[i][j] 表示以i,j为右下角的最大正方形边长
        int maxSide = 0; // 最长边长
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return maxSide;
        }
            int rows = matrix.length, cols = matrix[0].length;
        int[][] dp = new int[rows][cols];
        for ( int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 ||j == 0) {
                        dp[i][j] = 0;
                    } else {
                        // 如果三者有一个是0,则dp[i][j]就是1
                        dp[i][j] = Math.min(dp[i-1][j-1],Math.min(dp[i-1][j], dp[i][j-1])) + 1;
                    }
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }
        int maxSquare = maxSide * maxSide;
        return  maxSquare;
    }



}
