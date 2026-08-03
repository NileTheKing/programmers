class Solution {
    public int solution(int n, int[] money) {
        
        int MOD = 1_000_000_007;
        int[] dp = new int[n + 1];
        dp[0] = 1;        
        for (int m :  money) {
            for (int i = m; i <= n; i++) {
                // if (i - m <= 0) continue;
                dp[i] += dp[i - m];
            }
        }
        return dp[n];
    }
}