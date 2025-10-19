import java.util.*;
class Solution {
    public int solution(int[] arr) {
        int[] dp = new int[arr.length];
        dp[0] = arr[0];
        
        for (int i = 1; i < arr.length; i++) {
            //이전 숫자랑 최대공약수 구하기
            int mcd = Math.min(dp[i-1], arr[i]);
            int tempMcd = mcd;
            while (mcd <= Math.max(dp[i-1], arr[i])) {
                if (dp[i-1] % mcd == 0 && dp[i - 1] % mcd == 0) tempMcd = mcd;
                mcd++;
            }
            //최대공약수로 최소공배수 구해서 대입
            int m = 1;
            mcd = tempMcd;
            while (mcd * m % dp[i - 1] != 0 || mcd * m % arr[i] != 0) {
                m++;
            }
            dp[i] = mcd * m;
        }
        
        return dp[arr.length - 1];
    }
}
/**
최소공배순느 최대공약수의 배수중 하나임
*/