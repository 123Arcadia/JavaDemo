package AlgorithmNotes.hot100;

public class minDistance {


    public int minDistance(String word1, String word2) {
        // D[i][j] 表示 A 的前 i 个字母和 B 的前 j 个字母之间的编辑距离。
        int n1 = word1.length();
        int n2 = word2.length();
        int[][] dp = new int[n1+1][n2+1];
        // 当word1.len==0
        for (int i = 1; i <= n2; i++) {
            dp[0][i] = dp[0][i-1] + 1;
        }
        for (int i = 1; i <= n1; i++) {
            dp[i][0] = dp[i-1][0] + 1;
        }
        for(int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i-1][j-1], dp[i][j-1]))+1;
                }
            }
        }
        return dp[n1][n2];

    }
}
