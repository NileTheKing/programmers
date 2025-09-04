class Solution {
    public int solution(int n) {
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1; //dp[1] 은 1번쨰. dp[n]이 n번째.
        for (int i = 2; i <= n; i++) {
            dp[i] = (dp[i-1] % 1234567 + dp[i-2] % 1234567)%1234567;
            //System.out.printf("dp[%d]: %d\n", i, dp[i]);
        }
        return dp[n] ;
    }
}
/**
f(0) = 0
f(1) = 1
f(2) = 1
f(3) = 2
f(4)= 3
f(5) = 5
..

 2 <= n <= 100000
 n =3 -> 3번째 피보나치수
 n =5라면 5
 나머지를 해야하나 그냥 리턴하면 안됨?아..근데 계산은 해야겠네.

*/