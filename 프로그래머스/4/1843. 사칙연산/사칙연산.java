class Solution {
    public int solution(String arr[]) {
        int n = arr.length / 2 + 1; //피연산자 갯수
        int[][] min = new int[n][n]; //min[i][j] 라면 i부터j 까지 연산한 것 중 최소값
        int[][] max = new int[n][n];
        
        //초기화
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                min[i][j] = Integer.MAX_VALUE;
                max[i][j] = Integer.MIN_VALUE;
            }
        }
        for (int i = 0; i < n; i++) {
            min[i][i] = Integer.parseInt(arr[i * 2]);
            max[i][i] = Integer.parseInt(arr[i * 2]);
        }
        
        //순회하며 각 상태에서의 memoization 완성
        //step을 우선 고려. i와 j사이의 간격. 연산해야하니까 1이상 step - n이하
        for (int step = 1; step < n; step++) {
            //i 차례
            for (int i = 0; i < n - step; i++) {
                int j = i + step; //j의 위치
                
                //이제 i와 j의 위치가 정해졌기 때문에 i와 j를 둘로 나누는 경우의 수를 루프로
                for (int k = i; k < j; k++) { //i와 j의 번째(인덱스)
                    String op = arr[k * 2 + 1];
                    if (op.equals("+")) {
                        //최대 + 최대랑 비교해서 계속 업데이트
                        max[i][j] = Math.max(max[i][j], max[i][k] + max[k+1][j]);
                        min[i][j] = Math.min(min[i][j], min[i][k] + min[k+1][j]);
                        //최소 + 최소
                    }else {// 음수연산. 얘가 최대이려면 최대 - 최소
                        max[i][j] = Math.max(max[i][j], max[i][k] - min[k+1][j]);
                        min[i][j] = Math.min(min[i][j], min[i][k] - max[k+1][j]);
                    }
                }
            }
            
        }
        return max[0][n-1];
    
    }
}
/**
연산자는 피연산자 2개를 요한다.
+연산자 앞뒤로는 최대값이 나와야 숫자가 커지고
-연산자는 최대에서 최소를 뺴야 숫자가 커진다.
그렇다면 자리별로 최대와 최소를 따로 추적한다
추적하며 끝까지 순회하면 완성할 수 있다. 

설계 고민: dp에 인덱스 어떻게 하나?
일단 숫자만 넣은 배열을 하나 따로 만들고 그거 따로 순회? 그래도 되지 뭐 그렇게 하고싶다면야

1 - 3 + 5 - 8이라면
피연산자는 3개
[][][]
[][][]
[][][]
*/