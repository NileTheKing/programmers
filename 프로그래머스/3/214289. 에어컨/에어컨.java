import java.util.*;
class Solution {
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        int OFFSET = 10;
        int INF = 1000001;
        temperature += OFFSET;
        t1 += OFFSET;
        t2 += OFFSET;
        int time = onboard.length;
        int[][] dp = new int[time + 1][51]; //이래야이제 7분에값을알수있다.
        //아 일단dp[2]는 2분일때여야해..
        //dp초기값은 무조건 0이지.
        for(int[] d : dp) Arrays.fill(d, INF);
        dp[0][temperature] = 0;//나머지 시간축은? 못써먹음.
        //이제 1분부터 모든 경우의수 다 하는거임
        //지금 고려안한게 사람이 탔을떄 t1 t2이거 지켜야해. 다해놨는데 지금시점에 온도가 안되는건 넘어가야함
        for (int i = 1; i < time; i++) {//가로축 시간. 1분부터 7분까지
            for (int j = 0; j <= 50; j++) {//온도축. 인덱스는 0부터50인데 실제읽을떈
                if (onboard[i] == 1 && (j < t1 || j > t2)) continue;
                //-10부터 40도로 읽는거임. 마지막에.
                //이제 각 dp[i][j]를 이전상태가지고 계싼
                
                //온도가 내려옴
                int case1 = j + 1 <= 50 && j + 1 > temperature ? dp[i - 1][j + 1] : INF;
                int case2 = j + 1 <= 50 ? dp[i - 1][j + 1] + a : INF;
                //경우의수: 온도가 올라갔다
                    //3.에어컨 켜서 올라갔다(희망온도)
                int case3 = j - 1 >= 0 ? dp[i -1][j - 1] + a : INF;
                    //4.자동으로 올라갔다(외부기온땜시)
                int case4 = j - 1 >= 0 && j - 1 < temperature ? dp[i - 1][j - 1] :INF;
                //경우의수: 온도가 유지되었따
                    //5: 켰는데실내온도 = 희망온도라서
                int case5 = dp[i - 1][j] + b;
                    //6: 껐는데 실내=실외라서
                int case6 = j == temperature ? dp[i - 1][j] : INF;
                
                int min1 = Math.min(case1, case2);
                int min2 = Math.min(case3, case4);
                int min3 = Math.min(case5, case6);
                
                dp[i][j] = Math.min(min1, Math.min(min2, min3));
            }
        }
        return Arrays.stream(dp[time - 1]).min().getAsInt();
    }
}
/**
잠만 그 행이 시간이고..열이 온도잖아

*/