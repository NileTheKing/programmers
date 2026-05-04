import java.util.*;
class Solution {
    public int solution(int[] arr) {
        Arrays.sort(arr);
        int[] dp = new int[arr.length];
        dp[0] = arr[0]; //dp[i] : i-1인덱스에서의 최소공배수.
        //매번 최소공배수 구해가직 ㅗ다음번에 그거가지고 지금숫자랑 확인
        for (int i = 1; i < arr.length; i++) {
            //이전거랑 지금숫자i-1인덱스랑 최소공배수 구하기.
            //근데 초기값필요하니까..
            //지금
            // System.out.printf("===start of i : %d===\n", i);
            int prev = dp[i - 1];
            int current = arr[i];
            int gcd = prev;//최대공약수
            //지금숫자 arr[i]랑 prev랑 구하면됨.. 이거어케구하냐면..
            //그냥 둘중 더 작은거 해가지고 뭐 1씩더하면되지않나..종료조건을
            //^2
            int min = Math.min(prev, current);//더작은거로 시작
            for (int j = 2; j <= min; j++) {
                if (prev % j == 0 && current % j == 0) gcd = j;
            }
            // System.out.printf("i, prev, current, gcd: %d %d %d %d\n", i, prev, current, gcd);
            //gcd구했음. 이제 얘의 배수가지고 최대공배수구해서 dp
            int tmp = gcd;
            while ((prev != 0 && current != 0) && !(gcd % prev == 0 && gcd % current == 0)) {
                //gcd의 배수로 prev이랑 current둘다 나누면 최소공배
                gcd += tmp;
                // System.out.printf("gcd: %d\n",gcd);
            }
            dp[i] = gcd;
            // System.out.printf("dp[%d] = %d\n", i, dp[i]);
            // System.out.printf("===end of i : %d===\n", i);
        }
        return dp[arr.length - 1];
    }
}
/**
수학적으로.. 최소공배수는 최대공약수의 배수중 만족하는 놈이다.
그러면 일단 최소공배수를 구해놓고 걔 배수가지고 되는놈리턴하면됨.
근데 그거를 이제 반복
음.. 길이 15..원소는100이하니까
그냥 n^2해도 충분한거아님?
아 그래서 dp구만 ?모두 새로할필요가없음
*/