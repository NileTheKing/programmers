import java.util.*;
class Solution {
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        int OFFSET = 10;
        temperature += OFFSET;
        t1 += OFFSET;
        t2 += OFFSET;
        int[][] dp = new int[onboard.length][51];//[시간][온도]
        int INF = Integer.MAX_VALUE / 2;
        for (int[] d : dp) Arrays.fill(d, INF);
        dp[0][temperature] = 0;
        
        for (int t = 1; t < onboard.length; t++) {//time
            for (int c = 0; c <= 50; c++) {//celsius
                //사람타있는데 온도이상하면 컷
                if (onboard[t] == 1 && (c < t1 || c > t2)) continue;
                //온도 내려감
                    //자연 1
                    //에어컨 2
                //온도 유지
                    //자연 3
                    //유지 4
                //온도 올라감
                    //자연 5
                    //유지 6
                //1..c+1이 temp보다 높으면 추가로 c+1가인덱스
                int downOff = c + 1 > temperature && c + 1 <= 50 ? 
                    dp[t - 1][c + 1] : INF;
                int downOn = c + 1 <= 50 ? dp[t - 1][c + 1] + a : INF;
                int down = Math.min(downOff, downOn);
                
                //
                int sameOff = c == temperature ? dp[t - 1][c] : INF;
                int sameOn = dp[t - 1][c] + b;
                int same = Math.min(sameOff, sameOn);
                
                int upOff = c - 1 < temperature && c - 1 >= 0 ? 
                    dp[t - 1][c - 1] : INF;
                int upOn = c - 1 >= 0 ? dp[t - 1][c - 1] + a : INF;
                int up = Math.min(upOff, upOn);
                
                int res = Math.min(down, Math.min(same, up));
                dp[t][c] = res;
                
            }
        }
        return Arrays.stream(dp[dp.length - 1]).min().getAsInt();
    }
}