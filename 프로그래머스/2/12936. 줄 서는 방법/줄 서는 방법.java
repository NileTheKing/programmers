import java.util.*;
class Solution {
    public int[] solution(int n, long k) {
        long[] factorial = new long[n+1];
        List<Integer> nums = new ArrayList<>();
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
            nums.add(i);
        }
        int[] ans = new int[n];
        k--;
        for (int i = 0; i < n; i++) {
            long fact = factorial[n - 1 - i];
            int idx = (int)(k / fact); //몫->무슨바구니
            ans[i] = (nums.get(idx));
            nums.remove(idx);
            //나머지 -> 바구니에서 위치 -> 지금은 바구니 내 위치지만 다음 전체위치..
            k %= fact;
        }
        return ans;
    }
}