import java.util.*;
class Solution {
    int solution(int[][] land) {
        int M = land.length;
        int N = 4;//열
        int[][] dp = new int[M][N]; //dp[n][2] : n행에서 2열(3번째)갔을때 최대값.
    
        for (int[] d : dp) Arrays.fill(d, Integer.MIN_VALUE);
        for (int i = 0; i < N; i++) dp[0][i] = land[0][i];//초기값
        
        for (int i = 1; i < M; i++) {//행인데 1index임. land에서읽을때0index
            
            for (int j = 0; j < N; j++) { //열
                //j빼고 나머지 dp에서 구하기
                int max = Integer.MIN_VALUE;
                for (int k = 0; k < N; k++) {//열구하기위함
                    if (k == j) continue; //이전DP에서읽을건데 열같으면안됨
                    max = Math.max(max, dp[i - 1][k]);
                }
                dp[i][j] = max + land[i][j];
            }
        }
        // for(int[] d : dp) System.out.println(Arrays.toString(d));

        return Arrays.stream(dp[M - 1]).max().getAsInt();
    }
}
/**
N 10^5.. 백트래킹 생각했는데 안됨 시간초과임.
4 ^ (10^5) 당연히 터짐.
그러면 음.. 그리디나 ..그리디는 백ㅍ아님 부분해가 최적해절대안됨.
고ㅗㄹ 가지치기를 하는것이..
최소 O(N)인데.. 음..2차원dp로 한다면 어 쉽네..
어떻게 가지치기도 아닌 dp로 쉽게하는것일까 보니까..음.. 순차진행에
매턴 옵션이있는데.. 흠 약간 백트래킹이랑 좀 명확히 구분된다하는 단서는 없는데 직관이 그래.
매번 모든 가능성을 확인할필요없이 전턴이랑 비교해야하니까..
백트래킹보다 그냥 dp인 느낌인가? 흠 나만의 언어로 정리가 안디네.
*/