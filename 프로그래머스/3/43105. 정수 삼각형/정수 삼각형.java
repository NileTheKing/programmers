import java.util.*;
class Solution {
    public int solution(int[][] triangle) {
        int[][] dp = new int[triangle.length][triangle[triangle.length - 1].length];
        
        for (int[] d: dp) Arrays.fill(d, Integer.MIN_VALUE);
        dp[0][0] = triangle[0][0];
        int m = dp.length;
        int n = dp[0].length;
        for (int i = 1; i < m; i++) {
            for (int j = 0; j <= i; j++) {
                int val = triangle[i][j];
                int lookLeft = j == 0 ? 0 : j - 1;
                int lookRight = j == n - 1 ? n - 1 : j;
                //triangle[i][j]는 안전..핳ㅁ
                dp[i][j] = Math.max(dp[i - 1][lookLeft] + val, dp[i - 1][lookRight] + val);
            }
        }
        for (int i = 0; i < m; i++) {
            // System.out.println(Arrays.toString(dp[i]));
        }
        return Arrays.stream(dp[m - 1]).max().getAsInt();
    }
}