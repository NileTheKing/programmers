import java.util.*;
class Solution {
    public int[] solution(int n, long k) {
        long[] factorial = new long[n + 1];
        factorial[0] = 1;
        List<Integer> bucket = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
            bucket.add(i);
        }
        //bucket: [1,2,3,4,5....n] 0inxed
        //몫과나머지, 자리 위치 구하니까 0indexed
        k--;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {//n자리구해야함
            long fact = factorial[n - 1 - i];//인덱스발사대
            int idx = (int) (k / fact);
            // k는필요한 순서고 실제 바구니번호는 묶임때문에 나눠 즉 1 2 3 4 5 6순서가 12->1 34->2 56->3으로 배정
            ans[i] = bucket.get(idx);
            bucket.remove(idx);
            k %= fact; //이제 필요한순서는..바구니 내 위치니까 fact하고 난 나머지
        }
        return ans;
    }
}