import java.util.*;
class Solution
{
    public int solution(int [][]board)
    {
        int max = 0;
        int m = board.length;
        int n = board[0].length;
        int[][] dp = new int[m][n];
        //dp초기화
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 0) continue;
                dp[i][j] = board[i][j];
                max = board[i][j];
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                //1채워져있어야 일단 시작. 만약 1이아니면? 거기선 걍 0임
                if (board[i][j] == 0) continue;
                int min = Math.min(dp[i][j-1], dp[i-1][j]);
                min = Math.min(min, dp[i-1][j-1]);
                dp[i][j] = min + 1;
                max = Math.max(dp[i][j], max);
            }
        }
        return max * max;
    }
}