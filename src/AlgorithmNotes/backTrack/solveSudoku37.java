package AlgorithmNotes.backTrack;

import org.junit.Test;

import java.net.Inet4Address;
import java.net.InetAddress;

public class solveSudoku37 {
    /**
     * 37. 解数独
     * 数字 1-9 在每一行只能出现一次。
     * 数字 1-9 在每一列只能出现一次。
     * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
     *
     * board.length == 9
     * board[i].length == 9
     * board[i][j] 是一位数字或者 '.'
     */

    public void solveSudoku(char[][] board) {
        dfs(board);
    }

    private boolean dfs(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    continue;
                }
                for(char k = '1'; k <= '9'; k++) {
                    // 该处可以太填写数字，先判断isValid
                    if (isVaild(i, j, k, board)) {
                        board[i][j] = k;
                        if (dfs(board)) {
                            return true;
                        }
                        board[i][j] = '.';
                    }
                }
                return false;
            }
        }
        // 最后所有空格都填写了数字，会一直continue，return true
        return true;
    }

    private boolean isVaild(int i, int j, char k, char[][] board) {
        // 行
        for (int l = 0; l < 9; l++) {
            if (board[i][l] == k) {
                return false;
            }
        }
        // 列
        for (int l = 0; l < 9; l++) {
            if (board[l][j] == k) {
                return false;
            }
        }
        // 3x3
        int row = i / 3 * 3; // 9方格的起始
        int col = j / 3 * 3;
        for (int l = row; l < row + 3; l++) {
            for (int m = col; m < col + 3; m++) {
                if (board[l][m] == k) {
                    return false;
                }
            }
        }
        return true;
    }



    @Test
    public void test () {
        try {
            // 获取本地主机名对应的IP地址列表
            InetAddress localhost = InetAddress.getLocalHost();

            // 检查是否为IPv4地址
            if (localhost instanceof Inet4Address) {
                System.out.println("本机IPv4地址: " + localhost.getHostAddress());
            } else {
                System.out.println("当前获取到的不是IPv4地址");
            }
        } catch (Exception e) {
            System.err.println("无法获取本机IP地址: " + e.getMessage());
        }
    }
}
