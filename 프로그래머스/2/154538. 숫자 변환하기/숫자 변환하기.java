class Solution {
    public int solution(int x, int y, int n) {
        int[] dp = new int[y+1]; //dp[y]: y를 만들 수 있는 최소횟수
        dp[0] = 0;
        dp[x] = 0;
        int INF = 10000001;
        for (int i = 0; i < x - 1; i++) {
            dp[i] = INF;
        }
        for (int i = x + 1; i <= y; i++) {
            //n뺀거
            // i/2에서 2곱한거
            // i/3에서 3곱한거
            int opt1 = i - n >= 0 && i - n >= x ? dp[i - n] + 1 : INF;
            int opt2 = i % 2 == 0 && i / 2 >= x ? dp[i / 2] + 1 : INF;
            int opt3 = i % 3 == 0 && i /3 >= x ? dp[i / 3] + 1 : INF;
            int selected = Math.min(opt1, Math.min(opt2, opt3));
            if (selected == INF) dp[i] = INF;
            else dp[i] = selected;
            
            //System.out.printf("dp[%d] = %d\n", i, dp[i]);
        }
        
        return dp[y] == INF ? -1 : dp[y]; 
    }
}
/**
매번 옵션이 3개있음
최소횟수..1.완탐 2.그리디 3.dp
그리디는 절대 될수가 없지 ㅋㅋ 부분최적해 보장이안됨
그렇다면 완탐 dp임
완틈되나? 3^n번인데 흠... 어려울거같은뎀
그렇다면 dp?<<이거네
*/