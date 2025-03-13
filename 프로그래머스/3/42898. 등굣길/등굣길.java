import java.util.*;
class Solution {
    public int solution(int m, int n, int[][] puddles) {
        
        int MOD = 1_000_000_007;
        //가로m 세로n
        int[][] memo = new int[n][m]; //집(1,1)이 배열에서는 [0,0]에 해당
        boolean[][] puddleMap = new boolean[n][m];
        
        for (int[] puddle : puddles) {
            puddleMap[puddle[1] - 1][puddle[0] - 1] = true;
        }
        //memo완성하면서 puddle만나면 0을 할당하고 패스하면 됨.
        //그렇게하면 puddle의 아래 및 우측 칸은 더해서 그대로하면 됨
        
        //1행 및 1열 초기화
        memo[0][0] = 1;
        // ㅡ
        for (int i = 1; i < m; i++) {
            //초기화하는데 포함하는 것은 0로 변경 << 필요한가? 어차피 나중에 지도완성하면서 처리하면 되지않나 ? 나중에 웅덩이 처리를... 있는지확인해서 상좌에 따라 다르게 하는 것 보다 그냥 0으로 초기화하면 편하겠다.
            if (puddleMap[0][i] == false) {
                memo[0][i] = 1;
            }
            else {
                break; //발견되면 그 이후로는 0으로 두기
            }
        }
        
        // ㅣ
        for (int i = 1; i < n; i++) {
            if (puddleMap[i][0] == false) {
                memo[i][0] = 1;
            }
            else {
                break;
            }
        }
        
        
        //나머지 memo 완성
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                //현재 작업 중인 memo칸이 웅덩이라면 0을 할당(기본값)하고 패스
                if (puddleMap[i][j]) continue;
                else {
                    memo[i][j] = (memo[i - 1][j]%MOD + memo[i][j - 1]%MOD) % MOD;
                }
            }
        }
        
        
        return memo[n-1][m-1];
    }
}