import java.util.*;
class Solution {
    int[][] cost;
    int[][] hint;
    int n;
    int[] hintInfo;
    long min = Long.MAX_VALUE;
    public int solution(int[][] cost, int[][] hint) {
        this.cost = cost;
        this.hint = hint;
        n = cost.length;
        hintInfo = new int[n+1];
        
        backtrack(0, 0);
        return (int)min;
    }
    public void backtrack(int idx, long currentPrice) {
        if (idx == n) { //끝날조건
            min = Math.min(currentPrice, min);
            return;
        }
        
        //이번에 힌트 몇개 쓸거?
        int ableHints = Math.min(n-1,hintInfo[idx]);
        //이번에 힌트 사용한다면 드는 금액
        int priceToSolve = cost[idx][ableHints];
        
        if (idx == n - 1) { //마지막 스테이지면 안삼
            backtrack(idx + 1, currentPrice + priceToSolve);
        }else {
            
            //안슴
            backtrack(idx + 1, currentPrice + priceToSolve);
            //힌트번들 삼
            for (int i = 1; i < hint[idx].length; i++) {
                int targetIdx = hint[idx][i] - 1;
                hintInfo[targetIdx]++;
            }
            backtrack(idx + 1, currentPrice + priceToSolve + hint[idx][0]);
            //백트래킹
            for (int i = 1; i < hint[idx].length; i++) {
                int targetIdx = hint[idx][i] - 1;
                hintInfo[targetIdx]--;
            }
        }
    }
}