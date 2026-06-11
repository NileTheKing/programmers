class Solution {
    public long solution(int[] sequence) {
        //짝수면 1곱(i % 2 == 0) 홀수면 -1곱
        int len = sequence.length;
        int[] startWithPos = new int[len];
        int[] startWithNeg = new int[len];
        for (int i = 0; i < len; i++) {
            int multiplier = i % 2 == 0 ? 1 : -1;
            startWithPos[i] = sequence[i] * multiplier;
            startWithNeg[i] = sequence[i] * multiplier * -1;
        }
        long[] dp1 = new long[len + 1]; //dp[i] : sequence[i - 1]까지 했을떄최대
        long[] dp2 = new long[len + 1];
        dp1[0] = 0;
        dp2[0] = 0;
        long max = 0;
        for (int i = 1; i <= len; i++) {
            dp1[i] = Math.max(dp1[i - 1] + startWithPos[i - 1], startWithPos[i - 1]);
            dp2[i] = Math.max(dp2[i - 1] + startWithNeg[i - 1], startWithNeg[i - 1]);
            max = Math.max(Math.max(max, dp1[i]), dp2[i]);
        }
        return max;
        
        
    }
}
