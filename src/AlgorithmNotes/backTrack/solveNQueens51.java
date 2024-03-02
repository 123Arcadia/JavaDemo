package AlgorithmNotes.backTrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class solveNQueens51 {
    /**
     * 51. N 皇后
     * 1 <= n <= 9
     */
    List<List<String>> res = new ArrayList<>();


    public List<List<String>> solveNQueens(int n) {
        char[][] chessboard = new char[n][n];
        for (int i = 0; i < chessboard.length; i++) {
            Arrays.fill(chessboard[i], '.');
        }
        backTrack(chessboard, 0, n);
        return res;
    }

    private void backTrack(char[][] chessboard, int row, int n) {
        if (row == n) {
            res.add(charArr2List(chessboard));
            return ;
        }
        for (int col = 0; col < n; col++) {
            // row,col是否合法
            if (isValid(chessboard, row, col, n)) {
                chessboard[row][col] = 'Q';
                backTrack(chessboard, row + 1, n);
                chessboard[row][col] = '.';
            }
        }
    }

    private boolean isValid(char[][] chessboard, int row, int col, int n) {
        // 必须不同列
        for (int i = 0; i < row; i++) {
            if (chessboard[i][col] == 'Q') {
                return false;
            }
        }
        // 不同行：由回溯原因已经限制
        // 不同135°斜线
        for (int i = row-1, j = col-1; i >= 0 && j >= 0; i--, j--) {
            if (chessboard[i][j] == 'Q') {
                return false;
            }
        }
        // 不同-135°斜线
        for (int i = row-1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (chessboard[i][j] == 'Q') {
                return false;
            }
        }
        return true;

    }

    private List<String> charArr2List(char[][] chars) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            res.add(String.copyValueOf(chars[i]));
        }
        return res;
    }
}
