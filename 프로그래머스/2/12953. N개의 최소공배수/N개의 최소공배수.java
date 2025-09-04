import java.util.*;
class Solution {
    public int solution(int[] arr) {
        Arrays.sort(arr);
        int[] dp = new int[arr.length]; //dp[0]은 arr[0]까지의 최소공배수
        dp[0] = arr[0];
        int smallest = 0;
        for (int a : arr) {
            if (a != 1) {
                smallest = a; 
                //System.out.printf("smallest :%d \n", smallest);
                break;
            }
        }
        
        for (int i = 1; i < arr.length; i++) {
            int prev = dp[i-1]; //이숫자랑 arr[i]의 공배수 구하면됨
            int current = arr[i];
            //일단 곱한다음에 최소공배수를 찾는다. (나눠질때까지 나눔)
            // 130을 80으로 나눕니다: 130 = 80 * 1 + 50 
            // 다음으로 80을 50으로 나눕니다: 80 = 50 * 1 + 30 
            // 다음으로 50을 30으로 나눕니다: 50 = 30 * 1 + 20 
            // 다음으로 30을 20으로 나눕니다: 30 = 20 * 1 + 10 
            // 다음으로 20을 10으로 나눕니다: 20 = 10 * 2 + 0 
            int a = current;
            int b = prev;
            while (true) {
                // a = 130 b = 80, r = 50
                int r = a % b;
                if (r == 0) break;
                a = b;
                b = r;
            }
            
            
            dp[i] = prev * current / b;
            //System.out.printf("dp[%d]= %d\n", i, dp[i]);
        }
        return dp[arr.length -1];
    }
}
/**
2 * 6 * 8 * 14 = 
dp같은게 이거 숫자 커지면 못담음.
100^15여봐 10^30임. 담을 수는 있겠는디 흠
만약 2면 2
2 6이면 6이지.
2 6 8이면 6ㅇ과 8의 최소공배수인 24가 답일거고. 왜? 48에서 2는 사실 2여도 가능인데 8이 24까지밖에 용납안됨.
2 6 8 14라면 24와 14의 최소공배수가 답일거임
몇이냐면 24 * 14응  280 + 56인 336임.
여기서 나누기2하면 이게 답 왜? 또 2못나눔.
dp 네 ㅋ

*/