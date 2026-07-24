import java.util.*;
class Solution {
    public int[] solution(int n, long k) {
        long[] factorial = new long[n + 1];
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) factorial[i] = factorial[i - 1] * i;
        List<Integer> bucket = new ArrayList<>();
        for (int i = 0; i < n; i++) bucket.add(i + 1);//<<이거도맞는지모름
        //1 2 3 4 5
        //0 1 2 3 4
        // for (int b : bucket) System.out.printf("%d ", b);
        int[] ans = new int[n];
        k--; //0index..몫과나머지 컴퓨터용
        //몫은 어느바구니 나머지는 바구니 내 위치
        for (int i = 1; i <= n; i++) {
            //첫번째자리수
            long fact = factorial[n - i]; //뒤에나올애들
            int idx = (int) (k / fact);
            ans[i-1] = bucket.get(idx);
            bucket.remove(idx);
            k = k % fact;//다음 바구니는..현재바구니내위치가
        }
        return ans;
    }
}