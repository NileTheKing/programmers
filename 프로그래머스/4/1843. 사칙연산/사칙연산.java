class Solution {
    public int solution(String arr[]) {
        
        int n = arr.length / 2 + 1; //피연산자 개수
        int[][] mindp = new int[n][n];
        int[][] maxdp = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                mindp[i][j] = Integer.MAX_VALUE;
                maxdp[i][j] = Integer.MIN_VALUE;
            }
        }
        
        for (int i = 0; i < n; i++) {
            maxdp[i][i] = Integer.parseInt(arr[i * 2]);
            mindp[i][i] = Integer.parseInt(arr[i * 2]);
        }
        
        //step:i와 j의 간격
        for (int step = 1; step < n; step++) {
            for (int i = 0; i < n - step; i++) {//i부터 j까지의 연산 수행
                int j = i + step;
                //k는 i부터 j까지 돌면서 연산자별로 dp저장
                for (int k = i; k < j; k++) {
                    if (arr[k * 2 + 1].equals("+")){
                        maxdp[i][j] = Math.max(maxdp[i][j], maxdp[i][k] + maxdp[k+1][j]);
                        mindp[i][j] = Math.min(mindp[i][j], mindp[i][k] + mindp[k+1][j]);
                        
                    }else {
                        maxdp[i][j] = Math.max(maxdp[i][j], maxdp[i][k] - mindp[k+1][j]);
                        mindp[i][j] = Math.min(mindp[i][j], mindp[i][k] - maxdp[k+1][j]);
                    
                    }
                }
            }
        }
        return maxdp[0][n-1];
    }
}
/**

[1] - [2~5]
[1~2] - [3~5]
[1~3]
생각 나는 방법: 그리디, 백트래킹, dp
    그리디: 수학적으로 최선의 선택을 매번 할 수 있다면?
        =>정렬을 이용해서 큰 값은 +에다가 붙이고 작은 값은 -에다가 붙이면??
            => 아님. 순서는 그대로고 결합만 시켜서 최대
    백트래킹: 모든 경우의 수 체크
    
    dp: 이전에 계산한 값을 사용
    
    
    1 - 3  + 5 - 8
    괄호는 2 * (4-1)인 6개가 되어야함.
    
    모르겠는디.... 흠 레벨4라.. . .. 3은 풀리니까 4건들여볼 수준일수도 이쏙
    흠 30분만 고민해보자
    
    
    풀이를 떠올리며 dp랑 엮어보자.
    우선 그떄 그사람은 배열memo같은 거 두개 만들어서 갱신하면서 한듯?
    
    
    테스트케이스 가지고 봐보자.,
    핵심은 +를 만날떄랑 -를 만날떄 두 상황에 대해 최댓값을 추적을 해야함
    +만날 떄 앞 뒤 놈 모두 최대여야함
    -만날때는 앞에는 최대 뒤에는 최소가 나와야함.
    이것을 이용해서 ..
    maxDP
    
*/