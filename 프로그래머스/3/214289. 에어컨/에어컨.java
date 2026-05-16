import java.util.*;
class Solution {
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        int OFFSET = 10;
        int minuteLength = onboard.length;
        int[][] dp = new int[onboard.length][51];// 시간축 온도축. 
        //그냥 깔끔하게 머리아프니까 다 추가해버리는거임.. -10이면 0이라읽는다
        temperature += OFFSET;
        t1 += OFFSET;
        t2 += OFFSET;
        int INF = Integer.MAX_VALUE / 2;//오버플로우방지
        for (int[] d : dp) Arrays.fill(d, INF);
        dp[0][temperature] = 0;//0일떄 나머진 불가
        for (int i = 1; i < dp.length; i++) {//시간축
            System.out.println();
            for (int j = 0; j <= 50; j++) {//온도축
                // 이건 계산할필요가없음 애초에불가능.사람타있는데 이온도를 왜 계산해.
                if (onboard[i] == 1 && (j < t1 || j > t2)) continue;
                //경우의수 고려해서 제일 작은값.(전시간대비 온도가)
                //유지
                int stay = INF;
                    //실내==실외 (자연)
                int stayNatural = j == temperature ? dp[i - 1][j] : INF;
                    //희망온도==실내온도 (에어컨)
                int stayAC = dp[i - 1][j] + b; //유지비용만
                stay = Math.min(stayNatural, stayAC);
                //상승
                int up = INF;
                    //자연상승 실내 < 실외..그니까 j - 1에서 temp
                int upNatural = j - 1 >= 0 && j - 1 < temperature ? dp[i - 1][j - 1] : INF;
                    //에어컨으로 상승..
                int upAC = j - 1 >= 0 ? dp[i - 1][j - 1] + a : INF;
                up = Math.min(upNatural, upAC);
                //하락
                int down = INF;
                    //자연하락 실내 > 실외
                int downNatural = j + 1 <= 50 && j + 1 > temperature ? dp[i - 1][j + 1] : INF;
                    //에어컨으로 하락
                int downAC = j + 1 <= 50 ? dp[i - 1][j  + 1] + a : INF;
                down = Math.min(downNatural, downAC);
                int res = Math.min(Math.min(stay,up), down);
                dp[i][j] = res;
                // System.out.printf("(%d, %d):  %d\n",i,j,dp[i][j]);
            }
        }
        // for (int[] d : dp) System.out.println(Arrays.toString(d));
        return Arrays.stream(dp[minuteLength - 1]).min().getAsInt();
    }
}
/**
에어컨을 희망온도로 킨다. 다르다면 희망온도쪽으로 이동
만약 희망온도가 실내온도랑 같다면 안변하지 당연

만약 에어컨을 끄면 실내가 실외쪽으로 이동.. 만약 실내가 실외랑ㄱ 같다면 변하진않느다

소비전력.. 켰을떄지. 희망!=실내 -> a, 희망 == 실내 -> b만큼소비.온도변화X
*/