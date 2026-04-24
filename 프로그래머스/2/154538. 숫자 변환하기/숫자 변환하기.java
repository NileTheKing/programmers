import java.util.*;
class Solution {
    public int solution(int x, int y, int n) {
        int[] dp = new int[y + 1]; //dp[3] -> 3만들떄 최소횟수.
        int INF = 1_000_0001;
        Arrays.fill(dp, INF);
        dp[x] = 0;
        for (int i = x + 1; i <= y; i++) {
            int opt1 = i - n >= 0 ? dp[i - n] + 1 : INF;
            int opt2 = i % 2 == 0 ? dp[i / 2] + 1 : INF;
            int opt3 = i % 3 == 0 ? dp[i / 3] + 1 : INF;
            int chosen = Math.min(opt1, opt2);
            chosen = Math.min(chosen, opt3);
            // System.out.printf("dp[%d](%d, %d, %d)\n", i, opt1, opt2, opt3);
            dp[i] = chosen;
        }
        return dp[y] >= INF ? -1 : dp[y];
    }
}