import java.util.*;
class Solution {
    public int solution(int n) {
        int[] dp = new int[n + 1]; // dp[i] :길이 i인 사각형 채울 수 있는 방법
        int mod = 1_000_000_007;
        dp[0] = 1; //일단..
        dp[1] = 1;
        
        for (int i = 2; i <= n; i++) {
            dp[i] = (dp[i - 2] % mod + dp[i - 1] % mod) % mod;
        }
        
        
        return dp[n] % mod;
    }
}
/**
dp
세로는 항상2..



*/