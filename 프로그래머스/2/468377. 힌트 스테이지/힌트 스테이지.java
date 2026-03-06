import java.util.*;
class Solution {
    int[] hintInfo;
    int[][] cost;
    int[][] hint;
    long min = Long.MAX_VALUE;
    int n;
    public int solution(int[][] cost, int[][] hint) {
        n = cost.length; //스테이지 수
        hintInfo = new int[n + 1]; //hintInfo[i] : i스테이지의 힌트사용가능 갯수.
        this.hint =  hint;
        this.cost = cost;

        
        backtrack(0, 0);
        
        return (int)min;
    }
    public void backtrack(int idx, long currentPrice) {
        
        if (idx == n) {
            min = Math.min(currentPrice, min);
            return;
        }
        
        //현재 힌트 사용 가능 갯수찾기
        int ableHint = Math.min(n - 1, hintInfo[idx+1]);
        //비용계싼
        long priceToSolve = cost[idx][ableHint];
        
        
        //마지막 인덱스
        if (idx == n -1) {
            //마지막 인덱스면 힌트사용없이 그냥 가격더하기
            backtrack(idx + 1, currentPrice + priceToSolve);
        }else {
            //번들미구매
            backtrack(idx + 1, currentPrice + priceToSolve);
            

            //번들 구매
            for (int i = 1; i < hint[idx].length; i++) {
                int targetIdx = hint[idx][i];//힌트 저금할 인덱스 모두
                hintInfo[targetIdx]++;
            }
            backtrack(idx + 1, currentPrice + priceToSolve + hint[idx][0]);
            //백트래킹    
            for (int i = 1; i < hint[idx].length; i++) {
                int targetIdx = hint[idx][i];//힌트 저금할 인덱스 모두
                hintInfo[targetIdx]--;
            }
        }
        
    }
}
/**
완탐?
dp?
cost길이 16 즉 스테이지 최대 16개
각 스테이지 에서는 최대 15개까지 사용가능
그러면 이제 각 경우마다 15가지 경우의수 ..최악으로? 15^16승 아님?
근데 이거는 부분최적값이 있을거같은디. 그러면 완탐말고 , 그리디도 아닌ㅁ
그러면 dp네
그러면 축을 어떻게 놓고 상태 관리를 해야하나?
일단 스테이지가 한 축이고.
해당 좌표의 값은 가격일테고.
그러면 나머지 축은 힌트지? 힌트 사용여부에 다른..
아 그러면!! 힌트사용여부를 하면되..나 흠
근데 똑같은거 두개있을 수 도 있잖아. 3번 스테이지에 대한 힌트두개 흠
그러면 힌트사용 정보는 뭐여 [스테이지][힌트번호][남은갯수?사용갯수] = 최솟값
[스테이지][]
큐 써가지고 하는 bfs는 아닌가.
[칸][해당칸 힌트사용권] = 비용?
그럼 [스테이지1][0][0] = 160이고.. 스테이지1은 힌트사용못하니까 어차파ㅣ
그럼 [스테이지2][]
[스테이지][사용갯수][구매여부] 근데 샀는지 안샀는지 어떻게알아? 이거를 어디에 보관해야하지? 
그리고 설ㄹㅇ 보관한다고 한들 이거 사용했는지 안했는지 어떻게 알지?
그러면 백트래킹이랑 dp를 해가지고 하는건가?
어 그러면 번들번호를 가지고 있다면 차원 하나에 사용여부를 담을 수 있긴하네?
그렇다면 매번 이거 정보를 가져와가지고 확인 할 수 도 있고
*/