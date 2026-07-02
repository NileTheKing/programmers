import java.util.*;
class Solution {
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        int OFFSET = 10;
        temperature += OFFSET;
        t1 += OFFSET;
        t2 += OFFSET;
        int[][] dp = new int[onboard.length][51];
        int INF = Integer.MAX_VALUE / 2;
        for (int[] d : dp) Arrays.fill(d, INF);
        dp[0][temperature] = 0;
        // System.out.printf("dp[0][%d] = %d\n", temperature, dp[0][temperature]);
        for (int t = 1; t < onboard.length; t++) {
            // System.out.printf("===t:%d===\n", t);
            for (int j = 0; j < 51; j++) {
                //onboard인데 조건밖이 여기는 애초에 불가능하니까 뒤에도계산하면ㄷ안됨
                if (onboard[t] == 1 && (j < t1 || j > t2)) {
                    //System.out.printf("at %d skipping\n", j);
                    dp[t][j] = INF;
                    continue;
                }
                //System.out.printf("===temperature j = %d===\n", j);
                //이제 현재값을 이전상태에서 빌려와서 계산한다.
                //경우의수 1. 이전온도유지 2.온도 상승 3.온도하락
                //1-1 에어컨 켜서 유지
                //1-2 자연 유지(에어컨X)
                int sameOn = dp[t - 1][j] + b;
                int sameOff = j != temperature ? INF : dp[t-1][j];
                if (t == 1 && j == 38) {
                    // System.out.printf("tem: %d,,debug: %d %d\n", temperature, sameOn, sameOff);
                }
                int resSame = Math.min(sameOn, sameOff);
                //2-1 에어컨으로 온도 상승
                //2-2 자연상성
                int upOn = j == 0 ? INF : dp[t - 1][j - 1] + a;
                int upOff = j != 0 && j - 1 < temperature ? dp[t - 1][j - 1] : INF;
                int resUp = Math.min(upOn, upOff);
                
                //3-1 에어컨으로 온도하락
                //3-2 자연하락
                int downOn = j == 50 ? INF : dp[t - 1][j + 1] + a;
                int downOff = j != 50 && j + 1 > temperature ? dp[t - 1][j + 1] : INF;
                int resDown = Math.min(downOn, downOff);
                dp[t][j] = Math.min(resSame, Math.min(resUp, resDown));
                // System.out.printf("among %d %d %d\n", resSame, resDown, resUp);
                // System.out.printf("dp[%d][%d] = %d\n", t, j, dp[t][j]);
                System.out.println();
            }
        }
        // for (int[] d : dp) System.out.println(Arrays.toString(d));
        
        return Arrays.stream(dp[onboard.length - 1]).min().getAsInt();
    }
}
/**
시간축 행
온도축. 열
*/