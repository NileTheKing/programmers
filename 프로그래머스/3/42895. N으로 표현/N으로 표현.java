import java.util.*;
class Solution {
    public int solution(int N, int number) {
        if (N == number) return 1;
        Set<Integer>[] dp = new Set[10];
        dp[0] = new HashSet<>();
        dp[1] = new HashSet<>();
        dp[1].add(N);
        
        for (int i = 2; i <= 8; i++) {
            dp[i] = new HashSet<>();
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < i; j++) sb.append(N);
            dp[i].add(Integer.parseInt(sb.toString()));
            
            for (int j = 1; j < i; j++) {//앞피연산자의숫자갯수
                int k = i - j; //뒷 피연산자의 수
                for (int a : dp[j]) {
                    for (int b : dp[k]) {
                        dp[i].add(a + b);
                        dp[i].add(a - b);
                        dp[i].add(a * b);
                        if (b != 0) dp[i].add(a / b);
                    }
                }
                
            }
            if (dp[i].contains(number)) return i;
        }
        return -1;
    }
}/**
재활용가능.. 지금 문제작아지고 활용하는거를 떠올렸는데 구현을 못하는게병목
기존 dp는 점화식으로 하는거고 이거는 음.. 뭔가 흠....

*/