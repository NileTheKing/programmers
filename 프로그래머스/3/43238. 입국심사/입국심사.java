import java.util.*;
class Solution {
    public long solution(int n, int[] times) {
        long l = 1;
        long r = (long)Arrays.stream(times).max().getAsInt() * n;
        long ans = 0;
        while (l <= r) {
            long m = l + (r - l) / 2;//given time
            long done = 0;
            for (int t : times) {
                done += m / t;
                if (done >= n) break;
            }
            if (done >= n) {
                ans = m;
                r = m - 1;
            }else {
                l = m + 1;
            }
        }
        return ans;
    }
}