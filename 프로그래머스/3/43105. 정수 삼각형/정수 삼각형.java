import java.util.*;
class Solution {
    public int solution(int[][] triangle) {
        
        
        
      
        //1열에 해당하는 부분부터 완성
        for (int i = 1; i < triangle.length; i++) {
            for (int j = 0; j < triangle[i].length;j++) {
                //첫번째면 경계쳐리
                if (j == 0) {
                    triangle[i][j] = triangle[i-1][j] + triangle[i][j];
                }//마지막이면 또 경계처리
                else if(j == triangle[i].length - 1){
                    triangle[i][j] = triangle[i - 1][j - 1] + triangle[i][j];
                }
                else {
                    triangle[i][j] = Math.max(triangle[i-1][j-1], triangle[i-1][j]) + triangle[i][j];
                }
            }
        }
        

        
        return Arrays.stream(triangle[triangle.length - 1]).max().getAsInt();
    }
}
/**
트리?
음 백트래킹으로 할 수 있을 것 같은데..
그것도 그거고 dp이용해가지고 할 수 있을듯? 왜냐하면 유지되기 때문이라
그렇다면 특정 위치에서 최댓값으로 이동할 수 있는 방법은 단 하나일테니까 그거를 저장해가지고 탐색할 수 있을듯?

triangle이거 만든 다음에 그  뭐냐 가로세로 경우의수 하는 것 처럼 비교해서 더 큰 방법으로 계속해가지고 
완성된 배열의 마지막 행 순회해서 최댓값 추적해도 될듯?\\

dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j])

*/