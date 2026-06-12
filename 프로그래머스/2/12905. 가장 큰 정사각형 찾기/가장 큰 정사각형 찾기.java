import java.util.*;
class Solution {
    public int solution(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        int[][] dp = new int[m][n];
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = board[i][j];
                max = Math.max(max, dp[i][j]);
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (board[i][j] == 0) continue;
                dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
                max = Math.max(max, dp[i][j] * dp[i][j]);
            }
        }
        // for (int[] d : dp) System.out.println(Arrays.toString(d));
        return max;
    }
}