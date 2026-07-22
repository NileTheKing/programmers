import java.util.*;
class Solution {
    public int solution(int[] money) {
        if (money.length == 1) return money[0];
        return Math.max(helper(Arrays.copyOfRange(money, 0, money.length - 1)), helper(Arrays.copyOfRange(money, 1, money.length)));
    }
    public int helper(int[] money) {
        if (money.length == 1) return money[0];
        if (money.length == 0) return 0;
        int[] dp = new int[money.length];
        dp[0] = money[0];
        dp[1] = Math.max(money[1], money[0]);
        for (int i = 2; i < money.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + money[i]);
        }
        // System.out.printf("====\n");
        // for (int d : dp) System.out.printf("%d ", d);
        System.out.println();
        return dp[dp.length - 1];
    }
}