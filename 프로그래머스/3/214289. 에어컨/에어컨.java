import java.util.*;
class Solution {
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        int offset = 10;
        temperature += offset;
        t1 += offset;
        t2 += offset;
        int INF = 1000000;
        int[][] dp = new int[onboard.length][51];
        for (int i = 0; i < onboard.length; i++) {
            Arrays.fill(dp[i], INF);
        }
        
        dp[0][temperature] = 0;
        
        for (int i = 1; i < onboard.length; i++) {
            for (int j = 0; j <= 50; j++) {
                if (onboard[i] == 1 && (j < t1 || j > t2)) continue; //guard clause!
                
                int stayOff = j == temperature ? dp[i - 1][j] : INF;
                int stayOn = dp[i - 1][j] + b;
                int stayFinal = Math.min(stayOff, stayOn);
                
                int cameDown1 = INF; //내려온건데..자연강하랑 돈내고 강하
                int cameDown2 = INF;
                if (j < 50) {
                    cameDown1 = j + 1 > temperature ? dp[i - 1][j + 1] : INF;
                    cameDown2 = dp[i - 1][j + 1] + a;
                }
                int cameDownFinal = Math.min(cameDown1, cameDown2);
                
                int cameUp1 = INF;
                int cameUp2 = INF;
                if (j > 0) {
                    cameUp1 = j - 1 < temperature ? dp[i - 1][j - 1] : INF;//자연상
                    cameUp2 = dp[i - 1][j - 1] + a;//도내서
                }
                int cameUpFinal = Math.min(cameUp1, cameUp2);
                
                dp[i][j] = Math.min(stayFinal, Math.min(cameUpFinal, cameDownFinal));
            }
        }
        
        return Arrays.stream(dp[onboard.length - 1]).min().getAsInt();
    }
}