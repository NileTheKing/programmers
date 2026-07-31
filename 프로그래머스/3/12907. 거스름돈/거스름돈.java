class Solution {
    public int solution(int n, int[] money) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        int MOD = 1_000_000_007;
        
        for (int m : money) {//세로축(행))
            for (int i = m; i <= n; i++) { //가로축(열)
                dp[i] = dp[i] + dp[i - m]; //두번째항이 열을보는것 첫번째항은 이전 열(이전 경우의수 누적)
            }
        }
        return dp[n];
    }
}